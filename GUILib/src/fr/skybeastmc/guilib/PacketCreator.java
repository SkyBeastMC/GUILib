package fr.skybeastmc.guilib;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import fr.skybeastmc.guilib.debug.PrintPacketContent;
import fr.skybeastmc.guilib.guis.GUI;

public class PacketCreator {

	public static void sendCloseWindowPacket(Player player) {
		PacketContainer packet = new PacketContainer(
				PacketType.Play.Server.CLOSE_WINDOW);
		packet.getIntegers().write(0, 0);

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player,
					packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void sendOpenWindowPacket(Player player, int guiID,
			GUIInventory inventory, GUI gui) {
		PacketContainer packet = new PacketContainer(
				PacketType.Play.Server.OPEN_WINDOW);
		try {
			PrintPacketContent.printPacketContent(packet);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		packet.getIntegers().write(0, guiID); // Window ID
		packet.getStrings().write(0, inventory.getType().getName()); // Inventory
																		// ID
		packet.getChatComponents().write(0,
				WrappedChatComponent.fromText(inventory.getTitle())); // Window
																		// title
		packet.getIntegers().write(1, gui.getStorageSize()); // Size

		packet.getIntegers().write(2, 0); // EID

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player,
					packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void sendWindowItemsPacket(Player player, int guiID,
			ItemStack[] items) {
		PacketContainer packet = new PacketContainer(
				PacketType.Play.Server.WINDOW_ITEMS);
		packet.getIntegers().write(0, guiID); // Window ID
		packet.getItemArrayModifier().write(0, items); // Inventory items

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player,
					packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void sendSetItemPacket(Player player, int guiID, int slot,
			ItemStack item) {
		PacketContainer packet = new PacketContainer(
				PacketType.Play.Server.SET_SLOT);
		packet.getIntegers().write(0, guiID); // Window ID
		packet.getIntegers().write(1, slot); // Slot ID
		packet.getItemModifier().write(0, item); // Item

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player,
					packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void sendConfirmTransactionPacket(Player player, int guiID,
			short transactionID, boolean confirm) {
		PacketContainer packet = new PacketContainer(
				PacketType.Play.Server.TRANSACTION);
		packet.getIntegers().write(0, guiID); // Window ID
		packet.getShorts().write(0, transactionID); // Transaction ID
		packet.getBooleans().write(0, confirm); // Confirm

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player,
					packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void sendWindowPropertyPacket(Player player, int guiID,
			int property, int value) {
		PacketContainer packet = new PacketContainer(
				PacketType.Play.Server.WINDOW_DATA);
		packet.getIntegers().write(0, guiID); // Window ID
		packet.getIntegers().write(1, property); // Property
		packet.getIntegers().write(2, value); // Value

		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player,
					packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
