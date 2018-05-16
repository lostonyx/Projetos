/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import static Eventos.Eventos.EndCorrida;
import net.wiljafor1.SaoCorrida;
import static Eventos.Eventos.pgn;
import static Eventos.Eventos.pgt;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static Eventos.Eventos.PreLobby;
import static Eventos.Eventos.StartCorrida;
import static Eventos.Eventos.invsave;
import static Eventos.Eventos.l1;
import static Eventos.Eventos.l2;
import static Eventos.Eventos.l3;
import static Eventos.Eventos.locsave;
import static Eventos.Eventos.pg;
import static Eventos.Eventos.pgt;
import static Eventos.Eventos.task;
import static Eventos.Eventos.task_1;
import static Eventos.Eventos.task_10;
import static Eventos.Eventos.task_11;
import static Eventos.Eventos.task_12;
import static Eventos.Eventos.task_13;
import static Eventos.Eventos.task_2;
import static Eventos.Eventos.task_3;
import static Eventos.Eventos.task_4;
import static Eventos.Eventos.task_5;
import static Eventos.Eventos.task_6;
import static Eventos.Eventos.task_7;
import static Eventos.Eventos.task_8;
import static Eventos.Eventos.task_9;
import net.wiljafor1.SaoCorrida;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author WillianDev
 */
public class Comandos implements CommandExecutor{
    SaoCorrida plugin;
     
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    
    public Comandos(SaoCorrida plugin) {
        this.plugin = plugin;
    } 
    private World world;
    int max = Bukkit.getOnlinePlayers().size();
    BukkitScheduler scheduler1 = getServer().getScheduler();
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        Player p = (Player)cs;
        Player player = (Player)cs;
        if(player.hasPermission("saocorrida.admin")) {
        if (cmnd.getName().equalsIgnoreCase("saocorrida"))
        {
            if(strings.length == 0){
         p.sendMessage("§b§l[§f§lSaoCorrida§b§l] §2§lUse /saocorrida admin start - iniciar o evento!");
         p.sendMessage("§b§l[§f§lSaoCorrida§b§l] §2§lUse /saocorrida setspawn <lobby/spawn/corrida>");
         p.sendMessage("§b§l[§f§lSaoCorrida§b§l] §4§lCriado por Wiljafor1 :D"); 
            }
            if(strings.length == 1){
         p.sendMessage("§b§l[§f§lSaoCorrida§b§l] §2§lUse /saocorrida admin start - iniciar o evento!");
         p.sendMessage("§b§l[§f§lSaoCorrida§b§l] §2§lUse /saocorrida setspawn <lobby/spawn/corrida>");
         p.sendMessage("§b§l[§f§lSaoCorrida§b§l] §4§lCriado por Wiljafor1 :D"); 
            }
            if (strings.length == 2) {
                
         if (strings[0].equalsIgnoreCase("admin")){
         if (strings[1].equalsIgnoreCase("start")) {  
         if(Bukkit.getOnlinePlayers().size() >= 3){
         Eventos.Eventos.PreLobby=true;
         Eventos.Eventos.StartCorrida=true;
         p.sendMessage("Evento inicado!");
         task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
         public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida Iniciado §7§m-§b*§7-");
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         //v.sendMessage("§b§l[§f§lCorrida§b§l] §f§$Limpe o inventario antes de entrar no evento!(Nao Reclama a staff!");
         v.sendMessage("§7§m-§b*§7- §ePlayers no evento: §b"+ pg.size() +" §7§m-§b*§7-"); 
         v.sendMessage("§7§m-§b*§7- §eUse §b/Evento §epara Participar! §7§m-§b*§7-");          
         v.sendMessage("§7§m-§b*§7- §eNao tem §cPVP §edentro do Evento §7§m-§b*§7-");         
         }
         }
        }      
        }, 1 * 20);  
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida Iniciado §7§m-§b*§7-");
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         //v.sendMessage("§b§l[§f§lCorrida§b§l] §f§$Limpe o inventario antes de entrar no evento!(Nao Reclama a staff!");
         v.sendMessage("§7§m-§b*§7- §ePlayers no evento: §b"+ pg.size() +" §7§m-§b*§7-"); 
         v.sendMessage("§7§m-§b*§7- §eUse §b/Evento §epara Participar! §7§m-§b*§7-");          
         v.sendMessage("§7§m-§b*§7- §eNao tem §cPVP §edentro do Evento §7§m-§b*§7-");    
         }
         }
        }      
        }, 30 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Corrida Iniciado §7§m-§b*§7-");
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         //v.sendMessage("§b§l[§f§lCorrida§b§l] §4§lLimpe o inventario antes de entrar no evento!(Nao Reclama a staff!");
         v.sendMessage("§7§m-§b*§7- §ePlayers no evento: §b"+ pg.size() +" §7§m-§b*§7-"); 
         v.sendMessage("§7§m-§b*§7- §eUse §b/Evento §epara Participar! §7§m-§b*§7-");          
         v.sendMessage("§7§m-§b*§7- §eNao tem §cPVP §edentro do Evento §7§m-§b*§7-");       
         }
         }
        }      
        }, 45 * 20); 
     if(PreLobby == true){
    task = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
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
         Eventos.Eventos.PreLobby=false;
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
            v.getInventory().setContents(invsave.get(v));  
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
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Minuto §7§m-§b*§7-");
         }
         }
        }      
        }, 1 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 30 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 30 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 25 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 35 * 20);
    task_3 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 20 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 40 * 20);
    task_4 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 10 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 50 * 20);
    task_5 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 9 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 51 * 20);
    task_6 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 8 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 52 * 20);
    task_7 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 7 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 53 * 20);
    task_8 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 6 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 54 * 20);
     task_9 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 5 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 55 * 20);
     task_10 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 4 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 56 * 20);
     task_11 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 3 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 57 * 20);
     task_12 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 2 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 58 * 20);
     task_13 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Começa em 1 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 59 * 20);
    task_13 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento vai acabar em 40 Segundos §7§m-§b*§7-");
         }
         }
        }      
        }, 400 * 20);
     task_14 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
             
         if(!l1.isEmpty()){
            plugin.economy.depositPlayer(l1.toString(), plugin.getConfig().getDouble("Premio1"));
            plugin.getConfig().set("FlashAtual", null);
            plugin.getConfig().set("FlashAtual", l1.toString());
            plugin.flash = plugin.getConfig().getString("FlashAtual");
            plugin.saveConfig();    
             v.sendMessage("§7§m-§b*§7§m-§e 1º Lugar para "+ l1.toString()+" §7§m-§b*§7§m-§e");
             if(!l2.isEmpty()){
                plugin.economy.depositPlayer(l2.toString(), plugin.getConfig().getDouble("Premio2"));
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
         else{
             p.sendMessage("§bSem players!"); 
         }
                    }

                }

                if (strings[0].equalsIgnoreCase("setspawn")) {
                    if (strings[1].equalsIgnoreCase("lobby")) {
                        plugin.getConfig().set("evento.lobby.W", player.getWorld().getName());
                        plugin.getConfig().set("evento.lobby.X", Double.valueOf(player.getLocation().getX()));
                        plugin.getConfig().set("evento.lobby.Y", Double.valueOf(player.getLocation().getY()));
                        plugin.getConfig().set("evento.lobby.Z", Double.valueOf(player.getLocation().getZ()));
                        plugin.getConfig().set("evento.lobby.P", Double.valueOf(player.getLocation().getPitch()));
                        plugin.getConfig().set("evento.lobby.A", Double.valueOf(player.getLocation().getYaw()));
                        player.sendMessage("§bLobby §bsetada com sucesso!");
                        plugin.saveConfig();
                        return true;
                    }
                    if (strings[1].equalsIgnoreCase("corrida")) {
                        plugin.getConfig().set("evento.corrida.W", player.getWorld().getName());
                        plugin.getConfig().set("evento.corrida.X", Double.valueOf(player.getLocation().getX()));
                        plugin.getConfig().set("evento.corrida.Y", Double.valueOf(player.getLocation().getY()));
                        plugin.getConfig().set("evento.corrida.Z", Double.valueOf(player.getLocation().getZ()));
                        plugin.getConfig().set("evento.corrida.P", Double.valueOf(player.getLocation().getPitch()));
                        plugin.getConfig().set("evento.corrida.A", Double.valueOf(player.getLocation().getYaw()));
                        player.sendMessage("§bCorrida §bsetada com sucesso!");
                        plugin.saveConfig();
                        return true;
                    }
                    if (strings[1].equalsIgnoreCase("spawn")) {
                        plugin.getConfig().set("evento.spawn.W", player.getWorld().getName());
                        plugin.getConfig().set("evento.spawn.X", Double.valueOf(player.getLocation().getX()));
                        plugin.getConfig().set("evento.spawn.Y", Double.valueOf(player.getLocation().getY()));
                        plugin.getConfig().set("evento.spawn.Z", Double.valueOf(player.getLocation().getZ()));
                        plugin.getConfig().set("evento.spawn.P", Double.valueOf(player.getLocation().getPitch()));
                        plugin.getConfig().set("evento.spawn.A", Double.valueOf(player.getLocation().getYaw()));
                        player.sendMessage("§bSpawn §bsetada com sucesso!");
                        plugin.saveConfig();
                        return true;
                    }
                    return false;
                }
                return false; 
            }
            
            return false;   
        }
        return false;
    }
    return false;
}    
}
