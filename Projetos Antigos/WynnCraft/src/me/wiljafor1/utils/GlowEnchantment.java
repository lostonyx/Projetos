package me.wiljafor1.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class GlowEnchantment extends EnchantmentWrapper
{
  private static Enchantment glow = null;

  protected GlowEnchantment(int id)
  {
    super(id);
    try {
      Field _a = Enchantment.class.getDeclaredField("byId");
      _a.setAccessible(true);
      Field _b = Enchantment.class.getDeclaredField("byName");
      _b.setAccessible(true);
      HashMap byId = (HashMap)_a.get(null);
      HashMap byName = (HashMap)_b.get(null);
      if (byId.containsKey(Integer.valueOf(id))) byId.remove(Integer.valueOf(id));
      if (byName.containsKey(getName())) byName.remove(getName()); 
    }
    catch (Exception localException)
    {
    }
  }

  public boolean canEnchantItem(ItemStack item) {
    return true;
  }

  public boolean conflictsWith(Enchantment other)
  {
    return false;
  }

  public EnchantmentTarget getItemTarget()
  {
    return null;
  }

  public int getMaxLevel()
  {
    return 10;
  }

  public String getName()
  {
    return "Glow";
  }

  public int getStartLevel()
  {
    return 1;
  }

  public static Enchantment getGlowEnchantment() {
    if (glow != null) return glow; try
    {
      Field f = Enchantment.class.getDeclaredField("acceptingNew");
      f.setAccessible(true);
      f.set(null, Boolean.valueOf(true));
      f.setAccessible(false);
    } catch (Exception localException) {
    }
    glow = new GlowEnchantment(128);
    Enchantment.registerEnchantment(glow);
    return glow;
  }
}