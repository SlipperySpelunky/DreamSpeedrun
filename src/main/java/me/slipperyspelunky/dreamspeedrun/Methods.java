package me.slipperyspelunky.dreamspeedrun;

import org.bukkit.ChatColor;

public class Methods {
    public String getConfigString(String path) {
        return DreamSpeedrun.getPlugin(DreamSpeedrun.class).getConfig().getString(path);
    }

    public boolean getConfigBoolean(String path) {
        return DreamSpeedrun.getPlugin(DreamSpeedrun.class).getConfig().getBoolean(path);
    }

    public String translateColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void log(String message) {
        System.out.println(DreamSpeedrun.getPrefix() + translateColor(message));
    }
}
