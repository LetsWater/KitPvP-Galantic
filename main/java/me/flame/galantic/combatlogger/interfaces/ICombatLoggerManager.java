package me.flame.galantic.combatlogger.interfaces;

import org.bukkit.entity.Player;

public interface ICombatLoggerManager {

    void setCombat(Integer amount, Player p);

    void removeCombat(Player p);

    void getCombat(Player p);

    boolean hasActiveCombat(Player p);
}
