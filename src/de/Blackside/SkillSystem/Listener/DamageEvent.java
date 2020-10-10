package de.Blackside.SkillSystem.Listener;

import de.Blackside.SkillSystem.Util.Hologram;
import net.minecraft.server.v1_14_R1.ChatMessage;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftProjectile;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            IChatBaseComponent chatBase = new ChatMessage(ChatColor.RED + String.valueOf(Integer.valueOf((int) event.getDamage())) + "♡");
            Hologram damage = new Hologram(chatBase , event.getEntity().getLocation());
            damage.showPlayerTemp((Player) event.getDamager(), 3 * 20);
        }else if(event.getDamager() instanceof Projectile ) {
            Projectile p = (Projectile) event.getDamager();
            if(p.getShooter() instanceof Player) {
                Player player = (Player) p.getShooter();
                IChatBaseComponent chatBase = new ChatMessage(ChatColor.RED + String.valueOf(Integer.valueOf((int) event.getDamage())) + "♡️");
                Hologram damage = new Hologram(chatBase , event.getEntity().getLocation());
                damage.showPlayerTemp(player, 3 * 20);
            }
        }
    }
}
