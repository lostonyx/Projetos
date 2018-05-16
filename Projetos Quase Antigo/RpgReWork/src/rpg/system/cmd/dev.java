package rpg.system.cmd;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import net.minecraft.server.v1_8_R3.Material;
import rpg.Main;
import rpg.mob.MobManager;
import rpg.system.Metodos;
import rpg.system.listener.MobListener;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.models.RegionManager;
import rpg.utils.ArmorStandAnimator;
import rpg.utils.CItem;
import rpg.utils.Cooldown;
import rpg.utils.HologramApi;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class dev extends Command{
	public dev() {
		super("dev");
	}
	
	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) return false;
        Player player = (Player) s;
        if("ani".contains(args[0].toLowerCase())) {
        	Location l = player.getLocation();
        	ArmorStand a1 = HologramApi.createHologram(l.add(0.0, 1, 0.0), "..");
        	a1.setGravity(false);
        	a1.setCustomNameVisible(false);
        	a1.setBasePlate(true);
        	a1.setHelmet(new CItem(org.bukkit.Material.STONE).build());
        	a1.setHeadPose(new EulerAngle(0f,0f,0f));
        	a1.setArms(true);
        	a1.setVisible(true);
        
        }
        if("anii".contains(args[0].toLowerCase())) {
        	Location l = player.getLocation();
        	ArmorStand a1 = HologramApi.createHologram(l.add(0.0, 1, 0.0), "..");
        	a1.setGravity(false);
        	a1.setCustomNameVisible(false);
        	a1.setBasePlate(true);
        	a1.setHelmet(new CItem(org.bukkit.Material.STONE).build());
        	a1.setArms(true);
        	a1.setVisible(true);
        
        }
        if("aniii".contains(args[0].toLowerCase())) {
        	Location l = player.getLocation();
        	ArmorStand a1 = HologramApi.createHologram(l.add(0.0, 1, 0.0), "..");
        	a1.setGravity(false);
        	a1.setCustomNameVisible(false);
        	a1.setBasePlate(true);
        	a1.setHelmet(new CItem(org.bukkit.Material.STONE).build());
        	a1.setArms(true);
        	a1.setVisible(true);
        	ArmorStandAnimator ani = new ArmorStandAnimator(new File(Main.GetInstance().getDataFolder(), "pet.animc"), a1);
        	ani.play();
        }
        if("item".contains(args[0].toLowerCase())) {
        	Metodos.notificarpeloitem(player, "sdsd");
        }
        if("cp".contains(args[0].toLowerCase())) {
        	Bukkit.broadcastMessage("Lore1: " + player.getItemInHand().toString());
        	Bukkit.broadcastMessage("Lore2: " + player.getItemInHand());
        	Bukkit.broadcastMessage("Lore3: " + player.getItemInHand().getItemMeta().getLore());
        }
        if("lv".contains(args[0].toLowerCase())) {
        	Jogador j = JogadorManager.getWynnPlayer(player);
        	j.getCurrentAccount().setLevel(Integer.parseInt(args[1]));
        }
        if("mn".contains(args[0].toLowerCase())) {
        	Jogador j = JogadorManager.getWynnPlayer(player);
        	j.getCurrentAccount().setMana(Integer.parseInt(args[1]));;
        }
        if("mb".contains(args[0].toLowerCase())) {
        	MobManager.spawn(MobManager.getMob(args[1]), player.getLocation(), 5);
        }
        if("chunk".contains(args[0].toLowerCase())) {
        	//player.sendMessage("Size: "+RegionManager.mobsincache.toString());
        	player.sendMessage("Chunk: "+player.getLocation().getChunk().getX()+";"+player.getLocation().getChunk().getZ());
        }
        if("xp".contains(args[0].toLowerCase())) {
        	player.sendMessage("Xp max: "+JogadorManager.getWynnPlayer(player).getCurrentAccount().getMaxxp());
        	player.sendMessage("Xp: "+JogadorManager.getWynnPlayer(player).getCurrentAccount().getXp());
        }
        if("make".contains(args[0].toLowerCase())) {
        	Bukkit.broadcastMessage("MobManager.spawnEntity(\"DisGuard\", new Location(Bukkit.getWorld(\"world\"), "+player.getLocation().getX()+", "+player.getLocation().getY()+", "+player.getLocation().getZ()+"));");
        }
		return false;
	}
}
