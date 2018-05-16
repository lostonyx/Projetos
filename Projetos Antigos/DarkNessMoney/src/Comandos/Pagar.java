/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import darknessmoney.DarkNessMoney;
import static darknessmoney.DarkNessMoney.economy;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author WillianDev
 */
public class Pagar implements CommandExecutor {
    DarkNessMoney plugin;
    public Pagar(DarkNessMoney plugin) {
        this.plugin = plugin;
    }    

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if(strings.length <= 1){
        cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");      
        }        
        else{
        Player xp = (Player)cs;
        String playerGroup = plugin.perms.getPrimaryGroup(xp.getPlayer());
        double p1 = plugin.economy.getBalance(xp.getName());
        double p2 = plugin.economy.getBalance(strings[0]);
        int money = Integer.parseInt(strings[1]); 
        int p1t = (int) (p1 - money);
        int p2t = (int) (p2 + money);        
        if(cs instanceof Player){
          if(strings[0]== null || strings[1] == null){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");  
          }
          else{
            if(cs.hasPermission("dk.pagar")) {  
                    if(strings[1] == null){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings[0]== null){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings == null){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                if(cs.hasPermission("dk.novato")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        } 
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                    
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.terra")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.areia")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        } 
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.arenito")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                         else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.pedra")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.ferro")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }         
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        } 
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.ouro")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }                         
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.diamante")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                         
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.esmeralda")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                       
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                    
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.platina")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                  
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.time")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                  
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.guerreiro")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                  
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }
                if(cs.hasPermission("dk.assassino")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                  
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              } 
                if(cs.hasPermission("dk.templario")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                  
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              } 
                if(cs.hasPermission("dk.eterno")){
                if(cmnd.getName().equalsIgnoreCase("pagar")) {
                    if(strings.length < 1){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length <= 0){
                    cs.sendMessage("Use: /pagar [Nome do jogador] [nomey]");
                    }
                    if(strings.length >= 2){
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.novato")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.terra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.areia")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.arenito")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.pedra")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ferro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.ouro")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.diamante")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }                        
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.esmeralda")){
                        xp.sendMessage("§0§l[§6§lDarkBank§0§l] você nao pode doar ao rank inferior ao seu!");
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.platina")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.time")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                  
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.guerreiro")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.assassino")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                      
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.templario")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                     
                        }
                        if(plugin.getServer().getPlayerExact(strings[0]).hasPermission("dk.eterno")){
                        if(plugin.getServer().getPlayerExact(strings[0]).getName() == xp.getName()){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao Pode doar a si mesmo");
                            return true;
                        }
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            plugin.economy.depositPlayer(strings[0], money);
                            plugin.getServer().getPlayerExact(strings[0]).sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Recebido de "+ xp.getName() +" Total de "+money+" Reais!");
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Money Enviado!");
                            return true;
                        }                        
                        }                        
                    } 
                }
              }                 
            }
          }
        } else {
            cs.sendMessage("§0§l[§6§lPAY§0§l] "+"§cVoce nao e um player.");
        }
        return false;        
    }
        return false;
    }
}  