<?php

class ModelLocalisationCountry extends Model {

    public function getCountry($country_id) {
        $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "country WHERE country_id = '" . (int) $country_id . "' AND status = '2'");

        return $query->row;
    }

    public function getCountries() {
        $country_data = $this->cache->get('country.status');

        if (!$country_data) {
            $query = $this->db->query("SELECT * FROM " . DB_PREFIX . "country WHERE status = '2' ORDER BY name ASC");

            $country_data = $query->rows;

            $this->cache->set('country.status', $country_data);
        }

        return $country_data;
    }

}
