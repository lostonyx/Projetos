package rpg.system;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;

import rpg.system.event.JogadorLevelUpEvent;
import rpg.system.models.Account;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.utils.Cooldown;
import rpg.utils.ItemAPI;

public class Metodos {
	
	public static HashMap<Player, String> msgaction = Maps.newHashMap();
	
	public static void darxp(int quantidade, Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			int resto = a.getXp() + quantidade;
			if(resto >= a.getMaxxp()) {
				Bukkit.getPluginManager().callEvent(new JogadorLevelUpEvent(p));
				a.setLevel(a.getLevel()+1);
				a.setXp(0);
			}
			else {
				a.setXp(resto);
			}
		}
	}
	
	public static void usarmana(int quantidade, Player p) {
		Jogador pw = JogadorManager.getWynnPlayer(p);
		String cooldownName = "delay-subnoti";
		int timeInSeconds = 4;
		Cooldown c = new Cooldown(p.getUniqueId(), cooldownName, timeInSeconds);
		
		if(pw.getCurrentAccount() != null) {
			Account a = pw.getCurrentAccount();
			int resto = a.getMana() - quantidade;
			if(resto <= 0) {
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
	
	public static void notificarpeloitem(Player p, String msg) {
		String cooldownName = "delay-subnoti";
		int timeInSeconds = 4;
		Cooldown c = new Cooldown(p.getUniqueId(), cooldownName, timeInSeconds);
		if (!c.isInCooldown(cooldownName)) {
			c.start();
			notification.notificarpeloitem(msg, p);
		}
	}
	
	public static void deslogar(Player p) {
		Jogador w = JogadorManager.getWynnPlayer(p);
		if(w.getCurrentAccount() != null) {
			Account perfil = w.getCurrentAccount();
			perfil.setInv(p.getInventory());
			perfil.setLastLocSeri(p.getLocation());
			w.update();
			//JogadorManager.remove(w); boa inteligente
			p.getInventory().setHelmet(new ItemStack (Material.AIR));
            p.getInventory().setChestplate(new ItemStack (Material.AIR));
            p.getInventory().setLeggings(new ItemStack (Material.AIR));
            p.getInventory().setBoots(new ItemStack (Material.AIR));
			p.getInventory().clear();
			Jogador.scores.remove(p);
			w.setCurrentAccount(null);
		}
	}
	
	public static void damageEntity(Entity damager, Entity entity, double damage) {
        if (!(entity instanceof Damageable)) {
            return;
        }
        Damageable d = (Damageable)entity;
        d.damage(damage);
    }
	
	
	public static Material iconeclasse(String classe) {
		if("Archer".equals(classe)) {
			return Material.BOW;
		}
		if("Assassin".equals(classe)) {
			return Material.SHEARS;
		}
		if("Mage".equals(classe)) {
			return Material.STICK;
		}
		if("Warrior".equals(classe)) {
			return Material.IRON_SPADE;
		}
		return null;
	}
	
	public static void kitstarter(Player p,Account a) {
		p.sendMessage("1");
		if(a != null) {
			p.sendMessage("2");
			p.sendMessage(a.getClassp());
			if("Archer".equals(a.getClassp())) {
				ItemStack item1 = Lores.rpgItem(Material.BOW, "§aArco Comum", new String[] { 
						"§aAtributos:",
						"",
						"§a* §7Ataque Geral: §f" + 200,
						"§a* §7Defesa Geral: §f" + 350,
						"§a* §7Agilidade: §f" + 50,
						"§a* §7Dextreza: §f" + (-2),
						"§a* §7Contra Ataque: §f" + 500,
						"",
						"§2* §7Level Minimo: §f" + 1,
						"§2* §7Classe Requerida: §fArcher",
						""
				});
				p.getInventory().addItem(item1);
			}
			if("Assassin".equals(a.getClassp())) {
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
				
				ItemStack item7 = Lores.rpgItem(Material.WOOL, "§aKatana Comum", new String[] { 
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
				item7.setDurability((short) 9);
				p.getInventory().addItem(item , item2, item3, item4, item5, item6,item7);
			}
			if("".equals(a.getClassp())) {
				
			}
			if("".equals(a.getClassp())) {
				
			}
		}
	}

}
