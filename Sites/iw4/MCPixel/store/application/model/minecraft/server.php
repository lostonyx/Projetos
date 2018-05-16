<?php

class ModelMinecraftServer extends Model {
    
    public function getServerByProduct($product_id) {
        $products = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "minecraft_product_to_server pts "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_server ms ON (ms.minecraft_server_id = pts.minecraft_server_id) "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_server_lang msl ON (msl.minecraft_server_id = ms.minecraft_server_id) "
                . "WHERE pts.product_id = '" . (int) $product_id . "'");

        return $products->rows;
    }
    
    public function getServerByCode($code) {
        $products = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "minecraft_server ms "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_server_lang msl ON (msl.minecraft_server_id = ms.minecraft_server_id) "
                . "WHERE msl.code = '" . $this->db->escape($code) . "' "
                . "LIMIT 1");
        
        return $products->row;
    }

}
