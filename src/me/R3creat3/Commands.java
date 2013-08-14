package me.R3creat3;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;

import java.util.logging.Logger;

public class Commands {

    ModeratorNotes plugin;
    private Logger logger;

    public Commands(ModeratorNotes plugin) {
        this.plugin = plugin;
    }

    @Command(aliases = {"attach", "note"},
            min = 2,
            desc = "Attach an alert to a player",
            usage = "<player> <message>")
    @CommandPermissions("notes.attach")
    public void attach(CommandContext args, CommandSender sender) {
        String message = args.getJoinedStrings(1);
        String target = args.getString(0);
        plugin.getConfig().set(args.getString(0) + ".alert", sender.getName() + ": " + message);
        sender.sendMessage(ChatColor.RED + "Attached the note. Previous note(s) were deleted.");
        log(0, sender.getName() + " attached a note to " + target + ": " + message);
        plugin.saveConfig();
    }

    @Command(aliases = {"detach", "delnote"},
            min = 1,
            max = 1,
            desc = "Detach an alert from a player",
            usage = "<player>")
    @CommandPermissions("notes.detach")
    public void detach(CommandContext args, CommandSender sender) {
        if (plugin.getConfig().contains(args.getString(0))) {
            String target = args.getString(0);
            plugin.getConfig().set(args.getString(0), null);
            sender.sendMessage(ChatColor.RED + "Detached any notes on the player.");
            log(0, sender.getName() + " detached all notes from " + target);
            plugin.saveConfig();
        }
    }

    @Command(aliases = {"find", "findnote"},
            min = 1,
            max = 1,
            desc = "Find notes about a player",
            usage = "<player>")
    @CommandPermissions("notes.find")
    public void find(CommandContext args, CommandSender sender) {
        if (plugin.getConfig().contains(args.getString(0))) {
            String target = args.getString(0);
            sender.sendMessage(ChatColor.RED + "Notes for " + target + ": ");
            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString(target + ".alert"));
            log(0, sender.getName() + " looked up notes for " + target);
            plugin.saveConfig();
        } else {
            sender.sendMessage(ChatColor.RED + "There are no notes about this player!");
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
