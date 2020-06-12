package me.slipperyspelunky.dreamspeedrun;

import me.slipperyspelunky.dreamspeedrun.commands.CommandHandler;
import me.slipperyspelunky.dreamspeedrun.listeners.InventoryClick;
import me.slipperyspelunky.dreamspeedrun.listeners.ItemRightClick;
import me.slipperyspelunky.dreamspeedrun.listeners.GiveCompassListeners;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class DreamSpeedrun extends JavaPlugin {
    private final Methods methods = new Methods();

    @Override
    public void onEnable() {
        if(methods.getConfigBoolean("enabled")) {

            getServer().getPluginManager().registerEvents(new GiveCompassListeners(), this);
            getServer().getPluginManager().registerEvents(new ItemRightClick(), this);
            getServer().getPluginManager().registerEvents(new InventoryClick(), this);

            getCommand("speedrun").setExecutor(new CommandHandler());

            getConfig().options().copyDefaults();
            saveDefaultConfig();

            methods.log("&aI have loaded myself! Yay.");
        }else{
            methods.log("&eI am not allowed to load! Attempting to disable myself...");
            try {
                getServer().getPluginManager().disablePlugin(this);
            } catch (Exception e) {
                methods.log("&cFailed to disable myself! Logging error. (PLEASE REPORT THIS!)");
                e.printStackTrace();
            }
        }
    }

    public static String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&',"&8&lDreamSpeedrun&f>&r ");
    }
}
