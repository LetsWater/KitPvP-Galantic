package me.flame.galantic.sql.managers;

import me.flame.galantic.Core;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.interfaces.ISQLUser;
import me.flame.galantic.sql.levelSystem.managers.UserLevelManager;
import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SQLUserManager implements ISQLUser {

    private static SQLUserManager instance = new SQLUserManager();

    public static ArrayList<SQLUser> userList = new ArrayList<>();

    @Override
    public void registerUser(UUID uuid) {
        try (Connection connection = Core.getInstance().hikari.getConnection()) {
            OfflinePlayer p = Bukkit.getPlayer(uuid);
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM `user_data` WHERE uuid = '" + uuid + "';");
            ResultSet result = insert.executeQuery();

            if (!result.next()) {
                insert.executeUpdate("INSERT INTO `user_data` (`uuid`, `name`, `using_kit`, `pvp_coins`, `kills`, `deaths`, `best_streak`)  VALUE ('" + uuid + "', '" + p.getName() + "', 'warrior' , '0', '0', '0', '0');");
                insert.executeUpdate("INSERT INTO `user_levels` (`uuid`, `name`, `level`, `xp`) VALUE ('" + uuid + "', '" + p.getName() + "', '1', '0');");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            loadUser(uuid);
        }
    }

    @Override
    public void loadUser(UUID uuid) {
        try (Connection connection = Core.getInstance().hikari.getConnection()) {
            OfflinePlayer p = Bukkit.getPlayer(uuid);

            PreparedStatement userData = connection.prepareStatement("SELECT * FROM `user_data` WHERE uuid = '" + uuid + "';");
            PreparedStatement userLevels = connection.prepareStatement("SELECT * FROM `user_levels` WHERE uuid = '" + uuid + "';");

            ResultSet resultData = userData.executeQuery();
            ResultSet resultLevels = userLevels.executeQuery();

            if (resultData.next() && resultLevels.next()) {

                SQLUser user;
                String name = p.getName();
                String using_kit = resultData.getString("using_kit");
                double pvpCoins = resultData.getDouble("pvp_coins");
                Integer kills = resultData.getInt("kills");
                Integer deaths = resultData.getInt("deaths");
                Integer bestStreak = resultData.getInt("best_streak");
                Integer level = resultLevels.getInt("level");
                Integer xp = resultLevels.getInt("xp");

                user = new SQLUser(name, uuid, using_kit, pvpCoins, kills, deaths, bestStreak, level, xp);
                userList.add(user);

                PvPEventListener.killstreak.put(uuid, 0);

            } else {
                registerUser(uuid);
            }

            PvPEventListener.killstreak.put(p.getUniqueId(), 0);
            userData.close();
            userLevels.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(UUID uuid) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                try (Connection connection = Core.getInstance().hikari.getConnection()) {
                    PreparedStatement userData = connection.prepareStatement("SELECT * FROM `user_data` WHERE uuid = '" + uuid + "';");
                    PreparedStatement userLevels = connection.prepareStatement("SELECT * FROM `user_levels` WHERE uuid = '" + uuid + "';");

                    userData.executeUpdate("UPDATE `user_data` set `using_kit` = '" + user.getUsing_kit() + "' WHERE uuid = '" + uuid + "';");
                    userData.executeUpdate("UPDATE `user_data` set `pvp_coins` = '" + user.getPvpCoins() + "' WHERE uuid = '" + uuid + "';");
                    userData.executeUpdate("UPDATE `user_data` set `kills` = '" + user.getKills() + "' WHERE uuid = '" + uuid + "';");
                    userData.executeUpdate("UPDATE `user_data` set `deaths` = '" + user.getDeaths() + "' WHERE uuid = '" + uuid + "';");
                    userData.executeUpdate("UPDATE `user_data` set `best_streak` = '" + user.getBestStreak() + "' WHERE uuid = '" + uuid + "';");

                    userLevels.executeUpdate("UPDATE `user_levels` set `level` = '" + user.getLevel() + "' WHERE uuid = '" + uuid + "';");
                    userLevels.executeUpdate("UPDATE `user_levels` set `xp` = '" + user.getXp() + "' WHERE uuid = '" + uuid + "';");

                    userData.close();
                    userLevels.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addRewards(UUID uuid) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                user.setKills(user.getKills() + 1);
                user.setPvpCoins(user.getPvpCoins() + FileManager.get("config.yml").getDouble("PvP-Settings.coins-per-kill"));
                user.setXp(user.getXp() + FileManager.get("config.yml").getDouble("PvP-Settings.xp-per-kill"));
                UserLevelManager.getInstance().levelUp(user.getUuid());

                PvPEventListener.killstreak.replace(uuid, PvPEventListener.killstreak.get(uuid) + 1);
                broadcastKillstreak(uuid,PvPEventListener.killstreak.get(uuid));

                UserLevelManager.getInstance().setXPLevel(user.getUuid());
                break;
            }
        }
    }

    public void removeRewards(UUID uuid) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                user.setDeaths(user.getDeaths() + 1);
                UUID killerUUID = PvPEventListener.inFight.get(uuid);

                broadcastEndedKillstreak(uuid, killerUUID, PvPEventListener.killstreak.get(uuid));
                PvPEventListener.killstreak.replace(uuid, 0);

                if (user.getPvpCoins() - FileManager.get("config.yml").getDouble("PvP-Settings.coins-per-death") < 0) {
                    user.setPvpCoins(0);
                    break;
                }
                user.setPvpCoins(user.getPvpCoins() - FileManager.get("config.yml").getDouble("PvP-Settings.coins-per-death"));

                UserLevelManager.getInstance().setXPLevel(user.getUuid());
                break;
            }
        }
    }

    public void broadcastKillstreak(UUID uuid, Integer killStreak) {
        if(killStreak % 5 == 0) {
            Bukkit.getOnlinePlayers().forEach(target -> CoreAPI.getMessageManager().sendMessage(target, "killsteak", Bukkit.getServer().getPlayer(uuid).getName(), killStreak));
        }
    }

    public void broadcastEndedKillstreak(UUID uuid, UUID killerUuid, Integer killStreak){
        if(killStreak >= 5){
            Bukkit.getOnlinePlayers().forEach(target -> CoreAPI.getMessageManager().sendMessage(target, "ended_killsteak", Bukkit.getServer().getPlayer(uuid).getName(), Bukkit.getServer().getPlayer(killerUuid).getName() , killStreak));
        }
    }

    /**
     * Returning a static instance of this class.
     *
     * @return instance
     */

    public static SQLUserManager getInstance() {
        return instance;
    }
}
