package com.dayz.domination;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.dayz.Main;
import com.dayz.safezone.Safezone;
import com.dayz.safezone.SafezoneManager;
import com.dayz.type.JogadorManager;
import com.dayz.utils.WorldBorderApi;

import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.WorldBorder;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder.EnumWorldBorderAction;

public class WorldBorderListener {
	public static void checkWorldBorder(){
		    	for(Player v : Bukkit.getOnlinePlayers()){
		    		if(JogadorManager.getJogador(v).getCurrentAccount() != null){
						if(DominationManager.isOnDomination(v) == true){
							Domination area = DominationManager.getCurrentDomination(v);
							WorldBorderApi.removeWB(v);
						}
						else if(SafezoneManager.isOnSafeZone(v) == true){
							Safezone area = SafezoneManager.getCurrentSafezone(v);
							WorldBorderApi.removeWB(v);
						}
						else{
							if(DominationManager.getDominationPerDistance(v) != null){
								Domination area = DominationManager.getDominationPerDistance(v);
								
								WorldBorderApi.createWB(area.getCenter(), area.getC(), v , "RED", area.getSize());
							}
							else if(SafezoneManager.getSafezonePerDistance(v) != null){
								Safezone area = SafezoneManager.getSafezonePerDistance(v);
								WorldBorderApi.createWB(area.getCenter(), area.getC(), v, "BLUE", area.getSize());
							}
							else{
								WorldBorderApi.removeWB(v);
							}
						}
		    		}
		    	}
	}
}
