package me.flame.galantic.kits.upgradeKits.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.kits.upgradeKits.managers.UpgradeManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.objects.IUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class KitShopMenu {

    public Inventory openKitShopMenu(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);
        IUser user = CoreAPI.getUserManager().getUser(uuid);

        Inventory inventory = Bukkit.createInventory(null, 36, ChatUtils.format("&aKit Shop"));

        for(SQLUser SQLUser : SQLUserManager.userList){
            if(SQLUser.getUuid() == uuid){

                inventory.setItem(11, new ItemBuilder(Material.BOW, 1).setDisplayName("&bHood").setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "hood_unlock_lore", UpgradeManager.getInstance().getLevelUpgradeCost("hood", 1), UpgradeManager.getInstance().getLevelUpgradeCost("hood", 1) - SQLUser.getPvpCoins() <= 0 ? "0" : UpgradeManager.getInstance().getLevelUpgradeCost("hood", 1) - SQLUser.getPvpCoins(), SQLUser.getHood_level() > 0 ? "&a✔" : "&c✘")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(12, new ItemBuilder(Material.DIAMOND_AXE, 1).setDisplayName("&bHealer").setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "healer_unlock_lore", UpgradeManager.getInstance().getLevelUpgradeCost("healer", 1), UpgradeManager.getInstance().getLevelUpgradeCost("healer", 1) - SQLUser.getPvpCoins() <= 0 ? "0" : UpgradeManager.getInstance().getLevelUpgradeCost("healer", 1) - SQLUser.getPvpCoins(), SQLUser.getHealer_level() > 0 ? "&a✔" : "&c✘")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(13, new ItemBuilder(Material.FISHING_ROD, 1).setDisplayName("&bRogue").setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "rogue_unlock_lore", UpgradeManager.getInstance().getLevelUpgradeCost("rogue", 1), UpgradeManager.getInstance().getLevelUpgradeCost("rogue", 1) - SQLUser.getPvpCoins() <= 0 ? "0" : UpgradeManager.getInstance().getLevelUpgradeCost("rogue", 1) - SQLUser.getPvpCoins(), SQLUser.getRogue_level() > 0 ? "&a✔" : "&c✘")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(14, new ItemBuilder(Material.GOLD_SWORD, 1).setDisplayName("&bKnight").setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "knight_unlock_lore", UpgradeManager.getInstance().getLevelUpgradeCost("knight", 1), UpgradeManager.getInstance().getLevelUpgradeCost("knight", 1) - SQLUser.getPvpCoins() <= 0 ? "0" : UpgradeManager.getInstance().getLevelUpgradeCost("knight", 1) - SQLUser.getPvpCoins(), SQLUser.getKnight_level() > 0 ? "&a✔" : "&c✘")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());
                inventory.setItem(15, new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setDisplayName("&bAssassin").setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                        "assassin_unlock_lore", UpgradeManager.getInstance().getLevelUpgradeCost("assassin", 1), UpgradeManager.getInstance().getLevelUpgradeCost("assassin", 1) - SQLUser.getPvpCoins() <= 0 ? "0" : UpgradeManager.getInstance().getLevelUpgradeCost("assassin", 1) - SQLUser.getPvpCoins(), SQLUser.getAssassin_level() > 0 ? "&a✔" : "&c✘")).setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

                inventory.setItem(27, new ItemBuilder(Material.ARROW, 1).setDisplayName(String.valueOf(CoreAPI.getMessageManager().getMessage(user.getLanguage(), "go_back_arrow_name"))).setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(), "go_back_arrow_lore")).build());

                break;
            }
        }

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
