package rpg.data.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.google.common.collect.Maps;

import net.minecraft.server.v1_8_R3.EntityLiving;
import rpg.Config;
import rpg.mob.MobManager;
import rpg.mob.Mobs;
import rpg.system.models.City;
import rpg.utils.Cuboid;

public class Ragni implements City {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), -740.250, 255, -1754.318), new Location(getSpawn().getWorld(), -943.700, 0, -1417.700));
	}

	@Override
	public String getName() {
		return "Ragni";
	}

	@Override
	public Config getConfig() {
		return new Config("ragni.yml");
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
	public String getNBS() {
		return "ragni.nbs";
	}

	@Override
	public Location getSpawn() {
		return new Location(Bukkit.getWorld("world"), -882.104, 68, -1575.961);
	}

	@Override
	public List<Cuboid> getDoors() {
		return null;
	}

	@Override
	public int BGMInSeconds() {
		return 70;
	}

	@Override
	public int id() {
		return 1;
	}

	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), -633, 0, -1841), new Location(getSpawn().getWorld(), -981, 255, -1348));
	}

	@Override
	public Runnable init() {
		return new Runnable() {
			public void run() {
				/*PreCuboId pc = PreCuboId.Getinstance();
				pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -758.395, 67.0, -1573.537));
				pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -758.500, 67.0, -1578.300));
				
				pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -879.500, 67, -1420.500));
				pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67, -1420.500));
				
				pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -879.500, 67, -1750.500));
				pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67, -1750.500));
				//pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -878.500, 67.0, -1451.500));
				//pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67.0, -1451.500));
				
				//pc.registermob("Citizen", new Location(Bukkit.getWorld("world"), -816.500, 67.0, -1575.500));
				pc.registermob("CitizenN", new Location(Bukkit.getWorld("world"), -820.500, 67.0, -1575.500));*/
				
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -758.395, 67.0, -1573.537));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -758.500, 67.0, -1578.300));
				
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -879.500, 67, -1420.500));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67, -1420.500));
				
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -879.500, 67, -1750.500));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67, -1750.500));
				
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -878.500, 67.0, -1451.500));
				MobManager.spawnEntity("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67.0, -1451.500));
				
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -924, 67, -1568));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -929, 67, -1568));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -928, 69, -1564));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -925, 69, -1564));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -930, 67, -1582));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -923, 67, -1582));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -930, 67, -1600));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -923, 67, -1600));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -885, 67, -1720));
				MobManager.spawnEntity("DisGuard", new Location(Bukkit.getWorld("world"), -897, 67, -1721));
				
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -806, 67, -1594));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -811, 67, -1559));
				for(int y = 0;y >= 10; y++) {
					MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -811+y, 67, -1559+y));
				}
				
				for(int y = 0;y >= 10; y++) {
					MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -881+y, 67, -1549+y));
				}
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -816.500, 67.0, -1575.500));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -820.500, 67.0, -1575.500));
				
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -825.4151355070576, 67.0, -1579.5497237563711));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -834.4053463304422, 67.0, -1568.1901039546894));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -832.2875474612616, 67.0, -1550.8340452083696));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -815.6186634588864, 67.0, -1542.6986271614326));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -805.9459650801347, 67.0625, -1540.9185260580252));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -808.0897416319297, 67.0625, -1541.0027010226022));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -839.346235587378, 67.0, -1525.9945841054432));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -837.3713550613958, 67.0625, -1516.1276281036442));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -854.7001277521575, 67.0, -1510.2388035772572));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -876.6170089922504, 67.0, -1496.3526754017876));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -882.9247388460616, 67.0, -1506.936868141587));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -882.3981487752579, 67.0, -1519.9046471499773));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -881.8767688579385, 67.0, -1549.261603633669));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -885.9102294330563, 67.0, -1576.0130278235006));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -864.71145323381, 67.0, -1576.8337097818774));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -840.1149712207069, 67.0, -1576.7347341456596));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -797.0228657705987, 67.0, -1577.1332885134061));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -776.7505686921437, 67.0, -1576.8145537667253));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -821.1769248661118, 67.0, -1590.8605942764248));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -821.3946344890186, 67.0, -1618.4521120080776));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -840.1418949308608, 67.0, -1625.451293743839));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -883.6330989654141, 67.0, -1642.8564121811794));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -881.3225893322924, 67.0, -1663.1515657561717));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -882.9648795705074, 67.0, -1692.9263336331073));
				MobManager.spawnEntity("CitizenN", new Location(Bukkit.getWorld("world"), -879.9011044585322, 67.0, -1717.0227895414744));
				
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -706.5849380046388, 67.0, -1580.8868811906648));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -675.0286121275276, 67.0, -1589.5003378767303));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -662.5210942012867, 67.0, -1572.762882306675));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -633.7614728371844, 67.0, -1585.7311221392094));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -605.0968292599191, 67.0, -1579.8987158249688));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -574.1709988414893, 67.0, -1590.973273961405));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -537.1384084659393, 68.0, -1588.473553556946));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -504.838522256287, 67.0, -1602.4205289032989));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -472.6957587913796, 67.0, -1589.3151025514715));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -440.19342790017396, 67.0, -1606.075536116271));
				MobManager.spawnEntity("Citizen", new Location(Bukkit.getWorld("world"), -421.89219936135294, 67.0, -1599.067234134955));

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
