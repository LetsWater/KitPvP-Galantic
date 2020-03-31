package me.flame.galantic.kits.upgradeKits.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.kits.upgradeKits.managers.UpgradeManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.objects.IUser;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.UUID;

public class KitUpgradeMenu {

    public Inventory openNormalUpgradeMenuGUI(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);
        IUser user = CoreAPI.getUserManager().getUser(uuid);

        Inventory inventory = Bukkit.createInventory(null, 45, ChatUtils.format("&aKit upgrades"));

        for (SQLUser SQLuser : SQLUserManager.userList) {
            if (SQLuser.getUuid() == uuid) {

                inventory.setItem(11, new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&bWarrior")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "warrior_upgrade_lore", SQLuser.getWarrior_level(), (SQLuser.getWarrior_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getWarrior_level() + 1), (SQLuser.getWarrior_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (SQLuser.getWarrior_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (SQLuser.getWarrior_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (SQLuser.getWarrior_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getWarrior_level() > 0 ? "&a✔" : "&c✘", SQLuser.getWarrior_level() >= 5 || SQLuser.getWarrior_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("warrior", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).build());
                inventory.setItem(12, new ItemBuilder(Material.BOW, 1).setDisplayName("&bArcher")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "archer_upgrade_lore", SQLuser.getArcher_level(), (SQLuser.getArcher_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getArcher_level() + 1), (SQLuser.getArcher_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("archer", (SQLuser.getArcher_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("archer", (SQLuser.getArcher_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("archer", (SQLuser.getArcher_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getArcher_level() > 0 ? "&a✔" : "&c✘", SQLuser.getArcher_level() >= 5 || SQLuser.getArcher_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("archer", SQLuser.getArcher_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).build());
                inventory.setItem(13, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&bTank")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "tank_upgrade_lore", SQLuser.getTank_level(), (SQLuser.getTank_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getTank_level() + 1), (SQLuser.getTank_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("tank", (SQLuser.getTank_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("tank", (SQLuser.getTank_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("tank", (SQLuser.getTank_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getTank_level() > 0 ? "&a✔" : "&c✘", SQLuser.getTank_level() >= 5 || SQLuser.getTank_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("tank", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).build());
                inventory.setItem(14, new ItemBuilder(Material.IRON_AXE, 1).setDisplayName("&bAxe")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "axe_upgrade_lore", SQLuser.getAxe_level(), (SQLuser.getAxe_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getAxe_level() + 1), (SQLuser.getAxe_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("axe", (SQLuser.getAxe_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("axe", (SQLuser.getAxe_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("axe", (SQLuser.getAxe_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getAxe_level() > 0 ? "&a✔" : "&c✘", SQLuser.getAxe_level() >= 5 || SQLuser.getAxe_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("axe", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).build());
                inventory.setItem(15, new ItemBuilder(Material.POTION, 1, (byte) 8194).setDisplayName("&bNinja")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "ninja_upgrade_lore", SQLuser.getNinja_level(), (SQLuser.getNinja_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getNinja_level() + 1), (SQLuser.getNinja_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("ninja", (SQLuser.getNinja_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("ninja", (SQLuser.getNinja_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("ninja", (SQLuser.getNinja_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getNinja_level() > 0 ? "&a✔" : "&c✘", SQLuser.getTank_level() >= 5 || SQLuser.getTank_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("tank", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).setItemFlag(ItemFlag.HIDE_POTION_EFFECTS).build());

                inventory.setItem(20, new ItemBuilder(Material.BOW, 1).setDisplayName("&bHood")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "hood_upgrade_lore", SQLuser.getHood_level(), (SQLuser.getHood_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getHood_level() + 1), (SQLuser.getHood_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("hood", (SQLuser.getHood_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("hood", (SQLuser.getHood_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("hood", (SQLuser.getAxe_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getHood_level() > 0 ? "&a✔" : "&c✘", SQLuser.getHood_level() >= 5 || SQLuser.getHood_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("hood", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

                inventory.setItem(21, new ItemBuilder(Material.DIAMOND_AXE, 1).setDisplayName("&bHealer") .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "healer_upgrade_lore", SQLuser.getHealer_level(), (SQLuser.getHealer_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getHealer_level() + 1), (SQLuser.getHealer_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("healer", (SQLuser.getHealer_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("healer", (SQLuser.getHealer_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("healer", (SQLuser.getHealer_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getHealer_level() > 0 ? "&a✔" : "&c✘", SQLuser.getWarrior_level() >= 5 || SQLuser.getHealer_level() < 1 || SQLuser.getHealer_level() < UpgradeManager.getInstance().getLevelUpgradeCost("healer", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(22, new ItemBuilder(Material.FISHING_ROD, 1).setDisplayName("&bRogue") .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "rogue_upgrade_lore", SQLuser.getRogue_level(), (SQLuser.getRogue_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getRogue_level() + 1), (SQLuser.getRogue_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("rogue", (SQLuser.getRogue_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("rogue", (SQLuser.getRogue_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("rogue", (SQLuser.getRogue_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getRogue_level() > 0 ? "&a✔" : "&c✘", SQLuser.getRogue_level() >= 5 || SQLuser.getRogue_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("rogue", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(23, new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&bKnight") .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "knight_upgrade_lore", SQLuser.getKnight_level(), (SQLuser.getKnight_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getKnight_level() + 1), (SQLuser.getKnight_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("knight", (SQLuser.getKnight_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("knight", (SQLuser.getKnight_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("knight", (SQLuser.getKnight_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getKnight_level() > 0 ? "&a✔" : "&c✘", SQLuser.getKnight_level() >= 5 || SQLuser.getKnight_level() < 1 || SQLuser.getPvpCoins() < UpgradeManager.getInstance().getLevelUpgradeCost("knight", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(24, new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(Color.fromRGB(255,102, 255)).setDisplayName("&bAssassin") .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "assassin_upgrade_lore", SQLuser.getAssassin_level(), (SQLuser.getAssassin_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getAssassin_level() + 1), (SQLuser.getAssassin_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("assassin", (SQLuser.getAssassin_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("assassin", (SQLuser.getAssassin_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("assassin", (SQLuser.getAssassin_level() + 1)) - SQLuser.getPvpCoins(), SQLuser.getAssassin_level() > 1 ? "&a✔" : "&c✘", SQLuser.getWarrior_level() >= 5 || SQLuser.getAssassin_level() < 1 || SQLuser.getAssassin_level() < UpgradeManager.getInstance().getLevelUpgradeCost("assassin", SQLuser.getWarrior_level() + 1) ? CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_not_upgrade_kit") : CoreAPI.getMessageManager().getSingleMessage(user.getLanguage(), "can_upgrade_kit"))).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());


                inventory.setItem(36, new ItemBuilder(Material.ARROW, 1).setDisplayName(String.valueOf(CoreAPI.getMessageManager().getMessage(user.getLanguage(), "go_back_arrow_name"))).setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(), "go_back_arrow_lore")).build());
                break;
            }
        }

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
