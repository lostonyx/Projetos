
package com.criptonnetwork.type;

public class ServerInstance {

	private String name;
	private boolean autorized;
	private int players;
	private String ip;

	public ServerInstance(String name) {
		this.name = name;
		this.autorized = false;
		this.players = 0;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAutorized() {
		return autorized;
	}

	public void setAutorized(boolean autorized) {
		this.autorized = autorized;
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
