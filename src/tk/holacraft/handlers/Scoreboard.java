package tk.holacraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Scoreboard {

	///// Class Variables
	Main plugin;
	public Scoreboard(Main plugin) {
		this.plugin = plugin;
	}
	
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
	
	public void updateScoreboard(Player player) {
		Objective title = board.registerNewObjective("title", "dummy");
		title.setDisplaySlot(DisplaySlot.SIDEBAR);
		title.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "HolaCraft");
		
		Score money = title.getScore("Balance:");
		money.setScore(player.getMetadata("money").get(0).asInt());
		
//		Score points = title.getScore(ChatColor.ITALIC + "Points:");
//		points.setScore(player.getMetadata("points").get(0).asInt());
		
		Score infractions = title.getScore("Infractions:");
		infractions.setScore(player.getMetadata("infractions").get(0).asInt());
		
		Score players = title.getScore("Online:");
		
		players.setScore(GlobalData.playersOnline.size());
		
		
		player.setScoreboard(board);
	}
	
}
