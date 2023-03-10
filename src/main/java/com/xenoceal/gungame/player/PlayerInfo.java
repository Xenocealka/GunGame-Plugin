package com.xenoceal.gungame.player;

import com.xenoceal.gungame.game.GameManager;
import com.xenoceal.gungame.game.GameState;
import com.xenoceal.gungame.utility.ItemUtil;
import fr.mrmicky.fastboard.FastBoard;
import lombok.val;
import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public final class PlayerInfo {

    private final UUID uniqueId;

    private final FastBoard board;

    private final GameManager gameManager;

    private Level level;

    private int score;
	
	private int kills;

    public PlayerInfo(UUID uniqueId, FastBoard board, GameManager gameManager) {
        this.uniqueId = uniqueId;
        this.board = board;
        this.gameManager = gameManager;
        this.level = null;
        this.score = 0;
		this.kills = 0;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public FastBoard getBoard() {
        return board;
    }

    public Level getLevelByRequired() {
		val levels = Level.values();
		for (var i = levels.length - 1; i >= 0; i--)
			if (score >= levels[i].getRequired())
				return levels[i];
		return Level.FIRST;
	}

    public int getLevel() {
        return level.getValue();
    }

    public void setLevel(Level level) {
        if (level.getValue()<= 0 || level.getValue() > 11) return;
        if (this.level == level) return;

        this.level = level;

        val inventory = getPlayer().getInventory();

        inventory.clear();

        switch (level) {
            case FIRST:
                inventory.setArmorContents(new ItemStack[] {
						new ItemUtil(Material.LEATHER_BOOTS)
                                .unbreakable(true)
                                .build(),
						new ItemUtil(Material.LEATHER_LEGGINGS)
                                .unbreakable(true)
                                .build(),
						new ItemUtil(Material.LEATHER_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.WOOD_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case SECOND:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.WOOD_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case THIRD:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.STONE_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case FOURTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.STONE_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case FIFTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.LEATHER_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.STONE_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case SIXTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.STONE_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case SEVENTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.IRON_SWORD)
                        .unbreakable(true)
                        .build());
                break;
            case EIGHTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.IRON_SWORD)
                        .enchantment(Enchantment.DAMAGE_ALL)
                        .unbreakable(true)
                        .build());
                break;
            case NINTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.BOW)
                        .enchantment(Enchantment.ARROW_INFINITE)
                        .unbreakable(true)
                        .build());
                inventory.addItem(new ItemUtil(Material.ARROW)
                        .build());
                break;
            case TENTH:
                inventory.setArmorContents(new ItemStack[] {
                        new ItemUtil(Material.CHAINMAIL_BOOTS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_LEGGINGS)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_CHESTPLATE)
                                .unbreakable(true)
                                .build(),
                        new ItemUtil(Material.CHAINMAIL_HELMET)
                                .unbreakable(true)
                                .build()
                });
                inventory.addItem(new ItemUtil(Material.BOW)
                        .enchantment(Enchantment.ARROW_DAMAGE)
                        .enchantment(Enchantment.ARROW_INFINITE)
                        .unbreakable(true)
                        .build());
                inventory.addItem(new ItemUtil(Material.ARROW)
                        .build());
				break;
            case ELEVEN:
                gameManager.setState(GameState.ENDED);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        setLevel(getLevelByRequired());
    }

	public int getKills() {
		return kills;
	}
	
	public void setKills(int kills) {
		this.kills = kills;
	}

    public void reset() {
        level = null;
        score = 0;
		kills = 0;
    }

}
