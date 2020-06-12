package me.slipperyspelunky.dreamspeedrun.listeners;

import me.slipperyspelunky.dreamspeedrun.DreamSpeedrun;
import me.slipperyspelunky.dreamspeedrun.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class InventoryClick implements Listener {
    private final Methods methods = new Methods();
    private final Plugin compassTracker = DreamSpeedrun.getPlugin(DreamSpeedrun.class);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getCurrentItem() == null) return;

        if(e.getView().getTitle().equals(methods.translateColor("&a&lCHOOSE WHO WILL SPEEDRUN!"))) {
            e.setCancelled(true);
            
            Material materialToCheck = Material.valueOf(Bukkit.getVersion().contains("1.8") ? "SKULL_ITEM" : "PLAYER_HEAD");
            
            if(e.getCurrentItem().getType().equals(materialToCheck)) {
                ItemMeta playerMeta = e.getCurrentItem().getItemMeta();

                compassTracker.getConfig().set("speedrunner", playerMeta.getDisplayName());
                compassTracker.saveConfig();
                e.getWhoClicked().sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&aSuccessfully set speedrunner to &e" + playerMeta.getDisplayName() + "&a!"));

                e.getWhoClicked().closeInventory();
            }
        }
    }
}
