package me.flame.galantic.kits.upgradeKits.interfaces;

import java.util.UUID;

public interface IUpgrade {

    void loadUpgrades();

    void upgradeKitLevel(UUID uuid, String name, Integer level);

    double getLevelUpgradeCost(String name, Integer level);

}
