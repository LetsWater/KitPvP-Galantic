package me.flame.galantic.commands;

import me.flame.galantic.Core;
import me.flame.galantic.combatlogger.CombatLoggerManager;
import me.flame.galantic.combatlogger.listeners.CombatLogEvent;
import me.flame.galantic.commands.gui.utils.ItemBuilder;
import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class SpawnCommand implements CommandExecutor, Listener {

    private static HashMap<UUID, Integer> cooldownSeconds = new HashMap<>();
    private static HashMap<UUID, BukkitRunnable> cooldownTask = new HashMap<>();
    CombatLoggerManager combatLoggerManager = new CombatLoggerManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (args.length) {
            case 0:
                if (!(sender instanceof Player)) {
                    CoreAPI.getMessageManager().sendMessage(sender, "wrong_usage_spawn");
                    break;
                } else {
                    Player p = (Player) sender;
                    if(combatLoggerManager.hasActiveCombat(p)){
                        CoreAPI.getMessageManager().sendMessage(p, "combat_enabled", combatLoggerManager.getCombatTime(p));
                        return true;
                    }

                    if (p.hasPermission("kitpvp.spawn.cooldownbypass")) {
                        String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                        String[] loc = locatie.split(";");
                        p.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));
                        CoreAPI.getMessageManager().sendMessage(sender, "spawn_teleport");

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

                        combatLoggerManager.removeCombat(p);
                        break;
                    } else {
                        setCooldown(5, p);
                    }
                    break;
                }
            case 1:
                if (!(sender.hasPermission("kitpvp.spawn"))) {
                    CoreAPI.getMessageManager().sendMessage(sender, "no_permission", "kitpvp.spawn");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    CoreAPI.getMessageManager().sendMessage(sender, "no_player", args[0]);
                    break;
                }

                String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                String[] loc = locatie.split(";");
                target.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

                CoreAPI.getMessageManager().sendMessage(sender, "spawn_teleport_target", target.getName());
                CoreAPI.getMessageManager().sendMessage(target, "spawn_teleport_from", sender.getName());
                break;
        }

        return false;
    }

    public void setCooldown(Integer amount, Player p) {
        if (!cooldownTask.containsKey(p.getUniqueId())) {
            cooldownSeconds.put(p.getUniqueId(), amount);
            cooldownTask.put(p.getUniqueId(), new BukkitRunnable() {
                @Override
                public void run() {
                    if (cooldownSeconds.containsKey(p.getUniqueId())) {
                        CoreAPI.getMessageManager().sendMessage(p, "spawn_cooldown", cooldownSeconds.get(p.getUniqueId()));
                        cooldownSeconds.put(p.getUniqueId(), cooldownSeconds.get(p.getUniqueId()) - 1);
                    }
                    if (cooldownSeconds.get(p.getUniqueId()) == 0) {
                        cooldownSeconds.remove(p.getUniqueId());
                        cooldownTask.remove(p.getUniqueId());
                        String locatie = FileManager.get("config.yml").getString("spawn.locatie");
                        String[] loc = locatie.split(";");
                        p.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));
                        CoreAPI.getMessageManager().sendMessage(p, "spawn_teleport");

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

                        combatLoggerManager.removeCombat(p);
                        cancel();
                    }
                }
            });

            cooldownTask.get(p.getUniqueId()).runTaskTimer(Core.getInstance(), 20, 20);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (cooldownTask.containsKey(p.getUniqueId())) {
            if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY() || e.getFrom().getZ() != e.getTo().getZ()) {
                cooldownSeconds.remove(p.getUniqueId());
                cooldownTask.get(p.getUniqueId()).cancel();
                cooldownTask.remove(p.getUniqueId());

                CoreAPI.getMessageManager().sendMessage(p, "spawn_teleport_canceld");
            }
        }
    }
}
