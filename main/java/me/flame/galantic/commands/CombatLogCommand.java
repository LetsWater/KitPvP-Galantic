package me.flame.galantic.commands;

import me.flame.galantic.combatlogger.CombatLoggerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatLogCommand implements CommandExecutor {

    private final CombatLoggerManager combatLoggerManager = new CombatLoggerManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;
        combatLoggerManager.getCombat(p);

        return false;
    }
}
