package me.flame.galantic.adminpanel.commands.gui;

import me.flame.galantic.Core;
import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import me.flame.galantic.adminpanel.utils.Runnables;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.objects.IUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class XPBoosterGUI {

    public static Inventory openXPBoosterGUI(Player p) {

        IUser user = CoreAPI.getUserManager().getUser(p.getUniqueId());

        Inventory coinsBoosterInventory = Bukkit.createInventory(null, 45, ChatUtils.format("&6XP Booster"));

        coinsBoosterInventory.setItem(10, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.1 Booster").build());
        coinsBoosterInventory.setItem(11, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.2 Booster").build());
        coinsBoosterInventory.setItem(12, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.3 Booster").build());
        coinsBoosterInventory.setItem(13, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.4 Booster").build());
        coinsBoosterInventory.setItem(14, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.5 Booster").build());
        coinsBoosterInventory.setItem(15, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.6 Booster").build());
        coinsBoosterInventory.setItem(16, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.7 Booster").build());
        coinsBoosterInventory.setItem(20, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.8 Booster").build());
        coinsBoosterInventory.setItem(21, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n1.9 Booster").build());
        coinsBoosterInventory.setItem(22, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n2.0 Booster").build());
        coinsBoosterInventory.setItem(23, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n2.5 Booster").build());
        coinsBoosterInventory.setItem(24, new ItemBuilder(Material.EXP_BOTTLE, 1).setDisplayName("&e&n3.0 Booster").build());

        coinsBoosterInventory.setItem(40, new ItemBuilder(Material.BOOK, 1).setDisplayName("&e&nCurrent Booster")
                .setLore("&7"
                        , "&7Current booster: &e" + (AdminPanelManager.getInstance().getXpBooster() > 1 ? AdminPanelManager.getInstance().getXpBooster() : "&cNone")
                        , "&7Changed by: &e" + AdminPanelManager.getInstance().getLastChangerXpBooster()
                        , "&7Time left: " + (AdminPanelManager.getInstance().getBooster() > 1 ? "&e" + Runnables.minutesList.get("xp") + " &fminutes, &e" + Runnables.secondsList.get("xp") + " &fseconds." : "&cNone")
                        , "&7"
                        , AdminPanelManager.getInstance().getXpBooster() > 1 ? "&cClick here to end this booster." : "&eThere isn't an active booster.").build());

        coinsBoosterInventory.setItem(44, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&e&nCoins Booster").build());

        coinsBoosterInventory.setItem(36, new ItemBuilder(Material.ARROW, 1).setDisplayName(String.valueOf(CoreAPI.getMessageManager().getMessage(user.getLanguage(), "go_back_arrow_name"))).build());


        new BukkitRunnable() {

            public void run() {
                coinsBoosterInventory.setItem(40, new ItemBuilder(Material.BOOK, 1).setDisplayName("&e&nCurrent Booster")
                        .setLore("&7"
                                , "&7Current booster: &e" + (AdminPanelManager.getInstance().getXpBooster() > 1 ? AdminPanelManager.getInstance().getXpBooster() : "&cNone")
                                , "&7Changed by: &e" + AdminPanelManager.getInstance().getLastChangerXpBooster()
                                , "&7Time left: " + (AdminPanelManager.getInstance().getBooster() > 1 ? "&e" + Runnables.minutesList.get("xp") + " &fminutes, &e" + Runnables.secondsList.get("xp") + " &fseconds." : "&cNone")
                                , "&7"
                                , AdminPanelManager.getInstance().getXpBooster() > 1 ? "&cClick here to end this booster." : "&eThere isn't an active booster.").build());

            }
        }.runTaskTimer(Core.getInstance(), 1 * 20, 1 * 20);

        coinsBoosterInventory.setContents(coinsBoosterInventory.getContents());
        p.openInventory(coinsBoosterInventory);

        return coinsBoosterInventory;
    }
}
