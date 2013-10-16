package me.theremixpvp.hexkits;

import java.util.HashMap;

import me.theremixpvp.hexkits.cmds.HKCommand;
import me.theremixpvp.hexkits.cmds.KitsCommand;
import me.theremixpvp.hexkits.listeners.CommandListener;
import me.theremixpvp.hexkits.listeners.ItemListener;
import me.theremixpvp.hexkits.listeners.PlayerListener;
import me.theremixpvp.hexkits.listeners.SignListener;
import me.theremixpvp.hexkits.utils.KitConfigHandler;
import me.theremixpvp.hexkits.utils.KitManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class HexKits extends JavaPlugin {
	
	static HexKits instance;
	private KitManager kitManager;
	private KitConfigHandler kitConfigH = KitConfigHandler.getInstance();
	
	public PluginDescriptionFile pdf;
	
	public HashMap<String, String> kit;
	
	public void onEnable() {
		kit = new HashMap<String, String>();
		instance = this;
		kitManager = new KitManager();
		pdf = this.getDescription();
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		KitConfigHandler.getInstance().setup(this);
		getCommand("hexkits").setExecutor(new HKCommand());
		getCommand("kits").setExecutor(new KitsCommand());
		Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ItemListener(), this);
	}
	
	public static HexKits get() {
		return instance;
	}
	
	public KitManager getKitManager() {
		return kitManager;
	}
	
	public KitConfigHandler getKitsConfig() {
		return kitConfigH;
	}
}
