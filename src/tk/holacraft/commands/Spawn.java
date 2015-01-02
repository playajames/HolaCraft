package tk.holacraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Spawn implements CommandExecutor{

	///// Class Variables
	Main plugin;
	public Spawn(Main plugin) {
		this.plugin = plugin;
	}
	
	public static Location loc;
	
	public void store() {
		plugin.getConfig().set("Spawn.World", loc.getWorld().getName());
		plugin.getConfig().set("Spawn.X", loc.getX());
		plugin.getConfig().set("Spawn.Y", loc.getY());
		plugin.getConfig().set("Spawn.Z", loc.getZ());
		plugin.getConfig().set("Spawn.Pitch", loc.getPitch());
		plugin.getConfig().set("Spawn.Yaw", loc.getYaw());
	}
	
	public Location load() {
		FileConfiguration config = plugin.getConfig();
		World world = Bukkit.getWorld("world");
		if (!world.equals(null)) {
			Double x = config.getDouble("Spawn.X");
			Double y = config.getDouble("Spawn.Y");
			Double z = config.getDouble("Spawn.Z");
			Float pitch = (float) config.getDouble("Spawn.Pitch");
			Float yaw = (float) config.getDouble("Spawn.Yaw");
			Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
			return spawnLoc;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = ((Player) sender).getPlayer();
			if (args.length == 0) {
				BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		        int x = player.getLocation().getBlockX();
		        int y = player.getLocation().getBlockY();
		        int z = player.getLocation().getBlockZ();
		        player.sendMessage(GlobalData.styleChatServer + "Teleporting you to spawn, dont move for 5 seconds.");
		        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
		            @Override
		            public void run() {
		            	if (player.getLocation().getBlockX() == x && player.getLocation().getBlockY() == y && player.getLocation().getBlockZ() == z) {
			            	player.teleport(loc);
							player.sendMessage(GlobalData.styleChatServer + "Teleported to spawn.");
		            	} else {
		            		player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Teleport canceled, you seemed to have moved locations.");
		            	}
		            }
		        }, 100L);
				
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("set")) {
					if (player.isOp()) {
						loc = player.getLocation();
						store();
						// Notify player
						player.sendMessage(GlobalData.styleChatServer + "Spawn location has been set.");
					} else {
						player.sendMessage(GlobalData.styleChatServer + "You don't have permission to use this command.");
					}
				} else {
					player.sendMessage(GlobalData.styleChatServer + "Invalid arguments. Usage /spawn");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + "Invalid arguments. Usage /spawn");
			}
			
			
			return true;
		}
		return false;
	}

}
