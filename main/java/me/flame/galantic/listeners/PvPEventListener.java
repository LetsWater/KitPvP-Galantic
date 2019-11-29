package me.flame.galantic.listeners;

import me.flame.galantic.Core;
import me.flame.galantic.combatlogger.CombatLoggerManager;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.levelSystem.managers.UserLevelManager;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PvPEventListener implements Listener {

    private final CombatLoggerManager combatLoggerManager = new CombatLoggerManager();
    public static HashMap<UUID, Integer> killstreak = new HashMap<>();
    final public static HashMap<UUID, UUID> inFight = new HashMap<>();

    @EventHandler
    public void PvpEvent(PlayerDeathEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER))
            return;
        e.getDrops().clear();
        e.setDeathMessage(null);
        e.setDroppedExp(0);
        if (e.getEntity().getKiller() == null || inFight.containsKey(e.getEntity().getUniqueId())) {
            if (inFight.containsKey(e.getEntity().getUniqueId())) {
                Player killer = Bukkit.getServer().getPlayer(inFight.get(e.getEntity().getUniqueId()));
                CoreAPI.getMessageManager().sendMessage(e.getEntity(), "death_killed_by", killer.getName());
                CoreAPI.getMessageManager().sendMessage(killer, "killed_player", e.getEntity().getName());

                SQLUserManager.getInstance().addRewards(killer.getUniqueId());
                SQLUserManager.getInstance().removeRewards(e.getEntity().getUniqueId());

                combatLoggerManager.removeCombat(e.getEntity());

                e.getEntity().spigot().respawn();
                return;
            } else {
                CoreAPI.getMessageManager().sendMessage(e.getEntity(), "death_void");
                SQLUserManager.getInstance().removeRewards(e.getEntity().getUniqueId());
            }
        }

        Player p = e.getEntity();
        for (SQLUser sqlUser : SQLUserManager.userList) {
            if (sqlUser.getUuid() == p.getUniqueId()) {
                // Message
                CoreAPI.getMessageManager().sendMessage(e.getEntity(), "death_killed_by",
                        e.getEntity().getKiller().getName());

                // Rewards
                SQLUserManager.getInstance().removeRewards(p.getUniqueId());

                p.spigot().respawn();
                break;
            }
        }

        Player killer = e.getEntity().getKiller();
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == killer.getUniqueId()) {
                // Message
                CoreAPI.getMessageManager().sendMessage(killer, "killed_player", p.getName());

                // Rewards
                SQLUserManager.getInstance().addRewards(killer.getUniqueId());

                // Remove from fight
                break;
            }
        }

    }

    @EventHandler
    public void respawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        p.setGameMode(GameMode.ADVENTURE);

        String locatie = FileManager.get("config.yml").getString("spawn.locatie");
        String[] loc = locatie.split(";");
        e.setRespawnLocation(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]),
                Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]),
                Float.valueOf(loc[5])));

        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.getInventory().setItem(0,
                        new ItemBuilder(Material.ARMOR_STAND, 1).setDisplayName("&aKits").build());
                p.getInventory().setItem(1,
                        new ItemBuilder(Material.EMERALD, 1).setDisplayName("&aShop").build());
                p.getInventory().setItem(4,
                        new ItemBuilder(Material.COMPASS, 1).setDisplayName("&a&lServer Selector").build());
                p.getInventory().setItem(7,
                        new ItemBuilder(Material.CHEST, 1).setDisplayName("&aCosmetics").build());
                p.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                        .setDisplayName("&aProfile").setSkullOwner(p.getName()).build());

                p.setHealth(20);
                p.setFireTicks(0);
                UserLevelManager.getInstance().setXPLevel(p.getUniqueId());
            }
        }, 10);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void pvpEvent(EntityDamageEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER))
            return;

        Player p = (Player) e.getEntity();
        if (p.getInventory().getHelmet() == null) {
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            p.setHealth(0);
            p.spigot().respawn();
        }
    }

    @EventHandler
    public void pvpEventPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER))
            return;

        Player p = (Player) e.getEntity();
        if (p.getInventory().getHelmet() == null)
            return;

        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            Player shooter;
            if (arrow.getShooter() instanceof Player) {
                shooter = (Player) arrow.getShooter();
                combatLoggerManager.setCombat(20, shooter);
                combatLoggerManager.setCombat(20, p);
                inFight.put(shooter.getUniqueId(), p.getUniqueId());
                inFight.put(p.getUniqueId(), shooter.getUniqueId());

            }
        }

        Player damager;
        if (e.getDamager().getType() == EntityType.PLAYER) {
            damager = (Player) e.getDamager();
            combatLoggerManager.setCombat(20, damager);
            combatLoggerManager.setCombat(20, p);
            inFight.put(damager.getUniqueId(), p.getUniqueId());
            inFight.put(p.getUniqueId(), damager.getUniqueId());
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void soupClickEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null)
            return;
        if (p.getHealth() == p.getMaxHealth())
            return;
        if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                p.setHealth(p.getHealth() + 8 > p.getMaxHealth() ? p.getMaxHealth() : p.getHealth() + 8);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.setItemInHand(new ItemStack(Material.AIR));
                        p.updateInventory();
                    }
                }, 0);
            }
        }

    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            arrow.remove();
        }
    }

}
