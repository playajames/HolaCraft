package tk.holacraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.commands.Spawn;
import tk.holacraft.handlers.Logger;
import tk.holacraft.handlers.Permissions;
import tk.holacraft.handlers.Scoreboard;

public class PlayerJoin implements Listener {

	///// Class Variables
	Main plugin;
	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
	}
	///// On Join
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		GlobalData.playersOnline.add(player);
		new Logger(plugin).join(player);
		new Scoreboard(plugin).updateScoreboard(player);
		new Permissions(plugin).set(player);
		
		
		event.setJoinMessage(GlobalData.styleChatServer + player.getName() + " has joined the server.");
		
		///// Check for first time join
		if (player.getMetadata("joins").get(0).asInt() == 1) {
			player.sendMessage(GlobalData.styleChatServer + "Welcome to the server " + player.getDisplayName() + ".");
			player.teleport(Spawn.loc);
		} else {
			player.sendMessage(GlobalData.styleChatServer + "Welcome back to the server " + player.getDisplayName() + ".");
		}
	}
}
