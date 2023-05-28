package fr.cookyy.admin.listeners;

import fr.cookyy.admin.AdminSystemMain;
import fr.cookyy.admin.commands.Admin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WorldGUI implements Listener {
  public static void openWorldGUI(Player player) {
    Inventory world = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Item.GUI.World"));
    
    ItemStack kick = new ItemStack(Material.BLAZE_ROD);
    ItemMeta kickmeta = kick.getItemMeta();
    kickmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.KickAll"));
    kick.setItemMeta(kickmeta);
    
    ItemStack kill = new ItemStack(Material.BONE);
    ItemMeta killmeta = kill.getItemMeta();
    killmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.KillAll"));
    kill.setItemMeta(killmeta);
   
    ItemStack day = new ItemStack(Material.DOUBLE_PLANT);
    ItemMeta daymeta = day.getItemMeta();
    daymeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Day"));
    day.setItemMeta(daymeta);
    
    ItemStack night = new ItemStack(Material.SEEDS);
    ItemMeta nightmeta = night.getItemMeta();
    nightmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Night"));
    night.setItemMeta(nightmeta);
    
    ItemStack thunder = new ItemStack(Material.LAVA_BUCKET);
    ItemMeta thundermeta = thunder.getItemMeta();
    thundermeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Thunder"));
    thunder.setItemMeta(thundermeta);
    
    ItemStack rain = new ItemStack(Material.WATER_BUCKET);
    ItemMeta rainmeta = rain.getItemMeta();
    rainmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Rain"));
    rain.setItemMeta(rainmeta);
    
    ItemStack clear = new ItemStack(Material.BUCKET);
    ItemMeta clearmeta = clear.getItemMeta();
    clearmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Clear"));
    clear.setItemMeta(clearmeta);
    
    ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    ItemMeta nopmeta = nop.getItemMeta();
    nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
    nop.setItemMeta(nopmeta);
    
    ItemStack main = new ItemStack(Material.NETHER_STAR);
    ItemMeta mainmeta = main.getItemMeta();
    mainmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.NetherStar.Name"));
    main.setItemMeta(mainmeta);
    
    world.setItem(10, kick);
    world.setItem(14, kill);
    world.setItem(12, day);
    world.setItem(16, night);
    world.setItem(3, thunder);
    world.setItem(4, clear);
    world.setItem(5, rain);
    
    world.setItem(22, main);
    for (int i = 0; i < (world.getContents()).length; i++) {
      ItemStack is = world.getItem(i);
      if (is == null || is.getType() == Material.AIR)
        world.setItem(i, nop); 
    } 
    player.openInventory(world);
  }
  
  @EventHandler
  public void click(InventoryClickEvent event) {
    if (!event.getView().getTitle().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.GUI.World")))
      return; 
    event.setCancelled(true);
    Player p = (Player)event.getWhoClicked();
    if (event.getClickedInventory().getType() == null) {
      p.closeInventory();
      return;
    } 
    switch (event.getCurrentItem().getType()) {
      case BLAZE_ROD:
        for (Player playerp : Bukkit.getServer().getOnlinePlayers())
          playerp.kickPlayer("§cYou were kicked by admin"); 
        break;
      case BONE:
        for (Player health : Bukkit.getServer().getOnlinePlayers())
          health.setHealth(0.0D); 
        break;
      case DOUBLE_PLANT:
        p.getWorld().setTime(1000L);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§eIt\'s the day");
        p.closeInventory();
        break;
      case SEEDS:
        p.getWorld().setTime(14000L);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§eIt\'s night");
        p.closeInventory();
        break;
      case LAVA_BUCKET: 
    	p.getWorld().setStorm(true);
    	p.getWorld().setThundering(true);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§eThunder");
    	p.closeInventory();
    	break;
      case WATER_BUCKET: 
    	p.getWorld().setStorm(true);
    	p.getWorld().setThundering(false);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§eRain");
    	p.closeInventory();
    	break;
      case BUCKET: 
    	p.getWorld().setStorm(false);
    	p.getWorld().setThundering(false);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§eClear");
    	p.closeInventory();
        break;
      case NETHER_STAR:
        Admin.openAdminGUI(p);
        break;
      case STAINED_GLASS_PANE:
        p.closeInventory();
        break;
    } 
  }
}
