package me.flame.galantic.commands;

import me.flame.galantic.commands.gui.StatisticGUI;
import me.flame.galantic.utils.ChatUtils;
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
            sender.sendMessage(ChatUtils.format("&cCommand kan niet door console error."));
            return true;
        }

        Player p = (Player) sender;
        switch (args.length) {
            case 0:
                sender.sendMessage("Opent menu");
                statisticGUI.StatisticGUI(p.getUniqueId(), p);
                break;
            case 1:
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("Player is niet gevonden");
                    break;
                }
                sender.sendMessage("Opent menu speler " + args[0]);
                statisticGUI.StatisticGUI(target.getUniqueId(), p);
                break;
            default:
                sender.sendMessage("Opent menu");
                statisticGUI.StatisticGUI(p.getUniqueId(), p);
                break;
        }


        return false;
    }
}
