package net.wiljafor1;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import static com.sk89q.worldguard.bukkit.BukkitUtil.*;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.util.HashMap;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WillianDev
 */
public class SaoHouse extends JavaPlugin implements Listener{
    
    //Mysql
    protected String mysql_url = "";
    protected String mysql_user = "";
    protected String mysql_pass = "";
    protected boolean flatfile = true;
    protected HashMap<String,String> using_codes = new HashMap<String,String>();
    public static Economy economy = null;
    
    
    private WorldGuardPlugin getWorldGuard() {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

    // WorldGuard may not be loaded
    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
        return null; // Maybe you want throw an exception instead
    }

        return (WorldGuardPlugin) plugin;
    }
    
    private boolean setupEconomy(){
    RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
        economy = economyProvider.getProvider();
    }

    return (economy != null);
    } 
    
    @Override
    public void onEnable() {        
            //ResultSet result = statement.executeQuery("INSERT INTO spawnpoint (generalname, generalenabled, generalworld, mobname, mobamount, delaysmaxrespawn, delaysmaxdespawn, centerx, centery, centerz, radiusx, radiusy, radiusz, radiusdetection) VALUES ('teste', 'true', 'floor1', 'luck', '60', '60', '60', '0', '0', '0', '0', '0', '0', '0', '0'");

        
        setupEconomy();
        PluginManager pm = getServer().getPluginManager();
        Bukkit.getPluginManager().registerEvents(this,this);
        getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {

            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        reloadConfig();
        saveDefaultConfig();
         //Cardinal.call().sendException(new CardinalException("API LIGADA", "Testando api", "deu certo porra", Priority.HIGH));
         
         
         
		if(getConfig().getBoolean("MySQL.use")) {
			mysql_url = "jdbc:mysql://"+getConfig().getString("MySQL.Host").trim()+":"+getConfig().getInt("MySQL.Port")+"/"+getConfig().getString("MySQL.Database").trim()+"";
			mysql_user = getConfig().getString("MySQL.Username").trim();
			mysql_pass = getConfig().getString("MySQL.Password").trim();
			try {
				Connection con = DriverManager.getConnection(mysql_url,mysql_user,mysql_pass);
				flatfile = false;
				if (con == null) {
					getLogger().info("Connection to MySQL failed! Changing to flatfile.");
					flatfile = true;
				}
				else {
					getLogger().info("Connected to MySQL server!");
					Statement st = con.createStatement();
					st.execute("CREATE TABLE IF NOT EXISTS `spawnpoint` (`id` int(11) NOT NULL, `generalname` varchar(255) NOT NULL, `generalenabled` varchar(255) NOT NULL, `generalworld` varchar(255) NOT NULL, `mobname` varchar(255) NOT NULL, `mobamount` varchar(255) NOT NULL, `delaysmaxrespawn` varchar(255) NOT NULL, `delaysmaxdespawn` varchar(255) NOT NULL, `centerx` varchar(255) NOT NULL, `centery` varchar(255) NOT NULL, `centerz` varchar(255) NOT NULL, `radiusx` varchar(255) NOT NULL, `radiusy` varchar(255) NOT NULL, `radiusz` varchar(255) NOT NULL, `radiusdetection` varchar(255) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
				}
				con.close();
			}
			catch (SQLException e) {
				getLogger().warning("Connection to MySQL failed! Changing to flatfile.");
				e.printStackTrace();
				flatfile=true;
			}
		}
		else
			getLogger().info("Using flatfile system.");
    }
    
    public void addDef(YamlConfiguration config, String path, String value){
        if(!config.contains(path)){
            config.set(path, value);
        }    
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Action a = e.getAction();
        Block block = e.getClickedBlock();
        Player plr = e.getPlayer();
        Material myb = block.getType();
        //economia
        double p1ayermoney = economy.getBalance(plr.getName());
        
        
        
        if(myb.equals(Material.SIGN_POST) || myb.equals(Material.WALL_SIGN)) {
            WorldGuardPlugin worldGuard = getWorldGuard();
            Sign sign = (Sign) block.getState();
            String[] ln = sign.getLines();
            int money = Integer.parseInt(ln[3]);
            int pmt = (int) (p1ayermoney - money);
            if(ln[0].equalsIgnoreCase("[SaoHouse]")){
                //plr.sendMessage("" + plr.getLocation());
                if(ln[1].equalsIgnoreCase("A Venda")){
                    if(pmt < 0){
                    plr.sendMessage(ChatColor.RED + "§0§l[§6§lSaoHouse§0§l] "+ ChatColor.WHITE +"voce nao tem money!");   
                    }
                    else{
                        economy.withdrawPlayer(plr.getName(), money);
                        plr.sendMessage(ChatColor.RED + "§0§l[§6§lSaoHouse§0§l] "+ ChatColor.WHITE +"Casa Foi Comprada!");
                        sign.setLine(1, "Comprada");
                        sign.update();
                        //worldguard
                        RegionManager regionManager = worldGuard.getRegionManager(plr.getWorld());
                        ProtectedRegion set = regionManager.getRegion(ln[2]);
                        DefaultDomain dono = set.getOwners();
                        dono.addPlayer(plr.getName());
                    }
                //plr.sendMessage("Casa Comprada!");
                } 
                if(ln[1].equalsIgnoreCase("Comprada")){
                plr.sendMessage(ChatColor.RED + "§0§l[§6§lSaoHouse§0§l] "+ ChatColor.WHITE +"Essa Casa Nâo esta á venda!");
                }   
            }
        }
        else{
            
        }
    }

    @Override
    public void onDisable() {
    reloadConfig();
    saveDefaultConfig(); 
    }
    
}
