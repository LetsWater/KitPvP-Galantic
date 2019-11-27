package me.flame.galantic.sql.levelSystem;

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


    public double getXP() {
        return XP;
    }
}
