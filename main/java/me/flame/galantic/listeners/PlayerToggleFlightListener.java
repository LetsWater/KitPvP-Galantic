package me.flame.galantic.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;
import me.galantic.galanticcore.api.events.PlayerToggleFlightEvent;

public class PlayerToggleFlightListener implements Listener {

    @EventHandler
    public void on(PlayerToggleFlightEvent e) {
        Player target = e.getTarget();
        if (target.hasPermission("kitpvp.staff"))
            return;
        String locatie = FileManager.get("config.yml").getString("spawn.locatie");
        String[] loc = locatie.split(";");
        if (target.getLocation().getY() < Double.parseDouble(loc[2])
                && target.getGameMode() == GameMode.ADVENTURE) {
            e.setCancelled(true);
            CoreAPI.getMessageManager().sendMessage(e.getExecutor() == null ? target : e.getExecutor(),
                    "fly_cannot_enable");
        }
    }
}