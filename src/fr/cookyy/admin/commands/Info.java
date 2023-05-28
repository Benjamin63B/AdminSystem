package fr.cookyy.admin.commands;

import fr.cookyy.admin.AdminSystemMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Info implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
    if (cmd.getName().equalsIgnoreCase("info")) {
      sender.sendMessage(AdminSystemMain.messageFile.getString("Youtube.Message"));
      sender.sendMessage(AdminSystemMain.messageFile.getString("Forum.Message"));
      sender.sendMessage(AdminSystemMain.messageFile.getString("Web.Message"));
      return true;
    } 
    return true;
  }
}
