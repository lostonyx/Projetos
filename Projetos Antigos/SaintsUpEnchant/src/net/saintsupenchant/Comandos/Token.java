package net.saintsupenchant.Comandos;

import net.saintsupenchant.Main;
import static net.saintsupenchant.listener.Enchant.eventodouble;
import static net.saintsupenchant.listener.Enchant.task;
import net.saintsupenchant.util.Messages;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Token implements CommandExecutor{
    Main plugin;
    
    public Token(Main plugin){
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
                if(cs instanceof Player){
                    Player player = (Player)cs;
                  if (cmnd.getName().equalsIgnoreCase("token")) {
                      if(args.length == 0){
                      cs.sendMessage(Messages.getString("su.seustokens").replace("&", "§")+plugin.getConfig().getInt("token."+player.getName().toLowerCase()));
                      }
                      if(args.length == 1){
                      if(args[0].contains("double")){
                          if(cs.hasPermission("saints.dono")){
                            if(eventodouble==false){
                            cs.sendMessage(Messages.getString("su.eventoiniciado").replace("&", "§"));
                            eventodouble=true;
                            BukkitScheduler scheduler = getServer().getScheduler();
                            task = scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){
                                public void run(){
                                    eventodouble=false;
                                }      
                            }, 3600 * 20); 
                            }
                            else{
                              cs.sendMessage(Messages.getString("su.eventojainiciado").replace("&", "§"));
                            }
                          }
                      }
                      }
                      if(args.length == 3){
                          if(args[0].contains("set")){
                              if(cs.hasPermission("saints.dono")){
                              if(plugin.getConfig().getString("token."+args[1].toLowerCase()) == null){
                                cs.sendMessage(Messages.getString("su.playernaoexiste").replace("&", "§"));
                              }
                              else{
                                plugin.getConfig().set("token."+args[1].toLowerCase(), Integer.parseInt(args[2]));
                                cs.sendMessage(Messages.getString("su.coinset").replace("&", "§"));
                              }
                              }
                          }
                          if(args[0].contains("add")){
                              if(cs.hasPermission("saints.dono")){
                              if(plugin.getConfig().getString("token."+args[1].toLowerCase()) == null){
                                cs.sendMessage(Messages.getString("su.playernaoexiste").replace("&", "§"));
                              }
                              else{
                                cs.sendMessage(Messages.getString("su.coinadd").replace("&", "§"));
                                plugin.getConfig().set("token."+args[1].toLowerCase(), plugin.getConfig().getInt("token."+args[1].toLowerCase())+Integer.parseInt(args[2]));
                              }
                              }
                          }
                      }
                      
                      
                 }
                }
                else{
                  if (cmnd.getName().equalsIgnoreCase("token")) {
                      if(args.length == 1){
                      if(args[0].contains("double")){
                          if(cs.hasPermission("saints.dono")){
                            if(eventodouble==false){
                            cs.sendMessage(Messages.getString("su.eventoiniciado").replace("&", "§"));
                            eventodouble=true;
                            BukkitScheduler scheduler = getServer().getScheduler();
                            task = scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){
                                public void run(){
                                    eventodouble=false;
                                }      
                            }, 3600 * 20); 
                            }
                            else{
                              cs.sendMessage(Messages.getString("su.eventojainiciado").replace("&", "§"));
                            }
                          }
                      }
                      }
                      if(args.length == 3){
                          if(args[0].contains("set")){
                              if(cs.hasPermission("saints.dono")){
                              if(plugin.getConfig().getString("token."+args[1].toLowerCase()) == null){
                                cs.sendMessage(Messages.getString("su.playernaoexiste").replace("&", "§"));
                              }
                              else{
                                plugin.getConfig().set("token."+args[1].toLowerCase(), Integer.parseInt(args[2]));
                                cs.sendMessage(Messages.getString("su.coinset").replace("&", "§"));
                              }
                              }
                          }
                          if(args[0].contains("add")){
                              if(cs.hasPermission("saints.dono")){
                              if(plugin.getConfig().getString("token."+args[1].toLowerCase()) == null){
                                cs.sendMessage(Messages.getString("su.playernaoexiste").replace("&", "§"));
                              }
                              else{
                                cs.sendMessage(Messages.getString("su.coinadd").replace("&", "§"));
                                plugin.getConfig().set("token."+args[1].toLowerCase(), plugin.getConfig().getInt("token."+args[1].toLowerCase())+Integer.parseInt(args[2]));
                              }
                              }
                          }
                      }
                      
                      
                 }
                }
    return true;
    }
    
}
