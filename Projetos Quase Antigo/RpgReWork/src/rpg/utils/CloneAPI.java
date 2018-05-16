package rpg.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.slikey.effectlib.effect.SphereEffect;
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.VectorUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;
import net.citizensnpcs.npc.skin.Skin;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import rpg.Config;
import rpg.Main;

public class CloneAPI {
/*
	public static final HashMap<Player, List<NPC>> clone = Maps.newHashMap();
	public static final HashMap<NPC, Player> targets = Maps.newHashMap();
	public static final List<Integer> CLONE_DATA = Lists.newArrayList();

	public static Config cfg = new Config("npcs.yml");

	public static Player getTarget(NPC npc) {
		return targets.get(npc);

	}

	public static NPC spawnClone(Player owner, String name, String skin) {
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
		getClones(owner).add(npc);
		if (npc != null) {
			if (npc.getEntity() instanceof SkinnableEntity) {
				SkinnableEntity n = (SkinnableEntity) npc.getEntity();
				n.setSkinName(skin);
				Skin.get(n).apply(n);
			}
		}
		npc.spawn(owner.getLocation().add(Extra.getRandom(0, 5), 0, Extra.getRandom(0, 5)));

		if (!name.equalsIgnoreCase(skin)) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc sel " + npc.getId());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc skin " + skin);
		}

		npc.setFlyable(false);
		npc.setProtected(false);

		CLONE_DATA.add(npc.getId());
		cfg.set("npcs", CLONE_DATA);
		cfg.saveConfig();

		return npc;
	}

	public static void setTarget(NPC npc, Player i) {
		targets.put(npc, i);
	}

	public static void update() {
		for (Entry<Player, List<NPC>> set : clone.entrySet()) {

			for (NPC npc : set.getValue()) {
				Entity p = getNearestEntity(getOwner(npc), 100);

				if (getOwner(npc).getItemInHand() != null) {
					npc.getTrait(Equipment.class).set(EquipmentSlot.HAND, getOwner(npc).getItemInHand());
				}

				if (getOwner(npc) != null && getOwner(npc).isSneaking()) {
					npc.getNavigator().setTarget(getOwner(npc), false);
				} else if (p != getOwner(npc) && p != null && p != npc.getEntity() && p instanceof Damageable
						&& npc.isSpawned() && (!getOwner(npc).isSneaking()) && !CitizensAPI.getNPCRegistry().isNPC(p)) {

					npc.getNavigator().setTarget(p, true);

					if (0.05 <= 0.05) {//nextDouble()
						SphereEffect s = new SphereEffect(Main.GetInstance().getEffManager());
						s.radius = 5;
						s.particle = ParticleEffect.ENCHANTMENT_TABLE;
						s.asynchronous = false;
						s.particleCount = 0;
						s.iterations = 20 * 2;
						s.setEntity(npc.getEntity());
						s.start();
						if (npc.getFullName().contains("Pain")) {
							npc.getEntity().getNearbyEntities(10, 10, 10).stream()
									.filter(e -> e instanceof Damageable && e != getOwner(npc)).forEach(d -> {
										((Damageable) d).damage(8);
										d.setVelocity(d.getLocation().getDirection().multiply(-2).setY(2));
									});
						}
					}
				}

			}

		}

	}

	public static void remove(Player p) {
		for (NPC npc : getClones(p)) {
			npc.despawn();
			npc.destroy();
		}
	}

	public static void setSkin(NPC npc, String name, String prop, String sign) {

		npc.data().remove(NPC.PLAYER_SKIN_UUID_METADATA);
		npc.data().remove(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA);
		npc.data().remove(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA);
		npc.data().remove("cached-skin-uuid-name");
		npc.data().remove("cached-skin-uuid");
		npc.data().remove(NPC.PLAYER_SKIN_UUID_METADATA);

		npc.data().set(NPC.PLAYER_SKIN_USE_LATEST, false);
		npc.data().set("cached-skin-uuid-name", name);
		npc.data().set("cached-skin-uuid", UUID.randomUUID());
		npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, UUID.randomUUID());
		npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_METADATA, prop);
		npc.data().setPersistent(NPC.PLAYER_SKIN_TEXTURE_PROPERTIES_SIGN_METADATA, sign);

		if (npc.getEntity() instanceof SkinnableEntity) {
			if (npc.isSpawned()) {
				SkinnableEntity s = (SkinnableEntity) npc.getEntity();
				Skin.get(s).applyAndRespawn(s);
			}
		}

	}

	public static List<NPC> getClones(Player p) {
		if (clone.get(p) == null) {
			clone.put(p, Lists.newArrayList());
		}
		return clone.get(p);
	}

	public static Player getOwner(NPC npc) {

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (getClones(p) != null) {
				if (getClones(p).contains(npc)) {
					return p;
				}
			}
		}

		return null;
	}

	class CloneListener implements Listener {

		@EventHandler
		public void onNPC(NPCDeathEvent e) {
			NPC npc = e.getNPC();
			if (getTarget(npc) != null)
				setTarget(npc, null);
			if (getOwner(npc) != null) {

				Player o = getOwner(npc);
				getClones(o).remove(npc);
				npc.destroy();
			}

		}

		@EventHandler
		public void despawn(NPCDespawnEvent e) {
			if (getOwner(e.getNPC()) != null) {
				e.getNPC().destroy();
			}

		}

		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (!(e.getEntity() instanceof Player))
				return;
			if (!(e.getDamager() instanceof Player))
				return;

			Player p = (Player) e.getEntity();
			Player l = (Player) e.getDamager();

			if (!getClones(p).isEmpty()) {
				getClones(p).forEach(npc -> {
					if (npc.isSpawned()) {
						setTarget(npc, l);
					}
				});
			}

		}
	}
	
	public static List<String> getLivingEntities(String argument) {
        List<String> list = new ArrayList<String>();
        argument = argument.trim().replace("_", "");
        EntityType[] values;
        for (int length = (values = EntityType.values()).length, i = 0; i < length; ++i) {
            EntityType type = values[i];
            if (type != EntityType.PLAYER) {
                if (type.isAlive() & type.isSpawnable()) {
                    String text = Extra.toTitle(type.name(), "");
                    String line = type.name().trim().replace("_", "");
                    if (Extra.startWith(line, argument)) {
                        list.add(text);
                    }
                }
            }
        }
        return list;
    }
*/
}
