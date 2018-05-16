package me.wiljafor1.saocmds;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import static me.wiljafor1.saocmds.MainConfig.loadConfig;
import me.wiljafor1.saocmds.Utils.Database;
import me.wiljafor1.saocmds.Utils.SQLite;
import me.wiljafor1.saocmds.Comandos;
import me.wiljafor1.saocmds.Utils.Errors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin{
    public static Main plugin;
    public ArrayList<String> Comando = new ArrayList<String>();
    public static HashMap<String,String> ComandoC = new HashMap();
    public static HashMap<String,String> ComandoS = new HashMap();

   
        private Database db;
        @Override
        public void onEnable() { 
            File dataFolder = new File(getDataFolder(), "db.db");
            if (!dataFolder.exists()){
                try {
                    dataFolder.createNewFile();
                } catch (IOException e) {
                    plugin.getLogger().log(Level.SEVERE, "File write error: db.db");
                }
            }
            plugin = this;
            this.db = new SQLite(this);
            this.db.load();
            saveDefaultConfig();
            loadConfig();
            getCommand("saoc").setExecutor(new Comandos(this) {});
            Bukkit.getServer().getPluginManager().registerEvents(new Comandos(this), this); 
        }
        
        @Override
        public void onDisable() {
            
        } 
        
        public void reload()
        {
            try
            {
                Comando.clear();
                ComandoC.clear();
                ComandoS.clear();
                this.db = new SQLite(this);
                this.db.load();
                loadConfig();
                Bukkit.getConsoleSender().sendMessage("§4[OK]Sistema carregado");
            }
            catch (Exception e)
            {
                Bukkit.getConsoleSender().sendMessage("§4[ERROR]Sistema dando error");
            }
        }
        
        public Database getRDatabase() {
            return this.db;
        }
        

        
}
