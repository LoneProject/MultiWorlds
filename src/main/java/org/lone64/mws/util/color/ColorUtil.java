package org.lone64.mws.util.color;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String fetchColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
