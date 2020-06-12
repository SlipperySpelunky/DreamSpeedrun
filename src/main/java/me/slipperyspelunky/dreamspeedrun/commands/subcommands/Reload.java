package me.slipperyspelunky.dreamspeedrun.commands.subcommands;

import me.slipperyspelunky.dreamspeedrun.DreamSpeedrun;
import me.slipperyspelunky.dreamspeedrun.Methods;
import me.slipperyspelunky.dreamspeedrun.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Reload extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Simply reloads the configuration file!";
    }

    @Override
    public String getSyntax() {
        return "/speedrun reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        final Methods methods = new Methods();

        if(sender.hasPermission("speedrun.reload")) {
            DreamSpeedrun.getPlugin(DreamSpeedrun.class).reloadConfig();
            sender.sendMessage(DreamSpeedrun.getPrefix() + ChatColor.GREEN + "Successfully reloaded configuration!");
        }else{
            sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&cYou do not have permission to execute this command!"));
        }
    }
}
