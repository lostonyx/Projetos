/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.wiljafor1.saobalancear;

//import Comandos.Pagar;
import static me.wiljafor1.saobalancear.Gerencia.BlackList;
import static me.wiljafor1.saobalancear.Gerencia.WhiteList;
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
public class Main extends JavaPlugin{
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
        WhiteList.add("/money");
        WhiteList.add("pay");
        BlackList.add("/plugins");
        BlackList.add("/pl");
        BlackList.add("/bukkit:?");
        BlackList.add("/bukkit:pl");
        BlackList.add("/bukkit:help");
        BlackList.add("/bukkit:plugins");
        BlackList.add("/about");
        BlackList.add("/?");
        BlackList.add("/help");
        PluginManager pm = getServer().getPluginManager();
        Bukkit.getServer().getPluginManager().registerEvents(new Gerencia(this), this); 
        //getCommand("Pagar").setExecutor(new Pagar(this));            
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
