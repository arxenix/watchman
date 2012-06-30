package net.openprog.watchman.listener;

import net.openprog.watchman.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class WhitelistingListener implements Listener {
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
}
