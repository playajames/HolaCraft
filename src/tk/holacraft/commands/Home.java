package tk.holacraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.ServerTasks;

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
					// Set Command	
					case "set":
						if (args.length == 2) {
							setHome(player, args[1]);
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage: /home set <home_name>");
						}
					break;
					}
				}
			}
		}
		return false;
	}
	
	public void setHome(Player player, String name) {
		String homes = player.getMetadata("homes").get(0).asString();
		String[] parts = homes.split(":");
		if (player.getMetadata("group_home_limit").get(0).asInt() > parts.length) {
			player.sendMessage("You ended up here.");
			homes = homes + name + "," +new ServerTasks(plugin).sterilizeLoc(player.getLocation()) + ":";
			System.out.println(parts.length);
			player.setMetadata("homes", new FixedMetadataValue(plugin,homes));
		} else {
			player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You have already reached your homes limit. Use /remhome <home_name> to remove a home.");
		}
	}
	
}
