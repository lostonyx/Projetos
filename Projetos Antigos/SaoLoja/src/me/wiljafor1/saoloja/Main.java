/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.wiljafor1.saoloja;

import me.wiljafor1.saoloja.util.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WillianDev
 */
public class Main extends JavaPlugin{
    public static Main plugin;
    public static SQLite sql;
    
	public void debug(String message){
		Bukkit.getConsoleSender().sendMessage("§e[LOG] " + message);
	}
}
