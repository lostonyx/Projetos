package me.wiljafor1.system.mobs;


public interface C extends Mobs  {
	public enum Mode
	{
	  ATTACKONHIT, 
	  NORMAL;
	}
	public Mode mode();
}
