package com.dayz.safezone;

import org.bukkit.Location;

import com.criptonnetwork.util.Cuboid;

public class Safezone {
	
	
	
	private Location locOne;
	private Location locTwo;
	private Cuboid c;
	private Location spawn;
	private String Nome;
	//private Location Npc;
	
	
	public Safezone(String name , Location locOne, Location locTwo) {
		this.Nome = name;
		this.locOne = locOne;
		this.locTwo = locTwo;
		this.setC(new Cuboid(locOne , locTwo));
		
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

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
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
