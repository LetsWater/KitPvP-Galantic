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
    private Integer hood_level;
    private Integer healer_level;
    private Integer rogue_level;
    private Integer knight_level;
    private Integer assassin_level;


    public SQLUser(String name, UUID uuid, String using_kit, double pvpCoins, Integer kills, Integer deaths, Integer bestStreak, Integer level, double xp, Integer warrior_level, Integer archer_level, Integer tank_level, Integer axe_level, Integer ninja_level, Integer hood_level, Integer healer_level, Integer rogue_level, Integer knight_level, Integer assassin_level) {
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

        this.hood_level = hood_level;
        this.healer_level = healer_level;
        this.rogue_level = rogue_level;
        this.knight_level = knight_level;
        this.assassin_level = assassin_level;
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

    public Integer getHood_level() {
        return hood_level;
    }

    public void setHood_level(Integer hood_level) {
        this.hood_level = hood_level;
    }

    public Integer getHealer_level() {
        return healer_level;
    }

    public void setHealer_level(Integer healer_level) {
        this.healer_level = healer_level;
    }

    public Integer getRogue_level() {
        return rogue_level;
    }

    public void setRogue_level(Integer rogue_level) {
        this.rogue_level = rogue_level;
    }

    public Integer getKnight_level() {
        return knight_level;
    }

    public void setKnight_level(Integer knight_level) {
        this.knight_level = knight_level;
    }

    public Integer getAssassin_level() {
        return assassin_level;
    }

    public void setAssassin_level(Integer assassin_level) {
        this.assassin_level = assassin_level;
    }
}
