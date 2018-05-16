/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.saomc.wiljafor1.saohelp;

import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import static so.saomc.wiljafor1.saohelp.MainConfig.config;

/**
 *
 * @author WillianDev
 */
public class Eventos  implements Listener{
    Main plugin;
    Server sv = Bukkit.getServer();
    public Eventos(Main plugin) {
        this.plugin = plugin;
    }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
        String Comandos = args[0].replace("/", "");
        if(args[0].contains("/ajuda")){
        e.setCancelled(true);
        TextComponent comandos = new TextComponent( "Comandos" );
        comandos.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/vComandos") );
        comandos.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para ver os comandos").create() ) );
        TextComponent regras = new TextComponent( "Regras" );
        regras.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/vRegras") );
        regras.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para ver as regras").create() ) );
        TextComponent social = new TextComponent( "Social" );
        social.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/vSocial") );
        social.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para ver o social").create() ) );
        TextComponent comojogar = new TextComponent( "Como Jogar" );
        comojogar.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/vComoJogar") );
        comojogar.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para ver Como Jogar").create() ) );
        TextComponent guias = new TextComponent( "Guias" );
        guias.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/vGuias") );
        guias.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para ver as guias").create() ) );
        //p.spigot().sendMessage(Comandos);
        p.sendMessage("- - - - - - - - */* - - - - - - - -");
        p.sendMessage("Clique em um dos itens abaixo:");
        p.spigot().sendMessage(comandos);
        p.spigot().sendMessage(regras);
        p.spigot().sendMessage(social);
        p.spigot().sendMessage(comojogar);
        p.spigot().sendMessage(guias);
        p.sendMessage("- - - - - - - - */* - - - - - - - -");
        }
        if(args[0].contains("/vComandos")){
        e.setCancelled(true);
        TextComponent comandos = new TextComponent( "Comandos" );
        comandos.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/vComandos") );
        comandos.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para ver os comandos").create() ) );
        }
        if(args[0].contains("/vRegras")){
        e.setCancelled(true);
        p.sendMessage("regras");
        }
        if(args[0].contains("/vGuias")){
        e.setCancelled(true);
        p.sendMessage("- - - - - - - - */* - - - - - - - -");
        p.sendMessage("Clique em um dos itens abaixo:");
        TextComponent g1 = new TextComponent( "Comandos" );
        List<String> b1 = plugin.getConfig().getStringList("gb1");
        TextComponent g2 = new TextComponent( "Comandos" );
        TextComponent g3 = new TextComponent( "Comandos" );
        TextComponent g4 = new TextComponent( "Comandos" );
        TextComponent g5 = new TextComponent( "Comandos" );
        TextComponent g6 = new TextComponent( "Comandos" );
        TextComponent g7 = new TextComponent( "Comandos" );
        TextComponent g8 = new TextComponent( "Comandos" );
        TextComponent g9 = new TextComponent( "Comandos" );
        TextComponent g10 = new TextComponent( "Comandos" );
        g1.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("" + b1).create() ) );
        p.spigot().sendMessage(g1);
        p.sendMessage("- - - - - - - - */* - - - - - - - -");
        }
        return;    
  }
    
}
