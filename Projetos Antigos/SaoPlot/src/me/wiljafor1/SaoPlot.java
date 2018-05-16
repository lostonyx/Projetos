package me.wiljafor1;
import com.intellectualcrafters.plot.api.PlotAPI;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import me.wiljafor1.comandos.Comprar;
import me.wiljafor1.eventos.geral;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

public class SaoPlot extends JavaPlugin{
    public static Economy economy = null;
    public PlotAPI api;
    public String mysql_url = "";
    public String mysql_user = "";
    public String mysql_pass = "";
    public boolean flatfile = true;
    
    private boolean setupEconomy(){
    RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
        economy = economyProvider.getProvider();
    }

    return (economy != null);
    } 
    @Override
    public void onEnable() {
        setupEconomy();
        PluginManager manager = Bukkit.getServer().getPluginManager();
        final Plugin plotsquared = manager.getPlugin("PlotSquared");
        if(plotsquared != null && !plotsquared.isEnabled()) {
            manager.disablePlugin(this);
            return;
        }
        api = new PlotAPI();
        getCommand("terreno").setExecutor(new Comprar(this));  
        Bukkit.getServer().getPluginManager().registerEvents(new geral(this), this); 
        if(getConfig().getBoolean("MySQL.use")) {
	mysql_url = "jdbc:mysql://"+getConfig().getString("MySQL.Host").trim()+":"+getConfig().getInt("MySQL.Port")+"/"+getConfig().getString("MySQL.Database").trim()+"";
	mysql_user = getConfig().getString("MySQL.Username").trim();
	mysql_pass = getConfig().getString("MySQL.Password").trim();
		try {
			java.sql.Connection con = DriverManager.getConnection(mysql_url,mysql_user,mysql_pass);
			flatfile = false;
			if (con == null) {
				getLogger().info("Connection to MySQL failed! Changing to flatfile.");
				flatfile = true;
			}
			else {
				getLogger().info("MySQL server conexao com sucesso!");
				Statement st = con.createStatement();
                                st.execute("CREATE TABLE IF NOT EXISTS `plot_50` (`id` int(11) NOT NULL, `plot_id_x` int(11) NOT NULL, `plot_id_z` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
                                //st.execute("ALTER TABLE `plot_50` ADD PRIMARY KEY (`id`);");
                                //st.execute("ALTER TABLE `plot_50` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;");
                                //st.execute("CREATE TABLE IF NOT EXISTS `cardinal`.`Cristal` ( `id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(255) NOT NULL, `world` VARCHAR(255) NOT NULL, `x` DOUBLE NOT NULL , `y` DOUBLE NOT NULL , `z` DOUBLE NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;");
			}
			con.close();
			}
			catch (SQLException e) {
				getLogger().warning("Connection to MySQL failed! Changing to flatfile.");
				e.printStackTrace();
				flatfile=true;
			}
    }
    else{
	getLogger().info("Using flatfile system.");
    }
    saveDefaultConfig();  
    }
    
    @Override
    public void onDisable(){
        
    }
    
}
