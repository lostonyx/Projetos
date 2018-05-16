package me.wiljafor1.saintsup.Comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffectType;

import me.wiljafor1.saintsup.Main;
import me.wiljafor1.saintsup.APIs.Manager;
import me.wiljafor1.saintsup.Eventos.Board;

public class x1 implements CommandExecutor, Listener {
	private static ArrayList<Player> participando = new ArrayList();
	private static boolean batalhando = false;
	private static ArrayList<Player> convidado = new ArrayList();

	@EventHandler
	public void ClicarMenu(InventoryClickEvent e) {
		if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getWhoClicked();
			if (inv.getTitle().equals("X1 - Informação")) {
				p.playSound(p.getLocation(), Sound.PISTON_RETRACT, 5.0F, 5.0F);
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cFechar")) {
					p.closeInventory();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAceitar")) {
					p.closeInventory();
					p.chat("/x1 aceitar " + convidado.get(1));
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRecusar")) {
					p.closeInventory();
					p.sendMessage("§c[1v1] §eVocê recusou o desafio de §7" + convidado.get(1).getName() + "§e!");
					Player p2 = (Player) convidado.get(1);
					p2.sendMessage("§c[1v1] §6" + p.getName() + " §erecusou seu desafio!");
					convidado.clear();
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void comandoBloqueado(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if ((!e.isCancelled()) && (isParticipando(p))) {
			p.sendMessage("§c[1v1] §eVocê não pode executar comandos durante a batalha!");
			e.setCancelled(true);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("x1")) {
			if (args.length == 0) {
				p.sendMessage("§c[1v1] §eComandos:");
				p.sendMessage(" §eUse §6/x1 desafiar <Jogador> §epara convidar alguem para um duelo!");
				p.sendMessage(" §eUse §6/x1 aceitar <Jogador> §epara aceitar o duelo de um jogador!");
				p.sendMessage(" §eUse §6/x1 camarote §epara ir ao camarote e ver a treta!");
				p.sendMessage(" §eUse §6/x1 §einfo para ver as batalhas!");
				if (p.hasPermission("niferfull.staff")) {
					p.sendMessage(" §cUse /x1 setloc para definir um local!");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("desafiar")) {
				if (args.length == 1) {
					p.sendMessage("§cERRO§4 >> §eUse /x1 desafiar §6<Jogador>!");
					return true;
				}
				if (isBatalhando()) {
					p.sendMessage("§c[1v1] §eJá existe uma luta ocorrendo, espere-á encerrar e tente novamente!");
					return true;
				}
				if (convidado.size() != 0) {
					p.sendMessage("§c[1v1] §eJá existe uma luta ocorrendo, espere-á encerrar e tente novamente!");
					return true;
				}
				final Player p2 = Bukkit.getPlayer(args[1]);
				if (p2 == null) {
					p.sendMessage("§cERRO§4 >> §eJogador não encontrado!");
					return true;
				}
				if (p2 == p) {
					p.sendMessage("§cERRO§4 >> §eVocê não pode se auto-desafiar!");
					return true;
				}
				p.sendMessage("§c[1v1] §eJogador desafiado com §7sucesso!");
				p2.sendMessage("§c[1v1] §6" + p.getName() + " §ete desafiou para uma batalha!");
				p2.sendMessage("§c[1v1] §6Para mais Informações use §6/x1 info§e!");
				Bukkit.broadcastMessage(
						"§c[1v1] §6" + p.getName() + " §edesafiou §6" + p2.getName() + " §epara um duelo até a morte!");
				convidado.add(p2);
				convidado.add(p);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						if ((x1.convidado.contains(p)) && (x1.convidado.contains(p2))) {
							p.sendMessage("§c[1v1] §7" + p2.getName() + "§e ignorou seu desafio!");
							p2.sendMessage("§c[1v1] §eVocê ignorou o desafio do jogador§7 " + p.getName() + "!");
							x1.convidado.clear();
							Bukkit.broadcastMessage(
									"§c[1v1] §7" + p2.getName() + " §eficou com medo de §7" + p.getName() + "§e e ignorou seu desafio!");
						}
					}
				}, 80 * 20L);
			}
			if (args[0].equalsIgnoreCase("aceitar")) {
				if (args.length == 1) {
					p.sendMessage("§c[1v1] §6Use /x1 aceitar <Jogador>!");
					return true;
				}
				if (!isConvidado(p)) {
					p.sendMessage("§cERRO§4 >> §eVocê não possui nenhum convite de X1 pendente!");
					return true;
				}
				if (convidado.get(0) != p) {
					p.sendMessage("§cERRO§4 >> §eOcorreu um erro. Tente novamente!");
					return true;
				}
				Player p2 = (Player) convidado.get(1);
				p2.sendMessage("§6[X1] §b" + p.getName() + " §7aceitou seu desafio!");

				World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("X1.POS1.Mundo"));
				int x = Main.plugin.getConfig().getInt("X1.POS1.X");
				int y = Main.plugin.getConfig().getInt("X1.POS1.Y");
				int z = Main.plugin.getConfig().getInt("X1.POS1.Z");
				Location pos1 = new Location(w, x, y, z);
				pos1.setPitch((float) Main.plugin.getConfig().getDouble("X1.POS1.Pitch"));
				pos1.setYaw((float) Main.plugin.getConfig().getDouble("X1.POS1.Yaw"));
				p2.teleport(pos1);

				World w2 = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("X1.POS2.Mundo"));
				int x2 = Main.plugin.getConfig().getInt("X1.POS2.X");
				int y2 = Main.plugin.getConfig().getInt("X1.POS2.Y");
				int z2 = Main.plugin.getConfig().getInt("X1.POS2.Z");
				Location pos2 = new Location(w2, x2, y2, z2);
				pos2.setPitch((float) Main.plugin.getConfig().getDouble("X1.POS2.Pitch"));
				pos2.setYaw((float) Main.plugin.getConfig().getDouble("X1.POS2.Yaw"));
				p.teleport(pos2);
				iniciarContagem(p, p2);
				convidado.clear();
				participando.add(p);
				participando.add(p2);
			}
			if (args[0].equalsIgnoreCase("camarote")) {
				if (!Main.plugin.getConfig().contains("X1.Camarote.")) {
					p.sendMessage("§c[§4!§c] §cO Camarote ainda não foi setado!");
					return true;
				}
				p.sendMessage("§eAguarde §63 Segundos");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("X1.Camarote.Mundo"));
						int x = Main.plugin.getConfig().getInt("X1.Camarote.X");
						int y = Main.plugin.getConfig().getInt("X1.Camarote.Y");
						int z = Main.plugin.getConfig().getInt("X1.Camarote.Z");
						Location lobby = new Location(w, x, y, z);
						lobby.setPitch((float) Main.plugin.getConfig().getDouble("X1.Camarote.Pitch"));
						lobby.setYaw((float) Main.plugin.getConfig().getDouble("X1.Camarote.Yaw"));
						p.teleport(lobby);
						p.sendMessage("§c[1v1] §eBem Vindo ao §7Camarote!");
						p.removePotionEffect(PotionEffectType.SLOW);
						p.removePotionEffect(PotionEffectType.JUMP);
					}
				}, 60L);
			}
			if (args[0].equalsIgnoreCase("info")) {
				if ((isConvidado(p)) && (convidado.get(0) == p)) {
					Inventory inv = Bukkit.createInventory(p, 54, "X1 - Informação");

					ItemStack vidro = Manager.criarItem(Material.THIN_GLASS, " ");
					inv.setItem(0, vidro);
					inv.setItem(1, vidro);
					inv.setItem(2, vidro);
					inv.setItem(3, vidro);
					inv.setItem(4, vidro);
					inv.setItem(5, vidro);
					inv.setItem(6, vidro);
					inv.setItem(7, vidro);
					inv.setItem(8, vidro);
					inv.setItem(9, vidro);
					inv.setItem(17, vidro);
					inv.setItem(18, vidro);
					inv.setItem(26, vidro);
					inv.setItem(27, vidro);
					inv.setItem(35, vidro);
					inv.setItem(36, vidro);
					inv.setItem(44, vidro);
					inv.setItem(45, vidro);
					inv.setItem(46, vidro);
					inv.setItem(47, vidro);
					inv.setItem(48, vidro);
					inv.setItem(49, vidro);
					inv.setItem(50, vidro);
					inv.setItem(51, vidro);
					inv.setItem(52, vidro);
					inv.setItem(53, vidro);

					ItemStack player1 = new ItemStack(Material.SKULL_ITEM);
					player1.setDurability((short) 3);
					player1.setAmount(1);
					SkullMeta player12 = (SkullMeta) player1.getItemMeta();
					Player p1 = (Player) convidado.get(1);
					player12.setOwner(p1.getName());
					player12.setDisplayName("§a" + p1.getName());
					List<String> lore = new ArrayList();
					lore.add(" ");
					lore.add(" §7* Este jogador te convidou para uma batalha!");
					lore.add(" §7* Clan dele: " + Board.pegarClan(p1));
					lore.add(" §7* Ping dele: " + Board.pegarPing(p1));
					lore.add(" ");
					lore.add("§eMais Informações em: www.niferkits.com/perfil/" + p1.getName());
					player12.setLore(lore);
					player1.setItemMeta(player12);
					inv.setItem(11, player1);

					List<String> lore2 = new ArrayList();
					lore2.add(" ");
					lore2.add(" §7* Clique para pedir que um staff assista a batalha!");
					lore2.add(" §7* Sistema ainda está em BETA!");
					lore2.add(" ");
					lore2.add(" §7* Em breve novas informações!");
					inv.setItem(13, Manager.criarItem(Material.EYE_OF_ENDER, "§6Pedir análise da staff", lore2));

					inv.setItem(29,
							Manager.criarItem(Material.WOOL, "§aAceitar", " §7* Clique para aceitar a batalha!", 5));
					inv.setItem(30,
							Manager.criarItem(Material.WOOL, "§aAceitar", " §7* Clique para aceitar a batalha!", 5));

					inv.setItem(32,
							Manager.criarItem(Material.WOOL, "§cRecusar", " §7* Clique para recusar a batalha!", 14));
					inv.setItem(33,
							Manager.criarItem(Material.WOOL, "§cRecusar", " §7* Clique para recusar a batalha!", 14));

					inv.setItem(38,
							Manager.criarItem(Material.WOOL, "§aAceitar", " §7* Clique para aceitar a batalha!", 5));
					inv.setItem(39,
							Manager.criarItem(Material.WOOL, "§aAceitar", " §7* Clique para aceitar a batalha!", 5));

					inv.setItem(41,
							Manager.criarItem(Material.WOOL, "§cRecusar", " §7* Clique para recusar a batalha!", 14));
					inv.setItem(42,
							Manager.criarItem(Material.WOOL, "§cRecusar", " §7* Clique para recusar a batalha!", 14));

					ItemStack air = new ItemStack(Material.AIR);
					inv.setItem(0, air);
					inv.setItem(1, air);
					inv.setItem(2, air);
					inv.setItem(3, air);
					inv.setItem(4, air);
					inv.setItem(5, air);
					inv.setItem(6, air);
					inv.setItem(7, air);
					inv.setItem(8, air);
					inv.setItem(9, air);
					inv.setItem(17, air);
					inv.setItem(18, air);
					inv.setItem(26, air);
					inv.setItem(27, air);
					inv.setItem(35, air);
					inv.setItem(36, air);
					inv.setItem(44, air);
					inv.setItem(45, air);
					inv.setItem(46, air);
					inv.setItem(47, air);
					inv.setItem(48, air);
					inv.setItem(49, Manager.criarItem(Material.BARRIER, "§cFechar",
							"§7Clique para fechar o Menu de Informações!"));
					inv.setItem(50, air);
					inv.setItem(51, air);
					inv.setItem(52, air);
					inv.setItem(53, air);

					p.openInventory(inv);
					return true;
				}
				Inventory inv = Bukkit.createInventory(p, 45, "X1 - Informação");

				ItemStack vidro = new ItemStack(Material.THIN_GLASS);
				inv.setItem(0, vidro);
				inv.setItem(1, vidro);
				inv.setItem(2, vidro);
				inv.setItem(3, vidro);
				inv.setItem(4, vidro);
				inv.setItem(5, vidro);
				inv.setItem(6, vidro);
				inv.setItem(7, vidro);
				inv.setItem(8, vidro);
				inv.setItem(9, vidro);
				inv.setItem(17, vidro);
				inv.setItem(18, vidro);
				inv.setItem(26, vidro);
				inv.setItem(27, vidro);
				inv.setItem(35, vidro);
				inv.setItem(36, vidro);
				inv.setItem(37, vidro);
				inv.setItem(38, vidro);
				inv.setItem(39, vidro);
				inv.setItem(40, vidro);
				inv.setItem(41, vidro);
				inv.setItem(42, vidro);
				inv.setItem(43, vidro);
				inv.setItem(44, vidro);
				if (isBatalhando()) {
					ItemStack player1 = new ItemStack(Material.SKULL_ITEM);
					player1.setDurability((short) 3);
					player1.setAmount(1);
					SkullMeta player12 = (SkullMeta) player1.getItemMeta();
					Player p1 = (Player) participando.get(0);
					player12.setOwner(p1.getName());
					player12.setDisplayName("§a" + participando.get(0).getName());
					List<String> lore = new ArrayList();
					lore.add("§cBatalhando contra " + participando.get(1).getName());
					lore.add("§7Clan: " + Board.pegarTag(p1));
					lore.add("§7Ping: " + Board.pegarPing(p1));
					lore.add("§cClique para incorporar o jogador");
					player12.setLore(lore);
					player1.setItemMeta(player12);
					inv.setItem(20, player1);

					ItemStack player2 = new ItemStack(Material.SKULL_ITEM);
					player2.setDurability((short) 3);
					player2.setAmount(1);
					SkullMeta player22 = (SkullMeta) player2.getItemMeta();
					Player p2 = (Player) participando.get(1);
					player22.setOwner(p2.getName());
					player22.setDisplayName("§a" + participando.get(1).getName());
					List<String> lore2 = new ArrayList();
					lore2.add("§cBatalhando contra " + participando.get(0).getName());
					lore2.add("§7Clan: " + Board.pegarTag(p2));
					lore2.add("§7Ping: " + Board.pegarPing(p2));
					lore2.add("§cClique para incorporar o jogador");
					player22.setLore(lore2);
					player2.setItemMeta(player22);
					inv.setItem(24, player2);
				} else {
					inv.setItem(22, Manager.criarItem(Material.THIN_GLASS, "§cNenhuma batalha ocorrendo"));
				}
				ItemStack air = new ItemStack(Material.AIR);
				inv.setItem(0, air);
				inv.setItem(1, air);
				inv.setItem(2, air);
				inv.setItem(3, air);
				inv.setItem(4, air);
				inv.setItem(5, air);
				inv.setItem(6, air);
				inv.setItem(7, air);
				inv.setItem(8, air);
				inv.setItem(9, air);
				inv.setItem(17, air);
				inv.setItem(18, air);
				inv.setItem(26, air);
				inv.setItem(27, air);
				inv.setItem(35, air);
				inv.setItem(36, air);
				inv.setItem(37, air);
				inv.setItem(38, air);
				inv.setItem(39, air);
				inv.setItem(40,
						Manager.criarItem(Material.BARRIER, "§cFechar", "§7Clique para fechar o Menu de Informações!"));
				inv.setItem(41, air);
				inv.setItem(42, air);
				inv.setItem(43, air);
				inv.setItem(44, air);

				p.openInventory(inv);
			}
			if (args[0].equalsIgnoreCase("setloc")) {
				if (!p.hasPermission("niferfull.staff")) {
					p.sendMessage("§cERRO§4 >> §eVocê precisa do cargo §6ADMIN §eou superior para concluir esta ação!");
					p.closeInventory();
					return true;
				}
				if (args.length == 1) {
					p.sendMessage("§cERRO§4 >> §eUse /x1 setloc <Camarote|POS1|POS2>!");
					return true;
				}
				if (args[1].equalsIgnoreCase("camarote")) {
					Main.plugin.getConfig().set("X1.Camarote.Mundo", p.getLocation().getWorld().getName());
					Main.plugin.getConfig().set("X1.Camarote.X", Integer.valueOf(p.getLocation().getBlockX()));
					Main.plugin.getConfig().set("X1.Camarote.Y", Integer.valueOf(p.getLocation().getBlockY()));
					Main.plugin.getConfig().set("X1.Camarote.Z", Integer.valueOf(p.getLocation().getBlockZ()));
					Main.plugin.getConfig().set("X1.Camarote.Pitch", Float.valueOf(p.getLocation().getPitch()));
					Main.plugin.getConfig().set("X1.Camarote.Yaw", Float.valueOf(p.getLocation().getYaw()));
					Main.plugin.saveConfig();
					p.sendMessage("§c[1v1] §eLocalização setada com §7sucesso!");
				}
				if (args[1].equalsIgnoreCase("pos1")) {
					Main.plugin.getConfig().set("X1.POS1.Mundo", p.getLocation().getWorld().getName());
					Main.plugin.getConfig().set("X1.POS1.X", Integer.valueOf(p.getLocation().getBlockX()));
					Main.plugin.getConfig().set("X1.POS1.Y", Integer.valueOf(p.getLocation().getBlockY()));
					Main.plugin.getConfig().set("X1.POS1.Z", Integer.valueOf(p.getLocation().getBlockZ()));
					Main.plugin.getConfig().set("X1.POS1.Pitch", Float.valueOf(p.getLocation().getPitch()));
					Main.plugin.getConfig().set("X1.POS1.Yaw", Float.valueOf(p.getLocation().getYaw()));
					Main.plugin.saveConfig();
					p.sendMessage("§c[1v1] §eLocalização setada com §6sucesso!");
				}
				if (args[1].equalsIgnoreCase("pos2")) {
					Main.plugin.getConfig().set("X1.POS2.Mundo", p.getLocation().getWorld().getName());
					Main.plugin.getConfig().set("X1.POS2.X", Integer.valueOf(p.getLocation().getBlockX()));
					Main.plugin.getConfig().set("X1.POS2.Y", Integer.valueOf(p.getLocation().getBlockY()));
					Main.plugin.getConfig().set("X1.POS2.Z", Integer.valueOf(p.getLocation().getBlockZ()));
					Main.plugin.getConfig().set("X1.POS2.Pitch", Float.valueOf(p.getLocation().getPitch()));
					Main.plugin.getConfig().set("X1.POS2.Yaw", Float.valueOf(p.getLocation().getYaw()));
					Main.plugin.saveConfig();
					p.sendMessage("§c[1v1] §eLocalização setada com §6sucesso!");
				}
			}
		}
		return false;
	}

	public static boolean isBatalhando() {
		return batalhando;
	}

	public static boolean isParticipando(Player p) {
		return participando.contains(p);
	}

	public static boolean isConvidado(Player p) {
		return convidado.contains(p);
	}

	private void iniciarContagem(Player p, Player p2) {
		batalhando = true;
		for (Player p3 : Bukkit.getOnlinePlayers()) {
			Manager.sendActionBar(p3, "§c[1v1] §6" + p.getName() + " §eVs §6" + p2.getName());
		}
		int i = 0;
		while (i < 100) {
			p.sendMessage("  ");
			p2.sendMessage("  ");
			i++;
		}
		p.sendMessage("§c[1v1] §6Iniciando X1... Boa sorte!");
		p2.sendMessage("§c[1v1] §6Iniciando X1... Boa sorte!");
	}

	private void iniciarFinalizacao(final Player p) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
				x1.participando.clear();
				x1.convidado.clear();
				x1.batalhando = false;
			}
		}, 300L);
	}

	@EventHandler
	private void sair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (!isParticipando(p)) {
			return;
		}
		participando.remove(p);
		p.setHealth(0.0D);
		Player p2 = (Player) participando.get(0);
		p2.sendMessage("§c[1v1] §eVocê venceu, tem §615s§e para pegar os itens do chão!");
		Bukkit.broadcastMessage(
				"§c[1v1] §6" + p.getName() + " §eficou com medo e deslogou, vitoria de §7" + p2.getName() + "§e!");
		iniciarFinalizacao(p2);
	}

	@EventHandler
	private void matar(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (!isParticipando(p)) {
			return;
		}
		p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		participando.remove(p);
		Player p2 = (Player) participando.get(0);
		p2.sendMessage("§c[1v1] §eVocê venceu, tem &615s &epara pegar os itens do chão!");
		p.sendMessage("§c[1v1] §cVocê perdeu a batalha!");
		Bukkit.broadcastMessage("§c[1v1] §6" + p2.getName() + " §evenceu §7" + p.getName() + "§eno 1v1!");
		iniciarFinalizacao(p2);
	}
}
