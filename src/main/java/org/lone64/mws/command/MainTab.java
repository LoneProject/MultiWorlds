package org.lone64.mws.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTab implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if (sender instanceof Player player) {
            if (player.hasPermission("mws.admin")) {
                if (args.length == 1) {
                    tabs.addAll(Arrays.asList("create", "remove", "load", "unload", "reset", "backup", "warp"));
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("create")) {
                        tabs.add("[ 생성할 월드 ]");
                    } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")
                            || args[0].equalsIgnoreCase("load") || args[0].equalsIgnoreCase("unload")
                            || args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("backup")
                            || args[0].equalsIgnoreCase("warp")) {
                        tabs.addAll(getWorlds());
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("create")) {
                        tabs.addAll(Arrays.asList("normal", "nether", "the_end"));
                    }
                }
            }
        }
        return tabs;
    }

    private List<String> getWorlds() {
        List<String> stringList = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            stringList.add(world.getName());
        }
        return stringList;
    }

}
