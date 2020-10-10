package de.Eldatur.RPGSystem.ProfileSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import de.Eldatur.RPGSystem.RPGSystem;

public class RegisterProfile {
	
	private ProfileBuilder profile;
	
	private boolean succeed;
	
	public RegisterProfile(ProfileBuilder profile) {
		try {
			this.profile = profile;
			if(RPGSystem.rpgsytem.getConfig().get("RPGSystem.Profiles." + profile.getUuid()) == null) {
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid(), profile.getUuid());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".name", profile.getName());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".maxLevel", profile.getMaxLevel());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".maxExp", profile.getMaxEXP());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".Exp", profile.getExp());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".Level", profile.getLevel());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".Points", profile.getPoints());
				RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + profile.getUuid() + ".Rebirth", profile.isRebirth());
				setSucceed(true);
			}else {
				setSucceed(false);
			}
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.ITALIC + "Ein Fehler ist aufgetreten");
		}
	}

	public ProfileBuilder getProfile() {
		return profile;
	}
	
	public void save() {
		RPGSystem.rpgsytem.saveConfig();
	}
	
	private void setSucceed(boolean succed) {
		this.succeed = succed;
	}
	
	public boolean isSucceed() {
		return succeed;
	}

}
