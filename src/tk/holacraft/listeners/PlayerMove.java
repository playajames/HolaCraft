package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;

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
		if(player.isFlying()) {
			if(player.getAllowFlight() == false) {
				for(Player staff : GlobalData.playersOnline) {
					new Logger(plugin).hacking(player, "Flight");
					staff.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Player " + player.getName() + " is hacking (Flight), logged.");
				}
			}
		}
	}
}