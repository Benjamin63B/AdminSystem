package fr.cookyy.admin.utils;

import fr.cookyy.admin.AdminSystemMain;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.event.Listener;

public class Update implements Listener {
  public AdminSystemMain plugin;
  
  public String version;
  
  public Update(AdminSystemMain plugin) {
    this.plugin = plugin;
    this.version = getLatestVersion();
  }
  
  public String getLatestVersion() {
    try {
      int resource = 41271;
      HttpURLConnection con = (HttpURLConnection)(new URL("https://api.spigotmc.org/legacy/update.php?resource=41271")).openConnection();
      con.setDoOutput(true);
      con.setRequestMethod("POST");
      con.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=41271".getBytes("UTF-8"));
      String version = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
      if (version.length() <= 7)
        return version; 
    } catch (Exception ex) {
      System.out.println("---------------------------------");
      this.plugin.getLogger().info("§4Failed to check for a update!");
      System.out.println("---------------------------------");
    } 
    return null;
  }
  
  public boolean isConnected() {
    return (this.version != null);
  }
  
  public boolean hasUpdate() {
    return !this.version.equals(this.plugin.getDescription().getVersion());
  }
}
