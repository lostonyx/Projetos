package me.wiljafor1.saintsup.MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import me.wiljafor1.saintsup.Main;

public class EndDB extends Thread {
	private Logger log;
	private Connection con;

	public EndDB(Plugin plugin, Logger log, Connection con) {
		this.setDaemon(false);
		this.log = log;
		this.con = con;
	}

	@Override
	public void run() {
		Main.lock.lock();
		try {
			Main.plugin.getLogger().warning("Desconetando do MYSQL");
			this.con.close();
		} catch (SQLException ex) {
			Main.plugin.getLogger().warning("Erro ao fechar sua conexao");
			Main.plugin.getLogger().warning(ex.getMessage());
		} catch (NullPointerException ex) {
			Main.plugin.getLogger().warning("Erro ao fechar sua conexao");
		}
		if (!Main.SQL_DSC.booleanValue()) {
			this.log.info("Reconectando ao MYSQL...");
			Main.SQLconnect();
		}
		Main.lock.unlock();
	}
}
