<?php

class ModelAccountApi extends Model {

    public function getApiByKey($key) {
        $query = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "api api "
                . "WHERE api.key = '" . $this->db->escape($key) . "' AND status = '2'");

        return $query->row;
    }

    public function addApiSession($api_id, $ip) {
        $token = token(32);

        $this->db->query(""
                . "INSERT INTO " . DB_PREFIX . "api_session "
                . "SET api_id = '" . (int) $api_id . "', "
                . "token = '" . $this->db->escape($token) . "', "
                . "ip = '" . $this->db->escape($ip) . "', "
                . "date_added = NOW(), date_updated = NOW()");

        return $token;
    }

    public function getApis($data = array()) {
        $sql = "SELECT * FROM " . DB_PREFIX . "api";

        $sort_data = array(
            'name',
            'status',
            'date_added',
            'date_updated'
        );

        if (isset($data['sort']) && in_array($data['sort'], $sort_data)) {
            $sql .= " ORDER BY " . $data['sort'];
        } else {
            $sql .= " ORDER BY name";
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

            $sql .= " LIMIT " . (int) $data['start'] . "," . (int) $data['limit'];
        }

        $query = $this->db->query($sql);

        return $query->rows;
    }

    public function getApiIps($api_id) {
        $query = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "api_ip "
                . "WHERE api_id = '" . (int) $api_id . "'");

        return $query->rows;
    }

}
