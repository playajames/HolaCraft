package tk.holacraft.handlers.npc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import tk.holacraft.Main;
import tk.holacraft.handlers.ServerTasks;

public class Druggie {

	///// Class Variables
	Main plugin;
	public Druggie(Main plugin) {
		this.plugin = plugin;
	}
	
	public void randomChat(Player player, String npc) {
		String styleChat = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + npc + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + " ";
		int random = new ServerTasks(plugin).getRandomInt(1, 21);
		switch(random) {
		case 1:
			player.sendMessage(styleChat + "Make love, not war.");
			break;
		case 2:
			player.sendMessage(styleChat + "Don't let the man keep you down.");
			break;
		case 3:
			player.sendMessage(styleChat + "Live and let die.");
			break;
		case 4:
			player.sendMessage(styleChat + "Hell no, we won't go.");
			break;
		case 5:
			player.sendMessage(styleChat + "Never trust the man.");
			break;
		case 6:
			player.sendMessage(styleChat + "Give peace a chance.");
			break;
		case 7:
			player.sendMessage(styleChat + "Power to the people.");
			break;
		case 8:
			player.sendMessage(styleChat + "Drop acid not bombs.");
			break;
		case 9:
			player.sendMessage(styleChat + "Live and let live.");
			break;
		case 10:
			player.sendMessage(styleChat + "No one is free, even the birds are chained to the sky.");
			break;
		case 11:
			player.sendMessage(styleChat + "I'll let you be in my dreams if I can be in yours.");
			break;
		case 12:
			player.sendMessage(styleChat + "Yesterday's just a memory, tomorrow is never what it's supposed to be.");
			break;
		case 13:
			player.sendMessage(styleChat + "Bring it home, daddy-o.");
			break;
		case 14:
			player.sendMessage(styleChat + "Sock it to me.");
			break;
		case 15:
			player.sendMessage(styleChat + "Outtasight!");
			break;
		case 16:
			player.sendMessage(styleChat + "Right on.");
			break;
		case 17:
			player.sendMessage(styleChat + "Groovy.");
			break;
		case 18:
			player.sendMessage(styleChat + "Go with the flow.");
			break;
		case 19:
			player.sendMessage(styleChat + "Far out, man.");
			break;
		case 20:
			player.sendMessage(styleChat + "F*ck the establishment.");
			break;
		case 21:
			player.sendMessage(styleChat + "Watch the fro.");
			break;
		}
	}
}
