package wizAlpha.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.Main;

public class SaveConfig implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
		{
			execute();
			sender.sendMessage("Saved config");
			return true;
		}
		Player player = (Player) sender;
		if(!player.isOp()&&!player.hasPermission("wizAlpha.saveconfig"))
		{
			sender.sendMessage(ChatColor.DARK_RED+"You don't have permissions!");
			return false;
		}
		execute();
		player.sendMessage(ChatColor.GOLD+"Saved config");
		return true;
	}
	public void execute() {
		Main.config.set("random_drop_enable", Main.chanceEnable);
		Main.config.set("iron_chance", Main.ironChance);
		Main.config.set("iron_gold", Main.goldChance);
		Main.config.set("diamond_chance", Main.diamondChance);
		Main.saveTheConfig();
	}
}
