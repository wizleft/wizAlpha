package wizAlpha.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.Main;

public class Frozen implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String line="";
		Player player;
		if (!(sender instanceof Player))
		{
			
		}
		else {
			player = (Player) sender;
			if(!player.isOp()&&!player.hasPermission("wizAlpha.frozen"))
			{
				player.sendMessage(ChatColor.DARK_RED+"You don't have enough permissions to use this command!");
				return false;
			}
		}
		line+=ChatColor.GOLD+"Frozen players: ";
		if(Main.FrozenPlayers.isEmpty())
			line+=ChatColor.RED+"<No Frozen Players!>";
		else {
			for (Player p : Main.FrozenPlayers) {
				line+=ChatColor.AQUA+p.getName()+ChatColor.GOLD+", ";
			}
		}
		sender.sendMessage(line);
		return true;
	}
	
	
	
}
