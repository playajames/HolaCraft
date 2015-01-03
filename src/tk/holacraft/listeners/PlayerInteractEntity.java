package tk.holacraft.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import tk.holacraft.GlobalData;
import tk.holacraft.Main;
import tk.holacraft.handlers.npc.Druggie;
import tk.holacraft.handlers.npc.FoodTrader;
import tk.holacraft.handlers.npc.GeneralTrader;
import tk.holacraft.handlers.npc.PotionTrader;
import tk.holacraft.handlers.npc.SuppliesTrader;
import tk.holacraft.handlers.npc.WeaponsTrader;

public class PlayerInteractEntity implements Listener {

	///// Class Variables
	Main plugin;
	public PlayerInteractEntity(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler 
	public void onEntityClick(PlayerInteractEntityEvent event){ 
		Player player = event.getPlayer(); 
		Entity clicked = event.getRightClicked();
		
		if (clicked instanceof Player) {
			Player clickedPlayer = ((Player) clicked).getPlayer();
			//ItemStack item = player.getItemInHand();
			
			if (clicked.hasMetadata("NPC")) {
				Player npc = clickedPlayer;
			
				/////
				if (npc.getDisplayName().contentEquals("General Trader")) {
					if (player.hasPermission("holacraft.npc.generaltrader")) {
						new GeneralTrader(plugin).openMenu(player);
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Sorry but you don't have permission to access this trader.");
					}
				}
				
				/////
				if (npc.getDisplayName().contentEquals("Meat Mike")) {
					if (player.hasPermission("tds.npc.foodtrader")) {
						new FoodTrader(plugin).openMenu(player);
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Sorry but you don't have permission to access this trader.");
					}
				}
				
				/////
				if (npc.getDisplayName().contentEquals("Produce Pat")) {
					if (player.hasPermission("tds.npc.suppliestrader")) {
						new SuppliesTrader(plugin).openMenu(player);
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Sorry but you don't have permission to access this trader.");
					}
				}
				
				/////
				if (npc.getDisplayName().contentEquals("Potion Patricia")) {
					if (player.hasPermission("tds.npc.potiontrader")) {
						new PotionTrader(plugin).openMenu(player);
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Sorry but you don't have permission to access this trader.");
					}
				}

				
				/////
				if (npc.getDisplayName().contentEquals("Armor Amanda")) {
					if (player.hasPermission("tds.npc.weaponstrader")) {
						new WeaponsTrader(plugin).openMenu(player);
					} else {
						player.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "Sorry but you don't have permission to access this trader.");
					}
				}
				
				/////
				if (npc.getDisplayName().contentEquals("Stoner Joe")) {
					new Druggie(plugin).randomChat(player, npc.getDisplayName());
				}
				
				/////
				if (npc.getDisplayName().contentEquals("Hippie Jay")) {
					new Druggie(plugin).randomChat(player, npc.getDisplayName());
				}
				
				/////
				if (npc.getDisplayName().contentEquals("Druggie Bob")) {
					new Druggie(plugin).randomChat(player, npc.getDisplayName());
				}
			}
			
		}
	}
}
