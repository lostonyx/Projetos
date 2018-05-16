package rpg.system.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import rpg.system.Metodos;
import rpg.system.RpgSetup;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;

public class conta extends Command{

	public conta() {
		super("conta");
	}
	
	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) return false;
        Player player = (Player) s;
        Jogador p = JogadorManager.getWynnPlayer(player);
        if(p.getCurrentAccount() != null) {
        	Metodos.deslogar(player);
        	player.teleport(RpgSetup.LOCALAOLOGAR);
        	player.sendMessage(ChatColor.RED+"Escolha outra conta para logar!");
        	//p.setCurrentAccount(null);
        }
        return false;
	}
}
