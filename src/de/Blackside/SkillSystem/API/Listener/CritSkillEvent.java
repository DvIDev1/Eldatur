package de.Blackside.SkillSystem.API.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CritSkillEvent extends Event {

    Player player;

    Entity AttacketEntity;

    private static final HandlerList handlers = new HandlerList();

    boolean succed;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public CritSkillEvent(Player player, Entity AttacketEntity) {
        this.player = player;
        this.AttacketEntity = AttacketEntity;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Entity getAttacketEntity() {
        return AttacketEntity;
    }

    public void setSucced(boolean succed) {
        this.succed = succed;
    }

    public boolean isSucced() {
        return succed;
    }
}
