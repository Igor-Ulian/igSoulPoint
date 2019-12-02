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
			Main.getInstance().putSoulPoint(p, Main.getInstance().getSoulPoint(p) - 1);
			String message = Main.getInstance().config.getString("PlayerDieWithSoul").replaceAll("&", "§");
			p.sendMessage(message);
		}else {
			e.setKeepInventory(false);
			e.setKeepLevel(false);
			String message = Main.getInstance().config.getString("PlayerDieWithoutSoul").replaceAll("&", "§");
			p.sendMessage(message);
		}
	}

}
