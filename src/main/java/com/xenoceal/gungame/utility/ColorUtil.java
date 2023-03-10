package com.xenoceal.gungame.utility;

import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;
import org.bukkit.ChatColor;

@UtilityClass
public final class ColorUtil {

    public String colorize(String str, Object... args) {
        return ChatColor.translateAlternateColorCodes('&', String.format(str, args));
    }

    public String[] colorize(String[] lines, Object... args) {
        val s = new String[lines.length];
        for (var i = 0; i < s.length; ++i)
            s[i] = ChatColor.translateAlternateColorCodes('&', lines[i]);
        return s;
    }

}
