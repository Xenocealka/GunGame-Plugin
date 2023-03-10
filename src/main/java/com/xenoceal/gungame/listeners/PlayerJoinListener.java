package com.xenoceal.gungame.listeners;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.game.GameState;
import com.xenoceal.gungame.utility.ColorUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public final class PlayerJoinListener
        implements Listener {

    private final GameManager gameManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        val player = e.getPlayer();

        gameManager.addPlayer(player);

        for (val info : gameManager.getPlayers()) {
            val p = info.getPlayer();
            p.sendMessage(ColorUtil.colorize(
                    "&ai &fИгрок &e%s &fподключился к игре &8(&b%d&7/&a%d&8)",
                    player.getName(),
                    gameManager.getPlayersCount(),
                    gameManager.getMaxPlayers()
            ));
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.getInventory().clear();

        if (gameManager.getPlayersCount() == gameManager.getMinPlayers())
            gameManager.setState(GameState.STARTING);
    }

}
