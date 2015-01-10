package tk.holacraft.listeners;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;
import tk.holacraft.handlers.Permissions;
import tk.holacraft.handlers.PlayerData;
import tk.holacraft.handlers.Scoreboard;

public class PlayerQuit implements Listener {

	///// Class Variables
	Main plugin;
	public PlayerQuit(Main plugin) {
		this.plugin = plugin;
	}
	
	///// On Quit
	@EventHandler
	public void onQuit(PlayerQuitEvent event) throws SQLException{
		Player player = event.getPlayer();
		GlobalData.playersOnline.remove(player);
		new Logger(plugin).quit(player);
		new Permissions(plugin).remove(player);
		new Scoreboard(plugin).updateAll();

		event.setQuitMessage(GlobalData.styleChatServer + player.getName() + " has left the server.");
		if (plugin.getConfig().getBoolean("MySQL.enabled")) {
			if (new PlayerData(plugin).store(player)) {
				plugin.getLogger().info("(" + player.getDisplayName() + ") Successfuly saved data.");
			} else {
				plugin.getLogger().warning("(" + player.getDisplayName() + ") Failed to save data.");
			}
		}
	}
}
