package com.dayz.domination;

import org.bukkit.Location;

import com.criptonnetwork.util.Cuboid;

public class Domination {
	
	
	
	private Location locOne;
	private Location locTwo;
	private Cuboid c;
	private String Nome,PlayerName;
	private int time;
	
	
	public Domination(String name , Location locOne, Location locTwo, int time) {
		this.Nome = name;
		this.locOne = locOne;
		this.locTwo = locTwo;
		this.setC(new Cuboid(locOne , locTwo));
		this.time = time;
	}
	
	public Location getLocOne() {
		return locOne;
	}
	public void setLocOne(Location locOne) {
		this.locOne = locOne;
	}
	public Location getLocTwo() {
		return locTwo;
	}
	public void setLocTwo(Location locTwo) {
		this.locTwo = locTwo;
	}

	public Cuboid getC() {
		return c;
	}

	public void setC(Cuboid c) {
		this.c = c;
	}


	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}
	
	public Location getCenter() {
		int x1 = (int) (c.getUpperX() + 1);
		int y1 = (int) (c.getUpperY() + 1);
		int z1 = (int) (c.getUpperZ() + 1);
		return new Location(locOne.getWorld(), c.getLowerX() + (x1 - c.getLowerX()) / 2.0,
				c.getLowerY() + (y1 - c.getLowerY()) / 2.0,
				c.getLowerZ() + (z1 - c.getLowerZ()) / 2.0);
	}
	
	public int getSize(){
		int xDim = (int) Math.ceil(Math.abs(getC().getUpperX() - getC().getLowerX()));
		int zDim = (int) Math.ceil(Math.abs(getC().getUpperZ() - getC().getLowerZ()));
		if(xDim == zDim){
			return xDim;	
		}
		else{
			return xDim;	
		}
	}

}
