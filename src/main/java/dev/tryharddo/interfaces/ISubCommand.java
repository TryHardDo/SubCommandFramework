package dev.tryharddo.interfaces;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

public interface ISubCommand {
    /**
     * Gets the labels of the sub command which triggers
     * the execution.
     *
     * @return array of labels
     */
    String[] getLabels();

    /**
     * Gets the execution boolean for console command sender.
     *
     * @return true if it is console executable, false otherwise
     */
    boolean isConsoleExecutable();

    /**
     * Gets the required permission to execute the command.
     *
     * @return the permission object for the execution
     */
    Permission getExecutionPermission();

    /**
     * Gets the description of the command.
     *
     * @return the description string
     */
    String getCommandDescription();

    /**
     * The execution method which will be called by the command router
     * if the requested command match with any of the labels.
     *
     * @return true if the execution is considered as a success, false otherwise
     */
    boolean execute(@NotNull CommandSender sender, @NotNull String subArgument, @NotNull String[] args);
}
