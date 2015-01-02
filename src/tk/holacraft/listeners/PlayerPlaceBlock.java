package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.BlockProtect;
import tk.holacraft.handlers.Logger;
import tk.holacraft.handlers.items.Items;

public class PlayerPlaceBlock implements Listener {

	///// Class Variables
	Main plugin;
	public PlayerPlaceBlock(Main plugin) {
		this.plugin = plugin;
	}

	///// On Place
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		
		///// Method Variables
		Block block = event.getBlock();
		Player player = event.getPlayer();
		//ItemStack item = event.getItemInHand();
		
		if (player.getInventory().contains(Items.ProtectGem)) {
			player.sendMessage(GlobalData.styleChatServer + "Placed protected block.");
			new BlockProtect(plugin).setProtected(player, block);
		}
		
		///// Log Event
		new Logger(plugin).blockPlace(player, block);
		
		// TNT Check
		if (block.getType().equals(Material.TNT)) {
			if (!player.hasPermission("holacraft.place.tnt")) {
				event.setCancelled(true);
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
			}
		}
		
		// Block Place Count
		int result = player.getMetadata("blockPlaceCount").get(0).asInt() + 1;
		player.setMetadata("blockPlaceCount", new FixedMetadataValue(plugin,result));
	}
}
