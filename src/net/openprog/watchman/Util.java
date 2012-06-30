package net.openprog.watchman;

import org.bukkit.entity.Player;

public class Util {
    private Watchman plugin;

    public boolean anyWatchmen() {
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            if (p.hasPermission("watchman.watchman")) {
                return true;
            }
        }
        return false;
    }

    public void whitelistServer() {
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                plugin.getServer().setWhitelist(true);
                if(plugin.getConfig().getBoolean("kick-all-mode")==true){
                    String kickmessage = plugin.getConfig().getString("kick-message");
                    for (Player p : plugin.getServer().getOnlinePlayers()) {
                        if (!p.isOp() && !p.isWhitelisted()) {
                            p.kickPlayer(kickmessage);
                        }
                    }
                }
            }
        }, s2t(plugin.getConfig().getInt("whitelist-delay")));
    }

    public void unWhitelistServer() {
        plugin.getServer().setWhitelist(false);
    }

    public long s2t(int s) {
        int ticks = s*20;
        return ticks;
    }

    public void reloadWatchmen() {
        if (anyWatchmen()) {
            unWhitelistServer();
        } else {
            whitelistServer();
        }
    }
}