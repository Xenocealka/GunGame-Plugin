package com.xenoceal.gungame.utility;

import lombok.val;
import lombok.var;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Wool;

import java.util.ArrayList;

public final class ItemUtil {

    private final ItemStack item;

    public ItemUtil(Material material) {
        this.item = new ItemStack(material);
    }

    public ItemUtil amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemUtil name(String name) {
        val meta = item.getItemMeta();
        meta.setDisplayName(name);
		item.setItemMeta(meta);
        return this;
    }

    public ItemUtil lore(String line) {
        val meta = item.getItemMeta();
        var lore = meta.getLore();

        if (lore == null)
            lore = new ArrayList<>();

        lore.add(line);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return this;
    }

    public ItemUtil unbreakable(boolean unbreakable) {
        val meta = item.getItemMeta();
        meta.setUnbreakable(unbreakable);
        item.setItemMeta(meta);
        return this;
    }

    public ItemUtil durability(short durability) {
        item.setDurability(durability);
        return this;
    }

    public ItemUtil enchantment(Enchantment enchantment, int level) {
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemUtil enchantment(Enchantment enchantment) {
        return enchantment(enchantment, 1);
    }

    public ItemUtil type(Material material) {
        item.setType(material);
        return this;
    }

    public ItemUtil clearEnchantments() {
        for (val enchantment : item.getEnchantments().keySet())
            item.removeEnchantment(enchantment);
        return this;
    }

    public ItemUtil color(DyeColor color) {
        switch (item.getType()) {
            case LEATHER_HELMET:
            case LEATHER_BOOTS:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
                val meta = (LeatherArmorMeta) item.getItemMeta();
                meta.setColor(color.getColor());
                item.setItemMeta(meta);
                break;
            case WOOL:
                val data = (Wool) item.getData();
                data.setColor(color);
                item.setData(data);
        }
        return this;
    }

    public ItemStack build() {
        return item;
    }

}
