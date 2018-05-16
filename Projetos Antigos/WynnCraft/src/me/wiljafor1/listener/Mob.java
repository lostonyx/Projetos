package me.wiljafor1.listener;

import java.util.ArrayList;
import java.util.Map;
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
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerMoveEvent;
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
import me.wiljafor1.system.mobs.A;
import me.wiljafor1.system.mobs.B;
import me.wiljafor1.system.mobs.D;
import me.wiljafor1.system.mobs.MobManager;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.system.mobs.A.Mode;
import me.wiljafor1.system.mobs.Mobs.Tipo;
import me.wiljafor1.utils.CItem;
import me.wiljafor1.utils.Cooldown;
import me.wiljafor1.utils.HCStrings;
import me.wiljafor1.utils.HologramApi;
import me.wiljafor1.utils.ItemAPI;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;
import net.minecraft.server.v1_8_R3.EntityInsentient;


public class Mob implements Listener{
	public static Map<Chunk, Boolean> carregado = Maps.newHashMap();
	public static Map<Damageable, ArmorStand[]> holodata = Maps.newHashMap();
	public static Map<Damageable, Double> moblife = Maps.newHashMap();
	public static Map<Damageable, Tipo> holotype = Maps.newHashMap();
	public static Map<ArmorStand, Damageable> holotemp = Maps.newHashMap();
	
	public static void follow() {
		SimpleRunnable r = SimpleRunnable.getInstance();
        for(Entry<Damageable, ArmorStand[]> set : holodata.entrySet()) {
            Damageable d = set.getKey();
            ArmorStand[] a = set.getValue();
            if(d != null) {
            	if(d.isValid()) {
            		if(d.isDead()) {
            			a[0].remove();
                        a[1].remove();
                    	holodata.remove(d);
                    	holotype.remove(d);
                    	moblife.remove(d);
            		}
            		else {
            			if(holotype.get(d).equals(Tipo.A)) {
            				if(d.getType().equals(EntityType.IRON_GOLEM)) {
                                a[0].teleport(d.getLocation().add(0, 2.3, 0));
                        	}
            			}
            			if(holotype.get(d).equals(Tipo.B)) {
            				Double vida = moblife.get(d);
            				if(a.length == 1) {
            					a[0].teleport(d.getLocation().add(0, 1.5, 0));
            				}
            				else {
            					a[1].teleport(d.getLocation().add(0, 1.2, 0));
                                a[1].setCustomName("§4[§c:::§4"+vida.intValue()+"§c:::§4]");
                                a[0].teleport(d.getLocation().add(0, 1.5, 0));	
            				}
            			}
            			if(holotype.get(d).equals(Tipo.C)) {
            				
            			}
            			if(holotype.get(d).equals(Tipo.D)) {
                			if(d.getType().equals(EntityType.IRON_GOLEM)) {
                				Double vida = moblife.get(d);
                				if(a.length == 1) {
                					a[0].teleport(d.getLocation().add(0, 1.5, 0));
                				}
                				else {
                					a[1].teleport(d.getLocation().add(0, 1.2, 0));
                                    a[1].setCustomName("§4[§c:::§4"+vida.intValue()+"§c:::§4]");
                                    a[0].teleport(d.getLocation().add(0, 1.5, 0));	
                				}
                        	}
                        	else {
                        		Double vida = moblife.get(d);
                        		if(a.length == 1) {
                					a[0].teleport(d.getLocation().add(0, 1.5, 0));
                				}
                				else {
                					a[1].teleport(d.getLocation().add(0, 1.2, 0));
                                    a[1].setCustomName("§4[§c:::§4"+vida.intValue()+"§c:::§4]");
                                    a[0].teleport(d.getLocation().add(0, 1.5, 0));	
                				}
                        	}
            			}
            		}
            	}
            }
        }
        for(Entry<ArmorStand, Damageable> set : holotemp.entrySet()) {
        	Damageable d = set.getValue();
            ArmorStand a = set.getKey();
                if(d == null) {
                	a.remove();
                	r.createTaskLater(TaskType.SYNC, a.getEntityId()+"-"+d.getEntityId(), new Runnable() {
						@Override
						public void run() {
							if(holotype.get(d) != null) {
								holotype.remove(d);
								moblife.remove(d);
							}
							if(holodata.get(d) != null) {
								holodata.remove(d);
								moblife.remove(d);
							}
		                	holotemp.remove(a);
						}
					}, 2 * 20);
                }
                else if(d.isDead()) {
                	a.remove();
                	r.createTaskLater(TaskType.SYNC, a.getEntityId()+"-"+d.getEntityId(), new Runnable() {
						@Override
						public void run() {
							if(holotype.get(d) != null) {
								holotype.remove(d);
								moblife.remove(d);
							}
							if(holodata.get(d) != null) {
								holodata.remove(d);
								moblife.remove(d);
							}
		                	holotemp.remove(a);
						}
					}, 2 * 20);
                }
                else if(!d.isValid()) {
                	a.remove();
                	r.createTaskLater(TaskType.SYNC, a.getEntityId()+"-"+d.getEntityId(), new Runnable() {
						@Override
						public void run() {
							if(holotype.get(d) != null) {
								holotype.remove(d);
								moblife.remove(d);
							}
							if(holodata.get(d) != null) {
								holodata.remove(d);
								moblife.remove(d);
							}
		                	holotemp.remove(a);
						}
					}, 2 * 20);
                }
        }
    }
	
	public static void removemob(Entity e) {
		if(e != null) {
			if(holodata.get(e) != null) {
				if(holotype.get(e) != null) {
					if(holotype.get(e).equals(Tipo.A)) {
						ArmorStand[] l = holodata.get(e);
						l[0].remove();
						holotemp.remove(l[0]);
						holodata.remove(e);
						holotype.remove(e);
						e.remove();
					}
					if(holotype.get(e).equals(Tipo.B)) {
						ArmorStand[] l = holodata.get(e);
						if(l.length == 1) {
							l[0].remove();
							holotemp.remove(l[0]);
							holodata.remove(e);
							holotype.remove(e);
							e.remove();
						}
						else {
							l[0].remove();
							l[1].remove();
							holotemp.remove(l[0]);
							holotemp.remove(l[1]);
							holodata.remove(e);
							holotype.remove(e);
							e.remove();
						}
					}
					if(holotype.get(e).equals(Tipo.D)) {
						ArmorStand[] l = holodata.get(e);
						if(l.length == 1) {
							l[0].remove();
							holotemp.remove(l[0]);
							holodata.remove(e);
							holotype.remove(e);
							e.remove();
						}
						else {
							l[0].remove();
							l[1].remove();
							holotemp.remove(l[0]);
							holotemp.remove(l[1]);
							holodata.remove(e);
							holotype.remove(e);
							e.remove();
						}
						/*ArmorStand[] l = holodata.get(e);
						l[0].remove();
						l[1].remove();
						holotemp.remove(l[0]);
						holotemp.remove(l[1]);
						holodata.remove(e);
						holotype.remove(e);
						e.remove();*/
					}
				}
			}
		}
	}
	
	@EventHandler
	public void dis(CreatureSpawnEvent e) {
		if(e.getSpawnReason() == SpawnReason.CUSTOM) {
			Entity en = e.getEntity();
			Location loc = en.getLocation();
			Mobs m = MobManager.getMob(en.getCustomName());
			if(m != null) {
				if (!loc.getChunk().isLoaded()) loc.getChunk().load();
				if(m.tipo().equals(Tipo.A)) {
					A mob = (A) m;
					if(m.type().equals(EntityType.IRON_GOLEM)) {
						if(e.getEntityType().equals(m.type())) {
							if(mob.mode().equals(Mode.GUARD)) {
								int vida = (int) ((IronGolem) en).getHealth();
								ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 2.3, 0.0), "§b"+mob.nameDisplay() +" §6§l[Lv. "+mob.Level()+"]");
				                a.setMarker(true);
				                a.setGravity(false);
				                a.setSmall(true);
				                en.setCustomNameVisible(true);
				                en.setCustomNameVisible(false);
				                en.setCustomName(" ");
				                en.setCustomNameVisible(true);
				                ArmorStand[] lista = new ArmorStand[] {
				                        a
				                };
				                if(mob.player()) {
				                	PlayerDisguise dis = new PlayerDisguise("§4§l ");
						        	if(dis.isShowName()) {
						        		dis.setShowName(false);	
						        	}
						        	if(mob.SkinName() != null) {
						        		dis.setSkin(mob.SkinName());
						        	}
						        	dis.getWatcher().setItemInHand(new CItem(Material.WOOD).build());
						        	dis.getWatcher().setArmor(new ItemStack[] { new CItem(Material.LEATHER_LEGGINGS).build(), new CItem(Material.LEATHER_HELMET).build()});
									DisguiseAPI.disguiseEntity(en, dis);
				                }
				                holodata.put((Damageable) en, lista);
				                holotype.put((Damageable) en, m.tipo());
				                holotemp.put(a, (Damageable) en);
				                moblife.put((Damageable) en, (double) m.life());
							}
						}
					}
				}
				else if(m.tipo().equals(Tipo.B)) {
					B mob = (B) m;
					if(m.type().equals(EntityType.VILLAGER)) {
						if(e.getEntityType().equals(m.type())) {
							Double vida = (Double) ((Villager) en).getHealth();
							ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 1.5, 0.0), "§a"+mob.nameDisplay() +" §6§l[Lv. "+mob.Level()+"]");
							//ArmorStand av = HologramApi.createHologram(en.getLocation().add(0.0, 1.2, 0.0), "§4[§c:::§4"+vida.intValue()+"§c:::§4]");
			                a.setMarker(true);
			                a.setGravity(false);
			                a.setSmall(true);
			                //av.setMarker(true);
			                //av.setGravity(false);
			                //av.setSmall(true);
			                ((LivingEntity) en).addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 
			                        9999999, 9999999));
			                en.setCustomNameVisible(true);
			                en.setCustomNameVisible(false);
			                en.setCustomName(" ");
			                en.setCustomNameVisible(true);
			                ArmorStand[] lista = new ArmorStand[] {
			                        a
			                };
			                holodata.put((Damageable) en, lista);
			                holotype.put((Damageable) en, m.tipo());
			                holotemp.put(a, (Damageable) en);
			                moblife.put((Damageable) en, (double) m.life());
			                //holotemp.put(av, (Damageable) en);
			                if(mob.player()) {
			                	PlayerDisguise dis = new PlayerDisguise("§4§l ");
					        	if(dis.isShowName()) {
					        		dis.setShowName(false);	
					        	}
					        	dis.getWatcher().addPotionEffect(PotionEffectType.HEAL);
					        	if(mob.SkinName() != null) {
					        		dis.setSkin(mob.SkinName());
					        	}
								DisguiseAPI.disguiseEntity(en, dis);
			                }
						}
					}
				}
				else if(m.tipo().equals(Tipo.C)) {
					
				}	
				else if(m.tipo().equals(Tipo.D)) {
					D mob = (D) m;
					if(m.type().equals(EntityType.ZOMBIE)) {
						if(e.getEntityType().equals(m.type())) {
							Double vida = (Double) ((Zombie) en).getHealth();
							ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 1.5, 0.0), "§c"+mob.nameDisplay() +" §6§l[Lv. "+mob.Level()+"]");
							//ArmorStand av = HologramApi.createHologram(en.getLocation().add(0.0, 1.2, 0.0), "§4[:::"+vida.intValue()+":::]");
			                a.setMarker(true);
			                a.setGravity(false);
			                a.setSmall(true);
			                //av.setMarker(true);
			                //av.setGravity(false);
			                //av.setSmall(true);
			                en.setCustomNameVisible(true);
			                en.setCustomNameVisible(false);
			                en.setCustomName(" ");
			                en.setCustomNameVisible(true);
			                ArmorStand[] lista = new ArmorStand[] {
			                        a
			                };
			                holodata.put((Damageable) en, lista);
			                holotype.put((Damageable) en, m.tipo());
			                holotemp.put(a, (Damageable) en);
			                moblife.put((Damageable) en, (double) m.life());
			                //holotemp.put(av, (Damageable) en);
			                MobDisguise mobDisguise = new MobDisguise(DisguiseType.ZOMBIE);
							DisguiseAPI.disguiseEntity(en, mobDisguise);	
						}
					}
				}	
			}
		}
	}
	/*public static void follow() {
        for(Entry<Damageable, ArmorStand[]> set : holodata.entrySet()) {
            Damageable d = set.getKey();
            ArmorStand[] a = set.getValue();
            if(d != null) {
            	if(d.isValid()) {
            		if(d.isDead()) {
            			a[0].remove();
                        a[1].remove();
                    	holodata.remove(d);
            		}
            		else {
            			if(d.getType().equals(EntityType.IRON_GOLEM)) {
                    		a[1].teleport(d.getLocation().add(0, 2.0, 0));
                            a[1].setCustomName("§4§l[:::"+d.getHealth()+":::]");
                            a[0].teleport(d.getLocation().add(0, 2.3, 0));
                    	}
                    	else {
                    		a[1].teleport(d.getLocation().add(0, 1.2, 0));
                            a[1].setCustomName("§4§l[:::"+d.getHealth()+":::]");
                            a[0].teleport(d.getLocation().add(0, 1.5, 0));
                    	}
            		}
            	}
            	else {
                	a[0].remove();
                    a[1].remove();
                	//holodata.remove(d);
                }
            }
            else {
            	a[0].remove();
                a[1].remove();
            	//holodata.remove(d);
            }
        }
    }*/
	
	/*@EventHandler
	public void dis(CreatureSpawnEvent e) {
		if(e.getSpawnReason() == SpawnReason.CUSTOM) {
			Entity en = e.getEntity();
			if(en instanceof Zombie) {
				Mobs m = MobManager.getMob(en.getCustomName());
				int vida = (int) ((Zombie) en).getHealth();
				ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 1.5, 0.0), "§4§l"+m.nameDisplay() +" §e§l[LvL"+m.Level()+"]");
				ArmorStand av = HologramApi.createHologram(en.getLocation().add(0.0, 1.2, 0.0), "§4§l[:::"+vida+":::]");
                a.setMarker(true);
                a.setGravity(false);
                a.setSmall(true);
                av.setMarker(true);
                av.setGravity(false);
                av.setSmall(true);
                en.setCustomNameVisible(true);
                en.setCustomNameVisible(false);
                en.setCustomName(" ");
                en.setCustomNameVisible(true);
                ArmorStand[] lista = new ArmorStand[] {
                        a, av
                };
                holodata.put((Damageable) en, lista);
                MobDisguise mobDisguise = new MobDisguise(DisguiseType.ZOMBIE);
				DisguiseAPI.disguiseEntity(en, mobDisguise);	
				//if(m.player() == true) {
		        	PlayerDisguise dis = new PlayerDisguise("§4§l ");
		        	dis.setShowName(false);
		        	dis.setSkin(m.SkinName());
		        	dis.getWatcher().setHideCape(false);
		        	dis.getWatcher().rebuildWatchableObjects();
					DisguiseAPI.disguiseEntity(en, dis);
		        }
		        else {
		        	if(m.hostil() == true) {
		        		MobDisguise mobDisguise = new MobDisguise(DisguiseType.ZOMBIE);
						DisguiseAPI.disguiseEntity(en, mobDisguise);	
		        	}
		        	else {
		        		PlayerDisguise dis = new PlayerDisguise(" ");
			        	dis.setShowName(false);
			        	dis.setSkin(m.SkinName());
			        	dis.getWatcher().setHideCape(false);
			        	dis.getWatcher().rebuildWatchableObjects();
						DisguiseAPI.disguiseEntity(en, dis);
		        	}
		        }//	
			}
			if(en instanceof IronGolem) {
				Mobs m = MobManager.getMob(en.getCustomName());
				int vida = (int) ((IronGolem) en).getHealth();
				ArmorStand a = HologramApi.createHologram(en.getLocation().add(0.0, 2.3, 0.0), "§4§l"+m.nameDisplay() +" §e§l[LvL"+m.Level()+"]");
				ArmorStand av = HologramApi.createHologram(en.getLocation().add(0.0, 2.0, 0.0), "§4§l[:::"+vida+":::]");
                a.setMarker(true);
                a.setGravity(false);
                a.setSmall(true);
                av.setMarker(true);
                av.setGravity(false);
                av.setSmall(true);
                en.setCustomNameVisible(true);
                en.setCustomNameVisible(false);
                en.setCustomName(" ");
                en.setCustomNameVisible(true);
                ArmorStand[] lista = new ArmorStand[] {
                        a, av
                };
                holodata.put((Damageable) en, lista);
		        MobDisguise mobDisguise = new MobDisguise(DisguiseType.IRON_GOLEM);
				//DisguiseAPI.disguiseEntity(en, mobDisguise);	
			}
		}
	}*/
	
	@EventHandler
	public void onEntityCombust(EntityCombustEvent event) {
		if (event.getEntity() instanceof Zombie) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
    public void effect(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        if(entity instanceof Zombie) {
        	if(damager instanceof IronGolem) {
        		e.setCancelled(true);
                Damageable d = (Damageable)entity;
                d.damage(0);
                d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        		removemob(entity);
        	}
        	else if(damager instanceof Villager) {
        		e.setCancelled(true);
                Damageable d = (Damageable)entity;
                d.damage(0);
                d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        		removemob(entity);
        	}
        	else {
        		e.setCancelled(true);
                Damageable d = (Damageable)entity;
                Double dano = (moblife.get(d) -e.getDamage());
                if(dano <=0 ) {
                	d.remove();
                	moblife.remove(d);
                }
                else {
                	moblife.put(d, dano);
                    d.damage(0);
                    d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);	
                }
        	}
    	}
        if(entity instanceof IronGolem) {
        	if(damager instanceof Zombie) {
        		e.setCancelled(true);
        	}
        	else {
        		e.setCancelled(true);
                Damageable d = (Damageable)entity;
                Double dano = (moblife.get(d) -e.getDamage());
                if(dano <=0 ) {
                	d.remove();
                	moblife.remove(d);
                }
                else {
                	moblife.put(d, dano);
                    d.damage(0);
                    d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);	
                }
        	}
    	}
        if(entity instanceof Villager) {
        	if(damager instanceof Zombie) {
        		e.setCancelled(true);
        	}
        	else {
        		e.setCancelled(true);
                Damageable d = (Damageable)entity;
                Double dano = (moblife.get(d) -e.getDamage());
                if(dano <= 0 ) {
                	d.remove();
                	moblife.remove(d);
                }
                else {
                	moblife.put(d, dano);
                    d.damage(0);
                    //d.getWorld().playEffect(d.getLocation().add(0.0, 1.5, 0.0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);	
                }
        	}
    	}
    }
	
	@EventHandler
    public void hololife(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        if(holodata.get(entity) != null) {
        	if(holotype.get(entity) != null) {
        		if(holotype.get(entity).equals(Tipo.B)) {
        			ArmorStand[] a = holodata.get(entity);
            		if(a.length == 1) {
            			Damageable ed = (Damageable)e.getEntity();
            			Double dano = (moblife.get(ed) -e.getDamage());
            			moblife.put(ed, dano);
            			Double vida = moblife.get(ed);
            			ArmorStand av = HologramApi.createHologram(entity.getLocation().add(0.0, 1.2, 0.0), "§4[:::"+vida.intValue()+":::]");
		                av.setMarker(true);
		                av.setGravity(false);
		                av.setSmall(true);
		                ArmorStand[] lista = new ArmorStand[] {
		                        a[0], av
		                };
		                holodata.put((Damageable) entity, lista);
		                holotemp.put(av, (Damageable) entity);
            		}
            	}
        		if(holotype.get(entity).equals(Tipo.C)) {
        			ArmorStand[] a = holodata.get(entity);
            		if(a.length == 1) {
            			Damageable ed = (Damageable)e.getEntity();
            			Double dano = (moblife.get(ed) -e.getDamage());
            			moblife.put(ed, dano);
            			Double vida = moblife.get(ed);
            			ArmorStand av = HologramApi.createHologram(entity.getLocation().add(0.0, 1.2, 0.0), "§4[:::"+vida.intValue()+":::]");
		                av.setMarker(true);
		                av.setGravity(false);
		                av.setSmall(true);
		                ArmorStand[] lista = new ArmorStand[] {
		                        a[0], av
		                };
		                holodata.put((Damageable) entity, lista);
		                holotemp.put(av, (Damageable) entity);
            		}
            	}
        		if(holotype.get(entity).equals(Tipo.D)) {
        			ArmorStand[] a = holodata.get(entity);
            		if(a.length == 1) {
            			Damageable ed = (Damageable)e.getEntity();
            			Double dano = (moblife.get(ed) -e.getDamage());
            			moblife.put(ed, dano);
            			Double vida = moblife.get(ed);
            			ArmorStand av = HologramApi.createHologram(entity.getLocation().add(0.0, 1.2, 0.0), "§4[:::"+vida.intValue()+":::]");
		                av.setMarker(true);
		                av.setGravity(false);
		                av.setSmall(true);
		                ArmorStand[] lista = new ArmorStand[] {
		                        a[0], av
		                };
		                holodata.put((Damageable) entity, lista);
		                holotemp.put(av, (Damageable) entity);
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
		String cooldownName = c.getX()+";"+c.getZ();
		int timeInSeconds = 15;
		Cooldown co = new Cooldown(id, cooldownName, timeInSeconds);
		if (!co.isInCooldown(cooldownName)) {
			co.start();
			Entity[] lista = c.getEntities();
			ArrayList<EntityType> listamob = new ArrayList<EntityType>();
			for(int i = 0; i < lista.length; i++) {
				listamob.add(lista[i].getType());
			}
			if(listamob.contains(EntityType.PLAYER)) {
				if(carregado.get(c) == null) {
					int X = c.getX() * 16;
		            int Z = c.getZ() * 16;
		            for (int x = 0; x < 16; x++) {
		                for (int z = 0; z < 16; z++) {
		                    for (int y = 0; y < 128; y++) {	
		                    	//Bukkit.broadcastMessage("Mob: "+ entities.getType() + " Chunk: "+c.toString());
		                    	Block b = c.getWorld().getBlockAt(X+x, y, Z+z);
		                    	if (b.getType().equals(Material.SIGN_POST) || b.getType().equals(Material.WALL_SIGN)) {
		                    		Sign sg = (Sign) c.getWorld().getBlockAt(X+x, y, Z+z).getState();
		                    		if(sg.getLine(0).contains("WynnMob")) {
		                    			for (Mobs m : MobManager.mobs) {
		                    					if(m.name().equalsIgnoreCase(sg.getLine(1))) {
			                    					if(sg.getLine(2).isEmpty()) {
			                    						carregado.put(c, true);
				                    					listamob.clear();
				                    					MobManager.spawn(m, sg.getLocation().add(0, 5, 0), 5);
			                    					}
			                    					else {
			                    						carregado.put(c, true);
				                    					listamob.clear();
				                    					MobManager.spawn(m, sg.getLocation().add(0, 5, 0), Integer.parseInt(sg.getLine(2)));	
			                    					}
			                    					
			                    				}	
		                    			}
		                    		}
		                    		else if(sg.getLine(0).contains("CityMob")) {
		                    			for (Mobs m : MobManager.mobs) {
		                    				if(m.tipo().equals(Tipo.A)) {
		                    					if(m.name().equalsIgnoreCase(sg.getLine(1))) {
			                    					if(sg.getLine(2).isEmpty()) {
			                    						carregado.put(c, true);
				                    					listamob.clear();
				                    					MobManager.spawn(m, sg.getLocation().add(0, 5, 0), 5);
			                    					}
			                    					else {
			                    						carregado.put(c, true);
				                    					listamob.clear();
				                    					MobManager.spawn(m, sg.getLocation().add(0, 5, 0), Integer.parseInt(sg.getLine(2)));	
			                    					}
			                    				}		
		                    				}
		                    				if(m.tipo().equals(Tipo.B)) {
		                    					if(m.name().equalsIgnoreCase(sg.getLine(1))) {
			                    					if(sg.getLine(2).isEmpty()) {
			                    						carregado.put(c, true);
				                    					listamob.clear();
				                    					MobManager.spawn(m, sg.getLocation().add(0, 5, 0), 5);
			                    					}
			                    					else {
			                    						carregado.put(c, true);
				                    					listamob.clear();
				                    					MobManager.spawn(m, sg.getLocation().add(0, 5, 0), Integer.parseInt(sg.getLine(2)));	
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
			}
			else {
				listamob.clear();
					if(carregado.get(c) != null) {
						carregado.remove(c);
					}
					//mobs.remove();
			}
		}
	}
}
