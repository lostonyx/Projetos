package rpg.system;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import rpg.system.models.Account;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.utils.Cooldown;
import rpg.utils.PorcentApi;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class vida {

	private static int timeregen;

	public static void vida(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			int vidaextra = 0;
			ItemStack[] armorContents;
			for (int length = (armorContents = p.getInventory().getArmorContents()).length, k = 0; k < length; ++k) {
				ItemStack i = armorContents[k];
				if (Lores.containsAtribute("Vida", i)) {
					int vida = Lores.getAtrValue(i, "Vida");
					vidaextra += vida;
				}
			}
			int vidamax = (5 * a.getLevel());
			a.setMaxhealth(vidamax + vidaextra);
		}
	}

	public static void fixvida(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if (a.getHealth() > a.getMaxhealth()) {
				a.setHealth(a.getMaxhealth());
			}
			PorcentApi p1 = PorcentApi.getInstance();
			int por = percent(a.getHealth(), a.getMaxhealth());
			p.setHealth(p1.health(a.getMaxhealth(), a.getHealth()));
		}
	}

	public static void fixmana(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if (a.getMana() > 20) {
				a.setMana(20);
			}
			PorcentApi p1 = PorcentApi.getInstance();
			int por = percent(a.getMana(), 20);
			p.setFoodLevel((int) p1.health(20, a.getMana()));
		}
	}

	public static void healthregen(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if (percent(a.getHealth(), a.getMaxhealth()) < 25) {
				SimpleRunnable r = SimpleRunnable.getInstance();
				JogadorManager.regenhealth.add(p);
				if (JogadorManager.regenhealth.contains(p)) {
					timeregen = 150;
					r.createTaskTimer(TaskType.SYNC, p.getName() + "-regenhealth", new Runnable() {
						@Override
						public void run() {
							timeregen = timeregen - 1;
							if (a.getHealth() == a.getMaxhealth()) {
								JogadorManager.regenhealth.remove(p);
								r.cancel(p.getName() + "-regenhealth");
							}
							else {
								if (timeregen <= 0) {
									JogadorManager.regenhealth.remove(p);
									r.cancel(p.getName() + "-regenhealth");
								}
								else {
									if (a.getHealth() > a.getMaxhealth()) {
										a.setHealth(a.getMaxhealth());
									} else {
										int vida = a.getHealth();
										a.setHealth(vida + 2);
									}
								}
							}
						}
					}, 0, 10 * 20);
				}
			}
		}
	}

	public static void manaregen(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			SimpleRunnable r = SimpleRunnable.getInstance();
			if (percent(a.getMana(), 20) < 10) {
				JogadorManager.regenmana.add(p);
				if (JogadorManager.regenmana.contains(p)) {
					timeregen = 150;
					r.createTaskTimer(TaskType.SYNC, p.getName() + "-regenmana", new Runnable() {
						@Override
						public void run() {
							timeregen = timeregen - 1;
							if (a.getMana() == 20) {
								JogadorManager.regenmana.remove(p);
								r.cancel(p.getName() + "-regenmana");
							}
							else {
								if (timeregen <= 0) {
									JogadorManager.regenmana.remove(p);
									r.cancel(p.getName() + "-regenmana");
								}
								else {
									if (a.getMana() > 20) {
										a.setMana(20);
										r.cancel(p.getName() + "-regenmana");
									} else {
										int mana = a.getMana();
										a.setMana(mana + 2);
									}
								}
							}
						}
					}, 0, 5 * 20);
				}
			}
		}
	}

	public static void xp(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			int maxxp = a.getLevel() * 500;
			a.setMaxxp(maxxp);
			p.setLevel(a.getLevel());
			p.setExp(xp(maxxp, a.getXp()));
		}
	}
	
	public static int percent(int currentValue, int maxValue) {
		float percent = (currentValue * 100 / maxValue);
		return (int) percent;
	}
	
	public static float xp(int total, int atual) {
		return (((float) atual / (float) total));
	}
	
	
	public static void getAttackDamage(Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if (pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if(p.getItemInHand() != null) {
				a.setAttackdamage(Lores.getAtrValue(p.getItemInHand(), "Ataque Geral"));
			}
			else {
				a.setAttackdamage(0.5);
			}
		}
	}

	public static void update() {
		Bukkit.getOnlinePlayers().forEach(v -> {
			Jogador pw = JogadorManager.getWynnPlayer(v);
			if (pw.getCurrentAccount() != null) {
				getAttackDamage(v);
				vida(v);
				fixvida(v);
				healthregen(v);
				fixmana(v);
				manaregen(v);
				xp(v);
			} else {
				return;
			}
		});
	}
}
