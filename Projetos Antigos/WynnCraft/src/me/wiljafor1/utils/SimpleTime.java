package me.wiljafor1.utils;

public class SimpleTime {

	
	
	
	public static void main(String[] args) {
		int i = 24;
		System.out.println("Dias :: " + toDays(i));
		System.out.println("Dias :: " + toSeconds(toDays(i)));
		System.out.println("Tempo: \n" + ExtraAPI.formatTime(toDays(i)));
	}
	
	
	public static long toLong(int i) {
		return i * 1000;
	}

	public static int toSeconds(long l) {
		return (int) l / 1000;
	}
	
	
	public static int toDays(int i) {
		long l = toLong(i);
		l = l * 86400000;
				
		return (int) l / 1000;
		
	}

}
