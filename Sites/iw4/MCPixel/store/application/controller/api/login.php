<?php

class ControllerApiLogin extends Controller {

    public function index() {
        $this->load->language('api/general');
        $this->load->language('api/login');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        $this->load->model('account/api');

        // Check if IP is allowed
        $ip_data = array();

        $results = $this->model_account_api->getApiIps($this->config->get('config_api_id'));

        foreach ($results as $result) {
            $ip_data[] = $result['ip'];
        }

        if (!in_array($this->request->server['REMOTE_ADDR'], $ip_data)) {
            $json['error']['warning'] = sprintf($this->language->get('error_ip'), $this->request->server['REMOTE_ADDR']);
            $json['error']['ip'] = sprintf($this->language->get('error_ip'), $this->request->server['REMOTE_ADDR']);
        }

        if (!isset($json['error'])) {
            if (!isset($this->request->get['key'])) {
                $json['error']['warning'] = $this->language->get('error_key');
                $json['error']['key'] = $this->language->get('error_key');
            } else {
                // Login with API Key
                $api_info = $this->model_account_api->getApiByKey($this->request->get['key']);

                if ($api_info) {
                    $json['status'] = $this->language->get("text_success");
                    $json['response']['description'] = $this->language->get("text_login_success");

                    // Create Token
                    $json['response']['token'] = $this->model_account_api->addApiSession($api_info['api_id'], $this->request->server['REMOTE_ADDR']);
                } else {
                    $json['error']['warning'] = $this->language->get('error_key');
                    $json['error']['key'] = $this->language->get('error_key');
                }
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

    public function status() {
        $this->load->language('api/general');
        $this->load->language('api/login');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        $this->load->model('account/api');

        // Check if IP is allowed
        $ip_data = array();

        $results = $this->model_account_api->getApiIps($this->config->get('config_api_id'));

        foreach ($results as $result) {
            $ip_data[] = $result['ip'];
        }

        if (!in_array($this->request->server['REMOTE_ADDR'], $ip_data)) {
            $json['error']['warning'] = sprintf($this->language->get('error_ip'), $this->request->server['REMOTE_ADDR']);
        }

        if ($this->registry->get('api_id') === null) {
            $json['error']['warning'] = $this->language->get('error_permission');
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
