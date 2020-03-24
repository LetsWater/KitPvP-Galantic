package me.flame.galantic.sql.managers;

import me.flame.galantic.Core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLManager {

    public void createTables(){
        try{
            Connection connection = Core.getInstance().hikari.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `user_data` (`uuid` varchar(36) NOT NULL, `name` varchar(36) NOT NULL,`using_kit` varchar(36) NOT NULL ,`pvp_coins` DOUBLE NOT NULL, `kills` INT NOT NULL, `deaths` INT NOT NULL, `best_streak` INT NOT NULL, `warrior_level` INT DEFAULT 1, `archer_level` INT DEFAULT 1, `tank_level` INT DEFAULT 1, `axe_level` INT DEFAULT 1, `ninja_level` INT DEFAULT 1, `vip_level` INT DEFAULT 0, `elite_level` INT DEFAULT 0, `hero_level` INT DEFAULT 0, `god_level` INT DEFAULT 0, `custom_level` INT DEFAULT 0, PRIMARY KEY (`uuid`))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `levels` (`level` int, `xp` int, PRIMARY KEY (`level`))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `user_levels` (`uuid` varchar(36), `name` varchar(36), `level` int, `xp` int, PRIMARY KEY (`uuid`))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kit_upgrades` (`kit_name` varchar(36), `upgrade_cost_level1` int, `upgrade_cost_level2` int, `upgrade_cost_level3` int, `upgrade_cost_level4` int, `upgrade_cost_level5` int, PRIMARY KEY (`kit_name`)");

            Core.getInstance().getLogger().info("Connected to a database! Using SQL to store data.");

        } catch (SQLException e){

            Core.getInstance().getLogger().info("Couldn't connect to a working database!");
        }
    }
}
