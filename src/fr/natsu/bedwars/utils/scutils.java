package fr.natsu.bedwars.utils;

import org.bukkit.entity.Player;

import fr.natsu.bedwars.main.main;


public class scutils {

	public static void checkForScoreBoardAdd(Player player) {
		FastBoard board = new FastBoard(player);
		board.updateTitle("§4§lUniversality");
		main.INSTANCE.boards.put(player.getUniqueId(), board);
	}
	
	public static void removeInScoreBoard(Player player) {
		FastBoard board = main.INSTANCE.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
	}
	
	
	public static void showWaitingSC() {
		for (FastBoard sc : main.INSTANCE.boards.values()) {
			Player player = sc.getPlayer();
			sc.updateTitle("§e§lBed§6§lWars");
			sc.updateLines("§f",
					"§b§lPseudo: §6"+player.getName(),
					"§7En attente de joueurs...",
					"§1",
					"§7- §c.gg/Cwm9kA9syV §7-");
		}
	}
	
}
