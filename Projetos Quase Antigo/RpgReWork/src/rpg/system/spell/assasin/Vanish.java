package rpg.system.spell.assasin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.effect.SmokeEffect;
import de.slikey.effectlib.util.ParticleEffect;
import net.md_5.bungee.api.ChatColor;
import rpg.Main;
import rpg.system.Metodos;
import rpg.system.listener.Combo;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellType;
import rpg.utils.Cooldown;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class Vanish implements Spell {
	int time;
	
	@Override
	public void cast(Jogador j) {
        Player p = j.getPlayer();
        if(j.getCurrentAccount().getLevel() >= 46) {
        	time = 16;
        	Cooldown c = new Cooldown(p.getUniqueId(), p.getName()+"vanish", time+1);
        	if(!c.isInCooldown(p.getName()+"vanish")) {
        		c.start();
        		if (j.getCurrentAccount().getMana() > getManaCost()) {
                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
                    PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 20000, 0);
                    PotionEffect effects = new PotionEffect(PotionEffectType.SPEED, 20000, 0);
                    p.addPotionEffect(effect);
                    p.addPotionEffect(effects);
                    Vector v1 = p.getLocation().getDirection().setY(0.8);
    				Vector v2 = p.getLocation().getDirection().multiply(0.5);
    				p.setVelocity(v1.add(v2));
                    Combo.armorsave.put(p, p.getInventory().getArmorContents());
                    p.getInventory().setArmorContents(null);
                    Metodos.usarmana(getManaCost(), p);
                    for (Player players : Bukkit.getOnlinePlayers())
                    {
                        players.hidePlayer(p);
                    }
                    SimpleRunnable r = SimpleRunnable.getInstance();
                    r.createTaskTimer(TaskType.SYNC, p.getName()+"-skillvanish", new Runnable() {
    					@Override
    					public void run() {
    						--time;
    						if (time <= 0) {
    							for (Player players : Bukkit.getOnlinePlayers())
    			                {
    			                    players.showPlayer(p);
    			                }
    							p.removePotionEffect(PotionEffectType.INVISIBILITY);
    							p.removePotionEffect(PotionEffectType.SPEED);
    							p.getInventory().setArmorContents(Combo.armorsave.get(p));
    				           	r.cancel(p.getName()+"-skillvanish");
    				        }else {
    				        	SmokeEffect se = new SmokeEffect(Main.getEffManager());
    				        	se.setLocation(p.getLocation().add(0, -2, 0));;
    				        	se.color = Color.BLACK;
    				        	se.start();
    				        }
    					}
    				}, 0, 20);
                }
    	        else {
    	        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
    	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
    	        } 
        	} 
		}
        else if(j.getCurrentAccount().getLevel() >= 26) {
        	time = 13;
        	Cooldown c = new Cooldown(p.getUniqueId(), p.getName()+"vanish", time+1);
        	if(!c.isInCooldown(p.getName()+"vanish")) {
        		c.start();
        		if (j.getCurrentAccount().getMana() > getManaCost()) {
                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
                    PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 20000, 0);
                    p.addPotionEffect(effect);
                    Vector v1 = p.getLocation().getDirection().setY(0.5);
    				Vector v2 = p.getLocation().getDirection().multiply(0.3);
    				p.setVelocity(v1.add(v2));
                    Combo.armorsave.put(p, p.getInventory().getArmorContents());
                    p.getInventory().setArmorContents(null);
                    Metodos.usarmana(getManaCost(), p);
                    for (Player players : Bukkit.getOnlinePlayers())
                    {
                        players.hidePlayer(p);
                    }
                    SimpleRunnable r = SimpleRunnable.getInstance();
                    r.createTaskTimer(TaskType.SYNC, p.getName()+"-skillvanish", new Runnable() {
    					@Override
    					public void run() {
    						--time;
    						if (time <= 0) {
    							for (Player players : Bukkit.getOnlinePlayers())
    			                {
    			                    players.showPlayer(p);
    			                }
    							p.removePotionEffect(PotionEffectType.INVISIBILITY);
    							p.getInventory().setArmorContents(Combo.armorsave.get(p));
    				           	r.cancel(p.getName()+"-skillvanish");
    				        }else {
    				        	SmokeEffect se = new SmokeEffect(Main.getEffManager());
    				        	se.setLocation(p.getLocation().add(0, -2, 0));;
    				        	se.color = Color.BLACK;
    				        	se.start();
    				        }
    					}
    				}, 0, 20);
                }
    	        else {
    	        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
    	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
    	        } 
        	}
		}
        else if(j.getCurrentAccount().getLevel() >= 11) {
        	time = 10;
        	Cooldown c = new Cooldown(p.getUniqueId(), p.getName()+"vanish", time+1);
        	if(!c.isInCooldown(p.getName()+"vanish")) {
        		c.start();
        		if (j.getCurrentAccount().getMana() > getManaCost()) {
                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10.0f, 10.0f);
                    PotionEffect effect = new PotionEffect(PotionEffectType.INVISIBILITY, 20000, 0);
                    p.addPotionEffect(effect);
                    Vector v1 = p.getLocation().getDirection().setY(0.5);
    				Vector v2 = p.getLocation().getDirection().multiply(0.3);
    				p.setVelocity(v1.add(v2));
                    Combo.armorsave.put(p, p.getInventory().getArmorContents());
                    p.getInventory().setArmorContents(null);
                    Metodos.usarmana(getManaCost(), p);
                    for (Player players : Bukkit.getOnlinePlayers())
                    {
                        players.hidePlayer(p);
                    }
                    SimpleRunnable r = SimpleRunnable.getInstance();
                    r.createTaskTimer(TaskType.SYNC, p.getName()+"-skillvanish", new Runnable() {
    					@Override
    					public void run() {
    						--time;
    						if (time <= 0) {
    							for (Player players : Bukkit.getOnlinePlayers())
    			                {
    			                    players.showPlayer(p);
    			                }
    							p.removePotionEffect(PotionEffectType.INVISIBILITY);
    							p.getInventory().setArmorContents(Combo.armorsave.get(p));
    				           	r.cancel(p.getName()+"-skillvanish");
    				        }else {
    				        	SmokeEffect se = new SmokeEffect(Main.getEffManager());
    				        	se.setLocation(p.getLocation().add(0, -2, 0));;
    				        	se.color = Color.BLACK;
    				        	se.start();
    				        }
    					}
    				}, 0, 20);
                }
    	        else {
    	        	p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
    	            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
    	        } 
        	}
		}
		else {
			p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 10.0f, 10.0f);
			p.sendMessage(ChatColor.RED + "Voce nao possui nivel minimo para usar esta habilidade.");
		} 
	}

	@Override
	public String getName() {
		return "Vanish";
	}

	@Override
	public List<Integer> getCombo() {
		return Arrays.asList(1, 1, 1);
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
		return ClasseManager.getClasse("Assassin");
	}

	@Override
	public SpellType getType() {
		return SpellType.ACTIVE;
	}

}
