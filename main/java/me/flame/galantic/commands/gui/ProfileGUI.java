package me.flame.galantic.commands.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class ProfileGUI {

    public Inventory openProfileGUI(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        Inventory profileGUI = Bukkit.createInventory(null, 64, ChatUtils.format("&9Profile"));

        profileGUI.setContents(profileGUI.getContents());
        p.openInventory(profileGUI);

        profileGUI.setItem(22, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setDisplayName("&aProfile").setSkullOwner(p.getName()).build());
        profileGUI.setItem(30, new ItemBuilder(Material.EMERALD, 1).setDisplayName("&aBoosters").build());
        profileGUI.setItem(31, new ItemBuilder(Material.WRITTEN_BOOK, 1).setDisplayName("&aSettings").build());
        profileGUI.setItem(32, new ItemBuilder(Material.BREWING_STAND, 1).setDisplayName("&aLeveling Rewards").build());
        profileGUI.setItem(36, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setDisplayName("&aLanguage Selector").build());

        return profileGUI;
    }
}
