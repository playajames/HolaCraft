package tk.holacraft.handlers;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import tk.holacraft.Main;

public class InventoryMenu {

	///// Class Variables
	Main plugin;
	public InventoryMenu(Main plugin) {
		this.plugin = plugin;
	}
	
	///// Traders
	public static Inventory foodTrader = Bukkit.createInventory(null, 54, "Food Trader");
	public static Inventory generalTrader = Bukkit.createInventory(null, 9, "General Trader");
	public static Inventory suppliesTrader = Bukkit.createInventory(null, 9, "Supplies Trader");
	public static Inventory weaponsTrader = Bukkit.createInventory(null, 9, "Weapons Trader");
}
