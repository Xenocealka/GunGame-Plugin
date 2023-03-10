package com.xenoceal.gungame.listeners;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.utility.ColorUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public final class PlayerQuitListener
        implements Listener {

    private final GameManager gameManager;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);

        val player = e.getPlayer();

        gameManager.removePlayer(player);

        for (val info : gameManager.getPlayers()) {
            val p = info.getPlayer();
            p.sendMessage(ColorUtil.colorize(
                    "&ai &fИгрок &e%s &fвышел из игры &8(&b%d&7/&a%d&8)",
                    player.getName(),
                    gameManager.getPlayersCount(),
                    gameManager.getMaxPlayers()
            ));
        }
    }

}
