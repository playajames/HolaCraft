package tk.holacraft.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import tk.holacraft.Main;

public class ServerTasks {

	///// Class Variables
	Main plugin;
	public ServerTasks(Main plugin) {
		this.plugin = plugin;
	}
	
	/////
	public void storeLocations(ArrayList<Location> locations, String filename) {
		StringBuilder builder = new StringBuilder();
		for (Location l : locations) {
			builder.append(l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + ":");
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
	/////
	public ArrayList<Location> loadLocations(ArrayList<Location> locations, String filename) {
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
						Location loc = new Location(world, x, y, z);
						locations.add(loc);
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
	/////
	public void storeLocationsHashMap(HashMap<Integer, Location> locations, String filename) {
		StringBuilder builder = new StringBuilder();
		for (Entry<Integer, Location> l : locations.entrySet()) {
			builder.append(l.getKey() + "," + l.getValue().getWorld().getName() + "," + l.getValue().getBlockX() + "," + l.getValue().getBlockY() + "," + l.getValue().getBlockZ() + ":");
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
	/////
	public HashMap<Integer, Location> loadLocationsHashMap(HashMap<Integer, Location> locations, String filename) {
		try {
			Scanner file = new Scanner(new File(filename));
			String fileString = file.useDelimiter("\\A").next();
			if (fileString.contains(":")) {
				String[] split = fileString.split(":");
				for (String l : split) {
					if (l.contains(",")) {
						String[] parts = l.split(",");
						int id = Integer.parseInt(parts[0]);
						World world = Bukkit.getWorld(parts[1]);
						int x = Integer.parseInt(parts[2]);
						int y = Integer.parseInt(parts[3]);
						int z = Integer.parseInt(parts[4]);
						Location loc = new Location(world, x, y, z);
						locations.put(id, loc);
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
	
	public String sterilizeLoc(Location loc) {
		String str = loc.getWorld().getName() + "," + loc.getBlockX() + "," +loc.getBlockY() + "," + loc.getBlockZ() + "," + loc.getYaw() + "," + loc.getPitch();		
		return str;
	}
	public Location deSterilizeLoc(String str) {
		String[] parts = str.split(",");
		World world = plugin.getServer().getWorld(parts[0]);
		double x = Double.parseDouble(parts[1]);
		double y = Double.parseDouble(parts[2]);
		double z = Double.parseDouble(parts[3]);
		float yaw = Float.parseFloat(parts[4]);
		float pitch = Float.parseFloat(parts[5]);
		Location loc = new Location(world, x, y, z, yaw, pitch);
		return loc;
	}
	/////
	public int getRandomInt(int min, int max) {
		  return (int) (Math.floor(Math.random() * (max - min)) + min);
		}
}
