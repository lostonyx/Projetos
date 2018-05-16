<?php

class ModelMinecraftActive extends Model {

    public function getPackage($pkg_id) {
        $packages = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE minecraft_account_package_id = '" . (int)$pkg_id . "' "
            . "LIMIT 1");

        return $packages->row;
    }

    public function getPlayerPackages($player, $server = "") {
        $packages = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE player = '" . $this->db->escape($player) . "' "
            . "AND server LIKE '%\"" . $this->db->escape($server) . "\"%'");
        return $packages->rows;
    }

    public function getPlayerPackage($player, $product_id, $server) {
        $packages = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE player = '" . $this->db->escape($player) . "' "
            . "AND product_id = '" . (int)$product_id . "' "
            . "AND server LIKE '%\"" . $this->db->escape($server) . "\"%' "
            . "LIMIT 1");

        return $packages->row;
    }

    public function getPlayerPackageById($player, $pkg_id, $server) {
        $packages = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE player = '" . $this->db->escape($player) . "' "
            . "AND minecraft_account_package_id = '" . (int)$pkg_id . "' "
            . "AND server LIKE '%\"" . $this->db->escape($server) . "\"%' "
            . "LIMIT 1");

        return $packages->row;
    }

    public function getGroupCountPlayerPackage($player, $pkg_group_id, $server) {
        $packages = $this->db->query(""
            . "SELECT COUNT(*) As TotalCount FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE player = '" . $this->db->escape($player) . "' "
            . "AND minecraft_product_group_id = '" . (int)$pkg_group_id . "' "
            . "AND server LIKE '%\"" . $this->db->escape($server) . "\"%' "
            . "LIMIT 1");

        return $packages->row['TotalCount'];
    }

    public function getToUpdatePlayerPackages($player, $server = "") {
        $today = date("Y-m-d", time());
        $packages = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE player = '" . $this->db->escape($player) . "' "
            . "AND server LIKE '%\"" . $this->db->escape($server) . "\"%' "
            . "AND date_updated NOT LIKE '" . $today . "%' "
            . "AND lifetime = 0");

        return $packages->rows;
    }

    public function getFirstGroupPlayerPackage($player, $group_id, $server) {
        $packages = $this->db->query(""
            . "SELECT * FROM " . DB_PREFIX . "minecraft_account_package "
            . "WHERE player = '" . $this->db->escape($player) . "' "
            . "AND minecraft_product_group_id = '" . (int)$group_id . "'"
            . "AND server LIKE '%\"" . $this->db->escape($server) . "\"%' "
            . "ORDER BY minecraft_account_package_id ASC "
            . "LIMIT 1 ");

        return $packages->row;
    }

    public function insertPackage($data = array()) {
        $this->cache->delete('api.order.get.' . $this->db->escape($data['player']));

        $sql = "INSERT INTO " . DB_PREFIX . "minecraft_account_package "
            . "SET minecraft_product_group_id = '" . $this->db->escape($data['minecraft_product_group_id']) . "', "
            . "product_id = '" . $this->db->escape($data['product_id']) . "', "
            . "order_id = '" . $this->db->escape($data['order_id']) . "', "
            . "order_product_id = '" . $this->db->escape($data['order_product_id']) . "', "
            . "player = '" . $this->db->escape($data['player']) . "', "
            . "name = '" . $this->db->escape($data['name']) . "', "
            . "days = '" . $this->db->escape($data['duration']) . "', "
            . "lifetime = '" . $this->db->escape($data['lifetime']) . "', "
            . "server = '" . $this->db->escape($data['server_list']) . "', "
            . "date_added = NOW(), "
            . "date_updated = NOW() ";

        return $this->db->query($sql);
    }

    public function addDays($pkg_id, $days) {
        $pkg = $this->getPackage($pkg_id);
        if (!empty($pkg)) {

            $this->cache->delete('api.order.get.' . $pkg['player']);

            $update = $this->db->query(""
                . "UPDATE " . DB_PREFIX . "minecraft_account_package "
                . "SET days = (days + " . (int)$days . "), date_updated = NOW() "
                . "WHERE minecraft_account_package_id = '" . (int)$pkg_id . "' "
                . "LIMIT 1");

            return $update;
        }
        return false;
    }

    public function update($pkg_id, $set = array()) {
        $pkg = $this->getPackage($pkg_id);
        if (!empty($pkg)) {

            $this->cache->delete('api.order.get.' . $pkg['player']);

            $statement = "";
            foreach ($set as $column => $value) {
                $statement .= ", " . $this->db->escape($column) . " = '" . $this->db->escape($this->db->escape($value)) . "'";
            }
            $update = $this->db->query(""
                . "UPDATE " . DB_PREFIX . "minecraft_account_package "
                . "SET date_updated = NOW()" . $statement . " "
                . "WHERE minecraft_account_package_id = '" . (int)$pkg_id . "' "
                . "LIMIT 1");

            return $update;
        }

        return false;
    }

    public function delete($pkg_id) {
        $pkg = $this->getPackage($pkg_id);
        if (!empty($pkg)) {

            $this->cache->delete('api.order.get.' . $pkg['player']);

            $delete = $this->db->query(""
                . "DELETE FROM " . DB_PREFIX . "minecraft_account_package "
                . "WHERE minecraft_account_package_id = '" . (int)$pkg_id . "' "
                . "LIMIT 1");

            return $delete;
        }

        return true;
    }

    public function removePlayerPackage($player, $pkg_id, $server, $handler = "onstop") {
        $this->load->model("minecraft/event");
        $this->load->model("minecraft/sender");

        $pkg = $this->getPlayerPackageById($player, $pkg_id, $server);
        if (empty($pkg)) {
            return false;
        }

        $this->cache->delete('api.order.get.' . $pkg['player']);

        $event = $this->model_minecraft_event->getEvent($handler);
        if (empty($event)) {
            return false;
        }

        $product_event = $this->model_minecraft_event->getProductEvent($pkg['product_id'], $event['minecraft_event_id']);
        if (empty($product_event)) {
            $deleted = $this->delete($pkg_id);
            if (!$deleted) {
                return false;
            }
            return true;
        }

        $count = ($pkg['minecraft_product_group_id'] > 0) ? $this->getGroupCountPlayerPackage($player, $pkg['minecraft_product_group_id'], $server) : 1;

        if ($count == 1) {

            $send_data = array(
                "order_id"         => $pkg['order_id'],
                "order_product_id" => $pkg['order_product_id'],
                "player"           => $player,
                "event"            => $event['event'],
                "commands"         => $product_event['commands'],
                "server_list"      => $pkg['server']
            );
            if (!$this->model_minecraft_sender->insert($send_data)) {
                return false;
            }

        }

        $deleted = $this->delete($pkg_id);
        if (!$deleted) {
            return false;
        }

        return true;
    }

}
