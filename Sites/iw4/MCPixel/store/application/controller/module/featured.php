<?php

class ControllerModuleFeatured extends Controller {

    public function index($setting = array()) {
        $this->load->language('module/featured');

        $setting['heading_title'] = $this->language->get('heading_title');

        $setting['button_close'] = $this->language->get('button_close');
        $setting['button_more'] = $this->language->get('button_more');

        $this->load->model('catalog/product');

        $this->load->model('tool/image');

        $setting['products'] = array();

        if (!isset($setting['featured_limit'])) {
            $setting['featured_limit'] = $this->config->get("featured_limit");
        }

        if (!isset($setting['featured_name'])) {
            $setting['featured_name'] = $this->config->get("featured_name");
        }

        // Produtos
        $this->load->model('catalog/product');

        $products = $this->model_catalog_product->getPopularProducts($setting['featured_limit']);

        $setting['products'] = array();

        foreach ($products as $product) {
            if ($product['convert'] == true && $this->currency->getCode() !== $this->config->get("config_currency")) {
                $price = $this->currency->format($product['price']);
            } else {
                $price = $this->currency->format($product['price'], $this->currency->getCode(), 1);
            }
            $special = false;
            if (!empty($product['special'])) {
                if ($product['convert'] == true && $this->currency->getCode() !== $this->config->get("config_currency")) {
                    $special = $this->currency->format($product['special']);
                } else {
                    $special = $this->currency->format($product['special'], $this->currency->getCode(), 1);
                }
            }
            $pd = array(
                'id' => $product['product_id'],
                'thumb' => $product['image'],
                'name' => $product['name'],
                'short_description' => utf8_substr(strip_tags(html_entity_decode($product['short_description'], ENT_QUOTES, 'UTF-8')), 0, $this->config->get('config_product_lang_length')) . ' ..',
                'long_description' => html_entity_decode($product['long_description'], ENT_QUOTES, 'UTF-8'),
                'price' => ($special) ? $special : $price,
                'price_special' => $price,
                'href' => $this->url->link('checkout/cart/add', 'product=' . $product['product_id'])
            );
            if ($special) {
                $pd['price_special'] = '<s><small>'.$pd['price_special'].'</small></s> '.$special;
            }
            $setting['products'][] = $pd;
        }

        if ($setting['products']) {
            if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/module/featured.tpl')) {
                return $this->load->view($this->config->get('config_template') . '/template/module/featured.tpl', $setting);
            } else {
                return $this->load->view('/default/template/module/featured.tpl', $setting);
            }
        }
    }

}
