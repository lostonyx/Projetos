package me.wiljafor1.utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class HCStrings {

	public static String allArgs(int start, String[] args) {
		String temp = "";
		for (int i = start; i < args.length; i++) {
			temp += args[i] + " ";
		}
		return temp.trim();
	}
	
	
	public static void sendCenteredMsg(Player player , String... s) {
		for(String str : s) {
			CenterChat.sendCenteredMessage(player, str);
		}
		
	}
	
	
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static String format(double value)
	  {
	    DecimalFormat numberFormat = new DecimalFormat("#");
	    
	    String d = numberFormat.format(value);
	    if (value >= 1.0E9D) {
	      return new DecimalFormat("0.0B").format(value * 1.0D / 1.0E9D);
	    }
	    if (value > 999999.0D) {
	      return new DecimalFormat("0.0M").format(value * 1.0D / 1000000.0D);
	    }
	    if (value >= 100.0D) {
	      return new DecimalFormat("0.0K").format(value * 1.0D / 1000.0D);
	    }
	    if(value < 100.0d){
	    	return "" + value;
	    }
	    return  "" + value;
	  }
	
	
	public static Player[] arrayToList(List<Player> i) {
		
		return i.toArray(new Player[i.size()]);
	}
	
	

public static class BukkitSerialization {
    public static String toBase64(Inventory inventory) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
         
            dataOutput.writeInt(inventory.getSize());
            
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            
         
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }        
    }
    
    public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
    
            // Read the serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}
}