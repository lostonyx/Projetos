package com.criptonnetwork.type;

import java.util.List;

public class Group {
	
	private String name , prefix , suffix;
	private int rank;
	private List<String> permissions;
	
	
	public Group(String name , String prefix , String suffix , int rank , List<String> permissions) {
		setName(name);
		setPrefix(prefix);
		setSuffix(suffix);
		setRank(rank);
		setPermissions(permissions);
	}
	
	native public String getName(String name);//seria assim o correto? sem o return
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
	
 
	
	
	

}
