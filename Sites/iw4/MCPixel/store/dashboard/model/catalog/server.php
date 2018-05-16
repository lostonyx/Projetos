<?php

class ModelCatalogServer extends Model {
    public function addServer($data) {
        $this->event->trigger('pre.admin.server.add', $data);

        $this->db->query("
        INSERT INTO " . DB_PREFIX . "minecraft_server
        SET sort_order = '" . (int)$data['sort_order'] . "',
        status = '" . (int)$data['status'] . "',
        code = '" . $this->db->escape($data['code']) . "',
        date_updated = NOW(), date_added = NOW()");

        $minecraft_server_id = $this->db->getLastId();

        foreach ($data['server_description'] as $language_id => $value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_server_lang SET minecraft_server_id = '" . (int)$minecraft_server_id . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($value['name']) . "', description = '" . $this->db->escape($value['description']) . "'");
        }

        $this->cache->delete('server');

        $this->event->trigger('post.admin.server.add', $minecraft_server_id);

        return $minecraft_server_id;
    }

    public function editServer($minecraft_server_id, $data) {
        $this->event->trigger('pre.admin.server.edit', $data);

        $this->db->query("
        UPDATE " . DB_PREFIX . "minecraft_server
        SET sort_order = '" . (int)$data['sort_order'] . "',
        status = '" . (int)$data['status'] . "',
        code = '" . $this->db->escape($data['code']) . "',
        date_updated = NOW() WHERE minecraft_server_id = '" . (int)$minecraft_server_id . "'");

        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_server_lang WHERE minecraft_server_id = '" . (int)$minecraft_server_id . "'");

        foreach ($data['server_description'] as $language_id => $value) {
            $this->db->query("INSERT INTO " . DB_PREFIX . "minecraft_server_lang SET minecraft_server_id = '" . (int)$minecraft_server_id . "', language_id = '" . (int)$language_id . "', name = '" . $this->db->escape($value['name']) . "', description = '" . $this->db->escape($value['description']) . "'");
        }

        $this->cache->delete('server');

        $this->event->trigger('post.admin.server.edit', $minecraft_server_id);
    }

    public function deleteServer($minecraft_server_id) {
        $this->event->trigger('pre.admin.server.delete', $minecraft_server_id);

        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_server WHERE minecraft_server_id = '" . (int)$minecraft_server_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_server_lang WHERE minecraft_server_id = '" . (int)$minecraft_server_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "minecraft_product_to_server WHERE minecraft_server_id = '" . (int)$minecraft_server_id . "'");

        $this->cache->delete('server');

        $this->event->trigger('post.admin.server.delete', $minecraft_server_id);
    }

    public function getServer($minecraft_server_id) {
        $query = $this->db->query("
        SELECT DISTINCT *
        FROM " . DB_PREFIX . "minecraft_server c
        LEFT JOIN " . DB_PREFIX . "minecraft_server_lang cd2 ON (c.minecraft_server_id = cd2.minecraft_server_id)
        WHERE c.minecraft_server_id = '" . (int)$minecraft_server_id . "' AND cd2.language_id = '" . (int)$this->config->get('config_language_id') . "'");

        return $query->row;
    }

    public function getServerByCode($minecraft_server_code, $data = array()) {
        $sql = "
        SELECT DISTINCT *
        FROM " . DB_PREFIX . "minecraft_server c
        LEFT JOIN " . DB_PREFIX . "minecraft_server_lang cd2 ON (c.minecraft_server_id = cd2.minecraft_server_id)
        WHERE c.code = '" . $this->db->escape($minecraft_server_code) . "' AND cd2.language_id = '" . (int)$this->config->get('config_language_id') . "'";

        if (isset($data['not_like_id'])) {
            $sql .= ' AND c.minecraft_server_id != \''. $this->db->escape($data['not_like_id']) .'\'';
        }

        $query = $this->db->query($sql);

        return $query->row;
    }

    public function getServers($data = array()) {
        $sql = "
        SELECT * FROM " . DB_PREFIX . "minecraft_server sv
        LEFT JOIN " . DB_PREFIX . "minecraft_server_lang svl ON (sv.minecraft_server_id = svl.minecraft_server_id)
        WHERE svl.language_id = '" . (int)$this->config->get('config_language_id') . "'";

        if (!empty($data['filter_name'])) {
            $sql .= " AND svl.name LIKE '" . $this->db->escape($data['filter_name']) . "%'";
        }

        $sql .= " GROUP BY sv.minecraft_server_id";

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

    public function getServerDescriptions($minecraft_server_id) {
        $server_description_data = array();

        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "minecraft_server_lang WHERE minecraft_server_id = '" . (int)$minecraft_server_id . "'");

        foreach ($query->rows as $result) {
            $server_description_data[$result['language_id']] = array(
                'name'         => $result['name'],
                'description'  => $result['description']
            );
        }

        return $server_description_data;
    }

    public function getTotalServers() {
        $query = $this->db->query("SELECT COUNT(*) AS total FROM " . DB_PREFIX . "minecraft_server");

        return $query->row['total'];
    }

    public function getTotalServersByLayoutId($layout_id) {
        $query = $this->db->query("SELECT COUNT(*) AS total FROM " . DB_PREFIX . "minecraft_server_to_layout WHERE layout_id = '" . (int)$layout_id . "'");

        return $query->row['total'];
    }
}
