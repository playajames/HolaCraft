package tk.holacraft.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.MySQL;

public class Logger {
	
	///// Class Variables
	Main plugin;
	public Logger(Main plugin) {
		this.plugin = plugin;
	}
	
	public static ArrayList<String> cache = new ArrayList<String>();
	
	final MySQL mysql = new MySQL(plugin, GlobalData.HOST, GlobalData.PORT, GlobalData.DATABASE, GlobalData.USER, GlobalData.PASS);
	Connection c = null;
	
	///// Join Event
	public void join(Player player) {
		String action = "join";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + player.getAddress() + "," + dateNow;
		cache.add(values);
	}
	
	public void hacking(Player player, String type) {
		String action = "hacking";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + type + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + player.getAddress() + "," + dateNow;
		cache.add(values);
	}
	
	public void warned(Player player, Player warner, String reason) {
		String action = "warn";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String query = "INSERT INTO logger (`player_id`, `warner`, `action`, `reason`, `location_x`, `location_y`, `location_z`, `date`) VALUES ('" + player.getMetadata("id").get(0).asInt() + "', '" + warner.getMetadata("id").get(0).asInt() + "', '" + action + "','" + reason + "','" + player.getLocation().getBlockX() + "', '" + player.getLocation().getBlockY() + "', '" + player.getLocation().getBlockZ() + "', '" +  dateNow + "');";
		cache.add(query);
	}
	
	///// Quit Event
	public void quit(Player player) {
		String action = "quit";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + player.getAddress() + "," + dateNow;
		cache.add(values);
	}
	
	///// Block Place Event
	public void blockPlace(Player player, Block block) {
		String action = "blockPlace";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + block.getLocation().getBlockX() + "," + block.getLocation().getBlockY() + "," + block.getLocation().getBlockZ() + "," + block.getType().toString() + "," + dateNow;
		cache.add(values);
	}
	
	///// Block Break Event
	public void blockBreak(Player player, Block block) {
		String action = "blockBreak";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + block.getLocation().getBlockX() + "," + block.getLocation().getBlockY() + "," + block.getLocation().getBlockZ() + "," + block.getType().toString() + "," + dateNow;
		cache.add(values);
	}
	
	///// Shop Buy Event
	public void shopBuy(Player player, String item, String shop) {
		String action = "shopBuy";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + item + "," + shop + "," + dateNow;
		cache.add(values);
	}
	
	///// Shop Sell Event
	public void shopSell(Player player, String item, String shop) {
		String action = "shopSell";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + item + "," + shop + "," + dateNow;
		cache.add(values);
	}
	
	///// Consume Event
	public void consume(Player player, String item) {
		String action = "consume";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + item + "," + dateNow;
		cache.add(values);
	}
	
	///// Chat Event
	public void chat(Player player, String message) {
		String action = "chat";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asString() +","+ action +","+ player.getLocation().getBlockX() +","+ player.getLocation().getBlockY() +","+ player.getLocation().getBlockZ() +","+ message +","+ dateNow;
		cache.add(values);
	}
	
	///// Player Command Event
	public void command(Player player, String message) {
		String action = "command";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + message + "," + dateNow;
		cache.add(values);
	}
	
	///// Kick Event
	public void kick(Player player, String reason) {
		String action = "kick";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String values = player.getMetadata("id").get(0).asInt() + "," + action + "," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "," + reason + "," + dateNow;
		cache.add(values);
	}

	///// Store Logger Data
	public void store() {
		try {
			c = mysql.openConnection();
		} catch (Exception e) {
			plugin.getLogger().warning("Error: Could not open MySQL connection.");
		}
		try {
			if (mysql.checkConnection() == true) {
	        	if (cache.size() > 0) {
		        	for (String values : cache) {
		        		String[] parts = values.split(",");
		        		String player_id = parts[0];
		        		String action = parts[1];
		        		String location_x = parts[2];
		        		String location_y = parts[3];
		        		String location_z = parts[4];
		        		String data = parts[5];
		        		String date = parts[6];
		        		
		        		PreparedStatement ps = c.prepareStatement("INSERT INTO logger (`player_id`, `action`, `location_x`, `location_y`, `location_z`, `data`, `date`) VALUES (?, ?, ?, ?, ?, ?, ?);");
		        		ps.setString(1, player_id);
		        		ps.setString(2, action);
		        		ps.setString(3, location_x);
		        		ps.setString(4, location_y);
		        		ps.setString(5, location_z);
		        		ps.setString(6, data);
		        		ps.setString(7, date);
		        		ps.executeUpdate();
						ps.close();
		    		}
		        	cache.clear();
		        	plugin.getLogger().info("Logger Scheduler: All logger data stored.");
	        	} else {
	        		plugin.getLogger().info("Logger Scheduler: No logger data to store, task canceled.");
	        	}
			} else {
				plugin.getLogger().warning("Logger Scheduler: Error, could not connect to MySQL database..");
			}
		} catch (Exception e) {
			plugin.getLogger().warning("Logger Scheduler: Error, storing logger cache.");
			plugin.getLogger().warning(e.getMessage());
		}
		try {
			mysql.closeConnection();
		} catch (SQLException e) {
			plugin.getLogger().warning("Logger Scheduler: Error, could not close MySQL connection.");
			e.printStackTrace();
		}
	}

	public void muted(Player player, Player muter, String reason) {
		String action = "mute";
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		String query = "INSERT INTO logger (`player_id`, `muter_id`, `action`, `reason`, `location_x`, `location_y`, `location_z`, `date`) VALUES ('" + player.getMetadata("id").get(0).asInt() + "', '" + muter.getMetadata("id").get(0).asInt() + "', '" + action + "','" + reason + "','" + player.getLocation().getBlockX() + "', '" + player.getLocation().getBlockY() + "', '" + player.getLocation().getBlockZ() + "', '" +  dateNow + "');";
		cache.add(query);
	}
}
