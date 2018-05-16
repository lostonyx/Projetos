package rpg.system.spell.archer;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.slikey.effectlib.util.ParticleEffect;
import net.md_5.bungee.api.ChatColor;
import rpg.Main;
import rpg.system.Metodos;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellType;
import rpg.utils.ArrowUtils;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class Storm implements Spell{
	
	int i;
	
	@Override
	public void cast(Jogador j) {
		 Player p = j.getPlayer();
		 p.sendMessage("skill 1");
		 if(j.getCurrentAccount().getLevel() >= 36) {
			 i = 20;
			 if (j.getCurrentAccount().getMana() > getManaCost()) {
	             p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
	             if (getManaCost() > 0) {
	                 p.sendMessage("§c-" + getManaCost() + " de mana");
	             }
	             Metodos.usarmana(getManaCost(), p);
	             SimpleRunnable r = new SimpleRunnable().getInstance();
	             r.createTaskTimer(TaskType.SYNC, p.getName()+"-Storm", new Runnable() {
					@Override
					public void run() {
						--i;
				        if (i <= 0) {
				           	r.cancel(p.getName()+"-Storm");
				        }
				        else {
				            p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 100.0f, 0.0f);
				            ArrowUtils.spawnTripleArrowEffected(j, ParticleEffect.FLAME, false);
				        }
					}
				}, 0L, 2L);
	         }
			 else {
		        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
		            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
			 }
		 }
		 else if(j.getCurrentAccount().getLevel() >= 16) {
			 i = 20;
			 if (j.getCurrentAccount().getMana() > getManaCost()) {
	             p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
	             if (getManaCost() > 0) {
	                 p.sendMessage("§c-" + getManaCost() + " de mana");
	             }
	             Metodos.usarmana(getManaCost(), p);
	             SimpleRunnable r = new SimpleRunnable().getInstance();
	             r.createTaskTimer(TaskType.SYNC, p.getName()+"-Storm", new Runnable() {
					@Override
					public void run() {
						--i;
				        if (i <= 0) {
				           	r.cancel(p.getName()+"-Storm");
				        }
				        else {
				            p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 100.0f, 0.0f);
				            ArrowUtils.spawnArrowEffected(j, ParticleEffect.SPELL_WITCH, false);
				        }
					}
				}, 0L, 2L);
	         }
			 else {
		        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
		            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
			 }   
		 }
		 else if(j.getCurrentAccount().getLevel() >= 1) {
			 i = 10;
			 if (j.getCurrentAccount().getMana() > getManaCost()) {
	             p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
	             if (getManaCost() > 0) {
	                 p.sendMessage("§c-" + getManaCost() + " de mana");
	             }
	             Metodos.usarmana(getManaCost(), p);
	             SimpleRunnable r = new SimpleRunnable().getInstance();
	             r.createTaskTimer(TaskType.SYNC, p.getName()+"-Storm", new Runnable() {
					@Override
					public void run() {
						--i;
				        if (i <= 0) {
				           	r.cancel(p.getName()+"-Storm");
				        }
				        else {
				            p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 100.0f, 0.0f);
				            ArrowUtils.spawnArrowEffected(j, ParticleEffect.SPELL_WITCH, false);
				        }
					}
				}, 0L, 2L);
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
		return "Storm";
	}

	@Override
	public List<Integer> getCombo() {
		return Arrays.asList(0, 1, 0);
	}

	@Override
	public int getLevelMin() {
		return 1;
	}

	@Override
	public int getManaCost() {
		return 5;
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
