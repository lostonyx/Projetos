<?php

class ControllerCommonCurrency extends Controller {

    public function index() {

        if ($this->config->get("config_manual_currency") == false) {
            return false;
        }

        $this->load->language('common/currency');

        $data['text_currency'] = $this->language->get('text_currency');

        $data['action'] = $this->url->link('common/currency/currency', '', $this->request->server['HTTPS']);

        $data['code'] = $this->currency->getCode();

        $this->load->model('localisation/currency');

        $data['currencies'] = array();

        $results = $this->model_localisation_currency->getCurrencies();

        foreach ($results as $result) {
            if ($result['status']) {
                $data['currencies'][] = array(
                    'title' => $result['title'],
                    'code' => $result['code'],
                    'symbol_left' => $result['symbol_left'],
                    'symbol_right' => $result['symbol_right']
                );
            }
        }

        if (!isset($this->request->get['route'])) {
            $data['redirect'] = $this->url->link('common/home');
        } else {
            $url_data = $this->request->get;

            unset($url_data['_route_']);

            $route = $url_data['route'];

            unset($url_data['route']);

            $url = '';

            if ($url_data) {
                $url = '&' . urldecode(http_build_query($url_data, '', '&'));
            }

            $data['redirect'] = $this->url->link($route, $url, $this->request->server['HTTPS']);
        }

        $data['href'] = urlencode($_SERVER['REQUEST_URI']);

        if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/common/currency.tpl')) {
            return $this->load->view($this->config->get('config_template') . '/template/common/currency.tpl', $data);
        } else {
            return $this->load->view('/default/template/common/currency.tpl', $data);
        }
    }

    public function currency() {
        if (isset($this->request->get['code'])) {
            $this->currency->set($this->request->get['code']);
        }

        if (isset($this->request->get['redirect'])) {
            $this->response->redirect($this->request->get['redirect']);
        } else {
            $this->response->redirect($this->url->link('common/home'));
        }
    }

}
