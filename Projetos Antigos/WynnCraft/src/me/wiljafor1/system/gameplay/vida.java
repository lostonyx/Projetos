package me.wiljafor1.system.gameplay;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import me.wiljafor1.items.Lores;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.notification;
import me.wiljafor1.utils.Cooldown;
import me.wiljafor1.utils.PorcentApi;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;

public class vida {
	
	private static int timeregen;
	
	public static void vida(Player p) {
		WynnPlayer pw = WynnPlayerManager.getWynnPlayer(p);
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			int vidaextra = 0;
			if(p.getInventory().getHelmet() != null) {
				if(p.getInventory().getHelmet().getItemMeta().hasLore()) {
					ItemStack i = p.getInventory().getHelmet();
					if(Lores.containsAtribute("Vida", i)) {
						int vida = Lores.getAtrValue(i, "Vida");
						vidaextra += vida;
					}
					
				}
			}
			if(p.getInventory().getChestplate() != null) {
				if(p.getInventory().getChestplate().getItemMeta().hasLore()) {
					ItemStack i = p.getInventory().getChestplate();
					if(Lores.containsAtribute("Vida", i)) {
						int vida = Lores.getAtrValue(i, "Vida");
						vidaextra += vida;
					}
					
				}
			}
			if(p.getInventory().getLeggings() != null) {
				if(p.getInventory().getLeggings().getItemMeta().hasLore()) {
					ItemStack i = p.getInventory().getLeggings();
					if(Lores.containsAtribute("Vida", i)) {
						int vida = Lores.getAtrValue(i, "Vida");
						vidaextra += vida;
					}
					
				}
			}
			if(p.getInventory().getBoots() != null) {
				if(p.getInventory().getBoots().getItemMeta().hasLore()) {
					ItemStack i = p.getInventory().getBoots();
					if(Lores.containsAtribute("Vida", i)) {
						int vida = Lores.getAtrValue(i, "Vida");
						vidaextra += vida;
					}
					
				}
			}
			int vidamax = (5 * a.getLevel());
			a.setMaxhealth(vidamax + vidaextra);
		}
	}
	
	public static void fixvida(Player p) {
		WynnPlayer pw = WynnPlayerManager.getWynnPlayer(p);
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if(a.getHealth() > a.getMaxhealth()) {
				a.setHealth(a.getMaxhealth());
			}
			PorcentApi p1 = PorcentApi.getInstance();
			int por = percent(a.getHealth(), a.getMaxhealth());
			p.setHealth(p1.health(a.getMaxhealth(), a.getHealth()));
		}
	}
	
	public static void fixmana(Player p) {
		WynnPlayer pw = WynnPlayerManager.getWynnPlayer(p);
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if(a.getMana() > 20) {
				a.setMana(20);
			}
			PorcentApi p1 = PorcentApi.getInstance();
			int por = percent(a.getMana(), 20);
			p.setFoodLevel((int) p1.health(20, a.getMana()));
		}
	}
	
	public static void healthregen(Player p) {
		WynnPlayer pw = WynnPlayerManager.getWynnPlayer(p);
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			if(percent(a.getHealth(), a.getMaxhealth()) < 50) {
				SimpleRunnable r = SimpleRunnable.getInstance();
				if(!WynnPlayerManager.regenhealth.contains(p)) {
					timeregen = 150;
					WynnPlayerManager.regenhealth.add(p);
					r.createTaskTimer(TaskType.SYNC, p.getName()+"-regenhealth", new Runnable() {
						@Override
						public void run() {
							timeregen = timeregen - 1;
							if(a.getHealth() > a.getMaxhealth()) {
								a.setHealth(a.getMaxhealth());
							}
							else {
								int vida = a.getHealth();
								a.setHealth(vida+2);
							}
							
							if(percent(a.getHealth(), a.getMaxhealth()) == 100) {
								WynnPlayerManager.regenhealth.remove(p);
								r.cancel(p.getName()+"-regenhealth");
							}
							if(timeregen <= 0) {
								WynnPlayerManager.regenhealth.remove(p);
								r.cancel(p.getName()+"-regenhealth");
							}
						}
					}, 0, 5 * 20);
				}
			}
		}
	}
	
	public static void manaregen(Player p) {
		WynnPlayer pw = WynnPlayerManager.getWynnPlayer(p);
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			SimpleRunnable r = SimpleRunnable.getInstance();
			if(percent(a.getMana(), 20) < 15) {
				if(!WynnPlayerManager.regenmana.contains(p)) {
							timeregen = 150;
							WynnPlayerManager.regenmana.add(p);
							r.createTaskTimer(TaskType.SYNC, p.getName()+"-regenmana", new Runnable() {
								@Override
								public void run() {
									timeregen = timeregen - 1;
									if(a.getMana() > 20) {
										a.setMana(20);
										r.cancel(p.getName()+"-regenmana");
									}
									else {
										int mana = a.getMana();
										a.setMana(mana+2);
									}
									
									if(percent(a.getMana(), 20) == 100) {
										WynnPlayerManager.regenmana.remove(p);
										r.cancel(p.getName()+"-regenmana");
									}
									if(timeregen <= 0) {
										WynnPlayerManager.regenmana.remove(p);
										r.cancel(p.getName()+"-regenmana");
									}
								}
							}, 0, 5 * 20);	
				}
			}
		}
	}
	
	public static int percent(int currentValue, int maxValue){
        float percent = (currentValue *100/maxValue);
        return (int)percent;
	}
	
	public static void update() {
		Bukkit.getOnlinePlayers().forEach(v ->{
			WynnPlayer pw = WynnPlayerManager.getWynnPlayer(v);
			if(pw.getCurrentAccount() != null) {
				vida(v);
				fixvida(v);
				healthregen(v);
				fixmana(v);
				manaregen(v);
			}
		});
	}
}
