package de.Blackside.SkillSystem.Skills;

import de.Blackside.SkillSystem.API.Listener.CritSkillEvent;
import de.Blackside.SkillSystem.API.Listener.StreanghtSkillEvent;
import de.Blackside.SkillSystem.SkillSystemPlugin;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class criticalSkill implements Listener {

    int level;

    int number;

    private final HashMap<UUID, Boolean> ready = new HashMap<UUID, Boolean>();

    @EventHandler(priority = EventPriority.HIGH)
    public void onStreanghtSkill(StreanghtSkillEvent event) {
        int chance;
        number = getLevel(event.getPlayer()) * 2;
        Random random = new Random();
        chance = random.nextInt(100);
        CritSkillEvent cse = new CritSkillEvent(event.getPlayer() , event.getAttacketEntity());
        if (getLevel(event.getPlayer()) != 0) {
            if (chance <= number) {
                event.getPlayer().getWorld().spawnParticle(Particle.FLAME, event.getAttacketEntity().getLocation(), 2 * 20);
                event.addDamage(getLevel(event.getPlayer()));
                cse.setSucced(true);
            }
        }else cse.setSucced(false);
    }

    public static void setLevel(Player player, int level) {
        SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + player.getUniqueId() + ".Skills.CriticalSkill.level", level);
        SkillSystemPlugin.skillSystem.saveConfig();
    }

    public int getLevel(Player player) {
        level = SkillSystemPlugin.skillSystem.getConfig().getInt("SkillSystem.profiles." + player.getUniqueId() + ".Skills.CriticalSkill.level");
        return level;
    }
}
