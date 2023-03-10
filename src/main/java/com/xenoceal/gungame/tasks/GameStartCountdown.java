package com.xenoceal.gungame.tasks;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.game.GameState;
import com.xenoceal.gungame.utility.ColorUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class GameStartCountdown
        extends BukkitRunnable {

    final GameManager gameManager;

    int timeLeft = 10;

    @Override
    public void run() {
        if (timeLeft <= 0) {
            cancel();
            gameManager.setState(GameState.STARTED);
            return;
        }

        for (val info : gameManager.getPlayers())
            info.getPlayer().sendMessage(ColorUtil.colorize("&ai &fИгра начнется через &b%d &fсек", timeLeft));

        --timeLeft;
    }

}
