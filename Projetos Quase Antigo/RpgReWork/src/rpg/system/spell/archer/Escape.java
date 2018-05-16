package rpg.system.spell.archer;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import de.slikey.effectlib.util.ParticleEffect;
import net.md_5.bungee.api.ChatColor;
import rpg.system.Metodos;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellType;
import rpg.utils.ArrowUtils;

public class Escape implements Spell {

	@Override
	public void cast(Jogador j) {
		Player p = j.getPlayer();
		p.sendMessage("skill 3");
		if(j.getCurrentAccount().getLevel() >= 46) {
			if (j.getCurrentAccount().getMana() > getManaCost()) {
				p.sendMessage("nivel 3");
				Metodos.usarmana(getManaCost(), p);
				ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 8258);
				ThrownPotion entity = (ThrownPotion)p.getWorld().spawnEntity(p.getLocation(), EntityType.SPLASH_POTION);
				entity.setItem(itemStack);
				Vector v1 = p.getLocation().getDirection().setY(0.5);
				Vector v2 = p.getLocation().getDirection().multiply(-3);
				p.setVelocity(v1.add(v2)); 
	        }
			else {
				p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
	        } 
		}
		else if(j.getCurrentAccount().getLevel() >= 26) {
			if (j.getCurrentAccount().getMana() > getManaCost()) {
				p.sendMessage("nivel 2");
				Metodos.usarmana(getManaCost(), p);
				ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 8226);
				ThrownPotion entity = (ThrownPotion)p.getWorld().spawnEntity(p.getLocation(), EntityType.SPLASH_POTION);
				entity.setItem(itemStack);
				Vector v1 = p.getLocation().getDirection().setY(0.5);
				Vector v2 = p.getLocation().getDirection().multiply(-3);
				p.setVelocity(v1.add(v2)); 
	        }
			else {
				p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
	        } 
		}
		else if(j.getCurrentAccount().getLevel() >= 11) {
			if (j.getCurrentAccount().getMana() > getManaCost()) {
				p.sendMessage("nivel 1");
				ItemStack itemStack = new ItemStack(Material.POTION);
				ThrownPotion entity = (ThrownPotion)p.getWorld().spawnEntity(p.getLocation(), EntityType.SPLASH_POTION);
				entity.setItem(itemStack);
				Metodos.usarmana(getManaCost(), p);
				Vector v1 = p.getLocation().getDirection().setY(0.5);
				Vector v2 = p.getLocation().getDirection().multiply(-3);
				p.setVelocity(v1.add(v2)); 
	        }
	        else {
	        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
	        } 
		}
		else {
			p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
			p.sendMessage(ChatColor.RED + "Voce nao possui nivel minimo para usar esta habilidade.");
		}  
	}

	@Override
	public String getName() {
		return "Escape";
	}

	@Override
	public List<Integer> getCombo() {
		return Arrays.asList(0, 0, 0);
	}

	@Override
	public int getLevelMin() {
		return 11;
	}

	@Override
	public int getManaCost() {
		return 0;
	}

	@Override
	public Classe getClasse() {
		return ClasseManager.getClasse("Archer");
	}

	@Override
	public SpellType getType() {
		return SpellType.ACTIVE;
	}

}
