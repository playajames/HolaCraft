package tk.holacraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;

import tk.holacraft.Main;
import tk.holacraft.handlers.InventoryMenu;
import tk.holacraft.handlers.npc.GeneralTrader;

public class InventoryClick implements Listener{

	///// Class Variables
	Main plugin;
	public InventoryClick(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();
		if (event.getSlotType().equals(SlotType.CONTAINER)) {
			if (inventory.equals(InventoryMenu.generalTrader)) {
				event.setCancelled(true);
				new GeneralTrader(plugin).menuClick(event);
			}
		}
	}
}
