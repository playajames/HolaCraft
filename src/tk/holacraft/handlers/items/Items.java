package tk.holacraft.handlers.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import tk.holacraft.Main;
import tk.holacraft.handlers.InventoryMenu;

public class Items {
	
	///// Class Variables
	Main plugin;
	public Items(Main plugin) {
		this.plugin = plugin;
	}
	
	///// Items
	public static ItemStack IncrediStick = new ItemStack(Material.STICK,1);
	public static ItemStack ProtectGem = new ItemStack(Material.GLASS,1);
	public static ItemStack Strenght = new ItemStack(Material.POTION,1, (short) 8265);
	
	// Food Items

	///// Register Item Methods
	public void registerIncrediStick() {
    	List<String> lore = new ArrayList<String>();
    	lore.add("�4Incredi Rares");
		ItemMeta incrediStickMeta = IncrediStick.getItemMeta();
		incrediStickMeta.setDisplayName("Incredi Stick");
		incrediStickMeta.setLore(lore);
		incrediStickMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
		incrediStickMeta.addEnchant(Enchantment.FIRE_ASPECT, 4, true);
		IncrediStick.setItemMeta(incrediStickMeta);
	}
	public void registerStrenght() {
    	List<String> lore = new ArrayList<String>();
    	lore.add(ChatColor.GREEN + "Strenght potion");
		ItemMeta pot = Strenght.getItemMeta();
		pot.setDisplayName(ChatColor.GREEN + "Strenght");
		pot.setLore(lore);
		Strenght.setItemMeta(pot);
	}
	public void registerProtectGem() {
    	List<String> lore = new ArrayList<String>();
    	lore.add("Protects blocks");
    	lore.add("placed while in");
    	lore.add("inventory.");
    	lore.add(ChatColor.GREEN + "Buy:500");
    	lore.add(ChatColor.RED + "Sell:500");
		ItemMeta protectGemMeta = ProtectGem.getItemMeta();
		protectGemMeta.setDisplayName("Protect Gem");
		protectGemMeta.setLore(lore);
		ProtectGem.setItemMeta(protectGemMeta);
		InventoryMenu.generalTrader.addItem(ProtectGem);
	}
}
