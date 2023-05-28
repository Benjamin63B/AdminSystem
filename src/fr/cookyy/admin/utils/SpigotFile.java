package fr.cookyy.admin.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpigotFile {
  private File file;
  
  private FileConfiguration cfg;
  
  public SpigotFile(File datafolder, String name) {
    if (!datafolder.exists())
      datafolder.mkdir(); 
    this.file = new File(datafolder.getPath(), String.valueOf(name) + ".yml");
    if (!this.file.exists())
      try {
        this.file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }  
    this.cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    this.cfg.options().copyDefaults(true);
  }
  
  public FileConfiguration getConfig() {
    return this.cfg;
  }
  
  public File getFile() {
    return this.file;
  }
  
  public void reload() {
    this.cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
  }
  
  public void addDefault(String path, Object value) {
    this.cfg.addDefault(path, value);
    save();
  }
  
  public boolean getBoolean(String path) {
    return this.cfg.getBoolean(path);
  }
  
  public void set(String path, Object value) {
    this.cfg.set(path, value);
    save();
  }
  
  public String getString(String path) {
    return this.cfg.getString(path).replace('&', '§');
  }
  
  public int getInt(String path) {
    return this.cfg.getInt(path);
  }
  
  public double getDouble(String path) {
    return this.cfg.getDouble(path);
  }
  
  public List<String> getStringList(String path) {
    return this.cfg.getStringList(path);
  }
  
  public ItemStack ItemStack(String path, String displayName) {
    ItemStack item = new ItemStack(Material.getMaterial(getInt(String.valueOf(path) + displayName + ".type")));
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayName);
    item.setAmount(getInt(String.valueOf(path) + meta.getDisplayName() + ".amount"));
    meta.setLore(getStringList(String.valueOf(path) + meta.getDisplayName() + ".lore"));
    item.getData().setData((byte)getInt(String.valueOf(path) + meta.getDisplayName() + ".data"));
    for (String st : getStringList(String.valueOf(path) + meta.getDisplayName() + ".enchantments"))
      meta.addEnchant(Enchantment.getById(Integer.parseInt(st.split(", ")[0])), Integer.parseInt(st.split(", ")[1]), true); 
    item.setItemMeta(meta);
    return item;
  }
  
  public void setItemStack(String path, ItemStack item) {
    if (item.hasItemMeta()) {
      ItemMeta meta = item.getItemMeta();
      List<String> list = new ArrayList<>();
      if (meta.hasEnchants()) {
        for (Enchantment ent : item.getEnchantments().keySet())
          list.add(String.valueOf(ent.getId()) + ", " + item.getEnchantments().get(ent)); 
        set(String.valueOf(path) + meta.getDisplayName() + ".enchantments", list);
      } 
      if (meta.hasLore())
        set(String.valueOf(path) + meta.getDisplayName() + ".lore", meta.getLore()); 
      set(String.valueOf(path) + meta.getDisplayName() + ".type", Integer.valueOf(item.getTypeId()));
      set(String.valueOf(path) + meta.getDisplayName() + ".amount", Integer.valueOf(item.getAmount()));
      set(String.valueOf(path) + meta.getDisplayName() + ".data", Byte.valueOf(item.getData().getData()));
    } else {
      return;
    } 
    save();
    reload();
  }
  
  public void save() {
    try {
      this.cfg.save(this.file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
