package Comandos;
import static Eventos.Eventos.pgn;
import static Eventos.Eventos.pgt;
import net.wiljafor1.SaoCorrida;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author WillianDev
 */
public class Evento implements CommandExecutor{
    SaoCorrida plugin;
     
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    
    public Evento(SaoCorrida plugin) {
        this.plugin = plugin;
    }  
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        
        Player p = (Player)cs;
        if(p.hasPermission("saocorrida.user")){
        if(Bukkit.getOnlinePlayers().size() >= 3){
        if(Eventos.Eventos.PreLobby==true){
        if(Eventos.Eventos.StartCorrida == true){
        if(cmnd.getName().equalsIgnoreCase("evento")){
            if(Eventos.Eventos.pg.contains(p.getName())){
            p.sendMessage("§7§m-§b*§7§m-§e Voce já no §bEvento §7§m-§b*§7§m-");    
            }
            else{
            if((p.getInventory().getHelmet() == null) && (p.getInventory().getChestplate()== null) && (p.getInventory().getBoots()== null) && (p.getInventory().getLeggings()== null)){
         Eventos.Eventos.invsave.put(p, p.getInventory().getContents());
         Eventos.Eventos.locsave.put(p, p.getLocation());
         Eventos.Eventos.pg.add(p.getName());
         p.sendMessage("§7§m-§b*§7§m-§e Voce entrou no §bEvento §7§m-§b*§7§m-");
         String w = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getString("evento.corrida.W");
         double x = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.lobby.X");
         double y = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.lobby.Y");
         double z = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.lobby.Z");
         float pa = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.lobby.P");
         float a = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.lobby.A");
         Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
         p.teleport(loc);    
         ItemStack[] armorContents = p.getInventory().getArmorContents().clone();
         p.getPlayer().getInventory().clear();
         p.getInventory().setArmorContents(armorContents);
         p.updateInventory(); 
            }
            else{
            p.sendMessage("§7§m-§b*§7§m-§e Por Favor retire suas §barmaduras! §7§m-§b*§7§m-");    
            }
        }


        }
        }
        }
        }
        else{
            p.sendMessage("§7§m-§b*§7§m-§e nenhum evento disponivel!");
        }
        }
        return false;
    }
    
public boolean hasAvaliableSlot(Player player){
    Inventory inv = player.getInventory();
    for (ItemStack item: inv.getContents()) {
         if(item == null) {
                 return true;
         }
     }
return false;
}
        
    
}
