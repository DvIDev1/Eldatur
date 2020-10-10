package de.Blackside.SkillSystem.Skills;

import de.Blackside.SkillSystem.API.Listener.StreanghtSkillEvent;
import de.Blackside.SkillSystem.SkillSystemPlugin;
import net.minecraft.server.v1_14_R1.ItemSword;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_14_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftInventory;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class damageSkill implements Listener {

    private final HashMap<UUID, Boolean> ready = new HashMap<UUID, Boolean>();

    int level;

    long sec = 60;

    @EventHandler(priority = EventPriority.LOWEST)
    //onHit handelt alles was mit damage zu tuhen hat
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            double dmg = event.getDamage();
            Player damage = (Player) event.getDamager();
            Entity entity = event.getEntity();
            StreanghtSkillEvent hde = new StreanghtSkillEvent(dmg, damage, entity);

            if (Objects.requireNonNull(damage.getEquipment()).getItemInMainHand().getType() == Material.WOODEN_SWORD || Objects.requireNonNull(damage.getEquipment()).getItemInMainHand().getType() == Material.DIAMOND_SWORD || Objects.requireNonNull(damage.getEquipment()).getItemInMainHand().getType() == Material.STONE_SWORD || Objects.requireNonNull(damage.getEquipment()).getItemInMainHand().getType() == Material.IRON_SWORD || Objects.requireNonNull(damage.getEquipment()).getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
                if (this.getLevel(damage) <= 5) {
                    dmg = event.getDamage() + (double)this.getLevel(damage);
                    event.setDamage(event.getDamage() + (double)this.getLevel(damage));
                    Bukkit.getPluginManager().callEvent(hde);
                } else if (this.getLevel(damage) == 10) {
                    int ne = damage.getNearbyEntities(10.0D, 10.0D, 10.0D).size();
                    dmg = event.getDamage() + (double)this.getLevel(damage) + (double)ne;
                    event.setDamage(event.getDamage() + (double)this.getLevel(damage) + (double)ne);
                    Bukkit.getPluginManager().callEvent(hde);
                }
            }


            if (hde.isChangeDamage()) {
                event.setDamage(hde.getDealtdamage());
            }

            event.setDamage(dmg + hde.getDmg());

        }else if(event.getDamager() instanceof Projectile) {
            if(!(event.getDamager() instanceof Snowball || event.getDamager() instanceof Egg)) {
                double dmg = event.getDamage();
                Projectile p = (Projectile) event.getDamager();
                if(p.getShooter() instanceof Player) {
                    Player player = (Player) p.getShooter();
                    Entity entity = event.getEntity();
                    StreanghtSkillEvent hde = new StreanghtSkillEvent(dmg, player, entity);
                    assert player != null;
                    double y = p.getLocation().getY();

                    double shoty = entity.getLocation().getY();

                    boolean headshot = y - shoty >= 1.35D;
                    if (getLevel(player) <= 5) {
                        dmg = event.getDamage() + (double) getLevel(player);
                        if(!headshot) {
                            event.setDamage(event.getDamage() + (double) getLevel(player));
                        }else event.setDamage(event.getDamage() + (double) getLevel(player) + 5.0D);
                    } else if (getLevel(player) == 10) {
                        int ne = player.getNearbyEntities(10, 10, 10).size();
                         dmg = event.getDamage() + (double) getLevel(player) + (double) ne;
                        if(!headshot) {
                            event.setDamage(event.getDamage() + (double) getLevel(player) + ne);
                        }else event.setDamage(event.getDamage() + (double) getLevel(player) + 5.0D + (double) ne);
                    }

                    if(headshot) {
                        player.getWorld().spawnParticle(Particle.VILLAGER_ANGRY , p.getLocation(), 10);
                    }



                    Bukkit.getPluginManager().callEvent(hde);

                    if (hde.isChangeDamage()) {
                        event.setDamage(hde.getDealtdamage());
                    }

                    event.setDamage(dmg + hde.getDmg());
                }
            }
        }
    }

    @EventHandler
    public void onUseAbility(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getMaterial() == Material.WOODEN_SWORD || event.getMaterial() == Material.DIAMOND_SWORD || event.getMaterial() == Material.STONE_SWORD || event.getMaterial() == Material.IRON_SWORD || event.getMaterial() == Material.GOLDEN_SWORD) {
                if (getLevel(event.getPlayer()) >= 5) {
                    if (ready.containsValue(true)) {
                        ready.put(event.getPlayer().getUniqueId(), false);
                        for (Entity ne : event.getPlayer().getNearbyEntities(10, 10, 10)) {
                            if (ne instanceof Player) {
                                Player np = (Player) ne;
                                np.damage(5);
                            } else ne.setVelocity(event.getPlayer().getLocation().getDirection().multiply(3F));
                        }
                        Bukkit.getScheduler().runTaskLater(SkillSystemPlugin.skillSystem, new Runnable() {
                            @Override
                            public void run() {
                                ready.put(event.getPlayer().getUniqueId(), true);
                                event.getPlayer().sendMessage(ChatColor.GREEN + "Du kannst deine ability jz usen");
                            }
                        }, sec * 20L);

                    } else if (ready.isEmpty()) {
                        ready.put(event.getPlayer().getUniqueId(), true);
                    } else {
                        event.getPlayer().sendMessage(ChatColor.RED + "Du kannst das jz nicht usen");
                    }
                }
            }
        }
    }

    public int getLevel(Player player) {
        level = SkillSystemPlugin.skillSystem.getConfig().getInt("SkillSystem.profiles." + player.getUniqueId() + ".Skills.DamageSkill.level");
        return level;
    }

    public static void setLevel(Player player, int level) {
        SkillSystemPlugin.skillSystem.getConfig().set("SkillSystem.profiles." + player.getUniqueId() + ".Skills.DamageSkill.level", level);
        SkillSystemPlugin.skillSystem.saveConfig();
    }

    public HashMap<UUID, Boolean> getReady() {
        return ready;
    }
}
