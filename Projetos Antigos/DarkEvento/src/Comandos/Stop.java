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
import static Eventos.Eventos.task;
import static Eventos.Eventos.task_1;
import static Eventos.Eventos.task_2;
import static Eventos.Eventos.task_3;
import static Eventos.Eventos.task_4;
import static Eventos.Eventos.task_5;
import static Eventos.Eventos.task_6;
import static Eventos.Eventos.task_7;
import static Eventos.Eventos.task_8;
import static Eventos.Eventos.task_9;
import static Eventos.Eventos.task_10;
import static Eventos.Eventos.task_11;
import static Eventos.Eventos.task_12;
import static Eventos.Eventos.task_13;
/**
 *
 * @author WillianDev
 */
public class Stop implements CommandExecutor{
    DarkEvento plugin;
    public Stop(DarkEvento plugin) {
        this.plugin = plugin;
    } 
    private World world;
    int max = Bukkit.getOnlinePlayers().size();
    BukkitScheduler scheduler1 = getServer().getScheduler();
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
    final Player p = (Player)cs;
    if (cmnd.getName().equalsIgnoreCase("dkstop"))
    {
    if(p.hasPermission("dkevento.admin")) {
    if(PreLobby == true){
         World w = Bukkit.getWorld("world");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
         Location loc = new Location(w, x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§b§l[§f§lEventos§b§l] §f§lEvento Cancelado!");
         }
         pg.clear();
         Eventos.Eventos.l1.clear();
         Eventos.Eventos.l2.clear();
         Eventos.Eventos.l3.clear();
         Eventos.Eventos.pgt.clear();
         Eventos.Eventos.pgn.clear();
         Eventos.Eventos.mgp.clear();
         Eventos.Eventos.EndCorrida=false;
         Eventos.Eventos.PreLobby=false;
         Eventos.Eventos.StartCorrida=false;
         Eventos.Eventos.StartSpleef=false;
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
    }
    }
    
    if(Eventos.Eventos.StartCorrida== true){
         World w = Bukkit.getWorld("world");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
         Location loc = new Location(w, x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§b§l[§f§lEventos§b§l] §f§lEvento Cancelado!");
         }
         pg.clear();
         Eventos.Eventos.l1.clear();
         Eventos.Eventos.l2.clear();
         Eventos.Eventos.l3.clear();
         Eventos.Eventos.pgt.clear();
         Eventos.Eventos.pgn.clear();
         Eventos.Eventos.mgp.clear();
         Eventos.Eventos.EndCorrida=false;
         Eventos.Eventos.PreLobby=false;
         Eventos.Eventos.StartCorrida=false;
         Eventos.Eventos.StartSpleef=false;
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
    }
    }
    
    if(Eventos.Eventos.StartSpleef== true){
         World w = Bukkit.getWorld("world");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
         Location loc = new Location(w, x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§b§l[§f§lEventos§b§l] §f§lEvento Cancelado!");
         }
         pg.clear();
         Eventos.Eventos.l1.clear();
         Eventos.Eventos.l2.clear();
         Eventos.Eventos.l3.clear();
         Eventos.Eventos.pgt.clear();
         Eventos.Eventos.pgn.clear();
         Eventos.Eventos.mgp.clear();
         Eventos.Eventos.EndCorrida=false;
         Eventos.Eventos.PreLobby=false;
         Eventos.Eventos.StartCorrida=false;
         Eventos.Eventos.StartSpleef=false;
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
    }
    } 
    
    if(Eventos.Eventos.EndCorrida == true){
         World w = Bukkit.getWorld("world");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
         Location loc = new Location(w, x, y, z, pa, a);
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
         v.teleport(loc);
         v.sendMessage("§b§l[§f§lEventos§b§l] §f§lEvento Cancelado!");
         }
         pg.clear();
         Eventos.Eventos.l1.clear();
         Eventos.Eventos.l2.clear();
         Eventos.Eventos.l3.clear();
         Eventos.Eventos.pgt.clear();
         Eventos.Eventos.pgn.clear();
         Eventos.Eventos.mgp.clear();
         Eventos.Eventos.EndCorrida=false;
         Eventos.Eventos.PreLobby=false;
         Eventos.Eventos.StartCorrida=false;
         Eventos.Eventos.StartSpleef=false;
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
    }
    }    
    if(PreLobby==false && StartCorrida==false && Eventos.Eventos.StartSpleef && Eventos.Eventos.PreLobby == false){
      cs.sendMessage("§b§l[§f§lEventos§b§l] §f§lNenhum Evento ocorrendo!");
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
