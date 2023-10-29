package dev.tryharddo;

import dev.tryharddo.interfaces.ISubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseCommandHandler extends Command {
    private final SubCommandRegistry registry;

    public BaseCommandHandler(@NotNull SubCommandRegistry registry, @NotNull String baseCommand, @NotNull String baseDescription, @NotNull String usageMessage, @NotNull List<String> baseAliases) {
        super(baseCommand, baseDescription, usageMessage, baseAliases);
        this.registry = registry;
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String @NotNull [] args) {
        // Check if no arguments provided
        if (args.length == 0) {
            // Handle case where no arguments provided
            return true;
        }

        for (ISubCommand subCommand : this.registry.getSubCommandRegistry()) {
            if (!subCommand.getLabels().contains(args[0].toLowerCase())) {
                // Handle case where no matching sub-command found
                return true;
            }

            if (commandSender instanceof ConsoleCommandSender && !subCommand.isConsoleExecutable()) {
                // Handle case where command is only executable by player
                return true;
            }

            if (!commandSender.hasPermission(subCommand.getExecutionPermission())) {
                // Handle case where sender lacks necessary permission
                return true;
            }

            return subCommand.execute(commandSender, s, this.trimArgs(args));
        }

        return true;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args);
    }

    private String @NotNull [] trimArgs(String @NotNull [] argsWithSubCommand) {
        String[] trimmed = new String[argsWithSubCommand.length - 1];

        System.arraycopy(argsWithSubCommand, 1, trimmed, 0, argsWithSubCommand.length - 1);

        return trimmed;
    }
}
