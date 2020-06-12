package me.slipperyspelunky.dreamspeedrun.commands.subcommands;

import me.slipperyspelunky.dreamspeedrun.DreamSpeedrun;
import me.slipperyspelunky.dreamspeedrun.Methods;
import me.slipperyspelunky.dreamspeedrun.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class SetSpeedRunner extends SubCommand {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Use this command to set the speed runner! You can also do this by going to the config.yml.";
    }

    @Override
    public String getSyntax() {
        return "/speedrun set <optional_player>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        final Methods methods = new Methods();
        final Plugin compassTracker = DreamSpeedrun.getPlugin(DreamSpeedrun.class);

        if(sender.hasPermission("speedrun.setspeedrunner")) {
            if (args.length != 0) {
                Player speedRunner = Bukkit.getPlayer(args[0]);

                if (!(speedRunner == null)) {
                    compassTracker.getConfig().set("speedrunner", speedRunner.getName());
                    compassTracker.saveConfig();
                    sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&aSuccessfully set speedrunner to &e" + speedRunner.getName() + "&a!"));
                } else {
                    sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&cPlayer specified is not online."));
                }
            } else {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    Inventory menu = Bukkit.createInventory(p, 54, methods.translateColor("&a&lCHOOSE WHO WILL SPEEDRUN!"));

                    ArrayList<Player> onlinePlayers = new ArrayList<>(p.getServer().getOnlinePlayers());

                    for (Player onlinePlayer : onlinePlayers) {
                        ItemStack playerHead;

                        if(Bukkit.getVersion().contains("1.8")) {
                            playerHead = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (byte) 3);
                        }else{
                            playerHead = new ItemStack(Material.valueOf("PLAYER_HEAD"));
                        }

                        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();

                        if(!(Bukkit.getVersion().contains("1.8"))) {
                            playerHeadMeta.setOwningPlayer(onlinePlayer);
                        }else{
                            playerHeadMeta.setOwner(onlinePlayer.getName());
                        }
                        playerHeadMeta.setDisplayName(onlinePlayer.getName());
                        playerHead.setItemMeta(playerHeadMeta);

                        menu.addItem(playerHead);
                    }

                    p.openInventory(menu);
                } else {
                    sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&cYou must be a player to run this command without arguments."));
                }
            }
        }else{
            sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&cYou do not have permission to execute this command!"));
        }
    }
}
