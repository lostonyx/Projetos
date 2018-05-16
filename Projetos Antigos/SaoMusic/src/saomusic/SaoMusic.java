package saomusic;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


public class SaoMusic extends JavaPlugin {
    public static int task;
    
    @Override
    public void onEnable() {
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
    {
     public void run()
     {
     Musica();
     }
    }, 0, 20 * 85);
    }
    @Override
    public void onDisable() { 
        
    }
//Musica
  @EventHandler
  public void Musica(){
    BukkitScheduler scheduler1 = getServer().getScheduler();
        task = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         for (Player p : Bukkit.getOnlinePlayers()){
         Song s = NBSDecoder.parse(new File(getDataFolder(), "Sao.nbs")); 
         SongPlayer sp = new RadioSongPlayer(s);
         sp.addPlayer(p.getPlayer());
         sp.setPlaying(true);
          }
        }      
        }, 1 * 20);  

  }
}
