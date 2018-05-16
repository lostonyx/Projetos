package co.saomc.chunkspawnerlimit.listeners;
import static co.saomc.chunkspawnerlimit.ChunkSpawnerLimit.cleanOnChunkLoad;
import static co.saomc.chunkspawnerlimit.ChunkSpawnerLimit.instance;
import static co.saomc.chunkspawnerlimit.ChunkSpawnerLimit.limit;
import co.saomc.chunkspawnerlimit.utils;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import static org.bukkit.Material.AIR;
import static org.bukkit.Material.MOB_SPAWNER;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChunkLoad implements Listener {
    
    @EventHandler(ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent e){
        Chunk c = e.getChunk();
        int spawnercount = 0;
        for (BlockState block : c.getTileEntities()) {
            if(block instanceof CreatureSpawner){
                spawnercount++;
                if(spawnercount > limit){
                    Location cloc = new Location(c.getWorld(),c.getX() * 16,64,c.getZ() * 16);
                    if(cleanOnChunkLoad){
                        ItemStack drop = new ItemStack(MOB_SPAWNER);
                        CreatureSpawner existing = (CreatureSpawner) block;
                        utils.setSpawnerMob(drop, existing.getSpawnedType());
                        ItemMeta itemMeta = drop.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.RESET + existing.getSpawnedType().name());
                        drop.setItemMeta(itemMeta);
                        
                        cloc.getWorld().dropItem(block.getLocation().add(0.5, 0.5, 0.5), drop);
                        block.setType(AIR);
                        block.update(true);
                    }
                    getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> {
                        cloc.getWorld().getNearbyEntities(cloc, 100,100,100).stream().filter((ent) -> (ent instanceof Player)).map((ent) -> (Player)ent).forEach((player) -> {
                            player.sendMessage("Muitos spawner nesse chunk!, O limite é de " + limit + " por chunk! x:" + block.getLocation().getBlockX() + " y:" + block.getLocation().getBlockY() + " z:" + block.getLocation().getBlockZ());
                        });
                    }, 20L);
                }
            }
        }
    }
}
