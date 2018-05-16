<?php

class ControllerCommonHeader extends Controller {

    public function index($data = array()) {
        // Analytics
        $this->load->model('extension/extension');

        $data['analytics'] = array();

        $analytics = $this->model_extension_extension->getExtensions('analytics');

        foreach ($analytics as $analytic) {
            if ($this->config->get($analytic['code'] . '_status')) {
                $data['analytics'][] = $this->load->controller('analytics/' . $analytic['code']);
            }
        }

        if ($this->request->server['HTTPS']) {
            $server = $this->config->get('config_ssl');
        } else {
            $server = $this->config->get('config_url');
        }

        if (is_file(DIR_IMAGE . $this->config->get('config_icon'))) {
            $this->document->addLink($server . 'image/' . $this->config->get('config_icon'), 'icon');
        }

        $data['title'] = $this->document->getTitle();

        $data['base'] = $server;
        $data['description'] = $this->document->getDescription();
        $data['keywords'] = $this->document->getKeywords();
        $data['links'] = $this->document->getLinks();
        $data['styles'] = $this->document->getStyles();
        $data['scripts'] = $this->document->getScripts();
        $data['lang'] = $this->language->get('code');
        $data['direction'] = $this->language->get('direction');

        $data['name'] = $this->config->get('config_name');

        if (is_file(DIR_IMAGE . $this->config->get('config_logo'))) {
            $data['logo'] = $server . 'image/' . $this->config->get('config_logo');
        } else {
            $data['logo'] = '';
        }

        $this->load->language('common/header');

        $data['text_home'] = $this->language->get('text_home');

        $data['text_shopping_cart'] = $this->language->get('text_shopping_cart');
        $data['text_logged'] = sprintf($this->language->get('text_logged'), $this->url->link('account/view', '', 'SSL'), $this->customer->getFirstName(), $this->url->link('account/logout', '', 'SSL'));

        $data['text_account'] = $this->language->get('text_account');
        $data['text_register'] = $this->language->get('text_register');
        $data['text_login'] = $this->language->get('text_login');
        $data['text_logout'] = $this->language->get('text_logout');
        
        $data['home'] = $this->url->link('common/home');
        $data['logged'] = $this->customer->isLogged();
        $data['customer'] = $this->customer->getFirstName();
        $data['login'] = $this->url->link('account/login', '', 'SSL');
        $data['logout'] = $this->url->link('account/logout', '', 'SSL');

        $status = true;

        if (isset($this->request->server['HTTP_USER_AGENT'])) {
            $robots = explode("\n", str_replace(array("\r\n", "\r"), "\n", trim($this->config->get('config_robots'))));

            foreach ($robots as $robot) {
                if ($robot && strpos($this->request->server['HTTP_USER_AGENT'], trim($robot)) !== false) {
                    $status = false;

                    break;
                }
            }
        }

        $data['language'] = $this->load->controller('common/language');
        $data['currency'] = $this->load->controller('common/currency');

        // Current Menu
        $this->load->model('catalog/category');

        if (!isset($data['current_menu']) || $data['current_menu'] === true) {

            $current_category = (isset($this->request->get['category'])) ? intval($this->request->get['category']) : false;
            $current_category = $this->model_catalog_category->getCategory($current_category);

            if (!$current_category) {
                $current_category = $this->model_catalog_category->getFirstCategory();
            }

            $this->registry->set('current', chk_array($current_category, 'category_id'));
        } else {
            $this->registry->set('current', 0);
        }

        // Menu List
        $data['categories'] = array();

        $categories = $this->model_catalog_category->getCategories(0);

        foreach ($categories as $category) {
            // Level 1
            $data['categories'][] = array(
                'name' => $category['name'],
                'href' => $this->url->link('common/home', 'category=' . $category['category_id']),
                'active' => ($this->registry->get('current') === $category['category_id']) ? true : false
            );
        }

        // Cart
        $cart = array();
        $cart['total'] = $this->cart->countProducts();
        
        if ($cart['total'] > 1) {
            $cart['text'] = sprintf($this->language->get("item_multiple"), $this->currency->format($this->cart->getTotal(), $this->currency->getCode(), 1));
        } else {
            $cart['text'] = sprintf($this->language->get("item_single"), $this->currency->format($this->cart->getTotal(), $this->currency->getCode(), 1));
        }

        $data['cart'] = $cart;

        // For page specific css
        if (isset($this->request->get['route'])) {
            $class = '';
            $data['class'] = str_replace('/', '-', $this->request->get['route']) . $class;
        } else {
            $data['class'] = 'common-home';
        }

        // Template
        if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/common/header.tpl')) {
            return $this->load->view($this->config->get('config_template') . '/template/common/header.tpl', $data);
        } else {
            return $this->load->view('/default/template/common/header.tpl', $data);
        }
    }

}
