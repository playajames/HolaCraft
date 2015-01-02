package tk.holacraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.Main;

public class PlayerDeath implements Listener {
	
	///// Class Variables
	Main plugin;
	public PlayerDeath(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = event.getEntity();
			
			// Add to death count
			int resultD = player.getMetadata("deaths").get(0).asInt() + 1;
			player.setMetadata("deaths", new FixedMetadataValue(plugin,resultD));
			
			if (player.getKiller() != null) {
				Player killer = player.getKiller();
				int resultK = killer.getMetadata("kills").get(0).asInt() + 1;
				killer.setMetadata("kills", new FixedMetadataValue(plugin,resultK));
			}
		}
	}
}
