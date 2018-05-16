<?php

class ControllerApiMinecraftV1OrderSends extends Controller {

    public function index() {
        $this->load->language('api/general');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        if ($this->registry->get('api_id') === null) {
            $json['error']['warning'] = $this->language->get('error_permission');
        } else {
            if (isset($this->request->get['uuid']) && isset($this->request->get['server'])) {
                $pl = str_replace("-", "", $this->request->get['uuid']);
                $sv = $this->request->get['server'];

                $this->load->model("minecraft/sender");

                $sends = $this->model_minecraft_sender->getPlayerSendsByServer($pl, $sv);

                $response = array();
                foreach ($sends as $send) {
                    $item = array(
                        "pending_id"       => (int)$send['minecraft_sender_id'],
                        "product_id"       => (int)$send['product_id'],
                        "product_name"     => $send['name'],
                        "product_group"    => (int)$send['minecraft_product_group_id'],
                        "product_quantity" => (int)$send['quantity'],
                        "duration_single"  => (int)$send['duration'],
                        "duration"         => (int)$send['duration'] * $send['quantity'],
                        "lifetime"         => (int)$send['lifetime'],
                        "commands"         => json_decode($send['commands'], true),
                        "event"            => $send['event']
                    );
                    $response[] = $item;
                }

                $json['status'] = $this->language->get("text_success");
                $json['response'] = $response;
            } else {
                $json['error']['warning'] = $this->language->get('error_request');
            }
        }

        if (isset($this->request->server['HTTP_ORIGIN'])) {
            $this->response->addHeader('Access-Control-Allow-Origin: ' . $this->request->server['HTTP_ORIGIN']);
            $this->response->addHeader('Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS');
            $this->response->addHeader('Access-Control-Max-Age: 1000');
            $this->response->addHeader('Access-Control-Allow-Headers: Content-Type, Authorization, X-Requested-With');
        }

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }

}
