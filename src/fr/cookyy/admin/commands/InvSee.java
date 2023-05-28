package fr.cookyy.admin.commands;

import fr.cookyy.admin.AdminSystemMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvSee implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (label.equalsIgnoreCase("admininvsee"))
      if (sender.hasPermission("adminfr.admininvsee")) {
        if (args.length == 1) {
          if (sender instanceof Player) {
            String name = args[0];
            if (Bukkit.getPlayer(name) == null) {
              sender.sendMessage("§cPlayer is offline !");
              return false;
            } 
            Player target = Bukkit.getPlayer(name);
            Player player = (Player)sender;
            if (player == target) {
              player.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§cYou can not look at your own inventory!");
              return true;
            } 
            player.openInventory((Inventory)target.getInventory());
            return true;
          } 
          sender.sendMessage("§cYou must be a player !");
        } else {
          sender.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Inventory")) + "§c/admininvsee <player>");
        } 
      } else {
        sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Permission"));
      }  
    return false;
  }
}
