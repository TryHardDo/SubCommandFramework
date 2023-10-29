package dev.tryharddo.interfaces;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface ISubCommand {
    List<String> getLabels();

    boolean isConsoleExecutable();

    Permission getExecutionPermission();

    String getCommandDescription();

    boolean execute(@NotNull CommandSender sender, @NotNull String subCommand, @NotNull String[] trimmedArgs);

    List<String> brigadier(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args);
}
