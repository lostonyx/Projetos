package me.wiljafor1;

import java.util.ArrayList;
import java.util.List;
import static me.wiljafor1.Comandos.Cmds;
import static me.wiljafor1.MainConfig.loadConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
        public static Main plugin;
        public List<String> Info = new ArrayList();
        public List<String> Comandos = new ArrayList();
        public List<String> Plugins = new ArrayList();
        public List<String> Dev = new ArrayList();
        public List<String> Ajuda = new ArrayList();
        public List<String> Staff = new ArrayList();
        public List<String> Formulario = new ArrayList();
        public List<String> Vip = new ArrayList();
        public List<String> Site = new ArrayList();
        public List<String> Social = new ArrayList();
        public List<String> Regras = new ArrayList();
        public List<String> Playlist = new ArrayList();
        @Override
        public void onEnable() {  
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                Bukkit.getConsoleSender().sendMessage("§4[API]PlaceholderAPI carregado!");    
            }
            else{
                Bukkit.shutdown();;
            }
            plugin = this;
            saveDefaultConfig();
            loadConfig();
            Info = MainConfig.getInfo();
            Plugins = MainConfig.getPlugins();
            Dev = MainConfig.getPlugins();
            Ajuda = MainConfig.getAjuda();
            Comandos = MainConfig.getComandos();
            Formulario = MainConfig.getForm();
            Vip = MainConfig.getVip();
            Site = MainConfig.getSite();
            Social = MainConfig.getSocial();
            Regras = MainConfig.getRegras();
            Playlist = MainConfig.getPlaylist();
            Cmds.add("/info");
            Cmds.add("/informacoes");
            Cmds.add("/sao");
            Cmds.add("/plugins");
            Cmds.add("/pl");
            Cmds.add("/dev");
            Cmds.add("/bukkit");
            Cmds.add("/version");
            Cmds.add("/versao");
            Cmds.add("/ajuda");
            //Cmds.add("/help");
            Cmds.add("/ajd");
            Cmds.add("/comandos");
            Cmds.add("/cmds");
            Cmds.add("/formulario");
            Cmds.add("/serstaff");
            Cmds.add("/form");
            //Cmds.add("/vip");
            Cmds.add("/vips");
            Cmds.add("/doacao");
            Cmds.add("/comprar");
            Cmds.add("/staff");
            Cmds.add("/staffers");
            Cmds.add("/admins");
            Cmds.add("/operadores");
            Cmds.add("/site");
            Cmds.add("/social");
            Cmds.add("/regras");
            Cmds.add("/playlist");
            PluginManager pm = getServer().getPluginManager();
            Bukkit.getServer().getPluginManager().registerEvents(new Comandos(this), this); 
            getCommand("saoinfo").setExecutor(new Comandos(this));  
        }
        
        @Override
        public void onDisable() {
        }
        
        public void reload()
        {
            try
            {
                Info.clear();
                Comandos.clear();
                Plugins.clear();
                Dev.clear();
                Ajuda.clear();
                Staff.clear();
                Formulario.clear();
                Vip.clear();     
                Site.clear();
                Social.clear();
                Regras.clear();
                Playlist.clear();
                loadConfig();
                Bukkit.getConsoleSender().sendMessage("§4[OK]Sistema carregado");
            }
            catch (Exception e)
            {
                Bukkit.getConsoleSender().sendMessage("§4[ERROR]Sistema dando error");
            }
        }
        
        public void Salvar(String plugin, String linha, String desc){
                getConfig().set(""+plugin+"."+linha, "");
        }
        
}
