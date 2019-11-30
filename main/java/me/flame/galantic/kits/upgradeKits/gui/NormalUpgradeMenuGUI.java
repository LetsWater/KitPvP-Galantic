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
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class NormalUpgradeMenuGUI {

    public Inventory openNormalUpgradeMenuGUI(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);
        IUser user = CoreAPI.getUserManager().getUser(uuid);

        Inventory inventory = Bukkit.createInventory(null , 27, ChatUtils.format("&aNormal Upgrades"));

        for(SQLUser SQLuser : SQLUserManager.userList){
            if(user.getUuid() == uuid){


                inventory.setItem(11, new ItemBuilder(Material.IRON_HELMET, 1).setDisplayName("&bWarrior")
                        .setLore(CoreAPI.getMessageManager().getMessage(user.getLanguage(),
                                "warrior_upgrade_lore", SQLuser.getWarrior_level(), (SQLuser.getWarrior_level() + 1) >= 6 ? ChatUtils.format("&cMax Level") : (SQLuser.getWarrior_level() + 1), (SQLuser.getWarrior_level() + 1) >= 6 ? "&c0" : UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (SQLuser.getWarrior_level() + 1)), SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (SQLuser.getWarrior_level() + 1)) ? "&a✔" : "&c✘", UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (SQLuser.getWarrior_level() + 1)) - SQLuser.getPvpCoins(), "&a✔")).build());
                inventory.setItem(12, new ItemBuilder(Material.BOW, 1).setDisplayName("&bArcher").setLore("lore").build());
                inventory.setItem(13, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&bTank").setLore("lore").build());
                inventory.setItem(14, new ItemBuilder(Material.IRON_AXE, 1).setDisplayName("&bAxe").setLore("lore").build());
                inventory.setItem(15, new ItemBuilder(Material.POTION, 1, (byte) 8194).setLore("&bNinja").setLore("lore").build());

                inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aGo Back").setLore("lore").build());
            }
        }

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
