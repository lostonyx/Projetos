package net.saintsupenchant.listener;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.lightshard.prisonmines.MineAPI;
import net.lightshard.prisonmines.mine.Mine;
import net.minecraft.server.v1_8_R3.Enchantment;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.saintsupenchant.Main;
import net.saintsupenchant.util.CItem;
import net.saintsupenchant.util.Cuboid;
import net.saintsupenchant.util.Messages;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_8_R3.MapIcon;
import org.bukkit.DyeColor;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Door;
import org.bukkit.material.Dye;
import org.bukkit.scheduler.BukkitScheduler;

public class Enchant implements Listener{
    Main plugin;
    
    
    public Enchant(Main plugin) {
        this.plugin = plugin;
    }  
    public static HashMap<String,String> enchan = new HashMap<String,String>();
    public static HashMap<String, Integer> stop = new HashMap<String, Integer>();
    public static HashMap<String, Integer> blockFace = new HashMap<String, Integer>();
    public static HashMap<String, Integer> caclrad = new HashMap<String, Integer>();
    public static ArrayList<Material> listHM = new ArrayList<Material>();
    public static int task;
    public static boolean eventodouble = false;
    

    @EventHandler
    public void getBlockFace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        BlockFace bFace = event.getBlockFace();
        if ((itemInHand.getType() == Material.DIAMOND_PICKAXE)) {
            if ((bFace == BlockFace.UP) || (bFace == BlockFace.DOWN)) {
                blockFace.put(player.getName(), Integer.valueOf(1));
            }
            if ((bFace == BlockFace.NORTH) || (bFace == BlockFace.SOUTH)) {
                blockFace.put(player.getName(), Integer.valueOf(2));
            }
            if ((bFace == BlockFace.WEST) || (bFace == BlockFace.EAST)) {
                blockFace.put(player.getName(), Integer.valueOf(3));
            }
        }
    }
    
    public int getLuck(int level) {
        Random random = new Random();
        int Item = random.nextInt(4) + 1;
        if(level >= 30) {
            int max = level / 6;
            int least = level / 7;
            Item = random.nextInt(max) + least;
        }
        return Item;
        }
    
    @EventHandler
    public void onPickAxeBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        ItemMeta im = player.getItemInHand().getItemMeta();
        ItemStack is = player.getItemInHand();
        Location l1 = (Location)plugin.getConfig().get("loc.1");
        Location l2 = (Location)plugin.getConfig().get("loc.2");
        if((l1.getX() > player.getLocation().getX()) && (l1.getZ() > player.getLocation().getZ()) && (player.getLocation().getX() > l2.getX()) && (player.getLocation().getZ() > l2.getZ())){
        if (itemInHand.getType() == Material.DIAMOND_PICKAXE) {
            ArrayList<Material> list = listHM;
            list.add(Material.REDSTONE_ORE);
            if(list.contains(event.getBlock().getType())){
            if(!plugin.getConfig().contains("token."+player.getName().toLowerCase())){
                plugin.getConfig().set("token."+player.getName().toLowerCase(), "0");
                plugin.saveConfig();
            }
            else{
                if(eventodouble==true){
                plugin.getConfig().set("token."+player.getName().toLowerCase(), plugin.getConfig().getInt("token."+player.getName().toLowerCase())+2); 
                plugin.saveConfig();
                }
                else{
                plugin.getConfig().set("token."+player.getName().toLowerCase(), plugin.getConfig().getInt("token."+player.getName().toLowerCase())+1); 
                plugin.saveConfig();
                }
            }  
            }
            if(im.getLore() != null){
                List<String> lores = null;
                lores = im.getLore();
                if(lores.contains("§7Explosive")){
                    String enlvl = im.getLore().get(1).replace("§7nivel: ", "");
                    Random rand = new Random();
                    int  n = rand.nextInt(100) + 1;
                    if (n<=Integer.parseInt(enlvl)){
                        Detrycalc(player, event.getBlock().getLocation());
                        //Explosive(listHM, event);   
                    }
                    else{
                        return;
                    }             
                }
                if(lores.contains("§7Laser")){
                    
                    String enlvl = im.getLore().get(1).replace("§7nivel: ", "");
                    Random rand = new Random();
                    int  n = rand.nextInt(100) + 1;
                    //player.sendMessage(""+n);
                    if (n<=Integer.parseInt(enlvl)){
                        //player.sendMessage("foi");
                        Lasercalc(player, event.getBlock().getLocation());
                    }
                    else{
                        return;
                    }             
                }
                if(lores.contains("§7Destructor")){
                    String enlvl = im.getLore().get(1).replace("§7nivel: ", "");
                    Random rand = new Random();
                    int np = 0;
                    if(Integer.parseInt(enlvl)==1){
                        np= 1;   
                    }
                    if(Integer.parseInt(enlvl)==2){
                        np= 1;   
                    }
                    if(Integer.parseInt(enlvl)==3){
                        np= 1;   
                    }
                    if(Integer.parseInt(enlvl)==4){
                        np= 1;   
                    }
                    if(Integer.parseInt(enlvl)==5){
                        np= 1;   
                    }
                    if(Integer.parseInt(enlvl)==6){
                        np= 2;   
                    }
                    if(Integer.parseInt(enlvl)==7){
                        np= 2;   
                    }
                    if(Integer.parseInt(enlvl)==8){
                        np= 2;   
                    }
                    if(Integer.parseInt(enlvl)==9){
                        np= 2;   
                    }
                    if(Integer.parseInt(enlvl)==10){
                        np= 2;   
                    }
                    if(Integer.parseInt(enlvl)>10){
                        np= Integer.parseInt(enlvl);   
                    }
                    int  n = rand.nextInt(180) + 1;
                    //player.sendMessage(""+n);
                    if (n-2<=np){
                        plugin.getServer().dispatchCommand(player, "vender");
                        cylinder(event.getBlock().getLocation(), player);
                    }
                    else{
                        return;
                    }  
                    //Detrycalc(player, event.getBlock().getLocation()); 
                    //getBlocks(event.getBlock(), 0);
                }
            }else return;
        }else return; 
        }
        else return;

    }

    public void Explosive(ArrayList<Material> list, BlockBreakEvent event) {
        if (!event.isCancelled()) {
            Player player = event.getPlayer();
            ItemStack itemInHand = player.getItemInHand();
            Block mainBlock = event.getBlock();
            Block mainBlock2 = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY()-1, event.getBlock().getZ()).getBlock();
            Block mainBlock3 = new Location(event.getBlock().getWorld(), event.getBlock().getX(), event.getBlock().getY()-2, event.getBlock().getZ()).getBlock();
            int total = 0;
            int enchant = 0;
            stop.put(player.getName(), Integer.valueOf(1));
            if (itemInHand.containsEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS)) {
                enchant = 1;
            } else {
                if(itemInHand.containsEnchantment(org.bukkit.enchantments.Enchantment.SILK_TOUCH)){
                enchant = 1;    
                }
                else{
                enchant = 0;    
                }
            }

            ArrayList<Block> blocks = new ArrayList<Block>();
            if (((Integer) blockFace.get(player.getName())).intValue() == 1) {
                blocks.add(mainBlock.getRelative(BlockFace.SELF));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH_WEST));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH_EAST));
                blocks.add(mainBlock.getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.EAST));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH_WEST));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH_EAST));
                blocks.add(mainBlock2.getRelative(BlockFace.SELF));
                blocks.add(mainBlock2.getRelative(BlockFace.NORTH_WEST));
                blocks.add(mainBlock2.getRelative(BlockFace.NORTH));
                blocks.add(mainBlock2.getRelative(BlockFace.NORTH_EAST));
                blocks.add(mainBlock2.getRelative(BlockFace.WEST));
                blocks.add(mainBlock2.getRelative(BlockFace.EAST));
                blocks.add(mainBlock2.getRelative(BlockFace.SOUTH_WEST));
                blocks.add(mainBlock2.getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock2.getRelative(BlockFace.SOUTH_EAST)); 
                //blocks.add(mainBlock3.getRelative(BlockFace.SELF));
                //blocks.add(mainBlock3.getRelative(BlockFace.NORTH_WEST));
                //blocks.add(mainBlock3.getRelative(BlockFace.NORTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.NORTH_EAST));
                //blocks.add(mainBlock3.getRelative(BlockFace.WEST));
                //blocks.add(mainBlock3.getRelative(BlockFace.EAST));
                //blocks.add(mainBlock3.getRelative(BlockFace.SOUTH_WEST));
                //blocks.add(mainBlock3.getRelative(BlockFace.SOUTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.SOUTH_EAST)); 
            }
            if (((Integer) blockFace.get(player.getName())).intValue() == 2) {
                blocks.add(mainBlock.getRelative(BlockFace.SELF));
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.UP));
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
                blocks.add(mainBlock.getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.EAST));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
                blocks.add(mainBlock2.getRelative(BlockFace.SELF));
                blocks.add(mainBlock2.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
                blocks.add(mainBlock2.getRelative(BlockFace.UP));
                blocks.add(mainBlock2.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
                blocks.add(mainBlock2.getRelative(BlockFace.WEST));
                blocks.add(mainBlock2.getRelative(BlockFace.EAST));
                blocks.add(mainBlock2.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
                blocks.add(mainBlock2.getRelative(BlockFace.DOWN));
                blocks.add(mainBlock2.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
                //blocks.add(mainBlock3.getRelative(BlockFace.SELF));
                //blocks.add(mainBlock3.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
                //blocks.add(mainBlock3.getRelative(BlockFace.UP));
                //blocks.add(mainBlock3.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
                //blocks.add(mainBlock3.getRelative(BlockFace.WEST));
                //blocks.add(mainBlock3.getRelative(BlockFace.EAST));
                //blocks.add(mainBlock3.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
                //blocks.add(mainBlock3.getRelative(BlockFace.DOWN));
                //blocks.add(mainBlock3.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
            }
            if (((Integer) blockFace.get(player.getName())).intValue() == 3) {
                blocks.add(mainBlock.getRelative(BlockFace.SELF));
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.UP));
                blocks.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock.getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN));
                blocks.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock2.getRelative(BlockFace.SELF));
                blocks.add(mainBlock2.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
                blocks.add(mainBlock2.getRelative(BlockFace.UP));
                blocks.add(mainBlock2.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock2.getRelative(BlockFace.NORTH));
                blocks.add(mainBlock2.getRelative(BlockFace.SOUTH));
                blocks.add(mainBlock2.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
                blocks.add(mainBlock2.getRelative(BlockFace.DOWN));
                blocks.add(mainBlock2.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.SELF));
                //blocks.add(mainBlock3.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.UP));
                //blocks.add(mainBlock3.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.NORTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.SOUTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
                //blocks.add(mainBlock3.getRelative(BlockFace.DOWN));
                //blocks.add(mainBlock3.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
            }
            for (Block block : blocks) {
                //player.sendMessage(""+ block.toString());
                if (list.contains(block.getType())) {
                    if (enchant == 0) {
                        //block.breakNaturally();
                        byte data = block.getData();
                        Material drop = block.getType();
                        if(drop == Material.DIAMOND_ORE){
                           drop = Material.DIAMOND;
                        }
                        if(drop == Material.GOLD_ORE){
                           drop = Material.GOLD_INGOT;
                        }
                        if(drop == Material.IRON_ORE){
                           drop = Material.IRON_INGOT;
                        }
                        if(drop == Material.LAPIS_ORE){
                           drop = Material.INK_SACK;
                        }
                        if(drop == Material.REDSTONE_ORE){
                           drop = Material.REDSTONE;
                        }

                        player.getInventory().addItem(new ItemStack(drop, 1, data));
                        block.setType(Material.AIR);
                        total++;
                    }
                    if (enchant == 1) {
                        if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS)){
                        int lvl = player.getItemInHand().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS);
                        getLuck(lvl);
                        byte data = block.getData();
                        Material drop = block.getType();
                        if(drop == Material.DIAMOND_ORE){
                           drop = Material.DIAMOND;
                        }
                        if(drop == Material.GOLD_ORE){
                           drop = Material.GOLD_INGOT;
                        }
                        if(drop == Material.IRON_ORE){
                           drop = Material.IRON_INGOT;
                        }
                        if(drop == Material.LAPIS_ORE){
                           drop = Material.INK_SACK;
                        }
                        if(drop == Material.REDSTONE_ORE){
                           drop = Material.REDSTONE;
                        }

                        player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        block.setType(Material.AIR);
                        }
                        else if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.SILK_TOUCH)){
                        byte data = block.getData();
                        Material drop = block.getType();
                        player.getInventory().addItem(new ItemStack(drop, 1, data));
                        block.setType(Material.AIR);
                
                        }
                        
                        //byte data = block.getData();
                        //Material drop = block.getType();
                        //block.setType(Material.AIR);
                        //mainBlock.getLocation().getWorld().dropItemNaturally(mainBlock.getLocation(),
                        //        new ItemStack(drop, 1, data));
                        //mainBlock2.getLocation().getWorld().dropItemNaturally(mainBlock2.getLocation(),
                        //        new ItemStack(drop, 1, data));
                        //mainBlock3.getLocation().getWorld().dropItemNaturally(mainBlock3.getLocation(),
                                //new ItemStack(drop, 1, data));
                        total++;
                    }
                }
            }
        }
    }
    
    public boolean checarbloco(Material mat, ArrayList<Material> list, Player p, Location loc){
            //p.sendMessage("mat: " + mat);
            if(list.contains(mat)){
                if(mat.equals(Material.AIR)){
                    return true;    
                }
                else{
                //p.sendMessage("foi");
                if(p.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
                    
                    //p.sendMessage(p.getItemInHand().getEnchantments().toString());
                    if(p.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS)){
                        int lvl = p.getItemInHand().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS);
                        getLuck(lvl);
                        byte data = loc.getBlock().getData();
                        Material drop = loc.getBlock().getType();
                        if(drop == Material.DIAMOND_ORE){
                           drop = Material.DIAMOND;
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.GOLD_ORE){
                           drop = Material.GOLD_INGOT;
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.IRON_ORE){
                           drop = Material.IRON_INGOT;
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.LAPIS_ORE){
                           //Dye l = new Dye();
                           //l.setColor(DyeColor.BLUE);
                           //ItemStack lapis = l.toItemStack();
                            int itemID = 351;
                            int amountInStack = 1*getLuck(lvl);
                            short itemDamage = 4;
                            ItemStack itemstack = new ItemStack(itemID, amountInStack, itemDamage);
                           p.getInventory().addItem(itemstack);
                        }
                        if(drop == Material.REDSTONE_ORE){
                           drop = Material.REDSTONE;
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.QUARTZ_ORE){
                           drop = Material.QUARTZ; 
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.LAPIS_BLOCK){
                           drop = Material.LAPIS_BLOCK;
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.EMERALD_ORE){
                           drop = Material.EMERALD; 
                           p.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }

                    loc.getBlock().setType(Material.AIR);
                    //loc.getBlock().breakNaturally();
                        return true; 
                    }
                    else if(p.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.SILK_TOUCH)){
                    byte data = loc.getBlock().getData();
                    Material drop = loc.getBlock().getType();
                    p.getInventory().addItem(new ItemStack(drop, 1, data));
                    loc.getBlock().setType(Material.AIR);
                    //loc.getBlock().breakNaturally();
                
                    return true;  
                    }
                    else{
                    byte data = loc.getBlock().getData();
                    Material drop = loc.getBlock().getType();
                        if(drop == Material.DIAMOND_ORE){
                           drop = Material.DIAMOND;
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.GOLD_ORE){
                           drop = Material.GOLD_INGOT;
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.IRON_ORE){
                           drop = Material.IRON_INGOT;
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.LAPIS_ORE){
                           //Dye l = new Dye();
                           //l.setColor(DyeColor.BLUE);
                           //ItemStack lapis = l.toItemStack();
                            int itemID = 351;
                            int amountInStack = 1;
                            short itemDamage = 4;
                            ItemStack itemstack = new ItemStack(itemID, amountInStack, itemDamage);
                           p.getInventory().addItem(itemstack);
                        }
                        if(drop == Material.REDSTONE_ORE){
                           drop = Material.REDSTONE;
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.QUARTZ_ORE){
                           drop = Material.QUARTZ;
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.LAPIS_BLOCK){
                           drop = Material.LAPIS_BLOCK;
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.EMERALD_ORE){
                           drop = Material.EMERALD; 
                           p.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                    loc.getBlock().setType(Material.AIR);
                    //loc.getBlock().breakNaturally();
                
                    return true;  
                    }
                    
                }
                return true;
                }
            }
            else{
                //p.sendMessage("fail");
                return false;
            }
        
        }
        
    public void calcular1(Player player, Location lacalizacao){
       boolean Pos1 = false;
       boolean Pos2 = false;
       boolean ift = false;
       Location loc = lacalizacao;
       Location new1 = loc;
       Location new2 = loc;
       //player.sendMessage(new1.toString());
        if(Pos1 == false){//1
         if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
           //player.sendMessage("..");
           new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
           //player.sendMessage(new1.toString());
            if(Pos1 == false){//2
                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                    //player.sendMessage("..");
                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                    //player.sendMessage(new1.toString());
                        if(Pos1 == false){//3
                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                //player.sendMessage("..");
                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                //player.sendMessage(new1.toString());
                                if(Pos1 == false){//4
                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                        //player.sendMessage("..");
                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                        //player.sendMessage(new1.toString());
                                        if(Pos1 == false){//5
                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                //player.sendMessage("..");
                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                //player.sendMessage(new1.toString());
                                                if(Pos1 == false){//6
                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                        //player.sendMessage("..");
                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                        //player.sendMessage(new1.toString());
                                                        if(Pos1 == false){//7
                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                //player.sendMessage("..");
                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                //player.sendMessage(new1.toString());
                                                                if(Pos1 == false){//8
                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                        //player.sendMessage("..");
                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                            //player.sendMessage(new1.toString());
                                                                            if(Pos1 == false){//9
                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                    //player.sendMessage("..");
                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                    //player.sendMessage(new1.toString());
                                                                                    if(Pos1 == false){//10
                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                            //player.sendMessage("..");
                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                            //player.sendMessage(new1.toString());
                                                                                                if(Pos1 == false){//11
                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                        //player.sendMessage("..");
                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                        //player.sendMessage(new1.toString());
                                                                                                            if(Pos1 == false){//12
                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                    //player.sendMessage("..");
                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                        if(Pos1 == false){//13
                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                //player.sendMessage("..");
                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                    if(Pos1 == false){//14
                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                            //player.sendMessage("..");
                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                if(Pos1 == false){//15
                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                        if(Pos1 == false){//16
                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                    if(Pos1 == false){//17
                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                            if(Pos1 == false){//18
                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                        if(Pos1 == false){//19
                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                    if(Pos1 == false){//20
                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                if(Pos1 == false){//21
                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                            if(Pos1 == false){//22
                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                        if(Pos1 == false){//23
                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                    if(Pos1 == false){//24
                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                if(Pos1 == false){//25
                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                            if(Pos1 == false){//26
                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                        if(Pos1 == false){//27
                                                                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                    if(Pos1 == false){//28
                                                                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                if(Pos1 == false){//29
                                                                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                            if(Pos1 == false){//30
                                                                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                                    ////player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                                                    Pos1 = true;
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                                    //new1.getBlock().setType(Material.DIAMOND_BLOCK);
                                                                                                                                                                                                                                                                                                                                    ////player.teleport(new1);
                                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                            }  
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                                    Pos1 = true;
                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                }         
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        else{
                                                                                                                                                                                                                                                                                                            Pos1 = true;
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                            //player.teleport(new1);
                                                                                                                                                                                                                                                                                                        }                                  
                                                                                                                                                                                                                                                                                                    }          
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                    Pos1 = true;
                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                }                                  
                        }          
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos1 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }
                }
                else{
                    Pos1 = true;
                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                    //player.sendMessage("Acabou!");
                    //player.teleport(new1);
                }                           
            }
        }
        else{
            Pos1 = true;
            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
            //player.sendMessage("Acabou!");
            //player.teleport(new1);
        }                           
    }

         /*if((Pos1 == true)&&(Pos2 = true)){
          ift = true;
          }   */ 
       } 

    public void calcular2(Player player, Location lacalizacao){
       boolean Pos1 = false;
       boolean Pos2 = false;
       boolean ift = false;
       Location loc = lacalizacao;
       Location new1 = new Location(loc.getWorld(), loc.getX()-1.0, loc.getY(), loc.getZ()-1.0);
       Location new2 = loc;
       //player.sendMessage(new1.toString());
        if(Pos2 == false){//1
         if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
           //player.sendMessage("..");
           new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
           //player.sendMessage(new1.toString());
            if(Pos2 == false){//2
                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                    //player.sendMessage("..");
                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                    //player.sendMessage(new1.toString());
                        if(Pos2 == false){//3
                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                //player.sendMessage("..");
                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                //player.sendMessage(new1.toString());
                                if(Pos2 == false){//4
                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                        //player.sendMessage("..");
                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                        //player.sendMessage(new1.toString());
                                        if(Pos2 == false){//5
                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                //player.sendMessage("..");
                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                //player.sendMessage(new1.toString());
                                                if(Pos2 == false){//6
                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                        //player.sendMessage("..");
                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                        //player.sendMessage(new1.toString());
                                                        if(Pos2 == false){//7
                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                //player.sendMessage("..");
                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                //player.sendMessage(new1.toString());
                                                                if(Pos2 == false){//8
                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                        //player.sendMessage("..");
                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                            //player.sendMessage(new1.toString());
                                                                            if(Pos2 == false){//9
                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                    //player.sendMessage("..");
                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                    //player.sendMessage(new1.toString());
                                                                                    if(Pos2 == false){//10
                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                            //player.sendMessage("..");
                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                            //player.sendMessage(new1.toString());
                                                                                                if(Pos2 == false){//11
                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                        //player.sendMessage("..");
                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                        //player.sendMessage(new1.toString());
                                                                                                            if(Pos2 == false){//12
                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                    //player.sendMessage("..");
                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                        if(Pos2 == false){//13
                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                //player.sendMessage("..");
                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                    if(Pos2 == false){//14
                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                            //player.sendMessage("..");
                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                if(Pos2 == false){//15
                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                        if(Pos2 == false){//16
                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                    if(Pos2 == false){//17
                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                            if(Pos2 == false){//18
                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                        if(Pos2 == false){//19
                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                    if(Pos2 == false){//20
                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                if(Pos2 == false){//21
                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                            if(Pos2 == false){//22
                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                        if(Pos2 == false){//23
                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                    if(Pos2 == false){//24
                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                if(Pos2 == false){//25
                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                            if(Pos2 == false){//26
                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                        if(Pos2 == false){//27
                                                                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                    if(Pos2 == false){//28
                                                                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                if(Pos2 == false){//29
                                                                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                            if(Pos2 == false){//30
                                                                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                                    ////player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                else{
																																																																																
                                                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                                    //new1.getBlock().setType(Material.DIAMOND_BLOCK);
                                                                                                                                                                                                                                                                                                                                    ////player.teleport(new1);
                                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                            }  
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                }         
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        else{
                                                                                                                                                                                                                                                                                                            Pos2 = true;
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                            //player.teleport(new1);
                                                                                                                                                                                                                                                                                                        }                                  
                                                                                                                                                                                                                                                                                                    }          
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }
                }
                else{
                    Pos2 = true;
                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
                    //player.sendMessage("Acabou!");
                    //player.teleport(new1);
                }                           
            }
        }
        else{
            Pos2 = true;
            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()+1.0);
            //player.sendMessage("Acabou!");
            //player.teleport(new1);
        }                           
    }

         /*if((Pos2 == true)&&(Pos2 = true)){
          ift = true;
          }   */ 
       }
       
    public void calcular3(Player player, Location lacalizacao){
       boolean Pos1 = false;
       boolean Pos2 = false;
       boolean ift = false;
       Location loc = lacalizacao;
       Location new1 = new Location(loc.getWorld(), loc.getX()+1.0, loc.getY(), loc.getZ()-1.0);
       Location new2 = loc;
       //player.sendMessage(new1.toString());
        if(Pos2 == false){//1
         if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
           //player.sendMessage("..");
           new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
           //player.sendMessage(new1.toString());
            if(Pos2 == false){//2
                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                    //player.sendMessage("..");
                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                    //player.sendMessage(new1.toString());
                        if(Pos2 == false){//3
                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                //player.sendMessage("..");
                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                //player.sendMessage(new1.toString());
                                if(Pos2 == false){//4
                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                        //player.sendMessage("..");
                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                        //player.sendMessage(new1.toString());
                                        if(Pos2 == false){//5
                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                //player.sendMessage("..");
                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                //player.sendMessage(new1.toString());
                                                if(Pos2 == false){//6
                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                        //player.sendMessage("..");
                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                        //player.sendMessage(new1.toString());
                                                        if(Pos2 == false){//7
                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                //player.sendMessage("..");
                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                //player.sendMessage(new1.toString());
                                                                if(Pos2 == false){//8
                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                        //player.sendMessage("..");
                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                            //player.sendMessage(new1.toString());
                                                                            if(Pos2 == false){//9
                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                    //player.sendMessage("..");
                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                    //player.sendMessage(new1.toString());
                                                                                    if(Pos2 == false){//10
                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                            //player.sendMessage("..");
                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                            //player.sendMessage(new1.toString());
                                                                                                if(Pos2 == false){//11
                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                        //player.sendMessage("..");
                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                        //player.sendMessage(new1.toString());
                                                                                                            if(Pos2 == false){//12
                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                    //player.sendMessage("..");
                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                        if(Pos2 == false){//13
                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                //player.sendMessage("..");
                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                    if(Pos2 == false){//14
                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                            //player.sendMessage("..");
                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                if(Pos2 == false){//15
                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                        if(Pos2 == false){//16
                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                    if(Pos2 == false){//17
                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                            if(Pos2 == false){//18
                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                        if(Pos2 == false){//19
                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                    if(Pos2 == false){//20
                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                if(Pos2 == false){//21
                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                            if(Pos2 == false){//22
                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                        if(Pos2 == false){//23
                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                    if(Pos2 == false){//24
                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                if(Pos2 == false){//25
                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                            if(Pos2 == false){//26
                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                        if(Pos2 == false){//27
                                                                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                    if(Pos2 == false){//28
                                                                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                if(Pos2 == false){//29
                                                                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                            if(Pos2 == false){//30
                                                                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                                    ////player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                else{
																																																																																
                                                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                                    //new1.getBlock().setType(Material.DIAMOND_BLOCK);
                                                                                                                                                                                                                                                                                                                                    ////player.teleport(new1);
                                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                            }  
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                }         
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        else{
                                                                                                                                                                                                                                                                                                            Pos2 = true;
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                            //player.teleport(new1);
                                                                                                                                                                                                                                                                                                        }                                  
                                                                                                                                                                                                                                                                                                    }          
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }
                }
                else{
                    Pos2 = true;
                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
                    //player.sendMessage("Acabou!");
                    //player.teleport(new1);
                }                           
            }
        }
        else{
            Pos2 = true;
            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()+1.0, new1.getZ()+1.0);
            //player.sendMessage("Acabou!");
            //player.teleport(new1);
        }                           
    }

         /*if((Pos2 == true)&&(Pos2 = true)){
          ift = true;
          }   */ 
       }
       
    public void calcular4(Player player, Location lacalizacao){
       boolean Pos1 = false;
       boolean Pos2 = false;
       boolean ift = false;
       Location loc = lacalizacao;
       Location new1 = new Location(loc.getWorld(), loc.getX()-1.0, loc.getY(), loc.getZ()+1.0);
       Location new2 = loc;
       //player.sendMessage(new1.toString());
        if(Pos2 == false){//1
         if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
           //player.sendMessage("..");
           new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
           //player.sendMessage(new1.toString());
            if(Pos2 == false){//2
                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                    //player.sendMessage("..");
                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                    //player.sendMessage(new1.toString());
                        if(Pos2 == false){//3
                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                //player.sendMessage("..");
                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                //player.sendMessage(new1.toString());
                                if(Pos2 == false){//4
                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                        //player.sendMessage("..");
                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                        //player.sendMessage(new1.toString());
                                        if(Pos2 == false){//5
                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                //player.sendMessage("..");
                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                //player.sendMessage(new1.toString());
                                                if(Pos2 == false){//6
                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                        //player.sendMessage("..");
                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                        //player.sendMessage(new1.toString());
                                                        if(Pos2 == false){//7
                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                //player.sendMessage("..");
                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                //player.sendMessage(new1.toString());
                                                                if(Pos2 == false){//8
                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                        //player.sendMessage("..");
                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                            //player.sendMessage(new1.toString());
                                                                            if(Pos2 == false){//9
                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                    //player.sendMessage("..");
                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                    //player.sendMessage(new1.toString());
                                                                                    if(Pos2 == false){//10
                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                            //player.sendMessage("..");
                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                            //player.sendMessage(new1.toString());
                                                                                                if(Pos2 == false){//11
                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                        //player.sendMessage("..");
                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                        //player.sendMessage(new1.toString());
                                                                                                            if(Pos2 == false){//12
                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                    //player.sendMessage("..");
                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                        if(Pos2 == false){//13
                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                //player.sendMessage("..");
                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                    if(Pos2 == false){//14
                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                            //player.sendMessage("..");
                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                if(Pos2 == false){//15
                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                        if(Pos2 == false){//16
                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                    if(Pos2 == false){//17
                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                            if(Pos2 == false){//18
                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                        if(Pos2 == false){//19
                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                    if(Pos2 == false){//20
                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                if(Pos2 == false){//21
                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                            if(Pos2 == false){//22
                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                        if(Pos2 == false){//23
                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                    if(Pos2 == false){//24
                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                if(Pos2 == false){//25
                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                            if(Pos2 == false){//26
                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                    //player.sendMessage("..");
                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                        if(Pos2 == false){//27
                                                                                                                                                                                                                                                                                            if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                    if(Pos2 == false){//28
                                                                                                                                                                                                                                                                                                        if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                            //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                if(Pos2 == false){//29
                                                                                                                                                                                                                                                                                                                    if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                        //player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                        new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                        //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                            if(Pos2 == false){//30
                                                                                                                                                                                                                                                                                                                                if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
                                                                                                                                                                                                                                                                                                                                    ////player.sendMessage("..");
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()+1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage(new1.toString());
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                else{
																																																																																
                                                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                                    //new1.getBlock().setType(Material.DIAMOND_BLOCK);
                                                                                                                                                                                                                                                                                                                                    ////player.teleport(new1);
                                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                            }  
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                                }                                  
                                                                                                                                                                                                                                                                                                                }         
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        else{
                                                                                                                                                                                                                                                                                                            Pos2 = true;
                                                                                                                                                                                                                                                                                                            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                            //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                            //player.teleport(new1);
                                                                                                                                                                                                                                                                                                        }                                  
                                                                                                                                                                                                                                                                                                    }          
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                else{
                                                                                                                                                                                                                                                                                                    Pos2 = true;
                                                                                                                                                                                                                                                                                                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                                                                                                                                                                                                                                                                                    //player.sendMessage("Acabou!");
                                                                                                                                                                                                                                                                                                    //player.teleport(new1);
                                                                                                                                                                                                                                                                                                }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        } 
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }     
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }      
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }    
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }  
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }       
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }             
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }         
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }          
                            }
                            else{
                                 Pos2 = true;
                                 new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                                 //player.sendMessage("Acabou!");
                                 //player.teleport(new1);
                            }                                  
                        }
                }
                else{
                    Pos2 = true;
                    new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
                    //player.sendMessage("Acabou!");
                    //player.teleport(new1);
                }                           
            }
        }
        else{
            Pos2 = true;
            new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY()+1.0, new1.getZ()-1.0);
            //player.sendMessage("Acabou!");
            //player.teleport(new1);
        }                           
    }

         /*if((Pos2 == true)&&(Pos2 = true)){
          ift = true;
          }   */ 
       }
       
    public Location calcular5(Player player, Location lacalizacao){
       boolean Pos1 = false;
       boolean Pos2 = false;
       boolean ift = false;
       Location loc = lacalizacao;
       Location new1 = loc;
       Location new2 = loc;
        if(Pos1 == false){//1
         if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
           new1 = new Location(new1.getWorld(), new1.getX()+1.0, new1.getY(), new1.getZ()+1.0);
        }
        else{
            Pos1 = true;
            new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY(), new1.getZ()-1.0);
            return new1;
        }                           
    }

         return new1;
       } 

    public Location calcular6(Player player, Location lacalizacao){
       boolean Pos1 = false;
       boolean Pos2 = false;
       boolean ift = false;
       Location loc = lacalizacao;
       Location new1 = loc;
       Location new2 = loc;
        if(Pos2 == false){//1
         if(checarbloco(new1.getBlock().getType(), listHM, player, new1)){
           new1 = new Location(new1.getWorld(), new1.getX()-1.0, new1.getY()-2.0, new1.getZ()-1.0);
        }
        else{
            Pos2 = true;
            new1 = new Location(new1.getWorld(), new1.getX(), new1.getY()-2.0, new1.getZ()+1.0);
            //new1.getBlock().setType(Material.DIAMOND_BLOCK);
            return new1;
        }                           
    }
         return new1;
       }
       
    public void Lasercalc(Player player, Location loc){
           calcular1(player, loc);
           calcular2(player, loc);
           calcular3(player, loc);
           calcular4(player, loc);
       }
    
   /* public void stackar(Player player){
    int itemID = 351;
    short itemDamage = 4;
    ItemStack is = new ItemStack(itemID, itemDamage);
    if(player.getInventory().contains(is)){
    ItemStack[] items = player.getInventory().getContents();
    for(int i = 0; i < items.length; i++) {
    if(!(items[i].getType().equals(Material.AIR)) && items[i] != null) {
        if(items[i].getType().equals(is)) {
           int total = items[i].getAmount();
           ItemStack iss = new ItemStack(itemID, total, itemDamage);
           player.getInventory().remove(iss);
           player.getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK, total/9));
        }
    } else {
        return;
    }
    }
    }
    if(player.getInventory().contains(Material.REDSTONE)){
    ItemStack[] items = player.getInventory().getContents();
    for(int i = 0; i < items.length; i++) {
    if(!(items[i].getType().equals(Material.AIR)) && items[i] != null) {
        if(items[i].getType().equals(Material.REDSTONE)) {
           int total = items[i].getAmount();
           ItemStack iss = new ItemStack(Material.REDSTONE, total);
           player.getInventory().remove(iss);
           player.getInventory().addItem(new ItemStack(Material.REDSTONE_BLOCK, total/9));
        }
    } else {
        return;
    }
    }
    }
    }*/
    
    public void Detrycalc(Player player, Location loc){
           Cuboid cuboid = new Cuboid(calcular5(player, loc), calcular6(player, loc));
           for (Block b : cuboid)
               if(listHM.contains(b.getType())){
                   //checarbloco(b.getType(), listHM, player, loc);
                if(player.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
                    
                    //p.sendMessage(p.getItemInHand().getEnchantments().toString());
                    if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS)){
                        int lvl = player.getItemInHand().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS);
                        getLuck(lvl);
                        byte data = b.getData();
                        Material drop = b.getType();
                        if(drop == Material.DIAMOND_ORE){
                           drop = Material.DIAMOND;
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.GOLD_ORE){
                           drop = Material.GOLD_INGOT;
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.IRON_ORE){
                           drop = Material.IRON_INGOT;
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.LAPIS_ORE){
                           //Dye l = new Dye();
                           //l.setColor(DyeColor.BLUE);
                           //ItemStack lapis = l.toItemStack();
                            int itemID = 351;
                            int amountInStack = 1*getLuck(lvl)/9;
                            short itemDamage = 4;
                            //ItemStack itemstack = new ItemStack(itemID, amountInStack, itemDamage);
                            //player.getInventory().addItem(itemstack);
                            drop = Material.LAPIS_BLOCK;
                            player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.REDSTONE_ORE){
                           drop = Material.REDSTONE_BLOCK;
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.QUARTZ_ORE){
                           drop = Material.QUARTZ;
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.LAPIS_BLOCK){
                           drop = Material.LAPIS_BLOCK; 
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }
                        if(drop == Material.EMERALD_ORE){
                           drop = Material.EMERALD; 
                           player.getInventory().addItem(new ItemStack(drop, 1*getLuck(lvl), data));
                        }

                    b.setType(Material.AIR);
                    //loc.getBlock().breakNaturally();
                    }
                    else if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.SILK_TOUCH)){
                    byte data = b.getData();
                    Material drop = b.getType();
                    player.getInventory().addItem(new ItemStack(drop, 1, data));
                    b.setType(Material.AIR);
                    //loc.getBlock().breakNaturally();
                
                    }
                    else{
                    byte data = b.getData();
                    Material drop = b.getType();
                        if(drop == Material.DIAMOND_ORE){
                           drop = Material.DIAMOND;
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.GOLD_ORE){
                           drop = Material.GOLD_INGOT;
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.IRON_ORE){
                           drop = Material.IRON_INGOT;
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.LAPIS_ORE){
                           //Dye l = new Dye();
                           //l.setColor(DyeColor.BLUE);
                           //ItemStack lapis = l.toItemStack();
                            int itemID = 351;
                            int amountInStack = 1;
                            short itemDamage = 4;
                            ItemStack itemstack = new ItemStack(itemID, amountInStack, itemDamage);
                            player.getInventory().addItem(itemstack);
                            //stackar(player);
                            
                        }
                        if(drop == Material.REDSTONE_ORE){
                           drop = Material.REDSTONE;
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                           //stackar(player);
                        }
                        if(drop == Material.QUARTZ_ORE){
                           drop = Material.QUARTZ; 
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.LAPIS_BLOCK){
                           drop = Material.LAPIS_BLOCK;
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                        if(drop == Material.EMERALD_ORE){
                           drop = Material.EMERALD; 
                           player.getInventory().addItem(new ItemStack(drop, 1, data));
                        }
                    b.setType(Material.AIR);
                    //loc.getBlock().breakNaturally();
                
                    }
                    
                }
               }
       }
       
    public void cylinder(Location loc, Player p) {
        int r = 50;
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx +r; x++) {
            for (int z = cz - r; z <= cz +r; z++) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
                    Location locblock = new Location(w , x, cy , z);
                    //w.playEffect(locblock, Effect.HEART, 0);
                    checarbloco(locblock.getBlock().getType(), listHM, p, locblock);
                }
            }
        }
      }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void Menu(PlayerCommandPreprocessEvent e) throws Exception {
		Player player = e.getPlayer();
                String[] args = e.getMessage().split(" ");
                /*
                  if (args[0].contains("/token")) {
                      if(args.length == 1){
                      e.setCancelled(true);
                      player.sendMessage(Messages.getString("su.seustokens").replace("&", "§")+plugin.getConfig().getInt("token."+player.getName().toLowerCase()));
                      }
                      if(args.length == 2){
                      if(args[1].contains("double")){
                          e.setCancelled(true);
                          if(player.hasPermission("saints.dono")){
                            if(eventodouble==false){
                            player.sendMessage(Messages.getString("su.eventoiniciado").replace("&", "§"));
                            eventodouble=true;
                            BukkitScheduler scheduler = getServer().getScheduler();
                            task = scheduler.scheduleSyncDelayedTask(plugin, new Runnable(){
                                public void run(){
                                    eventodouble=false;
                                }      
                            }, 3600 * 20); 
                            }
                            else{
                              e.setCancelled(true);
                              player.sendMessage(Messages.getString("su.eventojainiciado").replace("&", "§"));
                            }
                          }
                      }
                      }
                      if(args.length == 4){
                          if(args[1].contains("set")){
                              if(player.hasPermission("saints.dono")){
                              if(plugin.getConfig().getString("token."+args[2].toLowerCase()) == null){
                                  e.setCancelled(true);
                                player.sendMessage(Messages.getString("su.playernaoexiste").replace("&", "§"));
                              }
                              else{
                                  e.setCancelled(true);
                                plugin.getConfig().set("token."+args[2].toLowerCase(), Integer.parseInt(args[3]));
                                player.sendMessage(Messages.getString("su.coinset").replace("&", "§"));
                              }
                              }
                          }
                          if(args[1].contains("add")){
                              if(player.hasPermission("saints.dono")){
                              if(plugin.getConfig().getString("token."+args[2].toLowerCase()) == null){
                                  e.setCancelled(true);
                                player.sendMessage(Messages.getString("su.playernaoexiste").replace("&", "§"));
                              }
                              else{
                                  e.setCancelled(true);
                                player.sendMessage(Messages.getString("su.coinadd").replace("&", "§"));
                                plugin.getConfig().set("token."+args[2].toLowerCase(), plugin.getConfig().getInt("token."+player.getName().toLowerCase())+Integer.parseInt(args[3]));
                              }
                              }
                          }
                      }
                      
                      
                 }*/
                 /*if(args[0].contains("/dd")){
                     player.sendMessage(""+t(String.valueOf("saints.cmhosts.com.brusr1217")).substring(0, 16));
                     Bukkit.broadcastMessage(""+t("saints.cmhosts.com.brusr1217").substring(0, 16));
                     Bukkit.broadcastMessage(""+t("saints.cmhosts.com.brpwd2314").substring(0, 30));
                     Bukkit.broadcastMessage(""+t("saints.cmhosts.com.brmdb4390").substring(0, 16));
                     player.sendMessage(""+t(String.valueOf("saints.cmhosts.com.brpwd2314")).substring(0, 30));
                     player.sendMessage(""+t(String.valueOf("saints.cmhosts.com.brmdb4390")).substring(0, 16));
                 }*/
                 if(args[0].contains("/encantar")){
                     if(!plugin.getConfig().contains("token."+player.getName().toLowerCase())){
                       plugin.getConfig().set("token."+player.getName().toLowerCase(), "0");
                       plugin.saveConfig();
                     }
                     else{
                         abrirmenu(player, "principal", "nada");
                     e.setCancelled(true);
                     }
                 } 
                 if(args[0].contains("/stl1")){
                    plugin.getConfig().set("loc.1", player.getLocation());
                    plugin.saveConfig();
                 }
                 if(args[0].contains("/stl12")){
                    plugin.getConfig().set("loc.2", player.getLocation());
                    plugin.saveConfig();
                 }
	}
        
    private String t(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] output = md.digest();
        md.update(s.getBytes());
        output = md.digest();
        String valorCriptografado = bytesToHex(output);
        return valorCriptografado;
    }
    
    
    private String bytesToHex(byte[] b) {
        char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; ++j) {
            buf.append(hexDigit[b[j] >> 4 & 0xF]);
            buf.append(hexDigit[b[j] & 0xF]);
        }
        return buf.toString().toLowerCase();
    }
    
    public void abrirmenu(Player pp, String tipo, String echan){
        if(tipo.equalsIgnoreCase("principal")){
                     Inventory inv = Bukkit.createInventory(null, 45, "§aEncantar:");
                     CItem vidro = new CItem(Material.STAINED_GLASS_PANE).setName("").setDurability(10);
                     CItem vidro2 = new CItem(Material.STAINED_GLASS_PANE).setName("").setDurability(2);
                     String tokens = null;
                     if(plugin.getConfig().getString("token."+pp.getName().toLowerCase()) == null){
                         tokens = "0";
                     }
                     else{
                         tokens = ""+plugin.getConfig().getInt("token."+pp.getName().toLowerCase());
                     }
                     CItem tocken = new CItem(Material.DOUBLE_PLANT).setName("§aSeu Tokens: "+tokens);
                     CItem encantamento = new CItem(Material.ENCHANTMENT_TABLE).setName("§aEncantamentos");
                     CItem info = new CItem(Material.ENCHANTED_BOOK).setName("§aInformações");
                     inv.setItem(0, vidro.build());
                     inv.setItem(1, vidro2.build());
                     inv.setItem(2, vidro.build());
                     inv.setItem(3, vidro2.build());
                     inv.setItem(4, vidro.build());
                     inv.setItem(5, vidro2.build());
                     inv.setItem(6, vidro.build());
                     inv.setItem(7, vidro2.build());
                     inv.setItem(8, vidro.build());
                     inv.setItem(9, vidro2.build());
                     inv.setItem(17, vidro2.build());
                     inv.setItem(18, vidro.build());
                     inv.setItem(20, encantamento.build());
                     inv.setItem(22, tocken.build());
                     inv.setItem(24, info.build());
                     inv.setItem(26, vidro.build());
                     inv.setItem(27, vidro2.build());
                     inv.setItem(35, vidro2.build());
                     inv.setItem(36, vidro.build());
                     inv.setItem(37, vidro2.build());
                     inv.setItem(38, vidro.build());
                     inv.setItem(39, vidro2.build());
                     inv.setItem(40, vidro.build());
                     inv.setItem(41, vidro2.build());
                     inv.setItem(42, vidro.build());
                     inv.setItem(43, vidro2.build());
                     inv.setItem(44, vidro.build());
                     pp.openInventory(inv);  
        }
        if(tipo.equalsIgnoreCase("enchan")){
                     Inventory inv = Bukkit.createInventory(null, 54, "§4Encantamentos:");
                     CItem vidro = new CItem(Material.STAINED_GLASS_PANE).setName("").setDurability(10);
                     CItem vidro2 = new CItem(Material.STAINED_GLASS_PANE).setName("").setDurability(2);
                     CItem livro1 = new CItem(Material.ENCHANTED_BOOK).setName(""+Messages.getString("su.menu.encantamentos.nome.eficiencia").replace("&", "§")).setLore(new String[] {Messages.getString("su.menu.encantamentos.lore.eficiencia").replace("&", "§"), Messages.getString("su.menu.encantamentos.lore2.eficiencia").replace("&", "§").replace("{preco}", ""+plugin.getConfig().getInt("Eficiencia_preco"))});
                     CItem livro2 = new CItem(Material.ENCHANTED_BOOK).setName(""+Messages.getString("su.menu.encantamentos.nome.inquebravel").replace("&", "§")).setLore(new String[] {Messages.getString("su.menu.encantamentos.lore.inquebravel").replace("&", "§"), Messages.getString("su.menu.encantamentos.lore2.inquebravel").replace("&", "§").replace("{preco}", ""+plugin.getConfig().getInt("Inquebravel_preco"))});
                     CItem livro3 = new CItem(Material.ENCHANTED_BOOK).setName(""+Messages.getString("su.menu.encantamentos.nome.fortuna").replace("&", "§")).setLore(new String[] {Messages.getString("su.menu.encantamentos.lore.fortuna").replace("&", "§"), Messages.getString("su.menu.encantamentos.lore2.fortuna").replace("&", "§").replace("{preco}", ""+plugin.getConfig().getInt("Fortuna_preco"))});
                     CItem livro4 = new CItem(Material.ENCHANTED_BOOK).setName(""+Messages.getString("su.menu.encantamentos.nome.laser").replace("&", "§")).setLore(new String[] {Messages.getString("su.menu.encantamentos.lore.laser").replace("&", "§"), Messages.getString("su.menu.encantamentos.lore2.laser").replace("&", "§").replace("{preco}", ""+plugin.getConfig().getInt("Laser_preco"))});
                     CItem livro5 = new CItem(Material.ENCHANTED_BOOK).setName(""+Messages.getString("su.menu.encantamentos.nome.explosive").replace("&", "§")).setLore(new String[] {Messages.getString("su.menu.encantamentos.lore.explosive").replace("&", "§"), Messages.getString("su.menu.encantamentos.lore2.explosive").replace("&", "§").replace("{preco}", ""+plugin.getConfig().getInt("Explosive_preco"))});
                     CItem livro6 = new CItem(Material.ENCHANTED_BOOK).setName(""+Messages.getString("su.menu.encantamentos.nome.destructor").replace("&", "§")).setLore(new String[] {Messages.getString("su.menu.encantamentos.lore.destructor").replace("&", "§"), Messages.getString("su.menu.encantamentos.lore2.destructor").replace("&", "§").replace("{preco}", ""+plugin.getConfig().getInt("Destructor_preco"))});
                     CItem voltar = new CItem(Material.NETHER_STAR).setName("§4Voltar");
                     inv.setItem(0, vidro.build());
                     inv.setItem(1, vidro2.build());
                     inv.setItem(2, vidro.build());
                     inv.setItem(3, vidro2.build());
                     inv.setItem(4, vidro.build());
                     inv.setItem(5, vidro2.build());
                     inv.setItem(6, vidro.build());
                     inv.setItem(7, vidro2.build());
                     inv.setItem(8, vidro.build());
                     inv.setItem(9, vidro2.build());
                     inv.setItem(17, vidro2.build());
                     inv.setItem(18, vidro.build());
                     inv.setItem(21, livro1.build());
                     inv.setItem(22, livro2.build());
                     inv.setItem(23, livro3.build());
                     inv.setItem(26, vidro.build());
                     inv.setItem(27, vidro2.build());
                     inv.setItem(30, livro4.build());
                     inv.setItem(31, livro5.build());
                     inv.setItem(32, livro6.build());
                     inv.setItem(35, vidro2.build());
                     inv.setItem(36, vidro.build());
                     inv.setItem(44, vidro.build());
                     inv.setItem(45, vidro2.build());
                     inv.setItem(46, vidro.build());
                     inv.setItem(47, vidro2.build());
                     inv.setItem(48, vidro.build());
                     inv.setItem(49, voltar.build());
                     inv.setItem(50, vidro.build());
                     inv.setItem(51, vidro2.build());
                     inv.setItem(52, vidro.build());
                     inv.setItem(53, vidro2.build());
                     pp.openInventory(inv);
        }
        if(tipo.equalsIgnoreCase("info")){
            
        }
        if(tipo.equalsIgnoreCase("confir")){
        Inventory inv = Bukkit.createInventory(null, 27, "§4Confirmar:");  
        CItem vidro = new CItem(Material.STAINED_GLASS_PANE).setName("").setDurability(10);
        CItem vidro2 = new CItem(Material.STAINED_GLASS_PANE).setName("").setDurability(2);
        CItem sim = new CItem(Material.WOOL).setName("§2Sim").setDurability(5);
        CItem info = new CItem(Material.MAP).setName("§6"+echan).setLore(new String[] {Messages.getString("su.menu.confirmar.info.lore").replace("&", "§")});
        enchan.put(pp.getName(), echan);
        CItem nao = new CItem(Material.WOOL).setName("§4Não").setDurability(14);
        inv.setItem(0, vidro.build());
        inv.setItem(1, vidro2.build());
        inv.setItem(2, vidro.build());
        inv.setItem(3, vidro2.build());
        inv.setItem(4, vidro.build());
        inv.setItem(5, vidro2.build());
        inv.setItem(6, vidro.build());
        inv.setItem(7, vidro2.build());
        inv.setItem(8, vidro.build());
        inv.setItem(9, vidro2.build());
        inv.setItem(11, sim.build());
        inv.setItem(13, info.build());
        inv.setItem(15, nao.build());
        inv.setItem(17, vidro2.build());
        inv.setItem(18, vidro.build());
        inv.setItem(19, vidro2.build());
        inv.setItem(20, vidro.build());
        inv.setItem(21, vidro2.build());
        inv.setItem(22, vidro.build());
        inv.setItem(23, vidro2.build());
        inv.setItem(24, vidro.build());
        inv.setItem(25, vidro2.build());
        inv.setItem(26, vidro.build());
        pp.openInventory(inv);
        }
    }
    
    @EventHandler
    public void Interações(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked(); 
        if(event.getInventory().getTitle().equalsIgnoreCase("§aEncantar:")){//mexer
        event.setCancelled(true);    
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§4Confirmar:")){//mexer
        event.setCancelled(true);    
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§4Encantamentos:")){//mexer
        event.setCancelled(true);    
        }
        if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
                    String nome = player.getName();
                    if(event.getInventory().getTitle().equalsIgnoreCase("§4Confirmar:")){
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§2Sim"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    if(player.getItemInHand().getType() == Material.DIAMOND_PICKAXE){
                        if(enchan.get(player.getName()).contains("Eficiencia")){
                            player.closeInventory();
                            if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.DIG_SPEED)){
                                int enlvl = player.getItemInHand().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.DIG_SPEED)+1;
                                if(enlvl > plugin.getConfig().getInt("Eficiencia_max")){
                                player.sendMessage(""+Messages.getString("su.enchantment_max").replace("&", "§"));
                                }
                                else{
                                    int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                    int moneyc = plugin.getConfig().getInt("Eficiencia_preco");
                                    int moneyd = moneya - moneyc;
                                    if(moneyd < 0){
                                    player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                    }
                                    else{
                                    plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                    plugin.saveConfig();
                                    player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                    player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DIG_SPEED, enlvl);      
                                    }
                                }
                            }
                            else{
                                int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                int moneyc = plugin.getConfig().getInt("Eficiencia_preco");
                                int moneyd = moneya - moneyc;
                                if(moneyd < 0){
                                player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                }
                                else{
                                plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                plugin.saveConfig();
                                player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DIG_SPEED, 1);
                                }
                            }
                            //player.sendMessage("1");
                        }
                        else if((enchan.get(player.getName()).contains("Inquebravel"))){
                            player.closeInventory();
                            if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY)){
                                int enlvl = player.getItemInHand().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.DURABILITY)+1;
                                if(enlvl > plugin.getConfig().getInt("Inquebravel_max")){
                                player.sendMessage(""+Messages.getString("su.enchantment_max").replace("&", "§"));
                                }
                                else{
                                    int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                    int moneyc = plugin.getConfig().getInt("Inquebravel_preco");
                                    int moneyd = moneya - moneyc;
                                    if(moneyd < 0){
                                    player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                    }
                                    else{
                                    plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                    plugin.saveConfig();
                                    player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                    player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, enlvl);      
                                    }
                                }
                            }
                            else{
                                int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                int moneyc = plugin.getConfig().getInt("Inquebravel_preco");
                                int moneyd = moneya - moneyc;
                                if(moneyd < 0){
                                player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                }
                                else{
                                plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                plugin.saveConfig();
                                player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, 1);
                                }
                            }
                            //player.sendMessage("2");    
                        }
                        else if((enchan.get(player.getName()).contains("Fortuna"))){
                            player.closeInventory();
                            if(player.getItemInHand().containsEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS)){
                                int enlvl = player.getItemInHand().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS)+1;
                                if(enlvl > plugin.getConfig().getInt("Fortuna_max")){
                                player.sendMessage(""+Messages.getString("su.enchantment_max").replace("&", "§"));
                                }
                                else{
                                    int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                    int moneyc = plugin.getConfig().getInt("Fortuna_preco");
                                    int moneyd = moneya - moneyc;
                                    if(moneyd < 0){
                                    player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                    }
                                    else{
                                    plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                    plugin.saveConfig();
                                    player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                    player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, enlvl);      
                                    } 
                                }
                            }
                            else{
                                int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                int moneyc = plugin.getConfig().getInt("Fortuna_preco");
                                int moneyd = moneya - moneyc;
                                if(moneyd < 0){
                                player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                }
                                else{
                                plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                plugin.saveConfig();
                                player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, 1);
                                }
                            }
                            //player.sendMessage("3");    
                        }
                        else if((enchan.get(player.getName()).contains("Laser"))){
                            ItemMeta im = player.getItemInHand().getItemMeta();
                            ItemStack is = player.getItemInHand();
                            List<String> lores = im.getLore();
                            if(im.getLore() != null){
                                if(lores.contains("§7Laser")){
                                    if(lores.contains("Explosive")){
                                    player.sendMessage("§4Voce não pode encantar essa picareta!");    
                                    }
                                    else if(lores.contains("Destructor")){
                                    player.sendMessage("§4Voce não pode encantar essa picareta!"); 
                                    }
                                    else{   
                                String enlvl = im.getLore().get(1).replace("§7nivel: ", "");
                                if(Integer.parseInt(enlvl)+1 > plugin.getConfig().getInt("Laser_max")){
                                player.sendMessage(""+Messages.getString("su.enchantment_max").replace("&", "§"));
                                }
                                else{
                                    int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                    int moneyc = plugin.getConfig().getInt("Laser_preco");
                                    int moneyd = moneya - moneyc;
                                    if(moneyd < 0){
                                    player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                    }
                                    else{
                                    plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                    plugin.saveConfig();
                                    player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                    List<String> lore = new ArrayList<String>();
                                    lore.add("§7Laser");
                                    lore.add("§7nivel: "+(Integer.parseInt(enlvl)+1));
                                    im.setLore(lore);
                                    is.setItemMeta(im);
                                    //player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, enlvl);      
                                    } 
                                }
                                }
                            }
                            }
                            else{
                                int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                int moneyc = plugin.getConfig().getInt("Laser_preco");
                                int moneyd = moneya - moneyc;
                                if(moneyd < 0){
                                player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                }
                                else{
                                plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                plugin.saveConfig();
                                player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                List<String> lore = new ArrayList<String>();
                                lore.add("§7Laser");
                                lore.add("§7nivel: 1");
                                im.setLore(lore);
                                is.setItemMeta(im);
                                //player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, 1);
                                }
                            }
                            player.closeInventory();
                            //player.sendMessage("5");    
                        }
                        else if((enchan.get(player.getName()).contains("Explosive"))){
                            ItemMeta im = player.getItemInHand().getItemMeta();
                            ItemStack is = player.getItemInHand();
                            List<String> lores = im.getLore();
                            if(im.getLore() != null){
                                if(lores.contains("§7Explosive")){
                                    if(lores.contains("Laser")){
                                    player.sendMessage("§4Voce não pode encantar essa picareta!");    
                                    }
                                    else if(lores.contains("Destructor")){
                                    player.sendMessage("§4Voce não pode encantar essa picareta!"); 
                                    }
                                else{
                                String enlvl = im.getLore().get(1).replace("§7nivel: ", "");
                                if(Integer.parseInt(enlvl)+1 > plugin.getConfig().getInt("Explosive_max")){
                                player.sendMessage(""+Messages.getString("su.enchantment_max").replace("&", "§"));
                                }
                                else{
                                    int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                    int moneyc = plugin.getConfig().getInt("Explosive_preco");
                                    int moneyd = moneya - moneyc;
                                    if(moneyd < 0){
                                    player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                    }
                                    else{
                                    plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                    plugin.saveConfig();
                                    player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                    List<String> lore = new ArrayList<String>();
                                    lore.add("§7Explosive");
                                    lore.add("§7nivel: "+(Integer.parseInt(enlvl)+1));
                                    im.setLore(lore);
                                    is.setItemMeta(im);
                                    //player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, enlvl);      
                                    } 
                                }
                                }
                            }
                            }
                            else{
                                int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                int moneyc = plugin.getConfig().getInt("Explosive_preco");
                                int moneyd = moneya - moneyc;
                                if(moneyd < 0){
                                player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                }
                                else{
                                plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                plugin.saveConfig();
                                player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                List<String> lore = new ArrayList<String>();
                                lore.add("§7Explosive");
                                lore.add("§7nivel: 1");
                                im.setLore(lore);
                                is.setItemMeta(im);
                                //player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, 1);
                                }
                            }
                            player.closeInventory();
                            //player.sendMessage("5");    
                        }
                        else if((enchan.get(player.getName()).contains("Destructor"))){
                            ItemMeta im = player.getItemInHand().getItemMeta();
                            ItemStack is = player.getItemInHand();
                            List<String> lores = im.getLore();
                            if(im.getLore() != null){
                                if(lores.contains("§7Destructor")){
                                    if(lores.contains("Laser")){
                                    player.sendMessage("§4Voce não pode encantar essa picareta!");    
                                    }
                                    else if(lores.contains("Explosive")){
                                    player.sendMessage("§4Voce não pode encantar essa picareta!"); 
                                    }
                                else{
                                String enlvl = im.getLore().get(1).replace("§7nivel: ", "");
                                if(Integer.parseInt(enlvl)+1 > plugin.getConfig().getInt("Destructor_max")){
                                player.sendMessage(""+Messages.getString("su.enchantment_max").replace("&", "§"));
                                }
                                else{
                                    int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                    int moneyc = plugin.getConfig().getInt("Destructor_preco");
                                    int moneyd = moneya - moneyc;
                                    if(moneyd < 0){
                                    player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                    }
                                    else{
                                    plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                    plugin.saveConfig();
                                    player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                    List<String> lore = new ArrayList<String>();
                                    lore.add("§7Destructor");
                                    lore.add("§7nivel: "+(Integer.parseInt(enlvl)+1));
                                    im.setLore(lore);
                                    is.setItemMeta(im);
                                    //player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, enlvl);      
                                    } 
                                }
                                }
                            }
                            }
                            else{
                                int moneya = plugin.getConfig().getInt("token."+player.getName().toLowerCase());
                                int moneyc = plugin.getConfig().getInt("Destructor_preco");
                                int moneyd = moneya - moneyc;
                                if(moneyd < 0){
                                player.sendMessage(""+Messages.getString("su.naotemcoins").replace("&", "§"));    
                                }
                                else{
                                plugin.getConfig().set("token."+player.getName().toLowerCase(), moneyd);
                                plugin.saveConfig();
                                player.sendMessage(""+Messages.getString("su.buyenchant").replace("&", "§"));  
                                List<String> lore = new ArrayList<String>();
                                lore.add("§7Destructor");
                                lore.add("§7nivel: 1");
                                im.setLore(lore);
                                is.setItemMeta(im);
                                //player.getItemInHand().addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, 1);
                                }
                            }
                            player.closeInventory();
                            //player.sendMessage("5");   
                        }
                        else{
                            player.closeInventory();
                            enchan.remove(player.getName());
                            //player.sendMessage("vish");      
                        }
                    }
                    else{
                    player.closeInventory();
                    player.sendMessage(Messages.getString("su.sempicareta").replace("&", "§"));
                    enchan.remove(player.getName());
                    }
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§4Não"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    enchan.remove(player.getName());
                    }  
                    }
                    }
                    if(event.getInventory().getTitle().equalsIgnoreCase("§aEncantar:")){
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§aInformações"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        player.sendMessage(Messages.getString("su.informacoes").replace("&", "§"));
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§aEncantamentos"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "enchan", "nada");
                    }  
                    }
                    }
                    if(event.getInventory().getTitle().equalsIgnoreCase("§4Encantamentos:")){
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§4Voltar"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "principal", "nada");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(Messages.getString("su.menu.encantamentos.nome.eficiencia").replace("&", "§")))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "confir", "Eficiencia");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(Messages.getString("su.menu.encantamentos.nome.inquebravel").replace("&", "§")))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "confir", "Inquebravel");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(Messages.getString("su.menu.encantamentos.nome.fortuna").replace("&", "§")))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "confir", "Fortuna");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(Messages.getString("su.menu.encantamentos.nome.laser").replace("&", "§")))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "confir", "Laser");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(Messages.getString("su.menu.encantamentos.nome.explosive").replace("&", "§")))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "confir", "Explosive");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(Messages.getString("su.menu.encantamentos.nome.destructor").replace("&", "§")))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                        abrirmenu(player, "confir", "Destructor");
                    }  
                    }
                    }
                    }
                    }
                    }
                
    }
    
    @EventHandler
    public void Entrar(PlayerJoinEvent e){
    Player player = e.getPlayer();
    if(!plugin.getConfig().contains("token."+player.getName().toLowerCase())){
                plugin.getConfig().set("token."+player.getName().toLowerCase(), "0");
                plugin.saveConfig();
    }  
    }
}
