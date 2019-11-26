package me.flame.galantic.combatlogger;

import me.flame.galantic.Core;
import me.flame.galantic.combatlogger.interfaces.ICombatLoggerManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class CombatLoggerManager implements ICombatLoggerManager {

    private static HashMap<UUID, Integer> cooldownSeconds = new HashMap<>();
    private static HashMap<UUID, BukkitRunnable> cooldownTask = new HashMap<>();

    @Override
    public void setCombat(Integer amount, Player p) {
        if (!hasActiveCombat(p)) {

            p.sendMessage(ChatUtils.format("&cYou are now in combat!"));
            cooldownSeconds.put(p.getUniqueId(), amount);
            cooldownTask.put(p.getUniqueId(), new BukkitRunnable() {
                @Override
                public void run() {
                    if (cooldownSeconds.containsKey(p.getUniqueId())) {
                        cooldownSeconds.put(p.getUniqueId(), cooldownSeconds.get(p.getUniqueId()) - 1);
                    }
                    if (cooldownSeconds.get(p.getUniqueId()) == 0) {
                        removeCombat(p);
                        cancel();
                    }
                }
            });

            cooldownTask.get(p.getUniqueId()).runTaskTimer(Core.getInstance(), 20, 20);
        }

        cooldownSeconds.replace(p.getUniqueId(), 20);
    }

    @Override
    public void removeCombat(Player p) {
        if (hasActiveCombat(p)) {
            p.sendMessage(ChatUtils.format("&aYou are no longer in combat!"));

            cooldownTask.get(p.getUniqueId()).cancel();
            cooldownTask.remove(p.getUniqueId());
            cooldownSeconds.remove(p.getUniqueId());
        }
    }

    @Override
    public void getCombat(Player p) {
        if (!hasActiveCombat(p)) {
            p.sendMessage(ChatUtils.format("&aYou don't have an active CombatTag."));
            return;
        }

        p.sendMessage(ChatUtils.format("&7You are in combat for &c" + cooldownSeconds.get(p.getUniqueId()) + " &7seconds!"));
    }

    @Override
    public boolean hasActiveCombat(Player p) {
        return cooldownTask.containsKey(p.getUniqueId());
    }
}
