package me.flame.galantic.commands.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.UUID;

public class KitSelectorGUI {

    public Inventory kitSelector(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);

        Inventory selector = Bukkit.createInventory(null, 54, ChatUtils.format("&9Kit Selector"));

        selector.setItem(20, new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&9Warrior")
                .setLore(false ,""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").build());

        selector.setItem(21, new ItemBuilder(Material.BOW, 1).setDisplayName("&9Archer")
                .setLore(false, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").build());

        selector.setItem(22, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&9Tank")
                .setLore(false, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").build());

        selector.setItem(23, new ItemBuilder(Material.IRON_AXE, 1).setDisplayName("&9Axe")
                .setLore(false, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").build());

        selector.setItem(24, new ItemBuilder(Material.POTION, 1, (byte) 8194).setDisplayName("&9Ninja")
                .setLore(false, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").setItemFlag(ItemFlag.HIDE_POTION_EFFECTS).build());

        selector.setItem(29, new ItemBuilder(Material.BOW, 1).setDisplayName("&9&lVIP")
                .setLore(true, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());


        selector.setItem(30, new ItemBuilder(Material.FISHING_ROD, 1).setDisplayName("&9&lElite")
                .setLore(true, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        selector.setItem(31, new ItemBuilder(Material.POTION, 1, (byte) 8259).setDisplayName("&9&lHero")
                .setLore(true, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).setItemFlag(ItemFlag.HIDE_POTION_EFFECTS).build());

        selector.setItem(32, new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName("&9&lGod")
                .setLore(true, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        selector.setItem(33, new ItemBuilder(Material.SLIME_BALL, 1).setDisplayName("&9&lCustom")
                .setLore(true, ""
                        , "&7&oRight click to select the kit."
                        , "&7&oLeft click to view the kit.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        selector.setContents(selector.getContents());
        p.openInventory(selector);

        return selector;
    }
}
