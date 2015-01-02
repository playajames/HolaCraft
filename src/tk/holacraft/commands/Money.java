package tk.holacraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Money implements CommandExecutor {

	///// Class Variables
	Main plugin;
	public Money(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				switch (args[0]) {
				case "help":
					player.sendMessage(GlobalData.styleChatServer + "This is the /money help command.");
					break;
				default:
					player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Use /money help for more information.");
					break;
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Use /money help for more information.");
			}
		}
		return false;
	}

}
