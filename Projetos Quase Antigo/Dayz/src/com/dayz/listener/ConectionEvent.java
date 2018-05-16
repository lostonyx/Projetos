package com.dayz.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.type.Gamer;
import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.util.ProgressBar;
import com.criptonnetwork.util.SasukeScore;
import com.dayz.gui.GuiAccountSelect;
import com.dayz.gui.GuiAccountSelect;
import com.dayz.gui.GuiCreateFirst;
import com.dayz.type.Account;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.dayz.utils.PlayerList;
import com.dayz.utils.SerializeApi;

import net.md_5.bungee.api.ChatColor;

public class ConectionEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
		Jogador j = JogadorManager.createOne(p);
		Gamer g = GamerManager.getGamer(p);
		g.setLastServer("DAYZ-1");
		g.update();
		// SalvarPerfil(p, 0);
		Score(p);
		PlayerList playerList = PlayerList.getPlayerList(p);
		//If you are using a server version lower than 1.8, then use SIZE_DEFAULT
		//as only 20 tabs are shown for older versions.
		 
		/**
		* In case you want to retrieve an existing tablist, use PlayerList.getPlayerList(Player)
		*/
		 
		//Creating a full board.
		playerList.initTable();
		 
		//Removes the player from the list
		//NOTE If you use this on the player with the tablist, then this will disable the player from 
		//being able to go into spectator mode, and will stop the user from being able to fly through blocks if they are in spectator mode.
		playerList.removePlayer(p);
		 
		//Adding a custom player
		//by default, the slots will use stone textures as the head texture, so you do not need to see
		//steve and alex skins for all of your tabs. If you wish for the tab to have the skin of the name
		//provided, add ' , true' after the name (as  shown below).
		playerList.updateSlot(0,"Top left");
		playerList.updateSlot(19,"Bottom left");
		playerList.updateSlot(60,"Top right");
		playerList.updateSlot(79,"Bottom right");
		 
		//Adds an existing tablist with the player's skin for the tablist. Only works in 1.8+
		playerList.updateSlot(44,"Notch",true);
		 
		//Sets the HeaderFooter.
		playerList.setHeaderFooter(ChatColor.GOLD+"Welcome","Test Message");
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Jogador j = JogadorManager.getJogador(p);
		SalvarPerfil(p);
		JogadorManager.remove(j);
		p.getInventory().clear();
		Jogador.scores.remove(p);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Jogador j = JogadorManager.getJogador(player);
		if(j.getContas().isEmpty()) {
			GuiCreateFirst.open(player);
		} else if(j.getCurrentAccount() == null) {
			GuiAccountSelect.open(player);
		}
		
		
		//player.sendMessage("§fNumero de Contas: §c" + j.getContas().size());
		//player.sendMessage("§fCurrent Account actived: " + j.getCurrentAccount() != null ? "§aYes" : "§cNo");

	}

	public static void Score(Player player) {
		Jogador j = JogadorManager.getJogador(player);
		SasukeScore s = new SasukeScore("§c§lDAYZ");
		Account a = j.getCurrentAccount();
		
		if(a != null) {
			s.add("§fVida: §c"+ a.getVida().intValue() +"/100");
			s.add("§7");
			s.add("§fKills: §c" + a.getKills());
			s.add("§fDeaths: §c" + a.getDeaths());
			s.add("§1");
			s.add("§fZombie Kills: §c" + a.getTotalzombies());
			s.add("§2");
			s.add("§fProgresso: §a" + a.getXp() +"/"+ (a.getLevel() * a.getLevel() )* 500);
			s.add("   §8[" + ProgressBar.getProgressBar(a.getXp(), (a.getLevel() * a.getLevel()) * 500, 10, "■", "§a", "§f") + "§8]");
			s.add("§3");
			s.add("§6www.criptonnetwork.com.br");
			s.build();
			s.send(player);
			Jogador.scores.put(player, s);
		}else {
			player.sendTitle("§eEscolha seu Personagem", "");

		}
		
	
	
	}

	public void SalvarPerfil(Player p) {
		Jogador j = JogadorManager.getJogador(p);
		Account perfil = j.getCurrentAccount();
		perfil.setInv(p.getInventory());
		perfil.setUltimolocal(SerializeApi.SerializarLoc(p.getLocation()));
		j.update();
	}
}
