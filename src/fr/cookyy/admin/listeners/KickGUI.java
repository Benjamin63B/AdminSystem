package fr.cookyy.admin.listeners;

import fr.cookyy.admin.AdminSystemMain;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class KickGUI implements Listener {
  public static ArrayList<String> kick = new ArrayList<>();
  
  public static void openOnlineKickGUI(Player player) {
    Inventory kick = Bukkit.createInventory(null, 54, AdminSystemMain.configFile.getString("Item.Players.Item.Kicked"));
    int slot = 0;
    for (Player t : Bukkit.getServer().getOnlinePlayers()) {
      ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
      SkullMeta meta = (SkullMeta)skull.getItemMeta();
      meta.setOwner(t.getName());
      skull.setItemMeta((ItemMeta)meta);
      meta.setDisplayName(t.getName());
      skull.setItemMeta((ItemMeta)meta);
      kick.setItem(slot, skull);
      slot = 1;
    } 
    player.openInventory(kick);
  }
  
  @EventHandler
  public void onClickKickOnline(InventoryClickEvent event) {
    if (!event.getView().getTitle().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Players.Item.Kicked")))
      return; 
    Player p = (Player)event.getWhoClicked();
    event.setCancelled(true);
    if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
      p.closeInventory();
      return;
    } 
  }
  
  @EventHandler
  public void clickplkick(InventoryClickEvent event) {
    Iterator<Player> localIterator;
    if (!event.getView().getTitle().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Players.Item.Kicked")))
      return; 
    Player p = (Player)event.getWhoClicked();
    event.setCancelled(true);
    if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
      p.closeInventory();
      return;
    } 
    switch (event.getCurrentItem().getType()) {
      case SKULL_ITEM:
        kick.add(event.getCurrentItem().getItemMeta().getDisplayName());
        Bukkit.broadcastMessage(event.getCurrentItem().getItemMeta().getDisplayName());
        localIterator = (Iterator<Player>) Bukkit.getServer().getOnlinePlayers().iterator();
        do {
          Player t = localIterator.next();
          if (!kick.contains(t.getName()))
            continue; 
          t.kickPlayer("§cYou were kicked by §b" + p.getName());
          kick.remove(t.getName());
        } while (localIterator.hasNext());
        break;
    } 
  }
}
