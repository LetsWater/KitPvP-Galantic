package me.flame.galantic.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String format(String input){

        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
