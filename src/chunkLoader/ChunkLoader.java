package chunkLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

public class ChunkLoader { 
	
	/**
	 * It unloads all chuncks of loaded list
	 */
	public static void ChunkUnloading() {
		@SuppressWarnings("rawtypes")
		//Lista chunk caricati
		List l = ChunkLoader.getChunkLoadedList();
		
		//disattivazione del FORCE LOADING
		for(int i=0; i< l.size(); i++) {
			int xz[] = findXZ(String.valueOf(l.get(i)));
			
			Bukkit.getServer().getWorld("world").setChunkForceLoaded(xz[0], xz[1], false);
		}
	}
	
	/**
	 * It loads all chunks had been written in chunk list
	 */
	public static void ChunkLoading() {		
		//Lista del config.yml
		List<String> l = Main.getInstance().getConfig().getStringList("ChunkLoader.chunks");
		
		//Attivazione del FORCE LOADING per i chunk nell lista
		for(int i=0; i< l.size(); i++) {
			try {				
				int xz[] = findXZ(String.valueOf(l.get(i)));
				
				Bukkit.getServer().getWorld("world").setChunkForceLoaded(xz[0], xz[1], true);
				//Debug delle attività
				if(Boolean.parseBoolean(Main.getInstance().getConfig().getString("ChunkLoader.debug"))) {
					System.out.println("[ChunkLoader][Debug] Enabled forced loading for "+ xz[0] +","+ xz[1] +" chunk" );
				}
			} catch(Exception e) {
				//Eccezione di caricamento dei chunk nella lista
				System.out.println("[ChunkLoader] #Error: FORMAT ERROR: You must check the chunk format in the chunk list of config.yml");
			}
		}
	}
	
	/**
	 * It converts a String coordinate into a int coordinate
	 * 
	 * @param s -> String chunk coordinate  
	 * @return int[] -> int chunk coordinate
	 */
	public static int[] findXZ(String s) {
		int arr[] = new int[2];
		int spacePos=-1;
		
		for(int i=0; i < s.length(); i++) {
			if(s.charAt(i) == ' ') {
				spacePos = i;
				break;
			}
		}
		
		arr[0] = Integer.valueOf(s.substring(0, spacePos));
		arr[1] = Integer.valueOf(s.substring(spacePos +1, s.length()));
		
		return arr;
	}
	
	/**
	 * In returns the chunks list of a config.yml
	 * 
	 * @return List -> Chunk list
	 */
	public static List<String> getChunkList(){
		return Main.getInstance().getConfig().getStringList("ChunkLoader.chunks");
	}
	
	/**
	 * It returns the server forced loaded chunck list
	 * 
	 * @return List -> chunk list
	 */
	public static List<String> getChunkLoadedList(){
		List<String> l = new ArrayList<String>();
		
		//Collezione di chunk 
		Collection<Chunk> chunks = Bukkit.getServer().getWorld("world").getForceLoadedChunks(); 
		
		//Trasformazione in lista con il chunk format
		for(Chunk c : chunks) {
			int x=c.getX();
			int z=c.getZ();
			
			l.add(x +" "+ z);
		}
		
		return l;
	}
}
