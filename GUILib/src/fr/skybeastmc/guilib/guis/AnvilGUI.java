package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.events.AnvilGUITypeCompleteEvent;
import fr.skybeastmc.guilib.events.AnvilGUITypeEvent;

public class AnvilGUI extends GUI {

	public AnvilGUI(String title) {
		super(GUIType.ANVIL, title);
	}

	public void onType(AnvilGUITypeEvent event) {
	}

	public void onTypeComplete(AnvilGUITypeCompleteEvent event) {
	}

	public final AnvilGUIView open(Player player) {
		return (AnvilGUIView) super.open(player);
	}

	public final AnvilGUIView open(Player player, boolean hidePlayerItem) {
		return (AnvilGUIView) super.open(player, hidePlayerItem);
	}

}
