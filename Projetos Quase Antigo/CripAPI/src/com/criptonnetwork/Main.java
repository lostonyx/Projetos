package com.criptonnetwork;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.criptonnetwork.cosmeticos.CosmeticPlayer;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.cosmeticos.CosmeticoManager;
import com.criptonnetwork.database.Database;
import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.type.Pacote;
import com.criptonnetwork.type.PacoteManager;
import com.criptonnetwork.util.SimpleItem;
import com.criptonnetwork.util.Tag;
import com.criptonnetwork.type.Pacote.PacoteType;

import de.slikey.effectlib.EffectManager;
import net.citizensnpcs.api.npc.NPC;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public static Main instancia;
	protected static EffectManager effManager;
	public void onEnable() {
		Database.start();
		instancia = this;
		effManager = new EffectManager(this);
		
		Pacote p = PacoteManager.add(new Pacote("VIP", (int) 60.0, 20, PacoteType.VIP));
		p.setIcon(SimpleItem.createItem(Material.GOLD_CHESTPLATE, "§eVip 1", 1, 0, "§7Vip §eLorde§7 que tem um poder imensuravel!" 
				,"" ,
				"§eVantagems:" , 
				" §7Slot Reservado",
				" §7Kit Mensal - 30d de cooldown" , 
				" §7Kit Semanal - 7d de Cooldown" , 
				" §7Kit Diario - 24h de Cooldown"));
		
		getServer().getOnlinePlayers().forEach(v -> {
			GamerManager.createOne(v);
		});
		
		
		Runnable r =  () -> { 
			Bukkit.getOnlinePlayers().forEach(players -> {
				
				
				
				
			});
		};
		
		Bukkit.getScheduler().runTaskTimer(this, () -> {
			Ranking.broadCast();
		}, 0, (60 * 20) * 5);
		RegisterUtils.getPackages(getFile(), "com.criptonnetwork").forEach(c -> {//constumer - Lambda - nao retorna um valor

			if (Listener.class.isAssignableFrom(c)) {//aqui pega a classe
				try {
					getServer().getPluginManager().registerEvents((Listener) c.newInstance(), this);
				} catch (Exception e) {
					getServer().getConsoleSender().sendMessage(ChatColor.RED + "(Evento)Error ao tentar carregar :" + e.getMessage());
				}
			}

			if (Command.class.isAssignableFrom(c)) {//aqui pega a classe
				try {
					RegisterUtils.createCommand((Command) c.newInstance());
				} catch (Exception e) {
					getServer().getConsoleSender().sendMessage(ChatColor.RED + "(Comando)Error ao tentar carregar :" + e.getMessage());
				}
			}
			
			if(Cosmetico.class.isAssignableFrom(c)) {
				try {
					CosmeticoManager.add((Cosmetico)c.newInstance());
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

		});
	}
	
	public void onDisable() {
		for( Entry<Player, NPC> set : CosmeticPlayer.pets.entrySet()) {
			Player p = set.getKey();
			NPC npc = set.getValue();
			npc.despawn();
			npc.destroy();
		}
		
		Bukkit.getWorlds().forEach(w -> {
			w.getEntities().stream().filter(e -> e.getType() != EntityType.PLAYER).forEach(e -> {
				e.remove();
			});
			
		});
		
	}
	
	public static Main PegarInstancia(){
		return instancia;
	}
	
	public static EffectManager getEffManager() {
		return effManager;
	}
	

}
