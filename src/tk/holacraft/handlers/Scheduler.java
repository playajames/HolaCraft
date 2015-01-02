package tk.holacraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.scheduler.BukkitScheduler;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Scheduler {

	///// Class Variables
	Main plugin;
	public Scheduler(Main plugin) {
		this.plugin = plugin;
	}
	
	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	
	///// Logger Store Scheduler
	public void initLogger(){
	    scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	if (GlobalData.serverProperties.get("Logger")) {
	        		new Logger(plugin).store();
	        	}
	        }
	    }, 0L, 6000L); ///// Every 12000L = 10 Minutes
	}
	
	///// Player Store Scheduler
	public void initPlayerStore(){
	    scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	new PlayerData(plugin).storeAll();
	        }
	    }, 0L, 3000L); ///// Every 12000L = 10 Minutes
	}
	
	///// Time Sync Scheduler
	public void initTimeSyncScheduler(){
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
		@Override
		public void run() {
			if (GlobalData.serverProperties.get("Time_Sync")) {
				new ServerData(plugin).syncTime();
			}
		}}, 0L, 600L); ///// Every 600L = 1 Minutes
	}
	
	///// Server Broadcaster Scheduler
	public void initServerBroadcaster(){
	    scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
	    	int count = 0;
	    	int max = 5;
	    	@Override
	        public void run() {
	        	Server server = plugin.getServer();
	        	count++;
	        	if (count > max) {
	        		count = 1;
	        	}
	        	switch(count) {
	        	case 1:
	        		server.broadcastMessage(GlobalData.styleChatServer + "This server is in beta. If you discover any bugs or glitches please report them to a staff member.");
	        		break;
	        	case 2:
	        		server.broadcastMessage(GlobalData.styleChatServer + "Need some help getting acquainted with the server? Check out http://www.holacraft.tk/wiki.");
	        		break;
	        	case 3:
	        		server.broadcastMessage(GlobalData.styleChatServer + "Enjoy your stay. :D");
	        		break;
	        	case 4:
	        		server.broadcastMessage(GlobalData.styleChatServer + "Be friendly, helpful, and respectful to fellow players.");
	        		break;
	        	case 5:
	        		server.broadcastMessage(GlobalData.styleChatServer + "We are not currently recruiting staff members, please don't ask.");
	        		break;
	        	}
	        }
	    }, 0L, 6000L); ///// Every 12000L = 10 Minutes
	}
}
