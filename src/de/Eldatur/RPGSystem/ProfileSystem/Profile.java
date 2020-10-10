package de.Eldatur.RPGSystem.ProfileSystem;

import org.bukkit.entity.Player;

import de.Eldatur.RPGSystem.RPGSystem;

public class Profile {
	
	private final Player player;
	
	private String name;

	private int MaxLevel;
	
	private int MaxEXP;

	private boolean rebirth;
	
	private int level;
	
	private int exp;
	
	private int points;
	
	public Profile(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setName(String name) {
		RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + player.getUniqueId() + ".name", name);
		this.name = name;
	}
	
	public String getName() {
		name = RPGSystem.rpgsytem.getConfig().getString("RPGSystem.Profiles." + player.getUniqueId() + ".name");
		return name;
	}
	
	public String getUuid() {
		String uuid = (String) RPGSystem.rpgsytem.getConfig().get("RPGSystem.Profiles." + player.getUniqueId(), player.getUniqueId());
		return uuid;
	}
	
	public void setMaxEXP(int maxEXP) {
		RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + player.getUniqueId() + ".maxExp", maxEXP);
		MaxEXP = maxEXP;
	}
	
	public void setMaxLevel(int maxLevel) {
		RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + player.getUniqueId() + ".maxLevel", maxLevel);
		MaxLevel = maxLevel;
	}
	
	public void setRebirth(boolean rebirth) {
		RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + player.getUniqueId() + ".Rebirth", rebirth);
		this.rebirth = rebirth;
	}
	
	public int getMaxEXP() {
		MaxEXP = RPGSystem.rpgsytem.getConfig().getInt("RPGSystem.Profiles." + player.getUniqueId() + ".maxExp");
		return MaxEXP;
	}
	
	public int getMaxLevel() {
		MaxLevel = RPGSystem.rpgsytem.getConfig().getInt("RPGSystem.Profiles." + player.getUniqueId() + ".maxLevel");
		return MaxLevel;
	}
	
	public boolean isRebirth() {
		rebirth = RPGSystem.rpgsytem.getConfig().getBoolean("RPGSystem.Profiles." + player.getUniqueId() + ".Rebirth");
		return rebirth;
	}
	
	public void setExp(int exp) {
		RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + player.getUniqueId() + ".Exp", exp);
		this.exp = exp;
	}
	
	public int getExp() {
		exp = RPGSystem.rpgsytem.getConfig().getInt("RPGSystem.Profiles." + player.getUniqueId() + ".Exp");
		return exp;
	}
	
	public int getLevel() {
		level = RPGSystem.rpgsytem.getConfig().getInt("RPGSystem.Profiles." + player.getUniqueId() + ".Level");
		return level;
	}
	
	public void setLevel(int level) {
		RPGSystem.rpgsytem.getConfig().set("RPGSystem.Profiles." + player.getUniqueId() + ".Level", level);
		this.level = level;
	}
	
	public int getPoints() {
		points = RPGSystem.rpgsytem.getConfig().getInt("RPGSystem.Profiles." + player.getUniqueId() + ".Points");
		return points;
	}
	
	public void setPoints(int points) {
		RPGSystem.rpgsytem.getConfig().getInt("RPGSystem.Profiles." + player.getUniqueId() + ".Points" , points);
		this.points = points;
	}

}
