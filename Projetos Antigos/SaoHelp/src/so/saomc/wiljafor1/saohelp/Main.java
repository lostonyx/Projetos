/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.saomc.wiljafor1.saohelp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import so.saomc.wiljafor1.saohelp.Eventos;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import static so.saomc.wiljafor1.saohelp.MainConfig.loadConfig;

/**
 *
 * @author WillianDev
 */
public class Main extends JavaPlugin{
        public static Main plugin;
        public List<String> gb1 = new ArrayList();
        public List<String> gb2 = new ArrayList();
        public List<String> gb3 = new ArrayList();
        public List<String> gb4 = new ArrayList();
        public List<String> gb5 = new ArrayList();
        public List<String> gb6 = new ArrayList();
        public List<String> gb7 = new ArrayList();
        public List<String> gb8 = new ArrayList();
        public List<String> gb9 = new ArrayList();
        public List<String> gb10 = new ArrayList();
        
    @Override
    public void onEnable() {
    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
    Bukkit.getConsoleSender().sendMessage("§4[API]PlaceholderAPI carregado!");    
    }
    else{
    Bukkit.shutdown();;
    }
    plugin = this;
    saveDefaultConfig();
    loadConfig();
    gb1 = MainConfig.getgb1();
    Bukkit.getServer().getPluginManager().registerEvents(new Eventos(this), this);
    }
    @Override
    public void onDisable() { 
    }
}
