<?php

class ModelLocalisationLanguage extends Model {
    public function addLanguage($data) {
        $this->event->trigger('pre.admin.language.add', $data);

        $this->db->query("INSERT INTO " . DB_PREFIX . "lang SET name = '" . $this->db->escape($data['name']) . "', code = '" . $this->db->escape($data['code']) . "', locale = '" . $this->db->escape($data['locale']) . "', directory = '" . $this->db->escape($data['directory']) . "', image = '" . $this->db->escape($data['image']) . "', sort_order = '" . $this->db->escape($data['sort_order']) . "', status = '" . (int)$data['status'] . "'");

        $this->cache->delete('language');

        $language_id = $this->db->getLastId();

        // Minecraft Event
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "minecraft_event_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $mev) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_event_lang SET minecraft_event_id = '" . (int)$mev['minecraft_event_id'] . "', name = '" . $this->db->escape($mev['name']) . "', language_id = '" . (int)$language_id . "'");
        }

        // Minecraft Server
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "minecraft_server_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $msv) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_server_lang SET minecraft_server_id = '" . (int)$msv['minecraft_server_id'] . "', name = '" . $this->db->escape($msv['name']) . "', language_id = '" . (int)$language_id . "', description = '" . $this->db->escape($msv['description']) . "'");
        }
		
        // Category
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "category_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $category) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "category_lang SET category_id = '" . (int)$category['category_id'] . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($category['name']) . "', description = '" . $this->db->escape($category['description']) . "'");
        }

        $this->cache->delete('category');

        // Customer Group
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "customer_group_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $customer_group) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "customer_group_lang SET customer_group_id = '" . (int)$customer_group['customer_group_id'] . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($customer_group['name']) . "', description = '" . $this->db->escape($customer_group['description']) . "'");
        }

        // Custom Field
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "custom_field_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $custom_field) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "custom_field_lang SET custom_field_id = '" . (int)$custom_field['custom_field_id'] . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($custom_field['name']) . "'");
        }

        // Custom Field Value
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "custom_field_value_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $custom_field_value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "custom_field_value_lang SET custom_field_value_id = '" . (int)$custom_field_value['custom_field_value_id'] . "', language_id = '" . (int)$language_id . "', custom_field_id = '" . (int)$custom_field_value['custom_field_id'] . "', name = '" . $this->db->escape($custom_field_value['name']) . "'");
        }

        // Order Status
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_status WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $order_status) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "order_status SET order_status_id = '" . (int)$order_status['order_status_id'] . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($order_status['name']) . "'");
        }

        $this->cache->delete('order_status');

        // Product
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $product) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "product_lang SET product_id = '" . (int)$product['product_id'] . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($product['name']) . "', short_description = '" . $this->db->escape($product['short_description']) . "', long_description = '" . $this->db->escape($product['long_description']) . "'");
        }

        $this->cache->delete('product');

        // Profiles
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "recurring_lang WHERE language_id = '" . (int)$this->config->get('config_language_id') . "'");

        foreach ($query->rows as $recurring) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "recurring_lang SET recurring_id = '" . (int)$recurring['recurring_id'] . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($recurring['name']) . "'");
        }

        $this->event->trigger('post.admin.language.add', $language_id);

        return $language_id;
    }

    public function editLanguage($language_id, $data) {
        $this->event->trigger('pre.admin.language.edit', $data);

        $this->db->query("UPDATE " . DB_PREFIX . "lang SET name = '" . $this->db->escape($data['name']) . "', code = '" . $this->db->escape($data['code']) . "', locale = '" . $this->db->escape($data['locale']) . "', directory = '" . $this->db->escape($data['directory']) . "', image = '" . $this->db->escape($data['image']) . "', sort_order = '" . $this->db->escape($data['sort_order']) . "', status = '" . (int)$data['status'] . "' WHERE language_id = '" . (int)$language_id . "'");

        $this->cache->delete('language');

        $this->event->trigger('post.admin.language.edit', $language_id);
    }

    public function deleteLanguage($language_id) {
        $this->event->trigger('pre.admin.language.delete', $language_id);

        $this->db->query("DELETE FROM " . DB_PREFIX . "lang WHERE language_id = '" . (int)$language_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_event_lang WHERE language_id = '" . (int)$language_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_server_lang WHERE language_id = '" . (int)$language_id . "'");

        $this->cache->delete('language');

        $this->db->query("DELETE FROM " . DB_PREFIX . "category_lang WHERE language_id = '" . (int)$language_id . "'");

        $this->cache->delete('category');

        $this->db->query("DELETE FROM " . DB_PREFIX . "customer_group_lang WHERE language_id = '" . (int)$language_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "custom_field_lang WHERE language_id = '" . (int)$language_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "custom_field_value_lang WHERE language_id = '" . (int)$language_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "order_status WHERE language_id = '" . (int)$language_id . "'");

        $this->cache->delete('order_status');

        $this->db->query("DELETE FROM " . DB_PREFIX . "product_lang WHERE language_id = '" . (int)$language_id . "'");

        $this->cache->delete('product');

        $this->db->query("DELETE FROM " . DB_PREFIX . "recurring_lang WHERE language_id = '" . (int)$language_id . "'");

        $this->event->trigger('post.admin.language.delete', $language_id);
    }

    public function getLanguage($language_id) {
        $query = $this->db->query("SELECT DISTINCT * FROM " . DB_PREFIX . "lang WHERE language_id = '" . (int)$language_id . "'");

        return $query->row;
    }

    public function getLanguages($data = array()) {
        if ($data) {
            $sql = "SELECT * FROM " . DB_PREFIX . "lang";

            $sort_data = array(
                'name',
                'code',
                'sort_order'
            );

            if (isset($data['sort']) && in_array($data['sort'], $sort_data)) {
                $sql .= " ORDER BY " . $data['sort'];
            } else {
                $sql .= " ORDER BY sort_order, name";
            }

            if (isset($data['order']) && ($data['order'] == 'DESC')) {
                $sql .= " DESC";
            } else {
                $sql .= " ASC";
            }

            if (isset($data['start']) || isset($data['limit'])) {
                if ($data['start'] < 0) {
                    $data['start'] = 0;
                }

                if ($data['limit'] < 1) {
                    $data['limit'] = 20;
                }

                $sql .= " LIMIT " . (int)$data['start'] . "," . (int)$data['limit'];
            }

            $query = $this->db->query($sql);

            return $query->rows;
        } else {
            $language_data = $this->cache->get('language');

            if (!$language_data) {
                $language_data = array();

                $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "lang ORDER BY sort_order, name");

                foreach ($query->rows as $result) {
                    $language_data[$result['code']] = array(
                        'language_id' => $result['language_id'],
                        'name'        => $result['name'],
                        'code'        => $result['code'],
                        'locale'      => $result['locale'],
                        'image'       => $result['image'],
                        'directory'   => $result['directory'],
                        'sort_order'  => $result['sort_order'],
                        'status'      => $result['status']
                    );
                }

                $this->cache->set('language', $language_data);
            }

            return $language_data;
        }
    }

    public function getTotalLanguages() {
        $query = $this->db->query("SELECT COUNT(*) AS total FROM " . DB_PREFIX . "lang");

        return $query->row['total'];
    }
}
