package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import tk.holacraft.commands.Spawn;

public class PlayerRespawn implements Listener{

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		event.setRespawnLocation(Spawn.loc);
	}
	
}
