package tk.holacraft.handlers.npc;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.InventoryMenu;
import tk.holacraft.handlers.Scoreboard;
import tk.holacraft.handlers.items.Items;

public class PotionTrader implements Listener {
	///// Class Variables
	Main plugin;
	public PotionTrader(Main plugin) {
		this.plugin = plugin;
	}
	
	public void openMenu(Player player) {
		player.openInventory(InventoryMenu.potionTrader);
		player.sendMessage(GlobalData.styleChatServer + ChatColor.BOLD + "Left click to buy items. Right click to sell items.");
	}

	public void menuClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		int money = player.getMetadata("money").get(0).asInt();
		Inventory inventory = player.getInventory();
		if (!clicked.getType().equals(Material.AIR)) {
			if (clicked.getItemMeta().hasDisplayName()) {
				// If left click - BUY
				if (event.isLeftClick()) {
					switch (clicked.getItemMeta().getDisplayName().toString()) {
					// Strenght Buy
					case "Strenght":
						String[] parts = clicked.getItemMeta().getLore().get(3).split(":");
						int value = Integer.parseInt(parts[1]);
						if (money >= value) {
							int result = money - value;
							ItemStack item = Items.Strenght.clone();
							ItemMeta itemMeta = item.getItemMeta();
							List<String> lore = itemMeta.getLore();
							lore.removeAll(lore);
							lore.add(ChatColor.GREEN + "Strenght potion.");
					    	itemMeta.setLore(lore);
							item.setItemMeta(itemMeta);
							inventory.addItem(item);
							player.setMetadata("money", new FixedMetadataValue(plugin,result));
							new Scoreboard(plugin).updateScoreboard(player);
						} else {
							player.closeInventory();
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have enough money to buy that.");
						}
						break;					
					default:
						break;
					}
				// If right click - SELL
				} else if (event.isRightClick()) {
					switch (clicked.getItemMeta().getDisplayName().toString()) {
					// Protect Gem Sell
					case "Protect Gem":
						ItemStack item = Items.ProtectGem.clone();
						ItemMeta itemMeta = item.getItemMeta();
						List<String> lore = itemMeta.getLore();
						lore.removeAll(lore);
						lore.add("Protects blocks");
				    	lore.add("placed while in");
				    	lore.add("inventory.");
				    	itemMeta.setLore(lore);
						item.setItemMeta(itemMeta);
						if (inventory.containsAtLeast(item, 1)) {
							String[] parts = clicked.getItemMeta().getLore().get(4).split(":");
							int value = Integer.parseInt(parts[1]);
							int result = money + value;
							inventory.removeItem(item);
							player.setMetadata("money", new FixedMetadataValue(plugin,result));
							new Scoreboard(plugin).updateScoreboard(player);
						} else {
							player.closeInventory();
							player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You dont have enough of that item.");
						}
						break;					
					default:
						break;
					}
				}
			}
		}
	}
}
