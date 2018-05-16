package rpg.system.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

import static rpg.mob.MobManager.*;
import static rpg.system.models.RegionManager.*;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import rpg.mob.MobDisPlayer;
import rpg.mob.MobManager;
import rpg.mob.MobTrans;
import rpg.mob.Mobs;
import rpg.mob.Mobs.Tipo;
import rpg.mob.type.A;
import rpg.mob.type.B;
import rpg.mob.type.D;
import rpg.mob.type.A.Mode;
import rpg.system.Metodos;
import rpg.system.models.Account;
import rpg.system.models.City;
import rpg.system.models.Dungeon;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.models.MobCache;
import rpg.system.models.Region;
import rpg.system.models.RegionManager;
import rpg.utils.CItem;
import rpg.utils.Cooldown;
import rpg.utils.HCStrings;
import rpg.utils.HologramApi;
import rpg.utils.ItemAPI;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class MobListener implements Listener {
	public static Map<Chunk, Boolean> carregado = Maps.newHashMap();
	public static Map<Damageable, ArmorStand[]> holodata = Maps.newHashMap();
	public static Map<Damageable, Double> moblife = Maps.newHashMap();
	public static Map<Damageable, Tipo> holotype = Maps.newHashMap();
	public static Map<Damageable, String> holomob = Maps.newHashMap();
	public static Map<Damageable, Location> guardloc = Maps.newHashMap();
	public static Map<ArmorStand, Damageable> holotemp = Maps.newHashMap();

	public static void follow() {
		SimpleRunnable r = SimpleRunnable.getInstance();
		for (Entry<Damageable, ArmorStand[]> set : holodata.entrySet()) {
			Damageable d = set.getKey();
			ArmorStand[] a = set.getValue();
			if (d != null) {
				if (d.isValid()) {
					if (d.isDead()) {
						a[0].remove();
						a[1].remove();
						holodata.remove(d);
						holotype.remove(d);
						moblife.remove(d);
						holomob.remove(d);
					} else {
						if (holotype.get(d).equals(Tipo.A)) {
							if (d.getType().equals(EntityType.IRON_GOLEM)) {
								a[0].teleport(d.getLocation().add(0, 2.3, 0));
							}
						}
						if (holotype.get(d).equals(Tipo.B)) {
							Double vida = moblife.get(d);
							if (a.length == 1) {
								a[0].teleport(d.getLocation().add(0, 2.5, 0));
							} else {
								a[1].teleport(d.getLocation().add(0, 2.2, 0));
								a[1].setCustomName("§4[§c:::§4" + vida.intValue() + "§c:::§4]");
								a[0].teleport(d.getLocation().add(0, 2.5, 0));
							}
						}
						if (holotype.get(d).equals(Tipo.C)) {

						}
						if (holotype.get(d).equals(Tipo.D)) {
							if (d.getType().equals(EntityType.IRON_GOLEM)) {
								Double vida = moblife.get(d);
								if (a.length == 1) {
									a[0].teleport(d.getLocation().add(0, 2.5, 0));
								} else {
									a[1].teleport(d.getLocation().add(0, 2.2, 0));
									a[1].setCustomName("§4[§c:::§4" + vida.intValue() + "§c:::§4]");
									a[0].teleport(d.getLocation().add(0, 2.5, 0));
								}
							} else {
								Double vida = moblife.get(d);
								if (a.length == 1) {
									a[0].teleport(d.getLocation().add(0, 2.5, 0));
								} else {
									a[1].teleport(d.getLocation().add(0, 2.2, 0));
									a[1].setCustomName("§4[§c:::§4" + vida.intValue() + "§c:::§4]");
									a[0].teleport(d.getLocation().add(0, 2.5, 0));
								}
							}
						}
					}
				}
			}
		}
		for (Entry<ArmorStand, Damageable> set : holotemp.entrySet()) {
			Damageable d = set.getValue();
			ArmorStand a = set.getKey();
			if (d == null) {
				a.remove();
				r.createTaskLater(TaskType.SYNC, a.getEntityId() + "-" + d.getEntityId(), new Runnable() {
					@Override
					public void run() {
						if (holotype.get(d) != null) {
							holotype.remove(d);
							moblife.remove(d);
							holomob.remove(d);
						}
						if (holodata.get(d) != null) {
							holodata.remove(d);
							moblife.remove(d);
							holomob.remove(d);
						}
						holotemp.remove(a);
					}
				}, 2 * 20);
			} else if (d.isDead()) {
				a.remove();
				r.createTaskLater(TaskType.SYNC, a.getEntityId() + "-" + d.getEntityId(), new Runnable() {
					@Override
					public void run() {
						if (holotype.get(d) != null) {
							holotype.remove(d);
							moblife.remove(d);
							holomob.remove(d);
						}
						if (holodata.get(d) != null) {
							holodata.remove(d);
							holomob.remove(d);
							moblife.remove(d);
						}
						holotemp.remove(a);
					}
				}, 2 * 20);
			} else if (!d.isValid()) {
				a.remove();
				r.createTaskLater(TaskType.SYNC, a.getEntityId() + "-" + d.getEntityId(), new Runnable() {
					@Override
					public void run() {
						if (holotype.get(d) != null) {
							holotype.remove(d);
							moblife.remove(d);
							holomob.remove(d);
						}
						if (holodata.get(d) != null) {
							holodata.remove(d);
							moblife.remove(d);
							holomob.remove(d);
						}
						holotemp.remove(a);
					}
				}, 2 * 20);
			}
		}
	}

	public static void removemob(Entity e) {
		if (e != null) {
			if (holodata.get(e) != null) {
				if (holotype.get(e) != null) {
					if (holotype.get(e).equals(Tipo.A)) {
						ArmorStand[] l = holodata.get(e);
						l[0].remove();
						holotemp.remove(l[0]);
						holodata.remove(e);
						holotype.remove(e);
						holomob.remove(e);
						guardloc.remove(e);
						e.remove();
					}
					if (holotype.get(e).equals(Tipo.B)) {
						ArmorStand[] l = holodata.get(e);
						if (l.length == 1) {
							l[0].remove();
							holotemp.remove(l[0]);
							holodata.remove(e);
							holotype.remove(e);
							holomob.remove(e);
							e.remove();
						} else {
							l[0].remove();
							l[1].remove();
							holotemp.remove(l[0]);
							holotemp.remove(l[1]);
							holodata.remove(e);
							holomob.remove(e);
							holotype.remove(e);
							e.remove();
						}
					}
					if (holotype.get(e).equals(Tipo.D)) {
						ArmorStand[] l = holodata.get(e);
						if (l.length == 1) {
							l[0].remove();
							holotemp.remove(l[0]);
							holodata.remove(e);
							holotype.remove(e);
							holomob.remove(e);
							e.remove();
						} else {
							l[0].remove();
							l[1].remove();
							holotemp.remove(l[0]);
							holotemp.remove(l[1]);
							holodata.remove(e);
							holomob.remove(e);
							holotype.remove(e);
							e.remove();
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void dis(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.CUSTOM) {
			Entity en = e.getEntity();
			Location loc = en.getLocation();
			Mobs m = MobManager.getMob(en.getCustomName());
			if (m != null) {
				if (!loc.getChunk().isLoaded())
					loc.getChunk().load();
				if (m.tipo().equals(Tipo.A)) {
					A mob = (A) m;
					if (m.type().equals(EntityType.IRON_GOLEM)) {
						if (e.getEntityType().equals(m.type())) {
							if (mob.classe().equals(Mobs.CEntityCustom.Defesa)) {
								int vida = (int) ((IronGolem) en).getHealth();
								ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 2.5, 0.0),
										"§b" + mob.nameDisplay() + " §6§l[Lv. " + mob.Level() + "]");
								a.setMarker(true);
								a.setGravity(false);
								a.setSmall(true);
								en.setCustomNameVisible(true);
								en.setCustomNameVisible(false);
								en.setCustomName(" ");
								en.setCustomNameVisible(true);
								ArmorStand[] lista = new ArmorStand[] { a };
								if (mob instanceof MobDisPlayer) {
									MobDisPlayer mp = (MobDisPlayer) mob;
									PlayerDisguise dis = new PlayerDisguise("§4§l ");
									if (dis.isShowName()) {
										dis.setShowName(false);
									}
									if (mp.SkinName() != null) {
										dis.setSkin(mp.SkinName());
									}
									dis.getWatcher().setItemInHand(new CItem(Material.WOOD).build());
									dis.getWatcher()
											.setArmor(new ItemStack[] { new CItem(Material.LEATHER_LEGGINGS).build(),
													new CItem(Material.LEATHER_HELMET).build() });
									DisguiseAPI.disguiseEntity(en, dis);
								}
								holodata.put((Damageable) en, lista);
								holotype.put((Damageable) en, m.tipo());
								holotemp.put(a, (Damageable) en);
								holomob.put((Damageable) en, mob.name());
								guardloc.put((Damageable) en, en.getLocation());
								moblife.put((Damageable) en, (double) m.life());
							}
						}
					}
				} else if (m.tipo().equals(Tipo.B)) {
					B mob = (B) m;
					if (e.getEntityType().equals(m.type())) {
						ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 2.5, 0.0),
								"§a" + mob.nameDisplay() + " §6§l[Lv. " + mob.Level() + "]");
						a.setMarker(true);
						a.setGravity(false);
						a.setSmall(true);
						en.setCustomNameVisible(true);
						en.setCustomNameVisible(false);
						en.setCustomName(" ");
						en.setCustomNameVisible(true);
						ArmorStand[] lista = new ArmorStand[] { a };
						holodata.put((Damageable) en, lista);
						holotype.put((Damageable) en, m.tipo());
						holotemp.put(a, (Damageable) en);
						holomob.put((Damageable) en, mob.name());
						moblife.put((Damageable) en, (double) m.life());
						if (mob instanceof rpg.mob.MobDisguise) {
							rpg.mob.MobDisguise md = (rpg.mob.MobDisguise)mob;
							MobDisguise mobDisguise = new MobDisguise(md.dismob());
							DisguiseAPI.disguiseEntity(en, mobDisguise);
						} else if (mob instanceof MobDisPlayer) {
							MobDisPlayer mp = (MobDisPlayer)mob;
							PlayerDisguise dis = new PlayerDisguise("§4§l ");
							if (dis.isShowName()) {
								dis.setShowName(false);
							}
							// dis.getWatcher().addPotionEffect(PotionEffectType.HEAL);
							if (mp.SkinName() != null) {
								dis.setSkin(mp.SkinName());
							}
							DisguiseAPI.disguiseEntity(en, dis);
						}
					}
				} else if (m.tipo().equals(Tipo.C)) {

				} else if (m.tipo().equals(Tipo.D)) {
					D mob = (D) m;
					/*
					 * if (m.type().equals(EntityType.ZOMBIE)) { if
					 * (e.getEntityType().equals(m.type())) { Double vida = (Double) ((Zombie)
					 * en).getHealth(); ArmorStand a =
					 * HologramApi.createHologram(en.getLocation().add(0.0, 1.5, 0.0), "§c" +
					 * mob.nameDisplay() + " §6§l[Lv. " + mob.Level() + "]"); a.setMarker(true);
					 * a.setGravity(false); // a.setSmall(true); en.setCustomNameVisible(true);
					 * en.setCustomNameVisible(false); en.setCustomName(" ");
					 * en.setCustomNameVisible(true); ArmorStand[] lista = new ArmorStand[] { a };
					 * holodata.put((Damageable) en, lista); holotype.put((Damageable) en,
					 * m.tipo()); holotemp.put(a, (Damageable) en); holomob.put((Damageable) en,
					 * mob.name()); moblife.put((Damageable) en, (double) m.life()); MobDisguise
					 * mobDisguise = new MobDisguise(DisguiseType.ZOMBIE);
					 * DisguiseAPI.disguiseEntity(en, mobDisguise); } }
					 */
					if (e.getEntityType().equals(m.type())) {
						ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 2.5, 0.0),
								"§c" + mob.nameDisplay() + " §6§l[Lv. " + mob.Level() + "]");
						a.setMarker(true);
						a.setGravity(false);
						// a.setSmall(true);
						en.setCustomNameVisible(true);
						en.setCustomNameVisible(false);
						en.setCustomName(" ");
						en.setCustomNameVisible(true);
						ArmorStand[] lista = new ArmorStand[] { a };
						holodata.put((Damageable) en, lista);
						holotype.put((Damageable) en, m.tipo());
						holotemp.put(a, (Damageable) en);
						holomob.put((Damageable) en, mob.name());
						moblife.put((Damageable) en, (double) m.life());
						D d = mob;
						if(mob instanceof rpg.mob.MobDisguise) {
							rpg.mob.MobDisguise md =(rpg.mob.MobDisguise)mob;
							MobDisguise mobDisguise = new MobDisguise(md.dismob());
							DisguiseAPI.disguiseEntity(en, mobDisguise);	
						}
					}
				}
			}
		} else if (e.getSpawnReason() == SpawnReason.NATURAL) {
			Location l = e.getLocation();
			for (Region r : RegionManager.regions) {
				if (r.isCity() == true) {
					City c = (City) r;
					if (c.getSpawn().distanceSquared(l) < 300) {
						e.setCancelled(true);
						// Bukkit.broadcastMessage("Spawn N");
						if (!c.getRegion().containsLocation(l)) {
							int randomNum = 1 + (int) (Math.random() * c.mobsproximos().size());
							MobManager.spawn(c.mobsproximos().get(randomNum - 1), l, 1);
						}
					} else {
						e.setCancelled(true);
					}
				}
				else if(r.isDungeon() == true) {
					Dungeon c = (Dungeon) r;
					if (c.getRegion().containsLocation(e.getLocation())) {
						e.setCancelled(true);
						// Bukkit.broadcastMessage("Spawn N");
						if (!c.getRegion().containsLocation(l)) {
							int randomNum = 1 + (int) (Math.random() * c.mobsproximos().size());
							MobManager.spawn(c.mobsproximos().get(randomNum - 1), l, 1);
						}
					} else {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onEntityCombust(EntityCombustEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void effect(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		/*
		 * if(entity instanceof Zombie) { e.setCancelled(true); Damageable d =
		 * (Damageable)entity; Double dano = (moblife.get(d) -e.getDamage()); if(dano
		 * <=0 ) { d.remove(); moblife.remove(d); } else { moblife.put(d, dano);
		 * d.damage(0); d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0),
		 * Effect.STEP_SOUND, Material.REDSTONE_BLOCK); } }
		 */
		if (entity instanceof IronGolem) {
			if (damager instanceof Zombie) {
				e.setCancelled(true);
				damager.remove();
			} else {
				e.setCancelled(true);
				Damageable d = (Damageable) entity;
				Double dano = (moblife.get(d) - e.getDamage());
				if (dano <= 0) {
					d.remove();
					moblife.remove(d);
				} else {
					moblife.put(d, dano);
					d.damage(0);
					d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND,
							Material.REDSTONE_BLOCK);
				}
			}
		}
		/*
		 * else if(entity instanceof Villager) { if(damager instanceof Zombie) {
		 * e.setCancelled(true); } else { e.setCancelled(true); Damageable d =
		 * (Damageable)entity; if(moblife.get(d) != null) { Double dano =
		 * (moblife.get(d) -e.getDamage()); if(dano <= 0 ) { d.remove();
		 * moblife.remove(d); } else { moblife.put(d, dano); d.damage(0); } } else {
		 * for(Player v : Bukkit.getOnlinePlayers()) { if(v.isOp()) {
		 * v.sendMessage("Um mob deu algum problema");
		 * v.sendMessage("Location: "+e.getEntity().getLocation().toString()); } } } } }
		 */
		else {
			Damageable d = (Damageable) entity;
			Player killer = null;
			if (damager instanceof Arrow) {
				Arrow a = (Arrow) damager;
				killer = (Player) a.getShooter();
			}
			if (damager instanceof Player) {
				killer = (Player) damager;
			}
			if (killer != null) {
				if (moblife.get(d) != null) {
					Double dano = (moblife.get(d) - e.getDamage());
					if (dano <= 0) {
						SimpleRunnable r = SimpleRunnable.getInstance();
						if (holomob.get(d) != null) {
							Mobs m = MobManager.getMob(holomob.get(d));
							Jogador j = JogadorManager.getWynnPlayer(killer);
							if (j.getCurrentAccount() != null) {
								Account a = j.getCurrentAccount();
								int xp = new Random().nextInt(j.getCurrentAccount().getLevel() * 10);
								Metodos.darxp(xp, killer);
							}
							if (m instanceof MobTrans) {
								MobTrans mt = (MobTrans)m;
								MobManager.spawnEntity(mt.proxmob(), d.getLocation());
							}
							if (m.respawn() == true) {
								r.createTaskLater(TaskType.SYNC, d + "respawn", new Runnable() {
									@Override
									public void run() {
										MobManager.spawnEntity(m.name(), d.getLocation());
									}
								}, 20 * 60);
							}
						}
						d.remove();
						moblife.remove(d);
					} else {
						e.setCancelled(true);
						moblife.put(d, dano);
						d.damage(0);
						d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND,
								Material.REDSTONE_BLOCK);
					}
				}
			} else {
				if (moblife.get(d) != null) {
					Double dano = (moblife.get(d) - e.getDamage());
					if (dano <= 0) {
						SimpleRunnable r = SimpleRunnable.getInstance();
						if (holomob.get(d) != null) {
							Mobs m = MobManager.getMob(holomob.get(d));
							if (m instanceof MobTrans) {
								MobTrans mt = (MobTrans)m;
								MobManager.spawnEntity(mt.proxmob(), d.getLocation());
							}
							if (m.respawn() == true) {
								r.createTaskLater(TaskType.SYNC, d + "respawn", new Runnable() {
									@Override
									public void run() {
										MobManager.spawnEntity(m.name(), d.getLocation());
									}
								}, 20 * 60);
							}
						}
						d.remove();
						moblife.remove(d);
					} else {
						e.setCancelled(true);
						moblife.put(d, dano);
						d.damage(0);
						d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND,
								Material.REDSTONE_BLOCK);
					}
				}
			}
		}
	}

	/*
	 * @EventHandler public void mobxp(EntityDeathEvent e) {
	 * if(moblastdamager.get(e.getEntity()) != null) { Player p =
	 * moblastdamager.get(e.getEntity()); Jogador j =
	 * JogadorManager.getWynnPlayer(p); if(j.getCurrentAccount() != null) { Account
	 * a = j.getCurrentAccount(); int xp = new
	 * Random().nextInt(j.getCurrentAccount().getLevel() * 10); Metodos.darxp(xp,
	 * p); } Bukkit.broadcastMessage("xp"); } }
	 */

	@EventHandler
	public void hololife(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		if (holodata.get(entity) != null) {
			if (holotype.get(entity) != null) {
				if (holotype.get(entity).equals(Tipo.B)) {
					ArmorStand[] a = holodata.get(entity);
					if (a.length == 1) {
						if (moblife.get((Damageable) e.getEntity()) != null) {
							Damageable ed = (Damageable) e.getEntity();
							Double dano = (moblife.get(ed) - e.getDamage());
							moblife.put(ed, dano);
							Double vida = moblife.get(ed);
							ArmorStand av = HologramApi.createHologram(entity.getLocation().add(0.0, 1.2, 0.0),
									"§4[:::" + vida.intValue() + ":::]");
							av.setMarker(true);
							av.setGravity(false);
							av.setSmall(true);
							ArmorStand[] lista = new ArmorStand[] { a[0], av };
							holodata.put((Damageable) entity, lista);
							holotemp.put(av, (Damageable) entity);
						}
					}
				}
				if (holotype.get(entity).equals(Tipo.C)) {
					ArmorStand[] a = holodata.get(entity);
					if (a.length == 1) {
						if (moblife.get((Damageable) e.getEntity()) != null) {
							Damageable ed = (Damageable) e.getEntity();
							Double dano = (moblife.get(ed) - e.getDamage());
							moblife.put(ed, dano);
							Double vida = moblife.get(ed);
							ArmorStand av = HologramApi.createHologram(entity.getLocation().add(0.0, 1.2, 0.0),
									"§4[:::" + vida.intValue() + ":::]");
							av.setMarker(true);
							av.setGravity(false);
							av.setSmall(true);
							ArmorStand[] lista = new ArmorStand[] { a[0], av };
							holodata.put((Damageable) entity, lista);
							holotemp.put(av, (Damageable) entity);
						}
					}
				}
				if (holotype.get(entity).equals(Tipo.D)) {
					ArmorStand[] a = holodata.get(entity);
					if (a.length == 1) {
						if (moblife.get((Damageable) e.getEntity()) != null) {
							Damageable ed = (Damageable) e.getEntity();
							Double dano = (moblife.get(ed) - e.getDamage());
							moblife.put(ed, dano);
							Double vida = moblife.get(ed);
							ArmorStand av = HologramApi.createHologram(entity.getLocation().add(0.0, 1.2, 0.0),
									"§4[:::" + vida.intValue() + ":::]");
							av.setMarker(true);
							av.setGravity(false);
							av.setSmall(true);
							ArmorStand[] lista = new ArmorStand[] { a[0], av };
							holodata.put((Damageable) entity, lista);
							holotemp.put(av, (Damageable) entity);
						}

					}
				}
			}
		}
	}

	@EventHandler
	public void OnMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Chunk c = p.getWorld().getChunkAt(p.getLocation());
		UUID id = p.getUniqueId();
		String cooldownName = c.getX() + ";" + c.getZ();
		int timeInSeconds = 15;
		Cooldown co = new Cooldown(id, cooldownName, timeInSeconds);
		if (!co.isInCooldown(cooldownName)) {
			co.start();
			Entity[] lista = c.getEntities();
			ArrayList<EntityType> listamob = new ArrayList<EntityType>();
			for (int i = 0; i < lista.length; i++) {
				listamob.add(lista[i].getType());
			}
			if (listamob.contains(EntityType.PLAYER)) {
				if (carregado.get(c) == null) {
					int X = c.getX() * 16;
					int Z = c.getZ() * 16;
					for (int x = 0; x < 16; x++) {
						for (int z = 0; z < 16; z++) {
							for (int y = 0; y < 128; y++) {
								Block b = c.getWorld().getBlockAt(X + x, y, Z + z);
								if (b.getType().equals(Material.SIGN_POST) || b.getType().equals(Material.WALL_SIGN)) {
									Sign sg = (Sign) c.getWorld().getBlockAt(X + x, y, Z + z).getState();
									if (sg.getLine(0).contains("WynnMob")) {
										for (Mobs m : MobManager.mobs) {
											if (m.name().equalsIgnoreCase(sg.getLine(1))) {
												if (sg.getLine(2).isEmpty()) {
													carregado.put(c, true);
													listamob.clear();
													MobManager.spawn(m, sg.getLocation().add(0, 5, 0), 5);
												} else {
													carregado.put(c, true);
													listamob.clear();
													MobManager.spawn(m, sg.getLocation().add(0, 5, 0),
															Integer.parseInt(sg.getLine(2)));
												}

											}
										}
									} else if (sg.getLine(0).contains("CityMob")) {
										for (Mobs m : MobManager.mobs) {
											if (m.tipo().equals(Tipo.A)) {
												if (m.name().equalsIgnoreCase(sg.getLine(1))) {
													if (sg.getLine(2).isEmpty()) {
														carregado.put(c, true);
														listamob.clear();
														MobManager.spawn(m, sg.getLocation().add(0, 5, 0), 5);
													} else {
														carregado.put(c, true);
														listamob.clear();
														MobManager.spawn(m, sg.getLocation().add(0, 5, 0),
																Integer.parseInt(sg.getLine(2)));
													}
												}
											}
											if (m.tipo().equals(Tipo.B)) {
												if (m.name().equalsIgnoreCase(sg.getLine(1))) {
													if (sg.getLine(2).isEmpty()) {
														carregado.put(c, true);
														listamob.clear();
														MobManager.spawn(m, sg.getLocation().add(0, 5, 0), 5);
													} else {
														carregado.put(c, true);
														listamob.clear();
														MobManager.spawn(m, sg.getLocation().add(0, 5, 0),
																Integer.parseInt(sg.getLine(2)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				listamob.clear();
				if (carregado.get(c) != null) {
					carregado.remove(c);
				}
				// mobs.remove();
			}
		}
	}

	@EventHandler
	public void unloadevent(ChunkUnloadEvent e) {
		ArrayList<MobCache> mobc;
		for (Entity entity : e.getChunk().getEntities()) {
			if (holomob.get(entity) != null) {
				if (holotype.get(entity) != null) {
					if (guardloc.get(entity) != null) {
						if (mobsincache.get(e.getChunk().getX() + ";" + e.getChunk().getZ()) == null) {
							mobc = new ArrayList<>();
						} else {
							mobc = (ArrayList<MobCache>) mobsincache
									.get(e.getChunk().getX() + ";" + e.getChunk().getZ());
						}
						MobCache mc = new MobCache(holomob.get(entity), guardloc.get(entity));
						mobc.add(mc);
						mobsincache.put(e.getChunk().getX() + ";" + e.getChunk().getZ(), mobc);
						// Bukkit.broadcastMessage("foi add mob: " + mc.getM());
						// Bukkit.broadcastMessage(
						// "size: " + mobsincache.get(e.getChunk().getX() + ";" +
						// e.getChunk().getZ()).size());
						// Bukkit.broadcastMessage("size 1: " + mobsincache.size());
						entity.remove();
					} else {
						if (mobsincache.get(e.getChunk().getX() + ";" + e.getChunk().getZ()) == null) {
							mobc = new ArrayList<>();
						} else {
							mobc = (ArrayList<MobCache>) mobsincache
									.get(e.getChunk().getX() + ";" + e.getChunk().getZ());
						}
						MobCache mc = new MobCache(holomob.get(entity), entity.getLocation());
						mobc.add(mc);
						mobsincache.put(e.getChunk().getX() + ";" + e.getChunk().getZ(), mobc);
						Bukkit.broadcastMessage("foi add mob: " + mc.getM());
						Bukkit.broadcastMessage(
						"size: " + mobsincache.get(e.getChunk().getX() + ";" +
						e.getChunk().getZ()).size());
						Bukkit.broadcastMessage("size 1: " + mobsincache.size());
						entity.remove();
					}
				}
			} else {
				entity.remove();
			}
		}
	}

	@EventHandler
	public void loadevent(ChunkLoadEvent e) {
		// Bukkit.broadcastMessage("Load");
		SimpleRunnable r = new SimpleRunnable().getInstance();
		if (mobsincache.get(e.getChunk().getX() + ";" + e.getChunk().getZ()) != null) {
			Chunk c = e.getChunk();
			List<MobCache> mc = mobsincache.get(c.getX() + ";" + c.getZ());
			for (MobCache m : mc) {
				Bukkit.broadcastMessage("Spawn : "+ m.getM());
				MobManager.spawnEntity(m.getM(), m.getUltimoloc());
				r.createTaskLater(TaskType.SYNC, mc+ "-spawn"+e.getChunk().getX() + ";" + e.getChunk().getZ()+"-"+System.currentTimeMillis(), new Runnable() {
					@Override
					public void run() {
						mc.remove(m);
					}
				}, 20 * 1);
			}
			if (mc.size() == 0) {
				Bukkit.broadcastMessage("Reset");
				mobsincache.remove(c);
			}
		} else {
			// Bukkit.broadcastMessage("f");
		}
	}

	/*
	 * public static Map<Damageable, ArmorStand[]> holodata = Maps.newHashMap();
	 * public static Map<Damageable, Double> moblife = Maps.newHashMap(); public
	 * static Map<Damageable, Tipo> holotype = Maps.newHashMap();
	 */
}
