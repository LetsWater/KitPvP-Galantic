package me.flame.galantic.kits.upgradeKits.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DonatorUpgradeMenuGUI {

    public Inventory openDonatorUpgradeMenu(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aDonator Upgrades"));

        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){

                inventory.setItem(11, new ItemBuilder(Material.BOW, 1).setDisplayName("&bVIP").setLore("lore").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(12, new ItemBuilder(Material.FISHING_ROD, 1).setDisplayName("&bElite").setLore("lore").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(13, new ItemBuilder(Material.POTION, 1, (byte) 8259).setDisplayName("&bHero").setLore("lore").setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName("&bGod").setLore("lore").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(15, new ItemBuilder(Material.SLIME_BALL, 1).setDisplayName("&bCustom").setLore("lore").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

                inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aGo Back").setLore("lore").build());
            }
        }

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
