<?php

class ModelMinecraftGroup extends Model {

    public function getGroups() {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "minecraft_product_group");

        return $query->rows;
    }

}
