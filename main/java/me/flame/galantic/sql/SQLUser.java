package me.flame.galantic.sql;

import java.util.UUID;

public class SQLUser {

    private String name;
    private UUID uuid;
    private String using_kit;
    private double pvpCoins;
    private Integer kills;
    private Integer deaths;
    private Integer bestStreak;
    private Integer level;
    private Integer xp;

    public SQLUser(String name, UUID uuid, String using_kit, double pvpCoins, Integer kills, Integer deaths, Integer bestStreak, Integer level, Integer xp) {
        this.name = name;
        this.uuid = uuid;
        this.using_kit = using_kit;
        this.pvpCoins = pvpCoins;
        this.kills = kills;
        this.deaths = deaths;
        this.bestStreak = bestStreak;
        this.level = level;
        this.xp = xp;
    }

    public double getPvpCoins() {
        return pvpCoins;
    }

    public void setPvpCoins(double coins) {
        this.pvpCoins = coins;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }


    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getBestStreak() {
        return bestStreak;
    }

    public void setBestStreak(Integer bstreak) {
        this.bestStreak = bstreak;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getXp() {
        return xp;
    }


    public String getUsing_kit() {
        return using_kit;
    }

    public void setUsing_kit(String using_kit) {
        this.using_kit = using_kit;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }
}
