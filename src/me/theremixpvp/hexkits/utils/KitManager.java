package me.theremixpvp.hexkits.utils;

import me.theremixpvp.hexkits.HexKits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KitManager {
	
	public void clearInventory(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
	}
	
	public void giveKit(Player p, String kit) {
		try {
			clearInventory(p);

			for (String itemslist : KitConfigHandler.getInstance().getConfig().getStringList("kits." + kit + ".items")) {
				String[] items = itemslist.split(" ");

				if (items.length == 2) {
					Material imat = Material.matchMaterial(items[0]);

					Integer amount = Integer.valueOf(Integer.parseInt(items[1]));

					p.getInventory().addItem(new ItemStack(imat, amount.intValue()));
				} else if (items.length == 4) {
					Material imat = Material.matchMaterial(items[0]);

					Integer amount = Integer.valueOf(Integer.parseInt(items[1]));
					
					Enchantment enchant = Enchantment.getByName(items[2]);

					Integer power = Integer.valueOf(Integer.parseInt(items[3]));

					ItemStack i = new ItemStack(imat, amount.intValue());

					i.addUnsafeEnchantment(enchant, power.intValue());

					p.getInventory().addItem(i);
				} else {
					Material imat = Material.matchMaterial(items[0]);
					p.getInventory().addItem(new ItemStack(imat));
				}

			}

			for (String potioni : KitConfigHandler.getInstance().getConfig().getStringList("kits." + kit + ".potions")) {
				String[] potions = potioni.split(" ");
				
				PotionEffectType pet = PotionType.valueOf(potions[0]).getType();
				int l = Integer.parseInt(potions[1]);
				int pwr = Integer.parseInt(potions[2]);
				PotionEffect pe = new PotionEffect(pet, l * 20, pwr - 1);
				p.addPotionEffect(pe);
			}

			String helms = KitConfigHandler.getInstance().getConfig().getString("kits." + kit + ".armor.helmet");

			String[] helmss = helms.split(" ");

			Material helmmat = Material.matchMaterial(helmss[0]);
			
			Integer helmamount = Integer.valueOf(Integer.parseInt(helmss[1]));

			ItemStack helmet = new ItemStack(helmmat, helmamount.intValue());

			if (helmss.length == 4) {
				Enchantment enchant = Enchantment.getByName(helmss[2]);

				Integer power = Integer.valueOf(Integer.parseInt(helmss[3]));

				helmet.addUnsafeEnchantment(enchant, power.intValue());
			}

			p.getInventory().setHelmet(helmet);

			String chests = KitConfigHandler.getInstance().getConfig().getString(
			        "kits." + kit + ".armor.chestplate");

			String[] chestss = chests.split(" ");

			Material chestmat = Material.matchMaterial(chestss[0]);

			Integer chestamount = Integer.valueOf(Integer.parseInt(chestss[1]));

			ItemStack chestplate = new ItemStack(chestmat, chestamount.intValue());

			if (chestss.length == 4) {
				Enchantment enchant = Enchantment.getByName(chestss[2]);

				Integer power = Integer.valueOf(Integer.parseInt(chestss[3]));

				chestplate.addUnsafeEnchantment(enchant, power.intValue());
			}

			p.getInventory().setChestplate(chestplate);

			String leggss = KitConfigHandler.getInstance().getConfig().getString("kits." + kit + ".armor.leggings");

			String[] leggsss = leggss.split(" ");

			Material legsmat = Material.matchMaterial(leggsss[0]);

			Integer leggsamount = Integer.valueOf(Integer.parseInt(leggsss[1]));

			ItemStack leggsplate = new ItemStack(legsmat, leggsamount.intValue());

			if (leggsss.length == 4) {
				Enchantment enchant = Enchantment.getByName(leggsss[2]);

				Integer power = Integer.valueOf(Integer.parseInt(leggsss[3]));

				leggsplate.addUnsafeEnchantment(enchant, power.intValue());
			}

			p.getInventory().setLeggings(leggsplate);

			String bootss = KitConfigHandler.getInstance().getConfig().getString("kits." + kit + ".armor.boots");

			String[] bootsss = bootss.split(" ");

			Material bootsmat = Material.matchMaterial(bootsss[0]);

			Integer bootsamount = Integer.valueOf(Integer.parseInt(bootsss[1]));

			ItemStack bootsplate = new ItemStack(bootsmat, bootsamount.intValue());

			if (bootsss.length == 4) {
				Enchantment enchant = Enchantment.getByName(bootsss[2]);

				Integer power = Integer.valueOf(Integer.parseInt(bootsss[3]));

				bootsplate.addUnsafeEnchantment(enchant, power.intValue());
			}

			p.getInventory().setBoots(bootsplate);
			
			
			p.sendMessage(HexKits.get().getConfig().getString("give-kit-message").replace("&", "¤").replace("$KITNAME$", kit));
		} catch (Exception ex) {
			ex.printStackTrace();
			p.sendMessage(ChatColor.RED + "An error has occured!");
		}
	} 

}
