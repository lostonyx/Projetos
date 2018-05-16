package com.criptonnetwork.cosmeticos;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class CosmeticoManager {
	
	
	public static List<Cosmetico> cos = Lists.newArrayList();
	
	public static void add(Cosmetico c) {
		cos.add(c);
	}
	
	public static List<Cosmetico> getCosmeticoList(CosmeticType type){
		
		return cos.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
	}
	
	public static Cosmetico getCosmetico(String name) {
		for(Cosmetico c : cos) {
			if(c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

}
