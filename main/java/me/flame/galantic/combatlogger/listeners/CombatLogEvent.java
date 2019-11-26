package me.flame.galantic.combatlogger.listeners;

import me.flame.galantic.combatlogger.CombatLoggerManager;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

public class CombatLogEvent implements Listener {

    private final CombatLoggerManager combatLoggerManager = new CombatLoggerManager();
    private ArrayList<UUID> combatLogged = new ArrayList<>();

    @EventHandler
    public void QuitInComat(PlayerQuitEvent e){
        Player p = e.getPlayer();

        if(combatLoggerManager.hasActiveCombat(p)){
            combatLoggerManager.removeCombat(p);
            combatLogged.add(p.getUniqueId());

            Player target = Bukkit.getServer().getPlayer(PvPEventListener.inFight.get(p.getUniqueId()));
            target.sendMessage(ChatUtils.format("&c" + p.getName() + " &7is uitgelogd! Je hebt hier &c1 &7kill voor gekregen."));
            for(SQLUser user : SQLUserManager.userList){
                if(user.getUuid() == target.getUniqueId()){
                    user.setKills(user.getKills() + 1);
                    user.setPvpCoins(user.getPvpCoins() + 0.5);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void JoinedAfterCombatEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(combatLogged.contains(p.getUniqueId())){
            p.sendMessage(ChatUtils.format("&7Welcome back! Please do not combat log again."));
            p.sendMessage(ChatUtils.format("&8» &7Punishment: &c-5 Coins &7& &c+2 Deaths"));
            for(SQLUser user : SQLUserManager.userList){
                if(user.getUuid() == p.getUniqueId()){
                    user.setDeaths(user.getDeaths() + 2);
                    if(user.getPvpCoins() - 5 < 0){
                        user.setPvpCoins(0);
                        break;
                    }
                    user.setPvpCoins(user.getPvpCoins() - 5);
                    break;
                }
            }
            combatLogged.remove(p.getUniqueId());
        }
    }
}
