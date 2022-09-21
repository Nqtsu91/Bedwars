package fr.natsu.bedwars.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.natsu.bedwars.game.Game;
import fr.natsu.bedwars.game.Team;
import fr.natsu.bedwars.utils.Targeter;
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
	public static void onOpenMenu(InventoryOpenEvent e) {
		if (e.getInventory().getType() == InventoryType.MERCHANT) {
			e.setCancelled(true);
			if (e.getInventory().getName() == "§e§lBuy Items") {
				utils.openMainShopMenu((Player)e.getPlayer());
			} else if (e.getInventory().getName() == "§d§lTeam Upgrades") {
				utils.openMainUpgrade((Player)e.getPlayer());
			}
		}
	}
	
	@EventHandler
	public static void onInteract(PlayerInteractAtEntityEvent e) {
		e.setCancelled(true);
		if (e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
			if (e.getRightClicked().getCustomName() == "§e§lBuy Items") {
				e.setCancelled(true);
				utils.openMainShopMenu(e.getPlayer());
			} else if (e.getRightClicked().getCustomName() == "§d§lTeam Upgrades") {
				e.setCancelled(true);
				utils.openMainUpgrade(e.getPlayer());
			} else {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public static void noDamageToNpc(EntityDamageEvent e) {
		if (e.getEntity() instanceof Villager) {
			if (e.getCause() == DamageCause.VOID) {
				
			} else {
				e.setCancelled(true);
			}
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
					
					
				case "§3Bed§bWars§f - ShopMain":
					event.setCancelled(true);
					if (event.getCurrentItem().getItemMeta().getDisplayName() == "§a§lBlocks") {
						utils.openBlockMenuShop(player);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§c§lArmor") {
						utils.openArmorMenuShop(player);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§b§lWeapons and Tools") {
						utils.openToolMenuShop(player);
					}
				break;
				
				case "§3Bed§bWars§f - UpMain":
					event.setCancelled(true);
					if (event.getCurrentItem().getItemMeta().getDisplayName() == "§6§lSummoner Upgrades") {
						utils.openUpSummonShop(player);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§c§lArmor") {
						utils.openArmorMenuShop(player);
					} else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§b§lWeapons and Tools") {
						utils.openToolMenuShop(player);
					}
				break;
				
				case "§3Bed§bWars§f - UpSummon":
					event.setCancelled(true);
					if (event.getCurrentItem().getType() == Material.IRON_INGOT) {
						int LevelClic = 0;
						String Name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§f§lIron Team Summoner ", "");
						LevelClic = (int)Float.parseFloat(Name);
						if (utils.getAmount(player, Game.IronPrices.get(LevelClic).Type) >= Game.IronPrices.get(LevelClic).Amount) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().addItem(item);
							utils.removeItemsIn(player, Game.IronPrices.get(LevelClic));
							Game.Teams.get(Game.getTeam(player)).upgradeIron();
							Game.Teams.get(Game.getTeam(player)).updateStands(Game.getTeam(player));
							utils.openUpSummonShop(player);
						} else {
							int Need = Game.IronPrices.get(LevelClic).Amount-utils.getAmount(player, Game.IronPrices.get(LevelClic).Type);
							player.sendMessage(Game.Prefix+"§cYou need §7"+Need+" "+Game.IronPrices.get(LevelClic).Type.name()+"§c !");
						} 
					} else if (event.getCurrentItem().getType() == Material.GOLD_INGOT) {
						int LevelClic = 0;
						String Name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§e§lGold Team Summoner ", "");
						LevelClic = (int)Float.parseFloat(Name);
						if (utils.getAmount(player, Game.GoldPrices.get(LevelClic).Type) >= Game.GoldPrices.get(LevelClic).Amount) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().addItem(item);
							utils.removeItemsIn(player, Game.GoldPrices.get(LevelClic));
							Game.Teams.get(Game.getTeam(player)).upgradeGold();
							Game.Teams.get(Game.getTeam(player)).updateStands(Game.getTeam(player));
							utils.openUpSummonShop(player);
						} else {
							int Need = Game.GoldPrices.get(LevelClic).Amount-utils.getAmount(player, Game.GoldPrices.get(LevelClic).Type);
							player.sendMessage(Game.Prefix+"§cYou need §7"+Need+" "+Game.GoldPrices.get(LevelClic).Type.name()+"§c !");
						} 
					} else if (event.getCurrentItem().getType() == Material.DIAMOND) {
						int LevelClic = 0;
						String Name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§b§lDiamond Team Summoner ", "");
						LevelClic = (int)Float.parseFloat(Name);
						if (utils.getAmount(player, Game.DiamondPrices.get(LevelClic).Type) >= Game.DiamondPrices.get(LevelClic).Amount) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().addItem(item);
							utils.removeItemsIn(player, Game.DiamondPrices.get(LevelClic));
							Game.Teams.get(Game.getTeam(player)).upgradeDiamond();
							Game.Teams.get(Game.getTeam(player)).updateStands(Game.getTeam(player));
							utils.openUpSummonShop(player);
						} else {
							int Need = Game.DiamondPrices.get(LevelClic).Amount-utils.getAmount(player, Game.DiamondPrices.get(LevelClic).Type);
							player.sendMessage(Game.Prefix+"§cYou need §7"+Need+" "+Game.DiamondPrices.get(LevelClic).Type.name()+"§c !");
						} 
					} else if (event.getCurrentItem().getType() == Material.BARRIER){
						utils.openMainUpgrade(player);
					}
					break;
				
				case "§3Bed§bWars§f - ShopBlocks":
					event.setCancelled(true);
					
					if (utils.getAmount(player, Game.BlockPrices.get(event.getCurrentItem().getType()).Type) >= Game.BlockPrices.get(event.getCurrentItem().getType()).Amount) {
						ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
						player.getInventory().addItem(item);
						utils.removeItemsIn(player, Game.BlockPrices.get(event.getCurrentItem().getType()));
					} else {
						int Need = Game.BlockPrices.get(event.getCurrentItem().getType()).Amount-utils.getAmount(player, Game.BlockPrices.get(event.getCurrentItem().getType()).Type);
						player.sendMessage(Game.Prefix+"§cYou need §7"+Need+" "+Game.BlockPrices.get(event.getCurrentItem().getType()).Type.name()+"§c !");
					} 
					if (event.getCurrentItem().getType() == Material.BARRIER) {
						utils.openMainShopMenu(player);
					}
					break;
					
				case "§3Bed§bWars§f - ShopTool":
					event.setCancelled(true);
					
					if (utils.getAmount(player, Game.ToolPrices.get(event.getCurrentItem().getType()).Type) >= Game.ToolPrices.get(event.getCurrentItem().getType()).Amount) {
						ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
						player.getInventory().addItem(item);
						utils.removeItemsIn(player, Game.ToolPrices.get(event.getCurrentItem().getType()));
					} else {
						int Need = Game.ToolPrices.get(event.getCurrentItem().getType()).Amount-utils.getAmount(player, Game.ToolPrices.get(event.getCurrentItem().getType()).Type);
						player.sendMessage(Game.Prefix+"§cYou need §7"+Need+" "+Game.ToolPrices.get(event.getCurrentItem().getType()).Type.name()+"§c !");
					}
					if (event.getCurrentItem().getType() == Material.BARRIER) {
						utils.openMainShopMenu(player);
					}
					break;
					
				case "§3Bed§bWars§f - ShopArmor":
					event.setCancelled(true);
					
					if (utils.getAmount(player, Game.ArmorPrices.get(event.getCurrentItem().getType()).Type) >= Game.ArmorPrices.get(event.getCurrentItem().getType()).Amount) {
						if (event.getCurrentItem().getType() == Material.CHAINMAIL_HELMET || event.getCurrentItem().getType() == Material.IRON_HELMET || event.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().setHelmet(item);
							utils.removeItemsIn(player, Game.ArmorPrices.get(event.getCurrentItem().getType()));
						} else if (event.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE || event.getCurrentItem().getType() == Material.IRON_CHESTPLATE || event.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().setChestplate(item);
							utils.removeItemsIn(player, Game.ArmorPrices.get(event.getCurrentItem().getType()));
						} else if (event.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS || event.getCurrentItem().getType() == Material.IRON_LEGGINGS || event.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().setLeggings(item);
							utils.removeItemsIn(player, Game.ArmorPrices.get(event.getCurrentItem().getType()));
						} else if (event.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS || event.getCurrentItem().getType() == Material.IRON_BOOTS || event.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
							ItemStack item = new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getAmount());
							player.getInventory().setBoots(item);
							utils.removeItemsIn(player, Game.ArmorPrices.get(event.getCurrentItem().getType()));
						} else {
							utils.openMainShopMenu(player);
						}
					} else {
						int Need = Game.ArmorPrices.get(event.getCurrentItem().getType()).Amount-utils.getAmount(player, Game.ArmorPrices.get(event.getCurrentItem().getType()).Type);
						player.sendMessage(Game.Prefix+"§cYou need §7"+Need+" "+Game.ArmorPrices.get(event.getCurrentItem().getType()).Type.name()+"§c !");
					}
					if (event.getCurrentItem().getType() == Material.BARRIER) {
						utils.openMainShopMenu(player);
					}
					break;
					
				}
			}
		}
	}
	
}
