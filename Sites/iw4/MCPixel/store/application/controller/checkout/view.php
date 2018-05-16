<?php

class ControllerCheckoutView extends Controller {

    public function index() {

        $this->document->setTitle($this->config->get('config_meta_title'));
        $this->document->setDescription($this->config->get('config_meta_lang'));
        $this->document->setKeywords($this->config->get('config_meta_keyword'));

        if (isset($this->request->get['route'])) {
            $this->document->addLink($this->config->get('config_ssl'), 'canonical');

            // Produtos

            $data['products'] = array();
            $products = $this->cart->getProducts();

            foreach ($products as $product) {
                $data['products'][] = array(
                    "cart_id"    => $product['cart_id'],
                    "product_id" => $product['product_id'],
                    "image"      => $product['image'],
                    "name"       => $product['name'],
                    "quantity"   => $product['quantity'],
                    "price"      => $this->currency->format($product['price'] * $product['quantity'], $this->currency->getCode(), 1)
                );
            }

            if (isset($this->session->data['error'])) {
                $data['error_warning'] = $this->session->data['error'];
                unset($this->session->data['error']);
            } else {
                $data['error_warning'] = '';
            }

            $data['logged'] = $this->customer->isLogged();

            if (isset($this->session->data['account'])) {
                $data['account'] = $this->session->data['account'];
            } else {
                $data['account'] = '';
            }

            $this->load->language('checkout/cart');
            $this->load->language('checkout/view');
        }

        if ($this->config->get('config_customer_price') && !$this->customer->isLogged()) {
            $data['attention'] = sprintf($this->language->get('text_login'), $this->url->link('account/login'), $this->url->link('account/register'));
        } else {
            $data['attention'] = '';
        }


        $this->document->addScript('application/resource/javascript/jquery/datetimepicker/moment.js');
        $this->document->addScript('application/resource/javascript/jquery/datetimepicker/bootstrap-datetimepicker.min.js');
        $this->document->addStyle('application/resource/javascript/jquery/datetimepicker/bootstrap-datetimepicker.min.css');

        $data['text_checkout_account'] = $this->language->get('text_checkout_account');
        $data['text_checkout_option'] = $this->language->get('text_checkout_option');
        $data['text_checkout_payment_address'] = $this->language->get('text_checkout_payment_address');
        $data['text_checkout_payment_method'] = $this->language->get('text_checkout_payment_method');
        $data['text_checkout_confirm'] = $this->language->get('text_checkout_confirm');
        $data['text_next'] = $this->language->get('text_next');
        $data['text_next_choice'] = $this->language->get('text_next_choice');

        $data['button_clear'] = $this->language->get('button_clear');
        $data['button_remove'] = $this->language->get('button_remove');

        $data['column_image'] = $this->language->get('column_image');
        $data['column_name'] = $this->language->get('column_name');
        $data['column_quantity'] = $this->language->get('column_quantity');
        $data['column_total'] = $this->language->get('column_total');
        $data['column_option'] = $this->language->get('column_option');

        $data['error_no_product'] = $this->language->get('error_no_product');

        $data['header'] = $this->load->controller('common/header', array("current_menu" => false));
        $data['column_left'] = $this->load->controller('common/column_left');
        $data['content_top'] = $this->load->controller('common/content_top');
        $data['content_bottom'] = $this->load->controller('common/content_bottom');
        $data['column_right'] = $this->load->controller('common/column_right');
        $data['footer'] = $this->load->controller('common/footer');

        if (isset($this->session->data['success'])) {
            $data['success'] = $this->session->data['success'];

            unset($this->session->data['success']);
        } else {
            $data['success'] = '';
        }

        // Totals
        $this->load->model('extension/extension');

        $total_data = array();
        $total = 0;

        // Display prices
        if (($this->config->get('config_customer_price') && $this->customer->isLogged()) || !$this->config->get('config_customer_price')) {
            $sort_order = array();

            $results = $this->model_extension_extension->getExtensions('total');

            foreach ($results as $key => $value) {
                $sort_order[$key] = $this->config->get($value['code'] . '_sort_order');
            }

            array_multisort($sort_order, SORT_ASC, $results);

            foreach ($results as $result) {
                if ($this->config->get($result['code'] . '_status')) {
                    $this->load->model('total/' . $result['code']);

                    $this->{'model_total_' . $result['code']}->getTotal($total_data, $total);
                }
            }

            $sort_order = array();

            foreach ($total_data as $key => $value) {
                $sort_order[$key] = $value['sort_order'];
            }

            array_multisort($sort_order, SORT_ASC, $total_data);
        }

        $data['totals'] = array();

        foreach ($total_data as $total) {
            $data['totals'][] = array(
                'title' => $total['title'],
                'text'  => $this->currency->format($total['value'], $this->currency->getCode(), 1)
            );
        }

        $files = glob(DIR_APPLICATION . '/controller/total/*.php');

        if ($files) {
            foreach ($files as $file) {
                $extension = basename($file, '.php');

                $data[$extension] = $this->load->controller('total/' . $extension);
            }
        }

        if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/checkout/view.tpl')) {
            $this->response->setOutput($this->load->view($this->config->get('config_template') . '/template/checkout/view.tpl', $data));
        } else {
            $this->response->setOutput($this->load->view('/default/template/checkout/view.tpl', $data));
        }
    }

    public function country() {
        $json = array();

        $this->load->model('localisation/country');

        $country_info = $this->model_localisation_country->getCountry($this->request->get['country_id']);

        if ($country_info) {
            $this->load->model('localisation/zone');

            $json = array(
                'country_id'        => $country_info['country_id'],
                'name'              => $country_info['name'],
                'iso_code_2'        => $country_info['iso_code_2'],
                'iso_code_3'        => $country_info['iso_code_3'],
                'address_format'    => $country_info['address_format'],
                'postcode_required' => $country_info['postcode_required'],
                'zone'              => $this->model_localisation_zone->getZonesByCountryId($this->request->get['country_id']),
                'status'            => $country_info['status']
            );
        }

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }

    public function customfield() {
        $json = array();

        $this->load->model('account/custom_field');

        // Customer Group
        if (isset($this->request->get['customer_group_id']) && is_array($this->config->get('config_customer_group_display')) && in_array($this->request->get['customer_group_id'], $this->config->get('config_customer_group_display'))) {
            $customer_group_id = $this->request->get['customer_group_id'];
        } else {
            $customer_group_id = $this->config->get('config_customer_group_id');
        }

        $custom_fields = $this->model_account_custom_field->getCustomFields($customer_group_id);

        foreach ($custom_fields as $custom_field) {
            $json[] = array(
                'custom_field_id' => $custom_field['custom_field_id'],
                'required'        => $custom_field['required']
            );
        }

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }

}
