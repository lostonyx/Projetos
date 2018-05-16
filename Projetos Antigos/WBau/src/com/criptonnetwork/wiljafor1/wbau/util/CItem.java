package com.criptonnetwork.wiljafor1.wreferencia.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

public class CItem
{
  private ItemStack a;
  private boolean b;
  private boolean c;

  public CItem(Material material)
  {
    this.a = new ItemStack(material, 1);
    this.b = false;
    this.c = false;
  }

  public CItem(int materialId)
  {
    this.a = new ItemStack(Material.getMaterial(materialId), 1);
    this.b = false;
    this.c = false;
  }

  public CItem(ItemStack itemStack) {
    this.a = itemStack.clone();
    this.b = this.a.containsEnchantment(GlowEnchantment.getGlowEnchantment());
    this.c = false;
  }

  public CItem setName(String name) {
    ItemMeta meta = this.a.getItemMeta();
    meta.setDisplayName(name);
    this.a.setItemMeta(meta);
    return this;
  }

  public CItem setAmount(int amount) {
    this.a.setAmount(amount);
    return this;
  }

  public int getAmount() {
    return this.a.getAmount();
  }

  public CItem setDurability(int durability) {
    this.a.setDurability((short)durability);
    return this;
  }

  public int getDurability() {
    return this.a.getDurability();
  }

  public CItem setData(int data)
  {
    this.a.setData(new MaterialData(this.a.getType(), (byte)data));
    return this;
  }

  public int getData()
  {
    return this.a.getData().getData();
  }

  public CItem setLore(String[] lore) {
    if ((lore == null) || (lore.length == 0)) {
      clearLore();
    }
    ArrayList lr = new ArrayList();
    for (int i = 0; i < lore.length; i++) {
      if (lore[i].contains("\n")) {
        for (String s : lore[i].split("\n"))
          lr.add(s.replaceAll("&", "�"));
      }
      else {
        lr.add(lore[i].replaceAll("&", "�"));
      }
    }
    ItemMeta meta = a.getItemMeta();
    meta.setLore(lr);
    a.setItemMeta(meta);
    return this;
  }

  public CItem addLore(String[] lore) {
    ItemMeta meta = this.a.getItemMeta();
    List original_lore = meta.getLore();
    if (original_lore == null)
      meta.setLore(new ArrayList());
    for (String s : lore)
      original_lore.add(s);
    meta.setLore(original_lore);
    this.a.setItemMeta(meta);
    return this;
  }

  public List<String> getLore() {
    return this.a.getItemMeta().getLore();
  }

  public CItem clearLore() {
    ItemMeta meta = this.a.getItemMeta();
    meta.setLore(new ArrayList());
    this.a.setItemMeta(meta);
    return this;
  }

  public CItem addEnchantment(Enchantment enchantment) {
    this.a.addUnsafeEnchantment(enchantment, 0);
    return this;
  }

  public CItem addEnchantment(Enchantment enchantment, int level) {
    this.a.addUnsafeEnchantment(enchantment, level);
    return this;
  }

  public CItem addEnchantments(Enchantment[] enchantments, int[] levels) {
    for (int i = 0; i < enchantments.length; i++)
      this.a.addUnsafeEnchantment(enchantments[i], levels[i]);
    return this;
  }

  public CItem removeEnchantments() {
    Enchantment[] enchantments = getEnchantments();
    for (int i = 0; i < enchantments.length; i++)
      this.a.removeEnchantment(enchantments[i]);
    return this;
  }

  public Enchantment[] getEnchantments() {
    return (Enchantment[])this.a.getEnchantments().keySet().toArray(new Enchantment[this.a.getEnchantments().keySet().size()]);
  }

  public Map<Enchantment, Integer> getDetailedEnchantments() {
    return this.a.getEnchantments();
  }

  public CItem setType(Material material) {
    this.a.setType(material);
    return this;
  }

  public Material getType() {
    return this.a.getType();
  }

  public CItem setType(int materialId)
  {
    this.a.setTypeId(materialId);
    return this;
  }

  public CItem setArmorColor(Color color) {
    if ((this.a.getType().equals(Material.LEATHER_HELMET)) || (this.a.getType().equals(Material.LEATHER_CHESTPLATE)) || 
      (this.a.getType().equals(Material.LEATHER_LEGGINGS)) || 
      (this.a.getType().equals(Material.LEATHER_BOOTS))) {
      LeatherArmorMeta meta = (LeatherArmorMeta)this.a.getItemMeta();
      meta.setColor(color);
      this.a.setItemMeta(meta);
    }
    return this;
  }

  public CItem setSkullOwner(String playerName) {
    if (this.a.getType().equals(Material.SKULL_ITEM)) {
      setData(3);
      SkullMeta meta = (SkullMeta)this.a.getItemMeta();
      meta.setOwner(playerName);
      this.a.setItemMeta(meta);
    }
    return this;
  }

  public CItem setGlow(boolean glow) {
    if (glow) {
      this.a.addUnsafeEnchantment(GlowEnchantment.getGlowEnchantment(), 1);
      this.b = true;
    } else {
      if (this.a.containsEnchantment(GlowEnchantment.getGlowEnchantment()))
        this.a.removeEnchantment(GlowEnchantment.getGlowEnchantment());
      this.b = false;
    }
    return this;
  }

  public boolean hasGlow() {
    return this.b;
  }

  public CItem clone() {
    return new CItem(this.a);
  }

  public String toString() {
    return this.a.toString();
  }

  public ItemStack build() {
    return this.a;
  }

  public Item spawn(Location l) {
    return l.getWorld().dropItem(l, this.a);
  }

  public static boolean equals(ItemStack item1, ItemStack item2) {
    return item1.isSimilar(item2);
  }

  public static ItemStack addLore(ItemStack itemStack, String[] toAdd) {
    ItemMeta meta = itemStack.getItemMeta();
    List lore = meta.hasLore() ? meta.getLore() : new ArrayList();
    for (String s : toAdd)
      lore.add(s.replaceAll("&", "�"));
    meta.setLore(lore);
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  public static String getValueFromLore(ItemStack itemStack, String key) {
    return getValueFromLore(itemStack, key, ":", true);
  }

  public static String getValueFromLore(ItemStack itemStack, String key, String separator, boolean trim) {
    if (!itemStack.hasItemMeta())
      return null;
    if (!itemStack.getItemMeta().hasLore())
      return null;
    String s = null;
    List lore = new ArrayList(itemStack.getItemMeta().getLore());
    for (int i = 0; i < lore.size(); i++)
      if (((String)lore.get(i)).contains(key))
        s = (String)lore.get(i);
    if (s == null)
      return null;
    String[] args = s.split(separator);
    return trim ? args[1].trim() : args[1];
  }
}