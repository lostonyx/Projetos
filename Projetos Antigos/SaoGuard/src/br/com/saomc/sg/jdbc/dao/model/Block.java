package br.com.saomc.sg.jdbc.dao.model;

public class Block {

	Integer id;
	Player blokedplayer_name;
        String full_name;
        Integer terreno_id;
    private String blocksAndLocations;


	public Block() {
		super();
	}

	public Player getBlokedPlayer() {
		return blokedplayer_name;
	}


	public void setBlokedPlayer(Player owner) {
		this.blokedplayer_name = owner;
	}
        
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBlocksAndLocations() {
		return blocksAndLocations;
	}


	public void setBlocksAndLocations(String blocksAndLocations) {
		this.blocksAndLocations = blocksAndLocations;
	}

}
