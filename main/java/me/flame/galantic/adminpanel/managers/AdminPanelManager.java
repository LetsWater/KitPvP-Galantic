package me.flame.galantic.adminpanel.managers;

import me.flame.galantic.Core;
import me.flame.galantic.adminpanel.AdminPanel;
import me.flame.galantic.adminpanel.interfaces.IAdminPanel;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AdminPanelManager implements IAdminPanel {

    private static AdminPanelManager instance = new AdminPanelManager();
    public static ArrayList<AdminPanel> adminPanelList = new ArrayList<>();

    public void registerAdminPanel() {
        try (Connection connection = Core.getInstance().hikari.getConnection()) {
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM `admin_panel`;");
            ResultSet result = insert.executeQuery();

            if (!result.next()) {
                insert.executeUpdate("INSERT INTO `admin_panel` (`permissions_enabled`, `perms_last_changed_by`, `coins_booster`, `coins_last_changed_by`, `xp_booster`, `xp_last_changed_by`)  VALUE ('true', 'iException', '1', 'iException', '1', 'iException');");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            loadAdminPanel();
        }
    }

    @Override
    public String loadAdminPanel() {
        try (Connection connection = Core.getInstance().hikari.getConnection()) {

            PreparedStatement adminPanelDB = connection.prepareStatement("SELECT * FROM `admin_panel`;");

            ResultSet resultData = adminPanelDB.executeQuery();

            if (resultData.next()) {

                Boolean permissions = resultData.getBoolean("permissions_enabled");
                String lastChangedPermissions = resultData.getString("perms_last_changed_by");
                Double coinBooster = resultData.getDouble("coins_booster");
                String lastChangedCoins = resultData.getString("coins_last_changed_by");
                Double xpBooster = resultData.getDouble("xp_booster");
                String lastChangedXP = resultData.getString("xp_last_changed_by");

                AdminPanel adminPanel;

                adminPanel = new AdminPanel(permissions, lastChangedPermissions, coinBooster, lastChangedCoins, xpBooster, lastChangedXP);
                adminPanelList.add(adminPanel);
            } else {
                registerAdminPanel();
            }

            adminPanelDB.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setPermissions(Boolean permissions, Player p) {
        for(AdminPanel admin : AdminPanelManager.adminPanelList){
            if(permissions == true){
                admin.setPermissions(true);
                admin.setLastChangerPermissions(p.getName());
                break;
            } else {
                admin.setPermissions(false);
                admin.setLastChangerPermissions(p.getName());
                break;
            }
        }
        return true;
    }


    @Override
    public boolean getPermissions() {
        for(AdminPanel adminP : AdminPanelManager.adminPanelList){
            if(adminP.getPermissions() == false){
                return false;
            } if(adminP.getPermissions() == true){
                return true;
            }
            break;
        }
        return true;
    }

    @Override
    public String getLastChangerPerms() {
        for(AdminPanel adminP : adminPanelList){
           return adminP.getLastChangerPermissions();
        }
        return null;
    }

    @Override
    public double setBooster(Double number, Player p) {
        for(AdminPanel adminP : adminPanelList){
            adminP.setBooster(number);
            adminP.setLastChangerBooster(p.getName());
            break;
        }
        return 1;
    }

    @Override
    public double getBooster() {
        for(AdminPanel adminP : adminPanelList){
            return adminP.getBooster();
        }
        return 1;
    }

    @Override
    public String getLastChangerCoinsBooster() {
        for(AdminPanel adminPanel : adminPanelList){
            return adminPanel.getLastChangerBooster();
        }
        return null;
    }

    @Override
    public double setXpBooster(Double number, Player p) {
        for(AdminPanel adminP : adminPanelList){
            adminP.setXpBooster(number);
            adminP.setLastChangerXPBooster(p.getName());
            break;
        }
        return 1;
    }

    @Override
    public double getXpBooster() {
        for(AdminPanel adminP : adminPanelList){
            return adminP.getXpBooster();
        }
        return 1;
    }

    @Override
    public String getLastChangerXpBooster() {
        for(AdminPanel adminP : adminPanelList){
            return adminP.getLastChangerXPBooster();
        }
        return null;
    }

    public static AdminPanelManager getInstance(){
        return instance;
    }
}
