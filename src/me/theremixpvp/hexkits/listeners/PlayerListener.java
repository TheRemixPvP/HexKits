package me.theremixpvp.hexkits.listeners;

import me.theremixpvp.hexkits.HexKits;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		HexKits.get().kit.remove(e.getEntity());
	}

}
