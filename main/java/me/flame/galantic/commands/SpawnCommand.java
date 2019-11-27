package me.flame.galantic.commands;

import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;

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
        	CoreAPI.getMessageManager().sendMessage( sender, "no_permission", "kitpvp.spawn" );
            return true;
        }

        switch (args.length) {
            case 0:
                if (!(sender instanceof Player)) {
                	CoreAPI.getMessageManager().sendMessage( sender, "wrong_usage_spawn" );
                    break;
                } else {
                    Player p = (Player) sender;
                    String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                    String[] loc = locatie.split(";");
                    p.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

                    CoreAPI.getMessageManager().sendMessage( sender, "spawn_teleport");
                    break;
                }
            case 1:
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                	CoreAPI.getMessageManager().sendMessage( sender, "no_player", args[0] );
                    break;
                }

                String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                String[] loc = locatie.split(";");
                target.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

                CoreAPI.getMessageManager().sendMessage(sender, "spawn_teleport_target", target.getName());
                CoreAPI.getMessageManager().sendMessage( target, "spawn_teleport_from", sender.getName() );
                break;
        }

        return false;
    }
}
