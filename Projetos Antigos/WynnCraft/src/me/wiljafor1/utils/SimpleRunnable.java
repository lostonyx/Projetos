package me.wiljafor1.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.google.common.collect.Maps;

import me.wiljafor1.Main;

public class SimpleRunnable {

	public static HashMap<String, BukkitTask> RUNDATA = Maps.newHashMap();

	public static enum TaskType {
		ASYNC, SYNC
	}

	public static SimpleRunnable getInstance() {
		return new SimpleRunnable();
	}

	public static Plugin plugin = Main.GetInstance();
	
	public BukkitTask getTask(String name) {
		return RUNDATA.get(name);
	}
	
	public void cancel(String name) {
		if(getTask(name) != null) {
			getTask(name).cancel();
		}
	}
	
	public void cancelAll() {
		for( Entry<String, BukkitTask> set : RUNDATA.entrySet()) {
			String s = set.getKey();
			if(getTask(s) != null) {
				getTask(s).cancel();
			}
		}
	}
	
	public BukkitTask createTaskTimer(TaskType type, String name, Runnable r, long time, long delay) {
		BukkitTask t = null;
		if (type == TaskType.ASYNC) {
			t = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, r, time, delay);
		} else {
			t = Bukkit.getScheduler().runTaskTimer(plugin, r, time, delay);
		}
		RUNDATA.put(name, t);
		return t;
	}

	public BukkitTask createTaskLater(TaskType type, String name, Runnable r, long delay) {
		BukkitTask t = null;
		if (type == TaskType.ASYNC) {
			t = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, r, delay);
		} else {
			t = Bukkit.getScheduler().runTaskLater(plugin, r, delay);
		}
		RUNDATA.put(name, t);
		return t;
	}

}
