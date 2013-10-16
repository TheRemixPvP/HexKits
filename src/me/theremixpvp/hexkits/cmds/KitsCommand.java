package me.theremixpvp.hexkits.cmds;

import me.theremixpvp.hexkits.HexKits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class KitsCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command!");
			return true;
		}
		Player p = (Player) sender;
		FileConfiguration cfg = HexKits.get().getKitsConfig().getConfig();
		
		String kits = "";
		String otherkits = "";
		for(String kitname : cfg.getConfigurationSection("kits").getKeys(false)) {
			if(p.isOp() || p.hasPermission("simplekits.kit." + kitname)) kits = kits + ChatColor.AQUA + kitname + ChatColor.GRAY + ", ";
			else otherkits = otherkits + ChatColor.DARK_AQUA + kitname + ChatColor.GRAY + ", ";
		}
		
		if(kits.length() == 0) p.sendMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "Your Kits: ");
		else p.sendMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "Your Kits: " + kits.substring(0, kits.length() - 2));
		if(otherkits.length() == 0) p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Other Kits: ");
		else p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "Other Kits: " + otherkits.substring(0, otherkits.length() - 2));
		return true;
	}

}
