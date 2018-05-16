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
import rpg.utils.Cuboid;

public class Detlas implements City {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 386.349, 0, -1503.465), new Location(getSpawn().getWorld(), 549.459, 255, -1661.554));
	}

	@Override
	public String getName() {
		return "Detlas";
	}

	@Override
	public Config getConfig() {
		return new Config("detlas.yml");
	}

	@Override
	public boolean isCity() {
		return true;
	}

	@Override
	public boolean isDungeon() {
		return false;
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
	public int BGMInSeconds() {
		return 140;
	}

	@Override
	public String getNBS() {
		return "detlas.nbs";
	}

	@Override
	public Location getSpawn() {
		return new Location(Bukkit.getWorld("world"), 470, 68, -1581.954);
	}

	@Override
	public List<Cuboid> getDoors() {
		return null;
	}

	@Override
	public int id() {
		return 2;
	}


	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 386.349, 0, -1503.465).add(50, 0, 50), new Location(getSpawn().getWorld(), 549.459, 255, -1661.554).add(50, 0, 50));
	}

	@Override
	public Runnable init() {
		return new Runnable() {
			public void run() {
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 398.54913726352095, 67.0, -1579.5602230745658));
			    MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 398.271960565376, 67.0, -1584.433071411739));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 415.4560739145719, 67.0, -1584.6903724276035));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 415.4302602335316, 67.0, -1579.5654589314875));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 468.40631721939343, 67.0, -1633.4068665613113));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 471.53741278740944, 67.0, -1633.4271571673146));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 467.5024494552038, 67.0, -1653.4123452193046));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 472.3317788328416, 67.0, -1653.274083895348));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 468.55695470943004, 67.0, -1529.4578984462867));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 471.52940832414885, 67.0, -1529.4943584756525));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 471.68556810280154, 67.0, -1510.5108786815495));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 468.4461597767877, 67.0, -1510.483055101109));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 523.4254545553076, 67.0, -1584.5681193634257));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 523.375593858739, 67.0, -1579.40926821966));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 541.436210639391, 67.0, -1584.6215478154916));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), 541.2094644898937, 67.0, -1579.3168955067965));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 490.7078082643715, 69.0, -1571.509458555098));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 457.76821016406905, 67.0, -1557.8282204434465));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 446.9594737010054, 67.0, -1548.8973465298448));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 437.60698891978916, 67.0, -1593.6578932844843));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 422.6362871161519, 67.0, -1608.483839386136));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 442.9136451509547, 67.0, -1601.539868235194));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 470.00154556740057, 67.0, -1612.2808736965005));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 480.5483121671262, 67.0, -1584.612274025022));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 480.7081689957476, 67.0625, -1562.9214059718379));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 498.36708507359685, 67.0625, -1563.040517700884));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 450.74205692370447, 67.0, -1559.9810646683918));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 433.10653931924384, 67.0, -1578.3248713589883));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 435.3555911817777, 67.0, -1608.2321756305812));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 458.1693823958441, 67.0, -1610.6849393292705));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), 461.4215218719944, 67.0, -1592.863626383654));
			}
		};
	}

	@Override
	public ArrayList<Mobs> mobsproximos() {
		ArrayList<Mobs> listamobs = new ArrayList<>();
		listamobs.add(MobManager.getMob("ZombieStarter"));
		return listamobs;
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] {
				"§6§lTeste de menssagem :D"
		};
	}
}
