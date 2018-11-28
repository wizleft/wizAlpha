package wizAlpha.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Tptop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.DARK_RED+"Can't tp console!");
			return false;
		}
		Player player = (Player) sender;
		if(!player.isOp()&&!player.hasPermission("wizAlpha.tptop"))
		{
			sender.sendMessage(ChatColor.DARK_RED+"You don't have permissions!");
			return false;
		}
		player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation());
		player.sendMessage(ChatColor.GOLD+"Teleported to top!");
		return true;
	}

}
