package fr.skybeastmc.guilib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.skybeastmc.guilib.guis.GUI;
import fr.skybeastmc.guilib.guis.GUIView;

public class GUIOpenEvent extends Event implements GUIEvent {
	private GUIView view;

	public GUIOpenEvent(GUIView view) {
		this.view = view;
	}

	public GUIView getView() {
		return view;
	}

	public Player getPlayer() {
		return view.getPlayer();
	}

	public GUI getGui() {
		return view.getGui();
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
