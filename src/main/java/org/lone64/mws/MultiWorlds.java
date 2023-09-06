package org.lone64.mws;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.mws.command.MainCmd;
import org.lone64.mws.command.MainTab;
import org.lone64.mws.util.file.Config;
import org.lone64.mws.world.WorldManager;

import java.io.File;

public final class MultiWorlds extends JavaPlugin {

    public static MultiWorlds plugin;
    public static String prefix;

    @Override
    public void onEnable() {
        plugin = this;
        prefix = "[MultiWorlds]";

        File file = new File(getDataFolder(), "");
        if (!file.exists()) {
            file.mkdir();
        }

        file = new File(getDataFolder(), "worlds.yml");
        if (!file.exists()) {
            saveResource("worlds.yml", false);
        }

        file = new File(getDataFolder(), "cache");
        if (!file.exists()) {
            file.mkdir();
        }

        getCommand("mws").setExecutor(new MainCmd());
        getCommand("mws").setTabCompleter(new MainTab());

        for (String s : new Config("worlds.yml").getSet("")) {
            WorldManager.of(s).load();
        }
    }

    @Override
    public void onDisable() {

    }

}
