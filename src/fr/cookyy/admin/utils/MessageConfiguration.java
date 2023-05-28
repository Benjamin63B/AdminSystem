package fr.cookyy.admin.utils;

import java.io.File;

import net.md_5.bungee.api.ChatColor;

public class MessageConfiguration extends SpigotFile {
  public MessageConfiguration(File datafolder, String name) {
    super(datafolder, name);
    
    ChatColor.translateAlternateColorCodes('&', "&");

    addDefault("Message.OnJoin", "&8[&a+&8]&6 %player% &bjoinded the server !");
    addDefault("Message.Title.1", "&6Hello");
    addDefault("Message.Title.2", "&6%player% &bjoinded the server !");
    addDefault("Message.OnQuit", "&8[&c-&8]&6 %player% &bquit the server !");
    addDefault("Message.Death", "&c%player% was killed by &a%killer%");
    addDefault("Message.Permission", "&cYou do not have permission to perform this command");
    addDefault("Message.Commands", "&cYou must be a player to perform this command");
    addDefault("Message.MutePlayerOn", "&cYou have muted &f");
    addDefault("Message.MutePlayerOff", "&bYou have unmuted &f");
    addDefault("Message.PlayerMuteOn", "&cYou are muted by &6");
    addDefault("Message.PlayerMuteOff", "&aYou are unmuted by &6");
    addDefault("Message.Teleport", "&7Teleporting to&b %player%");
    addDefault("Player.Teleport.Message", "&7You have been randomly teleported to the coordinate: ");
    addDefault("Message.Show", "&aShow Player");
    addDefault("Message.Hide", "&cHide Player");
    addDefault("HeadName", "&7%player%");
    addDefault("Youtube.Message", "&fYou&cTube: &1www.youtube.com");
    addDefault("Forum.Message", "&6Forum: &1www.forum.net");
    addDefault("Web.Message", "&5Link: &1www.myweb.com");
  }
}
