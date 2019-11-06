package me.flame.galantic.commands;

import me.flame.galantic.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("kitpvp.spawn"))) {
            sender.sendMessage("Geen permissions voor /spawn");
            return true;
        }

        switch (args.length) {
            case 0:
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Gebruikt /spawn <speler>");
                    break;
                } else {
                    Player p = (Player) sender;
                    String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                    String[] loc = locatie.split(";");
                    p.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

                    sender.sendMessage("teleported to spawn");
                    break;
                }
            case 1:
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("Speler niet gevonden / niet online");
                    break;
                }

                String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                String[] loc = locatie.split(";");
                target.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

                sender.sendMessage("teleported " + target.getName() + " naar te spawn");
                target.sendMessage("Geteleporteerd naar de spawn door " + sender.getName());
                break;
        }

        return false;
    }
}
