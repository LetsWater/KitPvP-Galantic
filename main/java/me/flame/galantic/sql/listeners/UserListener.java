package me.flame.galantic.sql.listeners;

import me.flame.galantic.sql.managers.SQLUserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UserListener implements Listener {

    private final SQLUserManager sqlUserManager = new SQLUserManager();

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        sqlUserManager.loadUser(e.getPlayer().getUniqueId());
    }
}
