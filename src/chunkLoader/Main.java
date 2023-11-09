package chunkLoader;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static Main plugin;
	
	public void onEnable() {
		//Operazioni caricamento
		plugin = this;
		saveDefaultConfig();
		
		this.getCommand("chunklist").setExecutor(new Cmd_chunkList());
		this.getCommand("chunkloadedlist").setExecutor(new Cmd_chunkLoadedList());
		this.getCommand("chunkload").setExecutor(new Cmd_chunkLoad());
		this.getCommand("chunkunload").setExecutor(new Cmd_chunkUnload());
		
		ChunkLoader.ChunkUnloading();
		ChunkLoader.ChunkLoading();
		System.out.println("All chunks are loaded");
		
		//Massaggio caricamento completo
		System.out.println("\t/--------------------------\\");
		System.out.println("\t|       ChunkLoader        |");
		System.out.println("\t\\--------------------------/");
		System.out.println("\t\tStatus: Enabled");
		System.out.println("\t\tVersion: "+ plugin.getDescription().getVersion());
	}
	
	public void onDisable() {
		System.out.println("\t[ChunkLoader] Status: Disabled");
	}
	
	public static Main getInstance() {
		return plugin;
	}
}
