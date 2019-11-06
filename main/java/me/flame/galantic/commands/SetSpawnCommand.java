package me.flame.galantic.commands;

import me.flame.galantic.Core;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("kitpvp.setspawn"))) {
            sender.sendMessage("Geen permissions voor setspawn");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtils.format("&cKan alleen door speler worden uitgevoerd."));
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
        sender.sendMessage("Spawn gezet");


        return false;
    }
}
