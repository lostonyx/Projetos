package com.criptonnetwork.cosmeticos.effect;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.criptonnetwork.Main;
import com.criptonnetwork.cosmeticos.CosmeticPlayer;
import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.util.Cooldown;
import com.criptonnetwork.util.ItemAPI;

import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class Flame implements Cosmetico{

	@Override
	public void use(Player p) {
		
		CircleEffect eff = new CircleEffect(Main.getEffManager());
		eff.radius = 1F;
		eff.particle = ParticleEffect.FLAME;
		eff.speed = 0f;
		eff.enableRotation = false;
		eff.iterations = 20 * 9999;
		eff.setEntity(p);
		eff.start();
		
		CosmeticPlayer.setEffect(p, eff);
	
		
	}

	@Override
	public String getName() {
		// TODO Auto-generoso metendo subindo
		return "Efeito de Chamas";
	}

	@Override
	public CosmeticType getType() {
		
		return CosmeticType.EFFECT;
	}

	@Override
	public ItemStack getIcon() {
		return ItemAPI.newItem(Material.BLAZE_POWDER, "§cEfeito de Chamas", 1, 0, "§7Cria um circulo de fogo que o contorna."
				, "");
	}

}
