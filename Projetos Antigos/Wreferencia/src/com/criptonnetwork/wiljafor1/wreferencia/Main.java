package com.criptonnetwork.wiljafor1.wreferencia;
import com.criptonnetwork.wiljafor1.wreferencia.Comandos.Referencia;
import com.criptonnetwork.wiljafor1.wreferencia.Util.SQL;
import com.criptonnetwork.wiljafor1.wreferencia.Util.Title;
import com.criptonnetwork.wiljafor1.wreferencia.Util.UserData;
import java.io.File;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *  @author WillianDev
 *  @Data 20/12/17
 *  @version 1.5
 */
public class Main extends JavaPlugin{
    public UserData UserData;
    int check = 15 * 1200;
    private String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Override
    public void onEnable() {
    getServer().getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
    getServer().getConsoleSender().sendMessage("§6" + getDescription().getName() + " §8v.§6" + getDescription().getVersion() + " §8de§6 "+ getDescription().getAuthors() + "§2Carregando o Plugin.");
    File chestFolder = new File(getDataFolder(), "userdata");
	if (!chestFolder.exists()) {
            getServer().getConsoleSender().sendMessage("§6<§8 Pasta 'userdata' nao encontrada, criando local...");
            chestFolder.mkdirs();
            getServer().getConsoleSender().sendMessage("§6<§8 Local " + chestFolder + " criado.");
	}
    UserData = new UserData(chestFolder, getLogger(), this);
    File db = new File(getDataFolder(), "dados.db");
    if (!(db.exists())) {
        try {
            saveResource("dados.db", false);
        } catch (Exception ignored) {
        }
    }
    SQL.setDb(db);
    SQL.createTable();
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				int refChests = UserData.pegarChestSalvar();
				getServer().getConsoleSender().sendMessage("§6<§8 Salvo " + refChests + " player(s)");
				boolean novo = false;
			}
    }, check, check);
    getServer().getPluginManager().registerEvents(new Referencia(this, UserData), this);
    getCommand("referencia").setExecutor(new Referencia(this, UserData));
    getServer().getPluginManager().registerEvents(new Title(this), this);
    getServer().getConsoleSender().sendMessage("§6<§8-----------------------------§6>");
    }
    
    @Override
    public void onDisable() {
    
    }  
    
    public String FormatKey() {
        String key = "";
        int t = 0;
        Random n = new Random();
        int tmax = 5;
        if(tmax<1||tmax>10)
            tmax=10;
            while(t<tmax) {
                switch(n.nextInt(2)) {
                    case 0: {key+=letras[n.nextInt(letras.length)];break;}
                    case 1: {key+=String.valueOf(n.nextInt(10));break;}
                }
            t++;
            }
    return key;
    }
    
}
