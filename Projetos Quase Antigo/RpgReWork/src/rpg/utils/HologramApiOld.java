package rpg.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

public class HologramApiOld {

    private List<ArmorStand> lines;

    public HologramApiOld(Location loc, String firstLine) {
        lines = Lists.newArrayList();
        spawnStand(loc, firstLine);

    }

    private ArmorStand spawnStand(Location loc, String customName) {
        ArmorStand a = loc.getWorld().spawn(loc, ArmorStand.class);
        a.setVisible(false);
        a.setMarker(true);
        a.setSmall(true);
        a.setGravity(false);
        a.setCustomNameVisible(true);
        a.setCustomName(customName);
        lines.add(a);
        return a;
    }

    public void remove() {
        lines.forEach(a -> a.remove());
    }

    public void teleport(Location loc) {
        double y = -0;
        int i = lines.size();
        for (ArmorStand a : lines) {
            i--;
            if (i <= 0)
                break;
            a.teleport(loc.add(0, y, 0));
            y -= 0.08D;

        }
    }

    public void setLine(int index, String line) {
        ArmorStand a = lines.get(index);
        if (a != null) {
            a.setCustomName(line);
        }
    }

    public void addLine(String line) {
        Location loc = lines.get(0).getLocation();
        spawnStand(loc.add(0, -0.08, 0), line);
    }

    public void showFor(Player... players) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (Player player : players) {
                if (!p.equals(player)) {
                    lines.forEach(a -> ((CraftPlayer) p).getHandle().playerConnection
                            .sendPacket(new PacketPlayOutEntityDestroy(a.getEntityId())));

                }
            }
        }
    }

}