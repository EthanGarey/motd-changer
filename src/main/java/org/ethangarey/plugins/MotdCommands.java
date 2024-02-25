package org.ethangarey.plugins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MotdCommands implements CommandExecutor, TabCompleter {

    MotdChanger plugin;

    public MotdCommands(MotdChanger plugin) {
        this.plugin = plugin;
        plugin.getCommand("motdmanager").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("reload")) {
                MotdChanger.updateConfig(plugin);
                sender.sendMessage("reload complete");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add("reload");
            return list;
        }
        return Collections.EMPTY_LIST;
    }
}
