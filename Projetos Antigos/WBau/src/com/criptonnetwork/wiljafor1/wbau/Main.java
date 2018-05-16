package com.criptonnetwork.wiljafor1.wbau;

import com.criptonnetwork.wiljafor1.wbau.sistema.Bau;
import com.criptonnetwork.wiljafor1.wbau.util.BauManager;
import com.criptonnetwork.wiljafor1.wbau.util.Title;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    private BauManager BauManager;
    @Override
    public void onEnable() {
        carregar();
    }
    
    @Override
    public void onDisable() {
    
    }  
    
    public void carregar(){
    File chestFolder = new File(getDataFolder(), "baus");
    if (!chestFolder.exists()) {
	getServer().getConsoleSender().sendMessage("§c[WBau] Pasta 'Baus' nao encontrada, criando local...");
	chestFolder.mkdirs();
	getServer().getConsoleSender().sendMessage("§c[WBau] Local " + chestFolder + " criado.");
    }
    BauManager = new BauManager(chestFolder, getLogger());
    getServer().getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
    getServer().getConsoleSender().sendMessage("§6" + getDescription().getName() + " §8v.§6" + getDescription().getVersion() + " §8de§6 "+ getDescription().getAuthors() + "§2Carregando o Plugin.");
    getServer().getPluginManager().registerEvents(new Title(this), this);
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				int refChests = BauManager.pegarChestSalvar();
				getServer().getConsoleSender().sendMessage("§6<§8 Salvo " + refChests + " player(s)");
				boolean novo = false;
			}
    }, 60 * 20, 60 * 20);
    getServer().getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
    getServer().getPluginManager().registerEvents(new Bau(this, BauManager), this);
    getCommand("bau").setExecutor(new Bau(this, BauManager));
    }
    
}
