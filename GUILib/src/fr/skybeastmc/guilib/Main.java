package fr.skybeastmc.guilib;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;

import fr.skybeastmc.guilib.debug.Debug;
import fr.skybeastmc.guilib.listeners.ProtocolPacketListener;

public class Main extends JavaPlugin { //
	private static JavaPlugin plugin;

	public void onEnable() { 

		try {
			plugin = this;
			boolean debug = true;
			Debug.setDebug(debug);
			Debug.info("Debug = " + debug + "!");
			if (debug)
				getServer().getPluginManager()
						.registerEvents(new Debug(), this);
			ProtocolLibrary.getProtocolManager().addPacketListener(
					new ProtocolPacketListener());

		} catch (Exception e) {
			Debug.error(e, "Enabling", true);
		}

	}

	public void onDisable() {}

	public static JavaPlugin getPlugin() {
		return plugin;
	}

	public static File getFolder() {
		return plugin.getDataFolder();
	}

	public static void setPlugin(JavaPlugin plugin) {
		Main.plugin = plugin;
	}
}
