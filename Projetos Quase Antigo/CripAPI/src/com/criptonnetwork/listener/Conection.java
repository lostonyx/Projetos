package com.criptonnetwork.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.criptonnetwork.Main;
import com.criptonnetwork.type.Gamer;
import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.util.GameAPI;
import com.criptonnetwork.util.HCStrings;
import com.criptonnetwork.util.ItemAPI;
import com.criptonnetwork.util.SasukeItem;
import com.criptonnetwork.util.Title;

import net.md_5.bungee.api.ChatColor;

public class Conection implements Listener {

	/**
	 * Colocar um player no manager(Pegar dados no mongoDB)
	 * 
	 *
	 *
	 */

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();

		Gamer g = GamerManager.createOne(p);
		g.setOnline(true);
		g.update();

		HCStrings.sendCenteredMsg(p, new String[] { "", "§6§lCRIPTON NETWORK", "", "§eSeja Bem-Vindo!",
				"§7Esperamos que você tenha um bom jogo!", "", });

		Bukkit.getOnlinePlayers().forEach(c -> {
			Title.sendTabTitle(p, "\n\n§cBem vindo a Cripton Network!\n" + "§cSe divirta jogando aqui!\n\n",
					"\n\n§fVips , Boosters e Items em: §cstore.criptonnetwork.com.br\n"
							+ "§fTwitter: §c@CriptonNetwork_\n" + "§fFacebook: §cNenhum\n" + "§fDiscord: §cNenhum");
		});

	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
	}

	/**
	 * Retirar um player no manager(Salvar dados no mongoDB)
	 * 
	 * 
	 *
	 */
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		Gamer g = GamerManager.getGamer(p);
		g.setOnline(false);
		g.update();
		GamerManager.remove(g);

	}

}
