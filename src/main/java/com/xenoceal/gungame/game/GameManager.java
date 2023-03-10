package com.xenoceal.gungame.game;

import com.xenoceal.gungame.GunGame;
import com.xenoceal.gungame.player.Level;
import com.xenoceal.gungame.player.PlayerInfo;
import com.xenoceal.gungame.tasks.GameEndCountdown;
import com.xenoceal.gungame.tasks.GameStartCountdown;
import com.xenoceal.gungame.utility.ColorUtil;
import fr.mrmicky.fastboard.FastBoard;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class GameManager {

    final GunGame plugin;

    GameState state = GameState.WAITING;

    final List<PlayerInfo> players;

    final int maxPlayers;
    final int minPlayers;

    /* tasks */
    GameStartCountdown gameStartCountdown;
    GameEndCountdown gameEndCountdown;

	Player winner;

    public GameManager(GunGame plugin, int maxPlayers, int minPlayers) {
        this.plugin = plugin;
        this.players = new ArrayList<>();
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
		this.winner = null;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        if (this.state == state)
            return;

        this.state = state;

        switch (state) {
            case WAITING:
                gameEndCountdown = null;

				for (val info : players)
					info.reset();

                for (val player : Bukkit.getOnlinePlayers())
                    player.kickPlayer("restart");
				
				winner = null;
				break;
            case STARTING:
                gameStartCountdown = new GameStartCountdown(this);
                gameStartCountdown.runTaskTimer(plugin, 0L, 20L);
                break;
            case STARTED:
                gameStartCountdown = null;

                for (val info : players)
                    info.setLevel(Level.FIRST);
                break;
            case ENDED:
				for (val info : players) {
					if (info.getLevel() == Level.ELEVEN.getValue()) {
						winner = info.getPlayer();
						break;
					}
				}
				
				if (winner == null)
					return;
			
                for (val info : players) {
					val player = info.getPlayer();
					
                    player.getInventory().clear();
					
                    player.sendMessage(ColorUtil.colorize(
                            "&ai &fИгра окончена. Победил игрок &b%s",
                            winner.getName()
                    ));
                }

                gameEndCountdown = new GameEndCountdown(this);
                gameEndCountdown.runTaskTimer(plugin, 0L, 20L);
        }
    }

    public List<PlayerInfo> getPlayers() {
        return players;
    }

    public PlayerInfo getPlayerInfo(Player player) {
        for (val info : players)
            if (info.getUniqueId().equals(player.getUniqueId()))
                return info;
        return null;
    }

    public void addPlayer(Player player) {
        val board = new FastBoard(player);
        board.updateTitle("§6Gun§fGame");
        players.add(new PlayerInfo(player.getUniqueId(), board, this));
    }

    public void removePlayer(Player player) {
        for (val info : players) {
            if (info.getUniqueId().equals(player.getUniqueId())) {
                info.getBoard().delete();
                players.remove(info);
                return;
            }
        }
    }

    public Player getLeader() {
        val sorted = new ArrayList<>(players);
		sorted.sort((info1, info2) -> {
			val o1 = info1.getLevel() + info1.getScore();
			val o2 = info2.getLevel() + info2.getScore();
			return Integer.compare(o2, o1);
		});
		return sorted.get(0).getPlayer();
    }

    public int getPlayersCount() {
        return getPlayers().size();
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void updateBoards(PlayerInfo info) {
        String[] lines = null;

        switch (state) {
            case WAITING:
            case STARTING:
                lines = ColorUtil.colorize(new String[] {
                        "",
                        "&aСтатус &7» &b" + state.getText(),
                        "&aИгроки &7» &b" + getPlayersCount(),
                        "",
                        "vk.com/xenoceal"
                });
                break;
            case STARTED:
                lines = ColorUtil.colorize(new String[] {
                        "",
                        "&aСтатус &7» &b" + state.getText(),
                        "&aИгроки &7» &b" + getPlayersCount(),
                        "",
                        "&aЛидер &7» &b" + getLeader().getName(),
                        "",
                        "&aУровень &7» &b" + info.getLevel(),
                        "&aОчков &7» &b" + info.getScore(),
                        "",
						"&aУбийств &7» &b" + info.getKills(),
						"",
                        "vk.com/xenoceal"
                });
                break;
            case ENDED:
				if (winner == null)
					return;
			
                lines = ColorUtil.colorize(new String[] {
                        "",
                        "&aСтатус &7» &b" + state.getText(),
                        "&aИгроки &7» &b" + getPlayersCount(),
                        "",
                        "&aПобедитель &7» &b" + winner.getName(),
                        "",
                        "vk.com/xenoceal"
                });
        }

        if (lines == null)
            return;

        info.getBoard().updateLines(lines);
    }

    public GunGame getPlugin() {
        return plugin;
    }

}
