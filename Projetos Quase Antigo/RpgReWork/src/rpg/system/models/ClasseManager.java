package rpg.system.models;

import java.util.List;

import com.google.common.collect.Lists;

public class ClasseManager {

	public static List<Classe> classes = Lists.newArrayList();

	public static Classe getClasse(String name) {
		for (Classe c : classes) {
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

	public static void add(Classe c) {
		classes.add(c);
	}

	public static void remove(Classe c) {
		classes.remove(c);
	}

}
