package rpg.system.spell.assasin;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.ParticleEffect;
import net.md_5.bungee.api.ChatColor;
import rpg.Main;
import rpg.system.Metodos;
import rpg.system.listener.MobListener;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellType;

public class Spin implements Spell {

	@Override
	public void cast(Jogador j) {
        Player p = j.getPlayer();
        if(j.getCurrentAccount().getLevel() >= 1) {
        	if (j.getCurrentAccount().getMana() > getManaCost()) {
                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
                Metodos.usarmana(getManaCost(), p);
                CircleEffect ce = new CircleEffect(Main.getEffManager());
                ce.setEntity((Entity)p);
                ce.radius = 1.2f;
                ce.particle = ParticleEffect.CRIT_MAGIC;
                ce.iterations = 20;
                ce.start();
                p.getWorld().playSound(p.getLocation(), Sound.ZOMBIE_WOODBREAK, 10.0f, 10.0f);
                p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, p.getLocation().getBlock().getType());
                Entity entity;
                p.getNearbyEntities(5.0, 5.0, 5.0).stream().filter(e -> e instanceof Damageable).map(e -> e).forEach(e -> {
                    LineEffect le = new LineEffect(Main.getEffManager());
                    le.setLocation(p.getLocation());
                    le.setTargetEntity(e);
                    le.iterations = 1;
                    le.start();
                	e.setVelocity(e.getLocation().getDirection().multiply(-2).setY(0.5));
                    Damageable d = (Damageable) e;
                    Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(p, e, DamageCause.ENTITY_ATTACK, (j.getCurrentAccount().getLevel() + j.getCurrentAccount().getAttackdamage()) / 2));
                    //d.damage((j.getCurrentAccount().getLevel() + j.getCurrentAccount().getAttackdamage()) / 2);
                    //DamageTracker.damageEntity(entity, (Entity)e, (double)((j.getLevel() + j.getAtk()) / 2));
                });
            }
	        else {
	        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
	        } 
		}
        else if(j.getCurrentAccount().getLevel() >= 16) {
			if (j.getCurrentAccount().getMana() > getManaCost()) {
				
	        }
	        else {
	        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
	        } 
		}
		else if(j.getCurrentAccount().getLevel() >= 36) {
			if (j.getCurrentAccount().getMana() > getManaCost()) {
				
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
		return "Spin";
	}

	@Override
	public List<Integer> getCombo() {
		return Arrays.asList(1, 0, 1);
	}

	@Override
	public int getLevelMin() {
		return 1;
	}

	@Override
	public int getManaCost() {
		return 0;
	}

	@Override
	public Classe getClasse() {
		return ClasseManager.getClasse("Assassin");
	}

	@Override
	public SpellType getType() {
		return SpellType.ACTIVE;
	}

}
