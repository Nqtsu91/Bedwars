package fr.natsu.bedwars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.InGamePlayers;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.main.main;

public class utils {

	public static void showMapSelection(Player player) {
		char bar = '\u258c';
		player.sendMessage("§7"+bar+" §3§lBed§b§lWars §7| §e§lVote for a map! §7Use /v #");
		for (String Map : Game.MapList) {
			String colorhere = "§7";
			if (Map == getMostVotedMap()) {
				colorhere = "§a";
			}
			player.sendMessage("§7"+bar+" §3§lBed§b§lWars §7| §6"+Map+colorhere+" [§f"+Game.VoteMap.get(Map)+colorhere+" votes]");
		}
	}
	
	
	public static String getMostVotedMap() {
		int Max = -1;
		String MapSelected = "";
		for (String Map : Game.VoteMap.keySet()) {
			if (Game.VoteMap.get(Map) > Max) {
				Max = Game.VoteMap.get(Map);
				MapSelected = Map;
			}
		}
		return(MapSelected);
	}
	
	public static String formatTeamColor(Team t) {
		switch (t) {
		
		case RED:
			return("§c");
		case AQUA:
			return("§b");
		case BLUE:
			return("§9");
		case DARK_RED:
			return("§4");
		case GREEN:
			return("§a");
		case ORANGE:
			return("§6");
		case PINK:
			return("§d");
		case YELLOW:
			return("§a");
		default:
			return("§f");
		
		}
	}
	
	public static String formatTeamName(Team t) {
		switch (t) {
		
		case RED:
			return("Red");
		case AQUA:
			return("Aqua");
		case BLUE:
			return("Blue");
		case DARK_RED:
			return("Dark Red");
		case GREEN:
			return("Green");
		case ORANGE:
			return("Orange");
		case PINK:
			return("Pink");
		case YELLOW:
			return("Yellow");
		default:
			return("Team_Error");
		
		}
	}
	
	public static String formatTeamSummonerSubName(Team t) {
		String ret = "";
		HashMap<Integer, Character> C = new HashMap<Integer, Character>(); 
		C.put(1, '\u2776');
		C.put(2, '\u2777');
		C.put(3, '\u2778');
		C.put(4, '\u2779');
		C.put(5, '\u277a');
		C.put(6, '\u277b');
		C.put(7, '\u277c');

		if (Game.Teams.get(t).getIron() > 0) {
			ret+= "§f"+C.get(Game.Teams.get(t).getIron())+"§l Iron";
		}
		if (Game.Teams.get(t).getGold() > 0) {
			ret+= " §e"+C.get(Game.Teams.get(t).getGold())+"§l Gold";
		}
		if (Game.Teams.get(t).getDiamond() > 0) {
			ret+= " §b"+C.get(Game.Teams.get(t).getDiamond())+"§l Diamond";
		}
		if (Game.Teams.get(t).getEmerald() > 0) {
			ret+= " §a"+C.get(Game.Teams.get(t).getEmerald())+"§l Emerald";
		}
		
		
		
		return ret;
	}
	
	public static String formatASCIIInteger(int i) {
		String ret = "";
		HashMap<Integer, Character> C = new HashMap<Integer, Character>(); 
		C.put(1, '\u2776');
		C.put(2, '\u2777');
		C.put(3, '\u2778');
		C.put(4, '\u2779');
		C.put(5, '\u277a');
		C.put(6, '\u277b');
		C.put(7, '\u277c');
		
		return C.get(i)+"";
	}
	
	public static void spawnBaseGenerator(Location Loc, Team t) {
		ArmorStand as = (ArmorStand) Loc.getWorld().spawnEntity(Loc, EntityType.ARMOR_STAND);
		as.setVisible(false);
		as.setGravity(false);
		as.setRemoveWhenFarAway(false);
		as.setCustomNameVisible(true);
		as.setCustomName(formatTeamColor(t)+formatTeamName(t)+" Summoner");
		Game.Teams.get(t).title = as;
		Loc.setY(Loc.getY()-0.3);
		ArmorStand Second = (ArmorStand) Loc.getWorld().spawnEntity(Loc, EntityType.ARMOR_STAND);
		Second.setVisible(false);
		Second.setGravity(false);
		Second.setRemoveWhenFarAway(false);
		Second.setCustomNameVisible(true);
		Second.setCustomName(formatTeamSummonerSubName(t));
		Game.Teams.get(t).state = Second;
	}
	
	public static void spawnDiamondGenerator(Location Loc) {
		ArmorStand as = (ArmorStand) Loc.getWorld().spawnEntity(Loc, EntityType.ARMOR_STAND);
		as.setVisible(false);
		as.setGravity(false);
		as.setRemoveWhenFarAway(false);
		as.setCustomNameVisible(true);
		as.setCustomName("§bDiamond Summoner");
		Loc.setY(Loc.getY()-0.3);
		ArmorStand Second = (ArmorStand) Loc.getWorld().spawnEntity(Loc, EntityType.ARMOR_STAND);
		Second.setVisible(false);
		Second.setGravity(false);
		Second.setRemoveWhenFarAway(false);
		Second.setCustomNameVisible(true);
		Second.setCustomName("§b"+formatASCIIInteger(Game.DiamondLevel)+" §lDiamond");
		Game.DiamondGen.add(Second);
	}

	public static void spawnEmeraldGenerator(Location Loc) {
		ArmorStand as = (ArmorStand) Loc.getWorld().spawnEntity(Loc, EntityType.ARMOR_STAND);
		as.setVisible(false);
		as.setGravity(false);
		as.setRemoveWhenFarAway(false);
		as.setCustomNameVisible(true);
		as.setCustomName("§aEmerald Summoner");
		Loc.setY(Loc.getY()-0.3);
		ArmorStand Second = (ArmorStand) Loc.getWorld().spawnEntity(Loc, EntityType.ARMOR_STAND);
		Second.setVisible(false);
		Second.setGravity(false);
		Second.setRemoveWhenFarAway(false);
		Second.setCustomNameVisible(true);
		Second.setCustomName("§a"+formatASCIIInteger(Game.EmeraldLevel)+" §lEmerald");
		Game.EmeraldGen.add(Second);
	}
	

	public static void loadGenerators(String WorldName) {
		String[] teams = {"red", "darkred", "blue", "aqua", "green", "orange", "pink", "yellow"};	
		Team[] coresp = {Team.RED, Team.DARK_RED, Team.BLUE, Team.AQUA, Team.GREEN, Team.ORANGE, Team.PINK, Team.YELLOW};
		int tick = 0;
		for (String team : teams) {
			Location Loc = new Location(Bukkit.getWorld(WorldName), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_genX"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_genY"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_genZ"));
			spawnBaseGenerator(Loc, coresp[tick]);
			tick++;
		}
	}
	
	public static void loadDiamondGenerators(String WorldName) {
		if (main.INSTANCE.getConfig().getInt(WorldName+"_amountOfDiamondGen") > 0) {
			for (int i = 0; i<main.INSTANCE.getConfig().getInt(WorldName+"_amountOfDiamondGen"); i++) {
				Location Loc = new Location(Bukkit.getWorld(WorldName), main.INSTANCE.getConfig().getInt(WorldName+"_diamondX_"+i), main.INSTANCE.getConfig().getInt(WorldName+"_diamondY_"+i), main.INSTANCE.getConfig().getInt(WorldName+"_diamondZ_"+i));
				Bukkit.broadcastMessage("S "+Loc.getX() +" "+Loc.getBlockY()+" "+Loc.getBlockZ());
				spawnDiamondGenerator(Loc);
			}
		} else {
			Bukkit.broadcastMessage("§cNo Diamond generators found for this map !");
		}
	}
	
	public static void loadEmeraldGenerators(String WorldName) {
		if (main.INSTANCE.getConfig().getInt(WorldName+"_amountOfEmeraldGen") > 0) {
			for (int i = 0; i<main.INSTANCE.getConfig().getInt(WorldName+"_amountOfEmeraldGen"); i++) {
				Location Loc = new Location(Bukkit.getWorld(WorldName), main.INSTANCE.getConfig().getInt(WorldName+"_emeraldX_"+i), main.INSTANCE.getConfig().getInt(WorldName+"_emeraldY_"+i), main.INSTANCE.getConfig().getInt(WorldName+"_emeraldZ_"+i));
				Bukkit.broadcastMessage("S "+Loc.getX() +" "+Loc.getBlockY()+" "+Loc.getBlockZ());
				spawnEmeraldGenerator(Loc);
			}
		} else {
			Bukkit.broadcastMessage("§cNo Emerald generators found for this map !");
		}
	}
	
	static public ItemStack getItem(Material item, int count, String Name, List<String> lore, int dmg) {
		ItemStack ItemReturn = new ItemStack(item);
		ItemMeta itemmeta = ItemReturn.getItemMeta();
		ItemReturn.setAmount(count);
		itemmeta.setDisplayName(Name);
		itemmeta.setLore(lore);
		ItemReturn.setItemMeta(itemmeta);
		ItemReturn.setDurability((short) dmg);
		return(ItemReturn);
	}
	
	public static void openTeamMenu(Player player) {
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 54, "§3Bed§bWars§f - Teams");
		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ConfigFirstInv.setItem(0, border);
		ConfigFirstInv.setItem(1, border);
		ConfigFirstInv.setItem(9, border);
		ConfigFirstInv.setItem(8, border);
		ConfigFirstInv.setItem(7, border);
		ConfigFirstInv.setItem(17, border);
		ConfigFirstInv.setItem(36, border);
		ConfigFirstInv.setItem(44, border);
		ConfigFirstInv.setItem(45, border);
		ConfigFirstInv.setItem(46, border);
		ConfigFirstInv.setItem(53, border);
		ConfigFirstInv.setItem(52, border);

		List<String> Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.RED).members) {
			Lore.add("§f - §c"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		ItemStack Slots = getItem(Material.WOOL, 1, "§c§lRouge", Lore, 14);
		ConfigFirstInv.setItem(20, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.BLUE).members) {
			Lore.add("§f - §9"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§9§lBleue", Lore, 11);
		ConfigFirstInv.setItem(21, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.GREEN).members) {
			Lore.add("§f - §2"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§2§lVerte", Lore, 5);
		ConfigFirstInv.setItem(22, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.YELLOW).members) {
			Lore.add("§f - §e"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§e§lJaune", Lore, 4);
		ConfigFirstInv.setItem(23, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.PINK).members) {
			Lore.add("§f - §d"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§d§lRose", Lore, 6);
		ConfigFirstInv.setItem(24, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.DARK_RED).members) {
			Lore.add("§f - §4"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§4§lPourpre", Lore, 14);
		ConfigFirstInv.setItem(30, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.AQUA).members) {
			Lore.add("§f - §b"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§b§lAqua", Lore, 3);
		ConfigFirstInv.setItem(31, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("§f - Clic gauche pour §arejoindre§f.");
		Lore.add("§e§lMembres:");
		for (InGamePlayers pl : Game.Teams.get(Team.ORANGE).members) {
			Lore.add("§f - §6"+Bukkit.getOfflinePlayer(pl.player).getName());
		}
		Slots = getItem(Material.WOOL, 1, "§6§lOrange", Lore, 1);
		ConfigFirstInv.setItem(32, Slots);

		
		player.openInventory(ConfigFirstInv);
	}
	
	
	

}
