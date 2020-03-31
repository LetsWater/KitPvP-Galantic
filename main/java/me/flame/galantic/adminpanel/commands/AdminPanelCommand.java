package me.flame.galantic.adminpanel.commands;

import me.flame.galantic.adminpanel.commands.gui.AdminpanelGUI;
import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminPanelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if(p.hasPermission("kitpvp.adminpanel.use")){
            AdminpanelGUI.openAdminPanel(p);
            return true;
        }

        return false;
    }
}
