package me.flame.galantic.kits;

import me.flame.galantic.kits.giveKits.giveKitManager;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.galantic.galanticcore.api.CoreAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class KitManager implements IKitManager {

    private static KitManager instance = new KitManager();
    final private giveKitManager GiveKitManager = new giveKitManager();

    @Override
    public void giveKit(UUID uuid, String kit) {
        Player p = Bukkit.getPlayer(uuid);
        if (p.getInventory().getHelmet() == null) {
            CoreAPI.getMessageManager().sendMessage(p, "kit_received", kit);
            GiveKitManager.giveKit(uuid, kit);
        }
    }

    public static KitManager getInstance() {
        return instance;
    }
}
