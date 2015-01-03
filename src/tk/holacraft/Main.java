package tk.holacraft;

import java.sql.Connection;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import tk.holacraft.commands.Hola;
import tk.holacraft.commands.Spawn;
import tk.holacraft.commands.Warp;
import tk.holacraft.handlers.BlockProtect;
import tk.holacraft.handlers.Logger;
import tk.holacraft.handlers.PlayerData;
import tk.holacraft.handlers.Scheduler;
import tk.holacraft.handlers.WorldGuard;
import tk.holacraft.handlers.items.Items;
import tk.holacraft.listeners.AsyncPlayerChat;
import tk.holacraft.listeners.EntityDamage;
import tk.holacraft.listeners.InventoryClick;
import tk.holacraft.listeners.ItemSpawn;
import tk.holacraft.listeners.PlayerBreakBlock;
import tk.holacraft.listeners.PlayerCommandPreprocess;
import tk.holacraft.listeners.PlayerDeath;
import tk.holacraft.listeners.PlayerInteract;
import tk.holacraft.listeners.PlayerInteractEntity;
import tk.holacraft.listeners.PlayerJoin;
import tk.holacraft.listeners.PlayerKick;
import tk.holacraft.listeners.PlayerLogin;
import tk.holacraft.listeners.PlayerMove;
import tk.holacraft.listeners.PlayerPlaceBlock;
import tk.holacraft.listeners.PlayerQuit;
import tk.holacraft.listeners.PlayerRespawn;
import tk.holacraft.listeners.SignChange;
import tk.holacraft.listeners.WorldChunkUnload;
import tk.holacraft.listeners.WorldLoad;

public class Main extends JavaPlugin {
	
	///// Class Variables
	
	///// On Plugin Disable
	public void onDisable() {
		
		///// Save Data
		new PlayerData(this).storeAll();
		new Logger(this).store();
		new BlockProtect(this).storeMap();
		
		///// Save Config
		this.saveConfig();
		
		if (GlobalData.pluginInitialized) {
			this.getLogger().info("Plugin has been disabled without any errors.");
		} else {
			this.getLogger().warning("Plugin has been disabled. pluginInitialized was false, server data was not saved..");
		}
		///// Kick all players for maintance.
		if (GlobalData.playersOnline.size() > 0) {
        	for (Player p : GlobalData.playersOnline) {
    			p.kickPlayer(GlobalData.styleChatServer + ChatColor.RED + "Don't worry, everything is fine. We are doing some unexpected maintenance and the server should be back up in no time.");
    		}
        	this.getLogger().info("All players kicked for maintenance.");
    	} else {
    		this.getLogger().info("Maintenance Kick: No players online, task canceled.");
    	}
	}
	
	///// On Plugin Enable
	@SuppressWarnings("unused")
	public void onEnable(){
		///// Get Configuration Data
		getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		///// Set Database Connection Info
		GlobalData.HOST = this.getConfig().getString("MySQL.Host");
		GlobalData.PORT = this.getConfig().getString("MySQL.Port");
		GlobalData.USER = this.getConfig().getString("MySQL.Username");;
		GlobalData.PASS = this.getConfig().getString("MySQL.Password");;
		GlobalData.DATABASE = this.getConfig().getString("MySQL.Database");;
		
		final MySQL mysql = new MySQL(this, GlobalData.HOST, GlobalData.PORT, GlobalData.DATABASE, GlobalData.USER, GlobalData.PASS);
		Connection c = null;
		
		///// Connect to Database
		if (this.getConfig().getBoolean("MySQL.enabled")) { /////For Debugging Purposes
			try {
				if (mysql.openConnection() != null) {
					c = mysql.openConnection();
				}
				if (mysql.checkConnection()) {
					this.getLogger().info("Connected to mysql database!");
					GlobalData.pluginInitialized = true;
					mysql.closeConnection();
				} else {
					this.getLogger().warning("Error: Could not connect to MySQL database!");
					GlobalData.pluginInitialized = false;
				}
			} catch (SQLException | ClassNotFoundException e) {
				this.getLogger().warning("Error: Could not open connection to MySQL database!");
				GlobalData.pluginInitialized = false;
			}
		} else {
			this.getLogger().info("MySQL is disabled!");
			GlobalData.pluginInitialized = true;
		}
		
		if (new WorldGuard(this).getWorldGuard() == null) {
			GlobalData.pluginInitialized = false; 
			this.getLogger().warning("Error: WorldGuard is not enabled.");
		}
		
		if (this.getServer().getPluginManager().getPlugin("Citizens") == null) {
			GlobalData.pluginInitialized = false; 
			this.getLogger().warning("Error: Citizens is not enabled.");
		}
		
		///// Register Login Handler Regardless 
		getServer().getPluginManager().registerEvents(new PlayerLogin(this), this);
		
		if (GlobalData.pluginInitialized) {
			///// Register Commands
			this.getCommand("hola").setExecutor(new Hola(this));
			this.getCommand("spawn").setExecutor(new Spawn(this));
			this.getCommand("warp").setExecutor(new Warp(this));

			
			///// Register Events
			getServer().getPluginManager().registerEvents(new AsyncPlayerChat(this), this);
			getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
			getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
			getServer().getPluginManager().registerEvents(new ItemSpawn(), this);
			getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
			getServer().getPluginManager().registerEvents(new PlayerInteractEntity(this), this);
			getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
			getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
			getServer().getPluginManager().registerEvents(new SignChange(this), this);
			getServer().getPluginManager().registerEvents(new WorldLoad(this), this);
			getServer().getPluginManager().registerEvents(new WorldChunkUnload(), this);
			getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
			getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
			getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
			getServer().getPluginManager().registerEvents(new PlayerKick(this), this);
			getServer().getPluginManager().registerEvents(new PlayerPlaceBlock(this), this);
			getServer().getPluginManager().registerEvents(new PlayerBreakBlock(this), this);
			getServer().getPluginManager().registerEvents(new PlayerCommandPreprocess(this), this);
			
			///// Load Server Data
			new PlayerData(this).loadAll();
			Spawn.loc = new Spawn(this).load();
			Warp.warps = new Warp(this).load();
			BlockProtect.protectedBlocks = new BlockProtect(this).loadMap();
			
			///// Register Custom Items
			new Items(this).registerIncrediStick();
			new Items(this).registerProtectGem();
			new Items(this).registerStrenght();
			
			///// Start Schedulers
			if (getConfig().getBoolean("Logger")) {
				new Scheduler(this).initLogger();
				GlobalData.serverProperties.put("Logger", true);
			}
			if (getConfig().getBoolean("Time_Sync")) {
				new Scheduler(this).initTimeSyncScheduler();
				GlobalData.serverProperties.put("Time_Sync", true);
			}
			new Scheduler(this).initPlayerStore();
			new Scheduler(this).initServerBroadcaster();
			
			///// Plugin Enabled
			this.getLogger().info("Plugin has been enabled without any errors.");
		} else {
			this.getLogger().warning("Error: Plugin has been enabled but was not initialized correctly.");
		}
	}  
}
