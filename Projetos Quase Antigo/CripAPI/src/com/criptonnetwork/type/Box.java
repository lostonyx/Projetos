package com.criptonnetwork.type;

import java.util.List;

import org.bukkit.Location;

public interface Box {
	
	
	public List<Pacote> getPacotes();
	
	public void open(Gamer g);
	
	public String getName();
	
	public Location getBoxLocation();

}
