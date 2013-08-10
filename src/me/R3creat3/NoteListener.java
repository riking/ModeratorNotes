package me.R3creat3;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NoteListener implements Listener{
    Notes plugin;
    public NoteListener(Notes pl){
        super();
        plugin = pl;
    }
    @EventHandler
    public void noteListener(PlayerJoinEvent event){
        String target = event.getPlayer().getName();
        if(plugin.getConfig().contains(target)){
            System.out.println("ALERT: This player has a note attached.");
            System.out.println(plugin.getConfig().getString(target+".alert"));
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.hasPermission("notes.alert")){
                    p.sendMessage(ChatColor.RED+"ALERT: This player has a note attached.");
                    p.sendMessage(plugin.getConfig().getString(target+".alert"));
                }
            }
        }
    }
}
