package org.woahhs.utils;

import org.bukkit.ChatColor;

public class Chat {

    public String translateChat(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }


}
