package br.com.igSoulPoint;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.sqlite.core.DB;


public class Main extends JavaPlugin implements Listener{
	
	  public static Main plugin;
	  public Config db;
	  public Config config;
	  
	  public void onLoad(){
		 plugin = this;
	  }
	  
	  public static Main getInstance(){
	    return plugin;
	  }
	
	@Override
	public void onEnable() {
	    Bukkit.getPluginManager().registerEvents(this, this);
	    ConsoleCommandSender c = Bukkit.getConsoleSender();
	    c.sendMessage("§6igSoulPoint §aAtivado");
		try {
			File arquivop = new File("plugins/igSoulPoint/Players.yml");
			if(!arquivop.exists()) {
				arquivop.createNewFile();
			}
		} catch (IOException e1) {e1.printStackTrace();} 
		db = new Config(this, "Players.yml");
		config.saveDefaultConfig();
		config = new Config(this, "config.yml");
		
	}
	
	@Override
	public void onDisable() {
	    ConsoleCommandSender c = Bukkit.getConsoleSender();
	    c.sendMessage("§6igSoulPoint §cDesativado");
	    HandlerList.unregisterAll();
	    saveConfig();
	}

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		db = new Config(this, "Players.yml");
	    if(!db.contains("" + e.getPlayer().getUniqueId())) {
	    	 db.set("" + e.getPlayer().getUniqueId(), 10);
	    	 db.saveConfig();
	    }
		startSoulPointCounter(e.getPlayer());
		config = new Config(this, "config.yml");
		String MensagemEntrou = config.getString("MensagemEntrou");
		e.getPlayer().sendMessage(MensagemEntrou);
	}
	
	  private void startSoulPointCounter(Player p) {
      new BukkitRunnable(){
        public void run(){
          if (p.hasPermission("igSoulPoint.vip")){
        	  if(getSoulPoint(p) < 15) {
        		 putSoulPoint(p,getSoulPoint(p) + 1);
	            p.sendMessage("§c§l~ §7ja se passou Meia hora que você está se aventurando conosco! §7Tome, pegue este presente para você");
        	  }else {
        		  putSoulPoint(p, 15);
        	  }
          }else{
        	  if(getSoulPoint(p) < 10) {
        		 putSoulPoint(p, getSoulPoint(p) + 1);
	            p.sendMessage("§c§l~ §7ja se passou Meia hora que você esá se aventurando conosco! §7Tome, pegue este presente para você");
        	  }else {
        		  putSoulPoint(p, 10);
        	  }
          }
      }
    }.runTaskTimer(Main.plugin, 36000L, 36000L);
} 
	
	
	public int getSoulPoint(Player p) {
		db = new Config(this, "Players.yml");
		return db.getInt("" + p.getUniqueId());
	}
	
	public void putSoulPoint(Player p, Integer value) {
		db = new Config(this, "Players.yml");
		db.set("" + p.getUniqueId(), value);
		db.saveConfig();
	}
	
	
	
	

}
