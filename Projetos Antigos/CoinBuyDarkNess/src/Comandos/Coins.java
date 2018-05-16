/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import coindarkness.CoinDarkNess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author WillianDev
 */
public class Coins implements CommandExecutor {
    CoinDarkNess plugin;
    public Coins(CoinDarkNess plugin) {
        this.plugin = plugin;
    }
String url=null;
static String linha;

// Dados da URL (código HTML)
String dados=getText(url);  

    
    private static String getText(String urlloc) {
	try {
		URL url = new URL(urlloc);
		URLConnection openConnection = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String linha = reader.readLine();
	}
        
        catch (IOException e) {
	}
	return null;
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if(strings.length <= 0){//esse era do meu antigo server yep do site online, vc juntava e comprava vip com isso, mas so ganhava com evento
        cs.sendMessage("Use: /coins [Compra/Ver]");      
        }        
        else{
        Player xp = (Player)cs;
        String playerGroup = plugin.perms.getPrimaryGroup(xp.getPlayer());
        double p1 = plugin.economy.getBalance(xp.getName());
        int money = 100000; 
        int p1t = (int) (p1 - money);        
        if(cs instanceof Player){
                if(cmnd.getName().equalsIgnoreCase("coins")) {
                    if (strings[0].equalsIgnoreCase("comprar")) {
                        if(p1t < 0){
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
                            return true;
                        }
                        else{
                            String url="http://localhost/api/addm.php?nickname=" +xp.getName()+ "&money=1";
                            getText(url);
                            plugin.economy.withdrawPlayer(xp.getName(), money);
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Coin Comprado!");
                            return true;
                        }                                            
                    }
                    if (strings[0].equalsIgnoreCase("ver")) {
                            cs.sendMessage(ChatColor.RED + "§0§l[§6§lDarkBank§0§l] "+ ChatColor.WHITE +"Entre no site e veja :D, http://darknessevolution.com/?&page=lojac");                                      
                    }                    
                }         
        } 
        else {
            cs.sendMessage("§0§l[§6§lPAY§0§l] "+"§cVoce nao e um player.");
        }
        return false;        
    }
        return false;
    }
}  
    
