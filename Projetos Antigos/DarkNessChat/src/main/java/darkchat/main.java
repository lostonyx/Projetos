/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkchat;
import mkremins.fanciful.FancyMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
 
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author WillianDev
 */
public class main extends JavaPlugin implements Listener{
   
        @Override
        public void onEnable() {
                Bukkit.getPluginManager().registerEvents(this, this);
        }
       
        @EventHandler
        public void onJoin(PlayerJoinEvent event){
                final Player player = event.getPlayer();
                TextComponent tc = new TextComponent();
                tc.setText("Novato");
                tc.setColor(ChatColor.AQUA);
                tc.setBold(true);
                tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND,"/say luckgay"));
                tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_ITEM, new ComponentBuilder("Luck é gayzam").create()));
                player.spigot().sendMessage(tc);
        }
        
        @EventHandler
        public void OnChat(AsyncPlayerChatEvent event){
          Player player = event.getPlayer();
          String name = player.getDisplayName();
          String message = event.getMessage();
          FancyMessage chatformat = new FancyMessage(name)
          .tooltip(
          "§6" + player.getName() + " §fis rank: " + "§aLOL").then(" : " + message);
          event.setCancelled(true);
          if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player p : Bukkit.getOnlinePlayers()){
            chatformat.send(p);
          }
        }
}

 
