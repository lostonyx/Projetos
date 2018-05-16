package me.wiljafor1.saoblock;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
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
import me.wiljafor1.util.Database;
import me.wiljafor1.util.SQLite;
//import me.wiljafor1.saocmds.Comandos;
import me.wiljafor1.util.Errors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin{
    public static Main plugin;
    public ArrayList<String> Comando = new ArrayList<String>();
    public static HashMap<String,String> ComandoC = new HashMap();
    public static HashMap<String,String> ComandoS = new HashMap();
    public ArrayList<String> plugins = new ArrayList();
    ProtocolManager protocolManager;
    Comandos com;

   
        private Database db;
        @Override
        public void onEnable() { 
            plugins.add("pl");
            plugins.add("bukkit:pl");
            plugins.add("plugins");
            plugins.add("bukkit:plugins");
            plugins.add("ver");
            plugins.add("bukkit:ver");
            plugins.add("version");
            plugins.add("bukkit:version");
            plugins.add("about");
            plugins.add("bukkit:about");
            plugins.add("?");
            plugins.add("bukkit:?");
            plugins.add("me");
            plugins.add("bukkit:me");
            plugins.add("kill");
            plugins.add("bukkit:kill");
            plugins.add("plugman");
            plugins.add("plugman:plugman");
            plugins.add("pex");
            protocolManager = ProtocolLibrary.getProtocolManager();
            protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE })
            {
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE)
                    try {
                        PacketContainer packet = event.getPacket();
                        String message = ((String)packet.getSpecificModifier(String.class).read(0)).toLowerCase();

                        if ((message.startsWith("")) && (!message.contains("  "))) {
                            event.setCancelled(true);
                        }
                    }
                    catch (FieldAccessException e)
                    {
            
                    }
            }
            });
            saveDefaultConfig();
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
            getCommand("saob").setExecutor(new Comandos(this) {});
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
