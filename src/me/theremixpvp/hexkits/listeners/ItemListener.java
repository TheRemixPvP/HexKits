package me.theremixpvp.hexkits.listeners;

import me.theremixpvp.hexkits.HexKits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemListener implements Listener {
	
	@EventHandler
	public void onSoup(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null && event.getItem().getType() == Material.MUSHROOM_SOUP) {
				if((p.getHealth() < 20.0D) || (p.getFoodLevel() < 20)) {
			        event.setCancelled(true);
			        if (p.getHealth() < 20.0D) {
			          if (p.getHealth() + 7 <= 20.0D)
			            p.setHealth(p.getHealth() + 7);
			          else
			            p.setHealth(20.0D);
			        } else if (p.getFoodLevel() < 20)
			          if (p.getFoodLevel() + 7 <= 20)
			            p.setFoodLevel(p.getFoodLevel() + 7);
			          else
			            p.setFoodLevel(20);
			        ItemStack i = new ItemStack(Material.BOWL);
			        ItemMeta im = i.getItemMeta();
			        im.setDisplayName(ChatColor.RED + "Bowl");
			        i.setItemMeta(im);
			        p.setItemInHand(i);
			        if(HexKits.get().getConfig().getBoolean("soup-sound")) {
			        	p.playSound(p.getLocation(), Sound.BURP, 1F, 1F);
			        }
				}
			}
		}
	}

}
