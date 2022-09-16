package fr.natsu.bedwars.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.natsu.bedwars.utils.utils;

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
	/*Shop data*/
	public static HashMap<ItemStack, PriceItem> ToolPrices = new HashMap<ItemStack, PriceItem>();
	public static HashMap<ItemStack, PriceItem> BlockPrices = new HashMap<ItemStack, PriceItem>();
	public static HashMap<ItemStack, PriceItem> ArmorPrices = new HashMap<ItemStack, PriceItem>();
	public static HashMap<ItemStack, PriceItem> TrapPrices = new HashMap<ItemStack, PriceItem>();
	
	public static ItemStack[] Tools = new ItemStack[16];
	public static ItemStack[] Armors = new ItemStack[12];
	public static ItemStack[] Blocks = new ItemStack[10];
	
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
		initPrices();
	}
	
	
	
	private void initPrices() {
		/*Tools*/
		int i = 0;
		ToolPrices.put(utils.getItem(Material.STONE_SWORD, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 5));
		Tools[i] = utils.getItem(Material.STONE_SWORD, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.STONE_PICKAXE, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 10));
		Tools[i] = utils.getItem(Material.STONE_PICKAXE, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.STONE_AXE, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 15));
		Tools[i] = utils.getItem(Material.STONE_AXE, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.STONE_SPADE, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 5));
		Tools[i] = utils.getItem(Material.STONE_SPADE, 1, null, null, 0);
		i++;
		
		ToolPrices.put(utils.getItem(Material.IRON_SWORD, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		Tools[i] = utils.getItem(Material.IRON_SWORD, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.IRON_PICKAXE, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		Tools[i] = utils.getItem(Material.IRON_PICKAXE, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.IRON_AXE, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 5));
		Tools[i] = utils.getItem(Material.IRON_AXE, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.IRON_SPADE, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 5));
		Tools[i] = utils.getItem(Material.IRON_SPADE, 1, null, null, 0);
		i++;
		
		
		ToolPrices.put(utils.getItem(Material.DIAMOND_SWORD, 1, null, null, 0), new PriceItem(Material.EMERALD, 5));
		Tools[i] = utils.getItem(Material.DIAMOND_SWORD, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.DIAMOND_PICKAXE, 1, null, null, 0), new PriceItem(Material.EMERALD, 2));
		Tools[i] = utils.getItem(Material.DIAMOND_PICKAXE, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.IRON_AXE, 1, null, null, 0), new PriceItem(Material.DIAMOND, 30));
		Tools[i] = utils.getItem(Material.IRON_AXE, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.DIAMOND_SPADE, 1, null, null, 0), new PriceItem(Material.DIAMOND, 5));
		Tools[i] = utils.getItem(Material.DIAMOND_SPADE, 1, null, null, 0);
		i++;
		
		ToolPrices.put(utils.getItem(Material.FIREBALL, 1, null, null, 0), new PriceItem(Material.EMERALD, 1));
		Tools[i] = utils.getItem(Material.FIREBALL, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.BOW, 1, null, null, 0), new PriceItem(Material.DIAMOND, 2));
		Tools[i] = utils.getItem(Material.BOW, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.ARROW, 10, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		Tools[i] = utils.getItem(Material.ARROW, 1, null, null, 0);
		i++;
		ToolPrices.put(utils.getItem(Material.WATER_BUCKET, 1, null, null, 0), new PriceItem(Material.EMERALD, 2));
		Tools[i] = utils.getItem(Material.WATER_BUCKET, 1, null, null, 0);
		i++;
		
		/*Armors*/
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_HELMET, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 10));
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_CHESTPLATE, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_LEGGINGS, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_BOOTS, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 10));
		
		ToolPrices.put(utils.getItem(Material.IRON_HELMET, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 50));
		ToolPrices.put(utils.getItem(Material.IRON_CHESTPLATE, 1, null, null, 0), new PriceItem(Material.EMERALD, 2));
		ToolPrices.put(utils.getItem(Material.IRON_LEGGINGS, 1, null, null, 0), new PriceItem(Material.DIAMOND, 20));
		ToolPrices.put(utils.getItem(Material.IRON_BOOTS, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 50));
		
		ToolPrices.put(utils.getItem(Material.DIAMOND_HELMET, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 200));
		ToolPrices.put(utils.getItem(Material.DIAMOND_CHESTPLATE, 1, null, null, 0), new PriceItem(Material.EMERALD, 5));
		ToolPrices.put(utils.getItem(Material.DIAMOND_LEGGINGS, 1, null, null, 0), new PriceItem(Material.DIAMOND, 50));
		ToolPrices.put(utils.getItem(Material.DIAMOND_BOOTS, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 200));
		
		
		/*Blocks*/
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_HELMET, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 10));
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_CHESTPLATE, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_LEGGINGS, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 10));
		ToolPrices.put(utils.getItem(Material.CHAINMAIL_BOOTS, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 10));
		
		ToolPrices.put(utils.getItem(Material.IRON_HELMET, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 50));
		ToolPrices.put(utils.getItem(Material.IRON_CHESTPLATE, 1, null, null, 0), new PriceItem(Material.EMERALD, 2));
		ToolPrices.put(utils.getItem(Material.IRON_LEGGINGS, 1, null, null, 0), new PriceItem(Material.DIAMOND, 20));
		ToolPrices.put(utils.getItem(Material.IRON_BOOTS, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 50));
		
		ToolPrices.put(utils.getItem(Material.DIAMOND_HELMET, 1, null, null, 0), new PriceItem(Material.IRON_INGOT, 200));
		ToolPrices.put(utils.getItem(Material.DIAMOND_CHESTPLATE, 1, null, null, 0), new PriceItem(Material.EMERALD, 5));
		ToolPrices.put(utils.getItem(Material.DIAMOND_LEGGINGS, 1, null, null, 0), new PriceItem(Material.DIAMOND, 50));
		ToolPrices.put(utils.getItem(Material.DIAMOND_BOOTS, 1, null, null, 0), new PriceItem(Material.GOLD_INGOT, 200));
		
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

	public static Team getTeam(Player player) {
		for (Team T : Game.Teams.keySet()) {
			for (InGamePlayers P : Game.Teams.get(T).members) {
				if (P.player == player.getUniqueId()) {
					return(T);
				}
			}
		}
		return(null);
	}

	public static boolean canStart() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Game.getTeam(player) == null) {
				return(false);
			}
		}
		return true;
	}
	
	
}
