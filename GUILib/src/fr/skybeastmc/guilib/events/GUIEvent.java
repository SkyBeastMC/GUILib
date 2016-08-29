package fr.skybeastmc.guilib.events;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.guis.GUI;
import fr.skybeastmc.guilib.guis.GUIView;

public interface GUIEvent {
	public GUI getGui();

	public GUIView getView();

	public Player getPlayer();
}
