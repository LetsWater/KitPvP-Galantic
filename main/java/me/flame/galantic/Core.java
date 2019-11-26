package me.flame.galantic;

import com.zaxxer.hikari.HikariDataSource;
import me.flame.galantic.combatlogger.listeners.CombatLogEvent;
import me.flame.galantic.commands.CombatLogCommand;
import me.flame.galantic.commands.SetSpawnCommand;
import me.flame.galantic.commands.SpawnCommand;
import me.flame.galantic.commands.StatisticsCommand;
import me.flame.galantic.commands.gui.listeners.InventoryListener;
import me.flame.galantic.listeners.JoinEventListener;
import me.flame.galantic.listeners.MoveEvent;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.sql.levelSystem.UserLevel;
import me.flame.galantic.sql.levelSystem.managers.UserLevelManager;
import me.flame.galantic.sql.listeners.UserListener;
import me.flame.galantic.sql.managers.SQLManager;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.FileManager;
import me.flame.galantic.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin implements Listener {

    private static Core instance;
    public static HikariDataSource hikari;
    private static SQLManager sqlManager;
    private static UserLevelManager userLevelManager = new UserLevelManager();
    private final SQLUserManager sqlUserManager = new SQLUserManager();
    private final ScoreboardUtils scoreboardUtils = new ScoreboardUtils();

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        FileManager.load(this, "config.yml");

        instance = this;
        sqlManager = new SQLManager();
        getLogger().info("KitPvP has been enabled!");

        registerListeners();
        registerCommands();

        connect();
        sqlManager.createTables();
        userLevelManager.loadLevels();


        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            sqlUserManager.loadUser(online.getUniqueId());
            scoreboardUtils.setScoreboard(online.getUniqueId());
        }

    }

    @Override
    public void onDisable() {
        instance = null;
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            SQLUserManager.getInstance().saveUser(online.getUniqueId());
        }

        if (hikari != null) {
            hikari.close();
        }
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new UserListener(), this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new JoinEventListener(), this);
        pm.registerEvents(new PvPEventListener(), this);
        pm.registerEvents(new MoveEvent(), this);
        pm.registerEvents(new CombatLogEvent(), this);
    }

    private void registerCommands() {
        getCommand("stats").setExecutor(new StatisticsCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("combatlog").setExecutor(new CombatLogCommand());
    }

    private void connect() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        hikari.addDataSourceProperty("serverName", "151.80.201.167");
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", "devac_KitPvP");
        hikari.addDataSourceProperty("user", "devac");
        hikari.addDataSourceProperty("password", "qJzNee3q7hLGHEW3");
    }
}
