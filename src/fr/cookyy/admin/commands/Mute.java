package fr.cookyy.admin.commands;

import fr.cookyy.admin.AdminSystemMain;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Mute implements CommandExecutor, Listener {
  public static ArrayList<String> muted = new ArrayList<>();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player p = (Player)sender;
    if (label.equalsIgnoreCase("adminmute"))
      if (p.hasPermission("adminfr.adminmute")) {
        if (args.length != 1) {
          p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Mute")) + "§cUse: §6/mute <Player>");
        } else {
          Player target = Bukkit.getPlayer(args[0]);
          if (target != null) {
            if (muted.contains(target.getName())) {
              muted.remove(target.getName());
              p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Mute")) + AdminSystemMain.messageFile.getString("Message.MutePlayerOff") + target.getName());
              target.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Mute")) + AdminSystemMain.messageFile.getString("Message.PlayerMuteOff") + p.getName());
            } else {
              muted.add(p.getName());
              p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Mute")) + AdminSystemMain.messageFile.getString("Message.MutePlayerOn") + target.getName());
              target.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Mute")) + AdminSystemMain.messageFile.getString("Message.PlayerMuteOn") + p.getName());
            } 
          } else {
            p.sendMessage("§cPlayer is offline !");
          } 
        } 
      } else {
        p.sendMessage(AdminSystemMain.messageFile.getString("Message.Permission"));
      }  
    return false;
  }
  
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    if (muted.contains(p.getName())) {
      e.setCancelled(true);
      p.sendMessage(String.valueOf(AdminSystemMain.prefixmute) + "§cYou are already muted!");
    } else {
      e.setCancelled(false);
    } 
  }
}
