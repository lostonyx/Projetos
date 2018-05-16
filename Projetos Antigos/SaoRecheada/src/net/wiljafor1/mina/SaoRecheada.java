package net.wiljafor1.mina;
import net.wiljafor1.mina.comandos.Comandos;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import net.wiljafor1.mina.comandos.Evento;
import net.wiljafor1.mina.eventos.Eventos;
import static net.wiljafor1.mina.eventos.Eventos.EndCorrida;
import static net.wiljafor1.mina.eventos.Eventos.PreLobby;
import static net.wiljafor1.mina.eventos.Eventos.StartCorrida;
import static net.wiljafor1.mina.eventos.Eventos.l1;
import static net.wiljafor1.mina.eventos.Eventos.l2;
import static net.wiljafor1.mina.eventos.Eventos.l3;
import static net.wiljafor1.mina.eventos.Eventos.pg;
import static net.wiljafor1.mina.eventos.Eventos.task;
import static net.wiljafor1.mina.eventos.Eventos.task_1;
import static net.wiljafor1.mina.eventos.Eventos.task_10;
import static net.wiljafor1.mina.eventos.Eventos.task_11;
import static net.wiljafor1.mina.eventos.Eventos.task_12;
import static net.wiljafor1.mina.eventos.Eventos.task_13;
import static net.wiljafor1.mina.eventos.Eventos.task_2;
import static net.wiljafor1.mina.eventos.Eventos.task_3;
import static net.wiljafor1.mina.eventos.Eventos.task_4;
import static net.wiljafor1.mina.eventos.Eventos.task_5;
import static net.wiljafor1.mina.eventos.Eventos.task_6;
import static net.wiljafor1.mina.eventos.Eventos.task_7;
import static net.wiljafor1.mina.eventos.Eventos.task_8;
import static net.wiljafor1.mina.eventos.Eventos.task_9;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import static net.wiljafor1.mina.eventos.Eventos.EndCorrida;
import static net.wiljafor1.mina.eventos.Eventos.StartCorrida;

public class SaoRecheada extends JavaPlugin implements Listener {
  public String flash = null;
  public static Economy economy = null;
  public int stop;
  private int diaAutoStart;
  private int horaAutoStart;
  private int minAutoStart;
  BukkitScheduler scheduler1 = getServer().getScheduler();
  private boolean setupEconomy(){
  RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
        economy = economyProvider.getProvider();
    }

  return (economy != null);
  } 
  
    @Override
    public void onEnable() { 
    Bukkit.getPluginManager().registerEvents(this, this);  
    setupEconomy();
    //saveDefaultConfig();
    //reloadConfig();
    //getCommand("evento").setExecutor(new Evento(this));
    getCommand("minarecheada").setExecutor(new Comandos(this));
    Bukkit.getServer().getPluginManager().registerEvents(new Eventos(this), this);
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bSaoRecheada§3]==========--------");
    Bukkit.getConsoleSender().sendMessage("§3 Status: §ahabilitado");
    Bukkit.getConsoleSender().sendMessage("§3 Versao: §b" + getDescription().getVersion());
    Bukkit.getConsoleSender().sendMessage("§3 Autor: §bWiljafor1");
    Bukkit.getConsoleSender().sendMessage("§3 Contato: §bwillian.programadorce@gmail.com");
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bSaoRecheada§3]==========--------");
    }
    @Override
    public void onDisable() { 
    saveConfig();
    }

    
    
}
