package me.flame.galantic.commands.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.objects.IUser;
import me.galantic.galanticcore.objects.User;
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

        IUser user = CoreAPI.getUserManager().getUser(p.getUniqueId());

        selector.setItem(20, new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&bWarrior")
                .setLore(false, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                                "warrior_kit_lore")).build());

        selector.setItem(21, new ItemBuilder(Material.BOW, 1).setDisplayName("&bArcher")
                .setLore(false, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "archer_kit_lore")).build());

        selector.setItem(22, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&bTank")
                .setLore(false, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "tank_kit_lore")).build());

        selector.setItem(23, new ItemBuilder(Material.IRON_AXE, 1).setDisplayName("&bAxe")
                .setLore(false, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "axe_kit_lore")).build());

        selector.setItem(24, new ItemBuilder(Material.POTION, 1, (byte) 8194).setDisplayName("&bNinja")
                .setLore(false, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "ninja_kit_lore")).setItemFlag(ItemFlag.HIDE_POTION_EFFECTS).build());

        selector.setItem(29, new ItemBuilder(Material.BOW, 1).setDisplayName("&b&lVIP")
                .setLore(true, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "vip_kit_lore")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());


        selector.setItem(30, new ItemBuilder(Material.FISHING_ROD, 1).setDisplayName("&b&lElite")
                .setLore(true, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "elite_kit_lore")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        selector.setItem(31, new ItemBuilder(Material.POTION, 1, (byte) 8259).setDisplayName("&b&lHero")
                .setLore(true, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "hero_kit_lore")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).setItemFlag(ItemFlag.HIDE_POTION_EFFECTS).build());

        selector.setItem(32, new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName("&b&lGod")
                .setLore(true, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "god_kit_lore")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        selector.setItem(33, new ItemBuilder(Material.SLIME_BALL, 1).setDisplayName("&b&lCustom")
                .setLore(true, CoreAPI.getMessageManager().getMessage( user.getLanguage(),
                        "custom_kit_lore")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        selector.setContents(selector.getContents());
        p.openInventory(selector);

        return selector;
    }
}
