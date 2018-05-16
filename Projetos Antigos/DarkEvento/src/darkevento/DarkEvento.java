/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkevento;

import Comandos.Evento;
import Comandos.Setar;
import Comandos.Start;
import Comandos.Stop;
import Eventos.Eventos;
import static Eventos.Eventos.l1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WillianDev
 */
public class DarkEvento extends JavaPlugin implements Listener{
  public static Economy economy = null;
  public int stop;
  public World world;
  World unload = Bukkit.getWorld("evento");
  private boolean setupEconomy(){
  RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
        economy = economyProvider.getProvider();
    }

  return (economy != null);
  }   
    @Override
    public void onEnable() { 
    setupEconomy();
    getCommand("evento").setExecutor(new Evento(this));
    getCommand("dkevento").setExecutor(new Start(this));
    getCommand("dksetar").setExecutor(new Setar(this));
    getCommand("dkstop").setExecutor(new Stop(this));
    Bukkit.getServer().getPluginManager().registerEvents(new Eventos(this), this);
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bDarKEvento§3]==========--------");
    Bukkit.getConsoleSender().sendMessage("§3 Status: §ahabilitado");
    Bukkit.getConsoleSender().sendMessage("§3 Versao: §b" + getDescription().getVersion());
    Bukkit.getConsoleSender().sendMessage("§3 Autor: §bWiljafor1");
    Bukkit.getConsoleSender().sendMessage("§3 Contato: §bwillian.programadorce@gmail.com");
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bDarKEvento§3]==========--------");
    }
    @Override
    public void onDisable() { 
    
    }

}
