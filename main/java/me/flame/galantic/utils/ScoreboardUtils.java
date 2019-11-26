package me.flame.galantic.utils;

import me.flame.galantic.Core;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ScoreboardUtils {

    public void setScoreboard(UUID uuid) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                Player p = Bukkit.getPlayer(uuid);
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("noflicker", "Dummy");

                int ping = 0;
                try {
                    Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
                    ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                    e.printStackTrace();
                }

                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                obj.setDisplayName(ChatUtils.format("&b&lKitPvP"));

                Score spacer4 = obj.getScore(ChatUtils.format("&4"));
                spacer4.setScore(12);

                Score spacer1 = obj.getScore(ChatUtils.format("&1"));
                spacer1.setScore(8);

                Team playerPing = scoreboard.registerNewTeam("ping");
                playerPing.addEntry(ChatUtils.format("&fPing: &e"));
                playerPing.setSuffix(String.valueOf(ping));
                obj.getScore(ChatUtils.format("&fPing: &e")).setScore(7);

                Team playerKit = scoreboard.registerNewTeam("usingkit");
                playerKit.addEntry(ChatUtils.format("&fKit: &6"));
                playerKit.setSuffix(user.getUsing_kit());
                obj.getScore(ChatUtils.format("&fKit: &6")).setScore(11);

                Team playerLevel = scoreboard.registerNewTeam("level");
                playerLevel.addEntry(ChatUtils.format("&fLevel: &e"));
                playerLevel.setSuffix(String.valueOf(user.getLevel()));
                obj.getScore(ChatUtils.format("&fLevel: &e")).setScore(10);

                Team playerCoins = scoreboard.registerNewTeam("coins");
                playerCoins.addEntry(ChatUtils.format("&fCoins: &e"));
                playerCoins.setSuffix(String.valueOf(user.getPvpCoins()));
                obj.getScore(ChatUtils.format("&fCoins: &e")).setScore(9);

                Score spacer2 = obj.getScore(ChatUtils.format("&2"));
                spacer2.setScore(6);

                Team playerKills = scoreboard.registerNewTeam("kills");
                playerKills.addEntry(ChatUtils.format("&fKills: &e"));
                playerKills.setSuffix(String.valueOf(user.getKills()));
                obj.getScore(ChatUtils.format("&fKills: &e")).setScore(5);

                Team playerDeaths = scoreboard.registerNewTeam("deaths");
                playerDeaths.addEntry(ChatUtils.format("&fDeaths: &e"));
                playerDeaths.setSuffix(String.valueOf(user.getDeaths()));
                obj.getScore(ChatUtils.format("&fDeaths: &e")).setScore(4);

                Team playerKillstreak = scoreboard.registerNewTeam("killstreak");
                playerKillstreak.addEntry(ChatUtils.format("&fKillstreak: &e"));
                playerKillstreak.setSuffix(String.valueOf(PvPEventListener.killstreak.get(uuid)));
                obj.getScore(ChatUtils.format("&fKillstreak: &e")).setScore(3);

                Score spacer3 = obj.getScore(ChatUtils.format("&3"));
                spacer3.setScore(2);

                Score footer = obj.getScore(ChatUtils.format("&7play.galanticmc.net"));
                footer.setScore(1);

                new BukkitRunnable(){

                    @Override
                    public void run(){
                        int ping = 0;
                        try {
                            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
                            ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }

                        scoreboard.getTeam("ping").setSuffix(String.valueOf(ping));
                        scoreboard.getTeam("level").setSuffix(String.valueOf(user.getLevel()));
                        scoreboard.getTeam("coins").setSuffix(String.valueOf(user.getPvpCoins()));
                        scoreboard.getTeam("kills").setSuffix(String.valueOf(user.getKills()));
                        scoreboard.getTeam("usingkit").setSuffix(user.getUsing_kit());
                        scoreboard.getTeam("deaths").setSuffix(String.valueOf(user.getDeaths()));
                        scoreboard.getTeam("killstreak").setSuffix(String.valueOf(PvPEventListener.killstreak.get(uuid)));



                    }
                }.runTaskTimer(Core.getInstance(), 10, 60);

                p.setScoreboard(scoreboard);
                break;
            }
        }
    }
}
