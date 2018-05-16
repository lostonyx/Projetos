/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import darknessloja.DarkNessLoja;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author WillianDev
 */
public class Loja implements CommandExecutor {
    DarkNessLoja plugin;
    public Loja(DarkNessLoja plugin) {
        this.plugin = plugin;
    }
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
            plugin.getDataFolder().mkdir();        
            File file = new File(plugin.getDataFolder(), "config.yml");        
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(cs instanceof Player){
            if(cs.hasPermission("sao.shop")) {
                plugin.reloadConfig();
                if(cmnd.getName().equalsIgnoreCase("shop")) {
                    if(strings.length == 0){
                    cs.sendMessage("§0§l[§6Shop§0§l] §eUse: §6/Shop §e<Player>");
                    }
                    else{
                    Player xp = (Player)cs;
                    if(plugin.getConfig().getString(strings[0]+"T") != "1"){
                    String w = plugin.getConfig().getString(strings[0]+".loja.W");
                    double x = plugin.getConfig().getDouble(strings[0]+".loja.X");
                    double y = plugin.getConfig().getDouble(strings[0]+".loja.Y");
                    double z = plugin.getConfig().getDouble(strings[0]+".loja.Z");
                    float p = (float)plugin.getConfig().getDouble(strings[0]+".loja.P");
                    float a = (float)plugin.getConfig().getDouble(strings[0]+".loja.A");
                    if(w == null){
                        xp.sendMessage("§0§l[§6Shop§0§l] §6"+ strings[0] +"§enao tem §6Shop §ecriado!");
                    }                 
                    if(w != null){
                    Location tpto = new Location(Bukkit.getWorld(w), x, y, z);
                    xp.teleport(tpto);  
                    xp.sendMessage("§0§l[§6Shop§0§l] §eVoce entro na loja de §6"+ strings[0] + "!"); 
                    }
                    return true;
                    }           
                    if(plugin.getConfig().getString(strings[0]+"T") == null){
                        xp.sendMessage("§0§l[§6Shop§0§l] §6"+ strings[0] +" §enao tem §6Shop §ecriado!");
                    }        
                    }
                    
                }
            }
        } else {
            cs.sendMessage("§0§l[§6§lLoja§0§l] "+"§cVoce nao e um player.");
        }
        return false;
    }
    
}
