package fr.skybeastmc.guilib;

public enum GUIType {
	
	CHEST("minecraft:chest", -1, -1),
	CRAFTING("minecraft:crafting_table", 0, 10),
	FURNACE("minecraft:furnace", 3, 3),
	DISPENSER("minecraft:dispenser", 9, 9),
	ENCHANTING_TABLE("minecraft:enchanting_table", 0, 2),
	BREWING_STAND("minecraft:brewing_stand", 4, 4), //4 since 1.9
	//VILLAGER("minecraft:villager", 0, 3), //WIP
	//BEACON("minecraft:beacon", 0, 1), //WIP
	ANVIL("minecraft:anvil", 0, 3),
	HOPPER("minecraft:hopper", 5, 5),
	DROPPER("minecraft:crafting_table", 9, 9);
	//SIGN("sign", 0, 0), //It's not a GUI, but why not adding it.
	//BOOK("book", 0, 0), //Same
	//HORSE("EntityHorse", 2, 2),
	//DONKEY("EntityHorse", 17, 17); //5*3 + 2 (There still is an armor slot, even though it cannot be used and is invisible.)

	private String name;
	private int storageSize;
	private int size;
	
	private GUIType(String name, int storageSize, int size) {
		this.name = name;
		this.storageSize = storageSize;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	@Deprecated
	public int getStorageSize() {
		return storageSize;
	}

	@Deprecated
	public int getSize() {
		return size;
	}
}
