package me.wiljafor1.saintsup;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import me.wiljafor1.saintsup.APIs.BauManager;
import me.wiljafor1.saintsup.APIs.Messages;
import me.wiljafor1.saintsup.Comandos.Bau;
import me.wiljafor1.saintsup.Comandos.Ping;
import me.wiljafor1.saintsup.Comandos.VIPs;
import me.wiljafor1.saintsup.Comandos.x1;
import me.wiljafor1.saintsup.Eventos.Crates;
import me.wiljafor1.saintsup.Eventos.E_Players;
import me.wiljafor1.saintsup.Eventos.E_Servers;
import me.wiljafor1.saintsup.Eventos.NPCs;
import me.wiljafor1.saintsup.Livros.Encantamentos;
import me.wiljafor1.saintsup.MySQL.EndDB;
import me.wiljafor1.saintsup.MySQL.MSManager;
import net.milkbowl.vault.economy.Economy;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	private BauManager BauManager;
	public static SimpleClans sc;
	public static Economy money = null;

	public static ReentrantLock lock = new ReentrantLock(true);
	public static String SQL_HOST = null;
	public static String SQL_PORT = null;
	public static String SQL_USER = null;
	public static String SQL_PASS = null;
	public static String SQL_DATA = null;
	public static Boolean SQL_DSC = Boolean.valueOf(false);
	private static Connection con;
	public MSManager mysql = new MSManager(this);
	public boolean database = false;

	public void onLoad() {
		getServer().getConsoleSender().sendMessage("§e[NiferPvP] Carregando plugin...");
		plugin = this;
		saveDefaultConfig();
		try {
			this.mysql.iniciarDatabase();
			//this.kitm.iniciarDatabase();
		} catch (SQLException e) {
			this.database = false;
			getServer().getConsoleSender().sendMessage("§c[NiferPvP] Nao foi possivel ativar o MySQL.");
		}
	}

	public void onEnable() {
            try {
                Messages.enable("pt_BR");
                System.out.println(Messages.getString("su.loaded"));
            } catch (Exception e) {
                Messages.enable("pt_BR");
                System.out.println(Messages.getString("su.loaded"));
            }
		setupEconomy();
		if (!hookSimpleClans()) {
			getServer().getConsoleSender()
					.sendMessage("§c[NiferPvP] SimpleClans nao encontrado, desligando servidor...");
			Bukkit.shutdown();
		}
		File chestFolder = new File(getDataFolder(), "baus");
		if (!chestFolder.exists()) {
			getServer().getConsoleSender().sendMessage("§c[NiferPvP] Pasta 'Baus' nao encontrada, criando local...");
			chestFolder.mkdirs();
			getServer().getConsoleSender().sendMessage("§c[NiferPvP] Local " + chestFolder + " criado.");
		}
		BauManager = new BauManager(chestFolder, getLogger());
		getServer().getConsoleSender().sendMessage("§e[NiferPvP] Plugin ativado.");
		getServer().getPluginManager().registerEvents(new VIPs(), this);
		getServer().getPluginManager().registerEvents(new x1(), this);
		getServer().getPluginManager().registerEvents(new Crates(), this);
		getServer().getPluginManager().registerEvents(new E_Players(), this);
		getServer().getPluginManager().registerEvents(new E_Servers(), this);
		getServer().getPluginManager().registerEvents(new NPCs(), this);
		getServer().getPluginManager().registerEvents(new Encantamentos(), this);
		getCommand("crate").setExecutor(new Crates());
		getCommand("bau").setExecutor(new Bau(BauManager));
		getCommand("ping").setExecutor(new Ping());
		getCommand("echest").setExecutor(new VIPs());
		getCommand("ec").setExecutor(new VIPs());
		getCommand("craft").setExecutor(new VIPs());
		getCommand("craftar").setExecutor(new VIPs());
		getCommand("fly").setExecutor(new VIPs());
		getCommand("voar").setExecutor(new VIPs());
		getCommand("reparar").setExecutor(new VIPs());
		getCommand("desencantar").setExecutor(new VIPs());
		getCommand("livros").setExecutor(new VIPs());
		getCommand("x1").setExecutor(new x1());
		gerarMsgs();
		int check = 5 * 1200;

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				int savedChests = BauManager.pegarChestSalvar();
				getServer().getConsoleSender().sendMessage("§e[NiferPvP] Salvo " + savedChests + " bau(s)");
				boolean novo = false;
				getServer().getConsoleSender().sendMessage("§c[NiferPvP] Checando magnata...");
				for (OfflinePlayer p2 : Bukkit.getOfflinePlayers()) {
					if (getConfig().getString("Magnata") != null) {
						String magnata = getConfig().getString("Magnata");
						if (Main.money.getBalance(p2.getName()) > Main.money.getBalance(magnata)) {
							if (Bukkit.getOnlinePlayers().contains(magnata)) {
								if (Bukkit.getPlayer(magnata).getDisplayName().contains("§2[$]")) {
									Bukkit.getPlayer(magnata)
											.setDisplayName("§7" + Bukkit.getPlayer(magnata).getName());
								}
							}
							getConfig().set("Magnata", p2.getName());
							saveConfig();
							novo = true;
						}
					}
				}
				if (novo == true) {
					String magnata = getConfig().getString("Magnata");
					if (Bukkit.getOnlinePlayers().contains(magnata)) {
						Player atual = Bukkit.getPlayer(magnata);
						Bukkit.broadcastMessage("§2[$] §7" + atual.getDisplayName() + " agora § o Magnata!");
						for (Player online : Bukkit.getOnlinePlayers()) {
							online.sendTitle(atual.getDisplayName(), "§f§ o novo §2Magnata§f!");
						}
						if ((!atual.getDisplayName().contains("§4[Dono]"))
								|| (!atual.getDisplayName().contains("§6[Dev]"))
								|| (!atual.getDisplayName().contains("§2[Moderador]"))
								|| (!atual.getDisplayName().contains("§c[Admin]"))
								|| (!atual.getDisplayName().contains("§e[Ajudante]"))
								|| (!atual.getDisplayName().contains("§c[YT]"))
								|| (!atual.getDisplayName().contains("§6[VIP]"))) {
							atual.setDisplayName("§2[$] §7" + atual.getName());
						}
					} else {
						Bukkit.broadcastMessage("§2[$] §7" + getConfig().getString("Magnata") + " agora e o Magnata!");
						for (Player online : Bukkit.getOnlinePlayers()) {
							online.sendTitle(getConfig().getString("Magnata"), "§f§ o novo §2Magnata§f!");
						}
					}
				}
			}
		}, check, check);
	}

	public void onDisable() {
		if (this.database) {
			this.mysql.closeDB();
		}
		int savedChests = BauManager.pegarChestSalvar();
		getServer().getConsoleSender().sendMessage("§e[NiferPvP] Salvo " + savedChests + " bau(s)");
		getServer().getConsoleSender().sendMessage("§e[NiferPvP] Plugin desativado.");
	}

	private boolean hookSimpleClans() {
		try {
			Plugin[] arrayOfPlugin;
			int j = (arrayOfPlugin = getServer().getPluginManager().getPlugins()).length;
			for (int i = 0; i < j; i++) {
				Plugin plugin = arrayOfPlugin[i];
				if ((plugin instanceof SimpleClans)) {
					sc = (SimpleClans) plugin;
					return true;
				}
			}
		} catch (NoClassDefFoundError e) {
			return false;
		}
		return false;
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			money = economyProvider.getProvider();
		}

		return (money != null);
	}

	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	public static boolean areaPvP(Player p) {
		ApplicableRegionSet region = getWorldGuard().getRegionManager(p.getWorld())
				.getApplicableRegions(p.getLocation());
		if (region.allows(DefaultFlag.PVP)) {
			return true;
		}
		return false;
	}

	public static boolean canBreakAt(Location loc, Player p) {
		if (getWorldGuard().canBuild(p, loc)) {
			return true;
		}
		return false;
	}

	public static synchronized void SQLconnect() {
		try {
			plugin.getLogger().info("Conectando ao MySQL...");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String conn = "jdbc:mysql://" + SQL_HOST + ":" + SQL_PORT + "/" + SQL_DATA;
			con = DriverManager.getConnection(conn, SQL_USER, SQL_PASS);
		} catch (ClassNotFoundException ex) {
			plugin.getLogger().warning("MySQL Driver nao encontrado!");
		} catch (SQLException ex) {
			plugin.getLogger().warning("Erro ao tentar conectar ao Mysql!");
		} catch (Exception ex) {
			plugin.getLogger().warning("Erro desconhecido enquanto tentava conectar ao MySQL.");
		}
	}

	public static void SQLdisconnect() {
		EndDB end = new EndDB(plugin, plugin.getLogger(), con);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(end);
		executor.shutdown();
	}

	private static void gerarMsgs() {
		new BukkitRunnable() {
			public void run() {
				List<String> msgs = new ArrayList();
				msgs.add("  §eO servidor esta na fase §6BETA§e, bugs podem ocorrer!");
				msgs.add("  §eUse /livros para ver as funcoes de cada um dos livros especiais!");
				msgs.add("  §eVoc§ pode retirar MobSpawner usando uma picareta com Toque Suave!");
				msgs.add(
						"  §eJogadores VIPs possuem os mesmos beneficios do rank §0[Obsidian]§e, adquira j§ o seu! \n  §6Loja: §eniferkits.com/loja");
				msgs.add("  §eUse /ranks para ver todos os ranks do servidor!");
				msgs.add(
						"  §eJogadores VIPs podem reparar seus itens sem nenhum custo atrav§s do comando /reparar, adquira j§ o seu! \n  §6Loja: §eniferkits.com/loja");
				msgs.add(
						"  §eJogadores VIPs podem modificar suas prefer§ncias atrav§s do comando /toggle, adquira j§ o seu! \n  §6Loja: §eniferkits.com/loja");
				msgs.add(
						"  §6[Eventos] §eSaiba de todos os eventos que ir§ acontecer no dia atrav§s do comando /eventos!");
				msgs.add(
						"  §eJogadores VIPs possuem desconto na loja, adquira j§ o seu! \n  §6Loja: §eniferkits.com/loja");
				msgs.add(
						"  §eJogadores VIPs possuem o dobro de chance de encontrarem cabe§as com almas matando outros jogadores! \n  §6Loja: §eniferkits.com/loja");
				msgs.add(
						"  §eJ§ pegou sua Key Monster hoje? Ainda n§o? Ent§o n§o perca mais tempo! Use /kit e v§ na p§gina de 'kits especiais'!");
				msgs.add("  §eVoc§ consegue almas matadando outros jogadores na warp arena!");
				msgs.add("  §eVoc§ pode trocar suas almas por itens especias!");
				msgs.add("  §eVoc§ pode encontrar itens especias nas caixas localizadas no spawn!");
				msgs.add("  §6[Mercado] §eVoc§ pode vender os itens que n§o quer mais no /mercado!");
				msgs.add(
						"  §eJogadores VIPs podem ter at§ tr§s(3) terrenos, adquira j§ o seu! \n  §6Loja: §eniferkits.com/loja");
				Random r = new Random();
				r.nextInt(msgs.size());
				String o = (String) msgs.get(new Random().nextInt(msgs.size()));
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(o);
				Bukkit.broadcastMessage(" ");
			}
		}.runTaskTimer(plugin, 0L, 20 * 150L);
	}
}