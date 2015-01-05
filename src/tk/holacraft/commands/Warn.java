package tk.holacraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;

public class Warn implements CommandExecutor {

	Main plugin;
	public Warn(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("holacraft.command.warn")) {
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);
						StringBuilder sb = new StringBuilder();
						for (int i = 2; i < args.length; i++){
						sb.append(args[i]).append(" ");
						}
						 
						String reason = sb.toString().trim();
						warn(target, player, reason);
					
				} else {
					player.sendMessage(GlobalData.styleChatServer + "Usage: /warn <player> <reason>");
				}
			}
		}
	
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public void warn(Player player, Player warner, String reason) {
		int warns = player.getMetadata("warns").get(0).asInt();
		int newWarns = warns + 1;
		player.setMetadata("warns", new FixedMetadataValue(plugin,newWarns));
		String warned = GlobalData.styleChatServer + ChatColor.RED + "You have been warned by " + warner.getName() + " for: " + reason;
		String warned2 = GlobalData.styleChatServer + ChatColor.RED + "You have warned " + player.getName() + " for: " + reason;
		String warned3 = GlobalData.styleChatServer + ChatColor.RED + "You have warned " + player.getName() + " for the second time for: " + reason;
		String warned4 = GlobalData.styleChatServer + ChatColor.RED + "You have warned " + player.getName() + " for the third time for: " + reason;
		
		
		
		if(warns == 0) {
			player.sendMessage(warned +  reason);
			
			warner.sendMessage(warned2);
		}
		
		if(warns == 1) {
			player.kickPlayer(warned);
			
			warner.sendMessage(warned3);
		}
		
		if(warns == 2) {
			player.kickPlayer(warned);
			player.setBanned(true);
			
			warner.sendMessage(warned4);
		}
		
		new Logger(plugin).warned(player, warner, reason);
	}
	
}
