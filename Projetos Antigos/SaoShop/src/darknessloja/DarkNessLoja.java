/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darknessloja;

import Comandos.DeletarShop;
import Comandos.Loja;
import Comandos.SetarLoja;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WillianDev
 */
public class DarkNessLoja extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {                         
        PluginManager pm = getServer().getPluginManager();
        Bukkit.getPluginManager().registerEvents(this,this);
        getCommand("ShopSet").setExecutor(new SetarLoja(this));
        getCommand("Shop").setExecutor(new Loja(this));  
        getCommand("DelShop").setExecutor(new DeletarShop(this)); 
        getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {

            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        reloadConfig();
        saveDefaultConfig();
        System.out.println("[DKTEST]"+getConfig().get("Wiljafor1T"));
    }
    public void addDef(YamlConfiguration config, String path, String value){
        if(!config.contains(path)){
            config.set(path, value);
        }    
    }

    @Override
    public void onDisable(){
    reloadConfig();
    saveDefaultConfig();
    }
}
