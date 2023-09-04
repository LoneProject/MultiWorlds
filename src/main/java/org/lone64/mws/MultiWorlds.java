package org.lone64.mws;

import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.mws.command.MainCmd;
import org.lone64.mws.command.MainTab;

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

        file = new File(getDataFolder(), "cache");
        if (!file.exists()) {
            file.mkdir();
        }

        getCommand("mws").setExecutor(new MainCmd());
        getCommand("mws").setTabCompleter(new MainTab());
    }

    @Override
    public void onDisable() {

    }

}
