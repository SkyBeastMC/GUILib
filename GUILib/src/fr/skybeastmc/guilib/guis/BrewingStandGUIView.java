package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.PacketCreator;

public class BrewingStandGUIView extends GUIView {
	private int progress;
	private int fuelLeft;

	public BrewingStandGUIView(AnvilGUI gui, Player player, int guiID, boolean hidePlayerItems) {
		super(gui, player, guiID, hidePlayerItems);
		if(gui.getType() != GUIType.FURNACE)
			throw new RuntimeException("GUIType is not a furnace.");
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 0, progress);
	}

	public int getFuelLeft() {
		return fuelLeft;
	}

	public void setFuelLeft(int fuelLeft) {
		this.fuelLeft = fuelLeft;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 1, fuelLeft);
	}
}
