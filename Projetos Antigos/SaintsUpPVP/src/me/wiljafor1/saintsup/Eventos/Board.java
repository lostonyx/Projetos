package me.wiljafor1.saintsup.Eventos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.wiljafor1.saintsup.Main;
import me.wiljafor1.saintsup.APIs.ScoreAPI;
import me.wiljafor1.saintsup.MySQL.MSManager;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

public class Board {

	public static Map<Player, ScoreAPI> boards = new HashMap<>();

	public static void setarScore(Player p) {
		ScoreAPI score = new ScoreAPI(p, "§b§lRANKUP");

		score.create();
		score.setLine("§3", 0);
		score.setLine(" §fRank Atual: SEM RANK", 1);
		score.setLine(" §fPróximo Rank: SEM RANK", 2);
		score.setLine("  ", 3);
		score.setLine("§2", 4);
		score.setLine(" §fCoins: §7" + pegarDinheiro(p) + "§2$", 5);
		score.setLine(" §fAlmas: §7" + MSManager.getAlmas(p), 6);
		score.setLine(" §fVítimas: §7" + MSManager.getVitimas(p), 7);
		score.setLine(" §fClã: " + pegarTag(p), 8);
		score.setLine("§1", 9);
		score.setLine("§b   www.niferkits.com", 10);
		boards.put(p, score);
	}



	public static String pegarPing(Player p) {
		if ((((CraftPlayer) p).getHandle().ping > 200) && (((CraftPlayer) p).getHandle().ping < 400)) {
			return "§2" + ((CraftPlayer) p).getHandle().ping;
		}
		if ((((CraftPlayer) p).getHandle().ping > 400) && (((CraftPlayer) p).getHandle().ping < 600)) {
			return "§c" + ((CraftPlayer) p).getHandle().ping;
		}
		if (((CraftPlayer) p).getHandle().ping > 600) {
			return "§4" + ((CraftPlayer) p).getHandle().ping;
		}
		return "§a" + ((CraftPlayer) p).getHandle().ping;
	}

	public static String pegarClan(Player p) {
		ClanPlayer cp = Main.sc.getClanManager().getClanPlayer(p);
		if (cp != null) {
			return cp.getCleanName();
		}
		return "§7Nenhum";
	}

	public static String pegarTag(Player p) {
		ClanPlayer cp = Main.sc.getClanManager().getClanPlayer(p);
		if (cp != null) {
			return cp.getTagLabel().replace(".", "");
		}
		return "§7Nenhum";
	}

	public static float pegarKDR(Player p) {
		ClanPlayer cp = Main.sc.getClanManager().getClanPlayer(p);
		if (cp != null) {
			return cp.getKDR();
		}
		return 0.0F;
	}

	public static String format2(double x) {
		return String.format("%.2f", x);
	}

	public static String pegarDinheiro(Player p) {
		double money = Main.money.getBalance(p.getName());
		return format2(money);
	}
}