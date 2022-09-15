package fr.natsu.bedwars.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.GameState;
import fr.natsu.bedwars.game.Ranks;
import fr.natsu.bedwars.utils.scutils;
import fr.natsu.bedwars.utils.utils;

public class LobbyListener implements Listener {

	
	
	@EventHandler
	public static void onJoin(PlayerJoinEvent event) {
		if (!(Game.State == GameState.READY || Game.State == GameState.WAITING)) {
			return;
		}
		/*
		 * Message type:
		 * 
		 * {BLUE}%Name% joinMessage
		 * 
		 * joinMessage correspond to a random message in this list:
		 * it can also depend on their permission
		 * 
		 * {DEFAULT} * {GRAY}wants to protect their bed.
		 * 
		 * {STAR} * {GOLD}has had a restless night.
		 * 
		 * {HOST} * {GREEN}feels sleepy.
		 * 
		 * {MEDIA} * {PINK}is counting sheep.
		 * 
		 * {MOD} * {YELLOW}just woke up.
		 * 
		 * {OP} * {DARK_RED}cant escape his nightmares!
		 * */
		
		/*Setting player join message*/
		switch (Game.getPlayerRank(event.getPlayer())) {
		
		case PLAYER:
			event.setJoinMessage("§9"+event.getPlayer().getName()+" §7wants to protect their bed.");
			break;
		
		case MOD:
			event.setJoinMessage("§9"+event.getPlayer().getName()+" §ajust woke up.");
			break;
			
		case MEDIA:
			event.setJoinMessage("§9"+event.getPlayer().getName()+" §dis counting sheep.");
			break;
			
		case STAR:
			event.setJoinMessage("§9"+event.getPlayer().getName()+" §6has had a restless night.");
			break;
			
		case HOST:
			event.setJoinMessage("§9"+event.getPlayer().getName()+" §afeels sleepy.");
			break;
			
		case OP:
			event.setJoinMessage("§9"+event.getPlayer().getName()+" §4cant escape his nightmares");
			break;
		}
		
		/*Setting name in color in the scoreboard*/
		event.getPlayer().setPlayerListName("§9"+event.getPlayer().getName());
		scutils.checkForScoreBoardAdd(event.getPlayer());
		utils.showMapSelection(event.getPlayer());
	}
	
	
	@EventHandler
	public static void onQuit(PlayerQuitEvent event) {
		/*No message in chat when a player leave*/
		if (!(Game.State == GameState.READY || Game.State == GameState.WAITING)) {
			return;
		}
		scutils.removeInScoreBoard(event.getPlayer());
		event.setQuitMessage(null);
		
	}
	
	@EventHandler
	public static void onChat(AsyncPlayerChatEvent event) {
		if (!(Game.State == GameState.READY || Game.State == GameState.WAITING)) {
			return;
		}
		char Level = '\u277a';
		event.setCancelled(true);
		Bukkit.broadcastMessage("§7| §e"+Level+" Sleepy "+"§9"+event.getPlayer().getName()+"§7: §f"+event.getMessage());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
