package me.flame.galantic.sql.levelSystem.interfaces;

import java.util.UUID;

public interface IUserLevel {

    void getLevel(UUID uuid);

    void setLevel(UUID uuid, Integer level);

    void getXP(UUID uuid);

    void setXP(UUID uuid, Integer level);

    void getNextLevelXP(Integer level);
}
