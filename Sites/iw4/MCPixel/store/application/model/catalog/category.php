<?php

class ModelCatalogCategory extends Model {

    public function getCategory($category_id) {
        $query = $this->db->query(""
                . "SELECT DISTINCT * FROM " . DB_PREFIX . "category c "
                . "LEFT JOIN " . DB_PREFIX . "category_lang cd ON (c.category_id = cd.category_id) "
                . "WHERE c.category_id = '" . (int) $category_id . "' AND cd.language_id = '" . (int) $this->config->get('config_language_id') . "' "
                . "AND c.status = '2' "
                . "LIMIT 1");

        return $query->row;
    }
    
    public function getFirstCategory() {
        $query = $this->db->query(""
                . "SELECT DISTINCT * FROM " . DB_PREFIX . "category c "
                . "LEFT JOIN " . DB_PREFIX . "category_lang cd ON (c.category_id = cd.category_id) "
                . "WHERE cd.language_id = '" . (int) $this->config->get('config_language_id') . "' AND c.status = '2' "
                . "ORDER BY c.sort_order, LCASE(cd.name) "
                . "LIMIT 1");

        return $query->row;
    }

    public function getCategories($parent_id = 0) {
        $query = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "category c "
                . "LEFT JOIN " . DB_PREFIX . "category_lang cd ON (c.category_id = cd.category_id) "
                . "WHERE cd.language_id = '" . (int) $this->config->get('config_language_id') . "' "
                . "AND c.status = '2' "
                . "ORDER BY c.sort_order, LCASE(cd.name)");

        return $query->rows;
    }

    public function getTotalCategoriesByCategoryId($parent_id = 0) {
        $query = $this->db->query(""
                . "SELECT COUNT(*) AS total FROM " . DB_PREFIX . "category c "
                . "WHERE c.parent_id = '" . (int) $parent_id . "' "
                . "AND c2s.store_id = '" . (int) $this->config->get('config_store_id') . "' AND c.status = '2'");

        return $query->row['total'];
    }

}
