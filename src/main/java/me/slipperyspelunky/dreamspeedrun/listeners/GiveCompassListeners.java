package me.slipperyspelunky.dreamspeedrun.listeners;

import me.slipperyspelunky.dreamspeedrun.Methods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCompassListeners implements Listener {
    private final Methods methods = new Methods();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        giveCompass(e.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        giveCompass(e.getPlayer());
    }

    private void giveCompass(Player p) {
        if(!(methods.getConfigString("speedrunner").equals(p.getName()))) {
            if(!(p.getInventory().contains(Material.COMPASS))) {
                ItemStack compass = new ItemStack(Material.COMPASS);
                ItemMeta compass_meta = compass.getItemMeta();

                compass_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lRIGHT CLICK TO TRACK"));
                compass.setItemMeta(compass_meta);

                p.getInventory().addItem(compass);
            }
        }
    }
}
