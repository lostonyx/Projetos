<?php

class ControllerApiMinecraftV1OrderSet extends Controller {

    public function index() {
        $this->load->language('api/general');

        $json = array();
        $json['status'] = $this->language->get("text_error");
        $json['logged'] = ($this->registry->get('api_id') === null) ? false : true;
        $json['response'] = array();

        if ($this->registry->get('api_id') === null) {
            $json['error']['warning'] = $this->language->get('error_permission');
        } else {
            if (isset($this->request->get['uuid']) && isset($this->request->get['server']) && isset($this->request->get['pending_list'])) {
                $uu = str_replace("-", "", $this->request->get['uuid']);
                $sv = $this->request->get['server'];
                $pl = json_decode($this->request->get['pending_list']);

                $this->load->model("minecraft/sender");
                $this->load->model("minecraft/active");

                $sends = array();

                foreach ($pl as $sender_id) {
                    $sender = $this->model_minecraft_sender->getPlayerSend($sender_id);
                    if (!empty($sender)) {
                        $sends[$sender['minecraft_sender_id']] = $sender;
                    }
                }

                $response = array();
                foreach ($sends as $send_id => $send) {
                    $ev = $send['event'];

                    $item = array(
                        "product_id"   => (int)$send['product_id'],
                        "product_name" => $send['name'],
                        "event" => $send['event']
                    );

                    if ($ev === "onstart") {

                        $pkg = $this->model_minecraft_active->getPlayerPackage($uu, $send['product_id'], $sv);

                        if (!empty($pkg)) {
                            $query = $this->model_minecraft_active->addDays($pkg['minecraft_account_package_id'], $sender['duration'] * $sender['quantity']);
                            if (!$query) {
                                continue;
                            }

                            if ($pkg['lifetime'] == false && $send['lifetime'] == true) {
                                $query = $this->model_minecraft_active->update($pkg['minecraft_account_package_id'], ["lifetime" => 1]);
                            }
                            if (!$this->model_minecraft_sender->remove($send_id)) {
                                continue;
                            }
                            $response[] = $item;
                        } else {
                            $query = $this->model_minecraft_active->insertPackage($send);
                            if (!$query || !$this->model_minecraft_sender->remove($send_id)) {
                                continue;
                            }
                            $response[] = $item;
                        }
                    } elseif ($ev === "onstop") {

                        if (!$this->model_minecraft_sender->remove($send_id)) {
                            continue;
                        }
                        $response[] = $item;
                    }

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
