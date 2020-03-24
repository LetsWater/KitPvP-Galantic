package me.flame.galantic.kits.upgradeKits.managers;

import me.flame.galantic.Core;
import me.flame.galantic.kits.upgradeKits.Upgrade;
import me.flame.galantic.kits.upgradeKits.interfaces.IUpgrade;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.levelSystem.UserLevel;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.objects.IUser;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.security.URIParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class UpgradeManager implements IUpgrade {

    public static ArrayList<Upgrade> upgradeList = new ArrayList<>();
    private static UpgradeManager instance = new UpgradeManager();

    @Override
    public void loadUpgrades() {
        try (Connection connection = Core.getInstance().hikari.getConnection()) {
            PreparedStatement upgradeData = connection.prepareStatement("SELECT * FROM `kit_upgrades`");
            ResultSet resultSet = upgradeData.executeQuery();

            while (resultSet.next()) {
                Upgrade upgrade;

                String name = resultSet.getString("kit_name");
                double upgrade_cost_level1 = resultSet.getDouble("upgrade_cost_level1");
                double upgrade_cost_level2 = resultSet.getDouble("upgrade_cost_level2");
                double upgrade_cost_level3 = resultSet.getDouble("upgrade_cost_level3");
                double upgrade_cost_level4 = resultSet.getDouble("upgrade_cost_level4");
                double upgrade_cost_level5 = resultSet.getDouble("upgrade_cost_level5");

                upgrade = new Upgrade(name, upgrade_cost_level1, upgrade_cost_level2, upgrade_cost_level3, upgrade_cost_level4, upgrade_cost_level5);
                upgradeList.add(upgrade);

            }

            upgradeData.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upgradeKitLevel(UUID uuid, String kit, Integer level) {
        Player p = Bukkit.getServer().getPlayer(uuid);
        if(level >= 6){
            return;
        }
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                for (Upgrade upgrade : upgradeList) {
                    if (upgrade.getName().equals(kit)) {
                        if (user.getPvpCoins() >= getLevelUpgradeCost(kit, level)) {
                            CoreAPI.getMessageManager().sendMessage( p, "kit_upgrade", upgrade.getName(), level);
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                            switch(upgrade.getName()){
                                case "warrior":
                                    user.setWarrior_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "archer":
                                    user.setArcher_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "tank":
                                    user.setTank_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "axe":
                                    user.setAxe_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "ninja":
                                    user.setNinja_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "hood":
                                    user.setHood_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "healer":
                                    user.setHealer_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "rogue":
                                    user.setRogue_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "knight":
                                    user.setKnight_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                case "assassin":
                                    user.setAssassin_level(level);
                                    user.setPvpCoins(user.getPvpCoins() - getLevelUpgradeCost(kit, level));
                                    break;
                                default:
                                    Bukkit.getServer().getPlayer(uuid).sendMessage(ChatUtils.format("&cError with your upgrade! Contact an Admin or Developer."));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public double getLevelUpgradeCost(String kit, Integer level){
        for(Upgrade update : upgradeList){
            if(update.getName().equalsIgnoreCase(kit)){
                switch(level){
                    case 1:
                        return update.getUpgradeCost1();
                    case 2:
                        return update.getUpgradeCost2();
                    case 3:
                        return update.getUpgradeCost3();
                    case 4:
                        return update.getUpgradeCost4();
                    case 5:
                        return update.getUpgradeCost5();
                    default:
                        return 0;
                }
            }
        }
        return 0;
    }

    public static UpgradeManager getInstance() {
        return instance;
    }
}
