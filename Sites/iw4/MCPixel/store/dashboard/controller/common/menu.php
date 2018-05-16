<?php

class ControllerCommonMenu extends Controller {

    public function index() {
        $this->load->language('common/menu');

        $data['text_analytics'] = $this->language->get('text_analytics');
        $data['text_api'] = $this->language->get('text_api');
        $data['text_attribute'] = $this->language->get('text_attribute');
        $data['text_attribute_group'] = $this->language->get('text_attribute_group');
        $data['text_backup'] = $this->language->get('text_backup');
        $data['text_captcha'] = $this->language->get('text_captcha');
        $data['text_catalog'] = $this->language->get('text_catalog');
        $data['text_category'] = $this->language->get('text_category');
        $data['text_confirm'] = $this->language->get('text_confirm');
        $data['text_contact'] = $this->language->get('text_contact');
        $data['text_country'] = $this->language->get('text_country');
        $data['text_coupon'] = $this->language->get('text_coupon');
        $data['text_currency'] = $this->language->get('text_currency');
        $data['text_customer'] = $this->language->get('text_customer');
        $data['text_customer_group'] = $this->language->get('text_customer_group');
        $data['text_customer_field'] = $this->language->get('text_customer_field');
        $data['text_custom_field'] = $this->language->get('text_custom_field');
        $data['text_sale'] = $this->language->get('text_sale');
        $data['text_module'] = $this->language->get('text_module');
        $data['text_paypal'] = $this->language->get('text_paypal');
        $data['text_paypal_search'] = $this->language->get('text_paypal_search');
        $data['text_design'] = $this->language->get('text_design');
        $data['text_error_log'] = $this->language->get('text_error_log');
        $data['text_extension'] = $this->language->get('text_extension');
        $data['text_filter'] = $this->language->get('text_filter');
        $data['text_geo_zone'] = $this->language->get('text_geo_zone');
        $data['text_dashboard'] = $this->language->get('text_dashboard');
        $data['text_help'] = $this->language->get('text_help');
        $data['text_information'] = $this->language->get('text_information');
        $data['text_installer'] = $this->language->get('text_installer');
        $data['text_language'] = $this->language->get('text_language');
        $data['text_layout'] = $this->language->get('text_layout');
        $data['text_localisation'] = $this->language->get('text_localisation');
        $data['text_location'] = $this->language->get('text_location');
        $data['text_marketing'] = $this->language->get('text_marketing');
        $data['text_modification'] = $this->language->get('text_modification');
        $data['text_option'] = $this->language->get('text_option');
        $data['text_order'] = $this->language->get('text_order');
        $data['text_order_status'] = $this->language->get('text_order_status');
        $data['text_order_recurring'] = $this->language->get('text_order_recurring');
        $data['text_iwnetwork_store'] = $this->language->get('text_iwnetwork_store');
        $data['text_payment'] = $this->language->get('text_payment');
        $data['text_product'] = $this->language->get('text_product');
        $data['text_reports'] = $this->language->get('text_reports');
        $data['text_report_sale_order'] = $this->language->get('text_report_sale_order');
        $data['text_report_sale_coupon'] = $this->language->get('text_report_sale_coupon');
        $data['text_report_product_viewed'] = $this->language->get('text_report_product_viewed');
        $data['text_report_product_purchased'] = $this->language->get('text_report_product_purchased');
        $data['text_report_customer_activity'] = $this->language->get('text_report_customer_activity');
        $data['text_report_customer_online'] = $this->language->get('text_report_customer_online');
        $data['text_report_customer_order'] = $this->language->get('text_report_customer_order');
        $data['text_report_customer_credit'] = $this->language->get('text_report_customer_credit');
        $data['text_report_customer_order'] = $this->language->get('text_report_customer_order');
        $data['text_review'] = $this->language->get('text_review');
        $data['text_return'] = $this->language->get('text_return');
        $data['text_return_action'] = $this->language->get('text_return_action');
        $data['text_return_reason'] = $this->language->get('text_return_reason');
        $data['text_return_status'] = $this->language->get('text_return_status');
        $data['text_setting'] = $this->language->get('text_setting');
        $data['text_system'] = $this->language->get('text_system');
        $data['text_tools'] = $this->language->get('text_tools');
        $data['text_total'] = $this->language->get('text_total');
        $data['text_upload'] = $this->language->get('text_upload');
        $data['text_tracking'] = $this->language->get('text_tracking');
        $data['text_user'] = $this->language->get('text_user');
        $data['text_user_group'] = $this->language->get('text_user_group');
        $data['text_users'] = $this->language->get('text_users');
        $data['text_zone'] = $this->language->get('text_zone');
        $data['text_recurring'] = $this->language->get('text_recurring');
        $data['text_server'] = $this->language->get('text_server');

        $data['analytics'] = $this->url->link('dashboard/extension/analytics', 'token=' . $this->session->data['token'], 'SSL');
        $data['home'] = $this->url->link('dashboard/common/dashboard', 'token=' . $this->session->data['token'], 'SSL');
        $data['api'] = $this->url->link('dashboard/user/api', 'token=' . $this->session->data['token'], 'SSL');
        $data['attribute'] = $this->url->link('dashboard/catalog/attribute', 'token=' . $this->session->data['token'], 'SSL');
        $data['attribute_group'] = $this->url->link('dashboard/catalog/attribute_group', 'token=' . $this->session->data['token'], 'SSL');
        $data['backup'] = $this->url->link('dashboard/tool/backup', 'token=' . $this->session->data['token'], 'SSL');
        $data['captcha'] = $this->url->link('dashboard/extension/captcha', 'token=' . $this->session->data['token'], 'SSL');
        $data['category'] = $this->url->link('dashboard/catalog/category', 'token=' . $this->session->data['token'], 'SSL');
        $data['server'] = $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'], 'SSL');
        $data['country'] = $this->url->link('dashboard/localisation/country', 'token=' . $this->session->data['token'], 'SSL');
        $data['contact'] = $this->url->link('dashboard/marketing/contact', 'token=' . $this->session->data['token'], 'SSL');
        $data['coupon'] = $this->url->link('dashboard/marketing/coupon', 'token=' . $this->session->data['token'], 'SSL');
        $data['currency'] = $this->url->link('dashboard/localisation/currency', 'token=' . $this->session->data['token'], 'SSL');
        $data['customer'] = $this->url->link('dashboard/customer/customer', 'token=' . $this->session->data['token'], 'SSL');
        $data['customer_fields'] = $this->url->link('dashboard/customer/customer_field', 'token=' . $this->session->data['token'], 'SSL');
        $data['customer_group'] = $this->url->link('dashboard/customer/customer_group', 'token=' . $this->session->data['token'], 'SSL');
        $data['custom_field'] = $this->url->link('dashboard/customer/custom_field', 'token=' . $this->session->data['token'], 'SSL');
        $data['error_log'] = $this->url->link('dashboard/tool/error_log', 'token=' . $this->session->data['token'], 'SSL');
        $data['filter'] = $this->url->link('dashboard/catalog/filter', 'token=' . $this->session->data['token'], 'SSL');
        $data['geo_zone'] = $this->url->link('dashboard/localisation/geo_zone', 'token=' . $this->session->data['token'], 'SSL');
        $data['information'] = $this->url->link('dashboard/catalog/information', 'token=' . $this->session->data['token'], 'SSL');
        $data['installer'] = $this->url->link('dashboard/extension/installer', 'token=' . $this->session->data['token'], 'SSL');
        $data['language'] = $this->url->link('dashboard/localisation/language', 'token=' . $this->session->data['token'], 'SSL');
        $data['layout'] = $this->url->link('dashboard/design/layout', 'token=' . $this->session->data['token'], 'SSL');
        $data['location'] = $this->url->link('dashboard/localisation/location', 'token=' . $this->session->data['token'], 'SSL');
        $data['modification'] = $this->url->link('dashboard/extension/modification', 'token=' . $this->session->data['token'], 'SSL');
        $data['marketing'] = $this->url->link('dashboard/marketing/marketing', 'token=' . $this->session->data['token'], 'SSL');
        $data['module'] = $this->url->link('dashboard/extension/module', 'token=' . $this->session->data['token'], 'SSL');
        $data['option'] = $this->url->link('dashboard/catalog/option', 'token=' . $this->session->data['token'], 'SSL');
        $data['order'] = $this->url->link('dashboard/sale/order', 'token=' . $this->session->data['token'], 'SSL');
        $data['order_status'] = $this->url->link('dashboard/localisation/order_status', 'token=' . $this->session->data['token'], 'SSL');
        $data['payment'] = $this->url->link('dashboard/extension/payment', 'token=' . $this->session->data['token'], 'SSL');
        $data['paypal_search'] = $this->url->link('dashboard/payment/pp_express/search', 'token=' . $this->session->data['token'], 'SSL');
        $data['product'] = $this->url->link('dashboard/catalog/product', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_sale_order'] = $this->url->link('dashboard/report/sale_order', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_sale_coupon'] = $this->url->link('dashboard/report/sale_coupon', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_product_viewed'] = $this->url->link('dashboard/report/product_viewed', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_product_purchased'] = $this->url->link('dashboard/report/product_purchased', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_customer_activity'] = $this->url->link('dashboard/report/customer_activity', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_customer_online'] = $this->url->link('dashboard/report/customer_online', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_customer_order'] = $this->url->link('dashboard/report/customer_order', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_customer_credit'] = $this->url->link('dashboard/report/customer_credit', 'token=' . $this->session->data['token'], 'SSL');
        $data['report_marketing'] = $this->url->link('dashboard/report/marketing', 'token=' . $this->session->data['token'], 'SSL');
        $data['setting'] = $this->url->link('dashboard/setting/store', 'token=' . $this->session->data['token'], 'SSL');
        $data['total'] = $this->url->link('dashboard/extension/total', 'token=' . $this->session->data['token'], 'SSL');
        $data['upload'] = $this->url->link('dashboard/tool/upload', 'token=' . $this->session->data['token'], 'SSL');
        $data['user'] = $this->url->link('dashboard/user/user', 'token=' . $this->session->data['token'], 'SSL');
        $data['user_group'] = $this->url->link('dashboard/user/user_permission', 'token=' . $this->session->data['token'], 'SSL');
        $data['zone'] = $this->url->link('dashboard/localisation/zone', 'token=' . $this->session->data['token'], 'SSL');
        $data['recurring'] = $this->url->link('dashboard/catalog/recurring', 'token=' . $this->session->data['token'], 'SSL');
        $data['order_recurring'] = $this->url->link('dashboard/sale/recurring', 'token=' . $this->session->data['token'], 'SSL');

        $data['openbay_markets'] = array(
            'ebay' => $this->config->get('ebay_status'),
            'amazon' => $this->config->get('openbay_amazon_status'),
            'amazonus' => $this->config->get('openbay_amazonus_status'),
            'etsy' => $this->config->get('etsy_status'),
        );

        return $this->load->view('common/menu.tpl', $data);
    }

}
