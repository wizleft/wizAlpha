package wizAlpha.commands;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

public class TPR implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED+"This command is not for console.");
			return false;
		}
		Player player = (Player) sender;
		if(!player.isOp()&&!player.hasPermission("wizAlpha.tpr"))
		{
			player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
			return false;
		}
		Random r = new Random();
		int x=r.nextInt(10000);
		int z=r.nextInt(10000);
		int y=1;
		if (r.nextInt(3)>1)
			x=-x;
		if (r.nextInt(3)>1)
			z=-z;
		Location loc=new Location(player.getWorld(),x,y,z);
		loc.setY(player.getWorld().getHighestBlockAt(loc).getY());
		player.sendMessage("Teleported you to "+x+", "+(int)loc.getY()+", "+z);
		player.teleport(loc);
		return true;
	}

}
