package me.flame.galantic.sql.levelSystem;

import me.flame.galantic.sql.levelSystem.interfaces.IUserLevel;

import java.util.UUID;

public class UserLevel {

    private Integer level;
    private double XP;

    public UserLevel(Integer level, double XP){
        this.level = level;
        this.XP = XP;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public double getXP() {
        return XP;
    }

    public void setXP(double XP) {
        this.XP = XP;
    }
}
