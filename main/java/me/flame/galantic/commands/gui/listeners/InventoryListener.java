package me.flame.galantic.commands.gui.listeners;

import me.flame.galantic.commands.gui.KitSelectorGUI;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
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

        if(e.getSlotType() == InventoryType.SlotType.ARMOR){
            e.setCancelled(true);
        }

        Player p = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getName().contains(ChatUtils.format("&a&lStatistics &8»"))) {
            e.setCancelled(true);
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&9Kit Selector"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() != Material.AIR) {
                String kitName = e.getCurrentItem().getItemMeta().getDisplayName().replace("§9", "").replaceAll("§l", "").toLowerCase();
                for (SQLUser user : SQLUserManager.userList) {
                    //if (p.hasPermission("kitpvp.kit." + kitName) || kitName.equals("warrior")) {
                        if (user.getUuid() == p.getUniqueId()) {
                            user.setUsing_kit(kitName);

                            p.sendMessage(kitName + " gekozen!");
                            p.closeInventory();
                            break;
                        }
                    //} else {
                    //    p.sendMessage(ChatUtils.format("Geen permissions voor " + kitName));
                    //    p.closeInventory();
                    //    break;
                    //}
                }
            }
        }

        if (e.getClickedInventory().getType() == InventoryType.PLAYER && p.getInventory().getHelmet() == null) {
            if (p.getGameMode() == GameMode.ADVENTURE) {
                e.setCancelled(true);
            }
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
            }
        }
    }

}
