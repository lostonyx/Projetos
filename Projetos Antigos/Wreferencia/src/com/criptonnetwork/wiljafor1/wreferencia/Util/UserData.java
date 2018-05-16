package com.criptonnetwork.wiljafor1.wreferencia.Util;
import com.criptonnetwork.wiljafor1.wreferencia.Main;
import com.sun.org.apache.xerces.internal.xs.StringList;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserData {

	private static final String YAML_EXTENCAO = ".userdata.yml";
	private static final int YAML_EXTENCAO_LENGTH = YAML_EXTENCAO.length();

	private final File dataFolder;
	private final Logger logger;
        private final Main plugin;
	private final Map<UUID, List<String>> referencias = new HashMap<>();

	public UserData(File dataFolder, Logger logger, Main pl) {
		this.logger = logger;
                this.plugin = pl;
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
					UUID playerUUID = UUID
							.fromString(chestFileName.substring(0, chestFileName.length() - YAML_EXTENCAO_LENGTH));
					referencias.put(playerUUID, loadFromYaml(chestFile));
				} catch (IllegalArgumentException e) {
					String playerName = chestFileName.substring(0, chestFileName.length() - YAML_EXTENCAO_LENGTH);
					boolean flagPlayerNotFound = true;

					for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
						if (player.getName().equalsIgnoreCase(playerName)) {
							flagPlayerNotFound = false;
							referencias.put(player.getUniqueId(), loadFromYaml(chestFile));
							chestFile.deleteOnExit();
						}
					}

					if (flagPlayerNotFound) {
                                            plugin.getServer().getConsoleSender().sendMessage("§6<§8 Nao foi possivel carregar o arquivo: " + chestFileName);
						//logger.log(Level.WARNING, "§6<§8 Nao foi possivel carregar o arquivo: " + chestFileName);
					}
				}
			} catch (Exception e) {
                                plugin.getServer().getConsoleSender().sendMessage("§6<§8 Nao foi possivel carregar o arquivo: " + chestFileName);
				//logger.log(Level.WARNING, "§6<§8 Nao foi possivel carregar o arquivo: " + chestFileName);
			}
		}
                plugin.getServer().getConsoleSender().sendMessage("§6<§8 Carregando os " + referencias.size() + " players!");
		//logger.info("§6<§8 Carregando os " + referencias.size() + " players!");
	}

	public int pegarChestSalvar() {
		int savedChests = 0;

		dataFolder.mkdirs();

		Iterator<Entry<UUID, List<String>>> chestIterator = referencias.entrySet().iterator();

		while (chestIterator.hasNext()) {
			final Entry<UUID, List<String>> entry = chestIterator.next();
			final UUID playerUUID = entry.getKey();
			final List<String> lista = entry.getValue();

			final File chestFile = new File(dataFolder, playerUUID.toString() + YAML_EXTENCAO);

			if (lista == null) {
				chestFile.delete();
				chestIterator.remove();
			} else {
				try {
					saveToYaml(lista, chestFile);

					savedChests++;
				} catch (IOException e) {
                                    plugin.getServer().getConsoleSender().sendMessage("Nao foi possivel salvar o arquivo: " + chestFile.getName());
                                    plugin.getServer().getConsoleSender().sendMessage("Error: " + e);
					//logger.log(Level.WARNING, "Nao foi possivel salvar o arquivo: " + chestFile.getName(), e);
				}
			}
		}

		return savedChests;
	}

	public void salvarChest(UUID playerUUID) {
		dataFolder.mkdirs();

		final String uuidString = playerUUID.toString();
		final List<String> chest = referencias.get(playerUUID);
		final File chestFile = new File(dataFolder, uuidString + YAML_EXTENCAO);

		if (chest == null) {
			chestFile.delete();
		} else {
			try {
				saveToYaml(chest, chestFile);
			} catch (IOException e) {
                            plugin.getServer().getConsoleSender().sendMessage("Nao foi possivel salvar o arquivo: " + chestFile.getName());
                            plugin.getServer().getConsoleSender().sendMessage("Error: "+e);
                            //logger.log(Level.WARNING, "Nao foi possivel salvar o arquivo: " + chestFile.getName(), e);
			}
		}
	}

	public List<String> pegarChest(Player p) {
		List<String> chest = referencias.get(p.getUniqueId());

		if (chest == null) {
			chest = new ArrayList();
			referencias.put(p.getUniqueId(), chest);
		}

		return chest;
	}
        
	public List<String> pegarChestUUID(UUID uuid) {
		List<String> chest = referencias.get(uuid);

		if (chest == null) {
			chest = new ArrayList();
			referencias.put(uuid, chest);
		}

		return chest;
	}

	public void delChest(UUID playerUUID) {
		referencias.put(playerUUID, null);
	}

	public int pegarChestQuant() {
		return referencias.size();
	}
        
	public static List<String> loadFromYaml(File file) throws IOException, InvalidConfigurationException {
		YamlConfiguration yaml = new YamlConfiguration();
		yaml.load(file);
		List<String> listauser = yaml.getStringList("referencia");
		return listauser;
	}

	public static void saveToYaml(List<String> lu, File file) throws IOException {
		YamlConfiguration yaml = new YamlConfiguration();
		List<String> listauser = lu;
		yaml.set("referencia", listauser);
		yaml.save(file);
	}
}