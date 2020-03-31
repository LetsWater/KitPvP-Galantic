package me.flame.galantic.adminpanel.utils;

import me.flame.galantic.Core;
import me.flame.galantic.adminpanel.AdminPanel;
import me.flame.galantic.adminpanel.managers.AdminPanelManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Runnables {

    public static HashMap<String, Integer> minutesList = new HashMap<>();
    public static HashMap<String, Integer> secondsList = new HashMap<>();
    public static HashMap<String, BukkitRunnable> runnable = new HashMap<>();

    public static void startBooster(Integer minutes, Double amount, String kind, Player p) {

        if (kind.equalsIgnoreCase("coins")) {
            AdminPanelManager.getInstance().setBooster(amount, p);
            minutesList.put("coins", (minutes - 1));
            secondsList.put("coins", 59);
            runnable.put("coins", new BukkitRunnable() {
                @Override
                public void run() {

                    if (secondsList.get("coins") != 0) {
                        secondsList.put("coins", secondsList.get("coins") - 1);
                    } else {
                        if (minutesList.get("coins") != 0) {
                            secondsList.put("coins", 59);
                            if (minutesList.get("coins") > 1) {
                                minutesList.put("coins", minutesList.get("coins") - 1);
                            } else {
                                minutesList.put("coins", 0);
                            }
                            return;
                        }
                        if (secondsList.get("coins") == 0) {
                            endBooster("coins", p);
                        }
                    }

                }
            });



            runnable.get(kind).runTaskTimer(Core.getInstance(), 1 * 20, 1 * 20);


        }
        if (kind.equalsIgnoreCase("xp")) {
            AdminPanelManager.getInstance().setXpBooster(amount, p);
            minutesList.put("xp", (minutes - 1));
            secondsList.put("xp", 59);
            runnable.put("xp", new BukkitRunnable() {
                @Override
                public void run() {

                    if (secondsList.get("xp") != 0) {
                        secondsList.put("xp", secondsList.get("xp") - 1);
                    } else {
                        if (minutesList.get("xp") != 0) {
                            secondsList.put("xp", 59);
                            if (minutesList.get("xp") > 1) {
                                minutesList.put("xp", minutesList.get("xp") - 1);
                            } else {
                                minutesList.put("xp", 0);
                            }
                            return;
                        }
                        if (secondsList.get("xp") == 0) {
                            endBooster("xp", p);
                        }
                    }

                }
            });

            runnable.get(kind).runTaskTimer(Core.getInstance(), 1 * 20, 1 * 20);
        }
    }

    public static void endBooster(String kind, Player p) {
        if (runnable.containsKey(kind)) {
            runnable.get(kind).cancel();

            runnable.remove(kind);
            minutesList.remove(kind);
            secondsList.remove(kind);

            if(kind.equalsIgnoreCase("coins")) {
                AdminPanelManager.getInstance().setBooster(1.0, p);
                return;
            }
            if(kind.equalsIgnoreCase("xp")){
                AdminPanelManager.getInstance().setXpBooster(1.0, p);
                return;
            }
        }
    }
}
