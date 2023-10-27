package dev.tryharddo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseCommandHandler extends Command {
    public BaseCommandHandler(@NotNull String baseCommand, @NotNull String baseDescription, @NotNull String usageMessage, @NotNull List<String> baseAliases) {
        super(baseCommand, baseDescription, usageMessage, baseAliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        return false;
    }
}
