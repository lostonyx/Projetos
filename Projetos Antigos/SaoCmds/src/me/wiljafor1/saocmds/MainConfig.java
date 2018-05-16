package me.wiljafor1.saocmds;

import me.wiljafor1.saocmds.Main;
import java.util.List;
import static me.wiljafor1.saocmds.Main.plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfig
{
  public static FileConfiguration config;
  
  public static void loadConfig()
  {
    plugin.reloadConfig();
    config = plugin.getConfig();
    //plugin.Tela = getTela(); por equanto assim
  }
  
  public static List<String> getTela()
  {
    return config.getStringList("tela");
  }
  
  
  public static String getTag()
  {
    return config.getString("tag");
  }
  
  
  public static void set(String s, Object o)
  {
    config.set(s, o);
    Main.plugin.saveConfig();
    loadConfig();
  }
}