package fr.natsu.bedwars.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.main.main;
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
					utils.openMainUpgrade(player);
				}
				if (args[0].equalsIgnoreCase("up")) {
					Game.Teams.get(Team.DARK_RED).upgradeDiamond();
					Game.Teams.get(Team.DARK_RED).updateStands(Team.DARK_RED);
				}
				
				if (args[0].equalsIgnoreCase("saveGen")) {
					if (args.length >= 2) {
						String Name = player.getWorld().getName();
						int X = (int)Math.round(player.getLocation().getX());
						int Y = (int)Math.round(player.getLocation().getY());
						int Z = (int)Math.round(player.getLocation().getZ());
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_genX", X);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_genY", Y);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_genZ", Z);
						player.sendMessage("§aGenerator for team '"+args[1]+"' has been set to "+X+" "+Y+" "+Z);
						try {
							main.INSTANCE.getConfig().save(new File("plugins/BedWars/config.yml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						player.sendMessage("§cUsage: /h saveGen <team config name>");
					}
				}
				
				else if (args[0].equalsIgnoreCase("saveBed")) {
					if (args.length >= 2) {
						String Name = player.getWorld().getName();
						int X = (int)Math.round(player.getLocation().getX());
						int Y = (int)Math.round(player.getLocation().getY());
						int Z = (int)Math.round(player.getLocation().getZ());
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_bedX", X);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_bedY", Y);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_bedZ", Z);
						player.sendMessage("§aBed for team '"+args[1]+"' has been set to "+X+" "+Y+" "+Z);
						try {
							main.INSTANCE.getConfig().save(new File("plugins/BedWars/config.yml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						player.sendMessage("§cUsage: /h saveBed <team config name>");
					}
				}
				
				
				else if (args[0].equalsIgnoreCase("saveNPC_1")) {
					if (args.length >= 2) {
						String Name = player.getWorld().getName();
						int X = (int)Math.round(player.getLocation().getX());
						int Y = (int)Math.round(player.getLocation().getY());
						int Z = (int)Math.round(player.getLocation().getZ());
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_NPC1X", X);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_NPC1Y", Y);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_NPC1Z", Z);
						player.sendMessage("§aNPC 1 for team '"+args[1]+"' has been set to "+X+" "+Y+" "+Z);
						try {
							main.INSTANCE.getConfig().save(new File("plugins/BedWars/config.yml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						player.sendMessage("§cUsage: /h saveNPC_1 <team config name>");
					}
				}
				
				else if (args[0].equalsIgnoreCase("saveNPC_2")) {
					if (args.length >= 2) {
						String Name = player.getWorld().getName();
						int X = (int)Math.round(player.getLocation().getX());
						int Y = (int)Math.round(player.getLocation().getY());
						int Z = (int)Math.round(player.getLocation().getZ());
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_NPC2X", X);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_NPC2Y", Y);
						main.INSTANCE.getConfig().set(Name+"_"+args[1]+"_NPC2Z", Z);
						player.sendMessage("§aNPC 2 for team '"+args[1]+"' has been set to "+X+" "+Y+" "+Z);
						try {
							main.INSTANCE.getConfig().save(new File("plugins/BedWars/config.yml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						player.sendMessage("§cUsage: /h saveNPC_1 <team config name>");
					}
				}
				
				else if (args[0].equalsIgnoreCase("addDiamondGen")) {
					String Name = player.getWorld().getName();
					int Number = main.INSTANCE.getConfig().getInt(Name+"_amountOfDiamondGen");
					int X = (int)Math.round(player.getLocation().getX());
					int Y = (int)Math.round(player.getLocation().getY());
					int Z = (int)Math.round(player.getLocation().getZ());
					main.INSTANCE.getConfig().set(Name+"_diamondX_"+Number, X);
					main.INSTANCE.getConfig().set(Name+"_diamondY_"+Number, Y);
					main.INSTANCE.getConfig().set(Name+"_diamondZ_"+Number, Z);
					main.INSTANCE.getConfig().set(Name+"_amountOfDiamondGen", Number+1);
					player.sendMessage("§bDiamond§a gen number '"+main.INSTANCE.getConfig().getInt(Name+"_amountOfDiamondGen")+"' has been set to "+X+" "+Y+" "+Z);
					try {
						main.INSTANCE.getConfig().save(new File("plugins/BedWars/config.yml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if (args[0].equalsIgnoreCase("addEmeraldGen")) {
					String Name = player.getWorld().getName();
					int Number = main.INSTANCE.getConfig().getInt(Name+"_amountOfEmeraldGen");
					int X = (int)Math.round(player.getLocation().getX());
					int Y = (int)Math.round(player.getLocation().getY());
					int Z = (int)Math.round(player.getLocation().getZ());
					main.INSTANCE.getConfig().set(Name+"_emeraldX_"+Number, X);
					main.INSTANCE.getConfig().set(Name+"_emeraldY_"+Number, Y);
					main.INSTANCE.getConfig().set(Name+"_emeraldZ_"+Number, Z);
					main.INSTANCE.getConfig().set(Name+"_amountOfEmeraldGen", Number+1);
					player.sendMessage("§2Emerald§b gen number '"+main.INSTANCE.getConfig().getInt(Name+"_amountOfEmeraldGen")+"' has been set to "+X+" "+Y+" "+Z);
					try {
						main.INSTANCE.getConfig().save(new File("plugins/BedWars/config.yml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		}
		return false;
	}

}
