<?php

class ModelCatalogCategory extends Model {
    public function addCategory($data) {
        $this->event->trigger('pre.admin.category.add', $data);

        $this->db->query("
        INSERT INTO " . DB_PREFIX . "category
        SET sort_order = '" . (int)$data['sort_order'] . "',
        status = '" . (int)$data['status'] . "',
        date_updated = NOW(), date_added = NOW()");

        $category_id = $this->db->getLastId();

        foreach ($data['category_lang'] as $language_id => $value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "category_lang SET category_id = '" . (int)$category_id . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($value['name']) . "', description = '" . $this->db->escape($value['description']) . "'");
        }

        $this->cache->delete('category');

        $this->event->trigger('post.admin.category.add', $category_id);

        return $category_id;
    }

    public function editCategory($category_id, $data) {
        $this->event->trigger('pre.admin.category.edit', $data);

        $this->db->query("
        UPDATE " . DB_PREFIX . "category
        SET sort_order = '" . (int)$data['sort_order'] . "',
        status = '" . (int)$data['status'] . "',
        date_updated = NOW() WHERE category_id = '" . (int)$category_id . "'");

        $this->db->query("DELETE FROM " . DB_PREFIX . "category_lang WHERE category_id = '" . (int)$category_id . "'");

        foreach ($data['category_lang'] as $language_id => $value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "category_lang SET category_id = '" . (int)$category_id . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($value['name']) . "', description = '" . $this->db->escape($value['description']) . "'");
        }

        $this->cache->delete('category');

        $this->event->trigger('post.admin.category.edit', $category_id);
    }

    public function deleteCategory($category_id) {
        $this->event->trigger('pre.admin.category.delete', $category_id);

        $this->db->query("DELETE FROM " . DB_PREFIX . "category WHERE category_id = '" . (int)$category_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "category_lang WHERE category_id = '" . (int)$category_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "product_to_category WHERE category_id = '" . (int)$category_id . "'");

        $this->cache->delete('category');

        $this->event->trigger('post.admin.category.delete', $category_id);
    }

    public function getCategory($category_id) {
        $query = $this->db->query("
        SELECT DISTINCT *
        FROM " . DB_PREFIX . "category c
        LEFT JOIN " . DB_PREFIX . "category_lang cd2 ON (c.category_id = cd2.category_id)
        WHERE c.category_id = '" . (int)$category_id . "' AND cd2.language_id = '" . (int)$this->config->get('config_language_id') . "'");

        return $query->row;
    }

    public function getCategories($data = array()) {
        $sql = "
        SELECT * FROM " . DB_PREFIX . "category catg
        LEFT JOIN " . DB_PREFIX . "category_lang catgl ON (catg.category_id = catgl.category_id)
        WHERE catgl.language_id = '" . (int)$this->config->get('config_language_id') . "'";

        if (!empty($data['filter_name'])) {
            $sql .= " AND catgl.name LIKE '" . $this->db->escape($data['filter_name']) . "%'";
        }

        $sql .= " GROUP BY catg.category_id";

        $sort_data = array(
            'name',
            'sort_order'
        );

        if (isset($data['sort']) && in_array($data['sort'], $sort_data)) {
            $sql .= " ORDER BY " . $data['sort'];
        } else {
            $sql .= " ORDER BY sort_order";
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

    public function getCategoryDescriptions($category_id) {
        $category_lang_data = array();

        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "category_lang WHERE category_id = '" . (int)$category_id . "'");

        foreach ($query->rows as $result) {
            $category_lang_data[$result['language_id']] = array(
                'name'         => $result['name'],
                'description'  => $result['description']
            );
        }

        return $category_lang_data;
    }

    public function getTotalCategories() {
        $query = $this->db->query("SELECT COUNT(*) AS total FROM " . DB_PREFIX . "category");

        return $query->row['total'];
    }

    public function getTotalCategoriesByLayoutId($layout_id) {
        $query = $this->db->query("SELECT COUNT(*) AS total FROM " . DB_PREFIX . "category_to_layout WHERE layout_id = '" . (int)$layout_id . "'");

        return $query->row['total'];
    }
}
