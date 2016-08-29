package fr.skybeastmc.guilib;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.guis.GUIView;

public class GUIManager {
	private static int guiIDCounter = 0;
	private static Map<Player, GUIView> openGuis = new HashMap<>();
	
	public static int nextGUIID() {
		guiIDCounter++;
		if(guiIDCounter >= 256) guiIDCounter = 0;
		return guiIDCounter;
	}
	
	public static GUIView getOpenGui(int guiID) {
		for(Entry<Player, GUIView> entry : openGuis.entrySet())
			if(entry.getValue().getGuiID() == guiID)
				return entry.getValue();
		return null;
	}

	public static Map<Player, GUIView> getOpenGuis() {
		return openGuis;
	}

	public static void addOpenGui(Player player, GUIView openGui) {
		openGuis.put(player, openGui);
	}

	public static void removeOpenGui(Player player) {
		openGuis.remove(player);
	}
	
	public static GUIView getOpenGui(Player player) {
		return openGuis.get(player);
	}
	
	public static boolean isOpenGui(Player player, GUIView openGui) {
		if(openGuis.get(player) != null) {
			return openGuis.get(player).equals(openGui);
		}
		return false;
	}
	
	public static boolean hasOpenGui(Player player) {
		return openGuis.get(player) != null;
	}
}
