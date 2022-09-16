package fr.natsu.bedwars.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.natsu.bedwars.game.GameState;
import fr.natsu.bedwars.utils.HotbarMessage;
import fr.natsu.bedwars.utils.scutils;
import fr.natsu.bedwars.utils.utils;
import fr.natsu.bedwars.game.Game;

public class LobbyRunnable extends BukkitRunnable {

	public static int MinPlayersToStart = 8;
	public static int TeamSize = 1;
	public static int TimeToStart = 10;
	
	
	@Override
	public void run() {
		/*Display scordboards*/
		if (Game.State == GameState.WAITING || Game.State == GameState.READY) {
			scutils.showWaitingSC();
			
			if (Bukkit.getOnlinePlayers().size() >= MinPlayersToStart) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (TimeToStart == 0) {
						if (Game.canStart()) {
							Game.State = GameState.PLAYING;
							/*Démarrage de la partie*/
							utils.loadGenerators("world");
							utils.loadDiamondGenerators("world");
							utils.loadSpawns("world");
							for (Player pl : Bukkit.getOnlinePlayers()) {
								utils.tpToSpawn(pl);
							}
						} else {
							Bukkit.broadcastMessage(Game.Prefix+"§6Players didnt join their team in time.");
							TimeToStart += 30;
						}
						
					} else if (TimeToStart <= 15) {
						HotbarMessage.send(player, "§a§lStarting in "+TimeToStart+" seconds.");
					}
					TimeToStart--;
					player.setLevel(TimeToStart);
				}
			}
			
		}
	}

}
