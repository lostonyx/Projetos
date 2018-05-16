<?php

class ModelMinecraftOrder extends Model {

    public function addProducts($order_id, $player, $event_handler = "onstart") {
        $this->load->model("minecraft/product");
        $this->load->model("minecraft/event");
        $this->load->model("minecraft/server");
        $this->load->model("minecraft/sender");
        
        // Get event
        $event = $this->model_minecraft_event->getEvent($event_handler);
        if (!$event) {
            return false;
        }

        // [remove] $this->model_minecraft_sender->removeOrderSenders($order_id);
        
        // Get products
        $products = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_product WHERE order_id = '" . (int) $order_id . "' AND (sent_status = '0' || sent_status = '2')");
        foreach ($products->rows as $order_product) {
            $product_event = $this->model_minecraft_event->getProductEvent($order_product['product_id'], $event['minecraft_event_id']);
            if (!$product_event) {
                continue;
            }
            
            // Get product server list
            $servers = $this->model_minecraft_server->getServerByProduct($order_product['product_id']);
            
            $server_list = array();
            foreach ($servers as $server) {
                $server_list[] = $server['code'];
            }
                
            // Send
            $send_data = array(
                "order_id" => $order_id,
                "order_product_id" => $order_product['order_product_id'],
                "player" => $player,
                "event" => $event['event'],
                "commands" => $product_event['commands'],
                "server_list" => json_encode($server_list)
            );

            if ($this->model_minecraft_sender->insert($send_data)) {
                $this->model_minecraft_product->updateProductSent($order_id, $order_product['product_id'], 1);
            }
        }
    }
    
    public function removeProducts($order_id, $player, $event_handler = "onstop") {
        $this->load->model("minecraft/product");
        $this->load->model("minecraft/event");
        $this->load->model("minecraft/server");
        $this->load->model("minecraft/sender");
        
        // Get event
        $event = $this->model_minecraft_event->getEvent($event_handler);
        if (!$event) {
            return false;
        }

        // [remove] $this->model_minecraft_sender->removeOrderSenders($order_id);
        
        // Get products
        $products = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_product WHERE order_id = '" . (int) $order_id . "' AND sent_status = '1'");
        foreach ($products->rows as $order_product) {
            $product_event = $this->model_minecraft_event->getProductEvent($order_product['product_id'], $event['minecraft_event_id']);
            if (!$product_event) {
                continue;
            }
            
            // Get product server list
            $servers = $this->model_minecraft_server->getServerByProduct($order_product['product_id']);
            
            $server_list = array();
            foreach ($servers as $server) {
                $server_list[] = $server['code'];
            }
                
            // Send
            $send_data = array(
                "order_id" => $order_id,
                "order_product_id" => $order_product['order_product_id'],
                "player" => $player,
                "event" => $event['event'],
                "commands" => $product_event['commands'],
                "server_list" => json_encode($server_list)
            );
            if ($this->model_minecraft_sender->insert($send_data)) {
                $this->model_minecraft_product->updateProductSent($order_id, $order_product['product_id'], 2);
            }
        }
    }

}
