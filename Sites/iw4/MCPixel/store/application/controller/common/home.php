<?php

class ControllerCommonHome extends Controller {

    public function index() {
        $this->document->setTitle($this->config->get('config_meta_title'));
        $this->document->setDescription($this->config->get('config_meta_lang'));
        $this->document->setKeywords($this->config->get('config_meta_keyword'));

        if (isset($this->request->get['route'])) {
            $this->document->addLink($this->config->get('config_ssl'), 'canonical');
        }

        $data['header'] = $this->load->controller('common/header');
        $data['column_left'] = $this->load->controller('common/column_left');
        $data['content_top'] = $this->load->controller('common/content_top');
        $data['content_bottom'] = $this->load->controller('common/content_bottom');
        $data['column_right'] = $this->load->controller('common/column_right');
        $data['footer'] = $this->load->controller('common/footer');

        $this->load->language("common/home");
        
        $data['button_close'] = $this->language->get('button_close');
        $data['button_more'] = $this->language->get('button_more');
        $data['text_not_found'] = $this->language->get('text_not_found');

        if ($this->registry->get("current")) {

            // Produtos
            $this->load->model('catalog/product');

            $filter = array(
                "filter_category_id" => $this->registry->get("current")
            );

            if (isset($this->request->get['q'])) {
                $filter += array("filter_name" => $this->request->get['q']);
            }

            $products = $this->model_catalog_product->getProducts($filter);

            $data['products'] = array();

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
                $data['products'][] = $pd;
            }
        } else {
            $data['products'] = array();
        }

        if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/common/home.tpl')) {
            $this->response->setOutput($this->load->view($this->config->get('config_template') . '/template/common/home.tpl', $data));
        } else {
            $this->response->setOutput($this->load->view('/default/template/common/home.tpl', $data));
        }
    }

}
