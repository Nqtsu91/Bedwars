package fr.natsu.bedwars.runnable;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.GameState;
import fr.natsu.bedwars.game.InGamePlayers;
import fr.natsu.bedwars.game.InGameTeam;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.utils.HotbarMessage;

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
				if (Game.Teams.get(T) != null) {
					if (Game.Teams.get(T).state != null) {
						Location Loc = Game.Teams.get(T).state.getLocation();
						InGameTeam IG = Game.Teams.get(T);
						HandleBaseIronDrop(IG, Loc);
						HandleBaseGoldDrop(IG, Loc);
						HandleBaseDiamondDrop(IG, Loc);
						HandleBaseEmeraldDrop(IG, Loc);
					}
				}
			}
			HandleMapDiamondGen();
			HandleMapEmeraldGen();
			HandleTimers();
			//HandlePlayerHotBar();
			
			InGameTimer++;
		}
	}

	
	/*
	private void HandlePlayerHotBar() {
		for (InGameTeam T : Game.Teams.values()) {
			for (InGamePlayers IG : T.members) {
				if (Bukkit.getPlayer(IG.player) != null) {			//for every player in every team (if they are online)
					Player player = Bukkit.getPlayer(IG.player);
					for (InGamePlayers IG_2 : T.members) {
						if (Bukkit.getPlayer(IG_2.player) != null && player != Bukkit.getPlayer(IG_2.player)) {			//showing every team mate
							HotbarMessage.send(player, Bukkit.getPlayer(IG.player).getName()+"§7 | §b"+IG_2.kills+" §fKills");
							
						}
					}
				}
			}
		}
	}*/



	private void HandleTimers() {
		if (InGameTimer == 10*60) {
			Bukkit.broadcastMessage(Game.Prefix+"§bDiamond Summoners §fupgraded to Tier 2 !");
			Game.DiamondLevel = 2;
		}
		else if (InGameTimer == 15*60) {
			Bukkit.broadcastMessage(Game.Prefix+"§aEmerald Summoners §fupgraded to Tier 2 !");
			Game.EmeraldLevel = 2;
		}
		else if (InGameTimer == 20*60) {
			Bukkit.broadcastMessage(Game.Prefix+"§bDiamond Summoners §fupgraded to Tier 3 !");
			Game.DiamondLevel = 3;
		}
		else if (InGameTimer == 20*60) {
			Bukkit.broadcastMessage(Game.Prefix+"§aEmerald Summoners §fupgraded to Tier 3 !");
			Game.EmeraldLevel = 3;
		}
	}



	private void HandleMapDiamondGen() {
		for (ArmorStand as : Game.DiamondGen) {
			Location Loc = as.getLocation();
			Loc.setY(Loc.getY()+0.4);
			
			switch (Game.DiamondLevel) {
			
			case 1:
				if (InGameTimer%60 == 0) {
					Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
				}
				break;
			
			case 2:
				if (InGameTimer%30 == 0) {
					Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
				}
				break;
			
			case 3:
				if (InGameTimer%10 == 0) {
					Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
				}
				break;
			}
			
			
		}
	}

	private void HandleMapEmeraldGen() {
		for (ArmorStand as : Game.EmeraldGen) {
			Location Loc = as.getLocation();
			Loc.setY(Loc.getY()+0.4);
			
			switch (Game.EmeraldLevel) {
			
			case 1:
				if (InGameTimer%120 == 0) {
					Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
				}
				break;
			
			case 2:
				if (InGameTimer%60 == 0) {
					Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
				}
				break;
			
			case 3:
				if (InGameTimer%30 == 0) {
					Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
				}
				break;
			}
			
			
		}
	}

	public static void HandleBaseIronDrop(InGameTeam IG, Location Loc){
		switch (IG.getIron()) {
		
		case 7:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 3));
			break;
		case 6:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			break;
		case 3:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.IRON_INGOT, 1));
			}
			break;
		}
	}
	
	public static void HandleBaseGoldDrop(InGameTeam IG, Location Loc){
		switch (IG.getGold()) {
		
		case 7:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 3));
			break;
		case 6:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		case 3:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.GOLD_INGOT, 1));
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
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 3));
			break;
		case 6:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			break;
		case 3:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.DIAMOND, 1));
			}
			break;
		case 0:
			//
			break;
		}
	}
	
	public static void HandleBaseEmeraldDrop(InGameTeam IG, Location Loc){
		switch (IG.getEmerald()) {
		
		case 7:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 3));
			break;
		case 6:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 2));
			break;
		case 5:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			break;
		case 4:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			}
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			break;
		case 3:
			Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			break;
		case 2:
			if (InGameTimer%2 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			}
			break;
		case 1:
			if (InGameTimer%3 == 0) {
				Loc.getWorld().dropItemNaturally(Loc, new ItemStack(Material.EMERALD, 1));
			}
			break;
		case 0:
			//
			break;
		}
	}
	
}
