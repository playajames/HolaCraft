package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public class WorldChunkUnload implements Listener{

		@EventHandler
		public void onChunkUnload(ChunkUnloadEvent event) {
//			if (GlobalData.chunkForceLoaded.contains(event.getChunk())) {
//				event.setCancelled(true);
//				event.getChunk().load();
//				Bukkit.getServer().broadcastMessage("Chunk unload canceled.");
//			}
		}
	}
