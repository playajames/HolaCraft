package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

@SuppressWarnings("deprecation")
public class PlayerChat implements Listener {

///// Class Variables
	Main plugin;
	public PlayerChat(Main plugin) {
		this.plugin = plugin;
	}
	
	public void onChat(PlayerChatEvent e) {
		Player player = e.getPlayer();
		if(player.hasMetadata("afk")) {
			player.setMetadata("afk", new FixedMetadataValue(plugin, 0));
			player.sendMessage(GlobalData.styleChatServer + ChatColor.GRAY + "You are no-longer AFK.");
		}
	}
}
