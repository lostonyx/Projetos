package me.wiljafor1.saintsup.MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import me.wiljafor1.saintsup.Main;

public class Query extends Thread {
	private String sql;
	private Logger log;
	private Connection con;

	public Query(String sql, Logger log, Connection con, Plugin plugin) {
		this.setDaemon(false);
		this.sql = sql;
		this.setLog(log);
		this.con = con;
	}

	@Override
	public void run() {
		Main.lock.lock();
		try {
			Statement stmt = this.con.createStatement();
			stmt.executeUpdate(this.sql);
			stmt.close();
		} catch (SQLException ex) {
			Main.plugin.getLogger().warning("Error with following query: " + this.sql);
			Main.plugin.getLogger().warning(ex.getMessage());
			Main.SQLdisconnect();
		} catch (NullPointerException ex) {
			Main.plugin.getLogger().warning("Error while performing a query. (NullPointerException)");
		}
		Main.lock.unlock();
	}

	public Logger getLog() {
		return this.log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
}
