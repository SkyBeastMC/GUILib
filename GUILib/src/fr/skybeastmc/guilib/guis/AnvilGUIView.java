package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.skybeastmc.guilib.GUIType;

public class AnvilGUIView extends GUIView {
	private String input;

	public AnvilGUIView(AnvilGUI gui, Player player, int guiID, boolean hidePlayerItems) {
		super(gui, player, guiID, hidePlayerItems);
		if(gui.getType() != GUIType.ANVIL)
			throw new RuntimeException("GUIType is not an anvil.");
	}

	public String getInput() {
		return input;
	}
	
	@Deprecated
	public void _INVALID_setInput(String input) {
		this.input = input;
	}
	
	public void setInput(String input) {
		ItemStack stack = getItem(0);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(input);
		stack.setItemMeta(meta);
		
		setItem(0, stack);
		this.input = input;
	}
}
