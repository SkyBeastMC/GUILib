package fr.skybeastmc.guilib.guis;

import fr.skybeastmc.guilib.GUIType;

public class ChestGUI extends GUI {
	private int rows;

	public ChestGUI(String title, int rows) {
		super(GUIType.CHEST, title);
		this.setRows(rows);
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSize() {
		return rows * 9;
	}

	public int getStorageSize() {
		return rows * 9;
	}

}
