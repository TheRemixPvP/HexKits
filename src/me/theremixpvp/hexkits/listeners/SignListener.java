package me.theremixpvp.hexkits.listeners;

import me.theremixpvp.hexkits.HexKits;
import me.theremixpvp.hexkits.utils.KitConfigHandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if(e.getPlayer().hasPermission("HexKits.sign.create")) {
			String l1 = e.getLine(0);
			String l2 = e.getLine(1);
			String l3 = e.getLine(2);
			String l4 = e.getLine(3);
			
			if(l1.equalsIgnoreCase("[HexKits]")) {
				
				if(l2.equalsIgnoreCase("kit")) {
					
					if(l3.equalsIgnoreCase("")) {
						e.setLine(0, "No Kit?");
						e.setLine(1, "");
						e.setLine(2, "");
						e.setLine(3, "");
						return;
					}
					String kit = l3;
					if(!KitConfigHandler.getInstance().kitExists(kit)) {
						e.setLine(0, "Invalid kit");
						e.setLine(1, "");
						e.setLine(2, "");
						e.setLine(3, "");
						return;
					}
					
					e.setLine(0, ChatColor.AQUA + "-Kit-");
					e.setLine(1, ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + KitConfigHandler.getInstance().getKitName(kit));
					e.setLine(2, "");
					e.setLine(3, "");
					return;
					
				}
				
			}
			return;
		}
		e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to create HexKits signs!");
		
	}
	
	
	
	@EventHandler
	public void onRightClickSign(PlayerInteractEvent e) {
		if(e.getClickedBlock() == null) 
			return;
		if ((e.getClickedBlock().getType() == Material.WALL_SIGN) || (e.getClickedBlock().getType() == Material.SIGN) || (e.getClickedBlock().getType() == Material.SIGN_POST)) {
			Sign sign = (Sign) e.getClickedBlock().getState();
			if(sign.getLine(0).equals(ChatColor.AQUA + "-Kit-")) {
				Player p = e.getPlayer();
				if(!p.hasPermission("HexKits.sign.use.kits")) {
					p.sendMessage(ChatColor.RED + "You do not have permission to use kit signs!");
					return;
				}
				if(!KitConfigHandler.getInstance().kitExists(ChatColor.stripColor(sign.getLine(1)))) {
					p.sendMessage(ChatColor.RED + "Invalid kit!");
					return;
				}
				if(HexKits.get().kit.containsKey(p.getName())) {
					if(!p.hasPermission("HexKits.bypasscooldown")) {
							p.sendMessage(ChatColor.RED + "You have already used a kit!");
							return;
					}
				}
				if(!p.hasPermission("HexKits.kit." + KitConfigHandler.getInstance().getKitName(ChatColor.stripColor(sign.getLine(1))))) {
					p.sendMessage(ChatColor.RED + "You don't have permission for this kit!");
					return;
				}
				HexKits.get().getKitManager().giveKit(p, KitConfigHandler.getInstance().getKitName(ChatColor.stripColor(sign.getLine(1))));
				HexKits.get().kit.put(p.getName(), KitConfigHandler.getInstance().getKitName(ChatColor.stripColor(sign.getLine(1))));
				return;
			}
			
			
		}
	}

}
