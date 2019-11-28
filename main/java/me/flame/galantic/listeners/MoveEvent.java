package me.flame.galantic.listeners;

import me.flame.galantic.kits.KitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.FileManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        Player p = e.getPlayer();

        String locatie = FileManager.get("config.yml").getString("spawn.locatie");
        String[] loc = locatie.split(";");
        if (p.getLocation().getY() <= Double.parseDouble(loc[2]) - 3 && p.getLocation().getY() >= Double.parseDouble(loc[2]) - 5 && p.getInventory().getHelmet() == null && p.getGameMode() == GameMode.ADVENTURE) {
            for (SQLUser user : SQLUserManager.userList) {
                if (user.getUuid() == p.getUniqueId()) {
                    KitManager.getInstance().giveKit(p.getUniqueId(), user.getUsing_kit());
                    p.setAllowFlight(false);
                    break;
                }
            }
        }
    }
}
