package co.saomc.SaoDesafio;
import co.saomc.SaoDesafio.Comandos.Comandos;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import co.saomc.SaoDesafio.Comandos.Evento;
import co.saomc.SaoDesafio.Eventos.Eventos;
import static co.saomc.SaoDesafio.Eventos.Eventos.EndCorrida;
import static co.saomc.SaoDesafio.Eventos.Eventos.PreLobby;
import static co.saomc.SaoDesafio.Eventos.Eventos.StartCorrida;
import static co.saomc.SaoDesafio.Eventos.Eventos.invsave;
import static co.saomc.SaoDesafio.Eventos.Eventos.locsave;
import static co.saomc.SaoDesafio.Eventos.Eventos.pg;
import static co.saomc.SaoDesafio.Eventos.Eventos.task;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_1;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_10;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_11;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_12;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_13;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_14;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_2;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_3;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_4;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_5;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_6;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_7;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_8;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_9;
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
import static co.saomc.SaoDesafio.Eventos.Eventos.EndCorrida;
import static co.saomc.SaoDesafio.Eventos.Eventos.StartCorrida;

public class SaoDesafio extends JavaPlugin implements Listener {
    public String flash = null;
    public static Economy economy = null;
    public int stop;
    private int diaAutoStart;
    private int horaAutoStart;
    private int minAutoStart;
    public double TempoInicial;//double aqui
    public double TempoFinal;//double aqui
    public double TempoFlash;//double aqui
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
       Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
       {
        public void run(){
           if (getConfig().contains("FlashAtual")){
                flash = getConfig().getString("FlashAtual");
           } 
           if (getConfig().contains("FlashTempo")){
                TempoFlash = getConfig().getDouble("FlashTempo");//double aqui
           } 
        }  
        }, 0, 20 * 480);   
    setupEconomy();
    saveDefaultConfig();
    reloadConfig();
    getCommand("saodesafio").setExecutor(new Comandos(this));
    Bukkit.getServer().getPluginManager().registerEvents(new Eventos(this), this);
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bSaoDesafio§3]==========--------");
    Bukkit.getConsoleSender().sendMessage("§3 Status: §ahabilitado");
    Bukkit.getConsoleSender().sendMessage("§3 Versao: §b" + getDescription().getVersion());
    Bukkit.getConsoleSender().sendMessage("§3 Autor: §bWiljafor1");
    Bukkit.getConsoleSender().sendMessage("§3 Contato: §bwillian.programadorce@gmail.com");
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bSaoDesafio§3]==========--------");
    if (getConfig().contains("FlashAtual")){
      flash = getConfig().getString("FlashAtual");
    }
    if (getConfig().contains("FlashTempo")){
      TempoFlash = getConfig().getDouble("FlashTempo");//double aqui
    } 
    if (getConfig().contains("PrefixoFlash")){
    }
    else{
      getConfig().set("PrefixoFlash", "&5&l[Flash]&f");
      getConfig().set("FlashTempo", 10.0);//double aqui
      getConfig().set("FlashAtual", "Wiljafor1");
      saveConfig();
    }
    }
    @Override
    public void onDisable() { 
    getConfig().set("FlashAtual", flash);
    getConfig().set("FlashTempo", TempoFlash);
    saveConfig();
    }
    
    public static int strToCalendar(final String dia) {
        if (dia.equalsIgnoreCase("domingo")) {
            return 1;
        }
        if (dia.equalsIgnoreCase("segunda")) {
            return 2;
        }
        if (dia.equalsIgnoreCase("terca")) {
            return 3;
        }
        if (dia.equalsIgnoreCase("quarta")) {
            return 4;
        }
        if (dia.equalsIgnoreCase("quinta")) {
            return 5;
        }
        if (dia.equalsIgnoreCase("sexta")) {
            return 6;
        }
        if (dia.equalsIgnoreCase("sabado")) {
            return 7;
        }
        return 7;
    }
    
    
}
