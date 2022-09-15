package fr.natsu.bedwars.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.utils.utils;

public class LobbyListenerMenu implements Listener{

	@EventHandler
	public static void checkItemEffects(PlayerInteractEvent event) {
		Player player = event.getPlayer();
	    if (player.getItemInHand() == null) {
    		return;
    	}
	    if (player.getItemInHand().getType() == Material.AIR) {
    		return;
    	}
	    if (!player.getItemInHand().hasItemMeta()) {
	    	return;
	    }
	    if (player.getItemInHand().getItemMeta().getDisplayName() == null) {
	    	return;
	    }
    	switch(player.getItemInHand().getItemMeta().getDisplayName()){
	    	case "§3§lChoix d'equipe":
	    		event.setCancelled(true);
	    		utils.openTeamMenu(player);
	    		break;
    	}
	}
	

	@EventHandler
	public void onClick(InventoryClickEvent event) throws ReflectiveOperationException {
		
		if (event.getCurrentItem() != null) {
			if(event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE && event.getInventory().getName().contains("§")) {
				
				Player player = (Player)event.getWhoClicked();
				
				switch (event.getInventory().getName()) {
				
				case "§3Bed§bWars§f - Teams":
					event.setCancelled(true);
					if (event.getCurrentItem().getItemMeta().getDisplayName() == "§c§lRouge") {
						Game.addPlayerToTeam(player, Team.RED);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§9§lBleue") {
						Game.addPlayerToTeam(player, Team.BLUE);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§e§lJaune") {
						Game.addPlayerToTeam(player, Team.YELLOW);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§2§lVerte") {
						Game.addPlayerToTeam(player, Team.GREEN);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§d§lRose") {
						Game.addPlayerToTeam(player, Team.PINK);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§4§lPourpre") {
						Game.addPlayerToTeam(player, Team.DARK_RED);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§b§lAqua") {
						Game.addPlayerToTeam(player, Team.AQUA);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§6§lOrange") {
						Game.addPlayerToTeam(player, Team.ORANGE);
					}
					utils.openTeamMenu(player);
					break;
				}
			}
		}
	}
	
}
