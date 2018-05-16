package Comandos;
import net.wiljafor1.SaoCorrida;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor{
    SaoCorrida plugin;
    public Reload(SaoCorrida plugin) {
        this.plugin = plugin;
    } 
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!(sender instanceof Player)) {
        sender.sendMessage("§cVoce nao e um player.");
      return false;
    }
    Player player = (Player)sender;
    if(player.hasPermission("saocorrida.admin")) {
    if (cmd.getName().equalsIgnoreCase("saocorrida"))
    {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
           if (plugin.getConfig().contains("FlashAtual")){
                plugin.flash = plugin.getConfig().getString("FlashAtual");
           } 
            player.sendMessage("§bReload com sucesso!");
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
