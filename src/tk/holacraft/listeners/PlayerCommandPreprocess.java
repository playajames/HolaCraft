package tk.holacraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;

public class PlayerCommandPreprocess implements Listener {
	
	///// Class Variables
	Main plugin;
	public PlayerCommandPreprocess(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		if (event.getPlayer() instanceof Player) {
			Player player = event.getPlayer();
			new Logger(plugin).command(player, event.getMessage());
			
			// Handle Ban Command
			if (event.getMessage().startsWith("/ban") && player.hasPermission("bukkit.command.ban.player")) {
				player.sendMessage("Here");
			}
		}
	}
}
