package fr.skybeastmc.guilib.guis;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import fr.skybeastmc.guilib.PacketCreator;

public class EnchantingTableGUIView extends GUIView {
	private int requiredLvlEnchant1;
	private int requiredLvlEnchant2;
	private int requiredLvlEnchant3;
	private int charGenerationSeed;
	private int enchant1;
	private int enchant2;
	private int enchant3;
	private int enchantLevel1;
	private int enchantLevel2;
	private int enchantLevel3;
	
	public EnchantingTableGUIView(EnchantingTableGUI gui, Player player, int guiID, boolean hidePlayerItems) {
		super(gui, player, guiID, hidePlayerItems);
	}

	public int getRequiredLvlEnchant1() {
		return requiredLvlEnchant1;
	}

	public void setRequiredLvlEnchant1(int requiredLvlEnchant1) {
		this.requiredLvlEnchant1 = requiredLvlEnchant1;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 0, requiredLvlEnchant1);
	}

	public int getRequiredLvlEnchant2() {
		return requiredLvlEnchant2;
	}

	public void setRequiredLvlEnchant2(int requiredLvlEnchant2) {
		this.requiredLvlEnchant2 = requiredLvlEnchant2;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 1, requiredLvlEnchant2);
	}

	public int getRequiredLvlEnchant3() {
		return requiredLvlEnchant3;
	}

	public void setRequiredLvlEnchant3(int requiredLvlEnchant3) {
		this.requiredLvlEnchant3 = requiredLvlEnchant3;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 2, requiredLvlEnchant3);
	}

	public int getCharGenerationSeed() {
		return charGenerationSeed;
	}

	public void setCharGenerationSeed(int charGenerationSeed) {
		this.charGenerationSeed = charGenerationSeed;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 3, charGenerationSeed);
	}

	@SuppressWarnings("deprecation")
	public Enchantment getEnchant1() {
		return Enchantment.getById(enchant1);
	}

	@SuppressWarnings("deprecation")
	public void setEnchant1(Enchantment enchant1) {
		this.enchant1 = enchant1.getId();
		PacketCreator.sendWindowPropertyPacket(player, guiID, 4, this.enchant1);
	}

	@SuppressWarnings("deprecation")
	public Enchantment getEnchant2() {
		return Enchantment.getById(enchant2);
	}

	@SuppressWarnings("deprecation")
	public void setEnchant2(Enchantment enchant2) {
		this.enchant2 = enchant2.getId();
		PacketCreator.sendWindowPropertyPacket(player, guiID, 5, this.enchant2);
	}

	@SuppressWarnings("deprecation")
	public Enchantment getEnchant3() {
		return Enchantment.getById(enchant3);
	}

	@SuppressWarnings("deprecation")
	public void setEnchant3(Enchantment enchant3) {
		this.enchant3 = enchant3.getId();
		PacketCreator.sendWindowPropertyPacket(player, guiID, 6, this.enchant3);
	}

	public int getEnchantLevel1() {
		return enchantLevel1;
	}

	public void setEnchantLevel1(int enchantLevel1) {
		this.enchantLevel1 = enchantLevel1;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 7, enchantLevel1);
	}

	public int getEnchantLevel2() {
		return enchantLevel2;
	}

	public void setEnchantLevel2(int enchantLevel2) {
		this.enchantLevel2 = enchantLevel2;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 8, enchantLevel2);
	}

	public int getEnchantLevel3() {
		return enchantLevel3;
	}

	public void setEnchantLevel3(int enchantLevel3) {
		this.enchantLevel3 = enchantLevel3;
		PacketCreator.sendWindowPropertyPacket(player, guiID, 9, enchantLevel3);
	}
}
