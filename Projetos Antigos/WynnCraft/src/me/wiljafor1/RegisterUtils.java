package me.wiljafor1;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;

public class RegisterUtils {

	public static List<Class<?>> getListeners(File file, String packagee) {

		return getPackages(file, packagee).stream()
				.filter(classe -> classe != null)
				.filter(classe -> Listener.class.isAssignableFrom(classe))
				.collect(Collectors.toList());
	}

	public static Set<Class<?>> getPackages(File file, String name) {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		try {
			JarFile jar = new JarFile(file);
			for (Enumeration<JarEntry> entry = jar.entries(); entry.hasMoreElements();) {
				JarEntry jarEntry = (JarEntry) entry.nextElement();
				String named = jarEntry.getName().replace("/", ".");
				if ((named.startsWith(name)) && (named.endsWith(".class")) && (!named.contains("$"))) {
					classes.add(Class.forName(named.substring(0, named.length() - 6)));
				}
			}
			jar.close();
		} catch (Exception localException) {
		}
		return classes;
	}

	public static void createCommand(Command... cmds) {
		try {
			if ((Bukkit.getServer() instanceof CraftServer)) {
				Field f = CraftServer.class.getDeclaredField("commandMap");
				f.setAccessible(true);

				CommandMap map = (CommandMap) f.get(Bukkit.getServer());
				Command[] arrayOfCommand = cmds;
				int j = cmds.length;
				for (int i = 0; i < j; i++) {
					Command cmd = arrayOfCommand[i];
					map.register("mchc", cmd);
				}
			}
		} catch (Exception localException) {
		}
	}

}
