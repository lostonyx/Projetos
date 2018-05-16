/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darknessmoney;

import Comandos.Pagar;
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
public class DarkNessMoney extends JavaPlugin{
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
        getCommand("Pagar").setExecutor(new Pagar(this));            
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
