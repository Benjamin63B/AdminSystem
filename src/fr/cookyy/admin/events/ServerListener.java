package fr.cookyy.admin.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import fr.cookyy.admin.AdminSystemMain;

public class ServerListener implements Listener {
  @EventHandler
  public void onServerListPing(ServerListPingEvent event) {
    String motd = ChatColor.translateAlternateColorCodes('&',
        String.valueOf(AdminSystemMain.configFile.getConfig().getString("MOTD.1")) + "\n" + AdminSystemMain.configFile.getConfig().getString("MOTD.2"));
    event.setMotd(motd);
  }
}
