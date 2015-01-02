package tk.holacraft.handlers;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import tk.holacraft.Main;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;

public class WorldGuard {
	
	///// Class Variables
	Main plugin;
	public WorldGuard(Main plugin) {
		this.plugin = plugin;
	}
	
	public WorldGuardPlugin getWorldGuard() {
	    Plugin worldGuard = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (worldGuard == null || !(worldGuard instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) worldGuard;
	}
	
	public boolean getRegion(Location loc) {
		WorldGuardPlugin guard = getWorldGuard();
		Vector v = BukkitUtil.toVector(loc);
		RegionManager manager = guard.getRegionManager(loc.getWorld());
		ApplicableRegionSet set = manager.getApplicableRegions(v);
		return set.size() > 0;
    }
	
	public void setOwner(Player player, String region) {
		WorldGuardPlugin guard = getWorldGuard();
		RegionManager manager = guard.getRegionManager(player.getWorld());
		DefaultDomain owners = new DefaultDomain();
        owners.addPlayer(player.getName());
        manager.getRegion(region).setOwners(owners);
        try {
			manager.save();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    public boolean inRegion(Location loc) {
		WorldGuardPlugin guard = getWorldGuard();
		Vector v = BukkitUtil.toVector(loc);
		RegionManager manager = guard.getRegionManager(loc.getWorld());
		ApplicableRegionSet set = manager.getApplicableRegions(v);
		return set.size() > 0;
    }
}
