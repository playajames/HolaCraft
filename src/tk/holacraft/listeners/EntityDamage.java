package tk.holacraft.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tk.holacraft.Main;
import tk.holacraft.handlers.WorldGuard;

public class EntityDamage implements Listener{
	
	///// Class Variables
	Main plugin;
	public EntityDamage(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamage(final EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player player = (Player)entity;
			
			if (new WorldGuard(plugin).inRegion(player.getLocation())) {
				event.setCancelled(true);
			}
		}
	}
}