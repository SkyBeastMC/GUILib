package fr.skybeastmc.guilib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.skybeastmc.guilib.guis.AnvilGUI;
import fr.skybeastmc.guilib.guis.AnvilGUIView;

public class AnvilGUITypeEvent extends Event implements GUIEvent {
	private AnvilGUIView view;
	private String input;

	public AnvilGUITypeEvent(String input, AnvilGUIView view) {
		this.view = view;
		this.input = input;
	}

	public AnvilGUI getGui() {
		return (AnvilGUI) view.getGui();
	}

	public String getInput() {
		return input;
	}

	public AnvilGUIView getView() {
		return view;
	}

	public Player getPlayer() {
		return view.getPlayer();
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
