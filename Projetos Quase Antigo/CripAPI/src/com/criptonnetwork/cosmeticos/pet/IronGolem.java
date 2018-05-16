package com.criptonnetwork.cosmeticos.pet;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.criptonnetwork.Main;
import com.criptonnetwork.cosmeticos.CosmeticPlayer;
import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.util.Hologram;
import com.criptonnetwork.util.ItemAPI;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.md_5.bungee.api.ChatColor;

public class IronGolem implements Cosmetico {

	@Override
	public void use(Player p) {
		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.IRON_GOLEM, "§ePet de " + p.getName());
		npc.spawn(p.getLocation());
		CosmeticPlayer.setPet(p, npc);
		new BukkitRunnable() {
			public void run() {
				if (CosmeticPlayer.getCurrentPet(p) != null) {
					npc.getNavigator().setTarget(p.getLocation());
				} else {
					cancel();
					
				}

			}
		}.runTaskTimer(Main.getPlugin(Main.class), 0, 20 * 2);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Guardiao de Iron";
	}

	@Override
	public ItemStack getIcon() {
		// TODO Auto-generated method stub
		return ItemAPI.newItem(Material.IRON_INGOT, "§e" + getName(), 1, 0, "§7Um incrivel guardião de ferro puro.");
	}

	@Override
	public CosmeticType getType() {
		// TODO Auto-generated method stub
		return CosmeticType.PET;
	}

}
