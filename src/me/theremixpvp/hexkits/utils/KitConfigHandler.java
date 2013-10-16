package me.theremixpvp.hexkits.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class KitConfigHandler {
	
	private KitConfigHandler() { }
	
	static KitConfigHandler instance = new KitConfigHandler();
	
	public static KitConfigHandler getInstance() {
		return instance;
	}
	
	Plugin plugin;
	
	FileConfiguration kits;
	File kitsFile;
	
	public void setup(Plugin p) {
		this.plugin = p;
		
		if(!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
		
		kitsFile = new File(plugin.getDataFolder(), "kits.yml");
		kits = YamlConfiguration.loadConfiguration(kitsFile);
		
		if(!kitsFile.exists()) {
			try { 
				kitsFile.createNewFile(); 
			} catch(IOException ex) {
				plugin.getLogger().log(Level.SEVERE, "Could not create kits file!", ex);
			}
		}
		
	}
	
	public FileConfiguration getConfig() {
		return kits;
	}
	
	public void saveConfig() {
		try {
			kits.save(kitsFile);
		} catch(IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "Could not save kits file!", ex);
		}
	}
	
	public boolean kitExists(String name) {
		for(String kn : kits.getStringList("kitlist")) {
			if(kn.equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	public String getKitName(String name) {
		for(String kn : kits.getStringList("kitlist")) {
			if(kn.equalsIgnoreCase(name)) return kn;
		}
		return null;
	}

}
