package me.flame.galantic.commands;

import me.flame.galantic.Core;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("kitpvp.setspawn"))) {
        	CoreAPI.getMessageManager().sendMessage( sender, "no_permission", "kitpvp.setspawn" );
            return true;
        }

        if (!(sender instanceof Player)) {
        	CoreAPI.getMessageManager().sendMessage( sender, "core_no_player_console" );
            return true;
        }

        Player p = (Player) sender;
        FileManager.get("config.yml").set("spawn.locatie", p.getLocation().getWorld().getName()
                + ";" + p.getLocation().getX()
                + ";" + p.getLocation().getY()
                + ";" + p.getLocation().getZ()
                + ";" + p.getLocation().getYaw()
                + ";" + p.getLocation().getPitch());

        FileManager.save(Core.getInstance(), "config.yml");
        CoreAPI.getMessageManager().sendMessage( sender, "spawn_saved" );


        return false;
    }
}
