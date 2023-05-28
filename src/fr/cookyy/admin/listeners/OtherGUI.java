package fr.cookyy.admin.listeners;

import fr.cookyy.admin.AdminSystemMain;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OtherGUI implements Listener {
  public static void openOtherGUI(Player player) {
	  
	ChatColor.translateAlternateColorCodes('&', "§");
	  
    Inventory others = Bukkit.createInventory(null, 9, AdminSystemMain.configFile.getString("Item.GUI.Others"));
    
    ItemStack cleari = new ItemStack(Material.PAPER);
    ItemMeta clearim = cleari.getItemMeta();
    ArrayList<String> lore = new ArrayList<>();
    lore.add("§6To clear a player's inventory");
    lore.add("§cYou can use:");
    lore.add("§a/adminclear <player>");
    clearim.setLore(lore);
    clearim.setDisplayName(AdminSystemMain.configFile.getString("Item.Others.Item.ClearInventory"));
    cleari.setItemMeta(clearim);
    
    ItemStack work = new ItemStack(Material.WORKBENCH); 
    ItemMeta workm = work.getItemMeta();
    workm.setDisplayName(AdminSystemMain.configFile.getString("Item.Others.Item.WorkBench"));
    work.setItemMeta(workm);
    
    ItemStack ender = new ItemStack(Material.ENDER_CHEST);
    ItemMeta enderm = ender.getItemMeta();
    enderm.setDisplayName(AdminSystemMain.configFile.getString("Item.Others.Item.Enderchest"));
    ender.setItemMeta(enderm);
    
    ItemStack anvil = new ItemStack(Material.ANVIL);
    ItemMeta anvilm = anvil.getItemMeta();
    anvilm.setDisplayName(AdminSystemMain.configFile.getString("Item.Others.Item.Anvil"));
    anvil.setItemMeta(anvilm);
    
    ItemStack clearc = new ItemStack(Material.SNOW_BLOCK);
    ItemMeta clearcm = clearc.getItemMeta();
    clearcm.setDisplayName(AdminSystemMain.configFile.getString("Item.Others.Item.ClearChat"));
    clearc.setItemMeta(clearcm);
    
    ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    ItemMeta nopmeta = nop.getItemMeta();
    nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
    nop.setItemMeta(nopmeta);
    
    
    others.setItem(1, cleari);
    others.setItem(2, work);
    others.setItem(4, anvil);
    others.setItem(6, ender);
    others.setItem(7, clearc);
    for (int i = 0; i < (others.getContents()).length; i++) {
      ItemStack is = others.getItem(i);
      if (is == null || is.getType() == Material.AIR)
        others.setItem(i, nop); 
    } 
    player.openInventory(others);
  }
  
  @EventHandler
  public void click(InventoryClickEvent event) {
    int i;
    if (!event.getView().getTitle().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.GUI.Others")))
      return; 
    Player p = (Player)event.getWhoClicked();
    event.setCancelled(true);
    if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
      p.closeInventory();
      return;
    } 
    switch (event.getCurrentItem().getType()) {
    
      case SNOW_BLOCK:
        for (i = 0; i < 50; i++)
        Bukkit.broadcastMessage(""); 
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "§")+ "§eChat was deleted by §c" + p.getName());
        break;
        
      case WORKBENCH:
    	p.openWorkbench(null, true);
    	break;
    	
      case ENDER_CHEST:
    	p.openInventory(p.getEnderChest());  
    	break;  
    	
      case ANVIL:
    	p.openInventory(Bukkit.createInventory(p, InventoryType.ANVIL));
    	break;
    	
      case PAPER:
        p.performCommand("adminclear");
        break;
        
      case STAINED_GLASS_PANE:
        p.closeInventory();
        break;
    } 
  }
}
