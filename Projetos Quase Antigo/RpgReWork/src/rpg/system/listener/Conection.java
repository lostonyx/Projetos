package rpg.system.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rpg.Main;
import rpg.system.Lores;
import rpg.system.RpgSetup;
import rpg.system.Tab;
import rpg.system.gui.GuiAccountSelect;
import rpg.system.gui.GuiCreateFirst;
import rpg.system.models.Account;
import rpg.system.models.Gamer;
import rpg.system.models.GamerManager;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;

public class Conection implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.teleport(RpgSetup.LOCALAOLOGAR);
		p.getInventory().setHelmet(new ItemStack (Material.AIR));
        p.getInventory().setChestplate(new ItemStack (Material.AIR));
        p.getInventory().setLeggings(new ItemStack (Material.AIR));
        p.getInventory().setBoots(new ItemStack (Material.AIR));
		p.getInventory().clear();
		p.setGameMode(GameMode.ADVENTURE);
		p.setHealth(20.0);
		p.setLevel(0);
		p.setExp(0F);
		p.setFoodLevel(20);
		Tab ap = new Tab();
		ap.tab(p);
		Jogador w = JogadorManager.createOne(p);
		Gamer g = GamerManager.createOne(p);
		g.setOnline(true);
		g.update();
		int task = Main.GetInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.GetInstance(),
				new Runnable() {
					public void run() {
						if (w.getCurrentAccount() == null) {
							if (w.getContas().isEmpty()) {
								GuiCreateFirst.open(p);
							} else if (w.getCurrentAccount() == null) {
								GuiAccountSelect.open(p);
							}
						}
					}
				}, 5 * 20);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		Gamer g = GamerManager.getGamer(p);
		g.setOnline(false);
		g.update();
		GamerManager.remove(g);
		Jogador w = JogadorManager.getWynnPlayer(p);
		if (w.getCurrentAccount() != null) {
			Account perfil = w.getCurrentAccount();
			perfil.setInv(p.getInventory());
			perfil.setLastLocSeri(p.getLocation());
			w.update();
			JogadorManager.remove(w);
			p.getInventory().setHelmet(new ItemStack (Material.AIR));
            p.getInventory().setChestplate(new ItemStack (Material.AIR));
            p.getInventory().setLeggings(new ItemStack (Material.AIR));
            p.getInventory().setBoots(new ItemStack (Material.AIR));
			p.getInventory().clear();
			Jogador.scores.remove(p);
		}
	}
	
	/*@EventHandler
	public void fechainv(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		Jogador w = JogadorManager.getWynnPlayer(p);
		if (w.getCurrentAccount() == null) {
			if (w.getContas().isEmpty()) {
				GuiCreateFirst.open(p);
			} else if (w.getCurrentAccount() == null) {
				GuiAccountSelect.open(p);
			}
		}
	}*/

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Jogador w = JogadorManager.getWynnPlayer(player);
		if (w.getCurrentAccount() == null) {
			if (w.getContas().isEmpty()) {
				Location loc = new Location(e.getFrom().getWorld(), e.getFrom().getX(), e.getFrom().getY(),
						e.getFrom().getZ());
				player.teleport(loc);
				GuiCreateFirst.open(player);
			} else if (w.getCurrentAccount() == null) {
				Location loc = new Location(e.getFrom().getWorld(), e.getFrom().getX(), e.getFrom().getY(),
						e.getFrom().getZ());
				player.teleport(loc);
				GuiAccountSelect.open(player);
			}
		}
	}

}
