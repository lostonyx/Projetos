package com.dayz.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.InventoryView;
import org.golde.bukkit.corpsereborn.CorpseAPI.CorpseAPI;
import org.golde.bukkit.corpsereborn.CorpseAPI.events.CorpseClickEvent;
import org.golde.bukkit.corpsereborn.CorpseAPI.events.CorpseRemoveEvent;
import org.golde.bukkit.corpsereborn.cmds.SpawnCorpse;
import org.golde.bukkit.corpsereborn.nms.Corpses.CorpseData;
import org.golde.bukkit.corpsereborn.nms.TypeOfClick;

import com.criptonnetwork.util.Tag;
import com.dayz.Main;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.dayz.utils.PlayerList;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;

public class PlayerEvent implements Listener {
	public static ArrayList<String> quebrou = new ArrayList<String>();
	private Map<Player, CorpseData> mapCorpse = new HashMap<Player, CorpseData>();

	@EventHandler
	public void Texture(PlayerResourcePackStatusEvent e) {
		Player p = e.getPlayer();
		if (e.getStatus() == Status.ACCEPTED) {
			p.sendTitle(ChatColor.RED + "Dayz", ChatColor.GOLD + "Carregando a textura...");
		}
		if (e.getStatus() == Status.DECLINED) {
			Bukkit.getScheduler().runTask(Main.getDayz(), new Runnable() {
				@Override
				public void run() {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + e.getPlayer().getName() + ChatColor.RED
							+ "Desculpe!, Para jogar e preciso ter a textura para ter melhor gameplay. \nCriptoNetwork.");
					Bukkit.getPlayer(p.getName()).kickPlayer(ChatColor.RED
							+ "Desculpe!, Para jogar e preciso ter a textura para ter melhor gameplay. \nCriptoNetwork.");
				}
			});
		}
		if (e.getStatus() == Status.FAILED_DOWNLOAD) {
			Bukkit.getScheduler().runTask(Main.getDayz(), new Runnable() {
				@Override
				public void run() {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + e.getPlayer().getName() + ChatColor.RED
							+ "Desculpe!, Para jogar e preciso ter a textura para ter melhor gameplay. \nCriptoNetwork.");
					Bukkit.getPlayer(p.getName()).kickPlayer(ChatColor.RED
							+ "Desculpe!, Para jogar e preciso ter a textura para ter melhor gameplay. \nCriptoNetwork.");
				}
			});
		}
		if (e.getStatus() == Status.SUCCESSFULLY_LOADED) {
			p.sendTitle(ChatColor.RED + "Obrigado!", ChatColor.GOLD + "Nossa equipe por compreender, bom jogo!");
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Jogador j = JogadorManager.getJogador(p);

			if (e.getCause() == DamageCause.FALL) {
				// p.sendMessage("Distance: "+p.getFallDistance());
				double vida = j.getCurrentAccount().getVida() - 2.5 * p.getFallDistance();
				if (vida < 0) {
					p.setHealth(0.5);
					e.setDamage(0.5);
					j.getCurrentAccount().setVida(100.0);
					j.update();
				} else {
					// p.sendMessage("Sua vida: "+ j.getCurrentAccount().getVida());
					p.setHealth(calculavida(vida));
					j.getCurrentAccount().setVida(vida);
					e.setDamage(0.0);
					j.update();
				}
			} else {
				double vida = j.getCurrentAccount().getVida() - e.getDamage();
				if (vida < 0) {
					p.setHealth(0.5);
					e.setDamage(0.5);
					j.getCurrentAccount().setVida(100.0);
					j.update();
				} else {
					// p.sendMessage("Sua vida: "+ j.getCurrentAccount().getVida());
					p.setHealth(calculavida(vida));
					j.getCurrentAccount().setVida(vida);
					e.setDamage(0.0);
					j.update();
				}
			}
		}
	}

	public void Respawn(PlayerRespawnEvent e) {
		quebrou.remove(e.getPlayer().getName());
		// e.setRespawnLocation();
	}

	@EventHandler
	public void EventoQuebraPerna(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Jogador j = JogadorManager.getJogador(p);

			if (e.getCause() == DamageCause.FALL) {
				// p.sendMessage("Distance: "+p.getFallDistance());
				double vida = j.getCurrentAccount().getVida() - 2.5 * p.getFallDistance();
				if (vida < 0) {
					quebrou.remove(p.getName());
				} else {
					if (p.getFallDistance() > 10) {
						p.sendMessage("quebrou quebrou");
						int stop;
						quebrou.add(p.getName());
						stop = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getDayz(), new Runnable() {
							public void run() {
								if ((j.getCurrentAccount().getVida() - 10.0) < 0) {
									quebrou.remove(p.getName());
									p.damage(1000);
									j.getCurrentAccount().setVida(100.0);
									j.update();
								} else {
									PacketPlayOutNamedSoundEffect sound = new PacketPlayOutNamedSoundEffect("teste.lul",
											p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(),
											300f, 1f);
									p.damage(0.1);
									p.setHealth(calculavida(j.getCurrentAccount().getVida() - 10.0));
									j.getCurrentAccount().setVida(j.getCurrentAccount().getVida() - 10.0);
									j.update();
								}
							}
						}, 25 * 20, 25 * 20);
						int task = Main.getDayz().getServer().getScheduler().scheduleSyncDelayedTask(Main.getDayz(),
								new Runnable() {
									public void run() {
										quebrou.remove(p.getName());
										Bukkit.getScheduler().cancelTask(stop);
									}
								}, 300 * 20);
					}
				}
			}
		}
	}

	public double calculavida(double vida) {
		if ((vida < 100) && (vida > 75)) {
			return 20.0;
		} else if ((vida < 74) && (vida > 50)) {
			return 12.0;
		} else if ((vida < 49) && (vida > 25)) {
			return 10.0;
		} else if ((vida < 24) && (vida > 1)) {
			return 5.0;
		}
		return 1.0;
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e) {
		
		List<CorpseData> list = CorpseAPI.getCorpseInRadius(e.getPlayer().getLocation(), 5);
		if(list.size() == 0) {return;}
		CorpseData cd = list.get(0);
		mapCorpse.put(e.getPlayer(), cd);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		Player p = e.getEntity();
		/*CorpseData data = CorpseAPI.spawnCorpse(p, p.getLocation(), p.getInventory().getContents());
		int task = Main.getDayz().getServer().getScheduler().scheduleSyncDelayedTask(Main.getDayz(),
		new Runnable() {
			public void run() {
				CorpseAPI.removeCorpse(data);
			}
		}, 20 * 20);*/
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void openLoot(PlayerInteractEvent e) {
		if(e.getAction() == Action.PHYSICAL) {return;}
		try {
			Player player = e.getPlayer();
			CorpseData cd = mapCorpse.getOrDefault(player, null);
			if(cd == null) {return;}
			InventoryView view = player.openInventory(cd.getLootInventory());
			cd.setInventoryView(view);
			cd = null;
			Bukkit.getServer().getPluginManager().callEvent(new CorpseClickEvent(cd, player, TypeOfClick.BOTH));
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@EventHandler
	public void removeCorpse(CorpseRemoveEvent e) {
		for(CorpseData cd1:e.getCorpses()) {
			for(CorpseData cd2: mapCorpse.values()) {
				if(cd1.getEntityId() == cd2.getEntityId()){
					mapCorpse.values().remove(cd1);
				}
			}
		}
		
	}
	

}
