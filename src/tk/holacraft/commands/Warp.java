package tk.holacraft.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Warp implements CommandExecutor {
	
	public static HashMap<String, Location> warps = new HashMap<String, Location>();

	///// Class Variables
	Main plugin;
	public Warp(Main plugin) {
		this.plugin = plugin;
	}
	
	public void store() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, Location> entry : warps.entrySet()) {
			String key = entry.getKey();
		    Location l = entry.getValue();
		    builder.append(key + "," + l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + "," + l.getYaw() + "," + l.getPitch() + ":");
		}
		try {
			PrintWriter out = new PrintWriter("warps.bin");
			out.println(builder.toString());
			out.close();
			plugin.getLogger().info("Warp locations edited, locations have been stored.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Location> load() {
		HashMap<String, Location> map = new HashMap<String, Location>();
		try {
			Scanner file = new Scanner(new File("warps.bin"));
			String fileString = file.useDelimiter("\\A").next();
			if (fileString.contains(":")) {
				String[] split = fileString.split(":");
				for (String l : split) {
					if (l.contains(",")) {
						String[] parts = l.split(",");
						String name = parts[0];
						World world = Bukkit.getWorld(parts[1]);
						double x = Double.parseDouble(parts[2]);
						double y = Double.parseDouble(parts[3]);
						double z = Double.parseDouble(parts[4]);
						float yaw = Float.parseFloat(parts[5]);
						float pitch = Float.parseFloat(parts[6]);
						Location loc = new Location(world, x, y, z, yaw, pitch);
						map.put(name, loc);
					}
				}
			}
			plugin.getLogger().info("Warp locations have been loaded.");
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				switch (args[0]) {
				case "list":
					if (player.hasPermission("holacraft.command.warp.list")) {
						int count = 1;
						player.sendMessage(GlobalData.styleChatServer + "Warp List");
						for (String warp : warps.keySet()) {
							player.sendMessage(ChatColor.DARK_AQUA +""+ count + ". " + warp);
							count++;
						}
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
					}
					break;
					
				case "create":
					if (player.hasPermission("holacraft.command.warp.create")) {
						if (args.length == 2) {
							if (!warps.containsKey(args[1].toLowerCase())) {
								warps.put(args[1].toLowerCase(), player.getLocation());
								store();
								player.sendMessage(GlobalData.styleChatServer + "Warp created!");
							} else {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "That warp already exists. You must delete it first.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /warp create <warp-name>");
						}
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
					}
					break;
				
				case "delete":
					if (player.hasPermission("holacraft.command.warp.delete")) {
						if (args.length == 2) {
							if (warps.containsKey(args[1].toLowerCase())) {
								warps.remove(args[1].toLowerCase());
								store();
								player.sendMessage(GlobalData.styleChatServer + "Warp deleted!");
							} else {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "That warp was not found.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /warp delete <warp-name>");
						}
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
					}
					break;
					
				default:
					if (player.hasPermission("holacraft.command.warp")) {
						if (args.length == 1) {
							if (warps.containsKey(args[0].toLowerCase())) {
								BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
								int x = player.getLocation().getBlockX();
						        int y = player.getLocation().getBlockY();
						        int z = player.getLocation().getBlockZ();
								player.sendMessage(GlobalData.styleChatServer + "Teleporting you to " + args[0].toLowerCase() + ", dont move for 5 seconds.");
								scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
						            @Override
						            public void run() {
						            	if (player.getLocation().getBlockX() == x && player.getLocation().getBlockY() == y && player.getLocation().getBlockZ() == z) {
						            		player.teleport(warps.get(args[0]));
											player.sendMessage(GlobalData.styleChatServer + "You have been teleported to " + args[0].toLowerCase() + ".");
						            	} else {
						            		player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Teleport canceled, you seemed to have moved locations.");
						            	}
						            }
						        }, 100L);
							} else {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Warp not found.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /warp <warp-name>");
						}
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
					}
					break;
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /warp <warp-name>");
			}
			return true;
		}
		return false;
	}
}
