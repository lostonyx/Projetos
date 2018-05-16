package me.wiljafor1.saochecar;

import java.util.ArrayList;
import java.util.List;
import static me.wiljafor1.saochecar.MainConfig.loadConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
        public static Main plugin;
        public ArrayList<String> PlayerCheck = new ArrayList<String>();
        public List<String> Tela = new ArrayList();
        @Override
        public void onEnable() {  
            plugin = this;
            saveDefaultConfig();
            loadConfig();
            Tela = MainConfig.getTela();
            PluginManager pm = getServer().getPluginManager();
            getCommand("checar").setExecutor(new Comandos(this));  
            getCommand("okay").setExecutor(new Comandos(this)); 
            Bukkit.getServer().getPluginManager().registerEvents(this, this); 
        }
        
        @Override
        public void onDisable() {
        }
        public void Salvar(String plugin, String linha, String desc){
                getConfig().set(""+plugin+"."+linha, "");
        }
  @EventHandler
  public void Mover(PlayerMoveEvent event){
     if(PlayerCheck.contains(event.getPlayer().getName())){
     event.setTo(event.getFrom());
     } 
  }
  @EventHandler
  public void SaindoNoCheck(PlayerQuitEvent event){
     if(PlayerCheck.contains(event.getPlayer().getName())){
     Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "kick "+ event.getPlayer().getName() +" Negou abrimento do ant-cheat!");
     } 
  }
}
