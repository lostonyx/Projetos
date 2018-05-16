package net.wiljafor1.sum;
import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.RunnableVal;
import com.sk89q.jnbt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class SubMain extends JavaPlugin implements CommandExecutor,Listener{
    public PlotAPI api;

    @Override
    public void onEnable() {
        getServer().broadcastMessage("oi");
    }
    
@SuppressWarnings("deprecation")
public boolean ConsomeItem(Player player, int count, Material mat, String nome , String lore) {
    Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);

    int found = 0;
    for (ItemStack stack : ammo.values())
        found += stack.getAmount();
    if (count > found)
        return false;

    for (Integer index : ammo.keySet()) {
        ItemStack stack = ammo.get(index);
        if(stack.getItemMeta().getDisplayName().contains(nome)){
            if(stack.getItemMeta().getLore().get(0).contains(lore)){
                int removed = Math.min(count, stack.getAmount());
                count -= removed;

                if (stack.getAmount() == removed)
                    player.getInventory().setItem(index, null);
                else
                    stack.setAmount(stack.getAmount() - removed);

                if (count <= 0)
                    break; 
                }
        }
    }

    player.updateInventory();
    return true;
}

    
@EventHandler
public void Item(PlayerInteractEvent e){
    final Player p = e.getPlayer();
    final Player pp = e.getPlayer();
    if(((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (p.getItemInHand().getTypeId() == 388)){
        if(p.getItemInHand().getItemMeta().hasDisplayName() == true){
            if(p.getItemInHand().getItemMeta().getDisplayName().contains("§4Plantacao")){
                if((p.getItemInHand().getItemMeta().getLore().get(0).contains("§4Fungo") && (!p.getItemInHand().getItemMeta().getLore().get(0).isEmpty()))){
                        p.getWorld().playSound(p.getLocation(), Sound.CLICK, 10, 1);
                        if(api.getPlot(pp.getLocation()) == null){
                        pp.sendMessage("Voce nao esta em uma plot");    
                        }
                        else{
                          if(api.getPlot(pp.getLocation()).isOwner(pp.getUniqueId())){
                          ConsomeItem(p, 1, Material.EMERALD, p.getItemInHand().getItemMeta().getDisplayName(), p.getItemInHand().getItemMeta().getLore().get(0)); 
                          Plot plot = api.getPlot(pp.getLocation());
                          Runnable whenDone = null;
                          Location l = new Location(Bukkit.getWorld(plot.getBottom().getWorld()), plot.getBottom().getX(), 33, plot.getBottom().getZ());
                          Location l2 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX(), 33, plot.getCenter().getZ());
                          Location l3 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX()+25, 33, plot.getCenter().getZ()+25);
                          l.getBlock().setType(Material.DIAMOND_BLOCK);
                          l2.getBlock().setType(Material.DIAMOND_BLOCK);
                          l3.getBlock().setType(Material.DIAMOND_BLOCK);
                          File pf = new File(getDataFolder(), "fungo.schematic");
                          Schematic she = null;
                              try {
                                  she = loadSchematic(pf);
                              } catch (IOException ex) {
                                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                              }
                          pasteSchematic(l2.getWorld(), l, she);
                          RunnableVal<Boolean> whenDones = null;
                          pp.sendMessage("Plantacao criada com sucesso!");
                          }
                          else{
                          pp.sendMessage("Voce nao e dono dessa plot");
                          }
                        }
                }
                if((p.getItemInHand().getItemMeta().getLore().get(0).contains("§4Cacto") && (!p.getItemInHand().getItemMeta().getLore().get(0).isEmpty()))){
                        p.getWorld().playSound(p.getLocation(), Sound.CLICK, 10, 1);
                        if(api.getPlot(pp.getLocation()) == null){
                        pp.sendMessage("Voce nao esta em uma plot");    
                        }
                        else{
                          if(api.getPlot(pp.getLocation()).isOwner(pp.getUniqueId())){
                          ConsomeItem(p, 1, Material.EMERALD, p.getItemInHand().getItemMeta().getDisplayName(), p.getItemInHand().getItemMeta().getLore().get(0)); 
                          Plot plot = api.getPlot(pp.getLocation());
                          Runnable whenDone = null;
                          Location l = new Location(Bukkit.getWorld(plot.getBottom().getWorld()), plot.getBottom().getX(), 1, plot.getBottom().getZ());
                          Location l2 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX(), 1, plot.getCenter().getZ());
                          Location l3 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX()+25, 33, plot.getCenter().getZ()+25);
                          l.getBlock().setType(Material.DIAMOND_BLOCK);
                          l2.getBlock().setType(Material.DIAMOND_BLOCK);
                          l3.getBlock().setType(Material.DIAMOND_BLOCK);
                          File pf = new File(getDataFolder(), "cacto.schematic");
                          Schematic she = null;
                              try {
                                  she = loadSchematic(pf);
                              } catch (IOException ex) {
                                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                              }
                          pasteSchematic(l2.getWorld(), l, she);
                          RunnableVal<Boolean> whenDones = null;
                          pp.sendMessage("Plantacao criada com sucesso!");
                          }
                          else{
                          pp.sendMessage("Voce nao e dono dessa plot");
                          }
                        }
                }
                if((p.getItemInHand().getItemMeta().getLore().get(0).contains("§4Cana") && (!p.getItemInHand().getItemMeta().getLore().get(0).isEmpty()))){
                        p.getWorld().playSound(p.getLocation(), Sound.CLICK, 10, 1);
                        if(api.getPlot(pp.getLocation()) == null){
                        pp.sendMessage("Voce nao esta em uma plot");    
                        }
                        else{
                          if(api.getPlot(pp.getLocation()).isOwner(pp.getUniqueId())){
                          ConsomeItem(p, 1, Material.EMERALD, p.getItemInHand().getItemMeta().getDisplayName(), p.getItemInHand().getItemMeta().getLore().get(0)); 
                          Plot plot = api.getPlot(pp.getLocation());
                          Runnable whenDone = null;
                          Location l = new Location(Bukkit.getWorld(plot.getBottom().getWorld()), plot.getBottom().getX(), 32, plot.getBottom().getZ());
                          Location l2 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX(), 32, plot.getCenter().getZ());
                          Location l3 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX()+25, 33, plot.getCenter().getZ()+25);
                          l.getBlock().setType(Material.DIAMOND_BLOCK);
                          l2.getBlock().setType(Material.DIAMOND_BLOCK);
                          l3.getBlock().setType(Material.DIAMOND_BLOCK);
                          File pf = new File(getDataFolder(), "cana.schematic");
                          Schematic she = null;
                              try {
                                  she = loadSchematic(pf);
                              } catch (IOException ex) {
                                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                              }
                          pasteSchematic(l2.getWorld(), l, she);
                          RunnableVal<Boolean> whenDones = null;
                          pp.sendMessage("Plantacao criada com sucesso!");
                          }
                          else{
                          pp.sendMessage("Voce nao e dono dessa plot");
                          }
                        }
                }
            }   
        }

    }
}
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
                if(cs instanceof Player){
                    Player player = (Player)cs;
                    if(args.length == 2){
                      if(args[0].contains("fungo")){
                          if(cs.hasPermission("saints.dono")){
                          Player pp = getServer().getPlayerExact(args[1]);
                          if(pp == null){
                          cs.sendMessage("§6[§2SUM§6] §6"+ args[1] +" §enao esta online!");
                          }
                          else{
                          if(api.getPlot(pp.getLocation()).isOwner(pp.getUniqueId())){
                          Plot plot = api.getPlot(pp.getLocation());
                          Runnable whenDone = null;
                          //plot.clear(whenDone);
                          //pp.sendMessage("x:"+ plot.getBottom());
                          Location l = new Location(Bukkit.getWorld(plot.getBottom().getWorld()), plot.getBottom().getX(), 33, plot.getBottom().getZ());
                          Location l2 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX(), 33, plot.getCenter().getZ());
                          Location l3 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX()+25, 33, plot.getCenter().getZ()+25);
                          l.getBlock().setType(Material.DIAMOND_BLOCK);
                          l2.getBlock().setType(Material.DIAMOND_BLOCK);
                          l3.getBlock().setType(Material.DIAMOND_BLOCK);
                          //pp.teleport(l);
                          //pp.sendMessage("X:"+ l.toString());
                          //pp.sendMessage("z:"+ l2.toString());
                          File p = new File(getDataFolder(), "fungo.schematic");
                          Schematic she = null;
                              try {
                                  she = loadSchematic(p);
                              } catch (IOException ex) {
                                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                              }
                          pasteSchematic(l2.getWorld(), l, she);
                          //Schematic she = api.getSchematicHandler().getSchematic(p);
                          RunnableVal<Boolean> whenDones = null;
                         //api.getSchematicHandler().paste(she, plot, l2.getBlockX(), l2.getBlockY(), l2.getBlockZ(), true, whenDones);
                          
                          pp.sendMessage("voce e dono");
                          }
                          else{
                          pp.sendMessage("voce n e dono");
                          }
                          }
                          }
                      }
                      if(args[0].contains("cana")){
                          if(cs.hasPermission("saints.dono")){
                          Player pp = getServer().getPlayerExact(args[1]);
                          if(pp == null){
                          cs.sendMessage("§6[§2SUM§6] §6"+ args[1] +" §enao esta online!");
                          }
                          else{
                          if(api.getPlot(pp.getLocation()).isOwner(pp.getUniqueId())){
                          Plot plot = api.getPlot(pp.getLocation());
                          Runnable whenDone = null;
                          //plot.clear(whenDone);
                          //pp.sendMessage("x:"+ plot.getBottom());
                          Location l = new Location(Bukkit.getWorld(plot.getBottom().getWorld()), plot.getBottom().getX(), 1, plot.getBottom().getZ());
                          Location l2 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX(), 1, plot.getCenter().getZ());
                          Location l3 = new Location(Bukkit.getWorld(plot.getCenter().getWorld()), plot.getCenter().getX()+25, 33, plot.getCenter().getZ()+25);
                          l.getBlock().setType(Material.DIAMOND_BLOCK);
                          l2.getBlock().setType(Material.DIAMOND_BLOCK);
                          l3.getBlock().setType(Material.DIAMOND_BLOCK);
                          //pp.teleport(l);
                          //pp.sendMessage("X:"+ l.toString());
                          //pp.sendMessage("z:"+ l2.toString());
                          File p = new File(getDataFolder(), "cana.schematic");
                          Schematic she = null;
                              try {
                                  she = loadSchematic(p);
                              } catch (IOException ex) {
                                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                              }
                          pasteSchematic(l2.getWorld(), l, she);
                          //Schematic she = api.getSchematicHandler().getSchematic(p);
                          RunnableVal<Boolean> whenDones = null;
                         //api.getSchematicHandler().paste(she, plot, l2.getBlockX(), l2.getBlockY(), l2.getBlockZ(), true, whenDones);
                          
                          pp.sendMessage("voce e dono");
                          }
                          else{
                          pp.sendMessage("voce n e dono");
                          }
                          }
                          }
                      }
                }  
                }
                else{
                if (cmnd.getName().equalsIgnoreCase("maconheiro")) {
                if(args.length == 2){
                      if(args[0].contains("cana")){
                          if(cs.hasPermission("saints.dono")){
                          Player pp = getServer().getPlayerExact(args[1]);
                          if(pp == null){
                          cs.sendMessage("§6[§2SUM§6] §6"+ args[1] +" §enao esta online!");
                          }
                          else{
                          if(api.getPlot(pp.getLocation()).isOwner(pp.getUniqueId())){
                          pp.sendMessage("voce e dono");
                          }
                          else{
                          pp.sendMessage("voce n e dono");
                          }
                          }
                          }
                      }
                }  
                }
                }
                return true;
    }
    
    public static void pasteSchematic(World world, Location loc, Schematic schematic)
    {
        byte[] blocks = schematic.getBlocks();
        byte[] blockData = schematic.getData();
 
        short length = schematic.getLenght();
        short width = schematic.getWidth();
        short height = schematic.getHeight();
 
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    Block block = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();
                    block.setTypeIdAndData(blocks[index], blockData[index], true);
                }
            }
        }
    }
 
    public static Schematic loadSchematic(File file) throws IOException
    {
        FileInputStream stream = new FileInputStream(file);
        NBTInputStream nbtStream = new NBTInputStream(new GZIPInputStream(stream));
 
        CompoundTag schematicTag = (CompoundTag) /*nbtStream.readTag*/nbtStream.readNamedTag().getTag();
 
        Map<String, Tag> schematic = schematicTag.getValue();
        if (!schematic.containsKey("Blocks")) {
            throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
        }
 
        short width = getChildTag(schematic, "Width", ShortTag.class).getValue();
        short length = getChildTag(schematic, "Length", ShortTag.class).getValue();
        short height = getChildTag(schematic, "Height", ShortTag.class).getValue();
 
        String materials = getChildTag(schematic, "Materials", StringTag.class).getValue();
        if (!materials.equals("Alpha")) {
            throw new IllegalArgumentException("Schematic file is not an Alpha schematic");
        }
 
        byte[] blocks = getChildTag(schematic, "Blocks", ByteArrayTag.class).getValue();
        byte[] blockData = getChildTag(schematic, "Data", ByteArrayTag.class).getValue();
        return new Schematic(blocks, blockData, width, length, height);
    }
 
    /**
    * Get child tag of a NBT structure.
    *
    * @param items The parent tag map
    * @param key The name of the tag to get
    * @param expected The expected type of the tag
    * @return child tag casted to the expected type
    * @throws DataException if the tag does not exist or the tag is not of the
    * expected type
    */
    private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException
    {
        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
        }
        Tag tag = items.get(key);
        if (!expected.isInstance(tag)) {
            throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
        }
        return expected.cast(tag);
    }
}