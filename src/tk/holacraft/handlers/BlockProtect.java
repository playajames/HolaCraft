package tk.holacraft.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import tk.holacraft.Main;

public class BlockProtect {
	
	public static HashMap<Location, Integer> protectedBlocks = new HashMap<Location, Integer>();
	
	///// Class Variables
	Main plugin;
	public BlockProtect(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean checkProtect(Player player, Block block) {
		if (player.hasPermission("hola.blockprotect.break.any")) {
			return false;
		}
		if (protectedBlocks.containsKey(block.getLocation())) {
			if (player.getMetadata("id").get(0).asInt() == protectedBlocks.get(block.getLocation())) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public void setProtected(Player player, Block block) {
		protectedBlocks.put(block.getLocation(), player.getMetadata("id").get(0).asInt());
	}
	
	public void removeProtected(Player player, Block block) {
		protectedBlocks.remove(block.getLocation());
	}
	
	public void storeMap() {
		String filename = "protectedBlocks.bin";
		StringBuilder builder = new StringBuilder();
		for (Entry<Location, Integer> l : protectedBlocks.entrySet()) {
			builder.append(l.getKey().getWorld().getName() + "," + l.getKey().getBlockX() + "," + l.getKey().getBlockY() + "," + l.getKey().getBlockZ() + "," + l.getValue() + ":");
		}
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println(builder.toString());
			out.close();
			plugin.getLogger().info(filename + " have been stored.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Location, Integer> loadMap() {
		HashMap<Location, Integer> locations = new HashMap<Location, Integer>();
		String filename = "protectedBlocks.bin";
		try {
			Scanner file = new Scanner(new File(filename));
			String fileString = file.useDelimiter("\\A").next();
			if (fileString.contains(":")) {
				String[] split = fileString.split(":");
				for (String l : split) {
					if (l.contains(",")) {
						String[] parts = l.split(",");
						World world = Bukkit.getWorld(parts[0]);
						int x = Integer.parseInt(parts[1]);
						int y = Integer.parseInt(parts[2]);
						int z = Integer.parseInt(parts[3]);
						int value = Integer.parseInt(parts[4]);
						Location loc = new Location(world, x, y, z);
						locations.put(loc, value);
					}
				}
			}
			plugin.getLogger().info(filename + " have been loaded.");
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return locations;
	}
}
