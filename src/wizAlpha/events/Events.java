package wizAlpha.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;


import wizAlpha.Main;


public class Events implements Listener {
	Random r = new Random();
	
	//Doesn't let the player move if frozen
	@EventHandler
	public void frozenPlayerMoved(PlayerMoveEvent event){
		if (Main.FrozenPlayers.contains(event.getPlayer()))
		event.setCancelled(true);
	}
	
	//Bonus loot if enabled
	@EventHandler
	public void bonusLoot (EntityDeathEvent e){
		if (!(e instanceof Player)&&Main.config.getBoolean("random_drop_enable")){
			e.getDrops().add(randomItem());
		}
	}
	
	@EventHandler
	public void closeInventory (InventoryCloseEvent e) {
		if(e.getInventory().getTitle().matches("Private Vault")) {
			
			List<String> itemsList = new ArrayList<>();
			List<Integer> itemsNumber = new ArrayList<>();
			List<Integer> itemsPlace = new ArrayList<>();
			for(int i = 0; i < e.getInventory().getSize(); i++) {
				try {
					if (e.getInventory().getItem(i)==null)
						continue;
					itemsList.add(e.getInventory().getItem(i).getType().toString());
					itemsNumber.add(e.getInventory().getItem(i).getAmount());
					itemsPlace.add(i);
				} catch (Exception e2) {
					e.getPlayer().sendMessage(""+e.getInventory().getContents().length);
				}
				
			}
			
			Main.invConfig.set("users."+e.getPlayer().getName()+".privateVaultItems", itemsList);
			Main.invConfig.set("users."+e.getPlayer().getName()+".privateVaultItemsNumber", itemsNumber);
			Main.invConfig.set("users."+e.getPlayer().getName()+".privateVaultItemsPlace", itemsPlace);
			try {
				Main.invConfig.save(Main.invFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};
		}
	}
	
	//Bonus loot randomizer
	private ItemStack randomItem()
	{
		ItemStack item = new ItemStack(Material.COBBLESTONE,1);
		int chance = r.nextInt(Main.ironChance+Main.goldChance+Main.diamondChance+1);
		if(chance<Main.ironChance)
			item.setType(Material.IRON_INGOT);
		else if (chance<Main.ironChance+Main.goldChance)
			item.setType(Material.GOLD_INGOT);
		else
			item.setType(Material.DIAMOND);
		return item;
	}
}
