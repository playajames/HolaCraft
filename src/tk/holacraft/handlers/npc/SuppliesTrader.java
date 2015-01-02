package tk.holacraft.handlers.npc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.InventoryMenu;

public class SuppliesTrader {

	///// Class Variables
	Main plugin;
	public SuppliesTrader(Main plugin) {
		this.plugin = plugin;
	}
	
	public void openMenu(Player player) {
		player.openInventory(InventoryMenu.suppliesTrader);
		player.sendMessage(GlobalData.styleChatServer + ChatColor.BOLD + "Left click to buy items. Right click to sell items.");
	}
}
