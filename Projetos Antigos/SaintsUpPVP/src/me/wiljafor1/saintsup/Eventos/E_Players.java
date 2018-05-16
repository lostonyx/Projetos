package me.wiljafor1.saintsup.Eventos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.wiljafor1.saintsup.Main;
import me.wiljafor1.saintsup.APIs.Manager;
import me.wiljafor1.saintsup.Livros.Encantamentos;
import me.wiljafor1.saintsup.MySQL.MSManager;

public class E_Players implements Listener {

	@EventHandler
	private void entrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		int i = 0;
		while (i < 50) {
			p.sendMessage(" ");
			i++;
		}
		p.sendMessage(" §e* Ol§ §6" + p.getName() + "§e, seja bem-vindo ao §9Saints§bUP!");
		try {
			MSManager.criarPefil(e.getPlayer());
			//.criarDados(e.getPlayer().getUniqueId());
		} catch (SQLException e1) {
			Main.plugin.getLogger().severe("Nao foi possivel salvar os dados do jogador: " + e.getPlayer().getName());
		}
		e.setJoinMessage(null);
		p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		p.setGameMode(GameMode.SURVIVAL);
		p.setPlayerListName("§7" + p.getName());
		p.setDisplayName("§f" + p.getName());
	}

	@EventHandler
	private void sair(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	private void matar(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		Player p = e.getEntity();
		if (p.getKiller() instanceof Player) {
			Player k = p.getKiller();
			if (k == p) {
				return;
			}
			p.sendMessage("§cVoce foi morto por " + k.getName() + "!");
			k.sendMessage("§aVoce matou " + p.getName() + "!");
			MSManager.setVitimas(k, MSManager.getVitimas(k) + 1);
			MSManager.setMortes(p, MSManager.getMortes(p) + 1);
			Random r = new Random();
			int o = r.nextInt(100);
			if (o < 16) {
				ItemStack cabeca = new ItemStack(Material.SKULL_ITEM);
				cabeca.setDurability((short) 3);
				cabeca.setAmount(1);
				SkullMeta cabeca2 = (SkullMeta) cabeca.getItemMeta();
				cabeca2.setDisplayName("§c" + p.getName());
				List<String> lore = new ArrayList();
				int alma = r.nextInt(4);
				if (alma == 0) {
					lore.add("§71 almas §8§l§");
				}
				if (alma > 0) {
					lore.add("§7" + alma + " almas §8§l§");
				}
				cabeca2.setLore(lore);
				cabeca2.setOwner(p.getName());
				cabeca.setItemMeta(cabeca2);
				e.getDrops().add(cabeca);
				k.sendMessage("§aVoc§ encontrou uma cabe§a com almas!");
			}
		} else {
			p.sendMessage("§cVoc§ morreu!");
			MSManager.setMortes(p, MSManager.getMortes(p) + 1);
		}
	}

	public static List<String> Sair = new ArrayList();

	@EventHandler
	private void DanoCombate(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
			final Player p = (Player) e.getEntity();
			final Player hitter = (Player) e.getDamager();
			if (e.isCancelled()) {
				return;
			}
			if ((!Main.areaPvP(hitter)) || (!Main.areaPvP(p))) {
				return;
			}
			if ((!Sair.contains(p.getName())) && (!Sair.contains(hitter.getName()))) {
				if ((hitter.getGameMode() == GameMode.CREATIVE) || (hitter.getGameMode() == GameMode.SPECTATOR)) {
					return;
				}
				Sair.add(p.getName());
				Sair.add(hitter.getName());
				p.sendMessage("§c[§4!§c] Voc§ est§ em combate, se deslogar ser§ morto!");
				hitter.sendMessage("§c[§4!§c] Voc§ est§ em combate, se deslogar ser§ morto!");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						E_Players.Sair.remove(p.getName());
						E_Players.Sair.remove(hitter.getName());
						p.sendMessage("§a[§2!§a] Voc§ n§o est§ mais em combate, pode deslogar!");
						hitter.sendMessage("§a[§2!§a] Voc§ n§o est§ mais em combate, pode deslogar!");
					}
				}, 240L);
			}
		}
	}

	@EventHandler
	private void SairCombate(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (Sair.contains(p.getName())) {
			p.setHealth(0.0D);
			p.teleport(new Location(Bukkit.getWorlds().get(0), 0.0D, 0.0D, 0.0D));
			p.teleport(e.getPlayer().getWorld().getSpawnLocation());
			Sair.remove(p.getName());
			for (Player p2 : Bukkit.getOnlinePlayers()) {
				Manager.sendActionBar(p2, "§c" + p.getName() + " deslogou em combate e morreu!");
			}
		}
	}


	@EventHandler
	private void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.SLIME_BALL) {
			if (p.getItemInHand().hasItemMeta()) {
				if (p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (p.getItemInHand().getItemMeta().getDisplayName().equals("§4Resetar KDR")) {
						if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
							Manager.remove(p.getItemInHand(), p.getInventory());
							MSManager.setVitimas(p, 0);
							MSManager.setMortes(p, 0);
							Main.sc.getClanManager().getAnyClanPlayer(p.getName()).setCivilianKills(0);
							Main.sc.getClanManager().getAnyClanPlayer(p.getName()).setNeutralKills(0);
							Main.sc.getClanManager().getAnyClanPlayer(p.getName()).setRivalKills(0);
							Main.sc.getClanManager().getAnyClanPlayer(p.getName()).setDeaths(0);
							p.sendMessage("§4[ResetKDR] §7Status resetados com sucesso!");
						}
					}
				}
			}
		}
		if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
			if (p.getItemInHand().hasItemMeta()) {
				if (p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (p.getItemInHand().getItemMeta().getDisplayName().equals("§eLivro Especial")) {
						if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if (e.getClickedBlock().getType() == Material.ANVIL) {
								e.setCancelled(true);
								p.sendMessage("§aChecando...");
								Encantamentos.abrirGeneretor(p, p.getItemInHand());
								p.setItemInHand(new ItemStack(Material.AIR));
							}
						}
					}
				}
			}
		}
		if (p.getItemInHand().getType() == Material.EXP_BOTTLE) {
			if (p.getItemInHand().hasItemMeta()) {
				if (p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (p.getItemInHand().getItemMeta().getDisplayName().equals("§eFrasco de EXP")) {
						if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
							e.setCancelled(true);
							String nome = p.getItemInHand().getItemMeta().getLore().toString();
							String split[] = nome.split(" ");
							String xp = split[0].replace("§7", "").replace("[", "").replace("]", "");
							p.sendMessage("§aAdicionados " + xp + " EXP Level em sua conta!");
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
							p.giveExpLevels(Integer.valueOf(xp));
							p.setItemInHand(new ItemStack(Material.AIR));
							p.updateInventory();
						}
					}
				}
			}
		}
	}
}