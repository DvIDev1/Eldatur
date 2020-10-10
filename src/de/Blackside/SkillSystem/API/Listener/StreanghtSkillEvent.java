package de.Blackside.SkillSystem.API.Listener;

import de.Eldatur.RPGSystem.ProfileSystem.ProfileBuilder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StreanghtSkillEvent extends Event {

    double dealtdamage;

    Player player;

    Entity AttacketEntity;

    boolean changeDamage;

    double dmg;

    private static final HandlerList handlers = new HandlerList();

    public StreanghtSkillEvent(double dealtdamage, Player player, Entity AttacketEntity) {
        this.dealtdamage = dealtdamage;
        this.player = player;
        this.AttacketEntity = AttacketEntity;
    }

    public void setDealtdamage(double dealtdamage) {
        this.dealtdamage = dealtdamage;
        changeDamage = true;
    }

    public void addDamage(double amount) {
        this.dmg = amount;
    }

    public double getDmg() {
        return dmg;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public double getDealtdamage() {
        return dealtdamage;
    }

    public Entity getAttacketEntity() {
        return AttacketEntity;
    }

    public boolean isChangeDamage() {
        return changeDamage;
    }
}
