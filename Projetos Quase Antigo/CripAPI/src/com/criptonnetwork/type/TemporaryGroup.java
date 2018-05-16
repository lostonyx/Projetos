package com.criptonnetwork.type;

public class TemporaryGroup {
	
	
	private long timeRemaining; // tempo restante
	private String name;
	
	
	public TemporaryGroup(String name , long timeRemaining) {
		setName(name);
		setTimeRemaining(timeRemaining);
	}


	public long getTimeRemaining() {
		return timeRemaining;
	}


	public void setTimeRemaining(long timeRemaining) {
		this.timeRemaining = timeRemaining;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
