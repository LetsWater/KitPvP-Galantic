package me.flame.galantic.adminpanel.commands.gui;

import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.objects.IUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TimeChooseGUI {

    public static Inventory openTimeChooseGUI(Player p, String kind) {

        IUser user = CoreAPI.getUserManager().getUser(p.getUniqueId());

        Inventory coinsBoosterInventory = Bukkit.createInventory(null, 45, ChatUtils.format("&6Time Selector - " + kind + ""));

        coinsBoosterInventory.setItem(10, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n5 Minutes").build());
        coinsBoosterInventory.setItem(11, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n10 Minutes").build());
        coinsBoosterInventory.setItem(12, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n15 Minutes").build());
        coinsBoosterInventory.setItem(13, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n20 Minutes").build());
        coinsBoosterInventory.setItem(14, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n25 Minutes").build());
        coinsBoosterInventory.setItem(15, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n30 Minutes").build());
        coinsBoosterInventory.setItem(16, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n35 Minutes").build());
        coinsBoosterInventory.setItem(20, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n40 Minutes").build());
        coinsBoosterInventory.setItem(21, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n45 Minutes").build());
        coinsBoosterInventory.setItem(22, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n50 Minutes").build());
        coinsBoosterInventory.setItem(23, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n55 Minutes").build());
        coinsBoosterInventory.setItem(24, new ItemBuilder(Material.WATCH, 1).setDisplayName("&e&n60 Minutes").build());

        coinsBoosterInventory.setItem(36, new ItemBuilder(Material.ARROW, 1).setDisplayName(String.valueOf(CoreAPI.getMessageManager().getMessage(user.getLanguage(), "go_back_arrow_name"))).build());

        coinsBoosterInventory.setContents(coinsBoosterInventory.getContents());
        p.openInventory(coinsBoosterInventory);

        return coinsBoosterInventory;
    }
}
