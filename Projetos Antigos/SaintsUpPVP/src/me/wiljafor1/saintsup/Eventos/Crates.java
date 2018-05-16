package me.wiljafor1.saintsup.Eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import me.wiljafor1.saintsup.Main;
import me.wiljafor1.saintsup.APIs.CrateSend;
import me.wiljafor1.saintsup.APIs.Manager;

public class Crates implements Listener, CommandExecutor {

	private void gerarPremio(Player p, String caixa) {
		if (caixa.equalsIgnoreCase("Basica")) {
			Random r = new Random();
			int o = r.nextInt(180);
			if (o <= 10) {
				p.sendMessage("§aVoc§ encontrou uma Picareta encantada!");
				p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_PICKAXE, "", Enchantment.DIG_SPEED, 3));
			}
			if (o > 10 && o <= 40) {
				p.sendMessage("§aVoc§ encontrou Blocos de Diamante!");
				p.getInventory().addItem(Manager.criarItem(Material.DIAMOND_BLOCK, 9, ""));
			}
			if (o > 40 && o <= 60) {
				p.sendMessage("§aVoc§ encontrou Blocos de Esmeralda!");
				p.getInventory().addItem(Manager.criarItem(Material.EMERALD_BLOCK, 9, ""));
			}
			if (o > 60 && o <= 90) {
				p.sendMessage("§aVoc§ encontrou Blocos de Ferro!");
				p.getInventory().addItem(Manager.criarItem(Material.IRON_BLOCK, 9, ""));
			}
			if (o > 90 && o <= 110) {
				p.sendMessage("§aVoc§ encontrou algumas Ma§as Dourada!");
				p.getInventory().addItem(Manager.criarItem(Material.GOLDEN_APPLE, 10, ""));
			}
			if (o > 110 && o <= 120) {
				p.sendMessage("§aVoc§ encontrou um Capacete de Diamante!");
				p.getInventory().addItem(
						Manager.criarItem(Material.DIAMOND_HELMET, "", Enchantment.PROTECTION_ENVIRONMENTAL, 2));
			}
			if (o > 120 && o <= 130) {
				p.sendMessage("§aVoc§ encontrou um Peitoral de Diamante!");
				p.getInventory().addItem(
						Manager.criarItem(Material.DIAMOND_CHESTPLATE, "", Enchantment.PROTECTION_ENVIRONMENTAL, 2));
			}
			if (o > 130 && o <= 140) {
				p.sendMessage("§aVoc§ encontrou uma Cal§a de Diamante!");
				p.getInventory().addItem(
						Manager.criarItem(Material.DIAMOND_LEGGINGS, "", Enchantment.PROTECTION_ENVIRONMENTAL, 2));
			}
			if (o > 140 && o <= 150) {
				p.sendMessage("§aVoc§ encontrou uma Bota de Diamante!");
				p.getInventory().addItem(
						Manager.criarItem(Material.DIAMOND_BOOTS, "", Enchantment.PROTECTION_ENVIRONMENTAL, 2));
			}
			if (o > 150 && o <= 179) {
				p.sendMessage("§aVoc§ encontrou uma boa quantia em dinheiro!");
				Main.money.depositPlayer(p.getName(), 5000);
			}
			if (o == 180) {
				p.sendMessage("§aVoc§ encontrou uma Chave Avan§ada!");
				p.getInventory()
						.addItem(Manager.criarItem(Material.TRIPWIRE_HOOK, "§4Chave Avan§ada", Enchantment.DURABILITY));
			}
		}
		if (caixa.equalsIgnoreCase("Avan§ada")) {
			Random r = new Random();
			int o = r.nextInt(160);
			if (o <= 40) {
				p.sendMessage("§aVoc§ encontrou um MobSpawner de Aranha!");
				p.getInventory().addItem(Manager.criarItem(Material.MOB_SPAWNER, "§aSpider Spawner"));
			}
			if (o > 40 && o <= 60) {
				p.sendMessage("§aVoc§ encontrou um MobSpawner de Zombie!");
				p.getInventory().addItem(Manager.criarItem(Material.MOB_SPAWNER, "§aZombie Spawner"));
			}
			if (o > 60 && o <= 95) {
				p.sendMessage("§aVoc§ encontrou um MobSpawner de Creeper!");
				p.getInventory().addItem(Manager.criarItem(Material.MOB_SPAWNER, "§aCreeper Spawner"));
			}
			if (o > 95 && o <= 100) {
				p.sendMessage("§aVoc§ encontrou um MobSpawner de IronGolem!");
				p.getInventory().addItem(Manager.criarItem(Material.MOB_SPAWNER, "§aIron Golem Spawner"));
				for (Player p2 : Bukkit.getOnlinePlayers()) {
					p2.sendTitle(p.getDisplayName(), "§7encontrou um Golem Spawner na chave §4Avan§ada§7!");
				}
			}
			if (o > 100 && o <= 110) {
				p.sendMessage("§§aVoc§ encontrou um livro especial!");
				p.getInventory().addItem(Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", "§7Thor I"));
			}
			if (o > 110 && o <= 120) {
				p.sendMessage("§aVoc§ encontrou um livro especial!");
				p.getInventory()
						.addItem(Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", "§7Explosive I"));
			}
			if (o > 120 && o <= 130) {
				p.sendMessage("§aVoc§ encontrou um livro especial!");
				p.getInventory()
						.addItem(Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", "§7Ice Aspect I"));
			}
			if (o > 130 && o <= 135) {
				p.sendMessage("§aVoc§ encontrou um ResetKDR!");
				List<String> lore = new ArrayList();
				lore.add("§7Clique com o bot§o direito");
				lore.add("§7para resetar seu KDR!");
				lore.add("§1");
				lore.add("§e * O n§mero de v§timas ser§ resetado!");
				p.getInventory().addItem(
						Manager.criarItem(Material.SLIME_BALL, "§4Resetar KDR", lore, Enchantment.DURABILITY, 10));
				for (Player p2 : Bukkit.getOnlinePlayers()) {
					p2.sendTitle(p.getDisplayName(), "§7encontrou um ResetKDR na chave §4Avan§ada§7!");
				}
			}
			if (o > 135 && o <= 140) {
				p.sendMessage("§aVoc§ encontrou uma TNT-Build!");
				List<String> lore = new ArrayList();
				lore.add("§7Ao colocar no ch§o essa tnt");
				lore.add("§7ser§ ativada automaticamente");
				lore.add("§7criando uma explos§o de 10x10!");
				lore.add("§1");
				lore.add("§e * Perfeita para escavar plots.");
				p.getInventory()
						.addItem(Manager.criarItem(Material.TNT, "§6TNT-Build", lore, Enchantment.DURABILITY, 10));
				for (Player p2 : Bukkit.getOnlinePlayers()) {
					p2.sendTitle(p.getDisplayName(), "§7encontrou uma TNT-Build na chave §4Avan§ada§7!");
				}
			}
			if (o > 140 && o < 149) {
				p.sendMessage("§aVoc§ encontrou um MobSpawner de Guardian!");
				p.getInventory().addItem(Manager.criarItem(Material.MOB_SPAWNER, "§aGuardian Spawner"));
				for (Player p2 : Bukkit.getOnlinePlayers()) {
					p2.sendTitle(p.getDisplayName(), "§7encontrou um Guardian Spawner na chave §4Avan§ada§7!");
				}
			}
			if (o == 150) {
				p.sendMessage("§aVoc§ encontrou um MobSpawner de Wither!");
				p.getInventory().addItem(Manager.criarItem(Material.MOB_SPAWNER, "§aWither Spawner"));
				for (Player p2 : Bukkit.getOnlinePlayers()) {
					p2.sendTitle(p.getDisplayName(), "§7encontrou um Wither Spawner na chave §4Avan§ada§7!");
				}
			}
		}
	}

	@EventHandler
	public void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getClickedBlock() == null) {
			return;
		}
		if (e.getClickedBlock().getType() == Material.CHEST) {
			if (e.getClickedBlock().getLocation().getBlockX() == Main.plugin.getConfig().getInt("Bau.0.X")) {
				if (e.getClickedBlock().getLocation().getBlockY() == Main.plugin.getConfig().getInt("Bau.0.Y")) {
					if (e.getClickedBlock().getLocation().getBlockZ() == Main.plugin.getConfig().getInt("Bau.0.Z")) {
						e.setCancelled(true);
						if (!p.getItemInHand().hasItemMeta()) {
							p.sendMessage("§cVoc§ precisa de uma Key para fazer isto!");
							return;
						}
						if (p.getItemInHand().getType() != Material.TRIPWIRE_HOOK) {
							p.sendMessage("§cVoc§ precisa de uma Key para fazer isto!");
							return;
						}
						if (p.getGameMode() == GameMode.CREATIVE) {
							p.sendMessage("§cVoc§ precisa alterar seu gamemode para fazer isto!");
							return;
						}
						if (p.getItemInHand().getItemMeta().getDisplayName().equals("§aChave B§sica")) {
							if (Manager.getFreeSlots(p) < 1) {
								p.sendMessage("§cSeu invent§rio est§ cheio!");
								return;
							}
							if (p.getItemInHand().getAmount() > 1) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
								p.updateInventory();
								gerarPremio(p, "Basica");
								return;
							}
							if (p.getItemInHand().getAmount() == 1) {
								p.getInventory().remove(p.getItemInHand());
								gerarPremio(p, "Basica");
								return;
							}
						}
					}
				}
			}

			// Bau 1
			if (e.getClickedBlock().getLocation().getBlockX() == Main.plugin.getConfig().getInt("Bau.1.X")) {
				if (e.getClickedBlock().getLocation().getBlockY() == Main.plugin.getConfig().getInt("Bau.1.Y")) {
					if (e.getClickedBlock().getLocation().getBlockZ() == Main.plugin.getConfig().getInt("Bau.1.Z")) {
						e.setCancelled(true);
						if (!p.getItemInHand().hasItemMeta()) {
							p.sendMessage("§cVoc§ precisa de uma Key para fazer isto!");
							return;
						}
						if (p.getItemInHand().getType() != Material.TRIPWIRE_HOOK) {
							p.sendMessage("§cVoc§ precisa de uma Key para fazer isto!");
							return;
						}
						if (p.getGameMode() == GameMode.CREATIVE) {
							p.sendMessage("§cVoc§ precisa alterar seu gamemode para fazer isto!");
							return;
						}
						if (p.getItemInHand().getItemMeta().getDisplayName().equals("§4Chave Avan§ada")) {
							if (Manager.getFreeSlots(p) < 1) {
								p.sendMessage("§cSeu invent§rio est§ cheio!");
								return;
							}
							if (p.getItemInHand().getAmount() > 1) {
								p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
								p.updateInventory();
								gerarPremio(p, "Avan§ada");
								return;
							}
							if (p.getItemInHand().getAmount() == 1) {
								p.getInventory().remove(p.getItemInHand());
								gerarPremio(p, "Avan§ada");
								return;
							}
						}
					}
				}
			}
		}
		if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
			if (!e.getClickedBlock().getLocation().getWorld().getName().equals("terrenos")) {
				p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(p, 27, "Ba§s");

				List<String> lore = new ArrayList();
				lore.add("§8/echest");
				lore.add("§1");
				lore.add("§7Clique com o §aESQUERDO §7para abrir");
				lore.add("§7Clique com o §aDIREITO §7para customizar");
				inv.setItem(12, Manager.criarItem(Material.ENDER_CHEST, "§eEnderChest", lore));

				if (p.hasPermission("niferfull.chest")) {
					lore.clear();
					lore.add("§8/bau");
					lore.add("§1");
					lore.add("§7Clique com o §aESQUERDO §7para abrir");
					lore.add("§7Clique com o §aDIREITO §7para customizar");
					inv.setItem(14, Manager.criarItem(Material.CHEST, "§eBa§", lore));
				} else {
					lore.clear();
					lore.add("§8/bau");
					lore.add("§1");
					lore.add("§cVoc§ n§o possui este ba§!");
					lore.add("§7Clique com o §aDIREITO §7para comprar");
					inv.setItem(14, Manager.criarItem(Material.CHEST, "§cBa§", lore));
				}
				p.openInventory(inv);
			}
		}
	}

	@EventHandler
	private void clicar(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("Ba§s")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
				if (e.getClick() == ClickType.LEFT) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eEnderChest")) {
						p.openInventory(p.getEnderChest());
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eBa§")) {
						p.chat("/bau");
					}
				}
				if (e.getClick() == ClickType.RIGHT) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eEnderChest")) {
						abrirCustom(p, "echest");
					}
				}
			}
		}
	}

	private static void abrirCustom(Player p, String tipo) {
		if (tipo.equalsIgnoreCase("chest-comprar")) {
			Inventory inv = Bukkit.createInventory(p, 45);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("crate")) {
			if (!p.hasPermission("niferfull.staff")) {
				p.sendMessage("§cVoc§ precisa do cargo MODERADOR ou superior para concluir esta a§§o!");
				return true;
			}
			if (args.length < 2) {
				p.sendMessage("§f[Crates] §fUse §7/crate set <ID> §fpara criar um ba§!");
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				if (args[1].equalsIgnoreCase("0")) {
					CrateSend id = new CrateSend(p.getLocation(), "0");
					p.sendMessage("§f[Crates] §aBa§ criado com sucesso!");
				}
				if (args[1].equalsIgnoreCase("1")) {
					CrateSend id = new CrateSend(p.getLocation(), "1");
					p.sendMessage("§f[Crates] §aBa§ criado com sucesso!");
				}
			}
		}
		return false;
	}
}