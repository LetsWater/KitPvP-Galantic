package me.flame.galantic.adminpanel.commands.gui.listeners;

import me.flame.galantic.Core;
import me.flame.galantic.adminpanel.AdminPanel;
import me.flame.galantic.adminpanel.commands.gui.AdminpanelGUI;
import me.flame.galantic.adminpanel.commands.gui.CoinsBoosterGUI;
import me.flame.galantic.adminpanel.commands.gui.TimeChooseGUI;
import me.flame.galantic.adminpanel.commands.gui.XPBoosterGUI;
import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import me.flame.galantic.adminpanel.utils.Runnables;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class InventoryListenerAdmin implements Listener {

    public static HashMap<UUID, Double> CoinsBooster = new HashMap<>();
    public static HashMap<UUID, Double> XPBooster = new HashMap<>();

    @EventHandler
    public void onClick(final InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        if (e.getSlotType() == InventoryType.SlotType.ARMOR) {
            e.setCancelled(true);
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&cAdmin Panel"))) {
            Player p = (Player) e.getWhoClicked();
            if (e.getSlot() == 22) {
                if (AdminPanelManager.getInstance().getPermissions()) {
                    AdminPanelManager.getInstance().setPermissions(false, p);
                    p.closeInventory();

                    return;
                }
                AdminPanelManager.getInstance().setPermissions(true, p);
                p.closeInventory();

                return;
            } if(e.getSlot() == 11){
                CoinsBoosterGUI.openCoinsBoosterGUI(p);
                e.setCancelled(true);
                return;
            } if(e.getSlot() == 15){
                XPBoosterGUI.openXPBoosterGUI(p);
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
        }


        if(e.getClickedInventory().getName().contains(ChatUtils.format("&6Coins Booster"))  && (!e.getClickedInventory().getName().contains(ChatUtils.format("&6Coins Booster Time")))){
            Player p = (Player) e.getWhoClicked();
            String[] splittedName = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§e§n", "").split(" ");
            String itemName = splittedName[0];

            if(e.getSlot() == 36){
                AdminpanelGUI.openAdminPanel(p);
                e.setCancelled(true);
                return;
            }

            if(e.getSlot() == 40){
                if(AdminPanelManager.getInstance().getBooster() > 1 ){

                    Runnables.endBooster("coins", p);

                    AdminpanelGUI.openAdminPanel(p);
                    e.setCancelled(true);
                    return;
                }
                e.setCancelled(true);
            }

            if(e.getSlot() == 44){
                XPBoosterGUI.openXPBoosterGUI(p);
                e.setCancelled(true);
                return;
            }

            if(Runnables.runnable.containsKey("coins")){
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatUtils.format("&cThere is already a Coins booster running."));
                return;
            } else {

                TimeChooseGUI.openTimeChooseGUI(p, "Coins");
                CoinsBooster.put(p.getUniqueId(), Double.valueOf(itemName));

                e.setCancelled(true);
            }
        }

        if(e.getClickedInventory().getName().equals(ChatUtils.format("&6XP Booster"))){
            Player p = (Player) e.getWhoClicked();
            String[] splittedName = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§e§n", "").split(" ");
            String itemName = splittedName[0];

            if(e.getSlot() == 36){
                AdminpanelGUI.openAdminPanel(p);
                e.setCancelled(true);
                return;
            }

            if(e.getSlot() == 40){
                if(AdminPanelManager.getInstance().getXpBooster() > 1 ){

                    Runnables.endBooster("xp", p);

                    AdminpanelGUI.openAdminPanel(p);
                    e.setCancelled(true);
                    return;
                }
                e.setCancelled(true);
            }

            if(e.getSlot() == 44){
                CoinsBoosterGUI.openCoinsBoosterGUI(p);
                e.setCancelled(true);
                return;
            }

            if(Runnables.runnable.containsKey("xp")){
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage(ChatUtils.format("&cThere is already a XP booster running."));
                return;
            } else {

                TimeChooseGUI.openTimeChooseGUI(p, "XP");
                XPBooster.put(p.getUniqueId(), Double.valueOf(itemName));
                AdminPanelManager.getInstance().setXpBooster(Double.valueOf(itemName), p);

                e.setCancelled(true);
            }
        }

        if(e.getInventory().getName().contains(ChatUtils.format("&6Time Selector"))){
            Player p = (Player) e.getWhoClicked();

            String[] splittedKind = e.getClickedInventory().getName().split(" ");

            String[] splittedName = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§e§n", "").split(" ");
            String itemName = splittedName[0];

            Double amount = 0.0;
            if(CoinsBooster.containsKey(p.getUniqueId())){
                amount = CoinsBooster.get(p.getUniqueId());
            } if(XPBooster.containsKey(p.getUniqueId())){
                amount = XPBooster.get(p.getUniqueId());
            }

            Runnables.startBooster(Integer.valueOf(itemName), amount, splittedKind[3].toLowerCase(), p);
            p.closeInventory();
        }
    }
}
