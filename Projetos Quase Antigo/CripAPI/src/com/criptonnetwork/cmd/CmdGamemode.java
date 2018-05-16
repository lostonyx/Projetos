package com.criptonnetwork.cmd;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CmdGamemode implements Listener {

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		String[] str = new String[] { "/gmc", "/gm 1", "/gamemode 1", "/gamemode creativo", "/gamemode c", "/gm c" };
		String[] st = new String[] { "/gms", "/gm 0", "/gamemode 0", "/gamemode survival", "/gamemode s", "/gm s" };
		String[] sta = new String[] { "/gma", "/gm 2", "/gamemode 2", "/gamemode adventure", "/gamemode a", "/gm a" };
		String[] sts = new String[] { "/gms", "/gm 3", "/gamemode 3", "/gamemode spectator", "/gamemode s", "/gm s" };

		for (String s : str) {
			if (e.getMessage().equalsIgnoreCase(s)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cGamemode> §7Seu gamemode foi setado para §ecreativo!");
				e.getPlayer().setGameMode(GameMode.CREATIVE);

			}
		}

		for (String s : sts) {
			if (e.getMessage().equalsIgnoreCase(s)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cGamemode> §7Seu gamemode foi setado para §eespectador!");
				e.getPlayer().setGameMode(GameMode.SPECTATOR);
			}
		}

		for (String s : sta) {
			if (e.getMessage().equalsIgnoreCase(s)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cGamemode> §7Seu gamemode foi setado para §eadventure!");
				e.getPlayer().setGameMode(GameMode.ADVENTURE);

			}
		}

		for (String s : st) {
			if (e.getMessage().equalsIgnoreCase(s)) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cGamemode> §7Seu gamemode foi setado para §esurvival!");
				e.getPlayer().setGameMode(GameMode.SURVIVAL);

			}
		}
	}

}
