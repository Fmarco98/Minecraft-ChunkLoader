package chunkLoader;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd_chunkLoad implements CommandExecutor { 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("chunkload")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("chunkloader.use")) {
					Player target = (Player) sender;
					
					Location playerLocation = target.getLocation();
					Chunk cChunk = playerLocation.getChunk();
					String cString = cChunk.getX() +" "+ cChunk.getZ();
					
					List<String> l = ChunkLoader.getChunkList();
					boolean exist=false;
					for(int i=0; i < l.size(); i++) {
						if(String.valueOf(l.get(i)).equalsIgnoreCase(cString)) {
							exist = true;
							break;
						}
					}
					if(!exist) {
						Bukkit.getServer().getWorld("world").setChunkForceLoaded(cChunk.getX(), cChunk.getZ(), true);
						l.add(cString);
						sender.sendMessage(Main.getInstance().getConfig().getString("ChunkLoader.messages.chunkload.forced-loading-enabled")+ "["+ cChunk.getX() +" "+ cChunk.getZ()+"]");
					} else {
						sender.sendMessage(Main.getInstance().getConfig().getString("ChunkLoader.messages.chunkload.forced-loading-already-enabled"));
					}
					
					Main.getInstance().getConfig().set("ChunkLoader.chunks", l);
					Main.getInstance().saveConfig();
					
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("ChunkLoader.messages.cannot-use-cmd"));
					return true;
				}
			} else {
				sender.sendMessage("[ChunkLoader] Excepion: Only players can use this command");
				return true;
			}
		}
		
		return false;
	}
	
}
