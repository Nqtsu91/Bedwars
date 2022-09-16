package fr.natsu.bedwars.game;

import org.bukkit.Material;
import org.bukkit.entity.Ambient;

public class PriceItem {

	
	public Material Type = null;
	public int Amount = 1;
	
	public PriceItem(Material T, int i) {
		this.Type = T;
		this.Amount = i;
	}
	
}
