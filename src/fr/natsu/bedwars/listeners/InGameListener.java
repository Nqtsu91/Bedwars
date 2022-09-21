package fr.natsu.bedwars.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.utils.utils;

public class InGameListener implements Listener {
	
	
	@EventHandler
	public static void onBedBreak(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.BED) {
			for (Team T : Game.Teams.keySet()) {
				if (Game.Teams.get(T).hasBed) {
					if (Game.Teams.get(T).BedLoc.getWorld() == e.getBlock().getWorld()) {
						if (Game.Teams.get(T).BedLoc.distance(e.getBlock().getLocation()) <= 3) {
							Bukkit.broadcastMessage(Game.Prefix+"§cTeam "+utils.formatTeamColor(T)+utils.formatTeamName(T)+" §cbed has been destroyed !");
							Game.Teams.get(T).hasBed = false;
							for (Player P : Bukkit.getOnlinePlayers()) {
								if (Game.getTeam(P) == T) {
									P.sendTitle("§cYour bed has been destroyed", "§cYou can no longer respawn");
								}
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public static void onDeath(PlayerDeathEvent e) {
		if (Game.Teams.get(Game.getTeam(e.getEntity())).hasBed) {
			if (e.getEntity().getKiller() != null) {
				Bukkit.broadcastMessage(Game.Prefix+""+utils.formatTeamColor(Game.getTeam(e.getEntity()))+e.getEntity().getName()+" §7was slain by "+utils.formatTeamColor(Game.getTeam(e.getEntity().getKiller()))+e.getEntity().getKiller().getName());
			} else {
				Bukkit.broadcastMessage(Game.Prefix+""+utils.formatTeamColor(Game.getTeam(e.getEntity()))+e.getEntity().getName()+" §7died in the void.");
			}
		} else {
			e.getEntity().setGameMode(GameMode.CREATIVE);
			Team T = Game.getTeam(e.getEntity());
			Game.Teams.get(Game.getTeam(e.getEntity())).removePlayer(Game.getInGame(e.getEntity()));
			if (Game.Teams.get(T).members.size() == 0) {
				Bukkit.broadcastMessage(Game.Prefix+"§cTeam "+utils.formatTeamColor(T)+utils.formatTeamName(T)+" §chas been eliminated!");
			}
		}
	}
	
	@EventHandler
	public static void onRespawn(PlayerRespawnEvent e) {
		if (Game.getTeam(e.getPlayer()) != null) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5*20, 99, false, false), true);
		}
	}
	
	
	
}
