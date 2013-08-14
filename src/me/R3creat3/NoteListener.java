package me.R3creat3;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Logger;

public class NoteListener implements Listener {

    ModeratorNotes plugin;
    private Logger logger;

    public NoteListener(ModeratorNotes plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void noteListener(PlayerJoinEvent event) {
        String target = event.getPlayer().getName();
        if (plugin.getConfig().contains(target)) {
            log(1, "ALERT: " + target + " has a note attached!");
            log(1, plugin.getConfig().getString(target + ".alert"));

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("notes.alert")) {
                    p.sendMessage(ChatColor.RED + "ALERT: " + target + " has a note attached!");
                    p.sendMessage(plugin.getConfig().getString(target + ".alert"));
                }
            }
        }
    }

    public void setLogger(java.util.logging.Logger logger) {
        this.logger = logger;
    }

    public void log(int priority, String msg) {
        if (logger != null) {
            if (priority == 0) {
                logger.info("[ " + plugin.getName() + " ] " + msg);
            } else {
                if (priority == 1) {
                    logger.warning("[ " + plugin.getName() + " ] " + msg);
                } else {
                    if (priority == 2) {
                        logger.severe("[ " + plugin.getName() + " ] " + msg);
                    }
                }
            }
        }
    }
}
