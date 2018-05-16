/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.wiljafor1.saobalancear;

import java.util.ArrayList;
import static me.wiljafor1.saobalancear.Main.economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author WillianDev
 */
public class Gerencia implements Listener{
public static ArrayList WhiteList = new ArrayList();
public static ArrayList BlackList = new ArrayList();
public static double money;
    Main plugin;
    public Gerencia(Main plugin) {
        this.plugin = plugin;
    }  
  @EventHandler
  public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
    //p.sendMessage(""+ args[0]);
    //p.sendMessage(""+ args[1]);
    //p.sendMessage(""+ args[2]);
    //p.sendMessage(""+ args.length);
    if(p.hasPermission("saosys.*")){//Admin
       if(BlackList.contains(args[0])){
       e.setCancelled(true);    
       }
       else{
       e.setCancelled(false);  
       return;    
       }
    }
    else{
        if(args.length >= 4){
         //Player Normal            [0]  [1]    [2]    [3]
        if(WhiteList.contains(args[0])){// /money pay [Player] [Money]
            //p.sendMessage("/money");
            if(WhiteList.contains(args[1])){
            e.setCancelled(true);
                Player player2 = plugin.getServer().getPlayerExact(args[2]);
                String playerGroup = plugin.perms.getPrimaryGroup(p.getPlayer());
                double p1 = plugin.economy.getBalance(p.getName());
                double p2 = plugin.economy.getBalance(args[2]);
                int money = Integer.parseInt(args[3]); 
                int p1t = (int) (p1 - money);
                int p2t = (int) (p2 + money); 
                if(p.hasPermission("saob.p1")){//p1
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p1")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p2")){//p2
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p2")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p3")){//p3
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p3")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p4")){//p4
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p4")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p5")){//p5
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p5")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p6")){//p6
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p6")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p7")){//p7
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p7")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p8")){//p8
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p8")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p9")){//p9
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p9")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
                if(p.hasPermission("saob.p10")){//p10
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                    else{
                    if(player2 == null){
                        p.sendMessage("§6[§2Money§6] §6"+ args[2] +" §enao esta online!");
                        return;
                    }
                        if(plugin.getServer().getPlayerExact(args[2]).hasPermission("saob.p10")){//P1 para P1
                            if(plugin.getServer().getPlayerExact(args[2]).getName() == p.getName()){//Verefica se o player estar mandando para se mesmo
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode para si mesmo!");
                                return;
                            }
                            if(p1t < 0){//nao tem money
                                p.sendMessage("§6[§2Money§6] §eVoce nao tem a quantidade nescessaria!");
                                return;
                            }
                            else{//dar certo
                                plugin.economy.withdrawPlayer(p.getName(), money);
                                plugin.economy.depositPlayer(args[2], money);
                                plugin.getServer().getPlayerExact(args[2]).sendMessage("§6[§2Money§6] §eVoce Recebeu §6"+ money +" §eReais de §6"+p.getName());
                                p.sendMessage("§6[§2Money§6] §eVoce enviou §6"+ money +" §e para §6"+ args[2]);
                                return;
                            } 
                        }
                        else{
                                p.sendMessage("§6[§2Money§6] §eVoce nao pode doar para §6"+ args[2] +"§e!");
                                return; 
                        }
                    }
                }
                
            }
            else{
                return;
            }
        }
        else{
            return;    
        }
    }
    else{
       if(BlackList.contains(args[0])){
       e.setCancelled(true);    
       }
       else{
       e.setCancelled(false);  
       return;    
       } 
    }
    }
  }
}
