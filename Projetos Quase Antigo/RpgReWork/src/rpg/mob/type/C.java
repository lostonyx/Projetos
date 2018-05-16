package rpg.mob.type;

import rpg.mob.Mobs;

public interface C extends Mobs  {
	public enum Mode
	{
	  ATTACKONHIT, 
	  NORMAL;
	}
	public Mode mode();
}
