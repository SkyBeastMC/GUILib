package fr.skybeastmc.guilib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import fr.skybeastmc.guilib.guis.GUI;
import fr.skybeastmc.guilib.guis.GUIView;

public class GUIClickEvent extends Event implements Cancellable, GUIEvent {
	private GUIView view;
	private int slot;
	private GUIClick clickType;
	private ItemStack item;
	private boolean cancel = true;
	private boolean real = false;
	private GUIAction action;

	public GUIClickEvent(int slot, GUIClick clickType, ItemStack item,
			GUIView view, GUIAction action) {
		this.slot = slot;
		this.clickType = clickType;
		this.item = item;
		this.view = view;
		this.action = action;
	}

	public GUI getGui() {
		return view.getGui();
	}

	public GUIView getView() {
		return view;
	}

	public int getSlot() {
		return slot;
	}

	public int getPlayerSlot() {
		return view.getGui().getSize() + slot;
	}

	public boolean isPlayerSlot() {
		return slot - view.getGui().getSize() <= 0;
	}

	public GUIClick getClickType() {
		return clickType;
	}

	public ItemStack getItem() {
		return item;
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

	public boolean isReal() {
		return real;
	}

	public void setReal(boolean real) {
		this.real = real;
	}

	public GUIAction getAction() {
		return action;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
