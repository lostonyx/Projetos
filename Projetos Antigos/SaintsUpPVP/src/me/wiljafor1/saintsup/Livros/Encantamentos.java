package me.wiljafor1.saintsup.Livros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wiljafor1.saintsup.Main;
import me.wiljafor1.saintsup.APIs.Manager;

public class Encantamentos implements Listener {

	private static ArrayList<Player> congelado = new ArrayList();

	@EventHandler
	private void hitar(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		final Player dano = (Player) e.getEntity();
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasLore()) {
				if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
					return;
				}
				if (p.getItemInHand().getItemMeta().getLore().contains("§7Hulk I")) {
					Random r = new Random();
					int o = r.nextInt(100);
					if (o < 21) {
						p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
						p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2, 30));
					}
				}
				if (p.getItemInHand().getItemMeta().getLore().contains("§7Thor I")) {
					Random r = new Random();
					int o = r.nextInt(100);
					if (o < 19) {
						p.getWorld().strikeLightning(dano.getLocation());
					}
				}
				if (p.getItemInHand().getItemMeta().getLore().contains("§7Wither I")) {
					Random r = new Random();
					int o = r.nextInt(100);
					if (o < 16) {
						p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
						dano.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2, 50));
					}
				}
				if (p.getItemInHand().getItemMeta().getLore().contains("§7Destroyer I")) {
					Random r = new Random();
					int o = r.nextInt(100);
					if (o < 16) {
						p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
						dano.damage(e.getDamage() * 2, p);
					}
				}
				if (p.getItemInHand().getItemMeta().getLore().contains("§7Ice Aspect I")) {
					Random r = new Random();
					int o = r.nextInt(100);
					if (o < 16) {
						p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
						congelado.add(dano);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
							public void run() {
								if (congelado.contains(dano)) {
									congelado.remove(dano);
								}
							}
						}, 4 * 20L);
					}
				}
			}
		}
	}

	@EventHandler
	private void move(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (congelado.contains(p)) {
			e.setTo(e.getFrom());
		}
	}

	int Radius = 3;
	boolean LargerRadius = true;
	boolean DropItems = false;

	private boolean isUsable(String iMat, String bMat) {
		if (((iMat.endsWith("PICKAXE"))
				&& ((bMat.contains("ORE")) || ((!bMat.contains("STAIRS")) && (bMat.contains("STONE")))
						|| (bMat.equals("STAINED_CLAY")) || (bMat.equals("NETHERRACK"))))
				|| ((iMat.endsWith("SPADE")) && ((bMat.contains("SAND")) || (bMat.equals("DIRT"))
						|| (bMat.equals("SNOW_BLOCK")) || (bMat.equals("SNOW")) || (bMat.equals("MYCEL"))
						|| (bMat.equals("CLAY")) || (bMat.equals("GRAVEL")) || (bMat.equals("GRASS"))))
				|| ((iMat.endsWith("AXE")) && (bMat.contains("LOG"))) || (bMat.contains("PLANKS"))
				|| ((iMat.endsWith("HOE"))
						&& ((bMat.equals("CROPS")) || (bMat.equals("POTATO")) || (bMat.equals("CARROT"))))) {
			return true;
		}
		return false;
	}

	@EventHandler
	private void andar(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if ((p.getInventory().getItemInHand() != null) && (p.getInventory().getItemInHand().hasItemMeta())
				&& (p.getInventory().getItemInHand().getItemMeta().hasLore())
				&& (p.getInventory().getItemInHand().getItemMeta().getLore().contains("§7Auto-Reparo I"))) {
			if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
				return;
			}
			int newDur = p.getInventory().getItemInHand().getDurability() - 2;
			if (newDur > 0) {
				p.getInventory().getItemInHand().setDurability((short) newDur);
				p.updateInventory();
			} else {
				p.getInventory().getItemInHand().setDurability((short) 0);
				p.updateInventory();
			}
		}
		if ((p.getInventory().getHelmet() != null) && (p.getInventory().getHelmet().hasItemMeta())
				&& (p.getInventory().getHelmet().getItemMeta().hasLore())
				&& (p.getInventory().getHelmet().getItemMeta().getLore().contains("§7Auto-Reparo I"))) {
			if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
				return;
			}
			int newDur = p.getInventory().getHelmet().getDurability() - 2;
			if (newDur > 0) {
				p.getInventory().getHelmet().setDurability((short) newDur);
			} else {
				p.getInventory().getHelmet().setDurability((short) 0);
			}
		}
		if ((p.getInventory().getChestplate() != null) && (p.getInventory().getChestplate().hasItemMeta())
				&& (p.getInventory().getChestplate().getItemMeta().hasLore())
				&& (p.getInventory().getChestplate().getItemMeta().getLore().contains("§7Auto-Reparo I"))) {
			if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
				return;
			}
			int newDur = p.getInventory().getChestplate().getDurability() - 2;
			if (newDur > 0) {
				p.getInventory().getChestplate().setDurability((short) newDur);
			} else {
				p.getInventory().getChestplate().setDurability((short) 0);
			}
		}
		if ((p.getInventory().getLeggings() != null) && (p.getInventory().getLeggings().hasItemMeta())
				&& (p.getInventory().getLeggings().getItemMeta().hasLore())
				&& (p.getInventory().getLeggings().getItemMeta().getLore().contains("§7Auto-Reparo I"))) {
			if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
				return;
			}
			int newDur = p.getInventory().getLeggings().getDurability() - 2;
			if (newDur > 0) {
				p.getInventory().getLeggings().setDurability((short) newDur);
			} else {
				p.getInventory().getLeggings().setDurability((short) 0);
			}
		}
		if ((p.getInventory().getBoots() != null) && (p.getInventory().getBoots().hasItemMeta())
				&& (p.getInventory().getBoots().getItemMeta().hasLore())
				&& (p.getInventory().getBoots().getItemMeta().getLore().contains("§7Auto-Reparo I"))) {
			if (p.getItemInHand().getType() == Material.ENCHANTED_BOOK) {
				return;
			}
			int newDur = p.getInventory().getBoots().getDurability() - 2;
			if (newDur > 0) {
				p.getInventory().getBoots().setDurability((short) newDur);
			} else {
				p.getInventory().getBoots().setDurability((short) 0);
			}
		}
	}

	@EventHandler
	private void colocar(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6TNT-Build")) {
					e.getBlock().setType(Material.AIR);
					List<Location> locations = new ArrayList();
					this.Radius = 10;
					int locRad = this.Radius;
					Random rs = new Random();
					if ((this.LargerRadius) && (rs.nextInt(100) < 3 * 5)) {
						locRad += 2;
					}
					int r = locRad - 1;
					int start = r / 2;

					Location sL = e.getBlock().getLocation();

					p.getWorld().createExplosion(sL, 0.0F);

					sL.setX(sL.getX() - start);
					sL.setY(sL.getY() - start);
					sL.setZ(sL.getZ() - start);
					int y;
					for (int x = 0; x < locRad; x++) {
						for (y = 0; y < locRad; y++) {
							for (int z = 0; z < locRad; z++) {
								if (((x != 0) || (y != 0) || (z != 0)) && ((x != r) || (y != 0) || (z != 0))
										&& ((x != 0) || (y != r) || (z != 0)) && ((x != 0) || (y != 0) || (z != r))
										&& ((x != r) || (y != r) || (z != 0)) && ((x != 0) || (y != r) || (z != r))
										&& ((x != r) || (y != 0) || (z != r)) && ((x != r) || (y != r) || (z != r))) {
									locations.add(
											new Location(sL.getWorld(), sL.getX() + x, sL.getY() + y, sL.getZ() + z));
								}
							}
						}
					}
					for (Location loc : locations) {
						if ((!loc.getBlock().getDrops(item).isEmpty()) && (Main.canBreakAt(loc, p))) {
							if (this.DropItems) {
								loc.getBlock().breakNaturally(item);
							} else {
								for (ItemStack i : loc.getBlock().getDrops(item)) {
									p.getInventory().addItem(new ItemStack[] { i });
									loc.getBlock().setType(Material.AIR);
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	private void quebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		List<Location> locations = new ArrayList();
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasLore()) {
				if ((p.getItemInHand().getItemMeta().getLore().contains("§7Explosive I"))
						|| (p.getItemInHand().getItemMeta().getLore().equals("§7Explosive I"))) {
					int locRad = this.Radius;
					Random rs = new Random();
					if ((this.LargerRadius) && (rs.nextInt(100) < 3 * 5)) {
						locRad += 2;
					}
					int r = locRad - 1;
					int start = r / 2;

					Location sL = e.getBlock().getLocation();

					p.getWorld().createExplosion(sL, 0.0F);

					sL.setX(sL.getX() - start);
					sL.setY(sL.getY() - start);
					sL.setZ(sL.getZ() - start);
					int y;
					for (int x = 0; x < locRad; x++) {
						for (y = 0; y < locRad; y++) {
							for (int z = 0; z < locRad; z++) {
								if (((x != 0) || (y != 0) || (z != 0)) && ((x != r) || (y != 0) || (z != 0))
										&& ((x != 0) || (y != r) || (z != 0)) && ((x != 0) || (y != 0) || (z != r))
										&& ((x != r) || (y != r) || (z != 0)) && ((x != 0) || (y != r) || (z != r))
										&& ((x != r) || (y != 0) || (z != r)) && ((x != r) || (y != r) || (z != r))) {
									locations.add(
											new Location(sL.getWorld(), sL.getX() + x, sL.getY() + y, sL.getZ() + z));
								}
							}
						}
					}
					for (Location loc : locations) {
						String iMat = item.getType().toString();
						Block b = loc.getBlock();
						String bMat = b.getType().toString();
						if ((isUsable(iMat, bMat)) && (!loc.getBlock().getDrops(item).isEmpty())
								&& (Main.canBreakAt(loc, p))) {
							if (this.DropItems) {
								loc.getBlock().breakNaturally(item);
							} else {
								for (ItemStack i : loc.getBlock().getDrops(item)) {
									p.getInventory().addItem(new ItemStack[] { i });
									loc.getBlock().setType(Material.AIR);
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void Clicar(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("Encantar")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
				ItemStack i3 = e.getCurrentItem();
				if (!e.getClick().toString().contains("SHIFT")) {
					if (e.getRawSlot() <= 1) {
						i3 = e.getCursor();
					}
				}
				if ((i3 != null) && (!i3.getType().equals(Material.AIR)) && (i3.hasItemMeta())) {
					if ((i3.getType().toString().endsWith("HELMET")) || (i3.getType().toString().endsWith("CHESTPLATE"))
							|| (i3.getType().toString().endsWith("LEGGINGS"))
							|| (i3.getType().toString().endsWith("BOOTS"))
							|| (i3.getType().toString().endsWith("PICKAXE"))
							|| (i3.getType().toString().endsWith("SWORD"))
							|| (i3.getType().toString().endsWith("AXE"))) {
						if (e.getRawSlot() == 25) {
							return;
						}
						// 19 = item
						// 20 = livro
						if (inv.getItem(19) != null) {
							p.getInventory().addItem(inv.getItem(19));
						}
						p.getInventory().remove(i3);
						inv.setItem(19, i3);
						ItemStack i4 = i3;
						ItemMeta i42 = i4.getItemMeta();
						if ((i3.hasItemMeta()) && (i3.getItemMeta().hasLore())) {
							if (i3.getItemMeta().getLore()
									.contains(inv.getItem(20).getItemMeta().getLore().get(0).toString())) {
								p.sendMessage("§cEste item j§ possui este encantamento!");
								p.getInventory().addItem(inv.getItem(19));
								p.getInventory().addItem(inv.getItem(20));
								p.closeInventory();
								return;
							}
							List<String> lore = inv.getItem(19).getItemMeta().getLore();
							lore.add(
									"§7" + inv.getItem(20).getItemMeta().getLore().get(0).toString().replace("§7", ""));
							i42.setLore(lore);
							i4.setItemMeta(i42);
							inv.setItem(25, i4);
						} else {
							List<String> lore = new ArrayList();
							lore.add(
									"§7" + inv.getItem(20).getItemMeta().getLore().get(0).toString().replace("§7", ""));
							i42.setLore(lore);
							i4.setItemMeta(i42);
							inv.setItem(25, i4);
						}
					}
				}
				if ((e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasDisplayName())
						&& (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aConcluir"))) {
					if (inv.getItem(25) == null) {
						p.getInventory().addItem(inv.getItem(20));
						p.closeInventory();
						return;
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Hulk I")) {
						if ((!inv.getItem(25).getType().toString().endsWith("SWORD"))
								&& (!inv.getItem(25).getType().toString().endsWith("AXE"))) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Explosive I")) {
						if ((!inv.getItem(25).getType().toString().endsWith("PICKAXE"))
								&& (!inv.getItem(25).getType().toString().endsWith("AXE"))) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Thor I")) {
						if ((!inv.getItem(25).getType().toString().endsWith("SWORD"))
								&& (!inv.getItem(25).getType().toString().endsWith("AXE"))) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Ice Aspect I")) {
						if (!inv.getItem(25).getType().toString().endsWith("SWORD")) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Wither I")) {
						if (!inv.getItem(25).getType().toString().endsWith("SWORD")) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Poseidon I")) {
						if (!inv.getItem(25).getType().toString().endsWith("BOOTS")) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Destroyer I")) {
						if ((!inv.getItem(25).getType().toString().endsWith("SWORD"))
								&& (!inv.getItem(25).getType().toString().endsWith("AXE"))) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (inv.getItem(20).getItemMeta().getLore().contains("§7Mago I")) {
						if ((!inv.getItem(25).getType().toString().endsWith("PICKAXE"))
								&& (!inv.getItem(25).getType().toString().endsWith("AXE"))) {
							p.sendMessage(
									"§cEste encantamento n§o § compat§vel com este item, saiba mais atrav§s do comando /livros!");
							p.getInventory().addItem(inv.getItem(19));
							p.getInventory().addItem(inv.getItem(20));
							p.closeInventory();
							return;
						}
					}
					if (p.getLevel() < 50) {
						p.sendMessage("§cVoc§ precisa ter 50leveis de XP para fazer isto!");
						p.getInventory().addItem(inv.getItem(19));
						p.getInventory().addItem(inv.getItem(20));
						p.closeInventory();
						return;
					}
					p.sendMessage("§aEncantamento adicionado com sucesso.");
					p.setLevel(p.getLevel() - 50);
					p.getInventory().addItem(inv.getItem(25));
					p.closeInventory();
					return;
				}
				if (e.getRawSlot() == 19) {
					if (inv.getItem(19) != null)
						p.getInventory().addItem(inv.getItem(19));
					inv.setItem(19, null);
					inv.setItem(25, null);
				}
				if (e.getRawSlot() == 20) {
					if (inv.getItem(19) != null) {
						p.getInventory().addItem(inv.getItem(19));
					}
					p.getInventory().addItem(inv.getItem(20));
					p.closeInventory();
				}
				if (e.getRawSlot() == 25) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static void abrirGeneretor(Player p, ItemStack livro) {
		Inventory inv = Bukkit.createInventory(p, 36, "Encantar");

		List<String> lore = new ArrayList();
		lore.add("§7 Ap§s clicar com o livro na bigorna");
		lore.add("§7voc§ deve selecionar o item que quer");
		lore.add("§7encantar, para escolher o item segure");
		lore.add("§7SHIFT e clique no item.");
		lore.add("§7 Logo ap§s escolher o item voc§ j§ pode");
		lore.add("§7finalizar o processo clicando no bot§o de");
		lore.add("§7madeira.");
		inv.setItem(4, Manager.criarItem(Material.getMaterial(349), "§3Tutorial", lore, 3));

		inv.setItem(20, livro);

		inv.setItem(24, Manager.criarItem(Material.WOOD_BUTTON, "§aConcluir"));

		p.openInventory(inv);
	}
}