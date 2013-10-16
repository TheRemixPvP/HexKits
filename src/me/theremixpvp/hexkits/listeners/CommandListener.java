package me.theremixpvp.hexkits.listeners;

import me.theremixpvp.hexkits.HexKits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	
	HexKits plugin = HexKits.get();
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		
		Player player = event.getPlayer();
		String msg = event.getMessage().replace("/", "");
		
		for(String n : plugin.getKitsConfig().getConfig().getStringList("kitlist")) {
			
			if(n.equalsIgnoreCase(msg)) {
				
				if(!player.hasPermission("simplekits.kit." + n)) {
					player.sendMessage(ChatColor.RED + "You don't have permission for this kit!");
					event.setCancelled(true);
				}
				
				if(plugin.kit.containsKey(player.getName())) {
					player.sendMessage(ChatColor.RED + "You have already used a kit!");
					event.setCancelled(true);
				}
				
				plugin.kit.put(player.getName(), n);
				plugin.getKitManager().giveKit(player, n);
				event.setCancelled(true);
			}
			
			
		}
		
		
	}

}
