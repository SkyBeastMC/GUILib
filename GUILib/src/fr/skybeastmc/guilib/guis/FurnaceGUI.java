package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.GUIType;

public class FurnaceGUI extends GUI {

	public FurnaceGUI(String title) {
		super(GUIType.FURNACE, title);
	}

	public final FurnaceGUIView open(Player player) {
		return (FurnaceGUIView) super.open(player);
	}

	public final FurnaceGUIView open(Player player, boolean hidePlayerItem) {
		return (FurnaceGUIView) super.open(player, hidePlayerItem);
	}

}
