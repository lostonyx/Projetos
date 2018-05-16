<?php

class ModelMinecraftProduct extends Model {

    public function updateProductSent($order_id, $product_id, $sent = 0) {
        $sql = "UPDATE " . DB_PREFIX . "order_product "
                . "SET sent_status = '" . (int) $sent . "' "
                . "WHERE product_id = '" . (int) $product_id . "' AND order_id = '" . (int) $order_id . "'";

        return $this->db->query($sql);
    }

}
