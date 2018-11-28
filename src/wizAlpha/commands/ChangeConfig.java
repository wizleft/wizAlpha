package wizAlpha.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.Main;

public class ChangeConfig implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
		{
			execute(sender, args);
			return true;
		}
		Player player = (Player) sender;
		if(!player.isOp()&&!player.hasPermission("wizAlpha.changeconfig"))
		{
			sender.sendMessage(ChatColor.DARK_RED+"You don't have permissions!");
			return false;
		}
		execute(sender, args);
		return true;
	}
	private void execute(CommandSender sender, String[] args) {
		if (args.length<3) {
			sender.sendMessage(ChatColor.DARK_RED+"/ChangeConfig <type> <name> <value>");
			return;
		}
		
		if(args[0].toLowerCase().matches("bool"))
			try {
				Main.config.set(args[1], Boolean.parseBoolean(args[2]));
			} catch (Exception e) {
				sender.sendMessage(ChatColor.GOLD+"The value "+ChatColor.DARK_RED+args[2]+ChatColor.GOLD+" is not existing");
			}
		else if(args[0].toLowerCase().matches("int"))
			try {
				Main.config.set(args[1], Integer.parseInt(args[2]));
			} catch (Exception e) {
				sender.sendMessage(ChatColor.GOLD+"The value "+ChatColor.DARK_RED+args[2]+ChatColor.GOLD+" is not existing");
			}
		else if(args[0].toLowerCase().matches("string"))
			Main.config.set(args[1], args[2]);
		else
			sender.sendMessage(ChatColor.GOLD+"There's no type called "+ChatColor.DARK_RED+args[0]);
		Main.saveTheConfig();
		Main.reloadWizConfig();
		sender.sendMessage(ChatColor.DARK_RED+args[1]+ChatColor.GOLD+" was changed to "+ChatColor.DARK_RED+args[2]);
	}
}
