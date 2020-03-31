package me.flame.galantic.adminpanel.commands.gui;

import me.flame.galantic.Core;
import me.flame.galantic.adminpanel.AdminPanel;
import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import me.flame.galantic.adminpanel.utils.Runnables;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class AdminpanelGUI {

    public static Inventory openAdminPanel(Player p) {

        Inventory adminPanel = Bukkit.createInventory(null, 27, ChatUtils.format("&cAdmin Panel"));


        adminPanel.setItem(4, new ItemBuilder(Material.PAPER, 1).setDisplayName("&cWelkom " + p.getName() + ",").build());
        adminPanel.setItem(11, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&f&nCoins Booster")
                .setLore("&7"
                        , "&7Current booster: &e" + (AdminPanelManager.getInstance().getBooster() > 1 ? AdminPanelManager.getInstance().getBooster() : "&cNone")
                        , "&7Changed by: &e" + AdminPanelManager.getInstance().getLastChangerCoinsBooster()
                        , "&7Time left: " + (AdminPanelManager.getInstance().getBooster() > 1 ? "&e" + Runnables.minutesList.get("coins") + " &fminutes, &e" + Runnables.secondsList.get("coins") + " &fseconds." : "&cNone")
                        , "&7"
                        , "&cClick here to change the coins booster.").build());
        adminPanel.setItem(22, new ItemBuilder(Material.BOOK, 1).setDisplayName("&f&nPermissions")
                .setLore("&7"
                        , "&7Permissions enabled: " + (AdminPanelManager.getInstance().getPermissions() ? "&atrue" : "&cfalse")
                        , "&7Last changed by: &e" + AdminPanelManager.getInstance().getLastChangerPerms()
                        , "&7"
                        , "&7Click here to change the"
                        , "&7permissions use to " + (!AdminPanelManager.getInstance().getPermissions() ? "&atrue" : "&cfalse") + "&7.").build());
        adminPanel.setItem(15, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&f&nXP Booster")
                .setLore("&7"
                        , "&7Current booster: &e" + AdminPanelManager.getInstance().getXpBooster()
                        , "&7Last changed by: &e" + AdminPanelManager.getInstance().getLastChangerXpBooster()
                        , "&7Time left: " + (AdminPanelManager.getInstance().getXpBooster() > 1 ? "&e" + Runnables.minutesList.get("xp") + " &fminutes, &e" + Runnables.secondsList.get("xp") + " &fseconds." : "&cNone")
                        , "&7"
                        , "&cClick here to change the XP booster.").build());


        new BukkitRunnable(){

            public void run(){
                adminPanel.setItem(11, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&f&nCoins Booster")
                        .setLore("&7"
                                , "&7Current booster: &e" + (AdminPanelManager.getInstance().getBooster() > 1 ? AdminPanelManager.getInstance().getBooster() : "&cNone")
                                , "&7Changed by: &e" + AdminPanelManager.getInstance().getLastChangerCoinsBooster()
                                , "&7Time left: " + (AdminPanelManager.getInstance().getBooster() > 1 ? "&e" + Runnables.minutesList.get("coins") + " &fminutes, &e" + Runnables.secondsList.get("coins") + " &fseconds." : "&cNone")
                                , "&7"
                                , "&cClick here to change the coins booster.").build());

                adminPanel.setItem(15, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&f&nXP Booster")
                        .setLore("&7"
                                , "&7Current booster: &e" + AdminPanelManager.getInstance().getXpBooster()
                                , "&7Last changed by: &e" + AdminPanelManager.getInstance().getLastChangerXpBooster()
                                , "&7Time left: " + (AdminPanelManager.getInstance().getXpBooster() > 1 ? "&e" + Runnables.minutesList.get("xp") + " &fminutes, &e" + Runnables.secondsList.get("xp") + " &fseconds." : "&cNone")
                                , "&7"
                                , "&cClick here to change the XP booster.").build());
            }
        }.runTaskTimer(Core.getInstance(), 1 * 20, 1 * 20);


        adminPanel.setContents(adminPanel.getContents());
        p.openInventory(adminPanel);

        return adminPanel;
    }
}
