package net.openprog.watchman.listener;

import net.openprog.watchman.Util;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class WhitelistingListener extends JavaPlugin implements Listener {
    Util u = new Util();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("watchman.watchman") && u.anyWatchmen() == false) {
            u.whitelistServer();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("watchman.watchman") && u.anyWatchmen() == false) {
            u.unWhitelistServer();
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (event.getResult() == Result.KICK_WHITELIST && u.anyWatchmen() == false) {
            event.setKickMessage((String) getConfig().getString("whitelist-message"));
        }
    }
}
