package me.wiljafor1.saochecar;

import me.wiljafor1.saochecar.Main;
import java.util.List;
import static me.wiljafor1.saochecar.Main.plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfig
{
  public static FileConfiguration config;
  
  public static void loadConfig()
  {
    plugin.reloadConfig();
    config = plugin.getConfig();
    plugin.Tela = getTela();
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