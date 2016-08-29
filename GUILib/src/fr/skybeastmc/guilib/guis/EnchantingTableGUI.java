package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.events.EnchantingTableGUIEnchantEvent;

public class EnchantingTableGUI extends GUI {

	public EnchantingTableGUI(String title) {
		super(GUIType.ENCHANTING_TABLE, title);
	}

	public void onEnchant(EnchantingTableGUIEnchantEvent event) {
	}

	public final EnchantingTableGUIView open(Player player) {
		return (EnchantingTableGUIView) super.open(player);
	}

	public final EnchantingTableGUIView open(Player player, boolean hidePlayerItem) {
		return (EnchantingTableGUIView) super.open(player, hidePlayerItem);
	}

}
