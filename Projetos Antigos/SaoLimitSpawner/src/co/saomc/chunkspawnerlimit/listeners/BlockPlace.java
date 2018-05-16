package co.saomc.chunkspawnerlimit.listeners;

import static co.saomc.chunkspawnerlimit.ChunkSpawnerLimit.instance;
import static co.saomc.chunkspawnerlimit.ChunkSpawnerLimit.limit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent e){
        if(e.getBlock().getType() != Material.MOB_SPAWNER){
            return;
        }
        Chunk c = e.getBlock().getLocation().getChunk();
        int spawnercount = 1;
        for (BlockState block : c.getTileEntities()) {
            if(block instanceof CreatureSpawner){
                spawnercount++;
                if(spawnercount > limit){
                    e.getPlayer().sendMessage("Já excedeu o limite de spanwer nessa área, O limite é de " + limit + " por chunk!");
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }
}
