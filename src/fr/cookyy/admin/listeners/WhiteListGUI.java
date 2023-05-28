package fr.cookyy.admin.listeners;

import fr.cookyy.admin.AdminSystemMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class WhiteListGUI implements Listener {
  public static void openWhitelistGUI(Player player) {
    Inventory inv3 = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Item.Server.Item.WhiteList"));
    
    ItemStack off = new ItemStack(Material.REDSTONE_BLOCK);
    ItemMeta offmeta = off.getItemMeta();
    offmeta.setDisplayName("§cDisable");
    off.setItemMeta(offmeta);
    
    ItemStack on = new ItemStack(Material.EMERALD_BLOCK);
    ItemMeta onmeta = on.getItemMeta();
    onmeta.setDisplayName("§aEnable");
    on.setItemMeta(onmeta);
    
    ItemStack gl = new ItemStack(Material.STAINED_GLASS_PANE);
    ItemMeta glmeta = gl.getItemMeta();
    glmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
    gl.setItemMeta(glmeta);
    
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
    SkullMeta meta = (SkullMeta)skull.getItemMeta();
    meta.setOwner(player.getName());
    meta.setDisplayName(AdminSystemMain.configFile.getString("Item.Server.Item.WhiteList"));
    skull.setItemMeta(meta);
    
    ItemStack bac = new ItemStack(Material.NETHER_STAR);
    ItemMeta bacmeta = bac.getItemMeta();
    bacmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.NetherStar.Name"));
    bac.setItemMeta(bacmeta);
    
    inv3.setItem(22, bac);
    inv3.setItem(12, bac);
    inv3.setItem(14, bac);
    inv3.setItem(4, bac);
    inv3.setItem(15, off);
    inv3.setItem(11, on);
    inv3.setItem(13, skull);
    for (int i = 0; i < (inv3.getContents()).length; i++) {
      ItemStack is = inv3.getItem(i);
      if (is == null || is.getType() == Material.AIR)
        inv3.setItem(i, gl); 
    } 
    player.openInventory(inv3);
  }
  
  @EventHandler
  public void clickWhitelist(InventoryClickEvent event) {
    if (!event.getView().getTitle().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Server.Item.WhiteList")))
      return; 
    Player p = (Player)event.getWhoClicked();
    event.setCancelled(true);
    if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
      p.closeInventory();
      return;
    } 
    switch (event.getCurrentItem().getType()) {
      case SKULL_ITEM:
        PlayerWhiteList.openWhitePlayersGUI(p);
        break;
      case EMERALD_BLOCK:
        Bukkit.getServer().setWhitelist(false);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§fThe whitelist: §cEnable" );
        p.closeInventory();
        openWhitelistGUI(p);
        break;
      case REDSTONE_BLOCK:
        Bukkit.getServer().setWhitelist(true);
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§fThis whitelist: §aDisable" );
        p.closeInventory();
        openWhitelistGUI(p);
        break;
      case NETHER_STAR:
        ServerGUI.openServerGUI(p);
        break;
    } 
  }
}
