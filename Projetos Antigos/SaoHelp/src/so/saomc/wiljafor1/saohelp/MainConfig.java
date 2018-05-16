/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.saomc.wiljafor1.saohelp;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import static so.saomc.wiljafor1.saohelp.Main.plugin;

public class MainConfig
{
  public static FileConfiguration config;
  
  public static void loadConfig()
  {
    plugin.reloadConfig();
    config = plugin.getConfig();
    plugin.gb1 = getgb1();
    plugin.gb2 = getgb2();
    plugin.gb3 = getgb3();
    plugin.gb4 = getgb4();
    plugin.gb5 = getgb5();
    plugin.gb6 = getgb6();
    plugin.gb7 = getgb7();
    plugin.gb8 = getgb8();
    plugin.gb9 = getgb9();
    plugin.gb10 = getgb10();
  }
  
  
  public static List<String> getgb1()
  {
    return config.getStringList("gb1");
  }
  
  public static List<String> getgb2()
  {
    return config.getStringList("gb2");
  }
  
  public static List<String> getgb3()
  {
    return config.getStringList("gb3");
  }
  
  public static List<String> getgb4()
  {
    return config.getStringList("gb4");
  }
  
  public static List<String> getgb5()
  {
    return config.getStringList("gb5");
  }
  
  public static List<String> getgb6()
  {
    return config.getStringList("gb6");
  }
  
  public static List<String> getgb7()
  {
    return config.getStringList("gb7");
  }
  
  public static List<String> getgb8()
  {
    return config.getStringList("gb8");
  }
  
  public static List<String> getgb9()
  {
    return config.getStringList("gb9");
  }
  
  public static List<String> getgb10()
  {
    return config.getStringList("gb10");
  }
  
  
  public static void set(String s, Object o)
  {
    config.set(s, o);
    Main.plugin.saveConfig();
    loadConfig();
  }
}