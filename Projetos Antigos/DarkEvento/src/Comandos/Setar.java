/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import Eventos.Eventos;
import darkevento.DarkEvento;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author WillianDev
 */
public class Setar implements CommandExecutor{
    DarkEvento plugin;
    public Setar(DarkEvento plugin) {
        this.plugin = plugin;
    } 
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!(sender instanceof Player)) {
        sender.sendMessage("§cVoce nao e um player.");
      return false;
    }
    Player player = (Player)sender;
    if(player.hasPermission("dkevento.admin")) {
    if (cmd.getName().equalsIgnoreCase("dksetar"))
    {
      if (args.length == 2) {
        if (args[0].equalsIgnoreCase("setspawn")) {
          if (args[1].equalsIgnoreCase("lobby")) {
            plugin.getConfig().set("evento.lobby.W", player.getWorld().getName());
            plugin.getConfig().set("evento.lobby.X", Double.valueOf(player.getLocation().getX()));
            plugin.getConfig().set("evento.lobby.Y", Double.valueOf(player.getLocation().getY()));
            plugin.getConfig().set("evento.lobby.Z", Double.valueOf(player.getLocation().getZ()));
            plugin.getConfig().set("evento.lobby.P", 89);
            plugin.getConfig().set("evento.lobby.A", 0);
            player.sendMessage("§bLobby §bsetada com sucesso!");
            plugin.saveConfig();
            return true;
          }
          if (args[1].equalsIgnoreCase("corrida")) {
            plugin.getConfig().set("evento.corrida.W", player.getWorld().getName());
            plugin.getConfig().set("evento.corrida.X", Double.valueOf(player.getLocation().getX()));
            plugin.getConfig().set("evento.corrida.Y", Double.valueOf(player.getLocation().getY()));
            plugin.getConfig().set("evento.corrida.Z", Double.valueOf(player.getLocation().getZ()));
            plugin.getConfig().set("evento.corrida.P", Double.valueOf(player.getLocation().getPitch()));
            plugin.getConfig().set("evento.corrida.A", Double.valueOf(player.getLocation().getYaw()));
            player.sendMessage("§bCorrida §bsetada com sucesso!");
            plugin.saveConfig();
            return true;
        }
          if (args[1].equalsIgnoreCase("spawn")) {
            plugin.getConfig().set("evento.spawn.W", player.getWorld().getName());
            plugin.getConfig().set("evento.spawn.X", Double.valueOf(player.getLocation().getX()));
            plugin.getConfig().set("evento.spawn.Y", Double.valueOf(player.getLocation().getY()));
            plugin.getConfig().set("evento.spawn.Z", Double.valueOf(player.getLocation().getZ()));
            plugin.getConfig().set("evento.spawn.P", Double.valueOf(player.getLocation().getPitch()));
            plugin.getConfig().set("evento.spawn.A", Double.valueOf(player.getLocation().getYaw()));
            player.sendMessage("§bSpawn §bsetada com sucesso!");
            plugin.saveConfig();
            return true;
        }
          if (args[1].equalsIgnoreCase("spleef")) {
            plugin.getConfig().set("evento.spleef.W", player.getWorld().getName());
            plugin.getConfig().set("evento.spleef.X", Double.valueOf(player.getLocation().getX()));
            plugin.getConfig().set("evento.spleef.Y", Double.valueOf(player.getLocation().getY()));
            plugin.getConfig().set("evento.spleef.Z", Double.valueOf(player.getLocation().getZ()));
            plugin.getConfig().set("evento.spleef.P", Double.valueOf(player.getLocation().getPitch()));
            plugin.getConfig().set("evento.spleef.A", Double.valueOf(player.getLocation().getYaw()));
            player.sendMessage("§bSpleef §bsetada com sucesso!");
            plugin.saveConfig();
            return true;
        }
      return false;
  }
        return false;
        
    
}

        return false;
    
}
        return false;
}
        return false;
}   
}
