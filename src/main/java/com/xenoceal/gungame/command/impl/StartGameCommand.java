package com.xenoceal.gungame.command.impl;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.game.GameState;
import com.xenoceal.gungame.utility.ColorUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class StartGameCommand
        implements CommandExecutor {

    private final GameManager gameManager;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        val player = (Player) sender;

        if (!player.hasPermission("gungame.start")) {
            player.sendMessage(ColorUtil.colorize("&cYou don't have permission to execute this command :c"));
            return true;
        }

        gameManager.setState(GameState.STARTING);

        return true;
    }

}
