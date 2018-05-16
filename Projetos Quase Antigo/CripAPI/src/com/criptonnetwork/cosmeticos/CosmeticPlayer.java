package com.criptonnetwork.cosmeticos;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.slikey.effectlib.Effect;
import net.citizensnpcs.api.npc.NPC;

public class CosmeticPlayer {

	public static HashMap<Player, Effect> effects = Maps.newHashMap();
	public static HashMap<Player, NPC> pets = Maps.newHashMap();
	
	
	public static NPC getCurrentPet(Player player) {
		return pets.get(player);
	}
	
	public static void removePet(Player player , NPC cos) {
		if(getCurrentPet(player) != null) {
			if(getCurrentPet(player).getEntity().getPassenger() != null) {
				getCurrentPet(player).getEntity().getPassenger().remove();
			}
			pets.get(player).despawn();
			pets.get(player).destroy();
			pets.remove(player);
		}
	}
	
	
	public static void setPet(Player player , NPC cos) {
		
		removePet(player, cos);
		pets.put(player, cos);
	}
	
	
	

	public static Effect getPlayerEffect(Player player) {
		return effects.get(player);

	}

	public static void setEffect(Player player, Effect effect) {
		if (getPlayerEffect(player) != null) {
			getPlayerEffect(player).cancel();
			effects.remove(player);
		}
		effects.put(player, effect);
	}
}
