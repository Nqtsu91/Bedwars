package fr.natsu.bedwars.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class Game {

	public static GameState State = GameState.WAITING;
	static char bar = '\u258c';
	public static String Prefix = "§7"+bar+" §3§lBed§b§lWars §7| ";
	public static String MapName = "";
	public static List<String> MapList = new ArrayList<String>();
	public static HashMap<String, Integer> VoteMap = new HashMap<String, Integer>();
	public static List<UUID> hasVoted = new ArrayList<UUID>();
	public static HashMap<Team, InGameTeam> Teams = new HashMap<Team, InGameTeam>();
	public static List<ArmorStand> DiamondGen = new ArrayList<ArmorStand>();
	public static List<ArmorStand> EmeraldGen = new ArrayList<ArmorStand>();
	public static HashMap<UUID, UUID> HotBarIsShowingPlayer = new HashMap<UUID, UUID>();
	public static int DiamondLevel = 1;
	public static int EmeraldLevel = 1;
	public static String[] MapNames = {"Bonsai",
			"Candy",
			"Castle",
			"CityScape",
			"Carousel",
			"Elsa",
			"Floral",
			"Food",
			"Hangar",
			"Haunted",
			"Hell",
			"Igloo",
			"Isla",
			};
	
	
	public Game() {
		selectMapList();
		createTeam();
	}
	
	
	
	public static void createTeam() {
		for (Team t : Team.values()) {
			Teams.put(t, new InGameTeam("Unamed", new Location(Bukkit.getWorld("world"), 0, 0, 0)));
		}
	}
	
	public static void selectMapList() {
		List<String> Maps = new ArrayList<String>();
		Collections.shuffle(Arrays.asList(MapNames));
		for (int i = 0; i<5; i++) {
			Maps.add(MapNames[i]);
		}
		MapList = Maps;
		for (String Map : MapList) {
			VoteMap.put(Map, 0);
		}
	}

	public static void addPlayerToTeam(Player player, Team t) {
		removeFromTeam(player);
		Game.Teams.get(t).members.add(new InGamePlayers(player.getUniqueId(), t));
		player.sendMessage(Game.Prefix+"§aVous avez rejoins une equipe.");
	}
	
	public static void removeFromTeam(Player player) {
		for (InGameTeam t : Game.Teams.values()) {
			InGamePlayers ToRemove = null;
			for (InGamePlayers pl : t.members) {
				if (pl.player == player.getUniqueId()) {
					ToRemove = pl;
					player.sendMessage(Game.Prefix+"§cVous avez quitté votre equipe.");
				}
			}
			t.members.remove(ToRemove);
		}
	}

	public static String getPlayerPrefix(Player player) {
		String Prefix = "";
		char star = '\u2605';
		if (player.isOp()) {
			Prefix = "§4§lOP§7 | ";
		} else if (player.hasPermission("moderator.all") == true) {
			Prefix = "§3§lMOD§7 | ";
		} else if (player.hasPermission("media.broadcast") == true) {
			Prefix = "§5§lMEDIA§7 | ";
		} else if (player.hasPermission("star.isstar") == true) {
			Prefix = "§e§l"+star+"§7 | ";
		}
		return(Prefix);
	}
	
	
	public static Ranks getPlayerRank(Player player) {
		if (player.isOp()) {
			return(Ranks.OP);
		} else if (player.hasPermission("moderator.all") == true) {
			return(Ranks.MOD);
		} else if (player.hasPermission("media.broadcast") == true) {
			return(Ranks.MEDIA);
		} else if (player.hasPermission("star.isstar") == true) {
			return(Ranks.STAR);
		} else if (player.hasPermission("host.canhost") == true) {
			return(Ranks.HOST);
		}
		return(Ranks.PLAYER);
	}
	
	
}
