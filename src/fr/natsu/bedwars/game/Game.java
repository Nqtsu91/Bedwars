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
	public static HashMap<Material, PriceItem> ToolPrices = new HashMap<Material, PriceItem>();
	public static HashMap<Material, PriceItem> BlockPrices = new HashMap<Material, PriceItem>();
	public static HashMap<Material, PriceItem> ArmorPrices = new HashMap<Material, PriceItem>();
	public static HashMap<ItemStack, PriceItem> TrapPrices = new HashMap<ItemStack, PriceItem>();
	public static HashMap<Integer, PriceItem> IronPrices = new HashMap<Integer, PriceItem>();
	public static HashMap<Integer, PriceItem> GoldPrices = new HashMap<Integer, PriceItem>();
	public static HashMap<Integer, PriceItem> DiamondPrices = new HashMap<Integer, PriceItem>();
	
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
		ToolPrices.put(Material.STONE_SWORD, new PriceItem(Material.IRON_INGOT, 5));
		Tools[i] = utils.getItem(Material.STONE_SWORD, 1, "Stone Sword", null, 0);
		i++;
		ToolPrices.put(Material.STONE_PICKAXE, new PriceItem(Material.IRON_INGOT, 10));
		Tools[i] = utils.getItem(Material.STONE_PICKAXE, 1, "Stone Pick", null, 0);
		i++;
		ToolPrices.put(Material.STONE_AXE, new PriceItem(Material.IRON_INGOT, 15));
		Tools[i] = utils.getItem(Material.STONE_AXE, 1, "Stone Axe", null, 0);
		i++;
		ToolPrices.put(Material.STONE_SPADE, new PriceItem(Material.IRON_INGOT, 5));
		Tools[i] = utils.getItem(Material.STONE_SPADE, 1, "Stone Spade", null, 0);
		i++;
		
		ToolPrices.put(Material.IRON_SWORD, new PriceItem(Material.GOLD_INGOT, 10));
		Tools[i] = utils.getItem(Material.IRON_SWORD, 1, "Iron Sword", null, 0);
		i++;
		ToolPrices.put(Material.IRON_PICKAXE, new PriceItem(Material.GOLD_INGOT, 10));
		Tools[i] = utils.getItem(Material.IRON_PICKAXE, 1, "Iron Pick", null, 0);
		i++;
		ToolPrices.put(Material.IRON_AXE, new PriceItem(Material.GOLD_INGOT, 5));
		Tools[i] = utils.getItem(Material.IRON_AXE, 1, "Iron Axe", null, 0);
		i++;
		ToolPrices.put(Material.IRON_SPADE, new PriceItem(Material.GOLD_INGOT, 5));
		Tools[i] = utils.getItem(Material.IRON_SPADE, 1, "Iron Spade", null, 0);
		i++;
		
		
		ToolPrices.put(Material.DIAMOND_SWORD, new PriceItem(Material.EMERALD, 5));
		Tools[i] = utils.getItem(Material.DIAMOND_SWORD, 1, "Diamond Sword", null, 0);
		i++;
		ToolPrices.put(Material.DIAMOND_PICKAXE, new PriceItem(Material.EMERALD, 2));
		Tools[i] = utils.getItem(Material.DIAMOND_PICKAXE, 1, "Diamond Pick", null, 0);
		i++;
		ToolPrices.put(Material.IRON_AXE, new PriceItem(Material.DIAMOND, 30));
		Tools[i] = utils.getItem(Material.IRON_AXE, 1, "Diamond Axe", null, 0);
		i++;
		ToolPrices.put(Material.DIAMOND_SPADE, new PriceItem(Material.DIAMOND, 5));
		Tools[i] = utils.getItem(Material.DIAMOND_SPADE, 1, "Diamond Spade", null, 0);
		i++;
		
		ToolPrices.put(Material.FIREBALL, new PriceItem(Material.EMERALD, 1));
		Tools[i] = utils.getItem(Material.FIREBALL, 1, "Fire Ball", null, 0);
		i++;
		ToolPrices.put(Material.BOW, new PriceItem(Material.DIAMOND, 2));
		Tools[i] = utils.getItem(Material.BOW, 1, "Bow", null, 0);
		i++;
		ToolPrices.put(Material.ARROW, new PriceItem(Material.GOLD_INGOT, 10));
		Tools[i] = utils.getItem(Material.ARROW, 10, "Arrows", null, 0);
		i++;
		ToolPrices.put(Material.WATER_BUCKET, new PriceItem(Material.EMERALD, 2));
		Tools[i] = utils.getItem(Material.WATER_BUCKET, 1, "MLG", null, 0);
		i++;
		
		/*Armors*/
		i = 0;
		ArmorPrices.put(Material.CHAINMAIL_HELMET, new PriceItem(Material.IRON_INGOT, 10));
		Armors[i] = utils.getItem(Material.CHAINMAIL_HELMET, 1, "Chain Helmet", null, 0);
		i++;
		ArmorPrices.put(Material.CHAINMAIL_CHESTPLATE, new PriceItem(Material.GOLD_INGOT, 10));
		Armors[i] = utils.getItem(Material.CHAINMAIL_CHESTPLATE, 1, "Chain Chestplate", null, 0);
		i++;
		ArmorPrices.put(Material.CHAINMAIL_LEGGINGS, new PriceItem(Material.GOLD_INGOT, 10));
		Armors[i] = utils.getItem(Material.CHAINMAIL_LEGGINGS, 1, "Chain Leggings", null, 0);
		i++;
		ArmorPrices.put(Material.CHAINMAIL_BOOTS, new PriceItem(Material.IRON_INGOT, 10));
		Armors[i] = utils.getItem(Material.CHAINMAIL_BOOTS, 1, "Chain Boots", null, 0);
		i++;
		ArmorPrices.put(Material.IRON_HELMET, new PriceItem(Material.IRON_INGOT, 50));
		Armors[i] = utils.getItem(Material.IRON_HELMET, 1, "Iron Helmet", null, 0);
		i++;
		ArmorPrices.put(Material.IRON_CHESTPLATE, new PriceItem(Material.EMERALD, 2));
		Armors[i] = utils.getItem(Material.IRON_CHESTPLATE, 1, "Iron Chestplate", null, 0);
		i++;
		ArmorPrices.put(Material.IRON_LEGGINGS, new PriceItem(Material.DIAMOND, 20));
		Armors[i] = utils.getItem(Material.IRON_LEGGINGS, 1, "Iron Leggings", null, 0);
		i++;
		ArmorPrices.put(Material.IRON_BOOTS, new PriceItem(Material.GOLD_INGOT, 50));
		Armors[i] = utils.getItem(Material.IRON_BOOTS, 1, "Iron Boots", null, 0);
		i++;
		
		ArmorPrices.put(Material.DIAMOND_HELMET, new PriceItem(Material.IRON_INGOT, 200));
		Armors[i] = utils.getItem(Material.DIAMOND_HELMET, 1, "Diamond Hemlet", null, 0);
		i++;
		ArmorPrices.put(Material.DIAMOND_CHESTPLATE, new PriceItem(Material.EMERALD, 5));
		Armors[i] = utils.getItem(Material.DIAMOND_CHESTPLATE, 1, "Diamond Chestplate", null, 0);
		i++;
		ArmorPrices.put(Material.DIAMOND_LEGGINGS, new PriceItem(Material.DIAMOND, 50));
		Armors[i] = utils.getItem(Material.DIAMOND_LEGGINGS, 1, "Diamond Leggings", null, 0);
		i++;
		ArmorPrices.put(Material.DIAMOND_BOOTS, new PriceItem(Material.GOLD_INGOT, 200));
		Armors[i] = utils.getItem(Material.DIAMOND_BOOTS, 1, "Diamond Boots", null, 0);
		i++;
		
		/*Blocks*/
		i = 0;
		BlockPrices.put(Material.STAINED_GLASS, new PriceItem(Material.IRON_INGOT, 5));
		System.out.println("Added STAINEDGLASS");
		Blocks[i] = utils.getItem(Material.STAINED_GLASS, 32, "Stained Glass", null, 0);
		i++;
		BlockPrices.put(Material.WOOL, new PriceItem(Material.IRON_INGOT, 5));
		Blocks[i] = utils.getItem(Material.WOOL, 32, "Wool", null, 0);
		i++;
		BlockPrices.put(Material.WOOD, new PriceItem(Material.IRON_INGOT, 6));
		Blocks[i] = utils.getItem(Material.WOOD, 32, "Wooden Planks", null, 0);
		i++;
		BlockPrices.put(Material.STONE, new PriceItem(Material.GOLD_INGOT, 10));
		Blocks[i] = utils.getItem(Material.STONE, 32, "Polished Andesite", null, 6);
		i++;
		BlockPrices.put(Material.ENDER_STONE, new PriceItem(Material.GOLD_INGOT, 8));
		Blocks[i] = utils.getItem(Material.ENDER_STONE, 32, "Ender Stone", null, 0);
		i++;
		BlockPrices.put(Material.STAINED_CLAY, new PriceItem(Material.DIAMOND, 10));
		Blocks[i] = utils.getItem(Material.STAINED_CLAY, 32, "Stained Clay", null, 0);
		i++;
		BlockPrices.put(Material.OBSIDIAN, new PriceItem(Material.EMERALD, 1));
		Blocks[i] = utils.getItem(Material.OBSIDIAN, 1, "Obsidian", null, 0);
		i++;
		
		
		IronPrices.put(1, new PriceItem(Material.IRON_INGOT, 5));
		IronPrices.put(2, new PriceItem(Material.IRON_INGOT, 10));
		IronPrices.put(3, new PriceItem(Material.GOLD_INGOT, 32));
		IronPrices.put(4, new PriceItem(Material.DIAMOND, 10));
		
		GoldPrices.put(1, new PriceItem(Material.DIAMOND, 1));
		GoldPrices.put(2, new PriceItem(Material.GOLD_INGOT, 10));
		GoldPrices.put(3, new PriceItem(Material.GOLD_INGOT, 32));
		GoldPrices.put(4, new PriceItem(Material.EMERALD, 2));
		
		DiamondPrices.put(1, new PriceItem(Material.EMERALD, 5));
		DiamondPrices.put(2, new PriceItem(Material.DIAMOND, 10));
		DiamondPrices.put(3, new PriceItem(Material.DIAMOND, 32));
		DiamondPrices.put(4, new PriceItem(Material.DIAMOND, 64));
		
	}


	static public InGamePlayers getInGame(Player player) {
		for (InGamePlayers Pl: InGamePlayers.ListOfPl) {
			if (Pl.player == player.getUniqueId()) {
				return(Pl);
			}
		}
		return(null);
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
