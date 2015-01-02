package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class PlayerMove implements Listener{

	///// Class Variables
	Main plugin;
	public PlayerMove(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasMetadata("afk")) {
			player.removeMetadata("afk", plugin);
			player.sendMessage(GlobalData.styleChatServer + ChatColor.GRAY + "You are no longer AFK.");
		}
	}
}