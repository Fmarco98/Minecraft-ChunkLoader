package chunkLoader;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Cmd_chunkLoadedList implements CommandExecutor{ 
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {	
		//Comando eseguibile da tutti i sender type
		if(cmd.getName().equalsIgnoreCase("chunkloadedlist")) {
			//Controllo permesso
			if(sender.hasPermission("chunkloader.use")) {				
				List<String> l = ChunkLoader.getChunkLoadedList();
				sender.sendMessage(Main.getInstance().getConfig().getString("ChunkLoader.messages.chunkloadedlist-msg"));
				
				//Stampa della lista
				for(int i=0; i < l.size(); i++) {
					sender.sendMessage("[Chunk "+(i+1)+"] "+String.valueOf(l.get(i)));
				}
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("ChunkLoader.messages.cannot-use-cmd"));
				return true;
			}
		}
		return false;
	}
}