package de.Blackside.SkillSystem.Skills;

import de.Blackside.SkillSystem.SkillSystemPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class defenseSkill implements Listener {

    int level;

    private final HashMap<UUID, Boolean> ready = new HashMap<UUID, Boolean>();

    @EventHandler(priority = EventPriority.LOW)
    public void onDefenseSKillUse(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setDamage(event.getDamage() / (double) getLevel((Player) event.getEntity()));
            System.out.println(event.getDamage());
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (ready.isEmpty() || ready.get(player.getUniqueId())) {
                if (!(getLevel(player) == 0)) {
                    if (getLevel(player) <= 5) {
                        event.setCancelled(true);
                        ready.put(player.getUniqueId(), false);
                        Bukkit.getScheduler().runTaskLater(SkillSystemPlugin.skillSystem, new Runnable() {
                            @Override
                            public void run() {
                                ready.put(player.getUniqueId(), true);
                            }
                        }, 30 * 20);
                    } else if (getLevel(player) == 10) {
                        event.setCancelled(true);
                        ready.put(player.getUniqueId(), false);
                        player.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(300, 1));
                        player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(300, 1));
                        player.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(300, 1));
                        Bukkit.getScheduler().runTaskLater(SkillSystemPlugin.skillSystem, new Runnable() {
                            @Override
                            public void run() {
                                ready.put(player.getUniqueId(), true);
                            }
                        }, 30 * 20);
                    }
                }
            }
        }
    }

    public int getLevel(Player player) {
        level = SkillSystemPlugin.skillSystem.getConfig().getInt("SkillSystem.profiles." + player.getUniqueId() + ".Skills.DefenseSkill.level");
        return level;
    }

    public static void setLevel(Player player, int level) {
        SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + player.getUniqueId() + ".Skills.DefenseSkill.level", level);
        SkillSystemPlugin.skillSystem.saveConfig();
    }
}
