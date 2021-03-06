package me.flame.galantic.commands.gui.listeners;

import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import me.flame.galantic.commands.gui.KitSelectorGUI;
import me.flame.galantic.commands.gui.utils.CheckPermissions;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.GUI;
import me.galantic.galanticcore.api.CoreAPI;

import me.galanticmc.hub.HubInventory;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryListener implements Listener {

    private final KitSelectorGUI kitSelectorGUI = new KitSelectorGUI();

    @EventHandler
    public void onClick(final InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        if (e.getSlotType() == InventoryType.SlotType.ARMOR) {
            e.setCancelled(true);
        }

        Player p = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getName().contains(ChatUtils.format("&a&lStats &8»"))) {
            e.setCancelled(true);
            return;
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&9Kit Selector"))) {
            String kitName = e.getCurrentItem().getItemMeta().getDisplayName().replace("§b", "").replaceAll("§l", "").toLowerCase();
            if (AdminPanelManager.getInstance().getPermissions() == false) {
                for (SQLUser user : SQLUserManager.userList) {
                    if (user.getUuid() == p.getUniqueId()) {
                        user.setUsing_kit(kitName);

                        CoreAPI.getMessageManager().sendMessage(p, "kit_chosen", kitName);
                        p.closeInventory();
                        break;
                    }
                    break;
                }
            } else {
                for(SQLUser user : SQLUserManager.userList){
                    if (CheckPermissions.CheckPermissions(p.getUniqueId(), kitName)) {
                        if (user.getUuid() == p.getUniqueId()) {
                            user.setUsing_kit(kitName);

                            CoreAPI.getMessageManager().sendMessage(p, "kit_chosen", kitName);
                            p.closeInventory();
                            break;
                        }
                    } else {
                        CoreAPI.getMessageManager().sendMessage(p, "kit_not_unlocked", kitName);
                        p.closeInventory();
                        break;
                    }
                }
            }
            e.setCancelled(true);
            return;
        }

        if (e.getClickedInventory().getType() == InventoryType.PLAYER && p.getInventory().getHelmet() == null) {
            if (p.getGameMode() == GameMode.ADVENTURE) {
                e.setCancelled(true);
            }
            return;
        }

        if (p.getGameMode() == GameMode.ADVENTURE && p.getInventory().

                getHelmet() == null) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void rightClick(final PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null) return;

        if (p.getGameMode() == GameMode.ADVENTURE && p.getInventory().getHelmet() == null) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (e.getItem().getType() == Material.ARMOR_STAND) {
                    kitSelectorGUI.kitSelector(p.getUniqueId());
                }

                if (e.getItem().getType() == Material.COMPASS) {
                    GUI.SERVER_SELECTOR.openInventory(p);
                }

                if (e.getItem().getType() == Material.SKULL_ITEM) {
                    GUI.PLAYER_PROFILE.openInventory(p);
                }

                if (e.getItem().getType() == Material.CHEST) {
                    p.sendMessage(ChatUtils.format("&cComing soon"));
                }
            }
        }
    }

}
