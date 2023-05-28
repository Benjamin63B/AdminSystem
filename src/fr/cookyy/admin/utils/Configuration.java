package fr.cookyy.admin.utils;

import java.io.File;

import net.md_5.bungee.api.ChatColor;

public class Configuration extends SpigotFile {
  public Configuration(File datafolder, String name) {
    super(datafolder, name);
    
    ChatColor.translateAlternateColorCodes('&', "§");
    
    addDefault("Prefix.Inventory", "&bAdminFR > ");
    addDefault("Prefix.Staff", "&4AdminStaff > ");
    addDefault("Prefix.Hat", "&6&lAdminHats> ");
    addDefault("Prefix.Mute", "&cAdminMute > ");
    addDefault("Prefix.HideShow", "&aHide &f/ &cShow &f > ");
    addDefault("Prefix.BroadCast", "&5Broadcast");
    addDefault("Item.GUI.Server", "&cServer ");
    addDefault("Item.GUI.World", "&aWorld ");
    addDefault("Item.GUI.Player", "&6Player ");
    addDefault("Item.GUI.Others", "&2Others ");
    addDefault("Item.GUI.Vanish", "&6Vanish ");
    addDefault("Item.GUI.Moderation", "&6&lModeration ");
    addDefault("Item.GUI.Players", "&5Players ");
    addDefault("Item.GUI.Weather", "&2Weather ");
    addDefault("Item.GUI.RandomTP", "&bTéléportation ");
    addDefault("Item.Server.Item.Stop", "&4&lSTOP Server ");
    addDefault("Item.Server.Item.Reload", "&3Reload ");
    addDefault("Item.Server.Item.WhiteList", "&f&lWhitelist ");
    addDefault("Item.Server.Item.Difficulty", "&aDifficulty ");
    addDefault("Item.World.Item.KickAll", "&cKick all connected ");
    addDefault("Item.World.Item.KillAll", "&7Kill everyone ");
    addDefault("Item.World.Item.Day", "&aDay ");
    addDefault("Item.World.Item.Night", "&7Night ");
    addDefault("Item.World.Item.Thunder", "&9Thunder ");
    addDefault("Item.World.Item.Rain", "&3Rain ");
    addDefault("Item.World.Item.Clear", "&8Clear ");
    addDefault("Item.Player.Item.HealFeed", "&aHeal &f / &cFeed ");
    addDefault("Item.Player.Item.Player", "&5Players ");
    addDefault("Item.Player.Item.GameMode", "&aGameMode ");
    addDefault("Item.Players.Item.Banned", "&4Player Banned ");
    addDefault("Item.Players.Item.Kicked", "&3Player Kicked ");
    addDefault("Item.Others.Item.ClearInventory", "&7Clear Inventory ");
    addDefault("Item.Others.Item.ClearChat", "&cClear chat ");
    addDefault("Item.Others.Item.WorkBench", "&6WorkBench");
    addDefault("Item.Others.Item.Enderchest", "&dEnderChest");
    addDefault("Item.Others.Item.Anvil", "&dAnvil");
    addDefault("Item.Vanish.Item.Show", "&aShow Player ");
    addDefault("Item.Vanish.Item.Hide", "&cHide Player ");
    addDefault("ItemJoin.Name", "&bAdminFR ");
    addDefault("Item.GlassPanel.Name", "&cThis is useless");
    addDefault("Item.Barrier.Name", "&c&lClose ");
    addDefault("Item.NetherStar.Name", "&cReturn ");
    addDefault("MOTD.1", "&4AdminFR &f- &4Modified line in config.yml ❤");
    addDefault("MOTD.2", "&6Thanks fo download ");
    addDefault("World.Max_x_value", Integer.valueOf(1000));
    addDefault("World.Max_z_value", Integer.valueOf(1000));
    addDefault("World.Max_y_value", Integer.valueOf(70));
  }
}
