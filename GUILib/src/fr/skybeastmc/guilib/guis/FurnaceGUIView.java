package fr.skybeastmc.guilib.guis;

import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.GUIType;
import fr.skybeastmc.guilib.PacketCreator;

public class FurnaceGUIView extends GUIView {
	private int fuelLeft;
	private int maxFuelLeft;
	private int progress;
	private int maxProgress;

	public FurnaceGUIView(FurnaceGUI gui, Player player, int guiID, boolean hidePlayerItems) {
		super(gui, player, guiID, hidePlayerItems);
		if(gui.getType() != GUIType.FURNACE)
			throw new RuntimeException("GUIType is not a furnace.");
	}

	public int getFuelLeft() {
		return fuelLeft;
	}

	public void setFuelLeft(int fuelLeft) {
		this.fuelLeft = fuelLeft;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 0, fuelLeft);
	}

	public int getMaxFuelLeft() {
		return maxFuelLeft;
	}

	public void setMaxFuelLeft(int maximumFuelLeft) {
		this.maxFuelLeft = maximumFuelLeft;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 1, maxFuelLeft);
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 2, progress);
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 3, maxProgress);
	}
}
