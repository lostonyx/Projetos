package me.wiljafor1.utils;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class UltraScore {

	private String type, title;
	private Player player;
	private List<String> types, values;

	public UltraScore(Player player, String title, String type) {
		this.player = player;
		this.title = title;

		this.types = new ArrayList<>();
		this.values = new ArrayList<>();

		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getTypes() {
		return types;
	}

	public List<String> getValues() {
		return types;
	}

	public void createLine(Object type, Object value) {
		types.add(String.valueOf(type));
		values.add(String.valueOf(value));
	}

	public void createLine(String text) {
		createLine(text.substring(0, text.length() / 2), text.substring(text.length() / 2));
	}

	public void update() {
		if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null
				|| !player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase(type)) {
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective(type, type);
			obj.setDisplayName(title);
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			int score = -1;
			for (int i = types.size() - 1; i >= 0; i--) {
				String s = types.get(i);
				obj.getScore(s).setScore(score++);
				board.registerNewTeam(s).addEntry(s);
			}
			player.setScoreboard(board);
		}
		Scoreboard board = player.getScoreboard();
		for (int i = types.size() - 1; i >= 0; i--) {
			String s = types.get(i);
			board.getTeam(s).setSuffix(values.get(i));
		}
	}
}
