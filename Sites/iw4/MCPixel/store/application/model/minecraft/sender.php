<?php

class ModelMinecraftSender extends Model {

    public function getPlayerSend($id) {
        $response = array();

        $query = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "minecraft_sender ms "
                . "LEFT JOIN " . DB_PREFIX . "order_product op ON (op.order_product_id = ms.order_product_id) "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_product_to_group ptg ON (ptg.product_id = op.product_id) "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_product_group pg ON (ptg.minecraft_product_group_id = pg.minecraft_product_group_id) "
                . "WHERE ms.minecraft_sender_id = '" . (int) $id . "' LIMIT 1");

        $nobj = false;
        foreach ($query->row as $index => $value) {
            if ($value == null) {
                $nobj = true;
                break;
            }
        }
        if (!$nobj) {
            return $query->row;
        } else {
            $this->remove($id);
        }
        return array();
    }

    public function getPlayerSendsByServer($player, $servercode) {
        $query = $this->db->query(""
                . "SELECT * FROM " . DB_PREFIX . "minecraft_sender ms "
                . "LEFT JOIN " . DB_PREFIX . "order_product op ON (op.order_product_id = ms.order_product_id) "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_product_to_group ptg ON (ptg.product_id = op.product_id) "
                . "LEFT JOIN " . DB_PREFIX . "minecraft_product_group pg ON (ptg.minecraft_product_group_id = pg.minecraft_product_group_id) "
                . "WHERE ms.player = '" . $this->db->escape($player) . "' "
                . "ORDER BY ms.minecraft_sender_id ASC ");

        $send_list = array();
        foreach ($query->rows as $send) {
            $server_list = json_decode($send['server_list'], true);
            if (in_array($servercode, $server_list)) {
                $nobj = false;
                foreach ($send as $index => $value) {
                    if ($value == null) {
                        $nobj = true;
                        break;
                    }
                }
                if (!$nobj) {
                    $send_list[] = $send;
                } else {
                    $this->remove($send['minecraft_sender_id']);
                }
            }
        }

        return $send_list;
    }

    public function insert($insert_data) {
        $sql = "INSERT INTO " . DB_PREFIX . "minecraft_sender "
                . "SET order_id = '" . (int) $insert_data['order_id'] . "', "
                . "order_product_id = '" . (int) $insert_data['order_product_id'] . "', "
                . "player = '" . $this->db->escape($insert_data['player']) . "', "
                . "event = '" . $this->db->escape($insert_data['event']) . "', "
                . "commands = '" . $this->db->escape($insert_data['commands']) . "', "
                . "server_list = '" . $this->db->escape($insert_data['server_list']) . "', "
                . "date_added = '" . date("Y-m-d-H-i", time()) . "' ";

        return $this->db->query($sql);
    }

    public function setCommands($pending_id, $commands) {
        $sql = "UPDATE " . DB_PREFIX . "minecraft_sender "
                . "SET commands = '" . $this->db->escape($commands) . "', "
                . "date_updated = '" . date("Y-m-d-H-i", time()) . "' "
                . "WHERE minecraft_sender_id = '" . (int) $pending_id . "'";

        return $this->db->query($sql);
    }

    public function remove($sender_id) {
        $sql = "DELETE FROM " . DB_PREFIX . "minecraft_sender "
                . "WHERE minecraft_sender_id = '" . (int) $sender_id . "'";

        return $this->db->query($sql);
    }

    public function removeOrderSenders($order_id) {
        $sql = "DELETE FROM " . DB_PREFIX . "minecraft_sender "
                . "WHERE order_id = '" . (int) $order_id . "'";

        return $this->db->query($sql);
    }

}
