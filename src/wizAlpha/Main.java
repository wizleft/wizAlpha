package wizAlpha;


import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import wizAlpha.commands.TPR;
import wizAlpha.commands.Tptop;
import wizAlpha.commands.Unfreeze;
import wizAlpha.commands.Bubble;
import wizAlpha.commands.ChangeConfig;
import wizAlpha.commands.Freeze;
import wizAlpha.commands.Frozen;
import wizAlpha.commands.PrivateVault;
import wizAlpha.commands.SaveConfig;
import wizAlpha.events.Events;

public class Main extends JavaPlugin {

	public static ArrayList<Player> FrozenPlayers = new ArrayList<>();
	public static FileConfiguration config;
	public static int ironChance=0, goldChance=0, diamondChance=0;
	public static boolean chanceEnable = false;
	public static Main main;
	public static File invFile;
	public static FileConfiguration invConfig;
	
	@Override
	public void onEnable() {
		config = this.getConfig();
		main = this;
		loadConfig();
		
		reloadWizConfig();
		
		//Commands
		getCommand("bubble").setExecutor(new Bubble());
		getCommand("changeconfig").setExecutor(new ChangeConfig());
		getCommand("freeze").setExecutor(new Freeze());
		getCommand("frozen").setExecutor(new Frozen());
		getCommand("privatevault").setExecutor(new PrivateVault());
		getCommand("saveconfig").setExecutor(new SaveConfig());
		getCommand("tpr").setExecutor(new TPR());
		getCommand("tptop").setExecutor(new Tptop());
		getCommand("unfreeze").setExecutor(new Unfreeze());
		
		//Event Handler
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		
		
		getServer().getConsoleSender().sendMessage("wizAlpha is being enabled");
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage("wizAlpha is being disabled");
	}
	
	public static void saveTheConfig()
	{
		main.saveConfig();
	}
	public static void reloadWizConfig() {
		try {
			ironChance = config.getInt("iron_chance");
			goldChance = config.getInt("gold_chance");
			diamondChance = config.getInt("diamond_chance");
		} catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage("Something wrong with chances in config.");
		}
		
		try {
			chanceEnable = config.getBoolean("random_drop_enable");
		} catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage("Something wrong with random loot spawn in config.");
		}
		
	}
	
	private void loadConfig() {
		if(!getDataFolder().exists())
			getDataFolder().mkdirs();
		invFile = new File(getDataFolder(), "playerVault.yml");
		if (!invFile.exists()) {
			try {
				invFile.createNewFile();
				getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Inventory file successfully created!");
			} catch (Exception e) {
				getServer().getConsoleSender().sendMessage(ChatColor.RED+"Inventory file wasn't created!");
			}
		}
		invConfig = YamlConfiguration.loadConfiguration(invFile);
	}
}
