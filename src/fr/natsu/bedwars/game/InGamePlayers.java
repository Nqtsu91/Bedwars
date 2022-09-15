package fr.natsu.bedwars.game;

import java.util.UUID;

public class InGamePlayers {

	public UUID player = null;
	public int kills = 0;
	public int bedDestroyed = 0;
	public int HeadLevel = 0;
	public int ChestLevel = 0;
	public int LegsLevel = 0;
	public int BootsLevel = 0;
	public Team t = null;
	
	public InGamePlayers(UUID id, Team t) {
		this.player = id;
		this.t = t;
	}
	
	public void addKills() {
		this.kills++;
	}
	
	public void addBed() {
		this.bedDestroyed++;
	}
	
	public boolean setHeadLevel(int i) {
		if (this.HeadLevel >= i) {
			return false;
		}
		this.HeadLevel = i;
		return(true);
	}
	
	public boolean setChestLevel(int i) {
		if (this.ChestLevel >= i) {
			return false;
		}
		this.ChestLevel = i;
		return(true);
	}
	
	public boolean setLegsLevel(int i) {
		if (this.LegsLevel >= i) {
			return false;
		}
		this.LegsLevel = i;
		return(true);
	}
	
	public boolean setBootsLevel(int i) {
		if (this.BootsLevel >= i) {
			return false;
		}
		this.BootsLevel = i;
		return(true);
	}
	
	
	
}
