package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.BlockProtect;
import tk.holacraft.handlers.Logger;

public class PlayerBreakBlock implements Listener{

	///// Class Variables
	Main plugin;
	public PlayerBreakBlock(Main plugin) {
		this.plugin = plugin;
	}

	///// On Break
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		///// Method Variables
		Block block = event.getBlock();
		Player player = event.getPlayer();
		//WorldGuardPlugin worldGuard = new WorldGuard(plugin).getWorldGuard();
		new Logger(plugin).blockBreak(player, block);
		
		if (!new BlockProtect(plugin).checkProtect(player, block)) {
			new BlockProtect(plugin).removeProtected(player, block);
		} else {
			event.setCancelled(true);
			player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Sorry but that block is protected.");
		}
		
		// Block Break Count
		int result = player.getMetadata("blockBreakCount").get(0).asInt() + 1;
		player.setMetadata("blockBreakCount", new FixedMetadataValue(plugin,result));
	}
}
