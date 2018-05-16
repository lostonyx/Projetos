package me.wiljafor1.utils;

import org.bukkit.entity.*;
import org.bukkit.*;

import org.bukkit.plugin.*;

import me.wiljafor1.Main;


public class HologramApi
{
    public static ArmorStand createHologram(Location loc, String firstLine) {
        ArmorStand a = loc.getWorld().spawn(loc, ArmorStand.class);
        a.setSmall(true);
        a.setMarker(true);
        a.setVisible(false);
        a.setCustomName(firstLine);
        a.setCustomNameVisible(true);
        return a;
    }
    
    public static void sendHolo(Location loc, int time, String... s) {
        for (String str : s) {
            ArmorStand a = createHologram(loc.add(0.0, 0.08, 0.0), str);
            doAction(() -> a.remove(), time);
        }
    }
    
    public static void doAction(Runnable r, int i) {
        Bukkit.getScheduler().runTaskLater(Main.GetInstance(), r, i);
    }
}