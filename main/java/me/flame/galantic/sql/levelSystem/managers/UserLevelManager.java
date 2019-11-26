package me.flame.galantic.sql.levelSystem.managers;

import me.flame.galantic.Core;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.levelSystem.UserLevel;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class UserLevelManager {

    public static ArrayList<UserLevel> levelList = new ArrayList<>();

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

            if (resultSet.next()) {
            }

            levelData.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void levelUp(UUID uuid) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                for (UserLevel userLevel : levelList) {
                    if (userLevel.getLevel() == user.getLevel() + 1) {
                        if (user.getXp() >= userLevel.getXP()) {
                            Player p = Bukkit.getServer().getPlayer(user.getUuid());

                            p.sendMessage(ChatUtils.format("&c&l! &7Gefeliciteerd! Je bent zojuist naar level &c" + user.getLevel() + 1 + " &7gegaan!"));
                            user.setXp((int) (user.getXp() - userLevel.getXP()));
                        }
                    }
                }
            }
        }
    }
}
