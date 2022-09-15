package fr.natsu.bedwars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.Team;

public class utils {

	public static void showMapSelection(Player player) {
		char bar = '\u258c';
		player.sendMessage("�7"+bar+" �3�lBed�b�lWars �7| �e�lVote for a map! �7Use /v #");
		for (String Map : Game.MapList) {
			String colorhere = "�7";
			if (Map == getMostVotedMap()) {
				colorhere = "�a";
			}
			player.sendMessage("�7"+bar+" �3�lBed�b�lWars �7| �6"+Map+colorhere+" [�f"+Game.VoteMap.get(Map)+colorhere+" votes]");
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
			return("�c");
		case AQUA:
			return("�b");
		case BLUE:
			return("�9");
		case DARK_RED:
			return("�4");
		case GREEN:
			return("�a");
		case ORANGE:
			return("�6");
		case PINK:
			return("�d");
		case YELLOW:
			return("�a");
		default:
			return("�f");
		
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
			ret+= "�f"+C.get(Game.Teams.get(t).getIron())+"�l Iron";
		}
		if (Game.Teams.get(t).getGold() > 0) {
			ret+= " �e"+C.get(Game.Teams.get(t).getGold())+"�l Gold";
		}
		if (Game.Teams.get(t).getDiamond() > 0) {
			ret+= " �b"+C.get(Game.Teams.get(t).getDiamond())+"�l Diamond";
		}
		if (Game.Teams.get(t).getEmerald() > 0) {
			ret+= " �a"+C.get(Game.Teams.get(t).getEmerald())+"�l Emerald";
		}
		
		
		
		return ret;
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
	

}