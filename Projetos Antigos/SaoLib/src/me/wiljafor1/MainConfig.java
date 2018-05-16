package me.wiljafor1;

import me.wiljafor1.Main;
import java.util.List;
import static me.wiljafor1.Main.plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfig
{
  public static FileConfiguration config;
  
  public static void loadConfig()
  {
    plugin.reloadConfig();
    config = plugin.getConfig();
    plugin.Info = getInfo();
    plugin.Plugins = getPlugins();
    plugin.Dev = getDev();
    plugin.Ajuda = getAjuda();
    plugin.Comandos = getComandos();
    plugin.Formulario = getForm();
    plugin.Vip = getVip();
    plugin.Site = getSite();
    plugin.Social = getSocial();
    plugin.Regras = getRegras();
    plugin.Playlist = getPlaylist();
  }
  
  public static List<String> getSocial()
  {
    return config.getStringList("social");
  }  
  
  public static List<String> getSite()
  {
    return config.getStringList("site");
  }
  
  public static List<String> getInfo()
  {
    return config.getStringList("info");
  }
  
  public static List<String> getForm()
  {
    return config.getStringList("formulario");
  }
  
  public static List<String> getVip()
  {
    return config.getStringList("vip");
  }
  
  public static List<String> getPlugins()
  {
    return config.getStringList("plugin");
  }
  
  public static List<String> getAjuda()
  {
    return config.getStringList("ajuda");
  }
  
  public static List<String> getComandos()
  {
    return config.getStringList("comandos");
  }
  
  public static List<String> getDev()
  {
    return config.getStringList("dev");
  }
  
  public static String getTag()
  {
    return config.getString("tag");
  }
  
  public static List<String> getRegras()
  {
    return config.getStringList("regras");
  }
  
  public static List<String> getPlaylist()
  {
    return config.getStringList("playlist");
  }
  
  public static void set(String s, Object o)
  {
    config.set(s, o);
    Main.plugin.saveConfig();
    loadConfig();
  }
}