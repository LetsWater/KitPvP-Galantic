package me.flame.galantic.adminpanel.interfaces;

import org.bukkit.entity.Player;

public interface IAdminPanel {

    String loadAdminPanel();
    boolean setPermissions(Boolean permissions, Player p);
    boolean getPermissions();
    String getLastChangerPerms();
    double setBooster(Double number, Player p);
    double getBooster();
    String getLastChangerCoinsBooster();
    double setXpBooster(Double number, Player p);
    double getXpBooster();
    String getLastChangerXpBooster();
}
