package fr.skybeastmc.guilib.listeners;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftReflection;

import fr.skybeastmc.guilib.GUIManager;
import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.InventoryUtils;
import fr.skybeastmc.guilib.Main;
import fr.skybeastmc.guilib.PacketCreator;
import fr.skybeastmc.guilib.events.AnvilGUITypeCompleteEvent;
import fr.skybeastmc.guilib.events.AnvilGUITypeEvent;
import fr.skybeastmc.guilib.events.EnchantingTableGUIEnchantEvent;
import fr.skybeastmc.guilib.events.GUIAction;
import fr.skybeastmc.guilib.events.GUIClick;
import fr.skybeastmc.guilib.events.GUIClickEvent;
import fr.skybeastmc.guilib.events.GUICloseEvent;
import fr.skybeastmc.guilib.events.GUIEvent;
import fr.skybeastmc.guilib.events.GUIOpenEvent;
import fr.skybeastmc.guilib.guis.AnvilGUI;
import fr.skybeastmc.guilib.guis.AnvilGUIView;
import fr.skybeastmc.guilib.guis.EnchantingTableGUI;
import fr.skybeastmc.guilib.guis.EnchantingTableGUIView;
import fr.skybeastmc.guilib.guis.GUIView;

public class ProtocolPacketListener extends PacketAdapter {

	public ProtocolPacketListener() {
		super(Main.getPlugin(), PacketType.Play.Client.CLOSE_WINDOW,
				PacketType.Play.Client.CUSTOM_PAYLOAD,
				PacketType.Play.Client.ENCHANT_ITEM,
				// PacketType.Play.Server.OPEN_WINDOW,
				// PacketType.Play.Server.WINDOW_ITEMS,
				// PacketType.Play.Server.WINDOW_DATA,
				PacketType.Play.Client.WINDOW_CLICK);
	}

	@SuppressWarnings("deprecation")
	public void onPacketReceiving(PacketEvent event) {
		GUIView view = GUIManager.getOpenGui(event.getPlayer());

		PacketContainer packet = event.getPacket();

		if (view != null) {
			if (event.getPacketType() == PacketType.Play.Client.WINDOW_CLICK) {

				event.setCancelled(true);
				int slot = packet.getIntegers().read(1);
				int button = packet.getIntegers().read(2);
				short actionNumber = packet.getShorts().read(0);
				ItemStack item = packet.getItemModifier().read(0);

				String shift;
				try {
					Class<?> clazz = Class.forName(MinecraftReflection
							.getMinecraftPackage() + ".InventoryClickType");
					// packet.getSpecificModifier(clazz).read(0);
					shift = String.valueOf(packet.getSpecificModifier(clazz)
							.read(0));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return;
				}

				GUIClick click = InventoryUtils.getClickType(slot, button,
						shift);
				
				GUIAction action = InventoryUtils.getAction(click, slot, view, event.getPlayer());
				
				GUIClickEvent ev = new GUIClickEvent(slot, click, item, view, action);

				boolean cancel = callEvent(ev);

				if (view.getType().equals(GUIType.ANVIL) && slot == 2)
					cancel = callEvent(new AnvilGUITypeCompleteEvent(click,
							item, ((AnvilGUIView) view).getInput(),
							(AnvilGUIView) view));
				
				

				PacketCreator.sendConfirmTransactionPacket(event.getPlayer(),
						view.getGuiID(), actionNumber, false);

				view.refresh();

			} else if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
				if (GUIManager.getOpenGui(event.getPlayer()) == null)
					return;

				event.setCancelled(true);
				InventoryUtils.refreshInventory(event.getPlayer());
				GUICloseEvent ev = new GUICloseEvent(
						GUIManager.getOpenGui(event.getPlayer()));
				GUIManager.removeOpenGui(event.getPlayer());
				callEvent(ev);
			} else if (event.getPacketType() == PacketType.Play.Client.CUSTOM_PAYLOAD) {

				try {
					String channel = packet.getStrings().read(0);
					Class<?> clazz = Class.forName(MinecraftReflection
							.getMinecraftPackage() + ".PacketDataSerializer");
					Object packetDataSerializer = packet.getSpecificModifier(
							clazz).read(0);

					switch (channel) {
					case "MC|ItemName":
						event.setCancelled(true);
						String input = String.valueOf(clazz.getDeclaredMethod(
								"e", int.class)
								.invoke(packetDataSerializer, 31));
						AnvilGUITypeEvent ev = new AnvilGUITypeEvent(input,
								(AnvilGUIView) view);
						((AnvilGUIView) view)._INVALID_setInput(input); // :p
						callEvent(ev);
					}

					// packet.getHandle().getClass().getDeclaredMethod("e",
					// int.class).invoke(o, 31);
				} catch (SecurityException | ClassNotFoundException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}

				// Debug.debug(packet.getSpecificModifier(PacketDataSerializer.class).read(0).e(31));

			} else if (event.getPacketType() == PacketType.Play.Client.ENCHANT_ITEM) {
				int slot = packet.getIntegers().read(1);
				GUIView open = GUIManager.getOpenGui(event.getPlayer());
				if (open == null
						|| open.getType().equals(GUIType.ENCHANTING_TABLE))
					return;

				callEvent(new EnchantingTableGUIEnchantEvent(slot,
						(EnchantingTableGUIView) view));
			}
		}
	}

	public void onPacketSending(PacketEvent event) {
	}

	public static boolean callEvent(GUIEvent event) {
		switch (event.getClass().getSimpleName()) {
		case "GUIClickEvent":
			event.getGui().onGUIClick((GUIClickEvent) event);
			break;
		case "GUIOpenEvent":
			event.getGui().onGUIOpen((GUIOpenEvent) event);
			break;
		case "GUICloseEvent":
			event.getGui().onGUIClose((GUICloseEvent) event);
			break;
		case "EnchantingTableGUIEnchantEvent":
			((EnchantingTableGUI) event.getGui())
					.onEnchant((EnchantingTableGUIEnchantEvent) event);
			break;
		case "AnvilGUITypeEvent":
			((AnvilGUI) event.getGui()).onType((AnvilGUITypeEvent) event);
			break;
		case "AnvilGUITypeCompleteEvent":
			((AnvilGUI) event.getGui())
					.onTypeComplete((AnvilGUITypeCompleteEvent) event);
			break;
		}

		Bukkit.getPluginManager().callEvent((Event) event);

		if (event instanceof Cancellable) {
			return ((Cancellable) event).isCancelled();
		}
		return false;
	}
}
