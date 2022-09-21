package fr.natsu.bedwars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.InGamePlayers;
import fr.natsu.bedwars.game.PriceItem;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.main.main;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

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
			return("§e");
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
	
	public static void spawnItemNPC(Location Loc, Team t) {
		Villager as = (Villager) Loc.getWorld().spawnEntity(Loc, EntityType.VILLAGER);
		as.setRemoveWhenFarAway(false);
		as.setAdult();
		as.setProfession(Profession.LIBRARIAN);
		as.setCustomNameVisible(true);
		as.setCustomName("§e§lBuy Items");
		as.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000000, 10, false, false), true);
		as.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 10, false, false), true);
		setAI(as, false);
	}
	
	public static void spawnUpNPC(Location Loc, Team t) {
		Villager as = (Villager) Loc.getWorld().spawnEntity(Loc, EntityType.VILLAGER);
		as.setRemoveWhenFarAway(false);
		as.setAdult();
		as.setProfession(Profession.PRIEST);
		as.setCustomNameVisible(true);
		as.setCustomName("§d§lTeam Upgrades");
		as.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000000, 10, false, false), true);
		as.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 10, false, false), true);
		setAI(as, false);
	}
	
	public static void setAI(LivingEntity entity, boolean hasAi) {
		  EntityLiving handle = ((CraftLivingEntity) entity).getHandle();
		  handle.getDataWatcher().watch(15, (byte) (hasAi ? 0 : 1));
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
	
	public static void loadShopNPC(String WorldName) {
		String[] teams = {"red", "darkred", "blue", "aqua", "green", "orange", "pink", "yellow"};	
		Team[] coresp = {Team.RED, Team.DARK_RED, Team.BLUE, Team.AQUA, Team.GREEN, Team.ORANGE, Team.PINK, Team.YELLOW};
		int tick = 0;
		for (String team : teams) {
			Location Loc = new Location(Bukkit.getWorld(WorldName), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_NPC1X"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_NPC1Y"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_NPC1Z"));
			spawnItemNPC(Loc, coresp[tick]);
			Loc = new Location(Bukkit.getWorld(WorldName), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_NPC2X"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_NPC2Y"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_NPC2Z"));
			spawnUpNPC(Loc, coresp[tick]);
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
	
	public static void loadSpawns(String WorldName) {
		String[] teams = {"red", "darkred", "blue", "aqua", "green", "orange", "pink", "yellow"};	
		Team[] coresp = {Team.RED, Team.DARK_RED, Team.BLUE, Team.AQUA, Team.GREEN, Team.ORANGE, Team.PINK, Team.YELLOW};
		int tick = 0;
		for (String team : teams) {
			Location Loc = new Location(Bukkit.getWorld(WorldName), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_spawnX"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_spawnY"), main.INSTANCE.getConfig().getInt(WorldName+"_"+team+"_spawnZ"));
			setSpawn(Loc, coresp[tick]);
			tick++;
		}
	}
	
	public static void setSpawn(Location loc, Team team) {
		Game.Teams.get(team).spawn = loc;
	}

	public static void tpToSpawn(Player player) {
		if (Game.getTeam(player) != null) {
			if (Game.Teams.get(Game.getTeam(player)).spawn != null) {
				player.teleport(Game.Teams.get(Game.getTeam(player)).spawn);
			}
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
	
	public static void openMainShopMenu(Player player) {
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 27, "§3Bed§bWars§f - ShopMain");

		List<String> Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Buy blocks to bridge, build and");
		Lore.add("§7protect your bed !");
		Lore.add("");
		Lore.add("§bClick to view §a§lBlocks");
		ItemStack Slots = getItem(Material.WOOL, 1, "§a§lBlocks", Lore, 0);
		ConfigFirstInv.setItem(10, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Be prepared when you encounter");
		Lore.add("§7your evil enemies");
		Lore.add("");
		Lore.add("§bClick to view §c§lArmor");
		Slots = getItem(Material.IRON_CHESTPLATE, 1, "§c§lArmor", Lore, 0);
		ConfigFirstInv.setItem(12, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7A choice of  weapons and tools");
		Lore.add("§7to use in your fights");
		Lore.add("");
		Lore.add("§bClick to view §b§lWeapons and Tools");
		Slots = getItem(Material.IRON_SWORD, 1, "§b§lWeapons and Tools", Lore, 0);
		ConfigFirstInv.setItem(14, Slots);

		
		player.openInventory(ConfigFirstInv);
	}
	
	public static void openMainUpgrade(Player player) {
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 27, "§3Bed§bWars§f - UpMain");

		List<String> Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Upgrade your summoner for:");
		Lore.add("§7§e- More Speed");
		Lore.add("§7§a- More Currencies");
		Lore.add("");
		Lore.add("§bClick to view §6§lSummoner Upgrades");
		ItemStack Slots = getItem(Material.BLAZE_POWDER, 1, "§6§lSummoner Upgrades", Lore, 0);
		ConfigFirstInv.setItem(11, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Buy upgrade that apply to all");
		Lore.add("§7members of your team!");
		Lore.add("");
		Lore.add("§bClick to view §b§lTeam Upgrades");
		Slots = getItem(Material.CHEST, 1, "§b§lTeam Upgrades", Lore, 0);
		ConfigFirstInv.setItem(13, Slots);
		
		Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Upgrade your island defences");
		Lore.add("§7to help ward off enemies");
		Lore.add("");
		Lore.add("§bClick to view §c§lIsland Defence Upgrades");
		Slots = getItem(Material.REDSTONE, 1, "§c§lIsland Defence Upgrades", Lore, 0);
		ConfigFirstInv.setItem(15, Slots);

		
		player.openInventory(ConfigFirstInv);
	}
	
	
	public static void openBlockMenuShop(Player player) {
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 36, "§3Bed§bWars§f - ShopBlocks");
		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		border.setDurability((short) 11);
		for (int i = 0; i<9; i++) {
			ConfigFirstInv.setItem(i, border);
		}
		for (int i = 27; i<36; i++) {
			ConfigFirstInv.setItem(i, border);
		}
		ConfigFirstInv.setItem(9, border);
		ConfigFirstInv.setItem(18, border);
		ConfigFirstInv.setItem(26, border);
		ConfigFirstInv.setItem(17, border);

		int ID = 10;
		for (ItemStack M : Game.Blocks) {
			if (M != null) {
				PriceItem P = null;
				P = Game.BlockPrices.get(M.getType());
				
				int Count = M.getAmount();
				String Name = M.getItemMeta().getDisplayName();
				ItemStack I = new ItemStack(P.Type, 1);
				
				if (M.getType() == Material.STAINED_GLASS || M.getType() == Material.WOOL) {
					I.setDurability((short)formatTeamDamage(player));
				}
				
				
				List<String> Lore = new ArrayList<String>();
				Lore.add("");
				Lore.add("§6§lCost");
				Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
				Lore.add("");
				Lore.add("§d- Dropped on death");
				Lore.add("§b- Left Clic to Buy !");
				ItemStack Slots = getItem(M.getType(), M.getAmount(), "§f§l"+Count+"x §b§l"+Name, Lore, 0);
				ConfigFirstInv.setItem(ID, Slots);
				ID++;
			}
			
		}	
		
		List<String> Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Back to main menu ");
		Lore.add("");
		ItemStack Slots = getItem(Material.BARRIER, 1, "§c§lBack", Lore, 0);
		ConfigFirstInv.setItem(31, Slots);
		player.openInventory(ConfigFirstInv);
	}
	
	private static short formatTeamDamage(Player player) {
		Team T = Game.getTeam(player);
		switch (T) {
		case RED:
			return(14);
		case AQUA:
			return(3);
		case BLUE:
			return(11);
		case DARK_RED:
			return(14);
		case GREEN:
			return(5);
		case ORANGE:
			return(1);
		case PINK:
			return(6);
		case YELLOW:
			return(4);
		default:
			return(0);
		}
	}


	public static void openToolMenuShop(Player player) {
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 54, "§3Bed§bWars§f - ShopTool");
		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		border.setDurability((short) 11);
		for (int i = 0; i<54; i++) {
			ConfigFirstInv.setItem(i, border);
		}

		int ID = 0;
		int[] SlotsForItem = {10, 19, 28, 37, 12, 21, 30, 39, 14, 23, 32, 41, 16, 25, 34, 43};
		for (ItemStack M : Game.Tools) {
			PriceItem P = null;
			
			P = Game.ToolPrices.get(M.getType());
			
			int Count = M.getAmount();
			String Name = M.getItemMeta().getDisplayName();
			ItemStack I = new ItemStack(P.Type, 1);
			
			List<String> Lore = new ArrayList<String>();
			Lore.add("");
			Lore.add("§6§lCost");
			Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
			Lore.add("");
			Lore.add("§d- Dropped on death");
			Lore.add("§b- Left Clic to Buy !");
			ItemStack Slots = getItem(M.getType(), M.getAmount(), "§f§l"+Count+"x §b§l"+Name, Lore, 0);
			ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
			ID++;
			
		}	
		
		List<String> Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Back to main menu ");
		Lore.add("");
		ItemStack Slots = getItem(Material.BARRIER, 1, "§c§lBack", Lore, 0);
		ConfigFirstInv.setItem(49, Slots);
		player.openInventory(ConfigFirstInv);
	}
	
	public static void openArmorMenuShop(Player player) {
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 54, "§3Bed§bWars§f - ShopArmor");
		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		border.setDurability((short) 11);
		for (int i = 0; i<54; i++) {
			ConfigFirstInv.setItem(i, border);
		}

		int ID = 0;
		int[] SlotsForItem = {10, 19, 28, 37, 13, 22, 31, 40, 16, 25, 34, 43};
		for (ItemStack M : Game.Armors) {
			PriceItem P = null;
			P = Game.ArmorPrices.get(M.getType());
			
			int Count = M.getAmount();
			String Name = M.getItemMeta().getDisplayName();
			ItemStack I = new ItemStack(P.Type, 1);
			
			List<String> Lore = new ArrayList<String>();
			Lore.add("");
			Lore.add("§6§lCost");
			Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
			Lore.add("");
			Lore.add("§d- Not dropped on death");
			Lore.add("§b- Left Clic to Buy !");
			ItemStack Slots = getItem(M.getType(), M.getAmount(), "§f§l"+Count+"x §b§l"+Name, Lore, 0);
			ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
			ID++;
			
		}	
		
		List<String> Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Back to main menu ");
		Lore.add("");
		ItemStack Slots = getItem(Material.BARRIER, 1, "§c§lBack", Lore, 0);
		ConfigFirstInv.setItem(49, Slots);
		
		player.openInventory(ConfigFirstInv);
	}


	private static String formatItemColor(Material type) {
		if (type == Material.IRON_INGOT) {
			return("§f");
		} else if (type == Material.GOLD_INGOT) {
			return("§e");
		} else if (type == Material.DIAMOND) {
			return("§b");
		} else if (type == Material.EMERALD) {
			return("§a");
		}
		return null;
	}
	
	
	public static void openUpSummonShop(Player player) {
		Team T = Game.getTeam(player);
		Inventory ConfigFirstInv = Bukkit.createInventory(null, 54, "§3Bed§bWars§f - UpSummon");
		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		border.setDurability((short) 2);
		for (int i = 0; i<54; i++) {
			ConfigFirstInv.setItem(i, border);
		}

		int ID = 0;
		int[] SlotsForItem = {10, 19, 28, 37, 13, 22, 31, 40, 16, 25, 34, 43};
		if (T != null) {
			for (int Level : Game.IronPrices.keySet()) {
				PriceItem P = null;
				P = Game.IronPrices.get(Level);
				
				if (Level == Game.Teams.get(T).IronLevel+1) {
					//Can buy this one
					List<String> Lore = new ArrayList<String>();
					Lore.add("");
					Lore.add("§6§lCost");
					Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
					Lore.add("");
					Lore.add("§b- Left Clic to Buy !");
					ItemStack Slots = getItem(Material.IRON_INGOT, 1, "§f§lIron Team Summoner "+Level, Lore, 0);
					ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
					ID++;
				} else if (Level <= Game.Teams.get(T).IronLevel){
					List<String> Lore = new ArrayList<String>();
					Lore.add("");
					Lore.add("§6§lCost");
					Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
					Lore.add("");
					Lore.add("§b- Left Clic to Buy !");
					ItemStack Slots = getItem(Material.IRON_BLOCK, 1, "§a§lPossessed", Lore, 0);
					ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
					ID++;
				} else {
					ID++;
				}
			}
			
			for (int Level : Game.IronPrices.keySet()) {
				PriceItem P = null;
				P = Game.GoldPrices.get(Level);
				
				if (Level == Game.Teams.get(T).GoldLevel+1) {
					//Can buy this one
					List<String> Lore = new ArrayList<String>();
					Lore.add("");
					Lore.add("§6§lCost");
					Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
					Lore.add("");
					Lore.add("§b- Left Clic to Buy !");
					ItemStack Slots = getItem(Material.GOLD_INGOT, 1, "§e§lGold Team Summoner "+Level, Lore, 0);
					ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
					ID++;
				} else if (Level <= Game.Teams.get(T).GoldLevel){
					List<String> Lore = new ArrayList<String>();
					Lore.add("");
					Lore.add("§6§lCost");
					Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
					Lore.add("");
					Lore.add("§b- Left Clic to Buy !");
					ItemStack Slots = getItem(Material.GOLD_BLOCK, 1, "§a§lPossessed", Lore, 0);
					ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
					ID++;
				} else {
					ID++;
				}
			}	
			
			for (int Level : Game.DiamondPrices.keySet()) {
				PriceItem P = null;
				P = Game.DiamondPrices.get(Level);
				
				if (Level == Game.Teams.get(T).DiamondLevel+1) {
					//Can buy this one
					List<String> Lore = new ArrayList<String>();
					Lore.add("");
					Lore.add("§6§lCost");
					Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
					Lore.add("");
					Lore.add("§b- Left Clic to Buy !");
					ItemStack Slots = getItem(Material.DIAMOND, 1, "§b§lDiamond Team Summoner "+Level, Lore, 0);
					ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
					ID++;
				} else if (Level <= Game.Teams.get(T).DiamondLevel){
					List<String> Lore = new ArrayList<String>();
					Lore.add("");
					Lore.add("§6§lCost");
					Lore.add(" "+formatItemColor(P.Type)+""+P.Amount+" "+P.Type.name());
					Lore.add("");
					Lore.add("§b- Left Clic to Buy !");
					ItemStack Slots = getItem(Material.DIAMOND_BLOCK, 1, "§a§lPossessed", Lore, 0);
					ConfigFirstInv.setItem(SlotsForItem[ID], Slots);
					ID++;
				} else {
					ID++;
				}
			}	
		}
		List<String> Lore = new ArrayList<String>();
		Lore.add("");
		Lore.add("§7Back to main menu ");
		Lore.add("");
		ItemStack Slots = getItem(Material.BARRIER, 1, "§c§lBack", Lore, 0);
		ConfigFirstInv.setItem(49, Slots);
		
		player.openInventory(ConfigFirstInv);
	}
	
	
	public static int getAmount(Player player, Material material)
	{
	        PlayerInventory inventory = player.getInventory();
	        ItemStack[] items = inventory.getContents();
	        int has = 0;
	        for (ItemStack item : items)
	        {
	            if ((item != null) && (item.getType() == material) && (item.getAmount() > 0))
	            {
	                has += item.getAmount();
	            }
	        }
	        return has;
	    }


	public static void removeItemsIn(Player player, PriceItem priceItem) {
		PlayerInventory inventory = player.getInventory();
        ItemStack[] items = inventory.getContents();
        removeItems(inventory, priceItem.Type, priceItem.Amount);
        player.updateInventory();
		
	}
	
	public static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }

}
