package tk.holacraft.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;


public class Chat {

	///// Class Variables
	Main plugin;
	public Chat(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public void onChat(PlayerChatEvent e) {
		Player player = e.getPlayer();
		if(player.hasMetadata("afk")) {
			player.setMetadata("afk", new FixedMetadataValue(plugin, 0));
			player.sendMessage(GlobalData.styleChatServer + ChatColor.GRAY + "You are no-longer AFK.");
		}
	}
}
