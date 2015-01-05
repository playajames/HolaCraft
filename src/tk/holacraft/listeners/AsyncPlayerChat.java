package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;

public class AsyncPlayerChat implements Listener{
     
	///// Class Variables
	Main plugin;
	public AsyncPlayerChat(Main plugin) {
		this.plugin = plugin;
	}
     
    @EventHandler()
    public void onChat(AsyncPlayerChatEvent event) {
    	Player player = event.getPlayer();
    	if (player.hasPermission("holacraft.chat")) {
    		new Logger(plugin).chat(player, event.getMessage());
    	} else {
    		event.setCancelled(true);
    		player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
    	}
    	
    	if (player.hasMetadata("afk")) {
    		player.removeMetadata("afk", plugin);
    		player.sendMessage(GlobalData.styleChatServer + ChatColor.GRAY + "You are no longer AFK.");
    	}
    	
    	if (player.hasMetadata("muted")) {
    		event.setCancelled(true);
    		player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission chat as you are muted.");
    	}
    }
   
}
