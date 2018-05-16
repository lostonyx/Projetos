/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.wiljafor1.saochecar;

import java.util.ArrayList;
import java.util.List;
import static me.wiljafor1.saochecar.MainConfig.config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ovh.minex.FPMCAPI;

/**
 *
 * @author WillianDev
 */
public class Comandos implements Listener,CommandExecutor {
    Main plugin;
    public Comandos(Main plugin) {
        this.plugin = plugin;
    }  
@Override
public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
            Player p = (Player)cs;
        if(cs instanceof Player) {
            if(cs.hasPermission("sao.admin")) {//saoinfo staff add <grupo> <nome>
                if(cmnd.getName().equalsIgnoreCase("checar")){
                    if(args.length == 1){
                        Player pp = plugin.getServer().getPlayerExact(args[0]);
                        if(pp == null){
                        cs.sendMessage("Esse player não está on!");    
                        }
                        else{
                        FPMCAPI.sendCheckRequest(pp, true);
                        //Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "fpmc check "+ pp.getName());
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawn "+ pp.getName());
                        pp.sendMessage(config.getString("tag").replace("&", "§"));
                        for(String linha:config.getStringList("tela")){
                            pp.sendMessage(linha.replace("&", "§").replace("%player%", pp.getDisplayName()));
                        }
                        plugin.PlayerCheck.add(pp.getName());
                        cs.sendMessage("Pedido foi enviado!");
                        }

                    }
                    else{
                        cs.sendMessage("Use /checar nome");    
                    }
                }
                if(cmnd.getName().equalsIgnoreCase("okay")){
                    if(args.length == 1){
                        Player pp = plugin.getServer().getPlayerExact(args[0]);
                        if(pp == null){
                        cs.sendMessage("Esse player não está on!");    
                        }
                        else{
                        cs.sendMessage("Player autorizado!");
                        plugin.PlayerCheck.remove(pp.getName());
                        }

                    }
                    else{
                        cs.sendMessage("Use /okay nome");    
                    }
                }

                    
            }
        }
        else{
                if(cmnd.getName().equalsIgnoreCase("okay")){
                    if(args.length == 1){
                        Player pp = plugin.getServer().getPlayerExact(args[0]);
                        if(pp == null){
                        cs.sendMessage("Esse player não está on!");    
                        }
                        else{
                        cs.sendMessage("Player autorizado!");
                        plugin.PlayerCheck.remove(pp.getName());
                        }

                    }
                    else{
                        cs.sendMessage("Use /okay nome");    
                    }
                }
        }
        return false;
    }
    
}
