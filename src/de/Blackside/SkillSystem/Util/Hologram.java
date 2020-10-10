package de.Blackside.SkillSystem.Util;

import de.Blackside.SkillSystem.SkillSystemPlugin;
import net.minecraft.server.v1_14_R1.EntityArmorStand;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hologram {

    private final List<EntityArmorStand> entitylist = new ArrayList<>();
    private final IChatBaseComponent Text;
    private final Location location;
    private final double DISTANCE = 0.25D;
    int count;

    public Hologram(IChatBaseComponent Text, Location location) {
        this.Text = Text;
        this.location = location;
        create();
    }


    public void showPlayerTemp(final Player p, int Time){
        showPlayer(p);
        Bukkit.getScheduler().runTaskLater(SkillSystemPlugin.skillSystem, new Runnable() {
            public void run() {
                hidePlayer(p);
            }
        }, Time);
    }


    public void showAllTemp(final Player p,int Time){
        showAll();
        Bukkit.getScheduler().runTaskLater(SkillSystemPlugin.skillSystem, new Runnable() {
            public void run() {
                hideAll();
            }
        }, Time);
    }

    public void showPlayer(Player p) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void hidePlayer(Player p) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

        }
    }

    public void showAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (EntityArmorStand armor : entitylist) {
                PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public void hideAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (EntityArmorStand armor : entitylist) {
                PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    private void create() {
        for (IChatBaseComponent Text : this.Text) {
            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) Objects.requireNonNull(this.location.getWorld())).getHandle(),this.location.getX(), this.location.getY(),this.location.getZ());
            entity.setCustomName(Text);
            entity.setCustomNameVisible(true);
            entity.setInvisible(true);
            entity.setNoGravity(false);
            entitylist.add(entity);
            this.location.subtract(0, this.DISTANCE, 0);
            count++;
        }

        for (int i = 0; i < count; i++) {
            this.location.add(0, this.DISTANCE, 0);
        }
        this.count = 0;
    }

}
