package de.Eldatur.RPGSystem.ProfileSystem;

import org.bukkit.entity.Player;

public class ProfileBuilder {
	
	private Player player;
	
	private String name;
	
	private String uuid;
	
	private int MaxLevel;
	
	private int MaxEXP;

	private boolean rebirth;
	
	private int level;
	
	private int exp;
	
	private int points;
	
	
	public ProfileBuilder(Player player) {
		this.player = player;
		setName(player.getName().toString());
		setUuid(player.getUniqueId().toString());
		setMaxEXP(100);
		setMaxLevel(5);
		setExp(0);
		setLevel(0);
		setPoints(1);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setMaxEXP(int maxEXP) {
		MaxEXP = maxEXP;
	}
	
	public void setMaxLevel(int maxLevel) {
		MaxLevel = maxLevel;
	}
	
	public void setRebirth(boolean rebirth) {
		this.rebirth = rebirth;
	}
	
	public int getMaxEXP() {
		return MaxEXP;
	}
	
	public int getMaxLevel() {
		return MaxLevel;
	}
	
	public boolean isRebirth() {
		return rebirth;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public int getExp() {
		return exp;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

}
