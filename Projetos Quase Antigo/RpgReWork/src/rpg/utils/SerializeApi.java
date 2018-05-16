package rpg.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
/*
 * 
 * @author Wiljafor1 & SasukeMCHCs
 * 
 */
public class SerializeApi {
	public static String Serializar(Inventory inventory) {
        return Serializar(inventory.getContents());
    }
 
    public static String Serializar(ItemStack[] contents) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
 
            dataOutput.writeInt(contents.length);
 
            for (ItemStack stack : contents) {
                dataOutput.writeObject(stack);
            }
 
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }
 
    public static Inventory DeserializarInventory(String data) throws IOException {
        ItemStack[] stacks = DeserializarStacks(data);
        Inventory inventory = Bukkit.createInventory(null, (int)Math.ceil(stacks.length / 9D) * 9);
 
        for (int i = 0; i < stacks.length; i++) {
            inventory.setItem(i, stacks[i]);
        }
 
        return inventory;
    }
 
    public static ItemStack[] DeserializarStacks(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] stacks = new ItemStack[dataInput.readInt()];
 
            for (int i = 0; i < stacks.length; i++) {
                stacks[i] = (ItemStack) dataInput.readObject();
            }
            dataInput.close();
            return stacks;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
    
    public static String SerializarLoc(Location loc){
    	return loc.getWorld().getName()+":"+loc.getBlockX()+":"+loc.getBlockY()+":"+loc.getBlockZ();
    }
    
    public static Location DeserializarLoc(String loc){
    	String[] splited = loc.split(":");

        World world = Bukkit.getServer().getWorld(splited[0]);
        int x = Integer.parseInt(splited[1]);
        int y = Integer.parseInt(splited[2]);
        int z = Integer.parseInt(splited[3]);
        return new Location(world, x, y, z);
    }
	public static String format(long time) {
		String format = "";
		long hours = TimeUnit.MILLISECONDS.toHours(time);
		long hoursInMillis = TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(time - hoursInMillis);
		long minutesInMillis = TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(time - (hoursInMillis + minutesInMillis));
		int days = (int) (time / (1000*60*60*24));
		if (hours > 0)
			if (days > 0) {
				time = time - TimeUnit.DAYS.toMillis(days);
				hours = TimeUnit.MILLISECONDS.toHours(time - minutesInMillis);
				format = days + " dias, " + hours + (hours > 1 ? " horas" : " hora");
				return format;
			} else {		
				format = hours + (hours > 1 ? " horas" : " hora");
			}
		if (minutes > 0) {
			if ((seconds > 0) && (hours > 0))
				format += ", ";
			else if (hours > 0)
				format += " e ";
			format += minutes + (minutes > 1 ? " minutos" : " minuto");
		}
		if (seconds > 0) {
			if ((hours > 0) || (minutes > 0))
				format += " e ";
			format += seconds + (seconds > 1 ? " segundos" : " segundo");
		}
		if (format.equals("")) {
			long rest = time / 100;
			if (rest == 0)
				rest = 1;
			format = "0." + rest + " segundo";
		}
		if (days > 0){
			format = days + " dias";
		}
		return format;
	}
	
}
