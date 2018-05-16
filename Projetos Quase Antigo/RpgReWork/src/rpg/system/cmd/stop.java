package rpg.system.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import rpg.Main;
import rpg.system.Metodos;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class stop extends Command{
	public stop() {
		super("stop");
	}

	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) {
			Bukkit.broadcastMessage("Servidor Desligando!");
			Bukkit.broadcastMessage("Deslogando todos os jogadores!");
			SimpleRunnable r = SimpleRunnable.getInstance();
			Main.GetInstance().getServer().setWhitelist(true);
			Bukkit.getOnlinePlayers().forEach(v -> {
				Jogador j = JogadorManager.getWynnPlayer(v);
				if(j.getCurrentAccount() != null) {
					Metodos.deslogar(v);
					r.createTaskLater(TaskType.SYNC, "stop", new Runnable() {
						@Override
						public void run() {
							v.kickPlayer("Desligando o server!");	
						}
					}, 20 * 1);
				}
				else {
					v.kickPlayer("Desligando o server!");
				}
			});
			r.createTaskLater(TaskType.SYNC, "stop", new Runnable() {
				@Override
				public void run() {
					Bukkit.shutdown();
				}
			}, 20 * 6);
		}
		else {
			Bukkit.broadcastMessage("Servidor Desligando!");
			Bukkit.broadcastMessage("Deslogando todos os jogadores!");
			SimpleRunnable r = SimpleRunnable.getInstance();
			Main.GetInstance().getServer().setWhitelist(true);
			Bukkit.getOnlinePlayers().forEach(v -> {
				Jogador j = JogadorManager.getWynnPlayer(v);
				if(j.getCurrentAccount() != null) {
					Metodos.deslogar(v);
					r.createTaskLater(TaskType.SYNC, "stop", new Runnable() {
						@Override
						public void run() {
							v.kickPlayer("Desligando o server!");	
						}
					}, 20 * 1);
				}
				else {
					v.kickPlayer("Desligando o server!");
				}
			});
			r.createTaskLater(TaskType.SYNC, "stop", new Runnable() {
				@Override
				public void run() {
					Bukkit.shutdown();
				}
			}, 20 * 6);
		}
		return false;
	}
}
