package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.GUIType;

public class BrewingStandGUI extends GUI {

	public BrewingStandGUI(String title) {
		super(GUIType.BREWING_STAND, title);
	}

	public final BrewingStandGUIView open(Player player) {
		return (BrewingStandGUIView) super.open(player);
	}

	public final BrewingStandGUIView open(Player player, boolean hidePlayerItem) {
		return (BrewingStandGUIView) super.open(player, hidePlayerItem);
	}

}
