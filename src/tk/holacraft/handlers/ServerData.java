package tk.holacraft.handlers;

import java.util.Date;

import tk.holacraft.Main;

public class ServerData {

	///// Class Variables
	Main plugin;
	public ServerData(Main plugin) {
		this.plugin = plugin;
	}
	
	///// Sync Time Handler
	@SuppressWarnings("deprecation")
	public void syncTime() {
		Date d = new Date();
		int h = d.getHours();
		int m = d.getMinutes();
		m = (100/6) * m;
		long ticks = (1000 * h) + m + 18000;
		plugin.getServer().getWorld("world").setTime(ticks);
	}
}
