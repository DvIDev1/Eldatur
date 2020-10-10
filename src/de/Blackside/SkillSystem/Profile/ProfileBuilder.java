package de.Blackside.SkillSystem.Profile;

import org.bukkit.entity.Player;

import java.util.UUID;

public class ProfileBuilder {

    private Player player;

    private int points;

    private UUID uuid;

    public ProfileBuilder(Player player, int points) {
        this.player = player;
        this.points = points;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public UUID getUuid() {
        return uuid;
    }
}
