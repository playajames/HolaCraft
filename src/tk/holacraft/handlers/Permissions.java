package tk.holacraft.handlers;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;

public class Permissions {
	
	///// Class Variables
	Main plugin;
	public Permissions(Main plugin) {
		this.plugin = plugin;
	}
	
	///// Set group permissions
		public void set(Player player) {
			switch (player.getMetadata("group").get(0).asInt()) {
			case 1:
				new Permissions(plugin).setGuest(player);
				break;
			case 2:
				new Permissions(plugin).setSurvivor(player);
				break;
			case 75:
				new Permissions(plugin).setJuniorBuilder(player);
				break;
			case 100:
				new Permissions(plugin).setBuilder(player);
				break;
			case 450:
				new Permissions(plugin).setJuniorDeveloper(player);
				break;
			case 500:
				new Permissions(plugin).setDeveloper(player);
				break;
			case 550:
				new Permissions(plugin).setDeveloperPlus(player);
				break;
			case 700:
				new Permissions(plugin).setModerator(player);
				break;
			case 750:
				new Permissions(plugin).setModeratorPlus(player);
				break;
			case 1000:
				new Permissions(plugin).setAdministrator(player);
				break;
			case 1050:
				new Permissions(plugin).setHeadAdministrator(player);
				break;
			default:
				new Permissions(plugin).setGuest(player);
				break;
			}
		}
		
		public void remove(Player player) {
			if (GlobalData.permissions.get(player.getUniqueId()) != null) {
				player.removeAttachment(GlobalData.permissions.get(player.getUniqueId()));
				GlobalData.permissions.remove(player.getUniqueId());
			}
		}
		
	///// Guest Permissions
	public void setGuest(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("bukkit.command.plugins", false);
		permissions.setPermission("bukkit.command.me", false);
	}
	
	///// Survivor Permissions
	public void setSurvivor(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		//Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		//Bukkit Permissions
		permissions.setPermission("bukkit.command.me", true);
		permissions.setPermission("bukkit.command.tell", true);
		permissions.setPermission("bukkit.command.help", true);
		
	}
	
	///// Junior Builder Permissions
	public void setJuniorBuilder(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		//Bukkit Permissions
		permissions.setPermission("bukkit.command.me", true);
		permissions.setPermission("bukkit.command.tell", true);
		permissions.setPermission("bukkit.command.help", true);
		permissions.setPermission("bukkit.command.gamemode", true);
	}
	
	///// Builder Permissions
	public void setBuilder(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		// Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.command.warp.list", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		permissions.setPermission("holacraft.command.warp.override", true);
		// Bukkit Permissions
		permissions.setPermission("bukkit.command.gamemode", true);
		permissions.setPermission("bukkit.command.teleport", true);
		// WorldEdit Permissions
		permissions.setPermission("worldedit.*", true);
	}
	
	///// Builder Plus Permissions
	public void setBuilderPlus(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		// Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.command.warp.list", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		permissions.setPermission("holacraft.command.warp.override", true);
		permissions.setPermission("holacraft.command.warn", true);
		// Bukkit Permissions
		permissions.setPermission("bukkit.command.gamemode", true);
		permissions.setPermission("bukkit.command.weather", true);
		permissions.setPermission("bukkit.command.time.add", true);
		permissions.setPermission("bukkit.command.time.set", true);
		permissions.setPermission("bukkit.command.teleport", true);
		// WorldEdit Permissions
		permissions.setPermission("worldedit.*", true);
	}
	
	///// Junior Developer Permissions
	public void setJuniorDeveloper(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		// Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.command.warp.list", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("hola.command.afk", true);
		//Bukkit Permissions
		permissions.setPermission("bukkit.command.me", true);
		permissions.setPermission("bukkit.command.tell", true);
		permissions.setPermission("bukkit.command.help", true);
	}
	
	///// Developer Permissions
	public void setDeveloper(Player player) {
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		player.setOp(true);
	}
	
	///// Developer Plus Permissions
	public void setDeveloperPlus(Player player) {
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		player.setOp(true);
	}
	
	///// Moderator Permissions
	public void setModerator(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		// Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.command.warp.list", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		permissions.setPermission("holacraft.command.warp.override", true);
		permissions.setPermission("holacraft.command.mute", true);
		permissions.setPermission("holacraft.command.warn", true);
		
		// Bukkit Permissions
		permissions.setPermission("bukkit.command.gamemode", true);
		permissions.setPermission("bukkit.command.weather", true);
		permissions.setPermission("bukkit.command.time.add", true);
		permissions.setPermission("bukkit.command.time.set", true);
		permissions.setPermission("bukkit.command.teleport", true);
	}
	
	///// Moderator Plus Permissions
	public void setModeratorPlus(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		// Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.hola", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.command.warp.list", true);
		permissions.setPermission("holacraft.command.warp.create", true);
		permissions.setPermission("holacraft.command.warp.delete", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		permissions.setPermission("holacraft.command.warp.override", true);
		permissions.setPermission("holacraft.command.mute", true);
		permissions.setPermission("holacraft.command.warn", true);
		
		// Bukkit Permissions
		permissions.setPermission("bukkit.command.gamemode", true);
		permissions.setPermission("bukkit.command.weather", true);
		permissions.setPermission("bukkit.command.time.add", true);
		permissions.setPermission("bukkit.command.time.set", true);
		permissions.setPermission("bukkit.command.teleport", true);
		permissions.setPermission("bukkit.command.kick", true);
		// WorldEdit Permissions
		permissions.setPermission("worldedit.*", true);
	}
	
	///// Administrator Permissions
	public void setAdministrator(Player player) {
		player.setOp(false);
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		PermissionAttachment permissions = GlobalData.permissions.get(player.getUniqueId());
		// Hola Permissions
		permissions.setPermission("holacraft.chat", true);
		permissions.setPermission("holacraft.command.hola", true);
		permissions.setPermission("holacraft.command.warp", true);
		permissions.setPermission("holacraft.command.warp.list", true);
		permissions.setPermission("holacraft.command.warp.create", true);
		permissions.setPermission("holacraft.command.warp.delete", true);
		permissions.setPermission("holacraft.npc.generaltrader", true);
		permissions.setPermission("holacraft.command.afk", true);
		permissions.setPermission("holacraft.command.warp.override", true);
		permissions.setPermission("holacraft.command.warn", true);
		permissions.setPermission("holacraft.command.mute", true);
		
		// Bukkit Permissions
		permissions.setPermission("bukkit.command.gamemode", true);
		permissions.setPermission("bukkit.command.weather", true);
		permissions.setPermission("bukkit.command.time.add", true);
		permissions.setPermission("bukkit.command.time.set", true);
		permissions.setPermission("bukkit.command.teleport", true);
		permissions.setPermission("bukkit.command.kick", true);
		permissions.setPermission("bukkit.command.ban.player", true);
		permissions.setPermission("bukkit.command.ban-ip", true);
		permissions.setPermission("bukkit.command.ban.list", true);
		permissions.setPermission("bukkit.command.unban.ip", true);
		permissions.setPermission("bukkit.command.unban.player", true);
		permissions.setPermission("bukkit.command.give", true);
		// WorldEdit Permissions
		permissions.setPermission("worldedit.*", true);
	}
	
	///// Head Administrator Permissions
	public void setHeadAdministrator(Player player) {
		GlobalData.permissions.put(player.getUniqueId(), player.addAttachment(plugin));
		player.setOp(true);
	}
}
