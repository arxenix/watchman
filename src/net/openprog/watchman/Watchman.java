package net.openprog.watchman;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Watchman extends JavaPlugin {
    Logger log = Logger.getLogger("Minecraft");
    Util u = new Util();

    @Override
    public void onEnable() {
        loadConfig();
        u.reloadWatchmen();
        log.info(String.format("[Watchman] Successfully enabled version %s!", getDescription().getVersion()));

    }

    @Override
    public void onDisable() {
        log.info(String.format("[Watchman] Successfully disabled version %s!", getDescription().getVersion()));
    }

    public void loadConfig() {
        getConfig().addDefault("whitelist-delay", 0);
        getConfig().addDefault("kick-all-if-no-watchmen-online?", true);
        getConfig().addDefault("kick-message", "There are no watchmen online!");
        getConfig().addDefault("whitelist-message", "There are no watchmen online!");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
