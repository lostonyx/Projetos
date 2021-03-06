package com.criptonnetwork.wiljafor1.wbau.util;

import com.criptonnetwork.wiljafor1.wbau.util.InventoryIO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class BauManager {

	private static final String YAML_EXTENCAO = ".bau.yml";
	private static final int YAML_EXTENCAO_LENGTH = YAML_EXTENCAO.length();

	private final File dataFolder;
	private final Logger logger;
	//private final Map<UUID, Inventory> chests = new HashMap<>();
        private final Map<String, Inventory> chests = new HashMap<>();

	public BauManager(File dataFolder, Logger logger) {
		this.logger = logger;
		this.dataFolder = dataFolder;

		carregar();
	}

	private void carregar() {
		dataFolder.mkdirs();

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(YAML_EXTENCAO);
			}
		};

		for (File chestFile : dataFolder.listFiles(filter)) {
			String chestFileName = chestFile.getName();
			try {
				try {
					String playerUUID = chestFileName.substring(0, chestFileName.length() - YAML_EXTENCAO_LENGTH);
					chests.put(playerUUID, InventoryIO.loadFromYaml(chestFile));
				} catch (IllegalArgumentException e) {
					String playerName = chestFileName.substring(0, chestFileName.length() - YAML_EXTENCAO_LENGTH);
					boolean flagPlayerNotFound = true;

					//for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
					//	if (player.getName().equalsIgnoreCase(playerName)) {
							//flagPlayerNotFound = false;
							//chests.put(player.getUniqueId(), InventoryIO.loadFromYaml(chestFile));
							//chestFile.deleteOnExit();
					//	}
					//}

					if (flagPlayerNotFound) {
						logger.log(Level.WARNING, "Nao foi possivel carregar o arquivo: " + chestFileName);
					}
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Nao foi possivel carregar o arquivo: " + chestFileName);
			}
		}

		logger.info("Carregando os " + chests.size() + " baus!");
	}

	public int pegarChestSalvar() {
		int savedChests = 0;

		dataFolder.mkdirs();

		Iterator<Entry<String, Inventory>> chestIterator = chests.entrySet().iterator();

		while (chestIterator.hasNext()) {
			final Entry<String, Inventory> entry = chestIterator.next();
			final String playerUUID = entry.getKey();
			final Inventory chest = entry.getValue();

			final File chestFile = new File(dataFolder, playerUUID + YAML_EXTENCAO);

			if (chest == null) {
				chestFile.delete();
				chestIterator.remove();
			} else {
				try {
					InventoryIO.saveToYaml(chest, chestFile);

					savedChests++;
				} catch (IOException e) {
					logger.log(Level.WARNING, "Nao foi possivel salvar o arquivo: " + chestFile.getName(), e);
				}
			}
		}

		return savedChests;
	}

	public void salvarChest(String playerUUID) {
		dataFolder.mkdirs();

		final String uuidString = playerUUID;
		final Inventory chest = chests.get(playerUUID);
		final File chestFile = new File(dataFolder, uuidString + YAML_EXTENCAO);

		if (chest == null) {
			chestFile.delete();
		} else {
			try {
				InventoryIO.saveToYaml(chest, chestFile);
			} catch (IOException e) {
				logger.log(Level.WARNING, "Nao foi possivel salvar o arquivo: " + chestFile.getName(), e);
			}
		}
	}

	public Inventory pegarChest(String p, int linha) {
		Inventory chest = chests.get(p);

		if (chest == null) {
			chest = Bukkit.getServer().createInventory(null, linha * 9);
			chests.put(p, chest);
		}

		return chest;
	}

	public void delChest(String playerUUID) {
		chests.put(playerUUID, null);
	}

	public int pegarChestQuant() {
		return chests.size();
	}

}