package me.wiljafor1.saintsup.Comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.wiljafor1.saintsup.APIs.Manager;
import me.wiljafor1.saintsup.APIs.Messages;

public class VIPs implements CommandExecutor, Listener {

	@EventHandler
	public void ClicarLivros(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("Livros Especiais")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if ((cmd.getName().equalsIgnoreCase("echest")) || (cmd.getName().equalsIgnoreCase("ec"))) {
			if (p.hasPermission("saints.echest")) {
				p.openInventory(p.getEnderChest());
			} else {
				p.sendMessage(Messages.getString("su.nopermechest").replace("&", "§"));
				return true;
			}
		}
		if ((cmd.getName().equalsIgnoreCase("craft")) || (cmd.getName().equalsIgnoreCase("craftar"))) {
			if (p.hasPermission("saints.craft")) {
				p.openWorkbench(p.getLocation(), true);
			} else {
				p.sendMessage(Messages.getString("su.nopermcraft").replace("&", "§"));
				return true;
			}
		}
		if ((cmd.getName().equalsIgnoreCase("fly")) || (cmd.getName().equalsIgnoreCase("voar"))) {
			if (p.hasPermission("saints.vip")) {
				if (p.getLocation().getWorld().getName().equalsIgnoreCase("terrenos")) {
					if (p.getAllowFlight()) {
						p.setAllowFlight(false);
                                                p.sendMessage(Messages.getString("su.flyoff").replace("&", "§"));
					} else {
						p.setAllowFlight(true);
						p.sendMessage(Messages.getString("su.flyon").replace("&", "§"));
					}
				} else {
                                        p.sendMessage(Messages.getString("su.flynoworld").replace("&", "§"));
					return true;
				}
			} else {
                                p.sendMessage(Messages.getString("su.nofly").replace("&", "§"));
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("reparar")) {
			if (p.hasPermission("saints.reparar")) {
				if ((p.getItemInHand().getType() == null) || (p.getItemInHand().getType() == Material.AIR)) {
                                        p.sendMessage(Messages.getString("su.repairsemitem").replace("&", "§"));	
					return true;
				}
				ItemStack item = p.getItemInHand();
				if (item.getDurability() != 0) {
					item.setDurability((short) 0);
                                        p.sendMessage(Messages.getString("su.repairok").replace("&", "§"));
					p.updateInventory();
					return true;
				} else {
                                        p.sendMessage(Messages.getString("su.norepair").replace("&", "§"));
				}
			} else {
                                p.sendMessage(Messages.getString("su.nopermrepair").replace("&", "§"));
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("desencantar")) {
			if (p.hasPermission("saints.desencantar")) {
				if ((p.getItemInHand().getType() == null) || (p.getItemInHand().getType() == Material.AIR)) {
                                        p.sendMessage(Messages.getString("su.desencantarsemitem").replace("&", "§"));
					return true;
				}
				ItemStack item = p.getItemInHand();
				if ((item.getItemMeta().hasEnchants()) && (item.hasItemMeta())) {
					for (Enchantment enchants : item.getEnchantments().keySet()) {
						item.removeEnchantment(enchants);
					}
					p.updateInventory();
					p.playSound(p.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
					return true;
				} else {
                                        p.sendMessage(Messages.getString("su.desencantarsemenchant").replace("&", "§"));
				}
			} else {
                                p.sendMessage(Messages.getString("su.desencantarsemperm").replace("&", "§"));
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("livros")) {
			Inventory inv = Bukkit.createInventory(p, 36, "Livros Especiais");

			List<String> lore = new ArrayList();
			lore.add("§7Hulk I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fEspadas e Machados");
			lore.add("§eFun§§o: §fAo hitar um jogador tenha chance");
			lore.add("§fde receber for§a por alguns segundos!");
			inv.setItem(11, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Auto-Reparo I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fArmaduras, Ferramentas e Espadas");
			lore.add("§eFun§§o: §fRepara automaticamente o equipamento!");
			inv.setItem(12, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Explosive I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fFerramentas");
			lore.add("§eFun§§o: §fCrie uma explos§o quebrando");
			lore.add("§ftodos os blocos em uma §rea de 3x3!");
			inv.setItem(13, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Thor I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fEspadas e Machados");
			lore.add("§eFun§§o: §fAo hitar um jogador tenha chance");
			lore.add("§fde lan§ar um raio nele!");
			inv.setItem(14, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Ice Aspect I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fEspadas");
			lore.add("§eFun§§o: §fAo hitar um jogador tenha chance");
			lore.add("§fde deixa-lo congelado!");
			inv.setItem(15, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Wither I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fEspadas");
			lore.add("§eFun§§o: §fAo hitar um jogador tenha chance");
			lore.add("§fde deixa-lo com Wither!");
			inv.setItem(20, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Poseidon I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fBotas");
			lore.add("§eFun§§o: §f--");
			inv.setItem(21, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Destroyer I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fEspadas e Machados");
			lore.add("§eFun§§o: §fAo hitar um jogador tenha chance");
			lore.add("§fde duplicar o dano!");
			inv.setItem(22, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			lore.clear();
			lore.add("§7Mago I");
			lore.add("§1");
			lore.add("§eEquipamentos: §fFerramentas");
			lore.add("§eFun§§o: §fGanhe o dobro de XP ao quebrar");
			lore.add("§fum min§rio!");
			inv.setItem(23, Manager.criarItem(Material.ENCHANTED_BOOK, "§eLivro Especial", lore));

			//p.openInventory(inv);
		}
		return false;
	}
}