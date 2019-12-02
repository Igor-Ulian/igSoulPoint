/*    */ package br.com.igSoulPoint;
/*    */ 
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ public class Config {
/*    */   private org.bukkit.plugin.java.JavaPlugin plugin;
/*    */   private String name;
/*    */   private java.io.File file;
/*    */   private YamlConfiguration config;
/*    */   
/*    */   public Config(org.bukkit.plugin.java.JavaPlugin plugin, String nome) {
/* 12 */     this.plugin = plugin;
/* 13 */     setName(nome);
/* 14 */     reloadConfig();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public org.bukkit.plugin.java.JavaPlugin getPlugin()
/*    */   {
/* 21 */     return this.plugin;
/*    */   }
/*    */   
/* 24 */   public void setPlugin(org.bukkit.plugin.java.JavaPlugin plugin) { this.plugin = plugin; }
/*    */   
/*    */   public String getName() {
/* 27 */     return this.name;
/*    */   }
/*    */   
/* 30 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public java.io.File getFile() {
/* 33 */     return this.file;
/*    */   }
/*    */   
/* 36 */   public YamlConfiguration getConfig() { return this.config; }
/*    */   
/*    */   public void saveConfig()
/*    */   {
/*    */     try {
/* 41 */       getConfig().save(getFile());
/*    */     } catch (java.io.IOException e) {
/* 43 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/* 47 */   public void saveDefault() { getConfig().options().copyDefaults(true); }
/*    */   
/*    */ 
/* 50 */   public void saveDefaultConfig() { getPlugin().saveResource(getName(), false); }
/*    */   
/*    */   public void reloadConfig() {
/* 53 */     this.file = new java.io.File(getPlugin().getDataFolder(), getName());
/* 54 */     this.config = YamlConfiguration.loadConfiguration(getFile());
/*    */   }
/*    */   
/*    */   public void deleteConfig() {
/* 58 */     getFile().delete();
/*    */   }
/*    */   
/* 61 */   public boolean existeConfig() { return getFile().exists(); }
/*    */   
/*    */   public String getString(String path)
/*    */   {
/* 65 */     return getConfig().getString(path);
/*    */   }
/*    */   
/*    */   public int getInt(String path) {
/* 69 */     return getConfig().getInt(path);
/*    */   }
/*    */   
/*    */   public boolean getBoolean(String path) {
/* 73 */     return getConfig().getBoolean(path);
/*    */   }
/*    */   
/*    */   public double getDouble(String path) {
/* 77 */     return getConfig().getDouble(path);
/*    */   }
/*    */   
/*    */   public java.util.List<?> getList(String path) {
/* 81 */     return getConfig().getList(path);
/*    */   }
/*    */   
/* 84 */   public boolean contains(String path) { return getConfig().contains(path); }
/*    */   
/*    */   public void set(String path, Object value)
/*    */   {
/* 88 */     getConfig().set(path, value);
/*    */   }
/*    */ }