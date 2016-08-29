package fr.skybeastmc.guilib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.skybeastmc.guilib.guis.EnchantingTableGUI;
import fr.skybeastmc.guilib.guis.EnchantingTableGUIView;

public class EnchantingTableGUIEnchantEvent extends Event implements GUIEvent {
	private EnchantingTableGUIView view;
	private int slot;
	
	public EnchantingTableGUIEnchantEvent(int slot, EnchantingTableGUIView view) {
		this.slot = slot;
		this.view = view;
	}

	public EnchantingTableGUI getGui() {
		return (EnchantingTableGUI) view.getGui();
	}

	public EnchantingTableGUIView getView() {
		return view;
	}

	public int getSlot() {
		return slot;
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
