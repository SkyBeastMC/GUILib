package fr.skybeastmc.guilib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import fr.skybeastmc.guilib.guis.AnvilGUI;
import fr.skybeastmc.guilib.guis.AnvilGUIView;

public class AnvilGUITypeCompleteEvent extends Event implements Cancellable, GUIEvent {
	private AnvilGUIView view;
	private GUIClick clickType;
	private ItemStack item;
	private String input;
	private boolean cancel = true;

	public AnvilGUITypeCompleteEvent(GUIClick clickType, ItemStack item,
			String input, AnvilGUIView view) {
		this.clickType = clickType;
		this.item = item;
		this.input = input;
		this.view = view;
	}

	public GUIClick clickType() {
		return clickType;
	}

	public ItemStack getItem() {
		return item;
	}

	public String getInput() {
		return input;
	}

	public AnvilGUIView getView() {
		return view;
	}

	public AnvilGUI getGui() {
		return (AnvilGUI) view.getGui();
	}

	public Player getPlayer() {
		return view.getPlayer();
	}

	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public boolean isCancelled() {
		return cancel;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
