package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

import tk.holacraft.Main;

public class WorldLoad implements Listener{
	///// Class Variables
	Main plugin;
	public WorldLoad(Main plugin) {
		this.plugin = plugin;
	}
	///// On Quit
	@EventHandler
	public void onWorldLoad(WorldLoadEvent event) {
		
	}
	
}
