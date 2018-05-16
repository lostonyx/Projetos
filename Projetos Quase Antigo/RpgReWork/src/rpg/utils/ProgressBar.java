package rpg.utils;

import org.bukkit.ChatColor;

public class ProgressBar {

	public static String getProgressBar(int valoratual, int valormax, int barras, String simbolo, String corCompleta,
			String corNaoCompleta) {

		float percent = (float) valoratual / valormax;

		int progressBars = (int) ((int) barras * percent);

		int leftOver = (barras - progressBars);

		StringBuilder sb = new StringBuilder();
		sb.append(ChatColor.translateAlternateColorCodes('&', corCompleta));
		for (int i = 0; i < progressBars; i++) {
			sb.append(simbolo);
		}
		sb.append(ChatColor.translateAlternateColorCodes('&', corCompleta));
		for (int i = 0; i < leftOver; i++) {
			sb.append(simbolo);
		}
		return sb.toString();
	}

}
