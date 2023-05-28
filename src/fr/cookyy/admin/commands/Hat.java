package fr.cookyy.admin.commands;

import fr.cookyy.admin.AdminSystemMain;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Hat implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      PlayerInventory inv = player.getInventory();
      ItemStack held = player.getItemInHand();
      ItemStack helm = inv.getHelmet();
      if (held.getAmount() == 1 || held.getType() == Material.AIR) {
        inv.setHelmet(held);
        player.setItemInHand(helm);
        player.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Hat")) + "§aNew Hats");
        player.updateInventory();
      } else {
        sender.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Hat")) + "§cYour held item must have a stack size of 1.");
      } 
    } else {
      sender.sendMessage(String.valueOf(AdminSystemMain.configFile.getString("Prefix.Hat")) + "§cYou cannot execute this command from the console.");
    } 
    return true;
  }
}
