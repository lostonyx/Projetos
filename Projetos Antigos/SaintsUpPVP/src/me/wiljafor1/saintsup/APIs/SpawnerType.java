package me.wiljafor1.saintsup.APIs;

import org.bukkit.entity.EntityType;

public enum SpawnerType {

	// Blaze = Ouro, Creeper = Esmeralda, Enderman = Diamante, SKELETON = Lapis,
	// Spider = Carvao
	// Zombie = Lapis, PIGMAN = Redstone, Wither = Obsidian, Golem = Ferro
	// Guardian = Quartzo
	BLAZE("Blaze", EntityType.BLAZE), CREEPER("Creeper", EntityType.CREEPER), ENDERMAN("Enderman",
			EntityType.ENDERMAN), SKELETON("Skeleton", EntityType.SKELETON), SPIDER("Spider",
					EntityType.SPIDER), ZOMBIE("Zombie", EntityType.ZOMBIE), PIGMAN("PigZombie",
							EntityType.PIG_ZOMBIE), WITHER("Wither", EntityType.WITHER), IRONGOLEM("IronGolem",
									EntityType.IRON_GOLEM), GUARDIAN("Guardian", EntityType.GUARDIAN);

	private String displayName;
	private EntityType type;

	private SpawnerType(String displayName, EntityType type) {
		this.type = type;
		this.displayName = displayName;
	}

	public EntityType getType() {
		return this.type;
	}

	public String getDisplayName() {
		return this.displayName;
	}
}
