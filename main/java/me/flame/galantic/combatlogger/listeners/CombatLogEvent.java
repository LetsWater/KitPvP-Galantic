package me.flame.galantic.combatlogger.listeners;

import me.flame.galantic.combatlogger.CombatLoggerManager;
import me.flame.galantic.listeners.PvPEventListener;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;
import me.flame.galantic.utils.ChatUtils;
import me.flame.galantic.utils.FileManager;
import me.galantic.galanticcore.api.CoreAPI;

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
	private ArrayList< UUID > combatLogged = new ArrayList<>();

	@EventHandler
	public void QuitInComat( PlayerQuitEvent e ) {
		Player p = e.getPlayer();

		if ( combatLoggerManager.hasActiveCombat( p ) ) {
			combatLogged.add( p.getUniqueId() );

			Player target = Bukkit.getServer().getPlayer( PvPEventListener.inFight.get( p.getUniqueId() ) );
			CoreAPI.getMessageManager().sendMessage( target, "combat_logged", p.getName() );
			for ( SQLUser user : SQLUserManager.userList ) {
				if ( user.getUuid() == target.getUniqueId() ) {

					SQLUserManager.getInstance().addRewards(user.getUuid());

					break;
				}
			}

			combatLoggerManager.removeCombat( p );
		}
	}

	@EventHandler
	public void JoinedAfterCombatEvent( PlayerJoinEvent e ) {
		Player p = e.getPlayer();
		if ( combatLogged.contains( p.getUniqueId() ) ) {
			CoreAPI.getMessageManager().sendMessage( p, "combat_logged_welcome_back",
					FileManager.get( "config.yml" ).getInt( "PvP-Settings.coins-per-combatlog" ) );
			for ( SQLUser user : SQLUserManager.userList ) {
				if ( user.getUuid() == p.getUniqueId() ) {
					user.setDeaths( user.getDeaths() + 2 );
					if ( user.getPvpCoins() - FileManager.get( "config.yml" ).getInt( "PvP-Settings.coins-per-combatlog" ) < 0 ) {
						user.setPvpCoins( 0 );
						break;
					}
					user.setPvpCoins( user.getPvpCoins() - FileManager.get( "config.yml" ).getInt( "PvP-Settings.coins-per-combatlog" ) );
					break;
				}
			}
			combatLogged.remove( p.getUniqueId() );
		}
	}
}
