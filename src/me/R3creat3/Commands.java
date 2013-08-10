package me.R3creat3;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class Commands{
    Notes plugin;

    public Commands(Notes pl) {
        super();
        plugin = pl;
    }

    @Command(aliases = {"attach", "note"},
            min = 2,
            desc = "Attach an alert to a player",
            usage = "<player> <message>")
    @CommandPermissions("notes.attach")
    public void attach(CommandContext args, CommandSender sender) {
        String message = args.getJoinedStrings(1);
        plugin.getConfig().set(args.getString(0)+".alert", sender.getName() + ": " + message);
        sender.sendMessage(ChatColor.RED + "Attached the note. Previous note(s) were deleted.");
        plugin.saveConfig();
    }
    @Command(aliases = {"detach", "delnote"},
            min = 1,
            max = 1,
            desc = "Detach an alert from a player",
            usage = "<player>")
    @CommandPermissions("notes.detach")
    public void detach(CommandContext args, CommandSender sender) {
        if(plugin.getConfig().contains(args.getString(0))){
        plugin.getConfig().set(args.getString(0), null);
        sender.sendMessage(ChatColor.RED + "Detached any notes on the player.");
        plugin.saveConfig();
        }
    }
}
