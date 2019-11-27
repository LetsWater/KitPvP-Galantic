package me.flame.galantic.commands;

import me.flame.galantic.commands.gui.StatisticGUI;
import me.flame.galantic.utils.ChatUtils;
import me.galantic.galanticcore.api.CoreAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatisticsCommand implements CommandExecutor {

    private final StatisticGUI statisticGUI = new StatisticGUI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
        	CoreAPI.getMessageManager().sendMessage( sender, "core_no_player_console" );
            return true;
        }

        Player p = (Player) sender;
        switch (args.length) {
            case 0:
            	CoreAPI.getMessageManager().sendMessage( sender, "opening_menu" );
                statisticGUI.StatisticGUI(p.getUniqueId(), p);
                break;
            case 1:
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                	CoreAPI.getMessageManager().sendMessage( sender, "no_player" );
                    break;
                }
            	CoreAPI.getMessageManager().sendMessage( sender, "opening_menu_target", args[0] );
                statisticGUI.StatisticGUI(target.getUniqueId(), p);
                break;
            default:
            	CoreAPI.getMessageManager().sendMessage( sender, "opening_menu" );
                statisticGUI.StatisticGUI(p.getUniqueId(), p);
                break;
        }


        return false;
    }
}
