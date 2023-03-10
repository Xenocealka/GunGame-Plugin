package com.xenoceal.gungame.listeners;

import com.xenoceal.gungame.game.GameManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

@RequiredArgsConstructor
public final class PlayerDropItemListener
        implements Listener {

    private final GameManager gameManager;

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

}
