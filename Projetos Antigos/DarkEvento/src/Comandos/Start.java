/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import static Eventos.Eventos.PreLobby;
import static Eventos.Eventos.StartCorrida;
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
import darkevento.DarkEvento;
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
import static Eventos.Eventos.StartCorrida;
import static Eventos.Eventos.StartCorrida;
import static Eventos.Eventos.StartCorrida;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author WillianDev
 */
public class Start implements CommandExecutor{
    DarkEvento plugin;
    public Start(DarkEvento plugin) {
        this.plugin = plugin;
    } 
    private World world;
    int max = Bukkit.getOnlinePlayers().size();
    BukkitScheduler scheduler1 = getServer().getScheduler();
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
    final Player p = (Player)cs;
    if(p.hasPermission("dkevento.admin")) {

    if(Bukkit.getOnlinePlayers().size() >= 3){
        if(cmnd.getName().equalsIgnoreCase("dkevento")){
            if(strings[0].equalsIgnoreCase("corrida")) {
            Eventos.Eventos.PreLobby=true;
            Eventos.Eventos.StartCorrida=true;
            p.sendMessage("Evento inicado!");
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento inicio");
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lO Evento Iniciara em: 60s");
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lLimpe o inventario antes de entrar no evento!");         
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§l PVP: OFF");         
         }
         }
        }      
        }, 1 * 20);  
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento inicio");
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lO Evento Iniciara em: 30s");
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lLimpe o inventario antes de entrar no evento!");         
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§l PVP: OFF");         
         }
         }
        }      
        }, 30 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento inicio");
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lO Evento Iniciara em: 15s");
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lLimpe o inventario antes de entrar no evento!");         
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§l PVP: OFF");         
         }
         }
        }      
        }, 45 * 20); 
     if(PreLobby == true){
    task = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         World w = Bukkit.getWorld("evento");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.corrida.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.corrida.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.corrida.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.corrida.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.corrida.A");
         Location loc = new Location(w, x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento Começou!");
         }
         Eventos.Eventos.PreLobby=false;
         }
        }      
        }, 60 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartCorrida=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 60 segundos");
         }
         }
        }      
        }, 1 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 30 segundos");
         }
         }
        }      
        }, 30 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 25 segundos");
         }
         }
        }      
        }, 35 * 20);
    task_3 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 20 segundos");
         }
         }
        }      
        }, 40 * 20);
    task_4 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 10 segundos");
         }
         }
        }      
        }, 50 * 20);
    task_5 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 9 segundos");
         }
         }
        }      
        }, 51 * 20);
    task_6 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 8 segundos");
         }
         }
        }      
        }, 52 * 20);
    task_7 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 7 segundos");
         }
         }
        }      
        }, 53 * 20);
    task_8 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 6 segundos");
         }
         }
        }      
        }, 54 * 20);
     task_9 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 5 segundos");
         }
         }
        }      
        }, 55 * 20);
     task_10 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 4 segundos");
         }
         }
        }      
        }, 56 * 20);
     task_11 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 3 segundos");
         }
         }
        }      
        }, 57 * 20);
     task_12 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 2 segundos");
         }
         }
        }      
        }, 58 * 20);
     task_13 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§lEvento vai inicar em 1 segundos");
         }
         }
        }      
        }, 59 * 20);
    }           
            }
            if(strings[0].equalsIgnoreCase("spleef")) {
            Eventos.Eventos.PreLobby=true;
            Eventos.Eventos.StartSpleef=true;
            p.sendMessage("Evento inicado!");
            pgt.put(5, 0);
  World unload = Bukkit.getWorld("evento");
File sourceFolder = new File(Bukkit.getWorldContainer(), "evento_b");
 File targetFolder = new File(Bukkit.getWorldContainer(), "evento");
// The world to overwrite when copying
         for(Player v : Bukkit.getOnlinePlayers()){
             if(Bukkit.getServer().getWorld("evento") == v.getWorld()){
         World w = Bukkit.getWorld("spawn");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
         Location locaa = new Location(w, x, y, z, pa, a);
         v.teleport(locaa);
             }
         }
Bukkit.unloadWorld("evento", false);
deleteWorld(targetFolder);
copyWorld(sourceFolder, targetFolder);
Bukkit.getServer().createWorld(new WorldCreator("evento"));
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartSpleef=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento Spleef");
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lO Evento Iniciara em: 60s");
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lLimpe o inventario antes de entrar no evento!");         
         v.sendMessage("§b§l[§f§lSpleef§b§l]§f§l PVP: OFF");         
         }
         }
        }      
        }, 11 * 20);  
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartSpleef=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento Spleef");
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lO Evento Iniciara em: 30s");
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lLimpe o inventario antes de entrar no evento!");         
         v.sendMessage("§b§l[§f§lSpleef§b§l]§f§l PVP: OFF");         
         }
         }
        }      
        }, 40 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartSpleef=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(!pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento Spleef");
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lO Evento Iniciara em: 15s");
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lLimpe o inventario antes de entrar no evento!");         
         v.sendMessage("§b§l[§f§lSpleef§b§l]§f§l PVP: OFF");         
         }
         }
        }      
        }, 55 * 20); 
     if(PreLobby == true){
    task = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         World w = Bukkit.getWorld("evento");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spleef.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spleef.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spleef.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spleef.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spleef.A");
         Location loc = new Location(w, x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.getInventory().clear();
         v.teleport(loc);
         if(Eventos.Eventos.kits1.contains(v.getName()) || Eventos.Eventos.kits2.contains(v.getName()) || Eventos.Eventos.kits3.contains(v.getName()) || Eventos.Eventos.kits4.contains(v.getName())){
         if(Eventos.Eventos.kits1.contains(v.getName())){
         v.getInventory();
         ItemStack kit = new ItemStack(273);
         ItemMeta kitmeta = kit.getItemMeta();
         kitmeta.setDisplayName("§bPa - Trapezio Descendente");
         kit.setItemMeta(kitmeta);
         v.getInventory().setItem(0, kit);
        }
         if(Eventos.Eventos.kits2.contains(v.getName())){
         v.getInventory();
         ItemStack kit = new ItemStack(256);
         ItemMeta kitmeta = kit.getItemMeta();
         kitmeta.setDisplayName("§bPa - Colossus");
         kit.setItemMeta(kitmeta);
         v.getInventory().setItem(0, kit);  
         }
         if(Eventos.Eventos.kits3.contains(v.getName())){
         v.getInventory();
         ItemStack kit = new ItemStack(284);
         ItemMeta kitmeta = kit.getItemMeta();
         kitmeta.setDisplayName("§bPa - Ostentaçao");
         kit.setItemMeta(kitmeta);
         v.getInventory().setItem(0, kit); 
         }
         if(Eventos.Eventos.kits4.contains(v.getName())){
         v.getInventory();
         ItemStack kit = new ItemStack(277);
         ItemMeta kitmeta = kit.getItemMeta();
         kitmeta.setDisplayName("§bPa - DimaCatra");
         kit.setItemMeta(kitmeta);
         v.getInventory().setItem(0, kit); 
         }
         }
         else{
         ItemStack kit = new ItemStack(269);
         ItemMeta kitmeta = kit.getItemMeta();
         kitmeta.setDisplayName("§bPa - Padrao");
         kit.setItemMeta(kitmeta);
         v.getInventory().setItem(0, kit); 
         }
         
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento Começou!");
         }
         PreLobby=false;
         }
        }      
        }, 70 * 20);
    task_1 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         Eventos.Eventos.StartSpleef=true;
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 60 segundos");
         }
         }
        }      
        }, 11 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 30 segundos");
         }
         }
        }      
        }, 40 * 20);
    task_2 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 25 segundos");
         }
         }
        }      
        }, 45 * 20);
    task_3 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 20 segundos");
         }
         }
        }      
        }, 50 * 20);
    task_4 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 10 segundos");
         }
         }
        }      
        }, 60 * 20);
    task_5 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 9 segundos");
         }
         }
        }      
        }, 61 * 20);
    task_6 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 8 segundos");
         }
         }
        }      
        }, 62 * 20);
    task_7 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 7 segundos");
         }
         }
        }      
        }, 63 * 20);
    task_8 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 6 segundos");
         }
         }
        }      
        }, 64 * 20);
     task_9 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 5 segundos");
         }
         }
        }      
        }, 65 * 20);
     task_10 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 4 segundos");
         }
         }
        }      
        }, 66 * 20);
     task_11 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 3 segundos");
         }
         }
        }      
        }, 67 * 20);
     task_12 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 2 segundos");
         }
         }
        }      
        }, 68 * 20);
     task_13 = scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){
         
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento vai inicar em 1 segundos");
         }
         }
        }      
        }, 69 * 20);
    }           
            }
        }
    }
    }
        return false;
        
    }
    
    
public void unloadWorld(World world) {
    this.world = Bukkit.getWorld("");
    if(!world.equals(null)) {
        Bukkit.getServer().unloadWorld(world, true);
    }
}
public boolean deleteWorld(File path) {
      if(path.exists()) {
          File files[] = path.listFiles();
          for(int i=0; i<files.length; i++) {
              if(files[i].isDirectory()) {
                  deleteWorld(files[i]);
              } else {
                  files[i].delete();
              }
          }
      }
      return(path.delete());
}
public void copyWorld(File source, File target){
    try {
        ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
        if(!ignore.contains(source.getName())) {
            if(source.isDirectory()) {
                if(!target.exists())
                target.mkdirs();
                String files[] = source.list();
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File destFile = new File(target, file);
                    copyWorld(srcFile, destFile);
                }
            } else {
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0)
                    out.write(buffer, 0, length);
                in.close();
                out.close();
            }
        }
    } catch (IOException e) {
 
    }
} 
}
