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
    private double xp;
    private Integer warrior_level;
    private Integer archer_level;
    private Integer tank_level;
    private Integer axe_level;
    private Integer ninja_level;
    private Integer vip_level;
    private Integer elite_level;
    private Integer hero_level;
    private Integer god_level;
    private Integer custom_level;


    public SQLUser(String name, UUID uuid, String using_kit, double pvpCoins, Integer kills, Integer deaths, Integer bestStreak, Integer level, double xp, Integer warrior_level, Integer archer_level, Integer tank_level, Integer axe_level, Integer ninja_level, Integer vip_level, Integer elite_level, Integer hero_level, Integer god_level, Integer custom_level) {
        this.name = name;
        this.uuid = uuid;
        this.using_kit = using_kit;
        this.pvpCoins = pvpCoins;
        this.kills = kills;
        this.deaths = deaths;
        this.bestStreak = bestStreak;
        this.level = level;
        this.xp = xp;

        this.warrior_level = warrior_level;
        this.archer_level = archer_level;
        this.tank_level = tank_level;
        this.axe_level = axe_level;
        this.ninja_level = ninja_level;

        this.vip_level = vip_level;
        this.elite_level = elite_level;
        this.hero_level = hero_level;
        this.god_level = god_level;
        this.custom_level = custom_level;
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

    public void setLevel(Integer level){
        this.level = level;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
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

    public Integer getWarrior_level() {
        return warrior_level;
    }

    public void setWarrior_level(Integer warrior_level) {
        this.warrior_level = warrior_level;
    }

    public Integer getArcher_level() {
        return archer_level;
    }

    public void setArcher_level(Integer archer_level) {
        this.archer_level = archer_level;
    }

    public Integer getTank_level() {
        return tank_level;
    }

    public void setTank_level(Integer tank_level) {
        this.tank_level = tank_level;
    }

    public Integer getAxe_level() {
        return axe_level;
    }

    public void setAxe_level(Integer axe_level) {
        this.axe_level = axe_level;
    }

    public Integer getNinja_level() {
        return ninja_level;
    }

    public void setNinja_level(Integer ninja_level) {
        this.ninja_level = ninja_level;
    }

    public Integer getVip_level() {
        return vip_level;
    }

    public void setVip_level(Integer vip_level) {
        this.vip_level = vip_level;
    }

    public Integer getElite_level() {
        return elite_level;
    }

    public void setElite_level(Integer elite_level) {
        this.elite_level = elite_level;
    }

    public Integer getHero_level() {
        return hero_level;
    }

    public void setHero_level(Integer hero_level) {
        this.hero_level = hero_level;
    }

    public Integer getGod_level() {
        return god_level;
    }

    public void setGod_level(Integer god_level) {
        this.god_level = god_level;
    }

    public Integer getCustom_level() {
        return custom_level;
    }

    public void setCustom_level(Integer custom_level) {
        this.custom_level = custom_level;
    }
}
