package tk.holacraft.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.MySQL;


public class PlayerData {
	
	///// Class Variables
	Main plugin;
	public PlayerData(Main plugin) {
		this.plugin = plugin;
	}
	
	final MySQL mysql = new MySQL(plugin, GlobalData.HOST, GlobalData.PORT, GlobalData.DATABASE, GlobalData.USER, GlobalData.PASS);
	Connection c = null;
	
	///// Create Player Data
	public boolean createPlayer(Player player) throws SQLException {
		Date d = new Date();
		String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		try {
			c= mysql.openConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		if (mysql.checkConnection()) {
			try {
				Statement s = c.createStatement();
				s.executeUpdate("INSERT INTO players" 
									+ "("
										+ "`playername`,"
										+ "`UUID`,"
										+ "`email`,"
										+ "`group`,"
										+ "`clan`,"
										+ "`infractions`,"
										+ "`money`,"
										+ "`points`,"
										+ "`homes`,"
										+ "`blockPlaceCount`,"
										+ "`blockBreakCount`,"
										+ "`kills`,"
										+ "`monster_kills`,"
										+ "`deaths`,"
										+ "`join_date`,"
										+ "`joins`,"
										+ "`time_played`,"
										+ "`password`,"
										+ "`ban`"
									+ ")"
									+ "VALUES ("
										+ "'" + player.getDisplayName() + "',"
										+ "'" + player.getUniqueId().toString() + "',"
										+ "NULL,"
										+ "1,"
										+ "NULL,"
										+ "0,"
										+ "500,"
										+ "0,"
										+ "NULL,"
										+ "0,"
										+ "0,"
										+ "0,"
										+ "0,"
										+ "0,"
										+ "'" + dateNow + "',"
										+ "0,"
										+ "0,"
										+ "NULL,"
										+ "0"
									+ ");");
				ResultSet rsCreate = s.executeQuery("SELECT id FROM players WHERE UUID = '" + player.getUniqueId() + "';");
				rsCreate.next();
				//int player_id = rsCreate.getInt("id");
				rsCreate.close();
				plugin.getLogger().info(player.getDisplayName() + " created data successfully.");
				new PlayerData(plugin).load(player);
			} catch (SQLException e) {
				plugin.getLogger().warning(e.getMessage().toString());;
				plugin.getLogger().warning(player.getDisplayName() + " created data unsuccessfully.");
				mysql.closeConnection();
				return false;
			}
		}
		mysql.closeConnection();
		return true;
	}
	
	///// Load Player Data
	public boolean load(Player player) throws SQLException {
		if (GlobalData.pluginInitialized) {
			//Date d = new Date();
			//String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			try {
				c = mysql.openConnection();
			} catch (Exception e) {
				e.printStackTrace();
				plugin.getLogger().warning("Error: Could not open MySQL connection.");
				return false;
			}
			if (mysql.checkConnection()) {
				try {
					//Get player data from database.
					Statement s = c.createStatement();
					ResultSet rsPlayer = s.executeQuery("SELECT * FROM players WHERE UUID = '" + player.getUniqueId() + "';");
					//Check if player has row created in database.
					if (!rsPlayer.first()) {
						if (!new PlayerData(plugin).createPlayer(player)) {
							return false;
						}
						return true;
					}
					//Values stored in database
					player.setMetadata("id", new FixedMetadataValue(                plugin,rsPlayer.getInt("id")));
					player.setMetadata("custom_name", new FixedMetadataValue(       plugin,rsPlayer.getInt("custom_name")));
					player.setMetadata("email", new FixedMetadataValue(             plugin,rsPlayer.getString("email")));
					player.setMetadata("group", new FixedMetadataValue(             plugin,rsPlayer.getInt("group")));
					player.setMetadata("clan", new FixedMetadataValue(              plugin,rsPlayer.getInt("clan")));
					player.setMetadata("infractions", new FixedMetadataValue(       plugin,rsPlayer.getInt("infractions")));
					player.setMetadata("money", new FixedMetadataValue(             plugin,rsPlayer.getInt("money")));
					player.setMetadata("points", new FixedMetadataValue(            plugin,rsPlayer.getInt("points")));
					player.setMetadata("homes", new FixedMetadataValue(             plugin,rsPlayer.getString("homes")));
					player.setMetadata("kills", new FixedMetadataValue(             plugin,rsPlayer.getInt("kills")));
					player.setMetadata("monster_kills", new FixedMetadataValue(     plugin,rsPlayer.getInt("monster_kills")));
					player.setMetadata("deaths", new FixedMetadataValue(            plugin,rsPlayer.getInt("deaths")));
					player.setMetadata("blockPlaceCount", new FixedMetadataValue(   plugin,rsPlayer.getInt("blockPlaceCount")));
					player.setMetadata("blockBreakCount", new FixedMetadataValue(   plugin,rsPlayer.getInt("blockBreakCount")));
					player.setMetadata("join_date", new FixedMetadataValue(         plugin,rsPlayer.getString("join_date")));
					player.setMetadata("joins", new FixedMetadataValue(             plugin,rsPlayer.getString("joins")));
					player.setMetadata("time_played", new FixedMetadataValue(       plugin,rsPlayer.getInt("time_played")));
					player.setMetadata("password", new FixedMetadataValue(          plugin,rsPlayer.getInt("password")));
					player.setMetadata("ban", new FixedMetadataValue(               plugin,rsPlayer.getInt("ban")));
					//Temporary values not stored in database
					player.setMetadata("stored_time", new FixedMetadataValue(          plugin,new Date().getTime()));
					rsPlayer.close();
				} catch (SQLException e) {
					e.printStackTrace();
					mysql.closeConnection();
					return false;
				}
				///// Get Group Data
				try {
					Statement s = c.createStatement();
					ResultSet rsGroup = s.executeQuery("SELECT * FROM groups WHERE id = '" + player.getMetadata("group").get(0).asInt() + "';");
					if (rsGroup.first()) {
						player.setMetadata("group_name", new FixedMetadataValue(       plugin,rsGroup.getString("group_name")));
						player.setMetadata("group_tag", new FixedMetadataValue(        plugin,rsGroup.getString("tag")));
						player.setMetadata("group_home_limit", new FixedMetadataValue( plugin,rsGroup.getString("home_limit")));
						if (player.getMetadata("custom_name").get(0).asString().length() > 1) {
							player.setDisplayName(rsGroup.getString("tag") + player.getMetadata("custom_name").get(0).asString());
						} else {
							player.setDisplayName(rsGroup.getString("tag") + player.getDisplayName());
						}
						rsGroup.close();
					} else {
						rsGroup.close();
						mysql.closeConnection();
						return false;
					}
				} catch (SQLException e) {
					mysql.closeConnection();
					e.printStackTrace();
					return false;
				}
				///// Disconnect from Database
				mysql.closeConnection();
			} else {
				plugin.getLogger().warning("Error: Could not connect to MySQL database");
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	///// Store Player Data
	public boolean store(Player player) throws SQLException {
		try {
			c = mysql.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
			plugin.getLogger().warning("Error: Could not open MySQL connection.");
			return false;
		}
		// Time Played Variables
		long timeNow = new Date().getTime();
		long storedTime = player.getMetadata("stored_time").get(0).asLong();
		long miliseconds = timeNow - storedTime;
		long seconds = miliseconds / 1000;
		long timePlayed = player.getMetadata("time_played").get(0).asLong() + seconds;
		player.setMetadata("stored_time", new FixedMetadataValue(plugin,new Date().getTime()));
		player.setMetadata("time_played", new FixedMetadataValue(plugin,timePlayed));
		if (mysql.checkConnection()) {
			try {
				Statement s = c.createStatement();
				s.executeUpdate("	UPDATE players SET"
									+ "   custom_name = '"      + player.getMetadata("custom_name").get(0).asString()
									+ "', email = '"            + player.getMetadata("email").get(0).asString()
									+ "', `group` = '"          + player.getMetadata("group").get(0).asInt() 
									+ "', clan = '"             + player.getMetadata("clan").get(0).asInt() 
									+ "', infractions = '"      + player.getMetadata("infractions").get(0).asInt()
									+ "', money = '"            + player.getMetadata("money").get(0).asInt()
									+ "', points = '"           + player.getMetadata("points").get(0).asInt()
									+ "', homes = '"            + player.getMetadata("homes").get(0).asString()
									+ "', kills = '"            + player.getMetadata("kills").get(0).asInt()
									+ "', monster_kills = '"    + player.getMetadata("monster_kills").get(0).asInt()
									+ "', blockPlaceCount = '"  + player.getMetadata("blockPlaceCount").get(0).asInt()
									+ "', blockBreakCount =  '" + player.getMetadata("blockBreakCount").get(0).asInt()
									+ "', deaths = '"           + player.getMetadata("deaths").get(0).asInt()
									+ "', joins = '"            + player.getMetadata("joins").get(0).asInt()
									+ "', time_played = '"      + player.getMetadata("time_played").get(0).asInt()
									+ "', password = '"         + player.getMetadata("password").get(0).asString()
									+ "', ban = '"              + player.getMetadata("ban").get(0).asInt()
									+ "' WHERE id = '"          + player.getMetadata("id").get(0).asInt() + "';");
			} catch (SQLException e) {
				e.printStackTrace();
				mysql.closeConnection();
				return false;
			}
		} else {
			return false;
		}
		mysql.closeConnection();
		return true;
	}
	
	///// Store All Player Data
	public void storeAll() {
		ArrayList<Player> playersOnline = GlobalData.playersOnline;
    	if (playersOnline.size() > 0) {
        	for (Player p : playersOnline) {
    			try {
					if (!new PlayerData(plugin).store(p)) {
						p.kickPlayer(GlobalData.styleChatServer + "There was a problem storing your data. Try logging back in.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
        	plugin.getLogger().info("Player Store Scheduler: All player data stored.");
    	} else {
    		plugin.getLogger().info("Player Store Scheduler: No players online, task canceled.");
    	}
	}
	
	///// Load All Player Data
	public void loadAll() {
		ArrayList<Player> playersOnline = GlobalData.playersOnline;
    	if (playersOnline.size() > 0) {
        	for (Player p : playersOnline) {
    			try {
					if (!new PlayerData(plugin).load(p)) {
						p.kickPlayer(GlobalData.styleChatServer + "There was a problem load your data. Try logging back in.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
        	plugin.getLogger().warning("All player data loaded.");
    	} else {
    		plugin.getLogger().warning("No players online, task canceled.");
    	}
	}
	
	///// Add Join Count
	public boolean addJoinCount(Player player) {
		int joins = player.getMetadata("joins").get(0).asInt();
		joins++;
		player.setMetadata("joins", new FixedMetadataValue(plugin,joins));
		return true;
	}
	
	///// Add Block Place Count
	public boolean addBlockBreakCount(Player player) {
		int count = player.getMetadata("blockBreakCount").get(0).asInt();
		count++;
		player.setMetadata("blockBreakCount", new FixedMetadataValue(plugin,count));
		return true;
	}
	
	///// Add Block Break Count
	public boolean addBlockPlaceCount(Player player) {
		int count = player.getMetadata("blockPlaceCount").get(0).asInt();
		count++;
		player.setMetadata("blockPlaceCount", new FixedMetadataValue(plugin,count));
		return true;
	}
}
