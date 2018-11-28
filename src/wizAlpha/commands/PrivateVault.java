package wizAlpha.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.Main;

public class PrivateVault implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> itemsList = new ArrayList<>();
		List<Integer> itemsNumber = new ArrayList<>();
		List<Integer> itemsPlace = new ArrayList<>();
		ItemStack[] items = null;
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED+"Console can't use this command!");
			return false;
		}
		Player player = (Player) sender;
		if(!player.isOp()&&!player.hasPermission("wizAlpha.privatevault"))
		{
			player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
			return false;
		}
		
		Inventory inv = Bukkit.createInventory(null, 36, "Private Vault");
		
		if(Main.invConfig.isSet("users."+player.getName()+".privateVaultItems")) {
			itemsList = Main.invConfig.getStringList("users."+player.getName()+".privateVaultItems");
			itemsNumber = Main.invConfig.getIntegerList("users."+player.getName()+".privateVaultItemsNumber");
			itemsPlace = Main.invConfig.getIntegerList("users."+player.getName()+".privateVaultItemsPlace");
			items = new ItemStack[itemsList.size()];
		}
		try {
			for (int i=0;i<items.length;i++) {
				items[i]= new ItemStack(Material.getMaterial(itemsList.get(i)), itemsNumber.get(i));
				inv.setItem(itemsPlace.get(i), items[i]);
			}
		} catch (Exception e) {
			player.sendMessage("something went wrong");
		}
		
		player.openInventory(inv);
		return true;
	}

}
