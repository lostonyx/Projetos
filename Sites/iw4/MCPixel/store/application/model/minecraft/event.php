<?php

class ModelMinecraftEvent extends Model {

    public function getEvent($event) {
        $products = $this->db->query("SELECT * FROM " . DB_PREFIX . "minecraft_event WHERE event = '" . $this->db->escape($event) . "' LIMIT 1");

        return $products->row;
    }

    public function getProductEvent($product_id, $event_id) {
        $products = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "minecraft_product_to_event "
                . "WHERE product_id = '" . (int) $product_id . "' "
                . "AND minecraft_event_id = '" . (int) $event_id . "' "
                . "LIMIT 1");
        return $products->row;
    }

}
