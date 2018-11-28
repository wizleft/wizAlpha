package wizAlpha.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.Main;

public class Unfreeze implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String frozen=ChatColor.GOLD+"Unfroze: ",unfrozen=ChatColor.GOLD+"Already unfrozen: ";
		
		if(!(sender instanceof Player)&&args.length==0)
		{
			sender.sendMessage("Can't unfreeze the console");
			return false;
		}
		Player player = (Player) sender;
		if(args.length==0)
		{
			player = (Player) sender;
			if (!player.isOp()&&!player.hasPermission("wizAlpha.unfreeze")) {
				player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
				return false;
			}
			else {
				if(Main.FrozenPlayers.contains(player)) {
					Main.FrozenPlayers.remove(player);
					player.sendMessage(ChatColor.DARK_RED+"You've been unfrozen.");
					return true;
				}
				player.sendMessage(ChatColor.DARK_RED+"You are not frozen.");
				return true;
			}
		}
		if (!player.isOp()&&!player.hasPermission("wizAlpha.freeze")) {
			player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
			return false;
		}
		
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			for (String s : args){
				if(p.getName().toLowerCase().matches(s.toLowerCase())){
					if(Main.FrozenPlayers.contains(p)) {
						Main.FrozenPlayers.remove(p);
						frozen+=ChatColor.DARK_RED+p.getName()+ChatColor.GOLD+", ";
						for(Player pOP : Bukkit.getOnlinePlayers())
							if((pOP.isOp()||pOP.hasPermission("wizalpha.freeze.notify"))&&!pOP.equals(player))
								pOP.sendMessage(player.getDisplayName()+ChatColor.GOLD+" unfroze "+ChatColor.DARK_RED+p.getName());
						p.sendMessage(player.getDisplayName()+ChatColor.DARK_RED+" unfroze you!");
						break;
					}
					else 
						unfrozen+=ChatColor.DARK_RED+p.getName()+ChatColor.GOLD+", ";
				}	
			}
		}
		try {
			frozen.substring(0, frozen.length()-3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			unfrozen.substring(0, unfrozen.length()-3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		player.sendMessage(frozen);
		player.sendMessage(unfrozen);
		return true;
	}
}
