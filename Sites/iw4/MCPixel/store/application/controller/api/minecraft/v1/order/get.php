<?php

class ControllerApiMinecraftV1OrderGet extends Controller {

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
                $uu = str_replace("-", "", $this->request->get['uuid']);
                $sv = $this->request->get['server'];

                $this->load->model("minecraft/active");

                $response = array();

                $cache = $this->cache->get('api.order.get.' . $uu . "." . $sv);
                if (empty($cache)) {

                    $pkgs = $this->model_minecraft_active->getPlayerPackages($uu, $sv);

                    foreach ($pkgs as $pkg) {
                        $item = array();
                        $item['minecraft_account_package_id'] = (int)$pkg['minecraft_account_package_id'];
                        $item['product_name'] = $pkg['name'];
                        $item['days'] = (int)$pkg['days'];
                        $item['lifetime'] = (boolean)$pkg['lifetime'];
                        $item['date_added'] = $pkg['date_added'];
                        $item['date_updated'] = $pkg['date_updated'];
                        $response[] = $item;
                    }

                    $this->cache->set('api.order.get.' . $uu . "." . $sv, $response);
                } else {
                    $response = $cache;
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
