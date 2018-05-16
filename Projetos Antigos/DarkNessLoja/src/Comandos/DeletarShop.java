package Comandos;

import darknessloja.DarkNessLoja;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
/**
 *
 * @author WillianDev
 */
public class DeletarShop implements CommandExecutor {
    DarkNessLoja plugin;
    public DeletarShop(DarkNessLoja plugin) {
        this.plugin = plugin;
    }   
    
   @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!(sender instanceof Player)) {
        sender.sendMessage("§0§l[§6§lLoja§0§l] " +"§cVoce nao e um player.");
      return false;
    }
    Player player = (Player)sender;
    if(player.hasPermission("sao.shopdel")) {
    if (cmd.getName().equalsIgnoreCase("delShop"))
    {
            plugin.getDataFolder().mkdir();        
            File file = new File(plugin.getDataFolder(), "config.yml");        
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            plugin.getConfig().set(player.getName(), null);
            player.sendMessage("§0§l[§6Shop§0§l]" + "§6Shop §edeletada com §6Sucesso!");
            plugin.getConfig().set(player.getName()+"T", null);          
            plugin.saveConfig();
            return true;  
}
}
    return false;
}   
    
}
