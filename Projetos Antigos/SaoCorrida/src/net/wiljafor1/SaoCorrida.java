package net.wiljafor1;
import Comandos.Comandos;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import Comandos.Evento;
import Comandos.Reload;
import Comandos.Setar;
import Comandos.Start;
import Eventos.Eventos;
import static Eventos.Eventos.EndCorrida;
import static Eventos.Eventos.PreLobby;
import static Eventos.Eventos.StartCorrida;
import static Eventos.Eventos.invsave;
import static Eventos.Eventos.l1;
import static Eventos.Eventos.l2;
import static Eventos.Eventos.l3;
import static Eventos.Eventos.locsave;
import static Eventos.Eventos.pg;
import static Eventos.Eventos.task;
import static Eventos.Eventos.task_1;
import static Eventos.Eventos.task_10;
import static Eventos.Eventos.task_11;
import static Eventos.Eventos.task_12;
import static Eventos.Eventos.task_13;
import static Eventos.Eventos.task_14;
import static Eventos.Eventos.task_2;
import static Eventos.Eventos.task_3;
import static Eventos.Eventos.task_4;
import static Eventos.Eventos.task_5;
import static Eventos.Eventos.task_6;
import static Eventos.Eventos.task_7;
import static Eventos.Eventos.task_8;
import static Eventos.Eventos.task_9;
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

public class SaoCorrida extends JavaPlugin implements Listener {
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
       Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
       {
        public void run(){
           if (getConfig().contains("FlashAtual")){
                flash = getConfig().getString("FlashAtual");
           } 
        }  
        }, 0, 20 * 480);
         
        
    setupEconomy();
    saveDefaultConfig();
    reloadConfig();
    diaAutoStart = strToCalendar(this.getConfig().getString("AutoStart.Dia"));
    horaAutoStart = Integer.parseInt(this.getConfig().getString("AutoStart.Hora").substring(0, 2));
    minAutoStart = Integer.parseInt(this.getConfig().getString("AutoStart.Hora").substring(2, 4));
    getCommand("evento").setExecutor(new Evento(this));
    //getCommand("corridaadmin").setExecutor(new Start(this));
    //getCommand("setcorrida").setExecutor(new Setar(this));
    //getCommand("saocorrida").setExecutor(new Reload(this));
    getCommand("saocorrida").setExecutor(new Comandos(this));
    Bukkit.getServer().getPluginManager().registerEvents(new Eventos(this), this);
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bSaoCorrida§3]==========--------");
    Bukkit.getConsoleSender().sendMessage("§3 Status: §ahabilitado");
    Bukkit.getConsoleSender().sendMessage("§3 Versao: §b" + getDescription().getVersion());
    Bukkit.getConsoleSender().sendMessage("§3 Autor: §bWiljafor1");
    Bukkit.getConsoleSender().sendMessage("§3 Contato: §bwillian.programadorce@gmail.com");
    Bukkit.getConsoleSender().sendMessage("§3--------==========[§bSaoCorrida§3]==========--------");
    this.getServer().getScheduler().runTaskTimer(this, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Calendar.getInstance().get(7) == 2 && Calendar.getInstance().get(11) == horaAutoStart && Calendar.getInstance().get(12) == minAutoStart) {
                    //Main.this.prepareGladiador();
                    StartEvento();
                    Bukkit.getConsoleSender().sendMessage("Foi FILHO DAPUTA");
                }
                if (Calendar.getInstance().get(7) == 4 && Calendar.getInstance().get(11) == horaAutoStart && Calendar.getInstance().get(12) == minAutoStart) {
                    //Main.this.prepareGladiador();
                    StartEvento();
                    Bukkit.getConsoleSender().sendMessage("Foi FILHO DAPUTA");
                }
                if (Calendar.getInstance().get(7) == 6 && Calendar.getInstance().get(11) == horaAutoStart && Calendar.getInstance().get(12) == minAutoStart) {
                    //Main.this.prepareGladiador();
                    StartEvento();
                    Bukkit.getConsoleSender().sendMessage("Foi FILHO DAPUTA");
                }
            }
        }, 0L, 1000L);
    if (getConfig().contains("FlashAtual")){
      flash = getConfig().getString("FlashAtual");
    }
    if (getConfig().contains("PrefixoFlash")){
    }
    else{
      getConfig().set("PrefixoFlash", "&5&l[Flash]&f");
      getConfig().set("Premio1", Double.valueOf("5000000.00"));
      getConfig().set("Premio2", Double.valueOf("3000000.00"));
      getConfig().set("Premio3", Double.valueOf("1000000.00"));
      getConfig().set("AutoStart.Dia","segunda");
      getConfig().set("AutoStart.Hora","1940");
      saveConfig();
    }
    }
    @Override
    public void onDisable() { 
    getConfig().set("FLashAtual", flash);
    saveConfig();
    }
    
    public void StartEvento(){
         PreLobby=true;
         StartCorrida=true;
         task_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
         public void run(){
         StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida Iniciado §7§m-§b*§7-");
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         //v.sendMessage("§b§l[§f§lCorrida§b§l] §f§$Limpe o inventario antes de entrar no evento!(Nao Reclama a staff!");
         v.sendMessage("§7§m-§b*§7- §eUse §b/Evento §epara Participar! §7§m-§b*§7-");          
         v.sendMessage("§7§m-§b*§7- §eNao tem §cPVP §edentro do Evento §7§m-§b*§7-");         
         }
         }
        }      
        }, 1 * 20);  
    task_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida Iniciado §7§m-§b*§7-");
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         //v.sendMessage("§b§l[§f§lCorrida§b§l] §f§$Limpe o inventario antes de entrar no evento!(Nao Reclama a staff!");
         v.sendMessage("§7§m-§b*§7- §eUse §b/Evento §epara Participar! §7§m-§b*§7-");          
         v.sendMessage("§7§m-§b*§7- §eNao tem §cPVP §edentro do Evento §7§m-§b*§7-");    
         }
         }
        }      
        }, 30 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida Iniciado §7§m-§b*§7-");
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         //v.sendMessage("§b§l[§f§lCorrida§b§l] §4§lLimpe o inventario antes de entrar no evento!(Nao Reclama a staff!");
         v.sendMessage("§7§m-§b*§7- §eUse §b/Evento §epara Participar! §7§m-§b*§7-");          
         v.sendMessage("§7§m-§b*§7- §eNao tem §cPVP §edentro do Evento §7§m-§b*§7-");       
         }
         }
        }      
        }, 45 * 20); 
     if(PreLobby == true){
    task = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         if(pg.size() >= 3){
         String w = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getString("evento.corrida.W");
         double x = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.corrida.X");
         double y = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.corrida.Y");
         double z = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.corrida.Z");
         float pa = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.corrida.P");
         float a = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.corrida.A");
         Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida §6Começou! §7§m-§b*§7-");
         }
         PreLobby=false;
         }
        }
         else{
           String w = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getString("evento.spawn.W");
           double x = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.X");
           double y = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.Y");
           double z = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.Z");
           float pa = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.spawn.P");
           float a = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.spawn.A");
           Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
           for(Player v : Bukkit.getOnlinePlayers()){
           v.sendMessage("§7§m-§b*§7- §cEvento cancelado! menos de 3 players §7§m-§b*§7-"); 
           if(pg.contains(v.getName())){
            v.teleport(loc);
            }
           }
            Bukkit.getScheduler().cancelTask(task);
            Bukkit.getScheduler().cancelTask(task_1);
            Bukkit.getScheduler().cancelTask(task_2);
            Bukkit.getScheduler().cancelTask(task_3);
            Bukkit.getScheduler().cancelTask(task_4);
            Bukkit.getScheduler().cancelTask(task_5);
            Bukkit.getScheduler().cancelTask(task_6);
            Bukkit.getScheduler().cancelTask(task_7);
            Bukkit.getScheduler().cancelTask(task_8);
            Bukkit.getScheduler().cancelTask(task_9);
            Bukkit.getScheduler().cancelTask(task_10);
            Bukkit.getScheduler().cancelTask(task_11);
            Bukkit.getScheduler().cancelTask(task_12);
            Bukkit.getScheduler().cancelTask(task_13);
            Bukkit.getScheduler().cancelTask(task_14);
            pg.clear();
            l1.clear();
            l2.clear();
            l3.clear();
            PreLobby=false;
            StartCorrida=false;
            EndCorrida=false;
         }
        }
        }, 60 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         }
         }
        }      
        }, 1 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 30 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 30 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 25 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 35 * 20);
    task_3 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 20 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 40 * 20);
    task_4 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 10 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 50 * 20);
    task_5 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 9 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 51 * 20);
    task_6 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 8 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 52 * 20);
    task_7 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 7 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 53 * 20);
    task_8 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 6 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 54 * 20);
     task_9 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 5 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 55 * 20);
     task_10 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 4 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 56 * 20);
     task_11 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 3 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 57 * 20);
     task_12 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 2 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 58 * 20);
     task_13 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 59 * 20);
    task_13 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento vai acabar em 40 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 400 * 20);
     task_14 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
             
         if(!l1.isEmpty()){
            economy.depositPlayer(l1.toString(), getConfig().getDouble("Premio1"));
            getConfig().set("FlashAtual", null);
            getConfig().set("FlashAtual", l1.toString());
            flash = getConfig().getString("FlashAtual");
            saveConfig();    
             v.sendMessage("§7§m-§b*§7§m-§e 1º Lugar para "+ l1.toString()+" §7§m-§b*§7§m-§e");
             if(!l2.isEmpty()){
                economy.depositPlayer(l2.toString(), getConfig().getDouble("Premio2"));
                v.sendMessage("§7§m-§b*§7§m-§e 2º Lugar para "+ l2.toString()+" §7§m-§b*§7§m-§e");    
             }
             else{
                v.sendMessage("§7§m-§b*§7§m-§e O Evento so teve 1 ganhador!");
             }
         }
         else{
            v.sendMessage("§7§m-§b*§7§m-§e O Evento nenhum ganhador!");    
         }
         v.sendMessage("§7§m-§b*§7- §eO Evento vai acabar agora! §7§m-§b*§7-");
         v.getInventory().setContents(invsave.get(v)); 
         v.teleport(locsave.get(v));
         }
         }
            Bukkit.getScheduler().cancelTask(task);
            Bukkit.getScheduler().cancelTask(task_1);
            Bukkit.getScheduler().cancelTask(task_2);
            Bukkit.getScheduler().cancelTask(task_3);
            Bukkit.getScheduler().cancelTask(task_4);
            Bukkit.getScheduler().cancelTask(task_5);
            Bukkit.getScheduler().cancelTask(task_6);
            Bukkit.getScheduler().cancelTask(task_7);
            Bukkit.getScheduler().cancelTask(task_8);
            Bukkit.getScheduler().cancelTask(task_9);
            Bukkit.getScheduler().cancelTask(task_10);
            Bukkit.getScheduler().cancelTask(task_11);
            Bukkit.getScheduler().cancelTask(task_12);
            Bukkit.getScheduler().cancelTask(task_13);
            Bukkit.getScheduler().cancelTask(task_14);
            pg.clear();
            l1.clear();
            l2.clear();
            l3.clear();
            PreLobby=false;
            StartCorrida=false;
            EndCorrida=false;
        }      
        }, 440 * 20);
    }  
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
