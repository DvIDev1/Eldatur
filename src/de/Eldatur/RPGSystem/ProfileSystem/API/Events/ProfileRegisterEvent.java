package de.Eldatur.RPGSystem.ProfileSystem.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.Eldatur.RPGSystem.ProfileSystem.ProfileBuilder;

public class ProfileRegisterEvent extends Event {
	
	ProfileBuilder RegisteredProfile;
	
	Player registeredPlayer;
	
	boolean succed;
	
	public ProfileRegisterEvent(ProfileBuilder RegisteredProfile , Player registeredPlayer, boolean succed) {
		this.registeredPlayer = registeredPlayer;
		this.RegisteredProfile = RegisteredProfile;
		this.succed = succed;
	}
	
	public Player getRegisteredPlayer() {
		return registeredPlayer;
	}
	
	public ProfileBuilder getRegisteredProfile() {
		return RegisteredProfile;
	}
	
	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	static public HandlerList getHandlerList() {
		return handlers;
	}
	
	public boolean isSucced() {
		return succed;
	}
	
}
