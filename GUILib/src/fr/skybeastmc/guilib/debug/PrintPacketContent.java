package fr.skybeastmc.guilib.debug;

import java.lang.reflect.Field;

import com.comphenix.protocol.events.PacketContainer;

public class PrintPacketContent {

	public static void printPacketContent(PacketContainer packet)
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		Object handle = packet.getHandle();
		Debug.debug("Packet " + handle.getClass().getSimpleName() + ":");
		Class<?> clazz = handle.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			printField(field, handle);
		}

	}

	public static void printField(Field field, Object instance)
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		Object o = field.get(instance);
		Debug.debug(field.getType().getName() + " " + field.getName() + " = "
				+ String.valueOf(o));
	}
}
