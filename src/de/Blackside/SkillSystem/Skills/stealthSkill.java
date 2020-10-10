package de.Blackside.SkillSystem.Skills;

import de.Blackside.SkillSystem.SkillSystemPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.management.PlatformLoggingMXBean;
import java.util.HashMap;

public class stealthSkill implements Listener {

    int level;

    private HashMap<String , Boolean> huntermode = new HashMap<>();

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if(huntermode.isEmpty()) {
            huntermode.put(event.getPlayer().getName() , true);
        }
        Player player = event.getPlayer();
        Location location = player.getLocation();
        if(getLevel(player) != 0) {
            if(location.getBlock().getType() == Material.LEGACY_LONG_GRASS || location.getBlock().getType() == Material.TALL_GRASS) {
                int s = 15 - level;
                System.out.println(s * 20L);
                Bukkit.getScheduler().runTaskLater(SkillSystemPlugin.skillSystem, new Runnable() {
                    @Override
                    public void run() {
                        if(location.getBlock().getType() == Material.LEGACY_LONG_GRASS || location.getBlock().getType() == Material.TALL_GRASS) {
                                if(player.isSneaking()) {
                                    huntermode.put(player.getName() , true);
                                    player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(60 * 3 * 20, 1));
                                }
                        }
                    }
                }, s * 20L);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        for (Entity e : event.getPlayer().getNearbyEntities(5 , 5 ,5)) {
            if(e instanceof Player) {
                Player player = (Player) e;
                if(huntermode.get(player.getName())) {
                    huntermode.put(player.getName() , false);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Entity damger = event.getDamager();
            if(player.getNearbyEntities(10 , 10 , 10).size() == 2) {
                for(Entity e : player.getNearbyEntities(10 ,10 , 10)) {
                    if(e == damger) {
                        player.getNearbyEntities(10 , 10 ,10).remove(damger);
                        Entity entity = player.getNearbyEntities(10 , 10 ,10).get(1);
                        if(huntermode.get(player.getName())) {
                            if(getLevel(player) == 10) {
                                huntermode.put(player.getName() , false);
                                player.teleport(entity.getLocation());
                                entity.teleport(player.getLocation());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setLevel(Player registeredPlayer, int level) {
        SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + registeredPlayer.getUniqueId() + ".Skills.StealthSkill.level", level);
        SkillSystemPlugin.skillSystem.saveConfig();
    }

    public int getLevel(Player player) {
        level = SkillSystemPlugin.skillSystem.getConfig().getInt("SkillSystem.profiles." + player.getUniqueId() + ".Skills.StealthSkill.level");
        return level;
    }

}
