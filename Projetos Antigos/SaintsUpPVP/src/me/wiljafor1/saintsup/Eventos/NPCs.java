package me.wiljafor1.saintsup.Eventos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.wiljafor1.saintsup.APIs.Manager;
import me.wiljafor1.saintsup.MySQL.MSManager;

public class NPCs implements Listener {

	@EventHandler
	public void ClicarMenu(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("Almas")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7[§] §eResgatar Almas")) {
					abrirMenu(p);
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7[§] §eAdicionar Almas")) {
					adicionarAlma(p);
				}
			}
		}
	}

	@EventHandler
	public void ClicarResgatar(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("Almas - Resgatar")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
				if (e.getRawSlot() == 11) {
					if (MSManager.getAlmas(p) < 40) {
						p.sendMessage("§4[§c§§4] §eVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§7[§] §eSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 40);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_HELMET, "",
							Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.DURABILITY, 4));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(11));
					}
				}
				if (e.getRawSlot() == 13) {
					if (MSManager.getAlmas(p) < 30) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 30);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_SWORD, "", Enchantment.DAMAGE_ALL, 5,
							Enchantment.FIRE_ASPECT, 3));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(13));
					}
				}
				if (e.getRawSlot() == 14) {
					if (MSManager.getAlmas(p) < 40) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 40);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_AXE, "", Enchantment.DAMAGE_ALL, 6,
							Enchantment.DURABILITY, 4));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(14));
					}
				}
				if (e.getRawSlot() == 20) {
					if (MSManager.getAlmas(p) < 50) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 50);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_CHESTPLATE, "",
							Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.DURABILITY, 4));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(20));
					}
				}
				if (e.getRawSlot() == 22) {
					if (MSManager.getAlmas(p) < 20) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 20);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					ItemStack item = new ItemStack(Material.BOW);
					ItemMeta item2 = item.getItemMeta();
					item2.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
					item2.addEnchant(Enchantment.ARROW_FIRE, 2, true);
					item2.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
					item.setItemMeta(item2);
					p.getInventory().addItem(item);
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(22));
					}
				}
				if (e.getRawSlot() == 29) {
					if (MSManager.getAlmas(p) < 45) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 45);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_LEGGINGS, "",
							Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.DURABILITY, 4));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(29));
					}
				}
				if (e.getRawSlot() == 38) {
					if (MSManager.getAlmas(p) < 40) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 40);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_BOOTS, "",
							Enchantment.PROTECTION_ENVIRONMENTAL, 5, Enchantment.DURABILITY, 4));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(38));
					}
				}
				if (e.getRawSlot() == 40) {
					if (MSManager.getAlmas(p) < 20) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 20);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					ItemStack item = new ItemStack(Material.POTION, 5, (short) 8233);
					p.getInventory().addItem(item);
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(40));
					}
				}
				if (e.getRawSlot() == 41) {
					if (MSManager.getAlmas(p) < 15) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 15);
					/*if (Board.boards.containsKey(p)) {
						Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
						Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
						Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
						Board.boards.get(p).setLine("§2", 4);
						Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
						Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
						Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
						Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
					}*/
					ItemStack item = new ItemStack(Material.POTION, 5, (short) 8226);
					p.getInventory().addItem(item);
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(41));
					}
				}
				if (e.getRawSlot() == 42) {
					if (MSManager.getAlmas(p) < 50) {
						p.sendMessage("§cVoc§ n§o possui almas suficiente.");
						p.closeInventory();
						return;
					}
					if (Manager.getFreeSlots(p) < 1) {
						p.sendMessage("§cSeu invent§rio est§ cheio!");
						p.closeInventory();
						return;
					}
					MSManager.setAlmas(p, MSManager.getAlmas(p) - 50);
					p.getInventory().addItem(
							Manager.criarItem(Material.TRIPWIRE_HOOK, "§4Key §4§lSUPREMA", Enchantment.DURABILITY, 1));
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						Manager.sendItemTooltipMessage(p2,
								"§6[Almas] §7" + p.getDisplayName() + " §7acaba de adquirir um item!", inv.getItem(42));
					}
				}
			}
		}
	}

	@EventHandler
	public void ClicarAdicionar(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("Almas - Adicionar")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
				if (!e.getCurrentItem().getItemMeta().hasDisplayName()) {
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVoltar")) {
					Inventory inv2 = Bukkit.createInventory(p, 27, "Almas");

					List<String> lore = new ArrayList<>();
					lore.add("§7Clique para resgatar(trocar) suas");
					lore.add("§7almas por itens especiais!");
					inv2.setItem(11, Manager
							.addEnchant(Manager.criarItem(Material.ENDER_PORTAL_FRAME, "§eResgatar Almas", lore)));

					inv.setItem(13, Manager.addEnchant(Manager.criarItem(Material.SKULL_ITEM, "§eCrie sua cabe§a",
							"§7Clique para criar uma cabe§a com almas!")));

					inv2.setItem(15, Manager.addEnchant(Manager.criarItem(Material.DEAD_BUSH, "§eAdicionar Almas",
							"§7Clique para adicionar suas almas!")));

					p.openInventory(inv2);
				}
				if ((e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§c"))
						&& (e.getCurrentItem().getType() == Material.SKULL_ITEM)) {
					e.setCancelled(false);
				}
				if (e.getRawSlot() == 4) {
					p.getInventory().addItem(inv.getItem(4));
					inv.setItem(4, null);
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aResgatar")) {
					if (inv.getItem(4) != null) {
						if (inv.getItem(4).getType() == Material.SKULL_ITEM) {
							if (inv.getItem(4).hasItemMeta() && inv.getItem(4).getItemMeta().hasLore()) {
								List<String> lore = inv.getItem(4).getItemMeta().getLore();
								String separador[] = lore.get(0).split(" ");
								String nalma = separador[0];
								if (inv.getItem(4).getAmount() > 1) {
									int quantidade = Integer.valueOf(nalma.replace("§7", ""))
											* inv.getItem(4).getAmount();
									p.sendMessage("§aVoc§ resgatou " + quantidade + " §aalma(s).");
									MSManager.setAlmas(p, MSManager.getAlmas(p) + quantidade);
									p.closeInventory();
									/*if (Board.boards.containsKey(p)) {
										Board.boards.get(p).setLine(" §fRank Atual: " + MSManager.getRank(p), 1);
										Board.boards.get(p).setLine(" §fPr§ximo Rank: " + Board.pegarProximo(p), 2);
										Board.boards.get(p).setLine("  " + Board.pegarPorcentagem(p), 3);
										Board.boards.get(p).setLine("§2", 4);
										Board.boards.get(p).setLine(" §fCoins: §7" + Board.pegarDinheiro(p) + "§2$", 5);
										Board.boards.get(p).setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
										Board.boards.get(p).setLine(" §fV§timas: §7" + MSManager.getVitimas(p), 7);
										Board.boards.get(p).setLine(" §fCl§: " + Board.pegarTag(p), 8);
									}*/
								} else {
									p.sendMessage("§aVoc§ resgatou " + nalma + " §aalma(s).");
									MSManager.setAlmas(p,
											MSManager.getAlmas(p) + Integer.valueOf(nalma.replace("§7", "")));
									p.closeInventory();
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	private void clicar(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Player v = (Player) e.getRightClicked();
			if (!v.isCustomNameVisible() || v.getCustomName() == null) {
				return;
			}
			if (v.getCustomName().equals("§cAlmas")) {
				e.setCancelled(true);
				Player p = e.getPlayer();
				Inventory inv = Bukkit.createInventory(p, 27, "Almas");

				List<String> lore = new ArrayList<>();
				lore.add("§7Clique para resgatar(trocar) suas");
				lore.add("§7almas por itens especiais!");
				inv.setItem(11,
						Manager.addEnchant(Manager.criarItem(Material.ENDER_PORTAL_FRAME, "§eResgatar Almas", lore)));

				inv.setItem(13, Manager.addEnchant(Manager.criarItem(Material.SKULL_ITEM, "§eCrie sua cabe§a",
						"§7Clique para criar uma cabe§a com almas!")));

				inv.setItem(15, Manager.addEnchant(Manager.criarItem(Material.DEAD_BUSH, "§eAdicionar Almas",
						"§7Clique para adicionar suas almas!")));

				p.openInventory(inv);
			}
			if (v.getCustomName().equals("§bMinerador")) {
				e.setCancelled(true);
				Player p = e.getPlayer();
				//Rank.abrirInv(p);
			}
		}
	}

	private void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, "Almas - Resgatar");

		List<String> lore = new ArrayList();
		lore.add("§bPre§o: §f40Almas");
		inv.setItem(11, Manager.criarItem(Material.DIAMOND_HELMET, "", lore, Enchantment.PROTECTION_ENVIRONMENTAL, 5,
				Enchantment.DURABILITY, 4));

		lore.clear();
		lore.add("§bPre§o: §f30Almas");
		inv.setItem(13, Manager.criarItem(Material.DIAMOND_SWORD, "", lore, Enchantment.DAMAGE_ALL, 5,
				Enchantment.FIRE_ASPECT, 3));

		lore.clear();
		lore.add("§bPre§o: §f40Almas");
		inv.setItem(14, Manager.criarItem(Material.DIAMOND_AXE, "", lore, Enchantment.DAMAGE_ALL, 6,
				Enchantment.DURABILITY, 4));

		lore.clear();
		lore.add("§bPre§o: §f50Almas");
		inv.setItem(20, Manager.criarItem(Material.DIAMOND_CHESTPLATE, "", lore, Enchantment.PROTECTION_ENVIRONMENTAL,
				5, Enchantment.DURABILITY, 4));

		lore.clear();
		lore.add("§bPre§o: §f20Almas");
		inv.setItem(22,
				Manager.criarItem(Material.BOW, "", lore, Enchantment.ARROW_DAMAGE, 5, Enchantment.ARROW_FIRE, 2));
		inv.getItem(22).addEnchantment(Enchantment.ARROW_INFINITE, 1);

		lore.clear();
		lore.add("§bPre§o: §f45Almas");
		inv.setItem(29, Manager.criarItem(Material.DIAMOND_LEGGINGS, "", lore, Enchantment.PROTECTION_ENVIRONMENTAL, 5,
				Enchantment.DURABILITY, 4));

		lore.clear();
		lore.add("§bPre§o: §f40Almas");
		inv.setItem(38, Manager.criarItem(Material.DIAMOND_BOOTS, "", lore, Enchantment.PROTECTION_ENVIRONMENTAL, 5,
				Enchantment.DURABILITY, 4));

		lore.clear();
		lore.add("§bPre§o: §f20Almas");
		inv.setItem(40, Manager.criarItem(Material.POTION, "", lore, 8233));
		inv.getItem(40).setAmount(5);

		lore.clear();
		lore.add("§bPre§o: §f15Almas");
		inv.setItem(41, Manager.criarItem(Material.POTION, "", lore, 8226));
		inv.getItem(41).setAmount(5);

		lore.clear();
		lore.add("§bPre§o: §f50Almas");
		inv.setItem(42,
				Manager.criarItem(Material.TRIPWIRE_HOOK, "§4Key §4§lSUPREMA", lore, Enchantment.DURABILITY, 1));
		p.openInventory(inv);
	}

	private void adicionarAlma(Player p) {
		Inventory inv = Bukkit.createInventory(p, 9, "Almas - Adicionar");

		inv.setItem(0, Manager.criarItem(Material.CARPET, "§cVoltar", "§7Voltar para o menu principal.", 14));
		inv.setItem(1, Manager.criarItem(Material.THIN_GLASS, "§eColoque a cabe§a >"));
		inv.setItem(2, Manager.criarItem(Material.THIN_GLASS, "§eColoque a cabe§a >"));
		inv.setItem(3, Manager.criarItem(Material.THIN_GLASS, "§eColoque a cabe§a >"));

		inv.setItem(5, Manager.criarItem(Material.THIN_GLASS, "§e< Coloque a cabe§a"));
		inv.setItem(6, Manager.criarItem(Material.THIN_GLASS, "§e< Coloque a cabe§a"));
		inv.setItem(7, Manager.criarItem(Material.THIN_GLASS, "§e< Coloque a cabe§a"));
		inv.setItem(8, Manager.criarItem(Material.CARPET, "§aResgatar", "§7Resgatar almas", 5));

		p.openInventory(inv);
	}
}