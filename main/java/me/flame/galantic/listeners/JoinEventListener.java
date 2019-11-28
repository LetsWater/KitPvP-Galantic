package me.flame.galantic.listeners;

import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.levelSystem.managers.UserLevelManager;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.FileManager;
import me.flame.galantic.utils.ScoreboardUtils;
import me.galantic.galanticcore.api.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

public class JoinEventListener implements Listener {

    private final ScoreboardUtils scoreboardUtils = new ScoreboardUtils();

    @EventHandler
    public void joinGame(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.setGameMode(GameMode.ADVENTURE);
        p.setFoodLevel(20);
        p.setHealth(20);

        for(PotionEffect potionEffect : p.getActivePotionEffects()){
            p.removePotionEffect(potionEffect.getType());
        }

        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        p.getInventory().setItem(0, new ItemBuilder(Material.ARMOR_STAND, 1).setDisplayName("&aKits").build());
        p.getInventory().setItem(1, new ItemBuilder(Material.EMERALD, 1).setDisplayName("&aShop").build());
        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS, 1).setDisplayName("&a&lServer Selector").build());
        p.getInventory().setItem(7, new ItemBuilder(Material.CHEST, 1).setDisplayName("&aCosmetics").build());
        p.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setDisplayName("&aProfile").setSkullOwner(p.getName()).build());

        String locatie = FileManager.get("config.yml").getString("spawn.locatie");
        String[] loc = locatie.split(";");
        p.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

        UserLevelManager.getInstance().setXPLevel(p.getUniqueId());
        scoreboardUtils.setScoreboard(p.getUniqueId());
        CoreAPI.getUserManager().getUser(p.getUniqueId()).setTabList();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();

        SQLUserManager.getInstance().saveUser(p.getUniqueId());
    }
}
