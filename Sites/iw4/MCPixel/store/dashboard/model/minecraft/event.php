<?php

class ModelMinecraftEvent extends Model {

    public function getEvent($event) {
        $event = $this->db->query("
        SELECT * FROM " . DB_PREFIX . "minecraft_event me
        LEFT JOIN " . DB_PREFIX . "minecraft_event_lang mel ON (me.minecraft_event_id = mel.minecraft_event_id)
        WHERE mel.language_id = '" . (int)$this->config->get('config_language_id') . "' AND event = '" . $this->db->escape($event) . "'
        LIMIT 1");

        return $event->row;
    }

    public function getEventByID($event_id) {
        $event = $this->db->query("
        SELECT * FROM " . DB_PREFIX . "minecraft_event me
        LEFT JOIN " . DB_PREFIX . "minecraft_event_lang mel ON (me.minecraft_event_id = mel.minecraft_event_id)
        WHERE mel.language_id = '" . (int)$this->config->get('config_language_id') . "' AND me.minecraft_event_id = '" . (int)$event_id . "'
        LIMIT 1");

        return $event->row;
    }

    public function getEvents() {
        $events = $this->db->query("
        SELECT * FROM " . DB_PREFIX . "minecraft_event me
        LEFT JOIN " . DB_PREFIX . "minecraft_event_lang mel ON (me.minecraft_event_id = mel.minecraft_event_id)
        WHERE mel.language_id = '" . (int)$this->config->get('config_language_id') . "' ORDER BY me.sort_order ASC");

        return $events->rows;
    }

    public function getProductEvent($product_id, $event_id) {
        $products = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_product_to_event "
            . "WHERE product_id = '" . (int)$product_id . "' "
            . "AND minecraft_event_id = '" . (int)$event_id . "' "
            . "LIMIT 1");
        return $products->row;
    }

}
