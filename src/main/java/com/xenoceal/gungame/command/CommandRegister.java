package com.xenoceal.gungame.command;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandRegister
        extends Command implements PluginIdentifiableCommand {

    Plugin plugin;
    CommandExecutor executor;

    public CommandRegister(String[] aliases, String desc, String usage, CommandExecutor executor, Plugin plugin) {
        super(aliases[0], desc, usage, Arrays.asList(aliases));
        this.plugin = plugin;
        this.executor = executor;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!testPermission(sender))
            return true;
        if (executor.onCommand(sender, this, s, strings)) {
            return true;
        } else {
            sender.sendMessage(usageMessage);
            return false;
        }
    }

    public static void register(Plugin plugin, CommandExecutor executor, String[] aliases, String desc, String usage) {
        try {
            val register = new CommandRegister(aliases, desc, usage, executor, plugin);
            val field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            val map = (CommandMap) field.get(Bukkit.getServer());
            map.register(plugin.getDescription().getName(), register);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
