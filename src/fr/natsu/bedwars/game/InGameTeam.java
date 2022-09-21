package fr.natsu.bedwars.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import fr.natsu.bedwars.utils.utils;

public class InGameTeam {

	public List<InGamePlayers> members = new ArrayList<InGamePlayers>();
	public String Name = "";
	public int IronLevel = 1;
	public int GoldLevel = 0;
	public int DiamondLevel = 0;
	public int EmeraldLevel = 0;
	public boolean hasBed = true;
	public Location spawn = null;
	public ArmorStand title = null;
	public Location BedLoc = null;
	public ArmorStand state = null;
	
	public InGameTeam(String Name, Location spawn) {
		this.Name = Name;
		this.spawn = spawn;
	}
	
	public void addPlayer(InGamePlayers p) {
		this.members.add(p);
	}
	
	public void removePlayer(InGamePlayers p) {
		this.members.remove(p);
	}
	
	public void upgradeIron() {
		this.IronLevel++;
	}
	
	public void upgradeGold() {
		this.GoldLevel++;
	}
	
	public void upgradeDiamond() {
		this.DiamondLevel++;
	}
	
	public void upgradeEmerald() {
		this.EmeraldLevel++;
	}
	
	public int getIron() {
		return(this.IronLevel);
	}
	
	public int getGold() {
		return(this.GoldLevel);
	}
	
	public int getDiamond() {
		return(this.DiamondLevel);
	}
	
	public int getEmerald() {
		return(this.EmeraldLevel);
	}

	public void updateStands(Team t) {
		Location Loc = this.title.getLocation();
		this.title.remove();
		this.state.remove();
		utils.spawnBaseGenerator(Loc, t);
		
	}
}
