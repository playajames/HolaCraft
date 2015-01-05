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

public class Mute implements CommandExecutor {

	Main plugin;
	public Mute(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("holacraft.command.mute")) {
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);
					if(!target.hasMetadata("muted")) {
						StringBuilder sb = new StringBuilder();
						for (int i = 2; i < args.length; i++){
						sb.append(args[i]).append(" ");
						}
						 
						String reason = sb.toString().trim();
						mute(target, player, reason);
					} else {
						unmute(target, player);
					}
				} else {
					player.sendMessage(GlobalData.styleChatServer + "Usage: /mute <player> <reason>");
				}
			}
		}
		return false;
	}
	
	public void mute(Player player, Player muter, String reason) {
		player.setMetadata("muted", new FixedMetadataValue(plugin,0));
		String muted = GlobalData.styleChatServer + ChatColor.RED + "You have been muted by " + muter.getName() + " for: ";
		String muted2 = GlobalData.styleChatServer + ChatColor.RED + "You have muted " + player.getName() + " for: " + reason;
		
		player.sendMessage(muted +  reason);
		
		muter.sendMessage(muted2);
		
		new Logger(plugin).muted(player, muter, reason);
	}
	
	public void unmute(Player player, Player muter) {
		player.removeMetadata("muted", plugin);
		String muted = GlobalData.styleChatServer + ChatColor.RED + "You have been unmuted by " + muter.getName() + " .";
		String muted2 = GlobalData.styleChatServer + ChatColor.RED + "You have unmuted " + player.getName() + " .";
		
		player.sendMessage(muted);
		
		muter.sendMessage(muted2);
		
		new Logger(plugin).muted(player, muter, "Unmuted");
	}
	
}
