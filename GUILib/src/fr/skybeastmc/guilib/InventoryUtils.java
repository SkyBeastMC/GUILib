package fr.skybeastmc.guilib;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.skybeastmc.guilib.events.GUIAction;
import fr.skybeastmc.guilib.events.GUIClick;
import fr.skybeastmc.guilib.events.GUIClickEvent;
import fr.skybeastmc.guilib.guis.GUIView;

public class InventoryUtils {

	public static void closeOpenInventory(Player player) {
		PacketCreator.sendCloseWindowPacket(player);
		refreshInventory(player);
	}

	public static void refreshInventory(Player player) {
		player.updateInventory();
	}

	public static GUIClick getClickType(int slot, int button, String shift) {
		switch (shift) {
		case "PICKUP":
			switch (button) {
			case 0:
				if (slot == -999)
					return GUIClick.WINDOW_BORDER_LEFT;
				return GUIClick.LEFT;
			case 1:
				if (slot == -999)
					return GUIClick.WINDOW_BORDER_RIGHT;
				return GUIClick.RIGHT;
			}
		case "QUICK_MOVE":
			switch (button) {
			case 0:
				return GUIClick.SHIFT_LEFT;
			case 1:
				return GUIClick.SHIFT_RIGHT;
			}
		case "SWAP":
			return GUIClick.intToNumberKey(button);
		case "CLONE":
			return GUIClick.MIDDLE;
		case "THROW":
			switch (button) {
			case 0:
				if (slot == -999)
					return GUIClick.WINDOW_BORDER_LEFT;
				return GUIClick.DROP;
			case 1:
				if (slot == -999)
					return GUIClick.WINDOW_BORDER_RIGHT;
				return GUIClick.CONTROL_DROP;
			}
		case "QUICK_CRAFT":
			switch (button) {
			case 0:
				return GUIClick.START_LEFT_MOUSE_DRAG;
			case 1:
				return GUIClick.LEFT_MOUSE_DRAG_ADD_SLOT;
			case 2:
				return GUIClick.END_LEFT_MOUSE_DRAG;
			case 4:
				return GUIClick.START_RIGHT_MOUSE_DRAG;
			case 5:
				return GUIClick.RIGHT_MOUSE_DRAG_ADD_SLOT;
			case 6:
				return GUIClick.END_RIGHT_MOUSE_DRAG;
			}
		case "PICKUP_ALL":
			return GUIClick.DOUBLE_CLICK;
		}

		return GUIClick.UNKNOWN;

	}

	public static GUIAction getAction(GUIClick click, int slot, GUIView view,
			Player player) {
		GUIInventory inv = view.getInventory();
		ItemStack cursor = inv.getCursor();
		ItemStack item = inv.get(slot);

		switch (click) {
		case LEFT:
			if (cursor != null)
				if (item != null)
					return GUIAction.SWAP_WITH_CURSOR; // Cursor & Item
				else
					return GUIAction.PLACE_ALL; // Cursor
			else if (item != null)
				return GUIAction.PICKUP_ALL; // Item
			else
				return GUIAction.NOTHING; // Nothing

		case RIGHT:
			if (cursor != null)
				if (item != null)
					return GUIAction.SWAP_WITH_CURSOR; // Cursor & Item
				else
					return GUIAction.PLACE_ONE; // Cursor
			else if (item != null)
				return GUIAction.PICKUP_HALF; // Item
			else
				return GUIAction.NOTHING; // Nothing
		case SHIFT_LEFT:
			if (item != null)
				return GUIAction.MOVE_TO_OTHER_INVENTORY;
			else
				return GUIAction.NOTHING;
		case SHIFT_RIGHT:
			if (item != null)
				return GUIAction.MOVE_TO_OTHER_INVENTORY;
			else
				return GUIAction.NOTHING;
		case DOUBLE_CLICK:
			if (cursor != null)
				return GUIAction.COLLECT_TO_CURSOR;
			else
				return GUIAction.NOTHING;
		case DROP:
			if (item != null)
				return GUIAction.DROP_ONE_SLOT;
			else
				return GUIAction.NOTHING;
		case CONTROL_DROP:
			if (item != null)
				return GUIAction.DROP_ALL_SLOT;
			else
				return GUIAction.NOTHING;
		case WINDOW_BORDER_LEFT:
			if (cursor != null)
				return GUIAction.DROP_ALL_CURSOR;
			else
				return GUIAction.NOTHING;
		case WINDOW_BORDER_RIGHT:
			if (cursor != null)
				return GUIAction.DROP_ONE_CURSOR;
			else
				return GUIAction.NOTHING;
		case MIDDLE:
			if (cursor == null && item != null
					&& player.getGameMode().equals(GameMode.CREATIVE))
				return GUIAction.CLONE_STACK;
			else
				return GUIAction.NOTHING;
		default:
			if (cursor == null && item != null && click.isNumberKey()) {
				ItemStack item2 = inv.get(view.getGui().getSize() + 36
						+ click.getNumberKey());
				if (item2 != null)
					return GUIAction.HOTBAR_MOVE_AND_READD;
				else
					return GUIAction.HOTBAR_SWAP;
			}

			return GUIAction.UNKNOWN;
		}
	}

	public static void doAction(GUIClickEvent event) {

	}

	public static int playerSlotToNetwork(int slot) {

		return slot >= 27 ? slot - 27 : slot + 9;
	}
}
