package com.dayz.domination;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.util.BukkitAPI;
import com.criptonnetwork.util.Cooldown;
import com.criptonnetwork.util.Cuboid;
import com.criptonnetwork.util.HCStrings;
import com.dayz.Main;
import com.dayz.safezone.SafezoneManager;
import com.dayz.type.JogadorManager;
import com.dayz.utils.WorldBorderApi;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder.EnumWorldBorderAction;
import net.minecraft.server.v1_8_R3.WorldBorder;

public class DominationListener {
	static Map<String, Integer> timeplayer = new HashMap<String, Integer>();

	public static void checkdomination() {
				for (Player v : Bukkit.getOnlinePlayers()) {
					if (JogadorManager.getJogador(v).getCurrentAccount() != null) {
						if (DominationManager.isOnDomination(v) == true) {
							Domination area = DominationManager.getCurrentDomination(v);
							Location center = area.getCenter();
							CraftPlayer pp = (CraftPlayer) v;
							WorldBorder wb = new WorldBorder();
							if (timeplayer.get(v.getName()) == null) {
								timeplayer.put(v.getName(), area.getTime());
							} else {
								if (area.getNome().contains(v.getName())) {
									UUID id = v.getUniqueId();
									String cooldownName = "domination";
									int timeInSeconds = 30;
									Cooldown c = new Cooldown(id, cooldownName, timeInSeconds);
									if(!c.isInCooldown(id, cooldownName)) {
										c.start();
										v.sendTitle(ChatColor.RED+"Domination", ChatColor.GOLD+"Voce ja dominou essa area!");
									}
								} else {
									int tempo = timeplayer.get(v.getName());
									if (tempo - 1 < 1) {
										UUID id = v.getUniqueId();
										String cooldownName = "domination";
										int timeInSeconds = 30;
										Cooldown c = new Cooldown(id, cooldownName, timeInSeconds);
										c.start();
										v.sendTitle(ChatColor.RED+"Domination", ChatColor.GOLD+"Voce dominou a area: " + area.getNome());
										area.setNome(v.getName());
										int tempoatual = area.getTime();
										area.setTime(tempoatual * 2);
									} else {
										v.setTotalExperience(tempo - 1);
										timeplayer.put(v.getName(), tempo - 1);
									}

								}
							}
						} else {
							if (timeplayer.get(v.getName()) != null) {
								timeplayer.remove(v.getName());
							}
						}
					}
				}
	}
	@EventHandler
	public void OnQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (timeplayer.get(p.getName()) != null) {
			timeplayer.remove(p.getName());
		}
	}

}
