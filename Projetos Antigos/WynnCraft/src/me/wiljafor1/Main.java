package me.wiljafor1;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.keenant.tabbed.Tabbed;

import de.slikey.effectlib.EffectManager;
import me.wiljafor1.interfaces.City;
import me.wiljafor1.interfaces.Region;
import me.wiljafor1.interfaces.RegionManager;
import me.wiljafor1.listener.Mob;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.Classe;
import me.wiljafor1.models.ClasseManager;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.Database;
import me.wiljafor1.system.Tab;
import me.wiljafor1.system.cache.PreCuboId;
import me.wiljafor1.system.gameplay.vida;
import me.wiljafor1.system.mobs.MobManager;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.system.mobs.customentity.a.CustomVillager;
import me.wiljafor1.system.mobs.customentity.a.EntityGuard;
import me.wiljafor1.utils.ActionBar;
import me.wiljafor1.utils.Cooldown;
import me.wiljafor1.utils.SerializeApi;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.MinecraftKey;

public class Main extends JavaPlugin{
	public static Main instance;
	protected static EffectManager effManager;
	public static Tabbed tabbed;

	public void onEnable() {
		Bukkit.getWorlds().forEach(
				w -> w.getEntities().stream().filter(i -> i.getType() != EntityType.PLAYER).forEach(i -> i.remove()));
		registerEntity("villager_golem", 99, EntityIronGolem.class, EntityGuard.class);
		Database.start();
		instance = this;
		tabbed = new Tabbed(this);
		effManager = new EffectManager(this);
		RegisterUtils.getPackages(getFile(), "com.wynncraft").forEach(c -> {
			
			if (Listener.class.isAssignableFrom(c)) {
				try {
					getServer().getPluginManager().registerEvents((Listener) c.newInstance(), this);
				} catch (Exception e) {
					getServer().getConsoleSender()
							.sendMessage(ChatColor.RED + "(Evento):" + e.getMessage());
				}
			}
			if (Command.class.isAssignableFrom(c)) {
				try {
					RegisterUtils.createCommand((Command) c.newInstance());
				} catch (Exception e) {
					getServer().getConsoleSender()
							.sendMessage(ChatColor.RED + "(Comando):" + e.getMessage());
				}
			}
			if (Classe.class.isAssignableFrom(c)) {
				try {
					ClasseManager.add((Classe) c.newInstance());
				} catch (Exception e) {
				}
			}
			if (Mobs.class.isAssignableFrom(c)) {
				try {
					MobManager.add((Mobs) c.newInstance());
				} catch (Exception e) {
				}
			}
			if (Region.class.isAssignableFrom(c)) {
				try {
					RegionManager.addOne((Region) c.newInstance());
					getServer().getConsoleSender()
					.sendMessage(ChatColor.RED + ":" + c.getName());
				} catch (Exception e) {
				}
			}

		});
		load();
	}

	public void onDisable() {
		Bukkit.getWorlds().forEach(w -> {
			w.getEntities().stream().filter(e -> e.getType() != EntityType.PLAYER).forEach(e -> {
				e.remove();
			});

		});
	}

	
	public void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        try {
     
            List<Map<?, ?>> dataMap = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()){
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())){
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }
     
            if (dataMap.get(2).containsKey(id)){
                dataMap.get(0).remove(name);
                dataMap.get(2).remove(id);
            }
     
            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);
     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static Main GetInstance() {
		return instance;
	}

	public static EffectManager getEffManager() {
		return effManager;
	}
	
	public void load() {
		SimpleRunnable r = SimpleRunnable.getInstance();
		r.createTaskTimer(TaskType.SYNC, "Tab", new Runnable() {
			@Override
			public void run() {
				Tab.getInstance().simpleupdate();
				PreCuboId.Getinstance().start();
			}
		}, 0, 5 * 20);
		r.createTaskTimer(TaskType.ASYNC, "Mob", new Runnable() {
			@Override
			public void run() {
				Mob.follow();
			}
		}, 0, 0);
		r.createTaskTimer(TaskType.SYNC, "Vida-Mana", new Runnable() {
			@Override
			public void run() {
				vida.update();
				Bukkit.getOnlinePlayers().forEach(v -> {
					WynnPlayer w = WynnPlayerManager.getWynnPlayer(v);
					if (w.getCurrentAccount() != null) {
						Account a = w.getCurrentAccount();
						ActionBar ac = new ActionBar("§4\u2764 §c" + a.getHealth()
						+ "/"+ a.getMaxhealth() +"                        §b\u2739 §l" + a.getMana()+"/20");
						ac.sendToPlayer(v);
					}
				});
			}
		}, 0, 0);
		r.createTaskTimer(TaskType.SYNC, "update", new Runnable() {
			@Override
			public void run() {
				RegionManager.CheckBGM();
				Bukkit.getOnlinePlayers().forEach(v -> {
					WynnPlayer w = WynnPlayerManager.getWynnPlayer(v);
					if (w.getCurrentAccount() != null) {
						Account a = w.getCurrentAccount();
						if(RegionManager.getCurrentRegion(v.getLocation()) != null) {
							Cooldown cd = new Cooldown(v.getUniqueId(), "lastcity", 5);
							if (!cd.isInCooldown("lastcity")) {
								cd.start();
								City c = (City) RegionManager.getCurrentRegion(v.getLocation());
								a.setLastcity(c.id());
							}
						}
						String cooldownName = "update";
						int timeInSeconds = 120;
						Cooldown c = new Cooldown(v.getUniqueId(), cooldownName, timeInSeconds);
						if (!c.isInCooldown(cooldownName)) {
							c.start();
							a.setInv(v.getInventory());
							a.setLastLocSeri(v.getLocation());
							w.update();
						}
					}
				});
			}
		}, 0, 20);
		//PreCuboId.Getinstance().loadallsignsRegion();
		//-758.395, 67, -1573.537
	}

}
