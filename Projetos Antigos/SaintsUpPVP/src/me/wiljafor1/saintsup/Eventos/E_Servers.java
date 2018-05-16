package me.wiljafor1.saintsup.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.wiljafor1.saintsup.Main;
import me.wiljafor1.saintsup.APIs.Manager;
import me.wiljafor1.saintsup.APIs.SpawnerType;

public class E_Servers implements Listener {

	@EventHandler
	private void cor(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("niferfull.placacor")) {
			return;
		}
		for (int i = 0; i <= 3; i++) {
			String line = e.getLine(i);
			line = ChatColor.translateAlternateColorCodes('&', line);
			e.setLine(i, line);
		}
	}

	@EventHandler
	private void motd(ServerListPingEvent e) {
		e.setMotd("§9§lSaints§b§lUP  §eRankUp em fase §cBETA");
	}

	@EventHandler
	private void chuva(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void comandoErrado(PlayerCommandPreprocessEvent e) {
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			String cmd = e.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			if (topic == null) {
				p.sendMessage("§4[§c>>§4] §eO comando §6'" + cmd + "'§e nao foi encontrado !");
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void bigornaReparar(PlayerInteractEvent e) {
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.hasBlock())
				&& (e.getClickedBlock().getType() == Material.ANVIL) && (e.getClickedBlock().getData() > 0)) {
			e.getClickedBlock().setData((byte) (e.getClickedBlock().getData() % 4));
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void comandoproibido(PlayerCommandPreprocessEvent e) {
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			if ((e.getMessage().equals("/?")) || (e.getMessage().equals("/help")) || (e.getMessage().equals("/pl"))
					|| (e.getMessage().equals("/spigot"))) {
				p.kickPlayer(
						"§9§lSaints§b§lUP \n§cN§o tente pegar nossos plusin! \n§cErro: §fPara feio... \n§cSite: §7saints.cmhosts.com.br");
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMobSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.SPAWNER)
			e.setCancelled(true);
	}

	@EventHandler
	public void blocos(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Block bloco = p.getLocation().getBlock().getRelative(0, -1, 0);
		Block bloco2 = p.getLocation().getBlock().getRelative(0, 0, 0);
		if (bloco.getType() == Material.SLIME_BLOCK) {
			p.playSound(p.getLocation(), Sound.SLIME_ATTACK, 4.0F, 4.0F);
			p.setVelocity(p.getEyeLocation().getDirection().multiply(3).add(new Vector(0, 1, 0)));
		}
		if (bloco2.getType() == Material.PORTAL) {
			p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		}
	}

	@EventHandler
	private void colocarspawner(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItemInHand();
		if (item.getType() == Material.MOB_SPAWNER) {
			if (item.getItemMeta().getDisplayName().contains("Zombie")) {
				EntityType type = SpawnerType.ZOMBIE.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Skeleton")) {
				EntityType type = SpawnerType.SKELETON.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Spider")) {
				EntityType type = SpawnerType.SPIDER.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Creeper")) {
				EntityType type = SpawnerType.CREEPER.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Enderman")) {
				EntityType type = SpawnerType.ENDERMAN.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("PigZombie")) {
				EntityType type = SpawnerType.PIGMAN.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("blaze")) {
				EntityType type = SpawnerType.BLAZE.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Iron")) {
				EntityType type = SpawnerType.IRONGOLEM.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Guardian")) {
				EntityType type = SpawnerType.GUARDIAN.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			if (item.getItemMeta().getDisplayName().contains("Wither")) {
				EntityType type = SpawnerType.WITHER.getType();
				CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
				spawner.setSpawnedType(type);
			}
			p.sendMessage("§6[§] §eSpawner colocado com sucesso!");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void quebrar(BlockBreakEvent e) {
		if (!Main.canBreakAt(e.getBlock().getLocation(), e.getPlayer())) {
			e.setCancelled(true);
			return;
		}
		Player p = e.getPlayer();
		if (Manager.getFreeSlots(p) < 1) {
			e.setCancelled(true);
			p.sendMessage("§4[§c§§4] §eEsvazie seu inventario para continuar.");
			return;
		}
		ItemStack item = p.getItemInHand();
		Block bloco = e.getBlock();
		boolean silk = item.containsEnchantment(Enchantment.SILK_TOUCH);
		if (p.getGameMode() == GameMode.CREATIVE) {
			bloco.setType(Material.AIR);
			return;
		}
		if (silk) {
			if (bloco.getType() == Material.MOB_SPAWNER) {
				CreatureSpawner spawner = (CreatureSpawner) bloco.getState();
				e.setExpToDrop(0);
				EntityType type = spawner.getSpawnedType();
				if (type == EntityType.ZOMBIE) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aZombie Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.SKELETON) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aSkeleton Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.SPIDER) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aSpider Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.CREEPER) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aCreeper Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.ENDERMAN) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aEnderman Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.PIG_ZOMBIE) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aPigZombie Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.BLAZE) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aBlaze Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.IRON_GOLEM) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aIron Golem Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.GUARDIAN) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aGuardian Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				if (type == EntityType.WITHER) {
					ItemStack drop = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta drop2 = drop.getItemMeta();
					drop2.setDisplayName("§aWither Spawner");
					drop.setItemMeta(drop2);
					p.getWorld().dropItemNaturally(bloco.getLocation(), drop);
				}
				p.sendMessage("§6[§] §eSpawner retirado.");
				bloco.setType(Material.AIR);
				return;
			}
		}
	}
}