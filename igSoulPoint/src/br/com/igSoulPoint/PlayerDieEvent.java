package br.com.igSoulPoint;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDieEvent implements Listener{
	
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if(Main.getInstance().getSoulPoint(p) > 0) {
			e.setKeepInventory(true);
			e.setKeepLevel(true);
			e.setDroppedExp(0);
			Main.getInstance().putSoulPoint(p, Main.getInstance().getSoulPoint(p) - 1);
			p.sendMessage("§cYou die, but your items were not dropped");
			p.sendMessage("§c-1 Soul");
		}else {
			e.setKeepInventory(false);
			e.setKeepLevel(false);
			p.sendMessage("§cYou die, and your items are dropped");
		}
	}

}
