package me.wiljafor1.models;

import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.wiljafor1.interfaces.Region;
import me.wiljafor1.utils.SerializeApi;


public class Account {

	private int id , level, xp, health, maxhealth, mana, soul,lastcity;
	private List<Integer> regionfound;
	
	private String inv, classp, ultimolocal;


	public Account(int id, int level, int xp, int life, int mana, int soul, String loc, String classe, String inv, int lc, List<Integer> regionfound) {
		this.id = id;
		this.level = level;
		this.xp = xp;
		this.health = life;
		this.mana = mana;
		this.soul = soul;
		this.inv = inv;
		this.classp = classe;
		this.ultimolocal = loc;
		this.lastcity = lc;
		this.regionfound = regionfound;
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

	public String getLastLoc() {
		return ultimolocal;
	}

	public void setLastLoc(String ultimolocal) {
		this.ultimolocal = ultimolocal;
	}

	public void setLastLocSeri(Location ultimolocal) {
		this.ultimolocal = SerializeApi.SerializarLoc(ultimolocal);
	}

	public void setInv(String inv) {
		this.inv = inv;
	}


	public int getMana() {
		return mana;
	}


	public void setMana(int mana) {
		this.mana = mana;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSoul() {
		return soul;
	}


	public void setSoul(int soul) {
		this.soul = soul;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
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


	public String getClassp() {
		return classp;
	}


	public void setClassp(String classp) {
		this.classp = classp;
	}


	public int getLastcity() {
		return lastcity;
	}


	public void setLastcity(int lastcity) {
		this.lastcity = lastcity;
	}


	public List<Integer> getRegionfound() {
		return regionfound;
	}


	public void setRegionfound(List<Integer> regionfound) {
		this.regionfound = regionfound;
	}


	public int getMaxhealth() {
		return maxhealth;
	}


	public void setMaxhealth(int maxhealth) {
		this.maxhealth = maxhealth;
	}

}
