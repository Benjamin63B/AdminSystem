package fr.cookyy.admin.commands;

import fr.cookyy.admin.AdminSystemMain;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RandomTP implements Listener, CommandExecutor {
  int x;
  
  int y;
  
  int z;
  
  World w;
  
  Location loc;
  
  Random r;
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (label.equalsIgnoreCase("rtp")) {
      if (sender.hasPermission("adminfr.rtp")) {
        if (!(sender instanceof Player)) {
          System.out.println(AdminSystemMain.configFile.getString("Message.Commands"));
          return true;
        } 
        Player player = (Player)sender;
        this.r = new Random();
        this.x = this.r.nextInt(AdminSystemMain.configFile.getInt("World.Max_x_value"));
        this.y = isPlayerUnderGround(player, this.x, AdminSystemMain.configFile.getInt("World.Max_y_value"), this.z, this.w);
        this.z = this.r.nextInt(AdminSystemMain.configFile.getInt("World.Max_z_value"));
        this.w = player.getWorld();
        this.loc = new Location(this.w, this.x, this.y, this.z);
        player.teleport(this.loc);
        sender.sendMessage(String.valueOf(AdminSystemMain.messageFile.getString("Player.Teleport.Message")) + "§6x:" + this.x + "§f/ " + "§6y:" + this.y + "§f/ " + "§6z:" + this.z);
        return true;
      } 
      sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Permission"));
      return true;
    } 
    return false;
  }
  
  public int isPlayerUnderGround(Player player, int x, int y, int z, World w) {
    int newY = y;
    int i = 1;
    Block checkedBlock = player.getWorld().getBlockAt(x, y, z);
    while (!checkedBlock.getType().equals(Material.AIR)) {
      checkedBlock = player.getWorld().getBlockAt(x, newY, z);
      newY += i;
      i++;
    } 
    return newY;
  }
}
