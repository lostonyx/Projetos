/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import darknessloja.DarkNessLoja;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author WillianDev
 */
public class SetarLoja implements CommandExecutor {
    DarkNessLoja plugin;
    public SetarLoja(DarkNessLoja plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!(sender instanceof Player)) {
        sender.sendMessage("§0§l[§6§lLoja§0§l] " +"§cVoce nao e um player.");
      return false;
    }
    Player player = (Player)sender;
    if(player.hasPermission("sao.shopset")) {
    if (cmd.getName().equalsIgnoreCase("ShopSet"))
    {
            plugin.getDataFolder().mkdir();        
            File file = new File(plugin.getDataFolder(), "config.yml");        
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            plugin.getConfig().set(player.getName() + ".loja.W", player.getWorld().getName());
            plugin.getConfig().set(player.getName() + ".loja.X", Double.valueOf(player.getLocation().getX()));
            plugin.getConfig().set(player.getName() + ".loja.Y", Double.valueOf(player.getLocation().getY()));
            plugin.getConfig().set(player.getName() + ".loja.Z", Double.valueOf(player.getLocation().getZ()));
            plugin.getConfig().set(player.getName() + ".loja.P", Float.valueOf(player.getLocation().getPitch()));
            plugin.getConfig().set(player.getName() + ".loja.A", Float.valueOf(player.getLocation().getYaw()));
            player.sendMessage("§0§l[§6Shop§0§l]" + "§6Shop §esetada com §6Sucesso!");
            plugin.getConfig().set(player.getName()+"T", "1");          
            plugin.saveConfig();
            return true;  
}
}
    return false;
}    
}
