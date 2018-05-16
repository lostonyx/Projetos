package net.saintsupenchant;

import java.io.File;
import net.saintsupenchant.listener.Enchant;
import static net.saintsupenchant.listener.Enchant.listHM;
import net.saintsupenchant.util.Config;
import net.saintsupenchant.util.Messages;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import net.lightshard.prisonmines.MineAPI.*;
import net.lightshard.prisonmines.*;
import net.saintsupenchant.Comandos.Token;
import static net.saintsupenchant.util.Key.PegarIp;
import static net.saintsupenchant.util.Key.pegarKey;
import net.saintsupenchant.util.License;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin{
    public Config config;
    public Main(){
        config = new Config(this, "config.yml");
    }

	@Override
	public void onEnable() {
        getCommand("token").setExecutor(new Token(this) {});    
        //if(!new License("WE6I-3KSW-MAGR-X23W", "http://saomc.com.br/api/verify.php", this).register()) return;
        File c= new File(getDataFolder(), "config.yml");
        if(!c.exists()){
        config.saveDefaultConfig();
        saveDefaultConfig();
        }
        getServer().getPluginManager().registerEvents(new Enchant(this), this);
        listHM.add(Material.DIAMOND_ORE);
        listHM.add(Material.AIR);
        listHM.add(Material.IRON_ORE);
        listHM.add(Material.GOLD_ORE);
        listHM.add(Material.LAPIS_ORE);
        listHM.add(Material.REDSTONE_ORE);
        listHM.add(Material.REDSTONE_BLOCK);
        listHM.add(Material.QUARTZ_ORE);
        listHM.add(Material.LAPIS_BLOCK);
        listHM.add(Material.EMERALD_ORE);
            try {
                Messages.enable("pt_BR");
                System.out.println(Messages.getString("su.loaded"));
            } catch (Exception e) {
                Messages.enable("pt_BR");
                System.out.println(Messages.getString("su.loaded"));
            }
            Okay();
        }
    
	@Override
	public void onDisable() {
        
        }
        
        public void Okay(){
        String ip = PegarIp();
        String Res = pegarKey(ip);
        //Bukkit.getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
        //Bukkit.getConsoleSender().sendMessage("§6ip: "+ip);
        //Bukkit.getConsoleSender().sendMessage("§6Res: "+Res);
       // Bukkit.getConsoleSender().sendMessage("§6Key in Config: "+getConfig().getString("key"));
        //Bukkit.getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
        //String key = Key();
            if(Res.contains(getConfig().getString("key"))){
            Bukkit.getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
            Bukkit.getConsoleSender().sendMessage("§6" + getDescription().getName() + " §8v.§6" + getDescription().getVersion() + " §8de§6 "
            + getDescription().getAuthors() + " §2Key Autenticada!");
            Bukkit.getConsoleSender().sendMessage("§6<§8-----------------------------§6>");    
            }
            else{
            Bukkit.getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
            Bukkit.getConsoleSender().sendMessage("§6" + getDescription().getName() + " §8v.§6" + getDescription().getVersion() + " §8de§6 "
            + getDescription().getAuthors() + " §2Key Invalida! ");
            Bukkit.getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
            getServer().getPluginManager().disablePlugin(this);    
            }
    }
        
}
