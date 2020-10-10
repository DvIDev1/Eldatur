package de.Eldatur.RPGSystem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.Eldatur.RPGSystem.ProfileSystem.ProfileRegister;

public class RPGSystem extends JavaPlugin {
	
	public static RPGSystem rpgsytem;
	
	public void onEnable() {
		rpgsytem = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ProfileRegister(), this);
	}

}
