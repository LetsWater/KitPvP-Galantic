package me.flame.galantic.listeners;

import javafx.scene.layout.Priority;
import me.flame.galantic.Core;
import me.flame.galantic.combatlogger.CombatLoggerManager;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLManager;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.FileManager;
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
    public static HashMap<UUID, UUID> inFight = new HashMap<>();

    @EventHandler
    public void PvpEvent(PlayerDeathEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER)) return;
        Player p = e.getEntity().getPlayer();
        e.setDeathMessage(null);
        if (p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID) return;

        Player killer = e.getEntity().getKiller();
        e.getDrops().clear();


        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == p.getUniqueId()) {
                user.setDeaths(user.getDeaths() + 1);

                killstreak.replace(user.getUuid(), 0);

                p.sendMessage("Verloren van " + killer.getName());

                if (user.getPvpCoins() - FileManager.get("config.yml").getInt("PvP-Settings.coins-per-death") < 0) {
                    user.setPvpCoins(0);
                } else {
                    user.setPvpCoins(user.getPvpCoins() - FileManager.get("config.yml").getInt("PvP-Settings.coins-per-death"));
                }

                combatLoggerManager.removeCombat(p);
                p.spigot().respawn();
                break;
            }

            if (user.getUuid() == killer.getUniqueId()) {
                user.setKills(user.getKills() + 1);
                user.setPvpCoins(user.getPvpCoins() + FileManager.get("config.yml").getInt("PvP-Settings.coins-per-kill"));
                user.setXp(user.getXp() + FileManager.get("config.yml").getInt("PvP-Settings.xp-per-kill"));
                if (killstreak.containsKey(user.getUuid())) {
                    killstreak.replace(user.getUuid(), killstreak.get(user.getUuid()) + 1);
                } else {
                    killstreak.put(user.getUuid(), 1);
                }
                if (user.getBestStreak() < killstreak.get(user.getUuid())) {
                    user.setBestStreak(killstreak.get(user.getUuid()));
                }


                killer.sendMessage("Gewonnen van " + p.getName());
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
        e.setRespawnLocation(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.getInventory().setItem(0, new ItemBuilder(Material.ARMOR_STAND, 1).setDisplayName("&aKits").build());
                p.getInventory().setItem(1, new ItemBuilder(Material.NAME_TAG, 1).setDisplayName("&aEvents &c(Coming Soon)").build());
                p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS, 1).setDisplayName("&a&lServer Selector").build());
                p.getInventory().setItem(7, new ItemBuilder(Material.CHEST, 1).setDisplayName("&aCosmetics").build());
                p.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setDisplayName("&aProfile").setSkullOwner(p.getName()).build());

                p.setFireTicks(0);
            }
        }, 10);
    }

    @EventHandler
    public void pvpEvent(EntityDamageEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER)) return;

        Player p = (Player) e.getEntity();
        if (p.getInventory().getHelmet() == null) {
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            p.setHealth(0.5);
            for (SQLUser user : SQLUserManager.userList) {
                if (user.getUuid() == p.getUniqueId()) {
                    user.setDeaths(user.getDeaths() + 1);
                    if (inFight.containsKey(p.getUniqueId())) {
                        UUID targetUUID = inFight.get(p.getUniqueId());
                        Player target = Bukkit.getServer().getPlayer(targetUUID);
                        p.sendMessage(ChatUtils.format("&cJe bent vermoord door " + target.getName()));
                    }
                }
                break;
            }
            for (SQLUser user : SQLUserManager.userList) {
                if (inFight.containsKey(p.getUniqueId())) {
                    UUID targetUUID = inFight.get(p.getUniqueId());
                    Player target = Bukkit.getServer().getPlayer(targetUUID);
                    if (user.getUuid() == target.getUniqueId()) {
                        user.setKills(user.getKills() + 1);
                        target.sendMessage(ChatUtils.format("&aJe hebt zojuist " + p.getName() + " vermoord!"));
                    }
                }
                break;
            }
        }
    }

    @EventHandler
    public void pvpEventPlayer(EntityDamageByEntityEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER)) return;

        Player p = (Player) e.getEntity();
        if (p.getInventory().getHelmet() == null) return;

        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player shooter = (Player) arrow.getShooter();
                combatLoggerManager.setCombat(20, shooter);
                combatLoggerManager.setCombat(20, p);
                if (!inFight.containsKey(shooter.getUniqueId())) {
                    inFight.put(shooter.getUniqueId(), p.getUniqueId());
                }

                if (!inFight.containsKey(p.getUniqueId())) {
                    inFight.put(p.getUniqueId(), shooter.getUniqueId());
                }

            }
        }


        if (e.getDamager().getType() == EntityType.PLAYER) {
            Player damager = (Player) e.getDamager();
            combatLoggerManager.setCombat(20, damager);
            combatLoggerManager.setCombat(20, p);

            if (!inFight.containsKey(damager.getUniqueId())) {
                inFight.put(damager.getUniqueId(), p.getUniqueId());
            }

            if (!inFight.containsKey(p.getUniqueId())) {
                inFight.put(p.getUniqueId(), damager.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void soupClickEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null) return;
        if (p.getHealth() == p.getMaxHealth()) return;
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
