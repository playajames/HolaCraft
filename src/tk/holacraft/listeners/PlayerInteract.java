package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import tk.holacraft.Main;

public class PlayerInteract implements Listener {
	
	///// Class Variables
	Main plugin;
	public PlayerInteract(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		//Player player = (Player) event.getPlayer();
		
		if (event.getClickedBlock() != null) {
//			Block block = event.getClickedBlock();
		}
	}
}
