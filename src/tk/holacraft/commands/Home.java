package tk.holacraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Home implements CommandExecutor {
	
	///// Class Variables
	Main plugin;
	public Home(Main plugin) {
		this.plugin = plugin;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("holacraft.command.home")) {
				if (args.length > 0) {
					switch (args[0]) {
					// creative Command	
					case "set":
						if (player.getMetadata("homes").get(0).asInt() == 3) {
							
						}
					break;
					// Clear Inventory
					}
				}
			}
		}
		return false;
	}
	
}
