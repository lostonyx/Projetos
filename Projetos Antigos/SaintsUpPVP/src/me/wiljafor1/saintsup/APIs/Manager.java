package me.wiljafor1.saintsup.APIs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Manager {

	public static boolean chat = true;

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, int quantidade, String nome) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material, quantidade);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, int quantidade, String nome, int data) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material, quantidade);
		stack.setDurability((short) data);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, String lore) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		List<String> lores = new ArrayList();
		lores.add(lore);
		stack2.setLore(lores);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, int data) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		stack.setDurability((short) data);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, List lore) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.setLore(lore);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, String lore,
			Enchantment encantamento, int numero) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, numero, true);
		List<String> lores = new ArrayList();
		lores.add(lore);
		stack2.setLore(lores);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, List lore,
			Enchantment encantamento, int numero) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, numero, true);
		stack2.setLore(lore);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, List lore,
			Enchantment encantamento, int c, Enchantment encantamento2, int d) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, c, true);
		stack2.addEnchant(encantamento2, d, true);
		stack2.setLore(lore);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, String lore,
			Enchantment encantamento, int c, Enchantment encantamento2, int d) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, c, true);
		stack2.addEnchant(encantamento2, d, true);
		List<String> lores = new ArrayList();
		lores.add(lore);
		stack2.setLore(lores);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, String lore, int data) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		stack.setDurability((short) data);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		List<String> lores = new ArrayList();
		lores.add(lore);
		stack2.setLore(lores);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, List lore, int data) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		stack.setDurability((short) data);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.setLore(lore);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, Enchantment encantamento) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, 1, true);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, Enchantment encantamento,
			int d) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, d, true);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static org.bukkit.inventory.ItemStack criarItem(Material material, String nome, Enchantment encantamento,
			int c, Enchantment encantamento2, int d) {
		org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack2.addEnchant(encantamento, c, true);
		stack2.addEnchant(encantamento2, d, true);
		stack.setItemMeta(stack2);
		return stack;
	}

	public static int getAmount(Player p, Material m) {
		int amount = 0;
		org.bukkit.inventory.ItemStack[] arrayOfItemStack;
		int j = (arrayOfItemStack = p.getInventory().getContents()).length;
		for (int i = 0; i < j; i++) {
			org.bukkit.inventory.ItemStack item = arrayOfItemStack[i];
			if ((item != null) && (item.getType() == m) && (item.getAmount() > 0)) {
				amount += item.getAmount();
			}
		}
		return amount;
	}

	public static org.bukkit.inventory.ItemStack addEnchant(org.bukkit.inventory.ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack itens = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = itens.getTag() == null ? new NBTTagCompound() : itens.getTag();
		NBTTagList ench = new NBTTagList();
		nbt.set("ench", ench);
		itens.setTag(nbt);
		return CraftItemStack.asBukkitCopy(itens);
	}

	public static void sendActionBar(Player p, String msg) {
		IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
	}

	public static boolean equals(ItemStack one, ItemStack two) {
		return one.isSimilar(two);
	}

	public static int getAmount(ItemStack item, Inventory inventory) {
		if (!inventory.contains(item.getType())) {
			return 0;
		}
		if (inventory.getType() == null) {
			return Integer.MAX_VALUE;
		}
		HashMap<Integer, ? extends ItemStack> items = inventory.all(item.getType());
		int itemAmount = 0;
		for (ItemStack iStack : items.values()) {
			if (equals(iStack, item)) {
				itemAmount += iStack.getAmount();
			}
		}
		return itemAmount;
	}

	public static int countItems(Map<Integer, ?> items) {
		int totalLeft = 0;
		for (Iterator localIterator = items.keySet().iterator(); localIterator.hasNext();) {
			int left = ((Integer) localIterator.next()).intValue();
			totalLeft += left;
		}
		return totalLeft;
	}

	public static int remove(ItemStack item, Inventory inventory) {
		Map<Integer, ItemStack> leftovers = inventory.removeItem(new ItemStack[] { item });

		return countItems(leftovers);
	}

	public static int getFreeSlots(Player p) {
		int slots = 36;

		for (ItemStack item : p.getInventory().getContents()) {

			if (item != null && item.getType() != Material.AIR) {
				slots--;
			}
		}
		return slots;
	}

	public static String convertItemStackToJson(ItemStack itemStack) {
		// ItemStack methods to get a net.minecraft.server.ItemStack object for
		// serialization
		Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
		Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);

		// NMS Method to serialize a net.minecraft.server.ItemStack to a valid
		// Json string
		Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
		Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
		Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

		Object nmsNbtTagCompoundObj; // This will just be an empty
										// NBTTagCompound instance to invoke the
										// saveNms method
		Object nmsItemStackObj; // This is the net.minecraft.server.ItemStack
								// object received from the asNMSCopy method
		Object itemAsJsonObject; // This is the net.minecraft.server.ItemStack
									// after being put through saveNmsItem
									// method

		try {
			nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
			nmsItemStackObj = asNMSCopyMethod.invoke(null, itemStack);
			itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
		} catch (Throwable t) {
			return null;
		}

		return itemAsJsonObject.toString();
	}

	public static void sendItemTooltipMessage(Player player, String message, ItemStack item) {
		String itemJson = convertItemStackToJson(item);

		BaseComponent[] hoverEventComponents = new BaseComponent[] { new TextComponent(itemJson) };
		HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);

		TextComponent component = new TextComponent(message);
		component.setHoverEvent(event);

		player.spigot().sendMessage(component);
	}
}
