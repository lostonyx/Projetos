package me.wiljafor1.system;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.wiljafor1.items.Lores;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.utils.Cooldown;

public class Metados {

	
	
	public static void usarmana(int quantidade, Player p) {
		WynnPlayer pw = WynnPlayerManager.getWynnPlayer(p);
		String cooldownName = "delay-subnoti";
		int timeInSeconds = 4;
		Cooldown c = new Cooldown(p.getUniqueId(), cooldownName, timeInSeconds);
		
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			int resto = a.getMana() - quantidade;
			if(resto <= 1) {
				if (!c.isInCooldown(cooldownName)) {
					c.start();
					notification.notificarpeloitem("§cVocê nao tem mana suficiente para executar!", p);
				}
				a.setMana(a.getMana());
			}
			else {
				if (!c.isInCooldown(cooldownName)) {
					c.start();
					notification.notificarpeloitem("§cAcabou de usar §b\u2739 [- "+quantidade+"]", p);
				}
				a.setMana(resto);
			}
		}
	}
	
	public static void deslogar(Player p) {
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		if(w.getCurrentAccount() != null) {
			Account perfil = w.getCurrentAccount();
			perfil.setInv(p.getInventory());
			perfil.setLastLocSeri(p.getLocation());
			w.update();
			WynnPlayerManager.remove(w);
			p.getInventory().clear();
			WynnPlayer.scores.remove(p);
			w.setCurrentAccount(null);
		}
	}
	
	public static void kitstarter(Player p) {
		
		ItemStack item = Lores.rpgItem(Material.IRON_SWORD, "§bKatana Selada", new String[] { 
				"§bAtributos:",
				"",
				"§b* §7Ataque Geral: §f" + 200,
				"§b* §7Defesa Geral: §f" + 350,
				"§b* §7Agilidade: §f" + 50,
				"§b* §7Dextreza: §f" + (-2),
				"§b* §7Contra Ataque: §f" + 500,
				"",
				"§9* §7Level Minimo: §f" + 1,
				"§9* §7Classe Requerida: §fAssassin",
				"",
				"§bItem Lendario: §8Esta arma foi selada",
				"§8a 300 anos atraz por um samurai",
				"§8que sacrificou sua vida para selar o poder",
				"§8imensuravel desta espada"
		});
		
		
		ItemStack item2 = Lores.rpgItem(Material.IRON_SWORD, "§aKatana Comum", new String[] { 
				"§aAtributos:",
				"",
				"§a* §7Ataque Geral: §f" + 200,
				"§a* §7Defesa Geral: §f" + 350,
				"§a* §7Agilidade: §f" + 50,
				"§a* §7Dextreza: §f" + (-2),
				"§a* §7Contra Ataque: §f" + 500,
				"",
				"§2* §7Level Minimo: §f" + 1,
				"§2* §7Classe Requerida: §fAssassin",
				""
		});
		
		ItemStack item3 = Lores.rpgItem(Material.CHAINMAIL_HELMET, "§aEquipamento Comum", new String[] { 
				"§aAtributos:",
				"",
				"§4\u2764 §4Vida: §f+" + 2,
				""
		});
		ItemStack item4 = Lores.rpgItem(Material.CHAINMAIL_CHESTPLATE, "§aEquipamento Comum", new String[] { 
				"§aAtributos:",
				"",
				"§4\u2764 §4Vida: §f+" + 5,
				""
		});
		ItemStack item5 = Lores.rpgItem(Material.CHAINMAIL_LEGGINGS, "§aEquipamento Comum", new String[] { 
				"§aAtributos:",
				"",
				"§4\u2764 §4Vida: §f+" + 3,
				""
		});
		ItemStack item6 = Lores.rpgItem(Material.CHAINMAIL_BOOTS, "§aEquipamento Comum", new String[] { 
				"§aAtributos:",
				"",
				"§4\u2764 §4Vida: §f+" + 6,
				""
		});
		p.getInventory().addItem(item , item2, item3, item4, item5, item6);
	}
}
