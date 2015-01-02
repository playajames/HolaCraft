package tk.holacraft.listeners;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.PlayerData;

public class PlayerLogin implements Listener {

	///// Class Variables
	Main plugin;
	public PlayerLogin(Main plugin) {
		this.plugin = plugin;
	}

	///// On Login
	@EventHandler
	public void onLogin(PlayerLoginEvent event) throws SQLException {
		
		///// Method Variables
		Player player = event.getPlayer();
		
		///// Load Data
		if (plugin.getConfig().getBoolean("MySQL.enabled")) {
			if (new PlayerData(plugin).load(player) && new PlayerData(plugin).addJoinCount(player)) {
				plugin.getLogger().info("(" + player.getDisplayName() + ") Loaded data successfuly.");
			} else {
				event.disallow(null, GlobalData.styleChatServer + ChatColor.RED + "There was a problem loading your data. Contact an administrator for further assistance.");
				plugin.getLogger().info("(" + player.getDisplayName() + ") Failed to load data.");
				return;
			}
		}
	}
}
