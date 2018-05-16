/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coindarkness;

import Comandos.Coins;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WillianDev
 */
public class CoinDarkNess extends JavaPlugin{
  public static Permission perms = null;
  public static Economy economy = null;    
        private boolean setupPermissions()
        {
        RegisteredServiceProvider permissionProvider = getServer()
        .getServicesManager().getRegistration(
        Permission.class);
        if (permissionProvider != null) {
            perms = (Permission)permissionProvider.getProvider();
        }
        return perms != null;
        }
        
        @Override
        public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        getCommand("coins").setExecutor(new Coins(this));            
        this.setupEconomy();
        setupEconomy();
        if (!setupPermissions()) {
            System.out.println("Vault not found. Shutting down");
        }    
        }
        
        @Override
        public void onDisable() {
        }
        
        private boolean setupEconomy()
        {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
        }        
}

