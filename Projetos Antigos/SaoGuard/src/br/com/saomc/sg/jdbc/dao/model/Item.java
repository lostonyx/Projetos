package br.com.saomc.sg.jdbc.dao.model;

import java.util.List;

public class Item {

	Integer id;
//	Integer minecraftItemId;
	String type;
	Integer amount;
	Short durability;
	Boolean hasEnchantment;
	List<Enchantment> enchantments;
	Chest chest;


	public Item() {
		super();
		hasEnchantment = false;
	}


	public Item(Integer id) {
		this();
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAmount() {
		return amount;
	}


	public void setAmount(Integer amount) {
		this.amount = amount;
	}


	public Short getDurability() {
		return durability;
	}


	public void setDurability(Short durability) {
		this.durability = durability;
	}


	public List<Enchantment> getEnchantments() {
		return enchantments;
	}


	public void setEnchantments(List<Enchantment> enchantments) {
		this.enchantments = enchantments;
	}


	public Chest getChest() {
		return chest;
	}


	public void setChest(Chest chest) {
		this.chest = chest;
	}


	public Boolean getHasEnchantment() {
		return hasEnchantment;
	}


	public void setHasEnchantment(Boolean hasEnchantment) {
		this.hasEnchantment = hasEnchantment;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", type=" + type + ", amount=" + amount + ", durability=" + durability + ", hasEnchantment=" + hasEnchantment + ", enchantments=" + enchantments + ", chest=" + chest
				+ "]";
	}

}
