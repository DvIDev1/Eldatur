package de.Blackside.SkillSystem;

import de.Blackside.SkillSystem.Listener.DamageEvent;
import de.Blackside.SkillSystem.Profile.ProfileRegister;
import de.Blackside.SkillSystem.Skills.*;
import de.Blackside.SkillSystem.Skills.Commands.profileCMD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SkillSystemPlugin extends JavaPlugin {
	
	public static SkillSystemPlugin skillSystem;
	
	public void onEnable() {
		skillSystem = this;
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new damageSkill(), this);
		pluginManager.registerEvents(new ProfileRegister(), this);
		pluginManager.registerEvents(new criticalSkill() , this);
		pluginManager.registerEvents(new DamageEvent() , this);
		pluginManager.registerEvents(new defenseSkill() , this);
		pluginManager.registerEvents(new speedSkill() , this);
		pluginManager.registerEvents(new stealthSkill() , this);
		Objects.requireNonNull(getCommand("profile")).setExecutor(new profileCMD());
	}

}
