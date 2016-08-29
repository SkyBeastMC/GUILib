package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.skybeastmc.guilib.GUIInventory;
import fr.skybeastmc.guilib.GUIManager;
import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.InventoryUtils;
import fr.skybeastmc.guilib.PacketCreator;

public class GUIView {
	protected GUI gui;
	protected Player player;
	protected GUIInventory inventory;
	protected final int guiID;
	protected boolean hidePlayerItems;

	public GUIView(GUI gui, Player player, int guiID, boolean hidePlayerItems) {
		this.player = player;
		this.gui = gui;
		inventory = new GUIInventory(gui.getType());
		this.guiID = guiID;
		this.hidePlayerItems = hidePlayerItems;
	}

	public final void refresh() {
		ItemStack[] items = new ItemStack[36 + gui.getSize()];
		for (int i = 0; i < (hidePlayerItems ? 36 : 0) + gui.getSize(); i++) {
			ItemStack item = inventory.get(i);
			items[i] = item;

		}
		if (!hidePlayerItems)
			for (int i = 0; i < 36; i++) {
				items[gui.getSize() + i] = player.getInventory().getItem(
						InventoryUtils.playerSlotToNetwork(i));
			}

		PacketCreator.sendWindowItemsPacket(player, guiID, items);
		PacketCreator.sendSetItemPacket(player, -1, -1, inventory.getCursor());
	}

	public final void close() {
		gui.close(player);
	}

	public final boolean isOpen() {
		return GUIManager.isOpenGui(player, this);
	}

	public final ItemStack getPlayerItem(int slot) {
		return getItem(slot + gui.getSize());
	}

	public final void setPlayerItem(int slot, ItemStack item) {
		setItem(slot + gui.getSize(), item);
	}

	public final void setItem(int slot, ItemStack item) {
		inventory.put(slot, item);
		PacketCreator.sendSetItemPacket(player, guiID, slot, item);
	}

	public final void setItems(ItemStack[] items) {
		for (int i = 0; i < items.length - (hidePlayerItems ? 36 : 0); i++) {
			ItemStack item = items[i];
			if (item != null)
				inventory.put(i, item);
		}
		PacketCreator.sendWindowItemsPacket(player, guiID, items);
	}

	public final void setCursor(ItemStack item) {
		inventory.setCursor(item);
		PacketCreator.sendSetItemPacket(player, -2, -2, item);
	}

	public final ItemStack getCursor() {
		return inventory.getCursor();
	}

	public final ItemStack getItem(int slot) {
		return inventory.get(slot);
	}

	public final GUIType getType() {
		return gui.getType();
	}

	public final GUI getGui() {
		return gui;
	}

	public final Player getPlayer() {
		return player;
	}

	public final GUIInventory getInventory() {
		return inventory;
	}

	public final void setInventory(GUIInventory inventory) {
		this.inventory = inventory;
	}

	public int getGuiID() {
		return guiID;
	}
}
