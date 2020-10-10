package de.Blackside.SkillSystem.Profile;

import de.Blackside.SkillSystem.SkillSystemPlugin;
import de.Blackside.SkillSystem.Skills.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.Eldatur.RPGSystem.ProfileSystem.API.Events.ProfileRegisterEvent;

public class ProfileRegister implements Listener {

    @EventHandler
    public void onRegister(ProfileRegisterEvent event) {
        if (event.isSucced()) {
            SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + event.getRegisteredPlayer().getUniqueId(), event.getRegisteredPlayer().getUniqueId());
            SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + event.getRegisteredPlayer().getUniqueId() + ".name", event.getRegisteredPlayer().getName());
            SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + event.getRegisteredPlayer().getUniqueId() + ".points", event.getRegisteredProfile().getPoints());
            SkillSystemPlugin.skillSystem.saveConfig();
            criticalSkill.setLevel(event.getRegisteredPlayer(), 0);
            damageSkill.setLevel(event.getRegisteredPlayer(), 0);
            defenseSkill.setLevel(event.getRegisteredPlayer(), 0);
            speedSkill.setLevel(event.getRegisteredPlayer() , 0);
            stealthSkill.setLevel(event.getRegisteredPlayer() , 0);
        }
    }

}
