package fr.cookyy.admin;

import net.md_5.bungee.api.ChatColor;

import java.awt.Event;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mysql.jdbc.StringUtils;

import fr.cookyy.admin.commands.Admin;
import fr.cookyy.admin.commands.ClearInventory;
import fr.cookyy.admin.commands.Hat;
import fr.cookyy.admin.commands.Help;
import fr.cookyy.admin.commands.Info;
import fr.cookyy.admin.commands.InvSee;
import fr.cookyy.admin.commands.Message;
import fr.cookyy.admin.commands.MessageReply;
import fr.cookyy.admin.commands.Mute;
import fr.cookyy.admin.commands.RandomTP;
import fr.cookyy.admin.events.NoPlugin;
import fr.cookyy.admin.events.ServerListener;
import fr.cookyy.admin.events.onPlayerJoin;
import fr.cookyy.admin.listeners.BanGUI;
import fr.cookyy.admin.listeners.DifficultyGUI;
import fr.cookyy.admin.listeners.GameModeGUI;
import fr.cookyy.admin.listeners.HealFeedGUI;
import fr.cookyy.admin.listeners.KickGUI;
import fr.cookyy.admin.listeners.OtherGUI;
import fr.cookyy.admin.listeners.PlayerGUI;
import fr.cookyy.admin.listeners.PlayerWhiteList;
import fr.cookyy.admin.listeners.PlayersGUI;
import fr.cookyy.admin.listeners.ServerGUI;
import fr.cookyy.admin.listeners.VanishGUI;
import fr.cookyy.admin.listeners.WhiteListGUI;
import fr.cookyy.admin.listeners.WorldGUI;
import fr.cookyy.admin.utils.Chat;
import fr.cookyy.admin.utils.Configuration;
import fr.cookyy.admin.utils.MessageConfiguration;

public class AdminSystemMain extends JavaPlugin {
  public static String prefix = ChatColor.translateAlternateColorCodes('&', "> ");
  
  public static String prefixstaff = ChatColor.translateAlternateColorCodes('&', "> ");
  
  public static String prefixhat = ChatColor.translateAlternateColorCodes('&', "> ");
  
  public static String prefixmute = ChatColor.translateAlternateColorCodes('&', "> ");
  
  public static String prefixhideshow = ChatColor.translateAlternateColorCodes('&', "> ");
  
  public static String prefixbroadcast = ChatColor.translateAlternateColorCodes('&', "> ");
  
  
  public static Configuration configFile;
  
  public static MessageConfiguration messageFile;
  
  public static AdminSystemMain instance;
  
  String target; 
    
  public void onEnable() {
    configFile = new Configuration(getDataFolder(), "config");
    messageFile = new MessageConfiguration(getDataFolder(), "message");
    prefix = configFile.getString("Prefix.Inventory");
    Bukkit.getConsoleSender().sendMessage(String.valueOf(prefix) + "§aenable" + "§c " + getDescription().getVersion());
    registerCommands();
    registerEvents();
    instance = this;
    
    }
  
  public String getTarget() {
	    return this.target;
	  }
	  
  public static AdminSystemMain getInstance() {
    return instance;
  }
  
  public void registerCommands() {
    getCommand("admin").setExecutor((CommandExecutor)new Admin());
    getCommand("adminhelp").setExecutor((CommandExecutor)new Help());
    getCommand("adminclear").setExecutor((CommandExecutor)new ClearInventory());
    getCommand("adminhat").setExecutor((CommandExecutor)new Hat());
    getCommand("adminmute").setExecutor((CommandExecutor)new Mute());
    getCommand("admininvsee").setExecutor((CommandExecutor)new InvSee());
    getCommand("info").setExecutor((CommandExecutor)new Info());
    getCommand("msg").setExecutor((CommandExecutor)new Message());
    getCommand("reply").setExecutor((CommandExecutor)new MessageReply());
    getCommand("rtp").setExecutor((CommandExecutor)new RandomTP());
  }
  
  public void registerEvents() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents((Listener)new Admin(), (Plugin)this);
    pm.registerEvents((Listener)new Help(), (Plugin)this);
    pm.registerEvents((Listener)new Mute(), (Plugin)this);
    pm.registerEvents((Listener)new onPlayerJoin(), (Plugin)this);
    pm.registerEvents((Listener)new NoPlugin(), (Plugin)this);
    pm.registerEvents((Listener)new Chat(), (Plugin)this);
    pm.registerEvents((Listener)new BanGUI(), (Plugin)this);
    pm.registerEvents((Listener)new DifficultyGUI(), (Plugin)this);
    pm.registerEvents((Listener)new GameModeGUI(), (Plugin)this);
    pm.registerEvents((Listener)new HealFeedGUI(), (Plugin)this);
    pm.registerEvents((Listener)new KickGUI(), (Plugin)this);
    pm.registerEvents((Listener)new OtherGUI(), (Plugin)this);
    pm.registerEvents((Listener)new PlayerGUI(), (Plugin)this);
    pm.registerEvents((Listener)new PlayersGUI(), (Plugin)this);
    pm.registerEvents((Listener)new PlayerWhiteList(), (Plugin)this);
    pm.registerEvents((Listener)new ServerGUI(), (Plugin)this);
    pm.registerEvents((Listener)new VanishGUI(), (Plugin)this);
    pm.registerEvents((Listener)new WhiteListGUI(), (Plugin)this);
    pm.registerEvents((Listener)new WorldGUI(), (Plugin)this);
    pm.registerEvents((Listener)new ServerListener(), (Plugin) this);
  }
  
  
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(String.valueOf(prefix) + "§c disable !");
  }
}
