<?php

class ControllerApiMinecraftV1OrderExpired extends Controller {

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

                $this->load->model("minecraft/sender");
                $this->load->model("minecraft/active");

                $response = array();

                $response["updated"] = array();
                $response["removed"] = array();

                $toupdate = $this->model_minecraft_active->getToUpdatePlayerPackages($uu, $sv);

                if (!empty($toupdate)) {
                    $update_list = array();
                    $remove_list = array();
                    $item_list = array();
                    foreach ($toupdate as $toup) {

                        $group = $toup['minecraft_product_group_id'];

                        if ($group > 0) {
                            $toup_new = $this->model_minecraft_active->getFirstGroupPlayerPackage($uu, $toup['minecraft_product_group_id'], $sv);
                            if (!$toup_new) {
                                continue;
                            }
                            if ($toup_new['minecraft_account_package_id'] !== $toup['minecraft_account_package_id']) {
                                $this->model_minecraft_active->update($toup['minecraft_account_package_id']);
                                $toup = $toup_new;
                            }
                            unset($toup_new);
                        }

                        if (!isset($item_list[$toup['minecraft_account_package_id']])) {

                            $date1 = new DateTime($toup['date_updated']);
                            $date2 = new DateTime(date("Y-m-d H:i:s", time()));

                            $diff = $date2->diff($date1)->format("%a");
                            $remaining = $toup['days'];

                            if ($remaining < 0) {
                                $remaining = 0;
                            }

                            if ($toup['minecraft_product_group_id'] > 0) {
                                $count = $this->model_minecraft_active->getGroupCountPlayerPackage($uu, $toup['minecraft_product_group_id'], $sv);
                            } else {
                                $count = 1;
                            }
                            $current = 1;

                            //Se a diferença possuir saldo positivo
                            if (($remaining - $diff) > 0) {
                                //Atualiza para o novo saldo positivo
                                if ($this->model_minecraft_active->update($toup['minecraft_account_package_id'], ["days" => ($remaining - $diff)])) {
                                    $update_list[$toup['minecraft_account_package_id']] = $toup;
                                }
                            } else {
                                //Enquanto a diferença for negativa OU a diferença for negativa e ter mais produtos ativos do grupo
                                while ((($remaining - $diff) < 0) || (($count >= $current) && ($remaining - $diff <= 0))) {
                                    //Remove o produto
                                    if (!$this->model_minecraft_active->removePlayerPackage($uu, $toup['minecraft_account_package_id'], $sv)) {
                                        //Cancela os loops. Houve falha na retirada do produto.
                                        break 2;
                                    } else {
                                        $remove_list[$toup['minecraft_account_package_id']] = $toup;
                                    }
                                    //Se a diferença for negativa (diferente de 0)
                                    if ($remaining - $diff < 0) {

                                        //Pega o próximo item ativo do grupo
                                        if ($toup['minecraft_product_group_id'] > 0 && ($count > $current)) {
                                            $toup = $this->model_minecraft_active->getFirstGroupPlayerPackage($uu, $toup['minecraft_product_group_id'], $sv);
                                            if (empty($toup)) {
                                                //Cancela o loop se não tiver outros itens do grupo
                                                break;
                                            }

                                            $diff = abs($remaining - $diff);
                                            $remaining = $toup['days'];

                                            if ($remaining < 0) {
                                                $remaining = 0;
                                            }

                                            $current++;

                                            //Se o saldo for positivo, atualiza os dias do item de mesmo grupo
                                            if (($remaining - $diff) > 0) {
                                                if ($this->model_minecraft_active->update($toup['minecraft_account_package_id'], ["days" => ($remaining - $diff)])) {
                                                    $update_list[$toup['minecraft_account_package_id']] = $toup;
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                        }

                        $item_list[$toup['minecraft_account_package_id']] = true;
                    }

                    // Response
                    foreach ($update_list as $ul) {
                        $item = array(
                            "product_id"   => (int)$ul['product_id'],
                            "product_name" => $ul['name']
                        );
                        $response["updated"][] = $item;
                    }
                    foreach ($remove_list as $rl) {
                        $item = array(
                            "product_id"   => (int)$rl['product_id'],
                            "product_name" => $rl['name']
                        );
                        $response["removed"][] = $item;
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
