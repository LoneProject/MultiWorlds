package org.lone64.mws.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.lone64.mws.MultiWorlds;
import org.lone64.mws.util.Util;
import org.lone64.mws.util.file.Config;

import java.io.File;

public class WorldManager {

    private final String name;

    public WorldManager(String name) {
        this.name = name;
    }

    public WorldManager create() {
        WorldCreator worldCreator = new WorldCreator(getName());
        worldCreator.environment(World.Environment.NORMAL).createWorld();
        return this;
    }

    public WorldManager create(WorldType type) {
        WorldCreator worldCreator = new WorldCreator(getName());
        worldCreator.environment(World.Environment.valueOf(type.name().toUpperCase()));
        worldCreator.createWorld();
        new Config("worlds.yml").setObject(getName(), "ofWorld");
        return this;
    }

    public WorldManager delete() {
        File file = new File(getName());
        if (file.exists() && Bukkit.unloadWorld(getName(), true)) {
            new Config("worlds.yml").setObject(getName(), null);
            Util.deleteFile(file);
        }
        return this;
    }

    public WorldManager load() {
        new WorldCreator(getName()).createWorld();
        return this;
    }

    public WorldManager unload() {
        Bukkit.unloadWorld(getName(), true);
        return this;
    }

    public Location center() {
        World world = Bukkit.getWorld(getName());
        if (world == null) return null;

        return world.getSpawnLocation();
    }

    public WorldManager backup() {
        File file = new File(getName());
        if (!file.exists()) return this;

        File backup = new File(MultiWorlds.plugin.getDataFolder(), "cache/" + getName());
        if (backup.exists()) return this;

        unload();
        Util.copyDirectory(file, backup);
        load();
        return this;
    }

    public WorldManager reset() {
        File file = new File(getName());
        if (!file.exists()) return this;

        File backup = new File(MultiWorlds.plugin.getDataFolder(), "cache/" + getName());
        if (!backup.exists()) return this;

        delete();
        Util.copyDirectory(backup, file);
        load();
        return this;
    }

    public int size() {
        World world = Bukkit.getWorld(getName());
        if (world == null) return 0;

        return world.getPlayers().size();
    }

    public World getWorld() {
        return Bukkit.getWorld(getName());
    }

    public boolean isWorld() {
        return getWorld() != null;
    }

    public boolean isBackup() {
        return new File(MultiWorlds.plugin.getDataFolder(), "cache/" + getName()).exists();
    }

    public String getName() {
        return name;
    }

    public static WorldManager of(String name) {
        return new WorldManager(name);
    }

}
