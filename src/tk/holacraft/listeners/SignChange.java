package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import tk.holacraft.Main;

public class SignChange implements Listener {

	///// Class Variables
	Main plugin;
	public SignChange(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		
	}
}
