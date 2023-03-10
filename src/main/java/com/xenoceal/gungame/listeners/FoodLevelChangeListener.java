package com.xenoceal.gungame.listeners;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.game.GameState;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

@RequiredArgsConstructor
public final class FoodLevelChangeListener
        implements Listener {

	private final GameManager gameManager;

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

}
