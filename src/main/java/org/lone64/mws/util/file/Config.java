package org.lone64.mws.util.file;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.mws.MultiWorlds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Config {

    private final File file;
    private final YamlConfiguration config;

    public Config(String path) {
        JavaPlugin plugin = MultiWorlds.getPlugin(MultiWorlds.class);
        this.file = new File(plugin.getDataFolder() + "/" + path);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void setFile() {
        try {
            this.file.createNewFile();
            this.config.save(this.file);
        } catch (Exception var2) {
        }
    }

    public boolean setFolder() {
        return this.file.mkdir();
    }

    public void setDelete() {
        this.file.delete();
    }

    private void save() {
        try {
            this.config.save(this.file);
        } catch (Exception var2) {}
    }

    public void setObject(String path, Object obj) {
        this.config.set(path, obj);
        this.save();
    }

    public Object getObject(String path) {
        return this.config.get(path);
    }

    public boolean getBoolean(String path) {
        return this.config.getBoolean(path);
    }

    public String getString(String path) {
        return this.config.getString(path);
    }

    public List<?> getList(String path) {
        return this.config.getList(path);
    }

    public List<String> getListString(String path) {
        return this.config.getStringList(path);
    }

    public ArrayList<String> getArrayList(String path) {
        return (ArrayList<String>) this.config.getStringList(path);
    }

    public Set<String> getSet(String path) {
        return this.config.getConfigurationSection(path).getKeys(false);
    }

    public String[] getList() {
        return this.file.list();
    }

    public int getInt(String path) {
        return this.config.getInt(path);
    }

    public double getDouble(String path) {
        return this.config.getDouble(path);
    }

    public long getLong(String path) {
        return this.config.getLong(path);
    }

    public boolean isContains(String path) {
        return this.config.contains(path);
    }

    public ItemStack getItemStack(String path) {
        return this.config.getItemStack(path);
    }

    public boolean isExists() {
        return this.file.exists();
    }

    public boolean isFile() {
        return this.file.isFile();
    }

    public boolean isFolder() {
        return this.file.isDirectory();
    }

    public File[] getFiles() {
        return this.file.listFiles();
    }

    public void setReload() {
        try {
            this.config.load(this.file);
        } catch (InvalidConfigurationException | IOException var2) {}
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

}
