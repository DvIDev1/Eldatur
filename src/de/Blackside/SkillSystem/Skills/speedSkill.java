package de.Blackside.SkillSystem.Skills;

import de.Blackside.SkillSystem.SkillSystemPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class speedSkill implements Listener {

    int level;

    private final HashMap<UUID, Boolean> ready = new HashMap<UUID, Boolean>();

    private final HashMap<UUID, Boolean> ready2 = new HashMap<UUID, Boolean>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        switch (getLevel(player)) {
            case 0:
                player.setWalkSpeed(0.2F);
                break;
            case 1:
                player.setWalkSpeed(0.3F);
                break;
            case 2:
                player.setWalkSpeed(0.4F);
                break;
            case 3:
                player.setWalkSpeed(0.5F);
                break;
            case 4:
                player.setWalkSpeed(0.6F);
                break;
            case 5:
                player.setWalkSpeed(0.7F);
                break;
            case 6:
                player.setWalkSpeed(0.8F);
                break;
            case 7:
                player.setWalkSpeed(0.9F);
                break;
            case 8:
                player.setWalkSpeed(1.0F);
                break;
        }
    }

    public static void setLevel(Player registeredPlayer, int level) {
        SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + registeredPlayer.getUniqueId() + ".Skills.SpeedSkill.level", level);
        SkillSystemPlugin.skillSystem.saveConfig();
    }

    public int getLevel(Player player) {
        level = SkillSystemPlugin.skillSystem.getConfig().getInt("SkillSystem.profiles." + player.getUniqueId() + ".Skills.SpeedSkill.level");
        return level;
    }
}
