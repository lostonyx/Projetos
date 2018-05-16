package me.wiljafor1.eventos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import me.wiljafor1.SaoPlot;
import static me.wiljafor1.SaoPlot.economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class geral implements Listener{
    SaoPlot plugin;
    public static final UUID everyone = UUID.fromString("1-1-3-3-7");
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    public geral(SaoPlot plugin) {
        this.plugin = plugin;
    }  
    @EventHandler
    public void Comprar(InventoryClickEvent event){
      if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
                    Player player = (Player)event.getWhoClicked(); 
                    //TERRENO MEDIO
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bTerreno MEDIO"))){
                      if(event.getWhoClicked() instanceof Player) {       
                          event.setCancelled(true);
                          player.closeInventory();
                          player.sendMessage("medio ");
                          if(player.hasPermission("sao.vip")){
                              if(plugin.economy.getBalance(player) > 20000){
                                 player.sendMessage("comprou");
                                    try {
                                    java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
                                    plugin.flatfile = false;
                                    if (con == null) {
                                        plugin.flatfile = true;
                                    }
                                    else {
                                        int id_x = 0;
                                        int id_z = 0;
                                        Statement st = con.createStatement();
                                        ResultSet result = st.executeQuery("SELECT * FROM plot_50 WHERE id =(select max(id) from plot_50);");
                                        //ResultSet result = st.executeQuery("SELECT * FROM plot WHERE id =(select max(id) from plot) AND world =`50`;");
                                        //ResultSet result = st.executeQuery("SELECT * FROM `plot` WHERE `world`=50 LIMIT 1;");
                                        //player.sendMessage("x: " + result.getString("plot_id_x") + " z: " + result.getString("plot_id_x"));
                                        while (result.next()) {
                                        player.sendMessage("x: "+result.getString("plot_id_x"));
                                        if(result.getInt("plot_id_z") == 50){
                                           id_x =  result.getInt("plot_id_x") + 1;
                                           id_z = 0;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        else{
                                           id_x = result.getInt("plot_id_x");
                                           id_z = result.getInt("plot_id_z") + 1;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        //player.sendMessage("z: "+result.getString("plot_id_z"));
                                        }
                                    }
                                    con.close();
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                        plugin.flatfile=true;
                                    }

                              }
                              else{
                                 player.sendMessage("$4[X] $2Você precisa de 20k coins para comprar este terreno!");
                              }
                          }
                          else{
                              if(plugin.economy.getBalance(player) > 60000){
                                 player.sendMessage("comprou");
                                    try {
                                    java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
                                    plugin.flatfile = false;
                                    if (con == null) {
                                        plugin.flatfile = true;
                                    }
                                    else {
                                        int id_x = 0;
                                        int id_z = 0;
                                        Statement st = con.createStatement();
                                        ResultSet result = st.executeQuery("SELECT * FROM plot_50 WHERE id =(select max(id) from plot_50);");
                                        while (result.next()) {
                                        player.sendMessage("x: "+result.getString("plot_id_x"));
                                        if(result.getInt("plot_id_z") == 50){
                                           id_x =  result.getInt("plot_id_x") + 1;
                                           id_z = 0;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        else{
                                           id_x = result.getInt("plot_id_x");
                                           id_z = result.getInt("plot_id_z") + 1;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        }
                                    }
                                    con.close();
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                        plugin.flatfile=true;
                                    }
                              }
                              else{
                                 player.sendMessage("$4[X] $2Você precisa de 60k coins para comprar este terreno!");
                              }   
                          }
                        }  
                    }
                    //TERRENO GRANDE
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bTerreno GRANDE"))){
                      if(event.getWhoClicked() instanceof Player) {       
                          event.setCancelled(true);
                          player.closeInventory();
                          player.sendMessage("grande");
                          if(player.hasPermission("sao.vip")){
                              if(plugin.economy.getBalance(player) > 100000){
                                 player.sendMessage("comprou");
                                    try {
                                    java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
                                    plugin.flatfile = false;
                                    if (con == null) {
                                        plugin.flatfile = true;
                                    }
                                    else {
                                        int id_x = 0;
                                        int id_z = 0;
                                        Statement st = con.createStatement();
                                        ResultSet result = st.executeQuery("SELECT * FROM plot_50 WHERE id =(select max(id) from plot_50);");
                                        while (result.next()) {
                                        player.sendMessage("x: "+result.getString("plot_id_x"));
                                        if(result.getInt("plot_id_z") == 50){
                                           id_x =  result.getInt("plot_id_x") + 1;
                                           id_z = 0;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        else{
                                           id_x = result.getInt("plot_id_x");
                                           id_z = result.getInt("plot_id_z") + 1;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        }
                                    }
                                    con.close();
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                        plugin.flatfile=true;
                                    }
                              }
                              else{
                                 player.sendMessage("$4[X] $2Você precisa de 100k coins para comprar este terreno!");
                              }
                          }
                          else{
                              if(plugin.economy.getBalance(player) > 300000){
                                 player.sendMessage("comprou");
                                    try {
                                    java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
                                    plugin.flatfile = false;
                                    if (con == null) {
                                        plugin.flatfile = true;
                                    }
                                    else {
                                        int id_x = 0;
                                        int id_z = 0;
                                        Statement st = con.createStatement();
                                        ResultSet result = st.executeQuery("SELECT * FROM plot_50 WHERE id =(select max(id) from plot_50);");
                                        while (result.next()) {
                                        player.sendMessage("x: "+result.getString("plot_id_x"));
                                        if(result.getInt("plot_id_z") == 50){
                                           id_x =  result.getInt("plot_id_x") + 1;
                                           id_z = 0;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        else{
                                           id_x = result.getInt("plot_id_x");
                                           id_z = result.getInt("plot_id_z") + 1;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        }
                                    }
                                    con.close();
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                        plugin.flatfile=true;
                                    }
                              }
                              else{
                                 player.sendMessage("$4[X] $2Você precisa de 300k coins para comprar este terreno!");
                              }   
                          }
                        }  
                    }
                    //TERRENO GIGANTE
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bTerreno GIGANTE"))){
                      if(event.getWhoClicked() instanceof Player) {       
                          event.setCancelled(true);
                          player.closeInventory();
                          player.sendMessage("gigante");
                          if(player.hasPermission("sao.vip")){
                              if(plugin.economy.getBalance(player) > 1000000){
                                 player.sendMessage("comprou");
                                    try {
                                    java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
                                    plugin.flatfile = false;
                                    if (con == null) {
                                        plugin.flatfile = true;
                                    }
                                    else {
                                        int id_x = 0;
                                        int id_z = 0;
                                        Statement st = con.createStatement();
                                        ResultSet result = st.executeQuery("SELECT * FROM plot_50 WHERE id =(select max(id) from plot_50);");
                                        while (result.next()) {
                                        player.sendMessage("x: "+result.getString("plot_id_x"));
                                        if(result.getInt("plot_id_z") == 50){
                                           id_x =  result.getInt("plot_id_x") + 1;
                                           id_z = 0;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        else{
                                           id_x = result.getInt("plot_id_x");
                                           id_z = result.getInt("plot_id_z") + 1;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        }
                                    }
                                    con.close();
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                        plugin.flatfile=true;
                                    }
                              }
                              else{
                                 player.sendMessage("$4[X] $2Você precisa de 1kk coins para comprar este terreno!");
                              }
                          }
                          else{
                              if(plugin.economy.getBalance(player) > 3000000){
                                 player.sendMessage("comprou");
                                    try {
                                    java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
                                    plugin.flatfile = false;
                                    if (con == null) {
                                        plugin.flatfile = true;
                                    }
                                    else {
                                        int id_x = 0;
                                        int id_z = 0;
                                        Statement st = con.createStatement();
                                        ResultSet result = st.executeQuery("SELECT * FROM plot_50 WHERE id =(select max(id) from plot_50);");
                                        while (result.next()) {
                                        player.sendMessage("x: "+result.getString("plot_id_x"));
                                        if(result.getInt("plot_id_z") == 50){
                                           id_x =  result.getInt("plot_id_x") + 1;
                                           id_z = 0;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        else{
                                           id_x = result.getInt("plot_id_x");
                                           id_z = result.getInt("plot_id_z") + 1;
                                           BuyPlot(id_x, id_z, player);
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onDisable();
                                           plugin.getServer().getPluginManager().getPlugin("PlotSquared").onEnable();
                                        }
                                        }
                                    }
                                    con.close();
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                        plugin.flatfile=true;
                                    }
                              }
                              else{
                                 player.sendMessage("$4[X] $2Você precisa de 3kk coins para comprar este terreno!");
                              }   
                          }
                        }  
                    }
                }
            }
      }  
    }

    public boolean BuyPlot(int idx, int idz, Player player){
    Player p = player.getPlayer();
    			try {
				java.sql.Connection con = DriverManager.getConnection(plugin.mysql_url,plugin.mysql_user,plugin.mysql_pass);
				plugin.flatfile = false;
				if (con == null) {
					plugin.flatfile = true;
				}
				else {
					Statement st = con.createStatement();
					st.execute("INSERT INTO `plot` (`id`, `plot_id_x`, `plot_id_z`, `owner`, `world`, `timestamp`) VALUES (NULL, '"+ idx +"', '"+ idz +"', '"+ player.getUniqueId() +"', '50', CURRENT_TIMESTAMP)");
                                        st.execute("UPDATE `plot_50` SET `plot_id_x`="+ idx +",`plot_id_z`="+ idz +" WHERE id=1");
                                }
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				plugin.flatfile=true;
			}

    
    return true;
}
    
}
