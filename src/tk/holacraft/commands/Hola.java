package tk.holacraft.commands;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.Logger;
import tk.holacraft.handlers.Permissions;
import tk.holacraft.handlers.PlayerData;

public class Hola implements CommandExecutor {

	///// Class Variables
	Main plugin;
	public Hola(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("holacraft.command.hola")) {
				if (args.length > 0) {
					switch (args[0]) {
					// creative Command	
					case "creative":
						if(player.getGameMode().equals(GameMode.CREATIVE)) {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You are already in creative mode.");
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.GREEN + "Gamemode switched to creative.");
							player.setGameMode(GameMode.CREATIVE);
						}
						break;
					// Clear Inventory
					case "ci":
						player.sendMessage(GlobalData.styleChatServer + ChatColor.GREEN + "Inventory cleared.");
						player.getInventory().clear();
						break;
					// Mute command.
					case "mute":
						if (args.length > 0) {
							Player target = Bukkit.getPlayer(args[0]);
							if(!target.hasMetadata("muted")) {
								StringBuilder sb = new StringBuilder();
								for (int i = 2; i < args.length; i++){
								sb.append(args[i]).append(" ");
								}
								 
								String reason = sb.toString().trim();
								new Mute(plugin).mute(target, player, reason);
							} else {
								new Mute(plugin).unmute(target, player);
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + "Usage: /mute <player> <reason>");
						}
						break;
					// Warn command
					case "warn":
						if (args.length > 0) {
							Player target = Bukkit.getPlayer(args[0]);
								StringBuilder sb = new StringBuilder();
								for (int i = 2; i < args.length; i++){
								sb.append(args[i]).append(" ");
								}
								 
								String reason = sb.toString().trim();
								new Warn(plugin).warn(target, player, reason);
							
						} else {
							player.sendMessage(GlobalData.styleChatServer + "Usage: /warn <player> <reason>");
						}
					// adventure Command	
					case "adventure":
						if(player.getGameMode().equals(GameMode.ADVENTURE)) {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You are already in adventure mode.");
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.GREEN + "Gamemode switched to adventure.");
							player.setGameMode(GameMode.ADVENTURE);
						}
						break;
					// survival Command	
					case "survival":
						if(player.getGameMode().equals(GameMode.SURVIVAL)) {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You are already in survival mode.");
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.GREEN + "Gamemode switched to survival.");
							player.setGameMode(GameMode.SURVIVAL);
						}
						break;
					// Help Command	
					case "help":
						player.sendMessage(ChatColor.GREEN + "               Hola Command Help Page");
						player.sendMessage(ChatColor.GOLD + " /setemail <your_email>" + ChatColor.GRAY + " - Set account email.");
						player.sendMessage(ChatColor.GOLD + " /stats" + ChatColor.GRAY + " - Display account stats.");
						break;
					// AFK Command
					case "afk":
						if(player.hasMetadata("afk")) {
							player.removeMetadata("afk", plugin);
							player.sendMessage(GlobalData.styleChatServer + ChatColor.GRAY + "You are no-longer AFK.");
						} else {
							player.setMetadata("afk", new FixedMetadataValue(plugin, 1));
							player.sendMessage(GlobalData.styleChatServer + ChatColor.GRAY + "You are now AFK.");
						}
						break;
					// TimeSync Command	
					case "timesync":
						if (args.length == 2) {
							if (args[1].equals("0")) {
								GlobalData.serverProperties.put("Time_Sync", false);
								player.sendMessage(GlobalData.styleChatServer + "Time Sync set to false.");

							} else if (args[1].equals("1")) {
								GlobalData.serverProperties.put("Time_Sync", true);
								player.sendMessage(GlobalData.styleChatServer + "Time Sync set to true.");
							} else {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /hola timesync 0 or 1.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /hola timesync 0 or 1.");
						}
						break;
					// Set Email Command	
					case "setemail":
						if (args.length == 2) {
							if (args[1].contains("@") && args[1].contains(".")) {
								player.setMetadata("email", new FixedMetadataValue(plugin, args[1]));
								player.sendMessage(GlobalData.styleChatServer + "Email address has been set.");
							} else {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Please enter a valid email address.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /hola setemail youremail@email.com.");
						}
						break;
					// Group Command
					case "group":
						if (args.length == 4) {
							Player targetPlayer = plugin.getServer().getPlayer(args[2]);
							if (targetPlayer != null) {
								targetPlayer.setMetadata("group", new FixedMetadataValue(plugin,args[3]));
								targetPlayer.setDisplayName(targetPlayer.getDisplayName().replace(targetPlayer.getMetadata("group_tag").get(0).asString(), ""));
								try {
									new PlayerData(plugin).store(targetPlayer);
									new PlayerData(plugin).load(targetPlayer);
									new Permissions(plugin).remove(targetPlayer);
									new Permissions(plugin).set(targetPlayer);
									player.sendMessage(GlobalData.styleChatServer + targetPlayer.getDisplayName() + " group has been updated.");
									targetPlayer.sendMessage(GlobalData.styleChatServer + "Your group has been updated.");
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "There was a problem updating players group.");
								}
							} else {
								player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "That player is not online.");
							}
						} else {
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Usage /hola group set <playername> <group_id>.");
						}
						break;
					// Stats Command	
					case "stats":
						player.sendMessage("ID: " + player.getMetadata("id").get(0).asInt());
						player.sendMessage("Custom Name: " + player.getMetadata("custom_name").get(0).asString());
						player.sendMessage("Email: " + player.getMetadata("email").get(0).asString());
						player.sendMessage("Group: " + player.getMetadata("group").get(0).asInt());
						player.sendMessage("Clan: " + player.getMetadata("clan").get(0).asInt());
						player.sendMessage("Infractions: " + player.getMetadata("infractions").get(0).asInt());
						player.sendMessage("Money: " + player.getMetadata("money").get(0).asInt());
						player.sendMessage("Points: " + player.getMetadata("points").get(0).asInt());
						player.sendMessage("Homes: " + player.getMetadata("homes").get(0).asString());
						player.sendMessage("Kills: " + player.getMetadata("kills").get(0).asInt());
						player.sendMessage("Monster Kills: " + player.getMetadata("monster_kills").get(0).asInt());
						player.sendMessage("Deaths: " + player.getMetadata("deaths").get(0).asInt());
						player.sendMessage("Block Place Count: " + player.getMetadata("blockPlaceCount").get(0).asInt());
						player.sendMessage("Block Break Count: " + player.getMetadata("blockBreakCount").get(0).asInt());
						player.sendMessage("Join Date: " + player.getMetadata("join_date").get(0).asString());
						player.sendMessage("Joins Count: " + player.getMetadata("joins").get(0).asInt());
						player.sendMessage("Time Played: " + player.getMetadata("time_played").get(0).asLong() / 60 + " minutes");
						player.sendMessage("Stored Time: " + player.getMetadata("stored_time").get(0).asString());
						player.sendMessage("Group Name: " + player.getMetadata("group_name").get(0).asString());
						player.sendMessage("Group Tag: " + player.getMetadata("group_tag").get(0).asString());
						player.sendMessage("Group Home Limit: " + player.getMetadata("group_home_limit").get(0).asString());
						player.sendMessage("Group: " + player.getMetadata("group").get(0).asInt());
						player.sendMessage("Password: " + player.getMetadata("password").get(0).asString());
						player.sendMessage("Ban: " + player.getMetadata("ban").get(0).asInt());
						break;
						
					// Default Response	
					default:
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Use /hola help for more information.");
							break;
					}
				} else {
					player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Invalid argument. Use /hola help for more information.");
				}
			} else {
				player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have permission to do that.");
			}
		}
		return false;
	}

}
