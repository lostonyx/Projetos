package me.wiljafor1.utils;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import gyurix.protocol.wrappers.outpackets.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.EnumWorldBorderState;
import net.minecraft.server.v1_8_R3.IWorldBorderListener;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder.EnumWorldBorderAction;
import net.minecraft.server.v1_8_R3.WorldBorder;

public class WorldBorderApi {
    private static List<IWorldBorderListener> a = Lists.newArrayList();
	public static WorldBorder wdb;

	public static void createWB(Location center, Cuboid c, Player p, String color, int size) {
		WorldBorder wb = new WorldBorder();
		wb.setCenter(center.getX(), center.getZ());
		wb.setSize(size-10);
		wdb = wb;
		SendPacket(p, color);
	}

	public static void removeWB(Player p) {
		CraftPlayer player = (CraftPlayer) p;
		final PacketPlayOutWorldBorder wbs = new PacketPlayOutWorldBorder();
		//PacketPlayOutWorldBorder wbs = new PacketPlayOutWorldBorder(wdb, EnumWorldBorderAction.INITIALIZE);
		wbs.warningTime = 0;
		wbs.warningBlocks = 0;
		wbs.time = 10000000L;
		wbs.oldRadius = 0;
		wbs.newRadius = 0;
		wbs.centerX = 0;
		wbs.centerZ = 0;
		wbs.action = PacketPlayOutWorldBorder.WorldBorderAction.INITIALIZE;
		wbs.portalTeleportBoundary = 999999999;
		player.getHandle().playerConnection.sendPacket((Packet) wbs.getVanillaPacket());
		//p.getWorld().getWorldBorder().reset();
	}

	public static void SendPacket(Player p, String s) {
		CraftPlayer player = (CraftPlayer) p;
		final PacketPlayOutWorldBorder wbs = new PacketPlayOutWorldBorder();
		//PacketPlayOutWorldBorder wbs = new PacketPlayOutWorldBorder(wdb, EnumWorldBorderAction.INITIALIZE);
		wbs.warningTime = 50;
		wbs.warningBlocks = 10 * 2;
		wbs.time = (s.equals("BLUE") ? 0L : 10000000L);
		wbs.oldRadius = wdb.getSize() * 2.0;
		wbs.newRadius = (s.equals("RED") ? (wdb.getSize() * 2.0 - 0.01) : (s.equals("GREEN") ? (wdb.getSize() * 2.0 + 0.01) : (wdb.getSize() * 2.0)));
		wbs.centerX = wdb.getCenterX();
		wbs.centerZ = wdb.getCenterZ();
		wbs.action = PacketPlayOutWorldBorder.WorldBorderAction.INITIALIZE;
		wbs.portalTeleportBoundary = 999999999;
		player.getHandle().playerConnection.sendPacket((Packet) wbs.getVanillaPacket());
	}
	
}
