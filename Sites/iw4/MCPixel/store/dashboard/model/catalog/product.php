<?php

class ModelCatalogProduct extends Model {
    public function addProduct($data) {
        $this->event->trigger('pre.admin.product.add', $data);

        $this->db->query(""
            . "INSERT INTO " . DB_PREFIX . "product "
            . "SET lifetime = '" . (int)$data['lifetime'] . "', "
            . "duration = '" . (($data['lifetime'] != true) ? (int)0 : (int)abs($data['duration'])) . "', "
            . "date_available = '" . $this->db->escape($data['date_available']) . "', "
            . "status = '" . (int)$data['status'] . "', "
            . "sort_order = '" . (int)$data['sort_order'] . "', "
            . "date_updated = NOW()");

        $product_id = $this->db->getLastId();

        if (isset($data['image'])) {
            $this->db->query("UPDATE " . DB_PREFIX . "product SET image = '" . $this->db->escape($data['image']) . "' WHERE product_id = '" . (int)$product_id . "'");
        }

        foreach ($data['product_description'] as $language_id => $value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "product_lang SET product_id = '" . (int)$product_id . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($value['name']) . "', short_description = '" . $this->db->escape($value['short_description']) . "', long_description = '" . $this->db->escape($value['long_description']) . "'");
        }

        if (isset($data['product_price'])) {
            $pd_price = 1;
            foreach ($data['product_price'] as $currency_id => $product_price) {
                if ($currency_id == $this->currency->getId($this->config->get("config_currency"))) {
                    $pd_price = $product_price['price'];
                    break;
                }
            }
            foreach ($data['product_price'] as $currency_id => $product_price) {

                $convert = 0;

                if (($currency_id != $this->currency->getId($this->config->get("config_currency"))) && $product_price['convert'] == '1') {
                    $convert = 1;
                    $price = $pd_price;
                } else {
                    $price = $product_price['price'];
                }

                $this->db->query("
                INSERT INTO " . DB_PREFIX . "product_price SET
                product_id  = '" . (int)$product_id . "',
                currency_id = '" . (int)$currency_id. "',
                price = '" . $this->db->escape($price) . "',
                " . DB_PREFIX . "product_price.convert = '" . (int)$convert . "'");
            }
        }

        if (isset($data['product_discount'])) {
            foreach ($data['product_discount'] as $product_discount) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_discount SET product_id = '" . (int)$product_id . "', customer_group_id = '" . (int)$product_discount['customer_group_id'] . "', quantity = '" . (int)$product_discount['quantity'] . "', priority = '" . (int)$product_discount['priority'] . "', price = '" . (float)$product_discount['price'] . "', date_start = '" . $this->db->escape($product_discount['date_start']) . "', date_end = '" . $this->db->escape($product_discount['date_end']) . "'");
            }
        }

        if (isset($data['product_special'])) {
            foreach ($data['product_special'] as $product_special) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_special SET product_id = '" . (int)$product_id . "', customer_group_id = '" . (int)$product_special['customer_group_id'] . "', priority = '" . (int)$product_special['priority'] . "', price = '" . (float)$product_special['price'] . "', date_start = '" . $this->db->escape($product_special['date_start']) . "', date_end = '" . $this->db->escape($product_special['date_end']) . "'");
            }
        }

        if (isset($data['product_image'])) {
            foreach ($data['product_image'] as $product_image) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_image SET product_id = '" . (int)$product_id . "', image = '" . $this->db->escape($product_image['image']) . "', sort_order = '" . (int)$product_image['sort_order'] . "'");
            }
        }

        if (isset($data['product_category'])) {
            foreach ($data['product_category'] as $category_id) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_to_category SET product_id = '" . (int)$product_id . "', category_id = '" . (int)$category_id . "'");
            }
        }

        if (isset($data['product_server'])) {
            foreach ($data['product_server'] as $server_id) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_product_to_server SET product_id = '" . (int)$product_id . "', minecraft_server_id = '" . (int)$server_id . "'");
            }
        }

        if (isset($data['minecraft_product_group_id'])) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_product_to_group SET product_id = '" . (int)$product_id . "', minecraft_product_group_id = '" . (int)$data['minecraft_product_group_id'] . "'");
        }

        if (isset($data['product_recurrings'])) {
            foreach ($data['product_recurrings'] as $recurring) {
                $this->db->query("INSERT INTO `" . DB_PREFIX . "product_recurring` SET `product_id` = " . (int)$product_id . ", customer_group_id = " . (int)$recurring['customer_group_id'] . ", `recurring_id` = " . (int)$recurring['recurring_id']);
            }
        }

        if (isset($data['product_command']) && !empty($data['product_command'])) {
            $this->load->model("minecraft/event");

            $events = group_assoc($data['product_command'], 'event_id');

            foreach ($events as $key => $value) {
                $e = $this->model_minecraft_event->getEventByID($key);

                if (!empty($e)) {
                    $commands = array();
                    foreach ($value as $command) {
                        if (!empty($command['value'])) {
                            $commands[] = $command['value'];
                        }
                    }

                    $commands = json_encode($commands);
                    $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_product_to_event SET product_id = '" . (int)$product_id . "', minecraft_event_id = '" . (int)$key . "', commands = '" . $this->db->escape($commands) . "'");
                }
            }

        }

        $this->cache->delete('product');

        $this->event->trigger('post.admin.product.add', $product_id);

        return $product_id;
    }

    public function editProduct($product_id, $data) {
        $this->event->trigger('pre.admin.product.edit', $data);

        $this->db->query(""
            . "UPDATE " . DB_PREFIX . "product "
            . "SET lifetime = '" . (int)$data['lifetime'] . "', "
            . "duration = '" . (($data['lifetime'] != true) ? (int)abs($data['duration']) : 0) . "', "
            . "date_available = '" . $this->db->escape($data['date_available']) . "', "
            . "status = '" . (int)$data['status'] . "', "
            . "sort_order = '" . (int)$data['sort_order'] . "', "
            . "date_updated = NOW() "
            . "WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['image'])) {
            $this->db->query("UPDATE " . DB_PREFIX . "product SET image = '" . $this->db->escape($data['image']) . "' WHERE product_id = '" . (int)$product_id . "'");
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "product_lang WHERE product_id = '" . (int)$product_id . "'");

        foreach ($data['product_description'] as $language_id => $value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "product_lang SET product_id = '" . (int)$product_id . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($value['name']) . "', short_description = '" . $this->db->escape($value['short_description']) . "', long_description = '" . $this->db->escape($value['long_description']) . "'");
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "product_price WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['product_price'])) {
            $pd_price = 1;
            foreach ($data['product_price'] as $currency_id => $product_price) {
                if ($currency_id == $this->currency->getId($this->config->get("config_currency"))) {
                    $pd_price = $product_price['price'];
                    break;
                }
            }
            foreach ($data['product_price'] as $currency_id => $product_price) {

                $convert = 0;

                if (($currency_id != $this->currency->getId($this->config->get("config_currency"))) && $product_price['convert'] == '1') {
                    $convert = 1;
                    $price = $pd_price;
                } else {
                    $price = $product_price['price'];
                }

                $this->db->query("
                INSERT INTO " . DB_PREFIX . "product_price SET
                product_id  = '" . (int)$product_id . "',
                currency_id = '" . (int)$currency_id. "',
                price = '" . $this->db->escape($price) . "',
                " . DB_PREFIX . "product_price.convert = '" . (int)$convert . "'");
            }
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "product_discount WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['product_discount'])) {
            foreach ($data['product_discount'] as $product_discount) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_discount SET product_id = '" . (int)$product_id . "', customer_group_id = '" . (int)$product_discount['customer_group_id'] . "', quantity = '" . (int)$product_discount['quantity'] . "', priority = '" . (int)$product_discount['priority'] . "', price = '" . (float)$product_discount['price'] . "', date_start = '" . $this->db->escape($product_discount['date_start']) . "', date_end = '" . $this->db->escape($product_discount['date_end']) . "'");
            }
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "product_special WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['product_special'])) {
            foreach ($data['product_special'] as $product_special) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_special SET product_id = '" . (int)$product_id . "', customer_group_id = '" . (int)$product_special['customer_group_id'] . "', priority = '" . (int)$product_special['priority'] . "', price = '" . (float)$product_special['price'] . "', date_start = '" . $this->db->escape($product_special['date_start']) . "', date_end = '" . $this->db->escape($product_special['date_end']) . "'");
            }
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "product_to_category WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['product_category'])) {
            foreach ($data['product_category'] as $category_id) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "product_to_category SET product_id = '" . (int)$product_id . "', category_id = '" . (int)$category_id . "'");
            }
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_server WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['product_server'])) {
            foreach ($data['product_server'] as $server_id) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_product_to_server SET product_id = '" . (int)$product_id . "', minecraft_server_id = '" . (int)$server_id . "'");
            }
        }

        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_group WHERE product_id = '" . (int)$product_id . "'");

        if (isset($data['minecraft_product_group_id'])) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_product_to_group SET product_id = '" . (int)$product_id . "', minecraft_product_group_id = '" . (int)$data['minecraft_product_group_id'] . "'");
        }

        $this->db->query("DELETE FROM `" . DB_PREFIX . "product_recurring` WHERE product_id = " . (int)$product_id);

        if (isset($data['product_recurring'])) {
            foreach ($data['product_recurring'] as $product_recurring) {
                $this->db->query("INSERT INTO `" . DB_PREFIX . "product_recurring` SET `product_id` = " . (int)$product_id . ", customer_group_id = " . (int)$product_recurring['customer_group_id'] . ", `recurring_id` = " . (int)$product_recurring['recurring_id']);
            }
        }

        if (isset($data['product_command']) && !empty($data['product_command'])) {
            $this->load->model("minecraft/event");

            $events = group_assoc($data['product_command'], 'event_id');

            foreach ($events as $key => $value) {
                $e = $this->model_minecraft_event->getEventByID($key);

                if (!empty($e)) {
                    $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_event WHERE product_id = '" . (int)$product_id . "' AND minecraft_event_id = '" . (int)$key . "' LIMIT 1");

                    $commands = array();
                    foreach ($value as $command) {
                        if (!empty($command['value'])) {
                            $commands[] = $command['value'];
                        }
                    }

                    $commands = json_encode($commands);
                    $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_product_to_event SET product_id = '" . (int)$product_id . "', minecraft_event_id = '" . (int)$key . "', commands = '" . $this->db->escape($commands) . "'");
                }
            }

        }

        $this->cache->delete('product');

        $this->event->trigger('post.admin.product.edit', $product_id);
    }

    public function copyProduct($product_id) {
        $query = $this->db->query("
        SELECT DISTINCT * FROM " . DB_PREFIX . "product p
        LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id)
        LEFT JOIN " . DB_PREFIX . "product_price pdc ON (p.product_id = pdc.product_id)
		LEFT JOIN " . DB_PREFIX . "minecraft_product_to_group mptg ON (p.product_id = mptg.product_id)
        WHERE p.product_id = '" . (int)$product_id . "' AND pd.language_id = '" . (int)$this->config->get('config_language_id') . "'");

        if ($query->num_rows) {
            $data = $query->row;

            $data['status'] = '0';

            $data['product_description'] = $this->getProductDescriptions($product_id);
            $data['product_category'] = $this->getProductCategories($product_id);
            $data['product_server'] = $this->getProductServers($product_id);
            $data['product_discount'] = $this->getProductDiscounts($product_id);
            $data['product_special'] = $this->getProductSpecials($product_id);
            $data['product_category'] = $this->getProductCategories($product_id);
            $data['product_recurrings'] = $this->getRecurrings($product_id);
            $data['product_command'] = $this->getProductCommands($product_id);

            $data['product_price'] = array();
            $price = $this->getProductPrices($product_id);
            foreach ($price as $p) {
                $data['product_price'][$p['currency_id']] = array(
                    "price" => $p['price'],
                    "convert" => $p['convert']
                );
            }

            $this->addProduct($data);
        }
    }

    public function deleteProduct($product_id) {
        $this->event->trigger('pre.admin.product.delete', $product_id);

        $this->db->query("DELETE FROM " . DB_PREFIX . "product WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_lang WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_price WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_discount WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_special WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_to_category WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_event WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_server WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_group WHERE product_id = '" . (int)$product_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_recurring WHERE product_id = " . (int)$product_id);

        $this->cache->delete('product');

        $this->event->trigger('post.admin.product.delete', $product_id);
    }

    public function getProduct($product_id) {
        $query = $this->db->query("
		SELECT DISTINCT *
	  	FROM " . DB_PREFIX . "product p
	  	LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id)
	  	LEFT JOIN " . DB_PREFIX . "product_price pdc ON (p.product_id = pdc.product_id)
		LEFT JOIN " . DB_PREFIX . "minecraft_product_to_group mptg ON (p.product_id = mptg.product_id)
	  	WHERE p.product_id = '" . (int)$product_id . "' AND pd.language_id = '" . (int)$this->config->get('config_language_id') . "'");

        return $query->row;
    }

    public function getProducts($data = array()) {
        $sql = "
		SELECT * FROM " . DB_PREFIX . "product p
		LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id)
		LEFT JOIN " . DB_PREFIX . "product_price pdc ON (p.product_id = pdc.product_id)
		LEFT JOIN " . DB_PREFIX . "minecraft_product_to_group mptg ON (p.product_id = mptg.product_id)
		WHERE pd.language_id = '" . (int)$this->config->get('config_language_id') . "'";

        if (!empty($data['filter_name'])) {
            $sql .= " AND pd.name LIKE '" . $this->db->escape($data['filter_name']) . "%'";
        }

        if (isset($data['filter_price']) && !is_null($data['filter_price'])) {
            $sql .= " AND pdc.price LIKE '" . $this->db->escape($data['filter_price']) . "%'";
        }

        if (isset($data['filter_status']) && !is_null($data['filter_status'])) {
            $sql .= " AND p.status = '" . (int)$data['filter_status'] . "'";
        }

        $sql .= " GROUP BY p.product_id";

        $sort_data = array(
            'pd.name',
            'pdc.price',
            'p.status',
            'p.sort_order'
        );

        if (isset($data['sort']) && in_array($data['sort'], $sort_data)) {
            $sql .= " ORDER BY " . $data['sort'];
        } else {
            $sql .= " ORDER BY pd.name";
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
    }

    public function getProductsByCategoryId($category_id) {
        $query = $this->db->query("
		SELECT * FROM " . DB_PREFIX . "product p
		LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id)
		LEFT JOIN " . DB_PREFIX . "product_price pdc ON (p.product_id = pdc.product_id)
		LEFT JOIN " . DB_PREFIX . "minecraft_product_to_group mptg ON (p.product_id = mptg.product_id)
		LEFT JOIN " . DB_PREFIX . "product_to_category p2c ON (p.product_id = p2c.product_id)
		WHERE pd.language_id = '" . (int)$this->config->get('config_language_id') . "' AND p2c.category_id = '" . (int)$category_id . "' ORDER BY pd.name ASC");

        return $query->rows;
    }

    public function getProductDescriptions($product_id) {
        $product_lang_data = array();

        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_lang WHERE product_id = '" . (int)$product_id . "'");

        foreach ($query->rows as $result) {
            $product_lang_data[$result['language_id']] = array(
                'name'              => $result['name'],
                'short_description' => $result['short_description'],
                'long_description'  => $result['long_description']
            );
        }

        return $product_lang_data;
    }

    public function getProductPrices($product_id) {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_price WHERE product_id = '" . (int)$product_id . "'");

        return $query->rows;
    }

    public function getProductCategories($product_id) {
        $product_category_data = array();

        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_to_category WHERE product_id = '" . (int)$product_id . "'");

        foreach ($query->rows as $result) {
            $product_category_data[] = $result['category_id'];
        }

        return $product_category_data;
    }

    public function getProductServers($product_id) {
        $product_server_data = array();

        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "minecraft_product_to_server WHERE product_id = '" . (int)$product_id . "'");

        foreach ($query->rows as $result) {
            $product_server_data[] = $result['minecraft_server_id'];
        }

        return $product_server_data;
    }

    public function getProductDiscounts($product_id) {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_discount WHERE product_id = '" . (int)$product_id . "' ORDER BY quantity, priority, price");

        return $query->rows;
    }

    public function getProductSpecials($product_id) {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_special WHERE product_id = '" . (int)$product_id . "' ORDER BY priority, price");

        return $query->rows;
    }

    public function getRecurrings($product_id) {
        $query = $this->db->query("SELECT * FROM `" . DB_PREFIX . "product_recurring` WHERE product_id = '" . (int)$product_id . "'");

        return $query->rows;
    }

    public function getProductCommands($product_id) {
        $product_server_data = array();
        $this->load->model("minecraft/event");

        $events = $this->model_minecraft_event->getEvents();

        foreach ($events as $event) {
            $pte = $this->model_minecraft_event->getProductEvent($product_id, $event['minecraft_event_id']);

            if (!empty($pte) && !empty($pte['commands'])) {
                $cmds = json_decode($pte['commands'], true);

                foreach ($cmds as $cmd) {
                    $product_server_data[] = array(
                        "event_id" => $event['minecraft_event_id'],
                        "value"    => $cmd
                    );
                }
            }
        }

        return $product_server_data;
    }

    public function getTotalProducts($data = array()) {
        $sql = "
		SELECT COUNT(DISTINCT p.product_id) AS total FROM " . DB_PREFIX . "product p
		LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id)
		LEFT JOIN " . DB_PREFIX . "product_price pdc ON (p.product_id = pdc.product_id) ";

        $sql .= " WHERE pd.language_id = '" . (int)$this->config->get('config_language_id') . "'";

        if (!empty($data['filter_name'])) {
            $sql .= " AND pd.name LIKE '" . $this->db->escape($data['filter_name']) . "%'";
        }

        if (isset($data['filter_price']) && !is_null($data['filter_price'])) {
            $sql .= " AND pdc.price LIKE '" . $this->db->escape($data['filter_price']) . "%'";
        }

        if (isset($data['filter_status']) && !is_null($data['filter_status'])) {
            $sql .= " AND p.status = '" . (int)$data['filter_status'] . "'";
        }

        $query = $this->db->query($sql);

        return $query->row['total'];
    }

    public function getTotalProductsByProfileId($recurring_id) {
        $query = $this->db->query("SELECT COUNT(*) AS total FROM " . DB_PREFIX . "product_recurring WHERE recurring_id = '" . (int)$recurring_id . "'");

        return $query->row['total'];
    }

}
