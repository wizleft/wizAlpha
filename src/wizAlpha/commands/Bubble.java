package wizAlpha.commands;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Bubble implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))	{
			if(args.length<2) {
				sender.sendMessage(ChatColor.RED+"/bubble <player> <radius>");
				return false;
			}
			execute(sender, args);
			return true;
		}
		Player player = (Player) sender;
		if(!player.isOp()&&!player.hasPermission("wizAlpha.bubble")) {
			player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
			return false;
		}
		if(args.length<2) {
			sender.sendMessage(ChatColor.RED+"/bubble <player> <radius>");
			return false;
		}
		execute(sender, args);
		return true;
	}
	private void execute(CommandSender sender, String[] args) {
		int radius=0;
		Player player=null;
		Location loc;
		
		try {
			radius = Integer.parseInt(args[1]);
		} catch (Exception e) {
			sender.sendMessage(ChatColor.DARK_RED+args[1]+" is not a number");
			return;
		}
		for (Player p : Bukkit.getOnlinePlayers()){
			if (p.getName().toLowerCase().matches(args[0].toLowerCase()))
				player = p;
		}
		if (player==null) {
			sender.sendMessage(ChatColor.DARK_RED+"Player "+args[0]+" is not found");
			return;
		}
		loc = player.getLocation();
		Location compared = new Location(player.getWorld(), 0, 0, 0);
		loc.setX((int)loc.getX());
		loc.setY((int)loc.getY());
		loc.setZ((int)loc.getZ());
		for (int x = (int)loc.getX()-radius;x<(int)loc.getX()+radius;x++)
			for (int y = (int)loc.getY()-radius;y<(int)loc.getY()+radius;y++)
				for (int z = (int)loc.getZ()-radius;z<(int)loc.getZ()+radius;z++) {
					compared.setX(x);
					compared.setY(y);
					compared.setZ(z);
					if (loc.distance(compared)<radius&&loc.distance(compared)>=(radius-1))
						compared.getBlock().setType(Material.GLASS);
				}
		/*radius--;
		for (int x = (int)loc.getX()-radius;x<(int)loc.getX()+radius;x++)
			for (int y = (int)loc.getY()-radius;y<(int)loc.getY()+radius;y++)
				for (int z = (int)loc.getZ()-radius;z<(int)loc.getZ()+radius;z++) {
					compared.setX(x);
					compared.setY(y);
					compared.setZ(z);
					if (loc.distance(compared)<radius)
						compared.getBlock().setType(Material.AIR);
				}*/
		loc.setX(loc.getX()+0.5);
		loc.setZ(loc.getZ()+0.5);
		player.teleport(loc);
		sender.sendMessage(ChatColor.GOLD+"Created bubble around "+player.getDisplayName());
	}
	
}
