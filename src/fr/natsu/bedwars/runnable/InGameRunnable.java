package fr.natsu.bedwars.runnable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.GameState;
import fr.natsu.bedwars.game.InGameTeam;
import fr.natsu.bedwars.game.Team;

public class InGameRunnable extends BukkitRunnable {

	public static int InGameTimer = 0;
	
	
	@Override
	public void run() {
		if (Game.State == GameState.PLAYING) {
			
			
			
			/*Handle item spawns
			 * 
			 * Level 1: 1 every 3 seconds
			 * Level 2: 1 every 2 seconds
			 * Level 3: 1 every 1 second
			 * Level 4: 1 every 1 second + 1 every 3 seconds
			 * Level 5: 1 every 1 second + 1 every 2 seconds
			 * Level 6: 2 every 1 second 
			 * Level 7: 3 every 1 second 
			 * */
			for (Team T : Game.Teams.keySet()) {
				Location Loc = Game.Teams.get(T).state.getLocation();
				InGameTeam IG = Game.Teams.get(T);
				HandleBaseIronDrop(IG, Loc);
				HandleBaseGoldDrop(IG, Loc);
				HandleBaseDiamondDrop(IG, Loc);
				HandleBaseEmeraldDrop(IG, Loc);
				
				
			}
			
			
			
			
		}
	}

	
	
	public static void HandleBaseIronDrop(InGameTeam IG, Location Loc){
		switch (IG.getIron()) {
		
		case 7:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 3));
			break;
		case 6:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			break;
		case 3:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			break;
		}
	}
	
	public static void HandleBaseGoldDrop(InGameTeam IG, Location Loc){
		switch (IG.getGold()) {
		
		case 7:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 3));
			break;
		case 6:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		case 3:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			break;
		case 0:
			//
			break;
		}
	}
	
	public static void HandleBaseDiamondDrop(InGameTeam IG, Location Loc){
		switch (IG.getDiamond()) {
		
		case 7:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 3));
			break;
		case 6:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			break;
		case 3:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			break;
		case 0:
			//
			break;
		}
	}
	
	public static void HandleBaseEmeraldDrop(InGameTeam IG, Location Loc){
		switch (IG.getDiamond()) {
		
		case 7:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 3));
			break;
		case 6:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			}
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			break;
		case 3:
			Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItem(Loc, new ItemStack(Material.EMERALD, 1));
			}
			break;
		case 0:
			//
			break;
		}
	}
	
}
