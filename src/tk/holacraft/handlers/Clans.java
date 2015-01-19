package tk.holacraft.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tk.holacraft.GlobalData;

public class Clans {

	public boolean isFriendly(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			String playerClan = player.getMetadata("clan").get(0).asString();
			String damagerClan = damager.getMetadata("clan").get(0).asString();
			if (playerClan != "0") {
				if (playerClan.equals(damagerClan)) {
					damager.sendMessage(GlobalData.styleChatServer + ChatColor.RED + "You can't harm players in the same clan as you.");
					return true;
				}
			}
		}
		return false;
	}
}
