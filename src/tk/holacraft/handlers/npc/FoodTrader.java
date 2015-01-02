package tk.holacraft.handlers.npc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.InventoryMenu;

public class FoodTrader {

	///// Class Variables
	Main plugin;
	public FoodTrader(Main plugin) {
		this.plugin = plugin;
	}
	
	public void openMenu(Player player) {
		player.openInventory(InventoryMenu.foodTrader);
		player.sendMessage(GlobalData.styleChatServer + ChatColor.BOLD + "Left click to buy items. Right click to sell items.");
	}

	public void menuClick(InventoryClickEvent event) {
		//Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		//int money = player.getMetadata("money").get(0).asInt();
		//Inventory inventory = player.getInventory();
		if (!clicked.getType().equals(Material.AIR)) {
			if (clicked.getItemMeta().hasDisplayName()) {
				switch (clicked.getItemMeta().getDisplayName().toString()) {
				default:
					break;
				}
			}
		}
	}
}
