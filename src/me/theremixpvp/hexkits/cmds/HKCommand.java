package me.theremixpvp.hexkits.cmds;

import java.util.ArrayList;
import java.util.List;

import me.theremixpvp.hexkits.HexKits;
import me.theremixpvp.hexkits.utils.KitConfigHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HKCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command!");
			return true;
		}
		Player p = (Player) sender;
		if(args.length == 0) {
			p.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "                  " + ChatColor.RESET + ChatColor.GOLD + "HexKits v" + HexKits.get().pdf.getVersion() + ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "                  ");
			p.sendMessage(ChatColor.GOLD + "Plugin Created by: " + ChatColor.BOLD + HexKits.get().pdf.getAuthors().toString().replace("[", "").replace("]", ""));
			p.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "                                                   ");
			return true;
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("create")) {
				if(!p.hasPermission("hexkits.create")) {
					p.sendMessage(ChatColor.RED + "You do not have permission to create kits!");
					return true;
				}
				String name = args[1];
				if(KitConfigHandler.getInstance().kitExists(name)) {
					p.sendMessage(ChatColor.RED + "Kit already exists!");
					return true;
				}
				FileConfiguration cfg = KitConfigHandler.getInstance().getConfig();
				List<String> kits = cfg.getStringList("kitlist");
				kits.add(name);
				cfg.set("kitlist", kits);
				ConfigurationSection ms = cfg.createSection("kits." + name);
				ConfigurationSection is = ms.createSection("items");
				List<String> items = new ArrayList<String>();
				for(ItemStack item : p.getInventory().getContents()) {
					if(item != null) {
						String finals = item.getType().name() + " " + item.getAmount();
						if(item.getEnchantments().size() != 0) {
							for(Enchantment ench : item.getEnchantments().keySet()) {
								String ename = " " + ench.getName();
								String lvl = " " + item.getEnchantmentLevel(ench);
								finals = finals + ename + lvl;
							}
							
						}
						
						items.add(finals);
					}
				}
				is.set("items", items);
				ConfigurationSection as = ms.createSection("armor");
				if(p.getInventory().getHelmet() != null) {
					String helmet = p.getInventory().getHelmet().getType().name() + " " + p.getInventory().getHelmet().getAmount();
					if(p.getInventory().getHelmet().getEnchantments().size() != 0) {
						for(Enchantment ench : p.getInventory().getHelmet().getEnchantments().keySet()) helmet = helmet + " " + ench.getName() + " " + p.getInventory().getHelmet().getEnchantmentLevel(ench);
					}
					as.set("helmet", helmet);
				} else as.set("helmet", null);
				if(p.getInventory().getChestplate() != null) {
					String chestplate = p.getInventory().getChestplate().getType().name() + " " + p.getInventory().getChestplate().getAmount();
					if(p.getInventory().getChestplate().getEnchantments().size() != 0) {
						for(Enchantment ench : p.getInventory().getChestplate().getEnchantments().keySet()) chestplate = chestplate + " " + ench.getName() + " " + p.getInventory().getChestplate().getEnchantmentLevel(ench);
					}
					as.set("chestplate", chestplate);
				} else as.set("chestplate", null);
				if(p.getInventory().getLeggings() != null) {
					String leggings = p.getInventory().getLeggings().getType().name() + " " + p.getInventory().getLeggings().getAmount();
					if(p.getInventory().getLeggings().getEnchantments().size() != 0) {
						for(Enchantment ench : p.getInventory().getLeggings().getEnchantments().keySet()) leggings = leggings + " " + ench.getName() + " " + p.getInventory().getLeggings().getEnchantmentLevel(ench);
					}
					as.set("leggings", leggings);
				} else as.set("leggings", null);
				if(p.getInventory().getBoots() != null) {
					String boots = p.getInventory().getBoots().getType().name() + " " + p.getInventory().getBoots().getAmount();
					if(p.getInventory().getBoots().getEnchantments().size() != 0) {
						for(Enchantment ench : p.getInventory().getBoots().getEnchantments().keySet()) boots = boots + " " + ench.getName() + " " + p.getInventory().getBoots().getEnchantmentLevel(ench);
					}
					as.set("boots", boots);
				} else as.set("boots", null);
				
				List<String> pots = new ArrayList<String>();
				for(PotionEffect pe : p.getActivePotionEffects()) {
					PotionEffectType type = pe.getType();
					int time = pe.getDuration();
					int power = pe.getAmplifier();
					String finalp = type.getName() + " " + time + " " + power;
					pots.add(finalp);
				}
				ms.set("potions", pots);
				KitConfigHandler.getInstance().saveConfig();
				p.sendMessage(ChatColor.AQUA + "Kit created!");
				return true;
			} else if(args[0].equalsIgnoreCase("delete")) {
				if(!p.hasPermission("hexkits.delete")) {
					p.sendMessage(ChatColor.RED + "You do not have permission to delete kits!");
					return true;
				}
				
				String name = args[1];
				if(!KitConfigHandler.getInstance().kitExists(name)) {
					p.sendMessage(ChatColor.RED + "Kit doesn't exist!");
					return true;
				}
				String fname = KitConfigHandler.getInstance().getKitName(name);
				FileConfiguration conf = KitConfigHandler.getInstance().getConfig();
				List<String> kits = conf.getStringList("kitlist");
				kits.remove(fname);
				conf.set("kitlist", kits);
				conf.set("kits." + fname, null);
				KitConfigHandler.getInstance().saveConfig();
				p.sendMessage(ChatColor.AQUA + fname + " kit deleted!");
				return true;
			}
		}
			unrecognizedCommand(p);
			return true;
	}
	
	private void unrecognizedCommand(Player p) {
		p.sendMessage(ChatColor.RED + "Unrecognized SimpleKits command!");
	}

}
