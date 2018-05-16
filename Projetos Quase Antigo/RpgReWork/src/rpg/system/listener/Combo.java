package rpg.system.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import rpg.Main;
import rpg.mob.Mobs;
import rpg.system.Lores;
import rpg.system.Metodos;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellManager;
import rpg.utils.ActionBar;
import rpg.utils.Cooldown;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

import java.util.*;

public class Combo implements Listener{

	public static HashMap<Player, List<Integer>> ints = new HashMap<>();
    public static HashMap<Player, Long> longs = new HashMap<>();
    public static HashMap<Player, ItemStack[]> armorsave = new HashMap<Player, ItemStack[]>();
    
    public static void doAction(final Runnable r, final int time) {
        Bukkit.getScheduler().runTaskLater(Main.GetInstance(), r, (20 * time));
    }
    
    public String combo(int i) {
        if (i == 0) {
            return "E";
        }
        return "D";
    }
    
    public int action(String a) {
        if (a.contains("LEFT")) {
            return 0;
        }
        return 1;
    }
    
    public boolean isCombo(List<Integer> list, List<Integer> lis) {
        for (int i = 0; i < 2; ++i) {
            if (list.get(i).equals(lis.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Jogador j = JogadorManager.getWynnPlayer(player);
        UUID id = player.getUniqueId();
        SimpleRunnable r = new SimpleRunnable().getInstance();
        if (j.getCurrentAccount().getClassp().equalsIgnoreCase("Archer")) {//archer
            if (event.getAction().name().contains("RIGHT") && j.getCurrentAccount().getClassp().equals(Lores.getValue(player.getItemInHand(), "Classe Requerida")) && (ints.get(player) == null || ints.get(player).isEmpty())) {
                Cooldown c = new Cooldown(id, "flecha-"+player.getName(), 1);
                if (!c.isInCooldown("flecha-"+player.getName())) {
                	c.start();
                	player.playSound(player.getLocation(), Sound.SHOOT_ARROW, 1.0f, 1.0f);
                	Arrow a = player.launchProjectile(Arrow.class);
                	a.setVelocity(player.getLocation().getDirection().multiply(3));
                	a.setShooter(player);
                }
            }
            else if (j.getCurrentAccount().getClassp().equals(Lores.getValue(player.getItemInHand(), "Classe Requerida"))) {
            	if (ints.get(player) == null) {
                    List<Integer> intes = Lists.newArrayList();
                    intes.add(action(event.getAction().name()));
                    ints.put(player, intes);
                    Metodos.msgaction.put(player, "§6" + this.combo(ints.get(player).get(0)) + "§7-§6?§7-§6?");
                    player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 10.0f);
                    r.cancel(player.getName()+"-remove");
                    r.createTaskLater(TaskType.SYNC, player.getName()+"-remove", new Runnable() {
						@Override
						public void run() {
							if(ints.containsKey(player)) {
								if(ints.get(player).size() == 1) {
									ints.remove(player);
				                    Metodos.msgaction.remove(player);
								}
							}
						}
					}, 20 * 6);
                }
                else if (ints.get(player).size() > 1) {
                    ints.get(player).add(this.action(event.getAction().name()));
                    Metodos.msgaction.put(player, "§6" + this.combo(ints.get(player).get(0)) + "§7-§6" + combo(ints.get(player).get(1)) + "§7-§6" + combo(ints.get(player).get(2)));
                    player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 10.0f);
                    r.cancel(player.getName()+"-remove");
                    for (Spell s : SpellManager.spells) {
                        if (s.getClasse().getName().equalsIgnoreCase(j.getCurrentAccount().getClassp()) && s.getCombo().equals(ints.get(player))) {
                            s.cast(j);
                            break;
                        }
                    }
                    ints.remove(player);
                    r.createTaskLater(TaskType.SYNC, player.getName()+"-remove", new Runnable() {
						@Override
						public void run() {
							Metodos.msgaction.remove(player);
						}
					}, 20);
                }
                else {
                    ints.get(player).add(this.action(event.getAction().name()));
                    Metodos.msgaction.put(player, "§6" + this.combo(ints.get(player).get(0)) + "-" + this.combo(ints.get(player).get(1)) + "-?");
                    player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 10.0f);
                    r.cancel(player.getName()+"-remove");
                    r.createTaskLater(TaskType.SYNC, player.getName()+"-remove", new Runnable() {
						@Override
						public void run() {
							if(ints.containsKey(player)) {
								if(ints.get(player).size() == 2) {
									ints.remove(player);
				                    Metodos.msgaction.remove(player);
								}
							}
						}
					}, 20 * 6);
                }
            }
        }
        if (j.getCurrentAccount().getClassp().equalsIgnoreCase("Assassin")) {
            if (event.getAction().name().contains("LEFT") && j.getCurrentAccount().getClassp().equals(Lores.getValue(player.getItemInHand(), "Classe Requerida")) && (ints.get(player) == null || ints.get(player).isEmpty())) {
                final Cooldown c = new Cooldown(id, "basic-attack-"+player.getName(), 2);
                if (!c.isInCooldown("basic-attack-"+player.getName())) {
                	c.start();
                    player.getNearbyEntities(3.0, 3.0, 1.0).stream().filter(e -> e instanceof Damageable).map(e -> e).forEach(e -> {
                        e.setVelocity(e.getLocation().getDirection().multiply(-2).setY(0.5));
                        Damageable d = (Damageable) e;
                        Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(player, e, DamageCause.ENTITY_ATTACK, (j.getCurrentAccount().getLevel() + j.getCurrentAccount().getAttackdamage()) / 4));
                        //d.damage((j.getCurrentAccount().getLevel() + j.getCurrentAccount().getAttackdamage()) / 4);
                        //DamageTracker.damageEntity(entity, (Entity)e, (double)((jogador.getLevel() + jogador.getAtk()) / 4));
                    }); 
                }
            }
            else if (j.getCurrentAccount().getClassp().equals(Lores.getValue(player.getItemInHand(), "Classe Requerida"))) {
            	if (ints.get(player) == null) {
                    List<Integer> intes = Lists.newArrayList();
                    intes.add(action(event.getAction().name()));
                    ints.put(player, intes);
                    Metodos.msgaction.put(player, "§6" + this.combo(ints.get(player).get(0)) + "§7-§6?§7-§6?");
                    player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 10.0f);
                    r.cancel(player.getName()+"-remove");
                    r.createTaskLater(TaskType.SYNC, player.getName()+"-remove", new Runnable() {
						@Override
						public void run() {
							if(ints.containsKey(player)) {
								if(ints.get(player).size() == 1) {
									ints.remove(player);
				                    Metodos.msgaction.remove(player);
								}
							}
						}
					}, 20 * 6);
                }
                else if (ints.get(player).size() > 1) {
                    ints.get(player).add(this.action(event.getAction().name()));
                    Metodos.msgaction.put(player, "§6" + this.combo(ints.get(player).get(0)) + "§7-§6" + combo(ints.get(player).get(1)) + "§7-§6" + combo(ints.get(player).get(2)));
                    player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 10.0f);
                    r.cancel(player.getName()+"-remove");
                    for (final Spell s : SpellManager.spells) {
                        if (s.getClasse().getName().equalsIgnoreCase(j.getCurrentAccount().getClassp()) && s.getCombo().equals(ints.get(player))) {
                            s.cast(j);
                            break;
                        }
                    }
                    ints.remove(player);
                    r.createTaskLater(TaskType.SYNC, player.getName()+"-remove", new Runnable() {
						@Override
						public void run() {
							Metodos.msgaction.remove(player);
						}
					}, 20);
                }
                else {
                    ints.get(player).add(this.action(event.getAction().name()));
                    Metodos.msgaction.put(player, "§6" + this.combo(ints.get(player).get(0)) + "-" + this.combo(ints.get(player).get(1)) + "-?");
                    player.playSound(player.getLocation(), Sound.CLICK, 10.0f, 10.0f);
                    r.cancel(player.getName()+"-remove");
                    r.createTaskLater(TaskType.SYNC, player.getName()+"-remove", new Runnable() {
						@Override
						public void run() {
							if(ints.containsKey(player)) {
								if(ints.get(player).size() == 2) {
									ints.remove(player);
				                    Metodos.msgaction.remove(player);
								}
							}
						}
					}, 20 * 6);
                }
            }
        }
    }
    
}
