<?php

class ModelCheckoutOrder extends Model {

    public function addOrder($data) {
        $this->event->trigger('pre.order.add', $data);

        $this->db->query(""
                . "INSERT INTO " . DB_PREFIX . "order "
                . "SET invoice_prefix = '" . $this->db->escape($data['invoice_prefix']) . "', "
                . "store_name = '" . $this->db->escape($data['store_name']) . "', "
                . "store_url = '" . $this->db->escape($data['store_url']) . "', "
                . "customer_id = '" . (int) $data['customer_id'] . "', "
                . "customer_group_id = '" . (int) $data['customer_group_id'] . "', "
                . "firstname = '" . $this->db->escape($data['firstname']) . "', "
                . "lastname = '" . $this->db->escape($data['lastname']) . "', "
                . "email = '" . $this->db->escape($data['email']) . "', "
                . "telephone = '" . $this->db->escape($data['telephone']) . "', "
                . "fax = '" . $this->db->escape($data['fax']) . "', "
                . "custom_field = '" . $this->db->escape(isset($data['custom_field']) ? json_encode($data['custom_field']) : '') . "', "
                . "payment_firstname = '" . $this->db->escape($data['payment_firstname']) . "', "
                . "payment_lastname = '" . $this->db->escape($data['payment_lastname']) . "', "
                . "payment_company = '" . $this->db->escape($data['payment_company']) . "', "
                . "payment_address_1 = '" . $this->db->escape($data['payment_address_1']) . "', "
                . "payment_address_2 = '" . $this->db->escape($data['payment_address_2']) . "', "
                . "payment_city = '" . $this->db->escape($data['payment_city']) . "', "
                . "payment_postcode = '" . $this->db->escape($data['payment_postcode']) . "', "
                . "payment_country = '" . $this->db->escape($data['payment_country']) . "', "
                . "payment_country_id = '" . (int) $data['payment_country_id'] . "', "
                . "payment_zone = '" . $this->db->escape($data['payment_zone']) . "', "
                . "payment_zone_id = '" . (int) $data['payment_zone_id'] . "', "
                . "payment_address_format = '" . $this->db->escape($data['payment_address_format']) . "', "
                . "payment_custom_field = '" . $this->db->escape(isset($data['payment_custom_field']) ? json_encode($data['payment_custom_field']) : '') . "', "
                . "payment_method = '" . $this->db->escape($data['payment_method']) . "', "
                . "payment_code = '" . $this->db->escape($data['payment_code']) . "', "
                . "total = '" . (float) $data['total'] . "', "
                . "marketing_id = '" . ($this->db->escape(chk_array($data, 'marketing_id'))) . "', "
                . "tracking = '" . ($this->db->escape(chk_array($data, 'tracking'))) . "', "
                . "language_id = '" . (int) $data['language_id'] . "', "
                . "currency_id = '" . (int) $data['currency_id'] . "', "
                . "currency_code = '" . $this->db->escape($data['currency_code']) . "', "
                . "currency_value = '" . (float) $data['currency_value'] . "', "
                . "ip = '" . $this->db->escape($data['ip']) . "', "
                . "forwarded_ip = '" . $this->db->escape($data['forwarded_ip']) . "', "
                . "user_agent = '" . $this->db->escape($data['user_agent']) . "', "
                . "accept_language = '" . $this->db->escape($data['accept_language']) . "', "
                . "date_added = NOW(), date_updated = NOW()");

        $order_id = $this->db->getLastId();

        // Products
        if (isset($data['products'])) {
            foreach ($data['products'] as $product) {

                $this->db->query(""
                        . "INSERT INTO " . DB_PREFIX . "order_product "
                        . "SET order_id = '" . (int) $order_id . "', "
                        . "product_id = '" . (int) $product['product_id'] . "', "
                        . "name = '" . $this->db->escape($product['name']) . "', "
                        . "quantity = '" . (int) $product['quantity'] . "', "
                        . "duration = '" . (int) $product['duration'] . "', "
                        . "lifetime = '" . (int) $product['lifetime'] . "', "
                        . "price = '" . (float) $product['price'] . "', "
                        . "total = '" . (float) $product['total'] . "' ");
            }
        }

        // Totals
        if (isset($data['totals'])) {
            foreach ($data['totals'] as $total) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "order_total SET order_id = '" . (int) $order_id . "', code = '" . $this->db->escape($total['code']) . "', title = '" . $this->db->escape($total['title']) . "', value = '" . (float) $total['value'] . "', sort_order = '" . (int) $total['sort_order'] . "'");
            }
        }

        $this->event->trigger('post.order.add', $order_id);

        return $order_id;
    }

    public function editOrder($order_id, $data) {
        $this->event->trigger('pre.order.edit', $data);

        // Void the order first
        $this->addOrderHistory($order_id, 0);

        $this->db->query(""
                . "UPDATE " . DB_PREFIX . "order "
                . "SET invoice_prefix = '" . $this->db->escape($data['invoice_prefix']) . "', "
                . "store_name = '" . $this->db->escape($data['store_name']) . "', "
                . "store_url = '" . $this->db->escape($data['store_url']) . "', "
                . "customer_id = '" . (int) $data['customer_id'] . "', "
                . "customer_group_id = '" . (int) $data['customer_group_id'] . "', "
                . "firstname = '" . $this->db->escape($data['firstname']) . "', "
                . "lastname = '" . $this->db->escape($data['lastname']) . "', "
                . "email = '" . $this->db->escape($data['email']) . "', "
                . "telephone = '" . $this->db->escape($data['telephone']) . "', "
                . "fax = '" . $this->db->escape($data['fax']) . "', "
                . "custom_field = '" . $this->db->escape(isset($data['custom_field']) ? json_encode($data['custom_field']) : '') . "', "
                . "payment_firstname = '" . $this->db->escape($data['payment_firstname']) . "', "
                . "payment_lastname = '" . $this->db->escape($data['payment_lastname']) . "', "
                . "payment_company = '" . $this->db->escape($data['payment_company']) . "', "
                . "payment_address_1 = '" . $this->db->escape($data['payment_address_1']) . "', "
                . "payment_address_2 = '" . $this->db->escape($data['payment_address_2']) . "', "
                . "payment_city = '" . $this->db->escape($data['payment_city']) . "', "
                . "payment_postcode = '" . $this->db->escape($data['payment_postcode']) . "', "
                . "payment_country = '" . $this->db->escape($data['payment_country']) . "', "
                . "payment_country_id = '" . (int) $data['payment_country_id'] . "', "
                . "payment_zone = '" . $this->db->escape($data['payment_zone']) . "', "
                . "payment_zone_id = '" . (int) $data['payment_zone_id'] . "', "
                . "payment_address_format = '" . $this->db->escape($data['payment_address_format']) . "', "
                . "payment_custom_field = '" . $this->db->escape(isset($data['payment_custom_field']) ? json_encode($data['payment_custom_field']) : '') . "', "
                . "payment_method = '" . $this->db->escape($data['payment_method']) . "', "
                . "payment_code = '" . $this->db->escape($data['payment_code']) . "', "
                . "total = '" . (float) $data['total'] . "', "
                . "marketing_id = '" . ($this->db->escape(chk_array($data, 'marketing_id'))) . "', "
                . "tracking = '" . ($this->db->escape(chk_array($data, 'tracking'))) . "', "
                . "language_id = '" . (int) $data['language_id'] . "', "
                . "currency_id = '" . (int) $data['currency_id'] . "', "
                . "currency_code = '" . $this->db->escape($data['currency_code']) . "', "
                . "currency_value = '" . (float) $data['currency_value'] . "', "
                . "ip = '" . $this->db->escape($data['ip']) . "', "
                . "forwarded_ip = '" . $this->db->escape($data['forwarded_ip']) . "', "
                . "user_agent = '" . $this->db->escape($data['user_agent']) . "', "
                . "accept_language = '" . $this->db->escape($data['accept_language']) . "', "
                . "date_added = NOW(), date_updated = NOW() 
				WHERE order_id = '" . (int) $order_id . "'");

        // Totals
        $this->db->query("DELETE FROM " . DB_PREFIX . "order_total WHERE order_id = '" . (int) $order_id . "'");

        if (isset($data['totals'])) {
            foreach ($data['totals'] as $total) {
                $this->db->query("INSERT INTO " . DB_PREFIX . "order_total SET order_id = '" . (int) $order_id . "', code = '" . $this->db->escape($total['code']) . "', title = '" . $this->db->escape($total['title']) . "', value = '" . (float) $total['value'] . "', sort_order = '" . (int) $total['sort_order'] . "'");
            }
        }

        $this->event->trigger('post.order.edit', $order_id);
    }

    public function deleteOrder($order_id) {
        $this->event->trigger('pre.order.delete', $order_id);

        // Void the order first
        $this->addOrderHistory($order_id, 0);

        $this->db->query("DELETE FROM " . DB_PREFIX . "order WHERE order_id = '" . (int) $order_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "order_product WHERE order_id = '" . (int) $order_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "order_total WHERE order_id = '" . (int) $order_id . "'");
        $this->db->query("DELETE FROM " . DB_PREFIX . "order_history WHERE order_id = '" . (int) $order_id . "'");
        $this->db->query("DELETE tor, tort FROM " . DB_PREFIX . "order_recurring tor, " . DB_PREFIX . "order_recurring_transaction tort WHERE order_id = '" . (int) $order_id . "' AND tort.order_recurring_id = tor.order_recurring_id");

        $this->event->trigger('post.order.delete', $order_id);
    }

    public function getOrder($order_id) {
        $order_query = $this->db->query(""
                . "SELECT *, "
                . "(SELECT os.name FROM " . DB_PREFIX . "order_status os "
                . "WHERE os.order_status_id = o.order_status_id AND os.language_id = o.language_id) AS order_status "
                . "FROM " . DB_PREFIX . "order o "
                . "WHERE o.order_id = '" . (int) $order_id . "'");
        
        if ($order_query->num_rows) {
            $country_query = $this->db->query(""
                    . "SELECT * FROM " . DB_PREFIX . "country "
                    . "WHERE country_id = '" . (int) $order_query->row['payment_country_id'] . "'");

            if ($country_query->num_rows) {
                $payment_iso_code_2 = $country_query->row['iso_code_2'];
                $payment_iso_code_3 = $country_query->row['iso_code_3'];
            } else {
                $payment_iso_code_2 = '';
                $payment_iso_code_3 = '';
            }

            $zone_query = $this->db->query("SELECT * FROM " . DB_PREFIX . "zone WHERE zone_id = '" . (int) $order_query->row['payment_zone_id'] . "'");

            if ($zone_query->num_rows) {
                $payment_zone_code = $zone_query->row['code'];
            } else {
                $payment_zone_code = '';
            }

            $this->load->model('localisation/language');

            $language_info = $this->model_localisation_language->getLanguage($order_query->row['language_id']);

            if ($language_info) {
                $language_code = $language_info['code'];
                $language_directory = $language_info['directory'];
            } else {
                $language_code = '';
                $language_directory = '';
            }

            return array(
                'order_id' => $order_query->row['order_id'],
                'invoice_no' => $order_query->row['invoice_no'],
                'invoice_prefix' => $order_query->row['invoice_prefix'],
                'store_name' => $order_query->row['store_name'],
                'store_url' => $order_query->row['store_url'],
                'customer_id' => $order_query->row['customer_id'],
                'firstname' => $order_query->row['firstname'],
                'lastname' => $order_query->row['lastname'],
                'email' => $order_query->row['email'],
                'telephone' => $order_query->row['telephone'],
                'fax' => $order_query->row['fax'],
                'custom_field' => json_decode($order_query->row['custom_field'], true),
                'payment_firstname' => $order_query->row['payment_firstname'],
                'payment_lastname' => $order_query->row['payment_lastname'],
                'payment_company' => $order_query->row['payment_company'],
                'payment_address_1' => $order_query->row['payment_address_1'],
                'payment_address_2' => $order_query->row['payment_address_2'],
                'payment_postcode' => $order_query->row['payment_postcode'],
                'payment_city' => $order_query->row['payment_city'],
                'payment_zone_id' => $order_query->row['payment_zone_id'],
                'payment_zone' => $order_query->row['payment_zone'],
                'payment_zone_code' => $payment_zone_code,
                'payment_country_id' => $order_query->row['payment_country_id'],
                'payment_country' => $order_query->row['payment_country'],
                'payment_iso_code_2' => $payment_iso_code_2,
                'payment_iso_code_3' => $payment_iso_code_3,
                'payment_address_format' => $order_query->row['payment_address_format'],
                'payment_custom_field' => json_decode($order_query->row['payment_custom_field'], true),
                'payment_method' => $order_query->row['payment_method'],
                'payment_code' => $order_query->row['payment_code'],
                'total' => $order_query->row['total'],
                'order_status_id' => $order_query->row['order_status_id'],
                'order_status' => $order_query->row['order_status'],
                'language_id' => $order_query->row['language_id'],
                'language_code' => $language_code,
                'language_directory' => $language_directory,
                'currency_id' => $order_query->row['currency_id'],
                'currency_code' => $order_query->row['currency_code'],
                'currency_value' => $order_query->row['currency_value'],
                'ip' => $order_query->row['ip'],
                'forwarded_ip' => $order_query->row['forwarded_ip'],
                'user_agent' => $order_query->row['user_agent'],
                'accept_language' => $order_query->row['accept_language'],
                'date_updated' => $order_query->row['date_updated'],
                'date_added' => $order_query->row['date_added']
            );
        } else {
            return false;
        }
    }

    public function addOrderHistory($order_id, $order_status_id, $comment = '', $notify = false, $override = false) {

        $event_data = array(
            'order_id' => $order_id,
            'order_status_id' => $order_status_id,
            'comment' => $comment,
            'notify' => $notify
        );

        $this->event->trigger('pre.order.history.add', $event_data);
        
        $order_info = $this->getOrder($order_id);

        if ($order_info) {
            // Fraud Detection
            $this->load->model('account/customer');

            $customer_info = $this->model_account_customer->getCustomer($order_info['customer_id']);

            if ($customer_info && $customer_info['safe']) {
                $safe = true;
            } else {
                $safe = false;
            }

            // If current order status is not processing or complete but new status is processing or complete then commence completing the order
            if (!in_array($order_info['order_status_id'], array_merge($this->config->get('config_processing_status'), $this->config->get('config_complete_status'))) && in_array($order_status_id, array_merge($this->config->get('config_processing_status'), $this->config->get('config_complete_status')))) {
                // Redeem coupon points
                $order_total_query = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_total WHERE order_id = '" . (int) $order_id . "' ORDER BY sort_order ASC");

                foreach ($order_total_query->rows as $order_total) {
                    $this->load->model('total/' . $order_total['code']);

                    if (method_exists($this->{'model_total_' . $order_total['code']}, 'confirm')) {
                        // Confirm coupon points
                        $fraud_status_id = $this->{'model_total_' . $order_total['code']}->confirm($order_info, $order_total);

                        // If the balance on the coupon is not enough to cover the transaction or has already been used then the fraud order status is returned.
                        if ($fraud_status_id) {
                            $order_status_id = $fraud_status_id;
                        }
                    }
                }
            }

            // Update the DB with the new statuses
            $this->db->query(""
                    . "UPDATE " . DB_PREFIX . "order "
                    . "SET order_status_id = '" . (int) $order_status_id . "', "
                    . "date_updated = NOW() WHERE order_id = '" . (int) $order_id . "'");

            $this->db->query(""
                    . "INSERT INTO " . DB_PREFIX . "order_history "
                    . "SET order_id = '" . (int) $order_id . "', "
                    . "order_status_id = '" . (int) $order_status_id . "', "
                    . "notify = '" . (int) $notify . "', "
                    . "comment = '" . $this->db->escape($comment) . "', "
                    . "date_added = NOW()");
            
            $this->event->trigger('on.order.history.add', $order_id);

            // If old order status is the processing or complete status but new status is not then commence restock, and remove coupon history
            if (in_array($order_info['order_status_id'], array_merge($this->config->get('config_processing_status'), $this->config->get('config_complete_status'))) && !in_array($order_status_id, array_merge($this->config->get('config_processing_status'), $this->config->get('config_complete_status')))) {

                // Remove coupon history
                $this->load->model('account/order');

                $order_total_query = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_total WHERE order_id = '" . (int) $order_id . "' ORDER BY sort_order ASC");

                foreach ($order_total_query->rows as $order_total) {
                    $this->load->model('total/' . $order_total['code']);

                    if (method_exists($this->{'model_total_' . $order_total['code']}, 'unconfirm')) {
                        $this->{'model_total_' . $order_total['code']}->unconfirm($order_id);
                    }
                }
            }

            $this->cache->delete('product');

            // If order status is 0 then becomes greater than 0 send main html email
            if (!$order_info['order_status_id'] && $order_status_id) {

                $order_product_query = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_product WHERE order_id = '" . (int) $order_id . "'");

                // Load the language for any mails that might be required to be sent out
                $language = new Language($order_info['language_directory']);
                $language->load($order_info['language_directory']);
                $language->load('mail/order');

                $order_status_query = $this->db->query(""
                        . "SELECT * FROM " . DB_PREFIX . "order_status "
                        . "WHERE order_status_id = '" . (int) $order_status_id . "' "
                        . "AND language_id = '" . (int) $order_info['language_id'] . "'");

                if ($order_status_query->num_rows) {
                    $order_status = $order_status_query->row['name'];
                } else {
                    $order_status = '';
                }

                $subject = sprintf($language->get('text_new_subject'), html_entity_decode($order_info['store_name'], ENT_QUOTES, 'UTF-8'), $order_id);

                // HTML Mail
                $data = array();

                $data['title'] = sprintf($language->get('text_new_subject'), $order_info['store_name'], $order_id);

                $data['text_greeting'] = sprintf($language->get('text_new_greeting'), $order_info['store_name']);
                $data['text_link'] = $language->get('text_new_link');
                $data['text_order_detail'] = $language->get('text_new_order_detail');
                $data['text_instruction'] = $language->get('text_new_instruction');
                $data['text_order_id'] = $language->get('text_new_order_id');
                $data['text_date_added'] = $language->get('text_new_date_added');
                $data['text_payment_method'] = $language->get('text_new_payment_method');
                $data['text_email'] = $language->get('text_new_email');
                $data['text_telephone'] = $language->get('text_new_telephone');
                $data['text_ip'] = $language->get('text_new_ip');
                $data['text_order_status'] = $language->get('text_new_order_status');
                $data['text_payment_address'] = $language->get('text_new_payment_address');
                $data['text_product'] = $language->get('text_new_product');
                $data['text_model'] = $language->get('text_new_model');
                $data['text_quantity'] = $language->get('text_new_quantity');
                $data['text_price'] = $language->get('text_new_price');
                $data['text_total'] = $language->get('text_new_total');
                $data['text_footer'] = $language->get('text_new_footer');

                $data['logo'] = $this->config->get('config_url') . 'application/resource/images/logo-small.png';
                $data['store_name'] = $order_info['store_name'];
                $data['store_url'] = $order_info['store_url'];
                $data['customer_id'] = $order_info['customer_id'];
                $data['link'] = $order_info['store_url'] . 'account/order/info&order_id=' . $order_id;

                $data['order_id'] = $order_id;
                $data['date_added'] = date($language->get('date_format_short'), strtotime($order_info['date_added']));
                $data['payment_method'] = $order_info['payment_method'];
                $data['email'] = $order_info['email'];
                $data['telephone'] = $order_info['telephone'];
                $data['ip'] = $order_info['ip'];
                $data['order_status'] = $order_status;

                if ($comment && $notify) {
                    $data['comment'] = nl2br($comment);
                } else {
                    $data['comment'] = '';
                }

                if ($order_info['payment_address_format']) {
                    $format = $order_info['payment_address_format'];
                } else {
                    $format = '{firstname} {lastname}' . "\n" . '{company}' . "\n" . '{address_1}' . "\n" . '{address_2}' . "\n" . '{city} {postcode}' . "\n" . '{zone}' . "\n" . '{country}';
                }

                $find = array(
                    '{firstname}',
                    '{lastname}',
                    '{company}',
                    '{address_1}',
                    '{address_2}',
                    '{city}',
                    '{postcode}',
                    '{zone}',
                    '{zone_code}',
                    '{country}'
                );

                $replace = array(
                    'firstname' => $order_info['payment_firstname'],
                    'lastname' => $order_info['payment_lastname'],
                    'company' => $order_info['payment_company'],
                    'address_1' => $order_info['payment_address_1'],
                    'address_2' => $order_info['payment_address_2'],
                    'city' => $order_info['payment_city'],
                    'postcode' => $order_info['payment_postcode'],
                    'zone' => $order_info['payment_zone'],
                    'zone_code' => $order_info['payment_zone_code'],
                    'country' => $order_info['payment_country']
                );

                $data['payment_address'] = str_replace(array("\r\n", "\r", "\n"), '<br />', preg_replace(array("/\s\s+/", "/\r\r+/", "/\n\n+/"), '<br />', trim(str_replace($find, $replace, $format))));

                $this->load->model('tool/upload');

                // Products
                $data['products'] = array();

                foreach ($order_product_query->rows as $product) {
                    $data['products'][] = array(
                        'name' => $product['name'],
                        'quantity' => $product['quantity'],
                        'price' => $this->currency->format($product['price'], $order_info['currency_code'], $order_info['currency_value']),
                        'total' => $this->currency->format($product['total'], $order_info['currency_code'], $order_info['currency_value'])
                    );
                }

                // Order Totals
                $order_total_query = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_total WHERE order_id = '" . (int) $order_id . "' ORDER BY sort_order ASC");

                foreach ($order_total_query->rows as $total) {
                    $data['totals'][] = array(
                        'title' => $total['title'],
                        'text' => $this->currency->format($total['value'], $order_info['currency_code'], $order_info['currency_value']),
                    );
                }

                if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/mail/order.tpl')) {
                    $html = $this->load->view($this->config->get('config_template') . '/template/mail/order.tpl', $data);
                } else {
                    $html = $this->load->view('/default/template/mail/order.tpl', $data);
                }

                // Text Mail
                $text = sprintf($language->get('text_new_greeting'), html_entity_decode($order_info['store_name'], ENT_QUOTES, 'UTF-8')) . "\n\n";
                $text .= $language->get('text_new_order_id') . ' ' . $order_id . "\n";
                $text .= $language->get('text_new_date_added') . ' ' . date($language->get('date_format_short'), strtotime($order_info['date_added'])) . "\n";
                $text .= $language->get('text_new_order_status') . ' ' . $order_status . "\n\n";

                if ($comment && $notify) {
                    $text .= $language->get('text_new_instruction') . "\n\n";
                    $text .= $comment . "\n\n";
                }

                // Products
                $text .= $language->get('text_new_products') . "\n";

                foreach ($order_product_query->rows as $product) {
                    $text .= $product['quantity'] . 'x ' . $product['name'] . html_entity_decode($this->currency->format($product['total'], $order_info['currency_code'], $order_info['currency_value']), ENT_NOQUOTES, 'UTF-8') . "\n";
                }

                $text .= "\n";

                $text .= $language->get('text_new_order_total') . "\n";

                foreach ($order_total_query->rows as $total) {
                    $text .= $total['title'] . ': ' . html_entity_decode($this->currency->format($total['value'], $order_info['currency_code'], $order_info['currency_value']), ENT_NOQUOTES, 'UTF-8') . "\n";
                }

                $text .= "\n";

                if ($order_info['customer_id']) {
                    $text .= $language->get('text_new_link') . "\n";
                    $text .= $order_info['store_url'] . 'account/order/info&order_id=' . $order_id . "\n\n";
                }

                // Comment
                if (isset($order_info['comment'])) {
                    $text .= $language->get('text_new_comment') . "\n\n";
                    $text .= $order_info['comment'] . "\n\n";
                }

                $text .= $language->get('text_new_footer') . "\n\n";

                $mail = new Mail();
                $mail->protocol = $this->config->get('config_mail_protocol');
                $mail->parameter = $this->config->get('config_mail_parameter');
                $mail->smtp_hostname = $this->config->get('config_mail_smtp_hostname');
                $mail->smtp_username = $this->config->get('config_mail_smtp_username');
                $mail->smtp_password = html_entity_decode($this->config->get('config_mail_smtp_password'), ENT_QUOTES, 'UTF-8');
                $mail->smtp_port = $this->config->get('config_mail_smtp_port');
                $mail->smtp_timeout = $this->config->get('config_mail_smtp_timeout');

                $mail->setTo($order_info['email']);
                $mail->setFrom($this->config->get('config_email'));
                $mail->setSender(html_entity_decode($order_info['store_name'], ENT_QUOTES, 'UTF-8'));
                $mail->setSubject(html_entity_decode($subject, ENT_QUOTES, 'UTF-8'));
                $mail->setHtml($html);
                $mail->setText($text);
                $mail->send();
            }

            // If order status is not 0 then send update text email
            if ($order_info['order_status_id'] && $order_status_id && $notify) {
                $language = new Language($order_info['language_directory']);
                $language->load($order_info['language_directory']);
                $language->load('mail/order');

                $subject = sprintf($language->get('text_update_subject'), html_entity_decode($order_info['store_name'], ENT_QUOTES, 'UTF-8'), $order_id);

                $message = $language->get('text_update_order') . ' ' . $order_id . "\n";
                $message .= $language->get('text_update_date_added') . ' ' . date($language->get('date_format_short'), strtotime($order_info['date_added'])) . "\n\n";

                $order_status_query = $this->db->query("SELECT * FROM " . DB_PREFIX . "order_status WHERE order_status_id = '" . (int) $order_status_id . "' AND language_id = '" . (int) $order_info['language_id'] . "'");

                if ($order_status_query->num_rows) {
                    $message .= $language->get('text_update_order_status') . "\n\n";
                    $message .= $order_status_query->row['name'] . "\n\n";
                }

                if ($order_info['customer_id']) {
                    $message .= $language->get('text_update_link') . "\n";
                    $message .= $order_info['store_url'] . 'account/order/info&order_id=' . $order_id . "\n\n";
                }

                if ($comment) {
                    $message .= $language->get('text_update_comment') . "\n\n";
                    $message .= strip_tags($comment) . "\n\n";
                }

                $message .= $language->get('text_update_footer');

                $mail = new Mail();
                $mail->protocol = $this->config->get('config_mail_protocol');
                $mail->parameter = $this->config->get('config_mail_parameter');
                $mail->smtp_hostname = $this->config->get('config_mail_smtp_hostname');
                $mail->smtp_username = $this->config->get('config_mail_smtp_username');
                $mail->smtp_password = html_entity_decode($this->config->get('config_mail_smtp_password'), ENT_QUOTES, 'UTF-8');
                $mail->smtp_port = $this->config->get('config_mail_smtp_port');
                $mail->smtp_timeout = $this->config->get('config_mail_smtp_timeout');

                $mail->setTo($order_info['email']);
                $mail->setFrom($this->config->get('config_email'));
                $mail->setSender(html_entity_decode($order_info['store_name'], ENT_QUOTES, 'UTF-8'));
                $mail->setSubject(html_entity_decode($subject, ENT_QUOTES, 'UTF-8'));
                $mail->setText($message);
                $mail->send();
            }
        }
           
        $this->event->trigger('post.order.history.add', $order_id);
    }

}
