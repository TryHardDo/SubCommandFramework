package dev.tryharddo;

import dev.tryharddo.interfaces.ISubCommand;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public final class SubCommandFramework {
    private final SubCommandRegistry registry;
    private final BaseCommandHandler baseHandler;
    private final JavaPlugin pluginInstance;

    public SubCommandFramework(JavaPlugin instance, String baseCommand, String description, String usageMessage, List<String> aliases) {
        this.registry = new SubCommandRegistry();
        this.baseHandler = new BaseCommandHandler(this.registry, baseCommand, description, usageMessage, aliases);
        this.pluginInstance = instance;
    }

    private void registerBaseHandler() {
        try {
            final Server server = pluginInstance.getServer();
            final Field bukkitCommandMap = server.getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(server);

            commandMap.register(baseHandler.getLabel(), baseHandler);
        } catch (Exception | NoSuchMethodError e) {
            pluginInstance.getLogger().severe("Could not load plugin command handler.");
            e.printStackTrace();
        }
    }

    public BaseCommandHandler getBaseHandler() {
        return baseHandler;
    }

    public SubCommandRegistry getRegistry() {
        return registry;
    }
}
