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
import java.text.DecimalFormat;
import java.util.UUID;

public class ScoreboardUtils {

    public void setScoreboard(UUID uuid) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                Player p = Bukkit.getPlayer(uuid);
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("noflicker", "Dummy");

                DecimalFormat df = new DecimalFormat("#0.00");
                double KDR;
                if(user.getDeaths() == 0){
                    KDR = (double) user.getKills();
                } else {
                    KDR = (double) user.getKills() / (double) user.getDeaths();
                }

                int ping = 0;
                try {
                    Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
                    ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                    e.printStackTrace();
                }

                obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                obj.setDisplayName(ChatUtils.format("&b&lGalantic Network"));

                Score spacer1 = obj.getScore(ChatUtils.format("&1"));
                spacer1.setScore(15);

                Score information = obj.getScore(ChatUtils.format("&3&lInformation"));
                information.setScore(14);

                Team playerPing = scoreboard.registerNewTeam("ping");
                playerPing.addEntry(ChatUtils.format(" &bPing: &f"));
                playerPing.setSuffix(String.valueOf(ping));
                obj.getScore(ChatUtils.format(" &bPing: &f")).setScore(13);

                Team playerKit = scoreboard.registerNewTeam("usingkit");
                playerKit.addEntry(ChatUtils.format(" &bKit: &f"));
                playerKit.setSuffix(user.getUsing_kit());
                obj.getScore(ChatUtils.format(" &bKit: &f")).setScore(12);

                Team playerLevel = scoreboard.registerNewTeam("level");
                playerLevel.addEntry(ChatUtils.format(" &bLevel: &f"));
                playerLevel.setSuffix(String.valueOf(user.getLevel()));
                obj.getScore(ChatUtils.format(" &bLevel: &f")).setScore(11);

                Team playerCoins = scoreboard.registerNewTeam("coins");
                playerCoins.addEntry(ChatUtils.format(" &bCoins: &f"));
                playerCoins.setSuffix(String.valueOf(user.getPvpCoins()));
                obj.getScore(ChatUtils.format(" &bCoins: &f")).setScore(10);

                Score spacer2 = obj.getScore(ChatUtils.format("&2"));
                spacer2.setScore(9);

                Score stats = obj.getScore(ChatUtils.format("&3&lPvP Stats"));
                stats.setScore(8);

                Team playerKills = scoreboard.registerNewTeam("kills");
                playerKills.addEntry(ChatUtils.format(" &bKills: &f"));
                playerKills.setSuffix(String.valueOf(user.getKills()));
                obj.getScore(ChatUtils.format(" &bKills: &f")).setScore(7);

                Team playerDeaths = scoreboard.registerNewTeam("deaths");
                playerDeaths.addEntry(ChatUtils.format(" &bDeaths: &f"));
                playerDeaths.setSuffix(String.valueOf(user.getDeaths()));
                obj.getScore(ChatUtils.format(" &bDeaths: &f")).setScore(6);

                Team playerKDR = scoreboard.registerNewTeam("kdr");
                playerKDR.addEntry(ChatUtils.format(" &bKDR: &f"));
                playerKDR.setSuffix(df.format(KDR));
                obj.getScore(ChatUtils.format(" &bKDR: &f")).setScore(5);

                Team playerKillstreak = scoreboard.registerNewTeam("killstreak");
                playerKillstreak.addEntry(ChatUtils.format(" &bKillstreak: &f"));
                playerKillstreak.setSuffix(String.valueOf(PvPEventListener.killstreak.get(uuid)));
                obj.getScore(ChatUtils.format(" &bKillstreak: &f")).setScore(4);

                Team playerTopKillstreak = scoreboard.registerNewTeam("topkillstreak");
                playerTopKillstreak.addEntry(ChatUtils.format(" &bTop Killstreak: &f"));
                playerTopKillstreak.setSuffix(String.valueOf(user.getBestStreak()));
                obj.getScore(ChatUtils.format(" &bTop Killstreak: &f")).setScore(3);

                Score spacer3 = obj.getScore(ChatUtils.format("&3"));
                spacer3.setScore(2);

                Score footer = obj.getScore(ChatUtils.format("&7play.galanticmc.net"));
                footer.setScore(1);

                new BukkitRunnable(){

                    @Override
                    public void run(){
                        DecimalFormat df = new DecimalFormat("#0.00");
                        double KDR;
                        if(user.getDeaths() == 0){
                            KDR = (double) user.getKills();
                        } else {
                            KDR = (double) user.getKills() / (double) user.getDeaths();
                        }

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
                        scoreboard.getTeam("kdr").setSuffix(String.valueOf(df.format(KDR)));
                        scoreboard.getTeam("killstreak").setSuffix(String.valueOf(PvPEventListener.killstreak.get(uuid)));
                        scoreboard.getTeam("topkillstreak").setSuffix(String.valueOf(user.getBestStreak()));



                    }
                }.runTaskTimer(Core.getInstance(), 10, 60);

                p.setScoreboard(scoreboard);
                break;
            }
        }
    }
}
