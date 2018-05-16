package com.criptonnetwork.cosmeticos.effect;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.Main;
import com.criptonnetwork.cosmeticos.CosmeticPlayer;
import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.util.ItemAPI;

import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class Coral implements Cosmetico{

	@Override
	public void use(Player p) {
		CircleEffect ce = new CircleEffect(Main.getEffManager());
		ce.radius = 0.7f;
		ce.particle = ParticleEffect.BLOCK_CRACK;
		ce.material = Material.DEAD_BUSH;
		ce.enableRotation = false;
		ce.setEntity(p);
		ce.iterations = 20 * 99999;
		
		ce.speed = 0f;
		ce.start();
		CosmeticPlayer.setEffect(p, ce);

		
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Efeito de Coral";
	}

	@Override
	public ItemStack getIcon() {
		// TODO Auto-generated method stub
		return ItemAPI.newItem(Material.INK_SACK, "§eCoral do mar");
		
	}

	@Override
	public CosmeticType getType() {
		// TODO Auto-generated method stub
		return CosmeticType.EFFECT;
	}

}
