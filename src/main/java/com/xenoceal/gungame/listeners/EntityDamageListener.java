package com.xenoceal.gungame.listeners;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.game.GameState;
import com.xenoceal.gungame.utility.ColorUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public final class EntityDamageListener
        implements Listener {

    private final GameManager gameManager;

    private final Map<Entity, Entity> damaged = new HashMap<>();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        val player = (Player) e.getEntity();

        if (gameManager.getState() != GameState.STARTED) {
            e.setCancelled(true);
        } else {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
                return;
            }

            if (e.getFinalDamage() >= player.getHealth()) {
                player.setHealth(20.0D);

                val killer = (Player) getKiller(player);

                if (killer != null) {
                    for (val info : gameManager.getPlayers()) {
                        info.getPlayer().sendMessage(ColorUtil.colorize(
                                "&ai &fИгрок &e%s &fбыл убит игроком &e%s",
                                player.getName(),
                                killer.getName()
                        ));
					}
						
                    damaged.remove(player);
					
					val killerInfo = gameManager.getPlayerInfo(killer);
					if (killerInfo != null) {
						killerInfo.setScore(killerInfo.getScore() + 12);
						killerInfo.setKills(killerInfo.getKills() + 1);
					}
					
					killer.sendMessage("§a+12 очков");
				}

                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (gameManager.getState() != GameState.STARTED)
            return;

        if (!(e.getEntity() instanceof Player))
            return;

        val player = (Player) e.getEntity();

        if (e.getDamager() instanceof Player) {
            val damager = (Player) e.getDamager();
            if (!damaged.containsKey(player))
                damaged.put(player, damager);
            else
				damaged.replace(player, damager);
        } else if (e.getDamager() instanceof Arrow) {
            val arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Player) {
                val shooter = (Player) arrow.getShooter();
                if (!damaged.containsKey(player))
                    damaged.put(player, shooter);
                else
					damaged.replace(player, shooter);
            }
        }
    }

    private Entity getKiller(Entity entity) {
        return damaged.getOrDefault(entity, null);
    }

}
