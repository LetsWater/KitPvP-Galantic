package me.flame.galantic.sql.levelSystem.managers;

import me.flame.galantic.Core;
import me.flame.galantic.sql.levelSystem.UserLevel;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.*;
import java.util.ArrayList;

public class UserLevelManager {

    public static ArrayList<UserLevel> levelList = new ArrayList<>();

    public void loadLevels(){
        try(Connection connection = Core.getInstance().hikari.getConnection()){
            PreparedStatement levelData = connection.prepareStatement("SELECT * FROM `levels`");
            ResultSet resultSet = levelData.executeQuery();

            while (resultSet.next()){
                    UserLevel userLevel;
                    Integer level = resultSet.getInt("level");
                    double XP = resultSet.getDouble("xp");

                    userLevel = new UserLevel(level, XP);
                    levelList.add(userLevel);
            }

            if(resultSet.next()){
            }

            levelData.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Bukkit.broadcastMessage("Ik heb zojuist " + levelList.size() + " levels ingeladen");
        }
    }
}
