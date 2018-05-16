package com.criptonnetwork.type;

import java.util.List;

import com.google.common.collect.Lists;

public class PacoteManager {
	
	
	public static List<Pacote> pacotes = Lists.newArrayList();
	
	
	public static Pacote add(Pacote pacote) {
		pacotes.add(pacote);
		
		return pacote;
	}
	
	public static Pacote getPacote(String name) {
		
		for(Pacote p : pacotes) {
			if(p.getName().equalsIgnoreCase(name))
				return p;
		}
		
		return null;
	}
	
	
}
