package fr.cookyy.admin.commands;

import fr.cookyy.admin.AdminSystemMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("adminclear")) {
      if (!(sender instanceof Player)) {
        sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Commands"));
        return false;
      } 
      Player p = (Player)sender;
      if (!p.hasPermission("adminfr.clear")) {
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + AdminSystemMain.messageFile.getString("Message.Permission"));
        return true;
      } 
      if (args.length == 0) {
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§aYour inventory has been cleaned !");
        p.getInventory().clear();
        return true;
      } 
      if (args.length == 1) {
        Player t = Bukkit.getPlayer(args[0]);
        if (t == null) {
          p.sendMessage("§cPlayer is offline");
          return true;
        } 
        p.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§cYour inventory was clear by " + "§6 "+ t.getName());
        t.getInventory().clear();
        t.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§7Player inventory " + p.getName() + "§7is clear");
        return true;
      } 
      p.sendMessage("§cUse: /clear <player>");
      return false;
    } 
    return false;
  }
}
