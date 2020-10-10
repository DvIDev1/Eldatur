package de.Eldatur.RPGSystem.ProfileSystem;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.Eldatur.RPGSystem.ProfileSystem.API.Events.ProfileRegisterEvent;

public class ProfileRegister implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		ProfileBuilder profile = new ProfileBuilder(event.getPlayer());
		profile.setName(event.getPlayer().getName());
		RegisterProfile register = new RegisterProfile(profile);
		ProfileRegisterEvent pre = new ProfileRegisterEvent(profile, event.getPlayer() , register.isSucceed());
		Bukkit.getPluginManager().callEvent(pre);
		register.save();
	}

}
