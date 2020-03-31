package me.flame.galantic.kits.upgradeKits.listeners;

import me.flame.galantic.kits.upgradeKits.gui.KitShopMenu;
import me.flame.galantic.kits.upgradeKits.gui.KitUpgradeMenu;
import me.flame.galantic.kits.upgradeKits.gui.UpgradeMenuGUI;
import me.flame.galantic.kits.upgradeKits.managers.UpgradeManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GUIInventoryListener implements Listener {

    final private KitUpgradeMenu kitUpgradeMenu = new KitUpgradeMenu();
    final private UpgradeMenuGUI upgradeMenuGUI = new UpgradeMenuGUI();
    final private KitShopMenu kitShopMenu = new KitShopMenu();

    @EventHandler
    public void InvClickEvent(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aKit Upgrades"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.IRON_SWORD) {
                kitUpgradeMenu.openNormalUpgradeMenuGUI(p.getUniqueId());
            }
            if (e.getCurrentItem().getType() == Material.ANVIL) {
                kitShopMenu.openKitShopMenu(p.getUniqueId());
            }
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aKit upgrades"))) {
            if (e.getCurrentItem().getType() == Material.ARROW) {
                upgradeMenuGUI.upgradeMenuGUI(p.getUniqueId());
            }
            for (SQLUser user : SQLUserManager.userList) {
                if (user.getUuid() == p.getUniqueId()) {
                    if (e.getSlot() == 11) {
                        if ((user.getWarrior_level() + 1) >= 5) {
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "warrior");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("warrior", (user.getWarrior_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "warrior", (user.getWarrior_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 12) {
                        if ((user.getArcher_level() + 1) >= 5) {
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "archer");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("archer", (user.getArcher_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "archer", (user.getArcher_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 13) {
                        if ((user.getTank_level() + 1) >= 5) {
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "tank");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("tank", (user.getTank_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "tank", (user.getTank_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 14) {
                        if ((user.getAxe_level() + 1) >= 5) {
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "axe");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("axe", (user.getAxe_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "axe", (user.getAxe_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 15) {
                        if ((user.getNinja_level() + 1) >= 5) {
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "ninja");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("ninja", (user.getNinja_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "ninja", (user.getNinja_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 20) {
                        if ((user.getHood_level() + 1) >= 5 || user.getHood_level() == 0) {
                            if (user.getHood_level() == 0) {
                                CoreAPI.getMessageManager().sendMessage(p, "kit_not_unlocked", "hood");
                                p.closeInventory();
                                break;
                            }
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "hood");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("hood", (user.getHood_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "hood", (user.getHood_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 21) {
                        if ((user.getHealer_level() + 1) >= 5 || user.getHealer_level() == 0) {
                            if (user.getHealer_level() == 0) {
                                CoreAPI.getMessageManager().sendMessage(p, "kit_not_unlocked", "healer");
                                p.closeInventory();
                                break;
                            }
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "healer");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("healer", (user.getHealer_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "healer", (user.getHealer_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 22) {
                        if ((user.getRogue_level() + 1) >= 5 || user.getRogue_level() == 0) {
                            if (user.getRogue_level() == 0) {
                                CoreAPI.getMessageManager().sendMessage(p, "kit_not_unlocked", "rogue");
                                p.closeInventory();
                                break;
                            }
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "rogue");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("rogue", (user.getRogue_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "rogue", (user.getRogue_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 23) {
                        if ((user.getKnight_level() + 1) >= 5 || user.getKnight_level() == 0) {
                            if (user.getKnight_level() == 0) {
                                CoreAPI.getMessageManager().sendMessage(p, "kit_not_unlocked", "knight");
                                p.closeInventory();
                                break;
                            }
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "knight");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("knight", (user.getKnight_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "knight", (user.getKnight_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    if (e.getSlot() == 24) {
                        if ((user.getAssassin_level() + 1) >= 5 || user.getAssassin_level() == 0) {
                            if (user.getAssassin_level() == 0) {
                                CoreAPI.getMessageManager().sendMessage(p, "kit_not_unlocked", "assassin");
                                p.closeInventory();
                                break;
                            }
                            CoreAPI.getMessageManager().sendMessage(p, "kit_max_level", "assassin");
                            p.closeInventory();
                            break;
                        }
                        if (user.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("assassin", (user.getAssassin_level() + 1))) {
                            UpgradeManager.getInstance().upgradeKitLevel(p.getUniqueId(), "assassin", (user.getAssassin_level() + 1));
                            p.closeInventory();
                            break;
                        }
                    }
                    break;
                }
            }
            e.setCancelled(true);
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aKit Shop"))) {
            String itemName = e.getCurrentItem().getType().getData().getName().replaceAll("&b", "");
            Double kitCost = UpgradeManager.getInstance().getLevelUpgradeCost(itemName, 1);
            for (SQLUser SQLuser : SQLUserManager.userList) {
                if (SQLuser.getUuid() == p.getUniqueId()) {
                    if (e.getSlot() == 11 && SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("hood", 1)) {
                        if (SQLuser.getHood_level() > 0) {
                            p.closeInventory();
                            CoreAPI.getMessageManager().sendMessage(p, "kit_already_unlocked", "hood");
                            return;
                        }
                        CoreAPI.getMessageManager().sendMessage(p, "kit_bought", "hood");
                        SQLuser.setHood_level(1);
                        SQLuser.setPvpCoins(SQLuser.getPvpCoins() - kitCost);
                        p.closeInventory();
                        return;
                    }
                    if (e.getSlot() == 12 && SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("healer", 1)) {
                        if (SQLuser.getHealer_level() > 0) {
                            p.closeInventory();
                            CoreAPI.getMessageManager().sendMessage(p, "kit_already_unlocked", "healer");
                            return;
                        }
                        CoreAPI.getMessageManager().sendMessage(p, "kit_bought", "healer");
                        SQLuser.setHealer_level(1);
                        SQLuser.setPvpCoins(SQLuser.getPvpCoins() - kitCost);
                        p.closeInventory();
                        return;
                    }
                    if (e.getSlot() == 13  && SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("rogue", 1)) {
                        if (SQLuser.getRogue_level() > 0) {
                            p.closeInventory();
                            CoreAPI.getMessageManager().sendMessage(p, "kit_already_unlocked", "rogue");
                            return;
                        }
                        CoreAPI.getMessageManager().sendMessage(p, "kit_bought", "rogue");
                        SQLuser.setRogue_level(1);
                        SQLuser.setPvpCoins(SQLuser.getPvpCoins() - kitCost);
                        p.closeInventory();
                        return;
                    }
                    if (e.getSlot() == 14 && SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("knight", 1)) {
                        if (SQLuser.getKnight_level() > 0) {
                            p.closeInventory();
                            CoreAPI.getMessageManager().sendMessage(p, "kit_already_unlocked", "knight");
                            return;
                        }
                        CoreAPI.getMessageManager().sendMessage(p, "kit_bought", "knight");
                        SQLuser.setKnight_level(1);
                        SQLuser.setPvpCoins(SQLuser.getPvpCoins() - kitCost);
                        p.closeInventory();
                        return;
                    }
                    if (e.getSlot() == 15 && SQLuser.getPvpCoins() >= UpgradeManager.getInstance().getLevelUpgradeCost("assassin", 1)) {
                        if (SQLuser.getAssassin_level() > 0) {
                            p.closeInventory();
                            CoreAPI.getMessageManager().sendMessage(p, "kit_already_unlocked", "assassin");
                            return;
                        }
                        CoreAPI.getMessageManager().sendMessage(p, "kit_bought", "assassin");
                        SQLuser.setAssassin_level(1);
                        SQLuser.setPvpCoins(SQLuser.getPvpCoins() - kitCost);
                        p.closeInventory();
                        return;
                    }

                    if (e.getSlot() == 27) {
                        upgradeMenuGUI.upgradeMenuGUI(p.getUniqueId());
                        e.setCancelled(true);
                        return;
                    }
                }
            }
            e.setCancelled(true);
        }

        if (e.getClickedInventory().getName().contains(ChatUtils.format("&aKit upgrades"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.ARROW) {
                upgradeMenuGUI.upgradeMenuGUI(p.getUniqueId());
                return;
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
