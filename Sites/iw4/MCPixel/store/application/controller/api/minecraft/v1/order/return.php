<?php

class ControllerApiMinecraftV1OrderReturn extends Controller {

    public function index() {
        $this->load->language('api/general');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        if ($this->registry->get('api_id') === null) {
            $json['error']['warning'] = $this->language->get('error_permission');
        } else {
            if (isset($this->request->get['pending_id']) && isset($this->request->get['command_list'])) {
                $pending_id = $this->request->get['pending_id'];
                $command_list = $this->request->get['command_list'];

                $this->load->model("minecraft/sender");
                
                $this->model_minecraft_sender->setCommands($pending_id, $command_list);
                
                $json['status'] = $this->language->get("text_success");
                
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
