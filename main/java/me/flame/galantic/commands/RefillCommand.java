package me.flame.galantic.commands;

import me.flame.galantic.Core;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class RefillCommand implements CommandExecutor {

    private static HashMap<UUID, Integer> cooldownSeconds = new HashMap<>();
    private static HashMap<UUID, BukkitRunnable> cooldownTask = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(p.getInventory().getHelmet() == null) return true;

        if(cooldownTask.containsKey(p.getUniqueId())){
            CoreAPI.getMessageManager().sendMessage(p, "refill_cooldown", cooldownSeconds.get(p.getUniqueId()));
            return true;
        }

        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == p.getUniqueId()) {
                if (user.getPvpCoins() >= FileManager.get("config.yml").getDouble("PvP-Settings.coins-for-refill")) {
                    ItemStack mushroomSoup = new ItemStack(new ItemBuilder(Material.MUSHROOM_SOUP, 1).setDisplayName("&fSoup").build());
                    CoreAPI.getMessageManager().sendMessage(p, "refill_completed", FileManager.get("config.yml").getDouble("PvP-Settings.coins-for-refill"));
                    user.setPvpCoins(user.getPvpCoins() - FileManager.get("config.yml").getDouble("PvP-Settings.coins-for-refill"));
                    setCooldown(120, p);
                    for (int i = 0; i < p.getInventory().getSize(); i++) {
                        if (p.getInventory().getItem(i) == null) {
                            p.getInventory().addItem(mushroomSoup);
                        }
                    }
                } else {
                    CoreAPI.getMessageManager().sendMessage(p, "refill_not_enough_coins");
                    break;
                }
            }
        }


        return false;
    }

    public void setCooldown(Integer amount, Player p) {
        if (!cooldownTask.containsKey(p.getUniqueId())) {
            cooldownSeconds.put(p.getUniqueId(), amount);
            cooldownTask.put(p.getUniqueId(), new BukkitRunnable() {
                @Override
                public void run() {
                    if (cooldownSeconds.containsKey(p.getUniqueId())) {
                        cooldownSeconds.put(p.getUniqueId(), cooldownSeconds.get(p.getUniqueId()) - 1);
                    }
                    if (cooldownSeconds.get(p.getUniqueId()) == 0) {
                        cooldownSeconds.remove(p.getUniqueId());
                        cooldownTask.remove(p.getUniqueId());
                        cancel();
                    }
                }
            });

            cooldownTask.get(p.getUniqueId()).runTaskTimer(Core.getInstance(), 20, 20);
        }
    }
}
