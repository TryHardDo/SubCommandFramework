package dev.tryharddo;

import dev.tryharddo.interfaces.ISubCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

public class SubCommandRegistry {
    private final HashSet<ISubCommand> subCommandRegistry;

    public SubCommandRegistry() {
        this.subCommandRegistry = new HashSet<>();
    }
    public boolean registerSubCommand(ISubCommand subCommand) {
        return this.subCommandRegistry.add(subCommand);
    }

    public boolean unregisterSubCommand(ISubCommand subCommand) {
        return this.subCommandRegistry.remove(subCommand);
    }

    public void unregisterAll() {
        this.subCommandRegistry.clear();
    }

    public @NotNull Collection<ISubCommand> registerAll(ISubCommand @NotNull ... subCommands) {
        HashSet<ISubCommand> added = new HashSet<>();
        for (ISubCommand sc : subCommands) {
            if (registerSubCommand(sc)) {
                added.add(sc);
            }
        }

        return added;
    }

    public HashSet<ISubCommand> getSubCommandRegistry() {
        return subCommandRegistry;
    }
}
