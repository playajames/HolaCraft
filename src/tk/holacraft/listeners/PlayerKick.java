package tk.holacraft.listeners;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;
import tk.holacraft.handlers.Permissions;
import tk.holacraft.handlers.PlayerData;

public class PlayerKick implements Listener {

	///// Class Variables
	Main plugin;
	public PlayerKick(Main plugin) {
		this.plugin = plugin;
	}
	///// On Kick
	@EventHandler
	public void onKick(PlayerKickEvent event) throws SQLException {
		Player player = event.getPlayer();
		GlobalData.playersOnline.remove(player);
		new Logger(plugin).kick(player, null);
		new Permissions(plugin).remove(player);
		
		if (plugin.getConfig().getBoolean("MySQL.enabled")) {
			if (new PlayerData(plugin).store(player)) {
				plugin.getLogger().info("(" + player.getDisplayName() + ") Successfuly saved data.");
			} else {
				plugin.getLogger().warning("(" + player.getDisplayName() + ") Failed to save data.");
			}
		}
	}
}
