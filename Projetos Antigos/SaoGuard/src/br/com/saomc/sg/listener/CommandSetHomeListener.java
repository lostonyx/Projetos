package br.com.saomc.sg.listener;

import br.com.saomc.sg.SaoGuard;
import static br.com.saomc.sg.SaoGuard.plugin;
import br.com.saomc.sg.SgConfig;
import br.com.saomc.sg.jdbc.dao.BlockDAO;
import br.com.saomc.sg.jdbc.dao.FlagDAO;
import br.com.saomc.sg.jdbc.dao.PlayerDAO;
import br.com.saomc.sg.jdbc.dao.RegionDAO;
import br.com.saomc.sg.jdbc.dao.model.Region;
import br.com.saomc.sg.util.CItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import br.com.saomc.sg.util.Messages;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

public class CommandSetHomeListener implements Listener {
        public SgConfig sgConfig;
        public CommandSetHomeListener(SgConfig hgConfig) {
		super();
		this.sgConfig = hgConfig;
	}

	WorldGuardPlugin worldGuard;
        SaoGuardCommandExecutor sub;
	public final String PrefixYellow = ChatColor.YELLOW.toString();
	public final String PrefixBlue = ChatColor.DARK_AQUA.toString();
	public final String PrefixRed = ChatColor.DARK_RED.toString();

	public final String PrefixYellowConsole = ChatColor.GOLD + "[SaoGuard] " + ChatColor.YELLOW;
	public final String PrefixBlueConsole = ChatColor.BLUE + "[SaoGuard] " + ChatColor.DARK_AQUA;
	public final String PrefixRedConsole = ChatColor.RED + "[SaoGuard] " + ChatColor.DARK_RED;


	@EventHandler
	public void OnCommand(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		RegionContainer container = plugin.getWorldGuard().getRegionContainer();
                RegionManager rm = container.get(player.getWorld());

		ApplicableRegionSet set = rm.getApplicableRegions(player.getLocation());
		if (set.size() == 0) {
			return;
		}

		set.toString().toLowerCase();
		String id = set.iterator().next().getId();

		if (!player.getName().toLowerCase().equalsIgnoreCase(rm.getRegion(id).getOwners().toUserFriendlyString().toLowerCase())
				&& !rm.getRegion(id).getMembers().contains(player.getName().toLowerCase())) {

			String s = e.getMessage().toLowerCase();
			if (s.startsWith("/sethome") || s.startsWith("/home set") || s.startsWith("/clan home set")) {
				e.getPlayer().sendMessage(PrefixRed + Messages.getString("sg.cannot_use_command"));
				e.setCancelled(true);
			}
                        else{
                            e.setCancelled(false);
                            return;
                        }

		}

	}
        @EventHandler(priority = EventPriority.HIGHEST)
	public void BlockE(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        RegionContainer container = plugin.getWorldGuard().getRegionContainer();
        RegionManager rm = container.get(p.getWorld());
        ApplicableRegionSet set = rm.getApplicableRegions(p.getLocation());
        for(ProtectedRegion region: set){
            try{
            PlayerDAO playerDAO = new PlayerDAO(sgConfig);
            RegionDAO regionDAO = new RegionDAO(sgConfig);
            BlockDAO blockDAO = new BlockDAO(sgConfig);
            blockDAO.findByNome(p.getName().toLowerCase());
            if(blockDAO.findByNome(p.getName().toLowerCase()) == null){
               //p.sendMessage("null"); 
            }
            else{
                if(blockDAO.findByNome(p.getName().toLowerCase()).equalsIgnoreCase(region.getId())){
                    p.setVelocity(p.getLocation().getDirection().normalize().setY(0).multiply(-1));    
                }
                else{
                   //p.sendMessage("nope"); 
                }
            }
                
            }catch (SQLException ee) {
			ee.printStackTrace();
            }
        //p.sendMessage("Welcome to" + region.getId());
        //p.setVelocity(p.getLocation().getDirection().normalize().setY(0).multiply(-1));
        }  
        } 
        @EventHandler(priority = EventPriority.HIGHEST)
	public void Menu(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		RegionContainer container = plugin.getWorldGuard().getRegionContainer();
                RegionManager rm = container.get(player.getWorld());

		ApplicableRegionSet set = rm.getApplicableRegions(player.getLocation());
		if (set.size() == 0) {
			return;
		}
                if(player.getWorld().getName().toString().equalsIgnoreCase(plugin.getConfig().getString("worldpossivel"))){ 
                String[] args = e.getMessage().split(" ");
		set.toString().toLowerCase();
		String id = set.iterator().next().getId();
                Double tamanhoint = rm.getRegion(id).getMaximumPoint().getX() - rm.getRegion(id).getMinimumPoint().getX();
                if(plugin.getConfig().getBoolean("menugui")==true){
                    if (!player.getName().toLowerCase().equalsIgnoreCase(rm.getRegion(id).getOwners().toUserFriendlyString().toLowerCase().replace("name:", ""))) {
			String s = e.getMessage().toLowerCase();
			if (args[0].contains("/terreno")) {
                            
                                String[] Vip1l = new String[2];
                                Vip1l[0] = "§7§LVIP DIARIO";
                                Vip1l[1] = "§6Preço: §aR$ 2,90";
                                CItem vip1 = new CItem(Material.IRON_INGOT).setName("§7§lDiario").setLore(Vip1l);
                                CItem dono = new CItem(Material.STAINED_GLASS_PANE).setDurability(5).setName("§2Dono: "+rm.getRegion(id).getOwners().toUserFriendlyString().replace("name:", ""));
                                CItem tamanho = new CItem(Material.STAINED_GLASS_PANE).setDurability(5).setName("§2Tamanho: "+tamanhoint.intValue()+"x"+tamanhoint.intValue());
                                Inventory menuinfo = Bukkit.createInventory(null, 9, "Terreno: "+rm.getRegion(id).getId().toString());
                                menuinfo.setItem(0, dono.build());
                                menuinfo.setItem(1, tamanho.build());
                                if(args[1].contains("info")){
                                 Bukkit.getPlayer(player.getName()).openInventory(menuinfo);    
                                }
                                else if(args[1].contains("info")){
                                    
                                }
				e.setCancelled(true);
			}
                    }
                    else{
                        if (args[0].contains("/terreno")) {
                                if(args[1].contains("info")){
                                e.setCancelled(false);    
                                }   
                                if(args[1].contains("del")){
                                CItem Sim = new CItem(Material.STAINED_GLASS_PANE).setDurability(5).setName("§2Sim");
                                CItem Nao = new CItem(Material.STAINED_GLASS_PANE).setDurability(14).setName("§4Nao");
                                Inventory menudel = Bukkit.createInventory(null, 9, "Deletar Terreno?");
                                menudel.setItem(2, Sim.build());
                                menudel.setItem(6, Nao.build());
                                Bukkit.getPlayer(player.getName()).openInventory(menudel);
                                e.setCancelled(true);    
                                }  
                        }
                    
                    }
                }
                else{
                    return;
                }
                
            }
            else{
                player.sendMessage(PrefixRed + Messages.getString("sg.not_world"));    
            }
	}
       
   

}
