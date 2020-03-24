package me.flame.galantic.combatlogger;

import me.flame.galantic.Core;
import me.flame.galantic.combatlogger.interfaces.ICombatLoggerManager;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;

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
        	
        	CoreAPI.getMessageManager().sendMessage( p, "combat_activated" );
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
        	CoreAPI.getMessageManager().sendMessage( p, "combat_disabled");

            cooldownTask.get(p.getUniqueId()).cancel();
            cooldownTask.remove(p.getUniqueId());
            cooldownSeconds.remove(p.getUniqueId());

            PvPEventListener.inFight.remove(p.getUniqueId());
        }
    }

    @Override
    public void getCombat(Player p) {
        if (!hasActiveCombat(p)) {
        	CoreAPI.getMessageManager().sendMessage( p, "combat_no_active" );
            return;
        }
        
        CoreAPI.getMessageManager().sendMessage( p, "combat_enabled", cooldownSeconds.get( p.getUniqueId() ) );
    }

    @Override
    public boolean hasActiveCombat(Player p) {
        return cooldownTask.containsKey(p.getUniqueId());
    }

    public Integer getCombatTime(Player p){
        if(hasActiveCombat(p)){
            return cooldownSeconds.get(p.getUniqueId());
        }
        return 0;
    }
}
