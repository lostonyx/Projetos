<?php

class ControllerDashboardMap extends Controller {
    public function index() {
        if (!$this->user->hasPermission('access', 'dashboard/map')) {
            return false;
        }

        $this->load->language('dashboard/map');

        $data['heading_title'] = $this->language->get('heading_title');

        $data['text_order'] = $this->language->get('text_order');
        $data['text_sale'] = $this->language->get('text_sale');

        $data['token'] = $this->session->data['token'];

        return $this->load->view('dashboard/map.tpl', $data);
    }

    public function map() {
        if (!$this->user->hasPermission('access', 'dashboard/map')) {
            return false;
        }

        $json = array();

        $this->load->model('report/sale');

        $results = $this->model_report_sale->getTotalOrdersByCountry(array("status" => $this->config->get("config_complete_status")));

        foreach ($results as $result) {
            $json[strtolower($result['iso_code_2'])] = array(
                'total'  => $result['total'],
                'amount' => $this->currency->format($result['amount'], $this->config->get('currency_code'))
            );
        }

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }
}