/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wiljafor1.mina.comandos;

import net.wiljafor1.mina.SaoRecheada;
import static net.wiljafor1.mina.eventos.Eventos.pgn;
import static net.wiljafor1.mina.eventos.Eventos.pgt;
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
import static net.wiljafor1.mina.eventos.Eventos.EndCorrida;
import static net.wiljafor1.mina.eventos.Eventos.PreLobby;
import static net.wiljafor1.mina.eventos.Eventos.StartCorrida;
import static net.wiljafor1.mina.eventos.Eventos.invsave;
import static net.wiljafor1.mina.eventos.Eventos.pg;
import static net.wiljafor1.mina.eventos.Eventos.pgt;
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
import net.wiljafor1.mina.SaoRecheada;
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
import static net.wiljafor1.mina.eventos.Eventos.task_14;
import static net.wiljafor1.mina.eventos.Eventos.task_15;
import static net.wiljafor1.mina.eventos.Eventos.task_2;
import static net.wiljafor1.mina.eventos.Eventos.task_3;
import static net.wiljafor1.mina.eventos.Eventos.task_4;
import static net.wiljafor1.mina.eventos.Eventos.task_5;
import static net.wiljafor1.mina.eventos.Eventos.task_6;
import static net.wiljafor1.mina.eventos.Eventos.task_7;
import static net.wiljafor1.mina.eventos.Eventos.task_8;
import static net.wiljafor1.mina.eventos.Eventos.task_9;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static net.wiljafor1.mina.eventos.Eventos.EndCorrida;
import static net.wiljafor1.mina.eventos.Eventos.StartCorrida;
import static net.wiljafor1.mina.eventos.Eventos.locsave;

/**
 *
 * @author WillianDev
 */
public class Comandos implements CommandExecutor{
    SaoRecheada plugin;
     
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    
    public Comandos(SaoRecheada plugin) {
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
        if (cmnd.getName().equalsIgnoreCase("minarecheada"))
        {
            if(strings.length == 0){
         p.sendMessage("§b§l[§f§lMinaRecheada§b§l] §2§lUse /minarecheada admin start - iniciar o evento!");
         p.sendMessage("§b§l[§f§lMinaRecheada§b§l] §2§lUse /minarecheada setspawn <corrida>");
         p.sendMessage("§b§l[§f§lMinaRecheada§b§l] §4§lCriado por Wiljafor1 :D"); 
            }
            if(strings.length == 1){
         p.sendMessage("§b§l[§f§lMinaRecheada§b§l] §2§lUse /minarecheada admin start - iniciar o evento!");
         p.sendMessage("§b§l[§f§lMinaRecheada§b§l] §2§lUse /minarecheada setspawn <corrida>");
         p.sendMessage("§b§l[§f§lMinaRecheada§b§l] §4§lCriado por Wiljafor1 :D"); 
            }
            if (strings.length == 2) {
                
         if (strings[0].equalsIgnoreCase("admin")){
         if (strings[1].equalsIgnoreCase("start")) {  
         if(Bukkit.getOnlinePlayers().size() >= 10){
         net.wiljafor1.mina.eventos.Eventos.PreLobby=true;
         net.wiljafor1.mina.eventos.Eventos.StartCorrida=true;
         p.sendMessage("Evento inicado!");
        task_14 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento vai Acabar em 1 Minuto! §7§m-§b*§7-");
         
         }
         }
        }      
        }, 5841 * 20);
        task_15 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eO Evento Acabou! §7§m-§b*§7-");
         v.teleport(locsave.get(v));
         pg.remove(v.getName());
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
            Bukkit.getScheduler().cancelTask(task_15);
            pg.clear();
            l1.clear();
            l2.clear();
            l3.clear();
            PreLobby=false;
            StartCorrida=false;
            EndCorrida=false;
        }      
        }, 5900 * 20);
         task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
         public void run(){
         net.wiljafor1.mina.eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Mina Recheada Iniciado §7§m-§b*§7-");
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
         net.wiljafor1.mina.eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Mina Recheada Iniciado §7§m-§b*§7-");
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
         net.wiljafor1.mina.eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§7§m-§b*§7- §eEvento Mina Recheada Iniciado §7§m-§b*§7-");
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
         if(pg.size() >= 10){
         String w = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getString("evento.corrida.W");
         double x = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getDouble("evento.corrida.X");
         double y = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getDouble("evento.corrida.Y");
         double z = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getDouble("evento.corrida.Z");
         float pa = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getInt("evento.corrida.P");
         float a = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getInt("evento.corrida.A");
         Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§7§m-§b*§7- §eEvento Mina Recheada §6Começou! §7§m-§b*§7-");
         }
         net.wiljafor1.mina.eventos.Eventos.PreLobby=false;
         }
        }
         else{
           String w = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getString("evento.spawn.W");
           double x = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getDouble("evento.spawn.X");
           double y = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getDouble("evento.spawn.Y");
           double z = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getDouble("evento.spawn.Z");
           float pa = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getInt("evento.spawn.P");
           float a = Bukkit.getPluginManager().getPlugin("SaoRecheada").getConfig().getInt("evento.spawn.A");
           Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
           for(Player v : Bukkit.getOnlinePlayers()){
           v.sendMessage("§7§m-§b*§7- §cEvento cancelado! menos de 10 players §7§m-§b*§7-"); 
           if(pg.contains(v.getName())){
            //v.getInventory().setContents(invsave.get(v));  
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
            Bukkit.getScheduler().cancelTask(task_15);
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
         net.wiljafor1.mina.eventos.Eventos.StartCorrida=true;
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
