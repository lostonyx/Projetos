/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkchatevo;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.channels.ChannelManager;
import mkremins.fanciful.FancyMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author WillianDev
 */
public class DarkChatEvo extends JavaPlugin implements Listener{
public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    ChannelManager channelManager = Legendchat.getChannelManager();
}
@EventHandler
private void onChat(ChatMessageEvent e) {
    if(e.getTags().contains("novato") && e.getSender().hasPermission("dk.novato")){
        Player player = e.getSender().getPlayer();
        TextComponent nomep = new TextComponent();
        nomep.setText(player.getDisplayName());
        nomep.setBold(true);
        nomep.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, player.getDisplayName()));
        nomep.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_ITEM, new ComponentBuilder("§6" + player.getName() + " §fRank: " + "§a")
        .create()));
        FancyMessage chatformat = new FancyMessage(e.getSender().getName())
        .tooltip("§6" + player.getName() + " §fRank: " + "§ag", "§2Money: §6"); 
        e.setTagValue("novato","&6[MyTag] " + nomep.getText() + " " + nomep.toLegacyText().intern() + " ") ;
    }
}
}

