<?php

class ControllerMinecraftOrder extends Controller {

    public function update($order_id) {
        $this->load->model("checkout/order");
        $this->load->model("minecraft/order");
        
        // Get order
        $order = $this->model_checkout_order->getOrder($order_id);
        if (!$order) {
            return false;
        }

        // Get player
        $player = $order['custom_field'][$this->config->get("config_custom_field_player")];
        if (!$player) {
            return false;
        }

        // Get status
        if (in_array($order['order_status_id'], $this->config->get("config_complete_status"))) {
            $this->model_minecraft_order->addProducts($order_id, $player);
        } elseif (!in_array($order['order_status_id'], $this->config->get("config_processing_status"))) {
            $this->model_minecraft_order->removeProducts($order_id, $player);
        }
    }

}
