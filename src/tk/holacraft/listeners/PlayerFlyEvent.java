package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;

public class PlayerFlyEvent implements Listener {
	
	Main plugin;
	public PlayerFlyEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	public void onPlayerFly(PlayerMoveEvent event) {
		Player player = event.getPlayer();
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
