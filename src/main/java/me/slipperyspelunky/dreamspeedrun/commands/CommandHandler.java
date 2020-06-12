package me.slipperyspelunky.dreamspeedrun.commands;

import me.slipperyspelunky.dreamspeedrun.DreamSpeedrun;
import me.slipperyspelunky.dreamspeedrun.Methods;
import me.slipperyspelunky.dreamspeedrun.commands.subcommands.Reload;
import me.slipperyspelunky.dreamspeedrun.commands.subcommands.SetSpeedRunner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements TabExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    private final Methods methods = new Methods();

    public CommandHandler() {
        subCommands.add(new SetSpeedRunner());
        subCommands.add(new Reload());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("speedrun.main")) {
            if (args.length > 0) {
                if (subCommandExists(args[0])) {
                    for (SubCommand subCommand : getSubCommands()) {
                        if (args[0].equalsIgnoreCase(subCommand.getName())) {
                            String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

                            subCommand.perform(sender, newArgs);
                        }
                    }
                } else {
                    sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&cThat is not a valid command!"));
                }
            } else {
                sender.sendMessage("---------------------------------");
                sender.sendMessage(methods.translateColor("&8&lDREAMSPEEDRUN HELP"));
                sender.sendMessage("---------------------------------");
                for (SubCommand subCommand : getSubCommands()) {
                    sender.sendMessage(methods.translateColor("&e" + subCommand.getSyntax() + " &6- " + "&e" + subCommand.getDescription()));
                    sender.sendMessage("---------------------------------");
                }
            }
        }else{
            sender.sendMessage(DreamSpeedrun.getPrefix() + methods.translateColor("&cYou do not have permission to execute this command!"));
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    private boolean subCommandExists(String name) {
        for(SubCommand subCommand : getSubCommands()) {
            if(subCommand.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            ArrayList<String> subCommandArguments = new ArrayList<>();

            for(SubCommand subCommand : getSubCommands()) {
                subCommandArguments.add(subCommand.getName());
            }

            return subCommandArguments;
        }
        return null;
    }
}
