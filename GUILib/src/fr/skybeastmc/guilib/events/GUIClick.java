package fr.skybeastmc.guilib.events;

public enum GUIClick {
	LEFT, RIGHT, MIDDLE,

	SHIFT_LEFT, SHIFT_RIGHT,

	NUMBER_KEY_1(0), NUMBER_KEY_2(1), NUMBER_KEY_3(2), NUMBER_KEY_4(3), NUMBER_KEY_5(
			4), NUMBER_KEY_6(5), NUMBER_KEY_7(6), NUMBER_KEY_8(7), NUMBER_KEY_9(
			8),

	WINDOW_BORDER_LEFT, WINDOW_BORDER_RIGHT,

	DROP, CONTROL_DROP,

	START_LEFT_MOUSE_DRAG, LEFT_MOUSE_DRAG_ADD_SLOT, END_LEFT_MOUSE_DRAG, START_RIGHT_MOUSE_DRAG, RIGHT_MOUSE_DRAG_ADD_SLOT, END_RIGHT_MOUSE_DRAG,

	DOUBLE_CLICK,

	UNKNOWN;

	private int numberKey = -1;

	private GUIClick() {
	}

	private GUIClick(int numberKey) {
		this.numberKey = numberKey;
	}

	public int getNumberKey() {
		return numberKey;
	}
	
	public boolean isNumberKey() {
		return numberKey != -1;
	}

	public static GUIClick intToNumberKey(int i) {
		for (GUIClick click : GUIClick.values()) {
			if (click.numberKey == i) {
				return click;
			}
		}
		return UNKNOWN;
	}

	public static int numberKeyToInt(GUIClick click) {
		return click.numberKey;
	}

}
