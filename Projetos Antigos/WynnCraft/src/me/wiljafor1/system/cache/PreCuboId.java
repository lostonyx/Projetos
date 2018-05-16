package me.wiljafor1.system.cache;

import static me.wiljafor1.interfaces.RegionManager.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.wiljafor1.Main;
import me.wiljafor1.interfaces.City;
import me.wiljafor1.interfaces.Region;
import me.wiljafor1.interfaces.RegionManager;
import me.wiljafor1.listener.Mob;
import me.wiljafor1.system.mobs.MobManager;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.system.mobs.Mobs.Tipo;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;
import net.md_5.bungee.api.ChatColor;

public class PreCuboId {
	
	public static PreCuboId Getinstance() {
		return new PreCuboId();
	}

/*
 * Preload 0
 * <Nome da Region> 1
 * <Nome de Mob> 2
 * <Quantidade> 3
*/
	public void loadallsigns() {//Todos os chunks
		for (Chunk c : Bukkit.getServer().getWorld("world").getLoadedChunks()) {
			int X = c.getX() * 16;
            int Z = c.getZ() * 16;
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 128; y++) {	
                    	Block b = c.getWorld().getBlockAt(X+x, y, Z+z);
                    	if (b.getType().equals(Material.SIGN_POST) || b.getType().equals(Material.WALL_SIGN)) {
                    		Sign sg = (Sign) c.getWorld().getBlockAt(X+x, y, Z+z).getState();
                    		Main.GetInstance().getServer().getConsoleSender()
                			.sendMessage(ChatColor.RED + sg.getLine(0));
                    		if(sg.getLine(0).contains("PreLoad")) {
                    			Main.GetInstance().getServer().getConsoleSender()
                    			.sendMessage(ChatColor.RED + "For");
                    			if(RegionManager.getRegion(sg.getLine(1)) != null) {
                    				for (Mobs m : MobManager.mobs) {
                    					if(m.name().equalsIgnoreCase(sg.getLine(2))) {
                    						City r = (City) RegionManager.getRegion(sg.getLine(1));
	                    					if(sg.getLine(3).isEmpty()) {
	                    						RegionManager.cachemob.put(sg.getLocation().add(0, 5, 0), m);
	                    						//RegionManager.cachemobamount.put(r.getName(), (Map<Mobs, Integer>) Maps.newHashMap().put(m, 1));
	                    						//r.SpawnMob().put(sg.getLocation().add(0, 5, 0), m);
	                    						//r.MobAmount().put(m, 1);
	                    						Main.GetInstance().getServer().getConsoleSender()
	                    						.sendMessage(ChatColor.RED + "Carregou");
	                    						
	                    					}
	                    					else {
	                    						Main.GetInstance().getServer().getConsoleSender()
	                    						.sendMessage(ChatColor.RED + "Carregou");
	                    						//r.SpawnMob().put(sg.getLocation().add(0, 5, 0), m);
	                    						//r.MobAmount().put(m, Integer.parseInt(sg.getLine(3)));	
	                    						RegionManager.cachemob.put(sg.getLocation().add(0, 5, 0), m);
	                    						//RegionManager.cachemobamount.put(r.getName(), (Map<Mobs, Integer>) Maps.newHashMap().put(m, sg.getLine(3)));
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
	/*
	 * Preload 0
	 * <Nome de Mob> 1
	*/
	public void loadallsignsRegion() {//Carrega por Region
		for(Region rg : RegionManager.regions) {
			for(Block b : rg.getRegion().getBlocks()) {
				if (b.getType().equals(Material.SIGN_POST)) {
            		Sign sg = (Sign) b.getState();
            		if(sg.getLine(0).contains("PreLoad")) {
            				for (Mobs m : MobManager.mobs) {
            					if(m.name().equalsIgnoreCase(sg.getLine(1))) {
            						City r = (City) RegionManager.getCurrentRegion(sg.getLocation());
                						/*Main.GetInstance().getServer().getConsoleSender()
                						.sendMessage(ChatColor.BLUE + "Carregou");
                						Main.GetInstance().getServer().getConsoleSender()
                						.sendMessage(ChatColor.BLUE + "Region: "+r.getName());
                						Main.GetInstance().getServer().getConsoleSender()
                						.sendMessage("Region: "+RegionManager.cachemob.size());*/
                						RegionManager.cachemob.put(sg.getLocation().add(0, 5, 0), m);
                				}	
            				}
            		}
            	} 
			}
		}
	}
	
	public void getinfo() {
		for(Region r : RegionManager.regions) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(RegionManager.getCurrentRegion(p.getLocation()) != null) {
					if(RegionManager.getCurrentRegion(p.getLocation()).equals(r)) {
						if(playerinregion.get(r.getName()) == null) {
							playerinregion.put(r.getName(), 1);
						}
						else {
							playerinregion.put(r.getName(),1+1);
						}
					}
				}
			}
		}
	}
	
	public void setstate() {
		for(Region r : RegionManager.regions) {
			if(playerinregion.get(r.getName()) == null) {
				regionhasplayer.put(r.getName(), false);
				//Bukkit.broadcastMessage("False");
			}
			else {
				regionhasplayer.put(r.getName(), true);
				if(regionpreload.contains(r.getName())) {
					regionpreload.remove(r.getName());
				}
				//Bukkit.broadcastMessage("True");
			}		
		}
	}
	
	public void removeentities() {
		for(Region r : RegionManager.regions) {
			if(regionhasplayer.get(r.getName()) == false) {
				for(Entity e : Bukkit.getWorld("world").getEntities()) {
					if(RegionManager.getCurrentRegion(e.getLocation()) != null) {
						if(RegionManager.getCurrentRegion(e.getLocation()).equals(r)) {
							if(!regionpreload.contains(r.getName())) {
								regionhasmob.put(r.getName(), false);
								e.remove();
							}
						}
					}
				}
			}
		}
	}
	
	public void start() {
		getinfo();
		setstate();
		removeentities();
		//Bukkit.broadcastMessage("Size debug:"+playerinregion.size());
		//Bukkit.broadcastMessage("rp detlas "+regionhasplayer.get("Detlas"));
		playerinregion.clear();
		check();
		/*SimpleRunnable r = SimpleRunnable.getInstance();
		r.createTaskLater(TaskType.SYNC, "city", new Runnable() {
			@Override
			public void run() {
				check();
			}
		}, 2 * 20);*/
	}
	
	public void check() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(RegionManager.getPreRegion(p.getLocation()) != null) {
				Region r = RegionManager.getPreRegion(p.getLocation());
				//p.sendMessage("Region: "+RegionManager.cachemob.size());
				/*p.sendMessage("Region: "+r.getName());
				p.sendMessage("rp detlas "+regionhasplayer.get("Detlas"));
				p.sendMessage("regionhasplayer: "+regionhasplayer.get(r.getName()));*/
				if(regionhasplayer.get(r.getName()) != null) {
					if(regionhasplayer.get(r.getName()) == false) {
						//Bukkit.broadcastMessage("s: "+regionpreload.contains(r.getName()));
						if(!regionpreload.contains(r.getName())) {
							regionpreload.add(r.getName());
							for(Entry<Location, Mobs> set : RegionManager.cachemob.entrySet()) {
								Mobs m = set.getValue();
								Location loc = set.getKey();
								if(r.getRegion().containsLocation(loc)) {
									MobManager.spawn(m, loc, m.Amount());
								}
								//p.sendMessage("Region: "+m.name());
								/*SimpleRunnable rr = SimpleRunnable.getInstance();
								rr.createTaskLater(TaskType.SYNC, "Mob-"+m.name()+""+loc, new Runnable() {
									@Override
									public void run() {
										
									}
								}, 3 * 20);*/
							}
							regionhasmob.put(r.getName(), true);
							Bukkit.broadcastMessage("um preload foi feito!");
							SimpleRunnable rr = SimpleRunnable.getInstance();
							rr.createTaskLater(TaskType.SYNC, "citytirarpre", new Runnable() {
								@Override
								public void run() {
									if(regionpreload.contains(r.getName())) {
										if(regionhasplayer.get(r.getName()) == false) {
											regionpreload.remove(r.getName());
										}
									}
								}
							}, 30 * 20);
						}
						/*if(regionpreload.get(r.getName())== null) {
							for(Entry<Location, Mobs> set : r.SpawnMob().entrySet()) {
								Mobs m = set.getValue();
								Location loc = set.getKey();
								MobManager.spawn(m, loc, r.MobAmount().get(m));
							}
							regionpreload.put(r.getName(), true);
							Bukkit.broadcastMessage("um preload foi feito!");
						}*/
					}
				}
			}
		}
	}

	
	public static void checkperplayer(Player p) {
			if(RegionManager.getPreRegion(p.getLocation()) != null) {
				Region r = RegionManager.getPreRegion(p.getLocation());
				if(regionhasplayer.get(r.getName()) != null) {
					if(regionhasplayer.get(r.getName()) == false) {
						if(regionhasmob.get(r.getName()) == null) {
							if(!regionpreload.contains(r.getName())) {
								regionpreload.add(r.getName());
								regionhasmob.put(r.getName(), true);
								for(Entry<Location, Mobs> set : RegionManager.cachemob.entrySet()) {
									Mobs m = set.getValue();
									Location loc = set.getKey();
									if(r.getRegion().containsLocation(loc)) {
										MobManager.spawn(m, loc, m.Amount());
									}
								}
								Bukkit.broadcastMessage("um preload foi feito! 1");
							}
						}
						else if(regionhasmob.get(r.getName()) == false) {
							if(!regionpreload.contains(r.getName())) {
								regionpreload.add(r.getName());
								regionhasmob.put(r.getName(), true);
								for(Entry<Location, Mobs> set : RegionManager.cachemob.entrySet()) {
									Mobs m = set.getValue();
									Location loc = set.getKey();
									if(r.getRegion().containsLocation(loc)) {
										MobManager.spawn(m, loc, m.Amount());
									}
								}
								Bukkit.broadcastMessage("um preload foi feito! 2");
							}
						}
					}
				}
			}
	}
	
	public static void forcepreload(Location l) {
		if(RegionManager.getPreRegion(l) != null) {
			Region r = RegionManager.getPreRegion(l);
			if(regionhasplayer.get(r.getName()) != null) {
				Bukkit.broadcastMessage("depois do regionhasplayer");
				if(regionhasplayer.get(r.getName()) == false) {
					if(regionhasmob.get(r.getName()) == null) {
						if(!regionpreload.contains(r.getName())) {
							regionpreload.add(r.getName());
							regionhasmob.put(r.getName(), true);
							for(Entry<Location, Mobs> set : RegionManager.cachemob.entrySet()) {
								Mobs m = set.getValue();
								Location loc = set.getKey();
								if(r.getRegion().containsLocation(loc)) {
									MobManager.spawn(m, loc, m.Amount());
								}
							}
							Bukkit.broadcastMessage("um preload foi feito! 1");
						}
					}
					else if(regionhasmob.get(r.getName()) == false) {
						if(!regionpreload.contains(r.getName())) {
							regionpreload.add(r.getName());
							regionhasmob.put(r.getName(), true);
							for(Entry<Location, Mobs> set : RegionManager.cachemob.entrySet()) {
								Mobs m = set.getValue();
								Location loc = set.getKey();
								if(r.getRegion().containsLocation(loc)) {
									MobManager.spawn(m, loc, m.Amount());
								}
							}
							Bukkit.broadcastMessage("um preload foi feito! 2");
						}
					}
				}
			}
		}
}
	
	public void registermob(String nome, Location loc) {
		for (Mobs m : MobManager.mobs) {
			if(m.name().equalsIgnoreCase(nome)) {
					RegionManager.cachemob.put(loc, m);
			}	
		}
	}
}
