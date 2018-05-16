package com.dayz.type;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.dayz.utils.SerializeApi;

public class Account {

	private int id , level, xp, kills, deaths, totalzombies, karma;
	
	
	private String inv, classe,ultimolocal;
	private Player player;
	private Double vida , currentRadiation, money;

	public Account(int id, String classe, String inv, int level, int xp, int kills, int deaths, int totalzombies, Double vida, String loc , double currentRadiation, int karma, double money) {
		this.id = id;
		this.classe = classe;
		this.vida = vida;
		this.inv = inv;
		this.level = level;
		this.xp = xp;
		this.kills = kills;
		this.deaths = deaths;
		this.totalzombies = totalzombies;
		this.ultimolocal = loc;
		this.currentRadiation = currentRadiation;
		this.karma = karma;
		this.money = money;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getTotalzombies() {
		return totalzombies;
	}

	public void setTotalzombies(int totalzombies) {
		this.totalzombies = totalzombies;
	}

	public Inventory getInv() {
		try {
			return SerializeApi.DeserializarInventory(inv);
		} catch (IOException e) {
		}
		return null;
	}
	
	public String getInvString() {
		return inv;
	}

	public void setInv(Inventory inv) {
		try {
			this.inv = SerializeApi.Serializar(inv);
		} catch (Exception e) {
		}
	}

	public Double getVida() {
		return vida;
	}

	public void setVida(Double vida) {
		this.vida = vida;
	}

	public String getUltimolocal() {
		return ultimolocal;
	}

	public void setUltimolocal(String ultimolocal) {
		this.ultimolocal = ultimolocal;
	}

	public Double getCurrentRadiation() {
		return currentRadiation;
	}

	public void setCurrentRadiation(Double currentRadiation) {
		this.currentRadiation = currentRadiation;
	}

	public void setInv(String inv) {
		this.inv = inv;
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	

}
