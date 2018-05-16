<?php

class ModelCatalogProduct extends Model {

    public function updateViewed($product_id) {
        $this->db->query("UPDATE " . DB_PREFIX . "product SET viewed = (viewed + 1) WHERE product_id = '" . (int) $product_id . "'");
    }

    public function getProduct($product_id) {
        $time = date("Y-m-d-H-i", time());

        $query = $this->db->query(""
                . "SELECT DISTINCT *, pd.name AS name, "
                . "(SELECT price FROM " . DB_PREFIX . "product_discount pd2 "
                . "WHERE pd2.product_id = p.product_id AND pd2.customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' "
                . "AND pd2.quantity = '1' AND ((pd2.date_start = '0000-00-00' OR pd2.date_start < '" . $time . "') "
                . "AND (pd2.date_end = '0000-00-00' OR pd2.date_end > '" . $time . "')) "
                . "ORDER BY pd2.priority ASC, pd2.price ASC LIMIT 1) AS discount, "
                . "(SELECT price FROM " . DB_PREFIX . "product_special ps WHERE ps.product_id = p.product_id "
                . "AND ps.customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' "
                . "AND ((ps.date_start = '0000-00-00' OR ps.date_start < '" . $time . "') AND (ps.date_end = '0000-00-00' OR ps.date_end > '" . $time . "')) "
                . "ORDER BY ps.priority ASC, ps.price ASC LIMIT 1) AS special "
                . "FROM " . DB_PREFIX . "product p "
                . "LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id) "
                . "LEFT JOIN " . DB_PREFIX . "product_price pc ON (p.product_id = pc.product_id) "
                . "WHERE p.product_id = '" . (int) $product_id . "' AND pd.language_id = '" . (int) $this->config->get('config_language_id') . "' "
                . "AND pc.currency_id = " . (int) $this->currency->getId() . " "
                . "AND p.status = '2' AND p.date_available <= '" . $time . "'");

        if ($query->num_rows) {
            return array(
                'product_id' => $query->row['product_id'],
                'name' => $query->row['name'],
                'short_description' => $query->row['short_description'],
                'long_description' => $query->row['long_description'],
                'image' => $query->row['image'],
                'price' => ($query->row['discount'] ? $query->row['discount'] : $query->row['price']),
                'convert' => $query->row['convert'],
                'special' => $query->row['special'],
                'sort_order' => $query->row['sort_order'],
                'status' => $query->row['status'],
                'duration' => $query->row['duration'],
                'lifetime' => $query->row['lifetime'],
                'date_added' => $query->row['date_added'],
                'date_updated' => $query->row['date_updated'],
                'viewed' => $query->row['viewed']
            );
        } else {
            return false;
        }
    }

    public function getProducts($data = array()) {

        $time = date("Y-m-d-H-i", time());
        $sql = "SELECT p.product_id, "
                . "(SELECT price FROM " . DB_PREFIX . "product_discount pd2 "
                . "WHERE pd2.product_id = p.product_id AND pd2.customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' "
                . "AND pd2.quantity = '1' AND ((pd2.date_start = '0000-00-00' OR pd2.date_start < '" . $time . "') "
                . "AND (pd2.date_end = '0000-00-00' OR pd2.date_end > '" . $time . "')) ORDER BY pd2.priority ASC, pd2.price ASC LIMIT 1) AS discount, "
                . "(SELECT price FROM " . DB_PREFIX . "product_special ps WHERE ps.product_id = p.product_id "
                . "AND ps.customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' "
                . "AND ((ps.date_start = '0000-00-00' OR ps.date_start < '" . $time . "') AND (ps.date_end = '0000-00-00' OR ps.date_end > '" . $time . "')) "
                . "ORDER BY ps.priority ASC, ps.price ASC LIMIT 1) AS special";

        $sql .= " FROM " . DB_PREFIX . "product p";

        $sql .= " LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id) ";

        if (!empty($data['filter_category_id'])) {
            $sql .= " LEFT JOIN " . DB_PREFIX . "product_to_category p2c ON (p2c.product_id = p.product_id)";
        }

        $sql .= " WHERE pd.language_id = '" . (int) $this->config->get('config_language_id') . "' "
                . "AND p.status = '2' AND p.date_available <= '" . $time . "' ";

        if (!empty($data['filter_category_id'])) {
            $sql .= " AND p2c.category_id = " . (int) $data['filter_category_id'];
        }

        if (!empty($data['filter_name']) || !empty($data['filter_tag'])) {
            $sql .= " AND (";

            if (!empty($data['filter_name'])) {
                $implode = array();

                $words = explode(' ', trim(preg_replace('/\s+/', ' ', $data['filter_name'])));

                foreach ($words as $word) {
                    $implode[] = "pd.name LIKE '%" . $this->db->escape($word) . "%'";
                }

                if ($implode) {
                    $sql .= " " . implode(" AND ", $implode) . "";
                }

                if (!empty($data['filter_lang'])) {
                    $sql .= " OR pd.long_description LIKE '%" . $this->db->escape($data['filter_name']) . "%'";
                }
            }

            $sql .= ")";
        }

        $sql .= " GROUP BY p.product_id";

        $sort_data = array(
            'pd.name',
            'p.price',
            'rating',
            'p.sort_order',
            'p.date_added'
        );

        if (isset($data['sort']) && in_array($data['sort'], $sort_data)) {
            if ($data['sort'] == 'pd.name' || $data['sort'] == 'p.model') {
                $sql .= " ORDER BY LCASE(" . $data['sort'] . ")";
            } elseif ($data['sort'] == 'p.price') {
                $sql .= " ORDER BY (CASE WHEN special IS NOT NULL THEN special WHEN discount IS NOT NULL THEN discount ELSE p.price END)";
            } else {
                $sql .= " ORDER BY " . $data['sort'];
            }
        } else {
            $sql .= " ORDER BY p.sort_order";
        }

        if (isset($data['order']) && ($data['order'] == 'DESC')) {
            $sql .= " DESC, LCASE(pd.name) DESC";
        } else {
            $sql .= " ASC, LCASE(pd.name) ASC";
        }

        if (isset($data['start']) || isset($data['limit'])) {
            if ($data['start'] < 0) {
                $data['start'] = 0;
            }

            if ($data['limit'] < 1) {
                $data['limit'] = 20;
            }

            $sql .= " LIMIT " . (int) $data['start'] . "," . (int) $data['limit'];
        }

        $product_data = array();

        $query = $this->db->query($sql);

        foreach ($query->rows as $result) {
            $product_data[$result['product_id']] = $this->getProduct($result['product_id']);
        }

        return $product_data;
    }

    public function getProductSpecials($data = array()) {
        $time = date("Y-m-d-H-i", time());
        $sql = "SELECT DISTINCT ps.product_id, "
                . "(SELECT AVG(rating) FROM " . DB_PREFIX . "review r1 WHERE r1.product_id = ps.product_id "
                . "AND r1.status = '2' GROUP BY r1.product_id) AS rating "
                . "FROM " . DB_PREFIX . "product_special ps LEFT JOIN " . DB_PREFIX . "product p ON (ps.product_id = p.product_id) "
                . "LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id) "
                . "LEFT JOIN " . DB_PREFIX . "product_to_store p2s ON (p.product_id = p2s.product_id) "
                . "WHERE p.status = '2' "
                . "AND ps.customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' "
                . "AND ((ps.date_start = '0000-00-00' OR ps.date_start < '" . $time . "') AND (ps.date_end = '0000-00-00' OR ps.date_end > '" . $time . "')) "
                . "GROUP BY ps.product_id";

        $sort_data = array(
            'pd.name',
            'ps.price',
            'rating',
            'p.sort_order'
        );

        if (isset($data['sort']) && in_array($data['sort'], $sort_data)) {
            if ($data['sort'] == 'pd.name' || $data['sort'] == 'p.model') {
                $sql .= " ORDER BY LCASE(" . $data['sort'] . ")";
            } else {
                $sql .= " ORDER BY " . $data['sort'];
            }
        } else {
            $sql .= " ORDER BY p.sort_order";
        }

        if (isset($data['order']) && ($data['order'] == 'DESC')) {
            $sql .= " DESC, LCASE(pd.name) DESC";
        } else {
            $sql .= " ASC, LCASE(pd.name) ASC";
        }

        if (isset($data['start']) || isset($data['limit'])) {
            if ($data['start'] < 0) {
                $data['start'] = 0;
            }

            if ($data['limit'] < 1) {
                $data['limit'] = 20;
            }

            $sql .= " LIMIT " . (int) $data['start'] . "," . (int) $data['limit'];
        }

        $product_data = array();

        $query = $this->db->query($sql);

        foreach ($query->rows as $result) {
            $product_data[$result['product_id']] = $this->getProduct($result['product_id']);
        }

        return $product_data;
    }

    public function getLatestProducts($limit) {
        $time = date("Y-m-d-H-i", time());
        $product_data = $this->cache->get('product.latest.' . (int) $this->config->get('config_language_id') . '.' . $this->config->get('config_customer_group_id') . '.' . (int) $limit);

        if (!$product_data) {
            $query = $this->db->query(""
                    . "SELECT p.product_id FROM " . DB_PREFIX . "product p "
                    . "WHERE p.status = '2' AND p.date_available <= '" . $time . "' "
                    . "AND p2s.store_id = '" . (int) $this->config->get('config_store_id') . "' "
                    . "ORDER BY p.date_added DESC LIMIT " . (int) $limit);

            foreach ($query->rows as $result) {
                $product_data[$result['product_id']] = $this->getProduct($result['product_id']);
            }

            $this->cache->set('product.latest.' . (int) $this->config->get('config_language_id') . '.' . $this->config->get('config_customer_group_id') . '.' . (int) $limit, $product_data);
        }

        return $product_data;
    }

    public function getPopularProducts($limit) {
        $time = date("Y-m-d-H-i", time());
        $product_data = array();

        $query = $this->db->query(""
                . "SELECT p.product_id, COUNT(*) As totalCount "
                . "FROM " . DB_PREFIX . "product p "
                . "LEFT JOIN " . DB_PREFIX . "order_product od ON (od.product_id = p.product_id) "
                . "WHERE p.status = '2' AND p.date_available <= '" . $time . "' "
                . "GROUP BY p.product_id "
                . "ORDER BY totalCount DESC, p.date_added ASC LIMIT " . (int) $limit);

        foreach ($query->rows as $result) {
            $product_data[$result['product_id']] = $this->getProduct($result['product_id']);
        }

        return $product_data;
    }

    public function getBestSellerProducts($limit) {
        $time = date("Y-m-d-H-i", time());
        $product_data = $this->cache->get('product.bestseller.' . (int) $this->config->get('config_language_id') . '.' . $this->config->get('config_customer_group_id') . '.' . (int) $limit);

        if (!$product_data) {
            $product_data = array();

            $query = $this->db->query("SELECT op.product_id, SUM(op.quantity) AS total FROM " . DB_PREFIX . "order_product op LEFT JOIN '" . DB_PREFIX . "order' o ON (op.order_id = o.order_id) LEFT JOIN '" . DB_PREFIX . "product' p ON (op.product_id = p.product_id) LEFT JOIN " . DB_PREFIX . "product_to_store p2s ON (p.product_id = p2s.product_id) WHERE o.order_status_id > '0' AND p.status = '2' AND p.date_available <= '" . $time . "' AND p2s.store_id = '" . (int) $this->config->get('config_store_id') . "' GROUP BY op.product_id ORDER BY total DESC LIMIT " . (int) $limit);

            foreach ($query->rows as $result) {
                $product_data[$result['product_id']] = $this->getProduct($result['product_id']);
            }

            $this->cache->set('product.bestseller.' . (int) $this->config->get('config_language_id') . '.' . $this->config->get('config_customer_group_id') . '.' . (int) $limit, $product_data);
        }

        return $product_data;
    }

    public function getProductDiscounts($product_id) {
        $time = date("Y-m-d-H-i", time());
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_discount WHERE product_id = '" . (int) $product_id . "' AND customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' AND quantity > 1 AND ((date_start = '0000-00-00' OR date_start < '" . $time . "') AND (date_end = '0000-00-00' OR date_end > '" . $time . "')) ORDER BY quantity ASC, priority ASC, price ASC");

        return $query->rows;
    }

    public function getProductImages($product_id) {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_image WHERE product_id = '" . (int) $product_id . "' ORDER BY sort_order ASC");

        return $query->rows;
    }

    public function getCategories($product_id) {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "product_to_category WHERE product_id = '" . (int) $product_id . "'");

        return $query->rows;
    }

    public function getTotalProducts($data = array()) {

        $time = date("Y-m-d-H-i", time());

        $sql = "SELECT COUNT(DISTINCT p.product_id) AS total";

        if (!empty($data['filter_category_id'])) {
            if (!empty($data['filter_sub_category'])) {
                $sql .= " FROM " . DB_PREFIX . "category_path cp LEFT JOIN " . DB_PREFIX . "product_to_category p2c ON (cp.category_id = p2c.category_id)";
            } else {
                $sql .= " FROM " . DB_PREFIX . "product_to_category p2c";
            }

            if (!empty($data['filter_filter'])) {
                $sql .= " LEFT JOIN " . DB_PREFIX . "product_filter pf ON (p2c.product_id = pf.product_id) LEFT JOIN " . DB_PREFIX . "product p ON (pf.product_id = p.product_id)";
            } else {
                $sql .= " LEFT JOIN " . DB_PREFIX . "product p ON (p2c.product_id = p.product_id)";
            }
        } else {
            $sql .= " FROM " . DB_PREFIX . "product p";
        }

        $sql .= " LEFT JOIN " . DB_PREFIX . "product_lang pd ON (p.product_id = pd.product_id) LEFT JOIN " . DB_PREFIX . "product_to_store p2s ON (p.product_id = p2s.product_id) WHERE pd.language_id = '" . (int) $this->config->get('config_language_id') . "' AND p.status = '2' AND p.date_available <= '" . $time . "' AND p2s.store_id = '" . (int) $this->config->get('config_store_id') . "'";

        if (!empty($data['filter_category_id'])) {

            $sql .= " AND p2c.category_id = '" . (int) $data['filter_category_id'] . "'";

            if (!empty($data['filter_filter'])) {
                $implode = array();

                $filters = explode(',', $data['filter_filter']);

                foreach ($filters as $filter_id) {
                    $implode[] = (int) $filter_id;
                }

                $sql .= " AND pf.filter_id IN (" . implode(',', $implode) . ")";
            }
        }

        $query = $this->db->query($sql);

        return $query->row['total'];
    }

    public function getProfile($product_id, $recurring_id) {
        return $this->db->query("SELECT * FROM '" . DB_PREFIX . "recurring' 'p' JOIN '" . DB_PREFIX . "product_recurring' 'pp' ON 'pp'.'recurring_id' = 'p'.'recurring_id' AND 'pp'.'product_id' = " . (int) $product_id . " WHERE 'pp'.'recurring_id' = " . (int) $recurring_id . " AND 'status' = 1 AND 'pp'.'customer_group_id' = " . (int) $this->config->get('config_customer_group_id'))->row;
    }

    public function getProfiles($product_id) {
        return $this->db->query("SELECT 'pd'.* FROM '" . DB_PREFIX . "product_recurring' 'pp' JOIN '" . DB_PREFIX . "recurring_lang' 'pd' ON 'pd'.'language_id' = " . (int) $this->config->get('config_language_id') . " AND 'pd'.'recurring_id' = 'pp'.'recurring_id' JOIN '" . DB_PREFIX . "recurring' 'p' ON 'p'.'recurring_id' = 'pd'.'recurring_id' WHERE 'product_id' = " . (int) $product_id . " AND 'status' = 1 AND 'customer_group_id' = " . (int) $this->config->get('config_customer_group_id') . " ORDER BY 'sort_order' ASC")->rows;
    }

    public function getTotalProductSpecials() {
        $time = date("Y-m-d-H-i", time());
        $query = $this->db->query("SELECT COUNT(DISTINCT ps.product_id) AS total FROM " . DB_PREFIX . "product_special ps LEFT JOIN " . DB_PREFIX . "product p ON (ps.product_id = p.product_id) LEFT JOIN " . DB_PREFIX . "product_to_store p2s ON (p.product_id = p2s.product_id) WHERE p.status = '2' AND p.date_available <= '" . $time . "' AND p2s.store_id = '" . (int) $this->config->get('config_store_id') . "' AND ps.customer_group_id = '" . (int) $this->config->get('config_customer_group_id') . "' AND ((ps.date_start = '0000-00-00' OR ps.date_start < '" . $time . "') AND (ps.date_end = '0000-00-00' OR ps.date_end > '" . $time . "'))");

        if (isset($query->row['total'])) {
            return $query->row['total'];
        } else {
            return 0;
        }
    }

}
