package co.saomc.chunkspawnerlimit;
import co.saomc.chunkspawnerlimit.listeners.BlockPlace;
import co.saomc.chunkspawnerlimit.listeners.ChunkLoad;
import co.saomc.chunkspawnerlimit.commands.ListCommand;
import co.saomc.chunkspawnerlimit.commands.DeleteCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class ChunkSpawnerLimit extends JavaPlugin {
        public static ChunkSpawnerLimit instance;
        public static int limit;
        public static boolean cleanOnChunkLoad;
         
	@Override
	public void onEnable() {
            instance = this;
            
            FileConfiguration config = getConfig();
            if(!config.contains("limit")){
                config.addDefault("limit", 4);
            }
            if(!config.contains("cleanOnChunkLoad")){
                config.addDefault("cleanOnChunkLoad", false);
            }
            config.options().copyDefaults(true);
            saveConfig();
            
            limit = config.getInt("limit");
            cleanOnChunkLoad = config.getBoolean("cleanOnChunkLoad");
            
            Bukkit.getPluginManager().registerEvents(new ChunkLoad(), this);
            Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
            getServer().getPluginCommand("spawnerlist").setExecutor(new ListCommand());
            getServer().getPluginCommand("spawnerdelete").setExecutor(new DeleteCommand());
        }
        
}
