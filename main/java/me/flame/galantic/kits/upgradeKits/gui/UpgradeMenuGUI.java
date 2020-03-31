package me.flame.galantic.kits.upgradeKits.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.UUID;

public class UpgradeMenuGUI {

    public Inventory upgradeMenuGUI(UUID uuid){
        Player p = Bukkit.getServer().getPlayer(uuid);

        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aKit Upgrades"));

        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){

                inventory.setItem(11, new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&aKit Upgrades").setLore("lore").build());
                inventory.setItem(13, new ItemBuilder(Material.GOLD_NUGGET, 1).setDisplayName("&7Coins: &a" + user.getPvpCoins()).setLore("lore").build());
                inventory.setItem(15, new ItemBuilder(Material.ANVIL, 1).setDisplayName("&aKit Shop")
                        .setLore("lore").build());

                break;
            }
        }

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
