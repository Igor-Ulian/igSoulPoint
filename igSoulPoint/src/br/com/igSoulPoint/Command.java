package br.com.igSoulPoint;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, org.bukkit.command.Command c, String arg2, String[] args) {
		Player p = (Player) s;
		if(c.getName().equalsIgnoreCase("Souls")) { 
			if(args.length == 0) {
				String message = Main.getInstance().config.getString("YourSouls").replaceAll("&", "§").replaceAll("@s", "" + Main.getInstance().getSoulPoint(p));
				p.sendMessage("" + message);
			}else if(args.length == 1) {
			 String player2name  = args[0].toString();
			 if(Main.getInstance().db.contains("" + player2name)) {
				 Player player2 = Bukkit.getPlayer(player2name);
				 if(player2.isOnline()) {
					 String message = Main.getInstance().config.getString("PlayerSouls").replaceAll("&", "§").replaceAll("@p","" + player2.getName()).replaceAll("@s", "" + Main.getInstance().getSoulPoint(player2));
						 p.sendMessage(message);
				 }else {
					 String message = Main.getInstance().config.getString("PlayerOffline").replaceAll("&", "§");
					 p.sendMessage(message);
					 return false;
				 } 
			 }else {
				 String message = Main.getInstance().config.getString("PlayerExists").replaceAll("&", "§");
				 p.sendMessage(message);
				 return false;
			 }
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("set")) {
					 String player2name  = args[1].toString();
					 if(Main.getInstance().db.contains("" + player2name)) {
						 Player player2 = Bukkit.getPlayer(player2name);
						 if(player2.isOnline()) {
							 Main.getInstance().db.set(player2.getName(), Integer.parseInt(args[2].toString()));
							 String message = Main.getInstance().config.getString("PlayerChangePlayerSouls").replaceAll("&", "§").replaceAll("@p", player2.getName()).replaceAll("@s", "" +  Main.getInstance().getSoulPoint(player2));
							 p.sendMessage(message);
						 }
					 }
				}
				
			}
		}
		return false;
	}

}
