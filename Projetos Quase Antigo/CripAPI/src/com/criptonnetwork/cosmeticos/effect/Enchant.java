package com.criptonnetwork.cosmeticos.effect;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.Main;
import com.criptonnetwork.cosmeticos.CosmeticPlayer;
import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.util.ItemAPI;

import de.slikey.effectlib.effect.AnimatedBallEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class Enchant implements Cosmetico{

	@Override
	public void use(Player p) {
		
		AnimatedBallEffect eff = new AnimatedBallEffect(Main.getEffManager());
		eff.setEntity(p);
		eff.yOffset = 0.5f;
		eff.iterations = 20 * 9999;
		eff.particle = ParticleEffect.REDSTONE;
		eff.color = Color.RED;
		eff.start();
		
		CosmeticPlayer.setEffect(p, eff);
	
		
	}

	@Override
	public String getName() {
		// TODO Auto-generoso metendo subindo
		return "Efeito de Sangue";
	}

	@Override
	public CosmeticType getType() {
		
		return CosmeticType.EFFECT;
	}

	@Override
	public ItemStack getIcon() {
		return ItemAPI.newItem(Material.BOOK, "§cEfeito de Sangue", 1, 0, "§7Cria um efeito de sangue!."
				, "");
	}

}
