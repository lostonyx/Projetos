package com.dayz.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

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
}
