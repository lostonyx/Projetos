package me.wiljafor1.saoblock;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import me.wiljafor1.saoblock.Main;
import me.wiljafor1.util.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;

public class Comandos implements Listener,CommandExecutor  {
    Main plugin;
    Connection connection;
    public Comandos(Main plugin) {
        this.plugin = plugin;
    }  
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
            Player p = (Player)cs;
        if(cs instanceof Player) {
            if(cs.hasPermission("sao.admin")) {//saob comando <add/del> <comando>
                if(cmnd.getName().equalsIgnoreCase("saob")){
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("comando")){
                            if(args.length == 3){
                                if(args[1].equalsIgnoreCase("add")){
                                    CriarComando(args[2]);
                                    p.sendMessage("Comando liberado!!");
                                    plugin.reload();
                                }
                                if(args[1].equalsIgnoreCase("del")){
                                    DelComando(args[2]);
                                    p.sendMessage("Comando bloqueado!!");
                                    plugin.reload();
                                }
                                else{
                                    p.sendMessage("use: /saob comando <add/del> <Comando>");
                                }
                            }
                            else{
                                p.sendMessage("use: /saob comando <add/del> <Comando>");
                            }
                    }
                    else{
                        if(args[0].equalsIgnoreCase("reload")){
                            p.sendMessage("reload");
                            plugin.reload();
                        }
                        else{
                            if(args[0].equalsIgnoreCase("list")){
                                ListarComando(p);
                            }
                            else{
                            p.sendMessage("§3§m------§f [§ESAOBLOCKS§f] §3§m------");
                            p.sendMessage("§e/saob comando <add/del> <Comando>");
                            p.sendMessage("§e/saob list §8- §7Listar os comandos");
                            p.sendMessage("§e/saob reload §8- §7Recarregar o Plugin");
                            p.sendMessage("§3§m------§f [§ESAOBLOCKS§f] §3§m------");
                            } 
                        } 
                    }
                }
                else{
                            p.sendMessage("§3§m------§f [§ESAOBLOCKS§f] §3§m------");
                            p.sendMessage("§e/saob comando <add/del> <Comando>");
                            p.sendMessage("§e/saob list §8- §7Listar os comandos");
                            p.sendMessage("§e/saob reload §8- §7Recarregar o Plugin");
                            p.sendMessage("§3§m------§f [§ESAOBLOCKS§f] §3§m------");
                }
                }                
            }
        }
        else{
        }
        return false;
    }
    public boolean CriarComando(String cmd){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `blacklist` (`id`, `comando`) VALUES (NULL, '"+ cmd +"');");
            ps.execute();
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return true;
    }
    public String BuscarComando(String cmd){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        ResultSet rs = null;
        String tipo = "s";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comandos WHERE Comando = '"+ cmd+ "'");
            rs = ps.executeQuery();
            while(rs.next()){
                
                tipo = rs.getString("Tipo");
                
            }
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return tipo;
    }
    public String ListarComando(Player p){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        ResultSet rs = null;
        String tipo = "s";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM blacklist");
            rs = ps.executeQuery();
            while(rs.next()){
                p.sendMessage("Lista de comandos liberados:");
                do {
		String Comandos = rs.getString("comando");
		p.sendMessage("§2/"+Comandos);
		} 
                while(rs.next()); 
		p.sendMessage("");
            }
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return tipo;
    }
    public void DelComando(String cmd){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM blacklist WHERE comando = '"+ cmd+ "'");
            ps.execute();
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
    String Comandos = args[0].replace("/", "");
    if(p.isOp()){
        //p.sendMessage("op");
        e.setCancelled(false);
        return;  
    }
    else{
    if(plugin.Comando.contains(Comandos)){
        //p.sendMessage("white");
        return;
    }
    else{
        //p.sendMessage("black");
        e.setCancelled(true);
        return;
    }
    }
  }
    
    
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
    Player player = event.getPlayer();

    String[] msg = event.getMessage().split(" ");

    if (!player.hasPermission("antitab.bypass"))
    {
        for (String Loop : plugin.plugins) {
          if (msg[0].equalsIgnoreCase("/" + Loop)) {
            player.sendMessage("");

            event.setCancelled(true);
          }
        }

    }
  }
}

