package com.xenoceal.gungame;

import com.xenoceal.gungame.command.CommandRegister;
import com.xenoceal.gungame.command.impl.StartGameCommand;
import com.xenoceal.gungame.game.GameManager;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public final class GunGame
        extends JavaPlugin {

    @Override
    public void onEnable() {
        val gameManager = new GameManager(this, 24, 16);

        val packageName = getClass().getPackage().getName();
        try {
            val reflections = new Reflections(packageName + ".listeners");
            for (val clazz : reflections.getSubTypesOf(Listener.class)) {
                val listener = clazz.getDeclaredConstructor(GameManager.class).newInstance(gameManager);
                getServer().getPluginManager().registerEvents(listener, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (val info : gameManager.getPlayers())
                gameManager.updateBoards(info);
        }, 0, 20);

        CommandRegister.register(this, new StartGameCommand(gameManager), new String[] { "start" }, "Starts the game", "/start");

        saveDefaultConfig();
        getLogger().info("GunGame plugin was enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("GunGame plugin was disabled");
    }

}
