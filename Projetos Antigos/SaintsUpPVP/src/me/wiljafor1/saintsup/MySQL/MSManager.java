package me.wiljafor1.saintsup.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.wiljafor1.saintsup.Main;

public class MSManager {

	private static MySQL db;
	public Main plugin;

	public MSManager(Main plugin2) {
		this.plugin = plugin2;
	}

	public void iniciarDatabase() throws SQLException {
		db = new MySQL(this.plugin, Main.plugin.getConfig().getString("mysql.host-name"),
				Main.plugin.getConfig().getString("mysql.porta"), Main.plugin.getConfig().getString("mysql.database"),
				Main.plugin.getConfig().getString("mysql.usuario"), Main.plugin.getConfig().getString("mysql.senha"));
		db.openConnection();
		Statement statement = db.getConnection().createStatement();
		statement.executeUpdate(
				"CREATE TABLE IF NOT EXISTS `status` (`UUID` varchar(255), `Vitimas` int, `Mortes` int,`Almas` int)");

		this.plugin.database = true;
	}

	public static void criarPefil(Player p) throws SQLException {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s = db.getConnection().createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM status WHERE `UUID`='" + p.getUniqueId() + "';");
		if (rs.next()) {
			return;
		}
		s.executeUpdate("INSERT INTO status (`UUID`, `Vitimas`, `Mortes`, `Almas`) VALUES ('" + p.getUniqueId()
				+ "', '0', '0', '0');");
		p.sendMessage(" §a* Voce recebeu uma Key Comum pelo First-Join!");
	}


	public static void setVitimas(Player p, int quantidade) {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s;
		try {
			s = db.getConnection().createStatement();
			s.executeUpdate("UPDATE status SET `Vitimas`='" + quantidade + "' WHERE `UUID`='" + p.getUniqueId() + "';");
		} catch (SQLException e) {
			Main.plugin.getLogger().severe("Nao foi possivel atualizar o numero de Vitimas do jogador: " + p.getName());
		}
	}

	public static void setMortes(Player p, int quantidade) {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s;
		try {
			s = db.getConnection().createStatement();
			s.executeUpdate("UPDATE status SET `Mortes`='" + quantidade + "' WHERE `UUID`='" + p.getUniqueId() + "';");
		} catch (SQLException e) {
			Main.plugin.getLogger().severe("Nao foi possivel atualizar o numero de Mortes do jogador: " + p.getName());
		}
	}

	public static void setAlmas(Player p, int quantidade) {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s;
		try {
			s = db.getConnection().createStatement();
			s.executeUpdate("UPDATE status SET `Almas`='" + quantidade + "' WHERE `UUID`='" + p.getUniqueId() + "';");
		} catch (SQLException e) {
			Main.plugin.getLogger().severe("Nao foi possivel atualizar o numero de Almas do jogador: " + p.getName());
		}
	}

	public static Integer getVitimas(Player p) {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s;
		try {
			s = db.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM status WHERE `UUID`='" + p.getUniqueId() + "';");
			if (!rs.next()) {
				return Integer.valueOf(0);
			}
			int retorno = rs.getInt("Vitimas");
			return Integer.valueOf(retorno);
		} catch (SQLException e) {
			Main.plugin.getLogger().severe("Nao foi possivel carregar o numero de Vitimas do jogador: " + p.getName());
		}
		return 0;
	}

	public static Integer getMortes(Player p) {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s;
		try {
			s = db.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM status WHERE `UUID`='" + p.getUniqueId() + "';");
			if (!rs.next()) {
				return Integer.valueOf(0);
			}
			int retorno = rs.getInt("Mortes");
			return Integer.valueOf(retorno);
		} catch (SQLException e) {
			Main.plugin.getLogger().severe("Nao foi possivel carregar o numero de Mortes do jogador: " + p.getName());
		}
		return 0;
	}

	public static Integer getAlmas(Player p) {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		Statement s;
		try {
			s = db.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM status WHERE `UUID`='" + p.getUniqueId() + "';");
			if (!rs.next()) {
				return Integer.valueOf(0);
			}
			int retorno = rs.getInt("Almas");
			return Integer.valueOf(retorno);
		} catch (SQLException e) {
			Main.plugin.getLogger().severe("Nao foi possivel carregar o numero de Almas do jogador: " + p.getName());
		}
		return 0;
	}

	public static List<String> getTOPAlmas() {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		List<String> lista = new ArrayList();
		try {
			Statement s = db.getConnection().createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM status ORDER BY `Almas`");
			int i = 1;
			while (r.next()) {
				if (i <= 5) {
					i++;
					lista.add("§4" + i + "§ §7" + r.getString("UUID") + " §f| §7" + r.getInt("Almas") + " Alma(s)");
				} else {
					return lista;
				}
			}
		} catch (SQLException e) {
		}
		return lista;
	}

	public static List<String> getTOPKills() {
		if (!db.checkConnection()) {
			db.openConnection();
		}
		List<String> lista = new ArrayList();
		try {
			Statement s = db.getConnection().createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM status ORDER BY `Vitimas`");
			int i = 1;
			while (r.next()) {
				if (i <= 5) {
					i++;
					lista.add(
							"§4" + i + "§ §7" + r.getString("UUID") + " §f| §7" + r.getInt("Vitimas") + " Vitimas(s)");
				} else {
					return lista;
				}
			}
		} catch (SQLException e) {
		}
		return lista;
	}

	public void closeDB() {
		db.closeConnection();
	}
}