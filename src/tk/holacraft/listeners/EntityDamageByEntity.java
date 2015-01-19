package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tk.holacraft.Main;
import tk.holacraft.handlers.Clans;

public class EntityDamageByEntity implements Listener{	
	///// Class Variables
	Main plugin;
	public EntityDamageByEntity(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
		// Clans friendly check
		if (new Clans().isFriendly(event)) {
			event.setCancelled(true);
		}	
	}
}
