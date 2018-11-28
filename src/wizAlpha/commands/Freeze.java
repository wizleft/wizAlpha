package wizAlpha.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.Main;

public class Freeze implements CommandExecutor {
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String frozen=ChatColor.GOLD+"Already frozen: ",unfrozen=ChatColor.GOLD+"Froze: ",name1,name2;
		
		if(!(sender instanceof Player)&&args.length==0)
		{
			sender.sendMessage("Can't freeze the console");
			return false;
		}
		Player player = (Player) sender;
		if(args.length==0)
		{
			player = (Player) sender;
			if (!player.isOp()&&!player.hasPermission("wizAlpha.freeze")) {
				player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
				return false;
			}
			else {
				if(!Main.FrozenPlayers.contains(player)) {
					Main.FrozenPlayers.add(player);
					player.sendMessage(ChatColor.DARK_RED+"You've been frozen.");
					return true;
				}
				player.sendMessage(ChatColor.DARK_RED+"You are already frozen.");
				return true;
			}
		}
		if (!player.isOp()&&!player.hasPermission("wizAlpha.freeze")) {
			player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
			return false;
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			for (String s : args){
				name1=p.getName().toLowerCase();
				name2=s.toLowerCase();
				if(name1.matches(name2)){
					if(!Main.FrozenPlayers.contains(p)) {
						Main.FrozenPlayers.add(p);
						unfrozen+=ChatColor.DARK_RED+p.getName()+ChatColor.GOLD+", ";
						for(Player pOP : Bukkit.getOnlinePlayers())
							if((pOP.isOp()||pOP.hasPermission("wizalpha.freeze.notify"))&&!pOP.equals(player))
								pOP.sendMessage(player.getDisplayName()+ChatColor.GOLD+" froze "+ChatColor.DARK_RED+p.getName());
						p.sendMessage(player.getDisplayName()+ChatColor.DARK_RED+" froze you!");
						break;
					}
					else { frozen+=ChatColor.DARK_RED+p.getName()+ChatColor.GOLD+", "; }
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
		player.sendMessage(unfrozen);
		player.sendMessage(frozen);
		return true;
	}

}
