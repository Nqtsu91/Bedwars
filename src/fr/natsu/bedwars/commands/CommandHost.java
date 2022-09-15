package fr.natsu.bedwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.utils.utils;

public class CommandHost implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String text, String[] args) {
		if (sender instanceof Player) {
			/*
			 * 
			 * */
			
			Player player = (Player)sender;
			
			if (command.getName().equalsIgnoreCase("h")) {
				if (args[0].equalsIgnoreCase("test")) {
					utils.spawnBaseGenerator(player.getLocation(), Team.DARK_RED);
				}
				if (args[0].equalsIgnoreCase("up")) {
					Game.Teams.get(Team.DARK_RED).upgradeDiamond();
					Game.Teams.get(Team.DARK_RED).updateStands(Team.DARK_RED);
				}
			}
		}
		return false;
	}

}
