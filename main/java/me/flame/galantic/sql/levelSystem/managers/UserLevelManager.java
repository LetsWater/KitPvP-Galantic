package me.flame.galantic.sql.levelSystem.managers;

import me.flame.galantic.Core;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.levelSystem.UserLevel;

import me.galantic.galanticcore.api.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static me.flame.galantic.sql.managers.SQLUserManager.userList;

public class UserLevelManager {

    public static ArrayList<UserLevel> levelList = new ArrayList<>();
    private static UserLevelManager instance = new UserLevelManager();

    public void loadLevels() {
        try (Connection connection = Core.getInstance().hikari.getConnection()) {
            PreparedStatement levelData = connection.prepareStatement("SELECT * FROM `levels`");
            ResultSet resultSet = levelData.executeQuery();

            while (resultSet.next()) {
                UserLevel userLevel;
                Integer level = resultSet.getInt("level");
                double XP = resultSet.getDouble("xp");

                userLevel = new UserLevel(level, XP);
                levelList.add(userLevel);
            }

            levelData.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void levelUp(UUID uuid) {
        for (SQLUser user : userList) {
            if (user.getUuid() == uuid) {
                for (UserLevel userLevel : levelList) {
                    if (userLevel.getLevel() == user.getLevel() + 1) {
                        if (user.getXp() >= userLevel.getXP()) {
                            Player p = Bukkit.getServer().getPlayer(user.getUuid());
                            user.setLevel(user.getLevel() + 1);
                            user.setXp(user.getXp() - userLevel.getXP());
                            CoreAPI.getMessageManager().sendMessage(p, "level_up", user.getLevel());
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                        }
                    }
                }
            }
        }
    }

    public void setXPLevel(UUID uuid) {
        for (SQLUser user : userList) {
            if (user.getUuid() == uuid) {
                Player p = Bukkit.getServer().getPlayer(user.getUuid());
                p.setLevel(user.getLevel());

                return;
            }
        }
    }

    public static UserLevelManager getInstance() {
        return instance;
    }
}