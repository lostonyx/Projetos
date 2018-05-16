package me.wiljafor1.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import me.wiljafor1.Main;
import me.wiljafor1.items.Lores;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.Gamer;
import me.wiljafor1.models.GamerManager;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.Tab;
import me.wiljafor1.system.gui.GuiAccountSelect;
import me.wiljafor1.system.gui.GuiCreateFirst;

public class Conection implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.teleport(new Location(e.getPlayer().getWorld(), 0.500, 2.0 ,0.500));
		p.getInventory().clear();
		p.setGameMode(GameMode.ADVENTURE);
		p.setHealth(20.0);
		p.setFoodLevel(20);
		Tab ap = new Tab();
		ap.tab(p);
		WynnPlayer w = WynnPlayerManager.createOne(p);
		Gamer g = GamerManager.createOne(p);
		g.setOnline(true);
		g.update();
		int task = Main.GetInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.GetInstance(),
				new Runnable() {
					public void run() {
						WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
						if (w.getContas().isEmpty()) {
							GuiCreateFirst.open(p);
						} else if (w.getCurrentAccount() == null) {
							GuiAccountSelect.open(p);
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
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		if (w.getCurrentAccount() != null) {
			Account perfil = w.getCurrentAccount();
			perfil.setInv(p.getInventory());
			perfil.setLastLocSeri(p.getLocation());
			w.update();
			WynnPlayerManager.remove(w);
			p.getInventory().clear();
			WynnPlayer.scores.remove(p);
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(player);
		if (w.getCurrentAccount() == null) {
			if (w.getContas().isEmpty()) {
				Location loc = new Location(e.getFrom().getWorld(), e.getFrom().getX(), e.getFrom().getY(),
						e.getFrom().getZ());
				// player.teleport(loc);
				GuiCreateFirst.open(player);
			} else if (w.getCurrentAccount() == null) {
				Location loc = new Location(e.getFrom().getWorld(), e.getFrom().getX(), e.getFrom().getY(),
						e.getFrom().getZ());
				// player.teleport(loc);
				GuiAccountSelect.open(player);
			}
		}
	}

}
