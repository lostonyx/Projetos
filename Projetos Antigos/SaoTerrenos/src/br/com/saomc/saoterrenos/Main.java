package br.com.saomc.saoterrenos;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.io.File;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    public static Economy economy = null;
    public static Permission perms = null;
    Main plugin;
    
    private boolean setupEconomy(){
    RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

    return (economy != null);
    }
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
    public void ChecarSchematic(){
        File p = new File(getDataFolder(), "pequeno.schematic");
        File m = new File(getDataFolder(), "medio.schematic");
        File g = new File(getDataFolder(), "grande.schematic");
        File c= new File(getDataFolder(), "config.yml");
        if (!p.exists()) {
            plugin.saveResource("pequeno.schematic", false);
        }
        if (!m.exists()) {
            plugin.saveResource("medio.schematic", false);
        }
        if (!g.exists()) {
            plugin.saveResource("grande.schematic", false);
        }
        if (!c.exists()){
            saveDefaultConfig();
        }
    }
    
    @Override
    public void onEnable() { 
        Bukkit.getConsoleSender().sendMessage("==========[DTerrenos]==========");
        Bukkit.getConsoleSender().sendMessage("Status: Ativado");
        Bukkit.getConsoleSender().sendMessage("By: Wiljafor1");
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getConsoleSender().sendMessage("Vault: Nao Encontrado");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        Bukkit.getConsoleSender().sendMessage("Vault: Hooked (Economy)");
        if(Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") == null) {
            Bukkit.getConsoleSender().sendMessage("WorldEdit: Nao Encontrado");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        Bukkit.getConsoleSender().sendMessage("WorldEdit: Hooked");
        if(Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") == null) {
            Bukkit.getConsoleSender().sendMessage("WorldGuard: Nao Encontrado");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        Bukkit.getConsoleSender().sendMessage("WorldGuard: Hooked (Regions)");
        setupEconomy();
        setupPermissions();
        ChecarSchematic();
    } 
    @Override
    public void onDisable() { 
        
    } 
}
