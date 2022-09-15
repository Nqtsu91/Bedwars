package fr.natsu.bedwars.main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.natsu.bedwars.commands.CommandHost;
import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.GameState;
import fr.natsu.bedwars.listeners.InGameListener;
import fr.natsu.bedwars.listeners.LobbyListener;
import fr.natsu.bedwars.listeners.LobbyListenerMenu;
import fr.natsu.bedwars.runnable.InGameRunnable;
import fr.natsu.bedwars.runnable.LobbyRunnable;
import fr.natsu.bedwars.utils.FastBoard;

public class main extends JavaPlugin implements PluginMessageListener {


	public static Map<UUID, FastBoard> boards = new HashMap<>();
	public static main INSTANCE = null;
	public static String BungeeName = "";
	
	
	public void onEnable() {
		System.out.println("[BEDWARS] Enabled !");

		if (!(new File("plugins/BedWars/config.yml").exists())) {
		    saveDefaultConfig();
		    System.out.println("========================================================== Plugin created config");
		}
		reloadConfig();
		
		//getCommand("h").setExecutor(new CommandHost(this));
		PluginManager Pm = getServer().getPluginManager();
		Pm.registerEvents(new LobbyListenerMenu(), this);
		Pm.registerEvents(new LobbyListener(), this);
		Pm.registerEvents(new InGameListener(), this);
		new LobbyRunnable().runTaskTimer(this, 0L, 20L);
		new InGameRunnable().runTaskTimer(this, 0L, 20L);
		new Game();
		getCommand("h").setExecutor(new CommandHost());
		
		INSTANCE = this;
		
		/* Deleting world (not used for now)
		System.out.println("[WeebArena] World deleted");
		File path = getServer().getWorldContainer().getAbsoluteFile();
		System.out.println("§4 "+path);
		unloadWorld(Bukkit.getWorld("world"));
		String StrPath = path.toString()+"/"+"world";
		Path newpath = Paths.get(StrPath);
		deleteWorld(newpath.toFile());
		*/
		
	}
	
	public static void unloadWorld(World world) {
		Bukkit.getServer().unloadWorld(world, true);
	}
	
	public static boolean deleteWorld(File path) {
	      if(path.exists()) {
	          File files[] = path.listFiles();
	          for(int i=0; i<files.length; i++) {
	              if(files[i].isDirectory()) {
	                  deleteWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	      return(path.delete());
	}
	
	public void onDisable() {
		this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
		System.out.println("[LOBBY] Disabled !");
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		
		if (subchannel.equals("GetServer")) {
			System.out.println("[UNIV] RECEIVED SERVER NAME INFO");
			String server = in.readUTF(); // Name of server, as given in the arguments
			System.out.println("GAME NAME IS "+server);
			main.BungeeName = server;
		} else if (subchannel.equals("getGameState")) {
			sendBackGameState();
		}
		
	}
	
	public void sendBackGameState() {
		System.out.println("[UNIV] SENT GAME STATE");
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Forward"); // So BungeeCord knows to forward it
		out.writeUTF("lobby");
		out.writeUTF("getGameState"); // The channel name to check if this your data

		ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
		DataOutputStream msgout = new DataOutputStream(msgbytes);
		try {
			if (Game.State == GameState.WAITING) {
				msgout.writeUTF(BungeeName+"/READY"); // You can do anything you want with msgout
			} else if (Game.State == GameState.ENDED) {
				msgout.writeUTF(BungeeName+"/ENDED"); // You can do anything you want with msgout
			} else {
				msgout.writeUTF(BungeeName+"/PLAYING"); // You can do anything you want with msgout
			}
			
		} catch (IOException exception){
			exception.printStackTrace();
		}

		out.writeShort(msgbytes.toByteArray().length);
		out.write(msgbytes.toByteArray());
		Player player = (Player) Bukkit.getOnlinePlayers().toArray()[0];
		player.sendPluginMessage(this, "BungeeCord", out.toByteArray());

	}
	
	
	
}
