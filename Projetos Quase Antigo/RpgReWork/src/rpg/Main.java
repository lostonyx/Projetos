package rpg;


import static rpg.system.models.RegionManager.regionhasmob;
import static rpg.system.models.RegionManager.regionhasplayer;
import static rpg.system.models.RegionManager.regionpreload;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.keenant.tabbed.Tabbed;

import de.slikey.effectlib.EffectManager;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.*;
import rpg.mob.MobManager;
import rpg.mob.Mobs;
import rpg.mob.customentity.a.CustomVillager;
import rpg.mob.customentity.a.EntityGuard;
import rpg.mob.customentity.scared.*;
import rpg.system.Database;
import rpg.system.Metodos;
import rpg.system.Tab;
import rpg.system.vida;
import rpg.system.listener.MobListener;
import rpg.system.models.Account;
import rpg.system.models.City;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.models.Region;
import rpg.system.models.RegionManager;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellManager;
import rpg.utils.ActionBar;
import rpg.utils.ArmorStandAnimator;
import rpg.utils.Cooldown;
import rpg.utils.SerializeApi;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class Main extends JavaPlugin{
	public static Main instance;
	protected static EffectManager effManager;
	public static Tabbed tabbed;

	public void onEnable() {
		instance = this;
		tabbed = new Tabbed(this);
		effManager = new EffectManager(this);
		Database.start();
		registerEntity("villager_golem", 99, EntityIronGolem.class, EntityGuard.class);
		registerEntity("Skeleton_Assustado", 51, EntitySkeletonScared.class, EntitySkeleton.class);
		
		RegisterUtils.getPackages(getFile(), "rpg").forEach(c -> {
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
			if (Spell.class.isAssignableFrom(c)) {
				try {
					SpellManager.add((Spell) c.newInstance());
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
		Bukkit.getWorlds().forEach(
				w -> w.getEntities().stream().filter(i -> i.getType() != EntityType.PLAYER).forEach(i -> i.remove()));
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
			}
		}, 0, 5 * 20);
		r.createTaskTimer(TaskType.SYNC, "MobListener", new Runnable() {
			@Override
			public void run() {
				MobListener.follow();
			}
		}, 0, 0);
		r.createTaskTimer(TaskType.SYNC, "Vida-Mana", new Runnable() {
			@Override
			public void run() {
				ArmorStandAnimator.updateAll();
				vida.update();
				Bukkit.getOnlinePlayers().forEach(v -> {
					Jogador w = JogadorManager.getWynnPlayer(v);
					if (w.getCurrentAccount() != null) {
						Account a = w.getCurrentAccount();
						if(Metodos.msgaction.containsKey(v)) {
							ActionBar ac = new ActionBar("§4\u2764 §c" + a.getHealth()
							+ "/"+ a.getMaxhealth() +"         "+ Metodos.msgaction.get(v) +"        §b\u2739 §l" + a.getMana()+"/20");
							ac.sendToPlayer(v);
						}
						else {
							ActionBar ac = new ActionBar("§4\u2764 §c" + a.getHealth()
							+ "/"+ a.getMaxhealth() +"                        §b\u2739 §l" + a.getMana()+"/20");
							ac.sendToPlayer(v);
						}
					}
				});
			}
		}, 0, 0);
		r.createTaskTimer(TaskType.SYNC, "update", new Runnable() {
			@Override
			public void run() {
				RegionManager.CheckBGM();
				Bukkit.getOnlinePlayers().forEach(v -> {
					Jogador w = JogadorManager.getWynnPlayer(v);
					if (w.getCurrentAccount() != null) {
						Account a = w.getCurrentAccount();
						if(RegionManager.getCurrentRegion(v.getLocation()) != null) {
							if(RegionManager.getCurrentRegion(v.getLocation()) instanceof City) {
								Cooldown cd = new Cooldown(v.getUniqueId(), "lastcity", 5);
								if (!cd.isInCooldown("lastcity")) {
									cd.start();
									City c = (City) RegionManager.getCurrentRegion(v.getLocation());
									a.setLastcity(c.id());
								}
							}
						}
					}
				});
			}
		}, 0, 20);
		registerAll();
	}

	public void registerAll() {
		registerEntity("IronGolem_Assustado", 99, EntityIronGolem.class, EntityIronGolemScared.class);
		registerEntity("Creeper_Assustado", 50, EntityCreeper.class, EntityCreeperScared.class);
		registerEntity("Skeleton_Assustado", 51, EntitySkeleton.class, EntitySkeletonScared.class);
		registerEntity("Spider_Assustado", 52, EntitySpider.class, EntitySpiderScared.class);
		registerEntity("GiantZombie_Assustado", 53, EntityGiantZombie.class, EntityGiantScared.class);
		registerEntity("Zombie_Assustado", 54, EntityZombie.class, EntityZombieScared.class);
		//registerEntity("villager_Assustado", 55, EntitySlime.class, EntitySlimeScared.class);
		//registerEntity("villager_Assustado", 56, EntityGhast.class, EntityGhastScared.class);
		registerEntity("PigZombie_Assustado", 57, EntityPigZombie.class, EntityPigZombieScared.class);
		registerEntity("Enderman_Assustado", 58, EntityEnderman.class, EntityEndermanScared.class);
		registerEntity("CaveSpider_Assustado", 59, EntityCaveSpider.class, EntityCaveSpiderScared.class);
		//registerEntity("villager_Assustado", 60, EntitySilverfish.class, EntitySilverfishScared.class);
		registerEntity("Blaze_Assustado", 61, EntityBlaze.class, EntityBlazeScared.class);
		//registerEntity("MagmaCube_Assustado", 62, EntityMagmaCube.class, EntityMagmaCubeScared.class);
		//registerEntity("villager_Assustado", 63, EntityEnderDragon.class, EntityEnderDragonScared.class);
		registerEntity("Wither_Assustado", 64, EntityWither.class, EntityWitherScared.class);
		//registerEntity("villager_Assustado", 65, EntityBat.class, EntityBatScared.class);
		registerEntity("Witch_Assustado", 66, EntityWitch.class, EntityWitchScared.class);
		registerEntity("Pig_Assustado", 90, EntityPig.class, EntityPigScared.class);
		registerEntity("Sheep_Assustado", 91, EntitySheep.class, EntitySheepScared.class);
		registerEntity("Cow_Assustado", 92, EntityCow.class, EntityCowScared.class);
		registerEntity("Chicken_Assustado", 93, EntityChicken.class, EntityChickenScared.class);
		//registerEntity("Squid_Assustado", 94, EntitySquid.class, EntitySquidScared.class);
		registerEntity("Wolf_Assustado", 95, EntityWolf.class, EntityWolfScared.class);
		registerEntity("MushroomCow_Assustado", 96, EntityMushroomCow.class, EntityMushroomCowScared.class);
		registerEntity("Snowman_Assustado", 97, EntitySnowman.class, EntitySnowGolemScared.class);
		registerEntity("Ocelot_Assustado", 98, EntityOcelot.class, EntityOcelotScared.class);
		registerEntity("IronGolem_Assustado", 99, EntityIronGolem.class, EntityIronGolemScared.class);
		registerEntity("Horse_Assustado", 100, EntityHorse.class, EntityHorseScared.class);
		registerEntity("Villager_Assustado", 120, EntityVillager.class, EntityVillagerScared.class);
		registerEntity("Guardian_Assustado", 68, EntityGuardian.class, EntityGuardianScared.class);
		registerEntity("Rabbit_Assustado", 101, EntityRabbit.class, EntityRabbitScared.class);
		registerEntity("Endermite_Assustado", 67, EntityEndermite.class, EntityEndermiteScared.class);
	}
}
