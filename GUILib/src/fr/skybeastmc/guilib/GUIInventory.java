package fr.skybeastmc.guilib;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public class GUIInventory extends HashMap<Integer, ItemStack> {
	private static final long serialVersionUID = 4459980950573098535L;
	private GUIType type;
	private String title;
	private ItemStack cursor;
	
	public GUIInventory(GUIType type) {
		super();
		this.type = type;
	}
	
	public ItemStack put(Integer paramK, ItemStack paramV) {
		if(containsKey(paramK)) {
			remove(paramK);
		}
		return super.put(paramK, paramV);
	}
	
	public GUIType getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ItemStack getItem(int i) {
		return get(i);
	}

	public ItemStack getCursor() {
		return cursor;
	}

	public void setCursor(ItemStack cursor) {
		this.cursor = cursor;
	}
}
