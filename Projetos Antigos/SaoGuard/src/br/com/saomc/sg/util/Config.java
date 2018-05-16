package br.com.saomc.sg.util;

import org.bukkit.plugin.java.*;
import org.bukkit.configuration.file.*;
import java.io.*;
import java.util.*;

public class Config
{
    private JavaPlugin plugin;
    private String name;
    private File file;
    private YamlConfiguration config;
    
    public Config(JavaPlugin pl, String nome) {
        plugin = pl;
        setName(nome);
        reloadConfig();
    }
    
    public JavaPlugin getPlugin() {
        return plugin;
    }
    
    public void setPlugin(JavaPlugin pl) {
        plugin = pl;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String nome) {
        name = nome;
    }
    
    public File getFile() {
        return file;
    }
    
    public YamlConfiguration getConfig() {
        return config;
    }
    
    public void saveConfig() {
        try {
            getConfig().save(getFile());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveDefault() {
        getConfig().options().copyDefaults(true);
    }
    
    public void saveDefaultConfig() {
        getPlugin().saveResource(getName(), false);
    }
    
    public void reloadConfig() {
        file = new File(getPlugin().getDataFolder(), getName());
        config = YamlConfiguration.loadConfiguration(getFile());
    }
    
    public void deleteConfig() {
        getFile().delete();
    }
    
    public boolean existeConfig() {
        return getFile().exists();
    }
    
    public String getString(String path) {
        return getConfig().getString(path);
    }
    
    public int getInt(String path) {
        return getConfig().getInt(path);
    }
    
    public boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }
    
    public double getDouble(String path) {
        return this.getConfig().getDouble(path);
    }
    
    public List<?> getList(String path) {//corrigir
        return (List<?>)getConfig().getList(path);
    }
    
    public boolean contains(String path) {
        return getConfig().contains(path);
    }
    
    public void set(String path, final Object value) {
        getConfig().set(path, value);
    }
}
