package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.skybeastmc.guilib.GUIInventory;
import fr.skybeastmc.guilib.GUIManager;
import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.InventoryUtils;
import fr.skybeastmc.guilib.PacketCreator;
import fr.skybeastmc.guilib.debug.Debug;
import fr.skybeastmc.guilib.events.GUIClickEvent;
import fr.skybeastmc.guilib.events.GUICloseEvent;
import fr.skybeastmc.guilib.events.GUIOpenEvent;
import fr.skybeastmc.guilib.listeners.ProtocolPacketListener;

public abstract class GUI {
	protected GUIInventory inventory;

	protected GUI(GUIInventory inventory, String title) {
		this.inventory = inventory;
		this.inventory.setTitle(title);
	}

	protected GUI(GUIType type, String title) {
		inventory = new GUIInventory(type);
		inventory.setTitle(title);
	}

	public void onGUIClick(GUIClickEvent event) {
	}

	public void onGUIClose(GUICloseEvent event) {
	}

	public void onGUIOpen(GUIOpenEvent event) {
	}

	@SuppressWarnings("deprecation")
	public int getStorageSize() {
		return inventory.getType().getStorageSize();
	}

	@SuppressWarnings("deprecation")
	public int getSize() {
		return inventory.getType().getSize();
	}

	public final GUIType getType() {
		return inventory.getType();
	}

	public final String getTitle() {
		return inventory.getTitle();
	}

	public final void setTitle(String title) {
		inventory.setTitle(title);
	}

	public final GUIInventory getInventory() {
		return inventory;
	}

	public final void setInventory(GUIInventory inventory) {
		this.inventory = inventory;
	}

	public GUIView open(Player player, boolean hidePlayerItems) {
		final int guiID = GUIManager.nextGUIID();

		PacketCreator.sendOpenWindowPacket(player, guiID, inventory, this);

		GUIView view = null;
		if (inventory.getType() == GUIType.FURNACE)
			view = new FurnaceGUIView((FurnaceGUI) this, player, guiID,
					hidePlayerItems);
		else if (inventory.getType() == GUIType.ENCHANTING_TABLE)
			view = new EnchantingTableGUIView((EnchantingTableGUI) this,
					player, guiID, hidePlayerItems);
		else if (inventory.getType() == GUIType.ANVIL)
			view = new AnvilGUIView((AnvilGUI) this, player, guiID,
					hidePlayerItems);
		else
			view = new GUIView(this, player, guiID, hidePlayerItems);

		GUIManager.addOpenGui(player, view);

		ItemStack[] items = new ItemStack[36 + getSize()];
		for (int i = 0; i < (hidePlayerItems ? 36 : 0) + getSize(); i++) {
			ItemStack item = inventory.get(i);
			items[i] = item;

		}
		if (!hidePlayerItems)
			for (int i = 0; i < 36; i++) {
				items[getSize() + i] = player.getInventory().getItem(
						InventoryUtils.playerSlotToNetwork(i));
				Debug.debug("Item "
						+ (getSize() + i)
						+ " = "
						+ player.getInventory().getItem(
								InventoryUtils
										.playerSlotToNetwork(i))
						+ " "
						+ InventoryUtils.playerSlotToNetwork(i));
			}

		/*
		 * for (int i = 0; i < (hidePlayerItems ? 36 : 0) + getSize(); i++) {
		 * ItemStack item = inventory.get(i); Debug.debug(i + " " + item); if
		 * (item == null) item = new ItemStack(Material.ACACIA_DOOR);
		 * 
		 * view.setItem(i, item);
		 * 
		 * }
		 */

		view.setItems(items);
		PacketCreator.sendSetItemPacket(player, -1, -1, inventory.getCursor());

		ProtocolPacketListener.callEvent(new GUIOpenEvent(view));
		return view;
	}

	public GUIView open(Player player) {
		return open(player, true);
	}

	public final void close(Player player) {
		InventoryUtils.closeOpenInventory(player);
	}

	public final void setItem(int i, ItemStack item) {
		inventory.put(i, item);
	}

	public final void setCursor(ItemStack item) {
		inventory.setCursor(item);
	}

	public final ItemStack getCursor() {
		return inventory.getCursor();
	}

	public final void setPlayerItem(int slot, ItemStack item) {
		setItem(slot + getSize(), item);
	}

}
