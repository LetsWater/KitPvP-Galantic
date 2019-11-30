package me.flame.galantic.kits.upgradeKits.listeners;

import me.flame.galantic.kits.upgradeKits.gui.DonatorUpgradeMenuGUI;
import me.flame.galantic.kits.upgradeKits.gui.NormalUpgradeMenuGUI;
import me.flame.galantic.kits.upgradeKits.gui.UpgradeMenuGUI;
import me.flame.galantic.kits.upgradeKits.managers.UpgradeManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GUIInventoryListener implements Listener {

    final private UpgradeMenuGUI upgradeMenuGUI = new UpgradeMenuGUI();
    final private NormalUpgradeMenuGUI normalUpgradeMenuGUI = new NormalUpgradeMenuGUI();
    final private DonatorUpgradeMenuGUI donatorUpgradeMenuGUI = new DonatorUpgradeMenuGUI();

    @EventHandler
    public void InvClickEvent(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aKit Upgrades"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.IRON_SWORD) {
                normalUpgradeMenuGUI.openNormalUpgradeMenuGUI(p.getUniqueId());
            }
            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                donatorUpgradeMenuGUI.openDonatorUpgradeMenu(p.getUniqueId());
            }
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aNormal Upgrades"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.ARROW) {
                upgradeMenuGUI.upgradeMenuGUI(p.getUniqueId());
            }
            for (SQLUser user : SQLUserManager.userList) {
                if (user.getUuid() == p.getUniqueId()) {
                    if (e.getSlot() == 11) {
                        if ((user.getWarrior_level() + 1) >= 6) {
                            e.setCancelled(true);
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (user.getWarrior_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "warrior", (user.getWarrior_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                }
            }
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aDonator Upgrades"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.ARROW) {
                upgradeMenuGUI.upgradeMenuGUI(p.getUniqueId());
            }
        }
    }

    @EventHandler
    public void clickEvent(final PlayerInteractEvent e) {

        Player p = e.getPlayer();
        if (e.getItem() == null) return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getItem().getType() == Material.EMERALD) {
                upgradeMenuGUI.upgradeMenuGUI(p.getUniqueId());
            }
        }
    }
}
