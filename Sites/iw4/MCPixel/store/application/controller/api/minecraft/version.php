<?php

class ControllerApiMinecraftVersion extends Controller {

    public function index() {
        return $this->current();
    }

    public function current() {
        $this->load->language('api/general');
        $this->load->language('api/minecraft/version');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        if ($this->registry->get('api_id') === null) {
            $json['error']['warning'] = $this->language->get('error_permission');
        } else {
            $json['status'] = $this->language->get("text_success");
            $json['response']['version'] = $this->config->get("version_api_minecraft_current");
            $json['response']['version_description'] = sprintf($this->language->get("text_current_version"), $this->config->get("version_api_minecraft_current"));
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

    public function compare() {
        $this->load->language('api/general');
        $this->load->language('api/minecraft/version');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        if ($this->registry->get('api_id') === null) {
            $json['error']['warning'] = $this->language->get('error_permission');
        } else {
            if (isset($this->request->get['request_version']) && isset($this->request->get['api_version'])) {
                $rv = $this->request->get['request_version'];
                $av = $this->request->get['api_version'];

                $version = $this->config->get("version_api_minecraft_" . $av);
                if (version_compare($rv, $version[0], $version[1])) {
                    $json['status'] = $this->language->get("text_success");
                    $json['response']['accept'] = true;
                } else {
                    $json['status'] = $this->language->get("text_success");
                    $json['response']['accept'] = false;
                }
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
