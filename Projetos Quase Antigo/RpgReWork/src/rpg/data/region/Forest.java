package rpg.data.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.EntityLiving;
import rpg.Config;
import rpg.mob.MobManager;
import rpg.mob.Mobs;
import rpg.system.models.City;
import rpg.system.models.Dungeon;
import rpg.utils.Cuboid;

public class Forest implements Dungeon {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(Bukkit.getWorld("world"), -453, 0, -1788), new Location(Bukkit.getWorld("world"), -25, 255, -1300));
	}

	@Override
	public String getName() {
		return "Forest";
	}

	@Override
	public Config getConfig() {
		return new Config("forest.yml");
	}

	@Override
	public boolean isCity() {
		return false;
	}

	@Override
	public boolean isDungeon() {
		return true;
	}

	@Override
	public boolean isQuest() {
		return false;
	}

	@Override
	public boolean haveBGM() {
		return true;
	}

	@Override
	public String getNBS() {
		return "forest.nbs";
	}

	@Override
	public int BGMInSeconds() {
		return 140;
	}



	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(Bukkit.getWorld("world"), 875.668, 0, -1531.358).add(50, 0, 50), new Location(Bukkit.getWorld("world"), 771.487, 255, -1665.395).add(-50, 0, -50));
	}

	@Override
	public Runnable init() {
		return new Runnable() {
			public void run() {
				Bukkit.broadcastMessage("Foi!");
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -124.80215151902522, 68.0, -1637.8752961181456));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -129.78298080398486, 69.0, -1622.8615392042461));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -151.56496486668624, 71.0, -1610.5328609599503));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -171.4167154999338, 70.0, -1628.5540875780976));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -190.6174256285035, 70.0, -1623.6183897596554));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -209.11115783141673, 69.0, -1632.248612075387));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -210.64829414036728, 67.0, -1599.7150353810416));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -235.01325680090105, 69.0, -1603.560419472147));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -245.90874031931745, 67.0, -1579.2214551525599));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -275.27434739727266, 71.5, -1575.2342381655392));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -291.34858389472674, 72.0, -1597.699999988079));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -306.6982698220237, 73.0, -1584.5497566204815));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -327.1604940145403, 70.0, -1588.3949041819105));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -348.6011343687575, 68.0, -1578.1012164920985));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -371.95218555458524, 69.0, -1597.5709488956986));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -392.5942780669695, 67.0, -1585.8106925294226));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -410.8461871790237, 68.0, -1601.562769704004));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -421.9606216810629, 68.0, -1593.9391748750131));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -429.5992810490345, 69.0, -1611.2842927788304));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -414.7237524588277, 71.0, -1606.863483597714));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -385.90650203227614, 67.5, -1589.4716682915366));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -363.60098376026224, 69.0, -1588.3572165250812));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -329.39299873609383, 70.0, -1585.0899076420933));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -318.5811810625247, 62.0, -1605.088520603068));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -323.4517554793823, 50.0, -1623.385071577916));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -313.0806278230775, 42.5, -1634.6901154473385));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -302.81525706224653, 36.0, -1619.4116258689369));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -326.7100095378653, 27.0, -1602.1028680921243));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -351.8456190155792, 25.0, -1602.5892412705236));
				MobManager.spawnEntity("Esqueleto3", new Location(Bukkit.getWorld("world"), -366.6999999880791, 27.0, -1607.699999988079));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -253.4076838515615, 68.0, -1587.778358715504));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -215.06653270452065, 68.0, -1608.6209973547245));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -189.05082949952964, 69.0, -1632.2810425897653));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -152.82259314058624, 70.0, -1622.0059388283435));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -127.27881534730707, 68.0, -1632.581703053552));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -99.76765650053053, 68.0, -1625.8076066703215));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -69.96707715461218, 65.0, -1621.3521204508643));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -57.82381730292069, 65.0, -1587.3197998064804));
				//MobManager.spawnEntity("Cogumelo", new Location(Bukkit.getWorld("world"), -33.13079578977857, 64.0, -1579.8707602141963));
				
			}
		};
	}

	@Override
	public ArrayList<Mobs> mobsproximos() {
		ArrayList<Mobs> listamobs = new ArrayList<>();
		listamobs.add(MobManager.getMob("ZombieStarter"));
		listamobs.add(MobManager.getMob("Esqueleto3"));
		return listamobs;
	}


	@Override
	public int getLevelMin() {
		return 1;
	}
}
