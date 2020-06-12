package me.slipperyspelunky.dreamspeedrun.listeners;

import me.slipperyspelunky.dreamspeedrun.DreamSpeedrun;
import me.slipperyspelunky.dreamspeedrun.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemRightClick implements Listener {
    private final Methods methods = new Methods();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Player target = Bukkit.getPlayer(methods.getConfigString("speedrunner"));

        if(target == null) target = player;

        ItemStack whatToGet = player.getInventory().getItemInHand();
        if(Bukkit.getVersion().contains("1.8"))

        if(!(methods.getConfigString("speedrunner").equals(player.getName()))) {
            if(!(Bukkit.getVersion().contains("1.8"))) {
                if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && (player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS) || player.getInventory().getItemInOffHand().getType().equals(Material.COMPASS))) {
                    player.setCompassTarget(target.getLocation());
                    player.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&aCompass is now pointing to &e" + target.getName() + "&a."));
                }
            }else{
                if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getIte) {
                    player.setCompassTarget(target.getLocation());
                    player.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&aCompass is now pointing to &e" + target.getName() + "&a."));
                }
            }
        }
    }
}
