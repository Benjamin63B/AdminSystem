package fr.cookyy.admin.utils;

import fr.cookyy.admin.AdminSystemMain;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
  
	  public static String prefixbroadcast = ChatColor.translateAlternateColorCodes('&', "§");

	
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    if (e.getPlayer().hasPermission("adminfr.staff") && 
      e.getMessage().startsWith("@")) {
      e.setCancelled(true);
      for (Player pls : Bukkit.getServer().getOnlinePlayers()) {
        if (pls.hasPermission("adminfr.staff"))
          pls.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Staff")) + e.getPlayer().getName() + "§f : " + e.getMessage().replaceFirst("@", "§l")); 
      } 
    } 
  }
  
  @EventHandler
  public void onChatBroadcast(AsyncPlayerChatEvent e) {
    if (e.getPlayer().hasPermission("adminfr.broadcast") && 
      e.getMessage().startsWith("!br")) {
      e.setCancelled(true);
      for (Player pls : Bukkit.getServer().getOnlinePlayers()) {
        if (pls.hasPermission("adminfr.broadcast"))
          pls.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.BroadCast")) + "§f>" + e.getMessage().replaceFirst("!br", "§5")); 
      } 
    } 
  }
}
