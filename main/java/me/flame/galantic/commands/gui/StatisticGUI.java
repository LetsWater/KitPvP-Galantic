package me.flame.galantic.commands.gui;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.levelSystem.UserLevel;
import me.flame.galantic.sql.levelSystem.managers.UserLevelManager;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.text.DecimalFormat;
import java.util.UUID;

import static me.flame.galantic.sql.managers.SQLUserManager.userList;

public class StatisticGUI {

    public Inventory StatisticGUI(UUID uuid, Player p) {
        Player target = Bukkit.getPlayer(uuid);

        Inventory StatsGUI = Bukkit.createInventory(null, 9, ChatUtils.format("&a&lStats &8» &7" + target.getName()));

        for (SQLUser user : userList) {
            if (user.getUuid() == uuid) {
                StatsGUI.setItem(1, new ItemBuilder(Material.DIAMOND_SWORD, 1)
                        .setDisplayName("&aKills: &7" + user.getKills()).setItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

                StatsGUI.setItem(2, new ItemBuilder(Material.SKULL_ITEM, 1)
                        .setDisplayName("&aDeaths: &7" + user.getDeaths()).build());

                StatsGUI.setItem(3, new ItemBuilder(Material.GOLD_SWORD, 1)
                        .setDisplayName("&aBest Killstreak: &7" + user.getBestStreak()).setItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

                DecimalFormat df = new DecimalFormat("#0.00");
                double KDR;
                if (user.getDeaths() == 0) {
                    KDR = (double) user.getKills();
                } else {
                    KDR = (double) user.getKills() / (double) user.getDeaths();
                }

                StatsGUI.setItem(5, new ItemBuilder(Material.WATCH, 1)
                        .setDisplayName("&aKill/Death Ratio: &7" + df.format(KDR)).build());

                StatsGUI.setItem(6, new ItemBuilder(Material.GOLD_NUGGET, 1)
                        .setDisplayName("&aCoins: &7" + user.getPvpCoins()).build());

                Integer nextLevel = user.getLevel() + 1;
                for (UserLevel userLevel : UserLevelManager.levelList) {
                    if (userLevel.getLevel() == nextLevel) {
                        StatsGUI.setItem(7, new ItemBuilder(Material.SIGN, 1)
                                .setDisplayName("&aLevel Information")
                                .setLore(false, " &fHuidig level &8» &7" + user.getLevel() + "/100", " &fXP &8» &7" + user.getXp() + "/" + userLevel.getXP()).build());
                        break;
                    }
                    if (userLevel.getLevel() == 100){
                        StatsGUI.setItem(7, new ItemBuilder(Material.SIGN, 1)
                                .setDisplayName("&aLevel Information")
                                .setLore(false, " &fHuidig level &8» &7" + user.getLevel() + "", " &fXP &8» &cMax Level").build());
                        break;
                    }
                }
                break;
            }
        }

        StatsGUI.setContents(StatsGUI.getContents());
        p.openInventory(StatsGUI);

        return StatsGUI;
    }
}
