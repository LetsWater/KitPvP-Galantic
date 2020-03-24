package me.flame.galantic;

import java.util.Arrays;

import me.flame.galantic.commands.*;
import me.flame.galantic.kits.upgradeKits.listeners.GUIInventoryListener;
import me.flame.galantic.kits.upgradeKits.managers.UpgradeManager;
import me.flame.galantic.listeners.PlayerToggleFlightListener;
import me.flame.galantic.utils.GUI;
import me.galantic.galanticcore.api.BungeecordAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.zaxxer.hikari.HikariDataSource;

import me.flame.galantic.combatlogger.listeners.CombatLogEvent;
import me.flame.galantic.commands.gui.listeners.InventoryListener;
import me.flame.galantic.listeners.JoinEventListener;
import me.flame.galantic.listeners.MoveEvent;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.sql.levelSystem.managers.UserLevelManager;
import me.flame.galantic.sql.listeners.UserListener;
import me.flame.galantic.sql.managers.SQLManager;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.FileManager;
import me.flame.galantic.utils.ScoreboardUtils;
import me.galantic.galanticcore.LanguageCombiner;
import me.galantic.galanticcore.LanguageCombiner.Language;
import me.galantic.galanticcore.api.CoreAPI;

public final class Core extends JavaPlugin implements Listener {

    private static Core instance;
    public static HikariDataSource hikari;
    private static SQLManager sqlManager;
    private static UserLevelManager userLevelManager = new UserLevelManager();
    private final SQLUserManager sqlUserManager = new SQLUserManager();
    private final ScoreboardUtils scoreboardUtils = new ScoreboardUtils();
    private final UpgradeManager upgradeManager = new UpgradeManager();

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        FileManager.load(this, "config.yml");

        GUI.loadInventories();

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
        loadMessages();
        upgradeManager.loadUpgrades();
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
        pm.registerEvents(new PlayerToggleFlightListener(), this);
        pm.registerEvents(new GUIInventoryListener(), this);
        pm.registerEvents(new SpawnCommand(), this);
    }

    private void registerCommands() {
        getCommand("stats").setExecutor(new StatisticsCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("combatlog").setExecutor(new CombatLogCommand());
        getCommand("refill").setExecutor(new RefillCommand());
    }

    private void connect() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        String hostName = FileManager.get("config.yml").getString("database.host");
        String[] hostNameAndPort = hostName.split(":");
        String databaseName = FileManager.get("config.yml").getString("database.databasename");
        String databasePassword = FileManager.get("config.yml").getString("database.password");
        String databaseUser = FileManager.get("config.yml").getString("database.username");

        hikari.addDataSourceProperty("serverName", hostNameAndPort[0]);
        hikari.addDataSourceProperty("port", hostNameAndPort[1]);
        hikari.addDataSourceProperty("databaseName", databaseName);
        hikari.addDataSourceProperty("user", databaseUser);
        hikari.addDataSourceProperty("password", databasePassword);
    }

    private void loadMessages() {
        for (String key : Arrays.asList("combat_enabled", "combat_no_active", "combat_disabled", "combat_activated", "combat_logged", "kit_chosen", "spawn_saved", "wrong_usage_spawn", "spawn_teleport",
                "spawn_teleport_target", "spawn_teleport_from", "opening_menu", "opening_menu_target", "kit_received",
                "death_killed_by", "killed_player", "death_void", "level_up", "killstreak", "ended_killstreak", "fly_cannot_enable", "combat_logged_welcome_back", "gui_player_profile_settings_displayname",
                "gui_player_profile_settings_lore", "gui_settings_title", "gui_arrow_back_lore",
                "gui_arrow_back_displayname", "coming_soon",
                "gui_settings_clock_true_displayname", "gui_settings_clock_false_displayname",
                "gui_settings_clock_true_lore", "gui_settings_clock_false_lore",
                "gui_settings_clock_dye_true_displayname", "gui_settings_clock_dye_false_displayname",
                "gui_settings_clock_dye_true_lore", "gui_settings_clock_dye_false_lore",
                "gui_settings_chat_true_displayname", "gui_settings_chat_false_displayname",
                "gui_settings_chat_true_lore", "gui_settings_chat_false_lore", "gui_settings_chat_dye_true_displayname",
                "gui_settings_chat_dye_false_displayname", "gui_settings_chat_dye_true_lore",
                "gui_settings_chat_dye_false_lore", "gui_settings_pm_true_displayname",
                "gui_settings_pm_false_displayname", "gui_settings_pm_true_lore", "gui_settings_pm_false_lore",
                "gui_settings_pm_dye_true_displayname", "gui_settings_pm_dye_false_displayname",
                "gui_settings_pm_dye_true_lore", "gui_settings_pm_dye_false_lore", "gui_settings_fly_true_displayname",
                "gui_settings_fly_false_displayname", "gui_settings_fly_true_lore", "gui_settings_fly_false_lore",
                "gui_settings_fly_dye_true_displayname", "gui_settings_fly_dye_false_displayname",
                "gui_settings_fly_dye_true_lore", "gui_settings_fly_dye_false_lore", "gui_settings_fly_no_perm_lore",
                "gui_settings_fly_no_perm_displayname", "gui_settings_fly_dye_no_perm_lore",
                "gui_settings_fly_dye_no_perm_displayname", "gui_settings_friends_displayname",
                "gui_settings_friends_lore", "gui_settings_friends_dye_displayname", "gui_settings_friends_dye_lore",
                "pm_disabled_sender", "pm_disabled_target", "youtube", "discord", "website", "twitter", "store",
                "gui_serverselector_hub_displayname", "gui_serverselector_hub_lore",
                "gui_serverselector_build_displayname", "gui_serverselector_build_lore",
                "gui_serverselector_dev03_displayname", "gui_serverselector_dev03_lore",
                "gui_serverselector_dev02_displayname", "gui_serverselector_dev02_lore",
                "gui_serverselector_dev01_displayname", "gui_serverselector_dev01_lore",
                "gui_serverselector_oitc_displayname", "gui_serverselector_oitc_lore",
                "gui_serverselector_kitpvp_displayname", "gui_serverselector_kitpvp_lore",
                "gui_serverselector_event_displayname", "gui_serverselector_event_lore",
                "inventory_player_selector_displayname", "inventory_server_selector_displayname",
                "inventory_player_profile_displayname", "gui_player_profile_language_displayname",
                "gui_player_profile_language_lore", "gui_player_profile_language_english_displayname",
                "gui_player_profile_language_english_lore", "gui_player_profile_language_netherlands_displayname",
                "gui_player_profile_language_netherlands_lore", "gui_player_profile_title",
                "gui_serverselector_title", "no_kit_permission", "warrior_kit_lore", "archer_kit_lore", "tank_kit_lore",
                "axe_kit_lore", "ninja_kit_lore", "vip_kit_lore", "elite_kit_lore", "hero_kit_lore",
                "god_kit_lore", "custom_kit_lore", "warrior_upgrade_lore", "archer_upgrade_lore", "tank_upgrade_lore",
                "axe_upgrade_lore", "ninja_upgrade_lore" , "wood_upgrade_lore", "healer_upgrade_lore", "rogue_upgrade_lore",
                "knight_upgrade_lore", "assassin_upgrade_lore", "go_back_arrow", "kit_upgrade", "refill_not_enough_coins",
                "spawn_cooldown", "refill_completed", "refill_cooldown", "spawn_teleport_canceld")) {
            CoreAPI.getMessageManager().addMessage(key,
                    LanguageCombiner.builder().addMessage(Language.DUTCH, "Key: " + key)
                            .addMessage(Language.ENGLISH, "Key: " + key).build());
        }
    }
}
