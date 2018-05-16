<?php

class ControllerCatalogServer extends Controller {
    private $error = array();

    public function index() {
        $this->language->load('catalog/server');

        $this->document->setTitle($this->language->get('heading_title'));

        $this->load->model('catalog/server');

        $this->getList();
    }

    public function add() {
        $this->language->load('catalog/server');

        $this->document->setTitle($this->language->get('heading_title'));

        $this->load->model('catalog/server');

        if (($this->request->server['REQUEST_METHOD'] == 'POST') && $this->validateForm()) {
            $this->model_catalog_server->addServer($this->request->post);

            $this->session->data['success'] = $this->language->get('text_success');

            $url = '';

            if (isset($this->request->get['sort'])) {
                $url .= '&sort=' . $this->request->get['sort'];
            }

            if (isset($this->request->get['order'])) {
                $url .= '&order=' . $this->request->get['order'];
            }

            if (isset($this->request->get['page'])) {
                $url .= '&page=' . $this->request->get['page'];
            }

            $this->response->redirect($this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url, 'SSL'));
        }

        $this->getForm();
    }

    public function edit() {
        $this->language->load('catalog/server');

        $this->document->setTitle($this->language->get('heading_title'));

        $this->load->model('catalog/server');

        if (($this->request->server['REQUEST_METHOD'] == 'POST') && $this->validateForm()) {
            $this->model_catalog_server->editServer($this->request->get['server_id'], $this->request->post);

            $this->session->data['success'] = $this->language->get('text_success');

            $url = '';

            if (isset($this->request->get['sort'])) {
                $url .= '&sort=' . $this->request->get['sort'];
            }

            if (isset($this->request->get['order'])) {
                $url .= '&order=' . $this->request->get['order'];
            }

            if (isset($this->request->get['page'])) {
                $url .= '&page=' . $this->request->get['page'];
            }

            $this->response->redirect($this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url, 'SSL'));
        }

        $this->getForm();
    }

    public function delete() {
        $this->language->load('catalog/server');

        $this->document->setTitle($this->language->get('heading_title'));

        $this->load->model('catalog/server');

        if (isset($this->request->post['selected']) && $this->validateDelete()) {
            foreach ($this->request->post['selected'] as $server_id) {
                $this->model_catalog_server->deleteServer($server_id);
            }

            $this->session->data['success'] = $this->language->get('text_success');

            $url = '';

            if (isset($this->request->get['sort'])) {
                $url .= '&sort=' . $this->request->get['sort'];
            }

            if (isset($this->request->get['order'])) {
                $url .= '&order=' . $this->request->get['order'];
            }

            if (isset($this->request->get['page'])) {
                $url .= '&page=' . $this->request->get['page'];
            }

            $this->response->redirect($this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url, 'SSL'));
        }

        $this->getList();
    }

    protected function getList() {
        if (isset($this->request->get['sort'])) {
            $sort = $this->request->get['sort'];
        } else {
            $sort = 'name';
        }

        if (isset($this->request->get['order'])) {
            $order = $this->request->get['order'];
        } else {
            $order = 'ASC';
        }

        if (isset($this->request->get['page'])) {
            $page = $this->request->get['page'];
        } else {
            $page = 1;
        }

        $url = '';

        if (isset($this->request->get['sort'])) {
            $url .= '&sort=' . $this->request->get['sort'];
        }

        if (isset($this->request->get['order'])) {
            $url .= '&order=' . $this->request->get['order'];
        }

        if (isset($this->request->get['page'])) {
            $url .= '&page=' . $this->request->get['page'];
        }

        $data['breadcrumbs'] = array();

        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('text_home'),
            'href' => $this->url->link('dashboard/common/dashboard', 'token=' . $this->session->data['token'], 'SSL')
        );

        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('heading_title'),
            'href' => $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url, 'SSL')
        );

        $data['add'] = $this->url->link('dashboard/catalog/server/add', 'token=' . $this->session->data['token'] . $url, 'SSL');
        $data['delete'] = $this->url->link('dashboard/catalog/server/delete', 'token=' . $this->session->data['token'] . $url, 'SSL');

        $data['servers'] = array();

        $filter_data = array(
            'sort'  => $sort,
            'order' => $order,
            'start' => ($page - 1) * $this->config->get('config_limit_admin'),
            'limit' => $this->config->get('config_limit_admin')
        );

        $server_total = $this->model_catalog_server->getTotalServers();

        $results = $this->model_catalog_server->getServers($filter_data);

        foreach ($results as $result) {
            $data['servers'][] = array(
                'server_id'  => $result['minecraft_server_id'],
                'name'       => $result['name'],
                'sort_order' => $result['sort_order'],
                'edit'       => $this->url->link('dashboard/catalog/server/edit', 'token=' . $this->session->data['token'] . '&server_id=' . $result['minecraft_server_id'] . $url, 'SSL'),
                'delete'     => $this->url->link('dashboard/catalog/server/delete', 'token=' . $this->session->data['token'] . '&server_id=' . $result['minecraft_server_id'] . $url, 'SSL')
            );
        }

        $data['heading_title'] = $this->language->get('heading_title');

        $data['text_list'] = $this->language->get('text_list');
        $data['text_no_results'] = $this->language->get('text_no_results');
        $data['text_confirm'] = $this->language->get('text_confirm');

        $data['column_name'] = $this->language->get('column_name');
        $data['column_sort_order'] = $this->language->get('column_sort_order');
        $data['column_action'] = $this->language->get('column_action');

        $data['button_add'] = $this->language->get('button_add');
        $data['button_edit'] = $this->language->get('button_edit');
        $data['button_delete'] = $this->language->get('button_delete');
        $data['button_rebuild'] = $this->language->get('button_rebuild');

        if (isset($this->error['warning'])) {
            $data['error_warning'] = $this->error['warning'];
        } else {
            $data['error_warning'] = '';
        }

        if (isset($this->session->data['success'])) {
            $data['success'] = $this->session->data['success'];

            unset($this->session->data['success']);
        } else {
            $data['success'] = '';
        }

        if (isset($this->request->post['selected'])) {
            $data['selected'] = (array)$this->request->post['selected'];
        } else {
            $data['selected'] = array();
        }

        $url = '';

        if ($order == 'ASC') {
            $url .= '&order=DESC';
        } else {
            $url .= '&order=ASC';
        }

        if (isset($this->request->get['page'])) {
            $url .= '&page=' . $this->request->get['page'];
        }

        $data['sort_name'] = $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . '&sort=name' . $url, 'SSL');
        $data['sort_sort_order'] = $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . '&sort=sort_order' . $url, 'SSL');

        $url = '';

        if (isset($this->request->get['sort'])) {
            $url .= '&sort=' . $this->request->get['sort'];
        }

        if (isset($this->request->get['order'])) {
            $url .= '&order=' . $this->request->get['order'];
        }

        $pagination = new Pagination();
        $pagination->total = $server_total;
        $pagination->page = $page;
        $pagination->limit = $this->config->get('config_limit_admin');
        $pagination->url = $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url . '&page={page}', 'SSL');

        $data['pagination'] = $pagination->render();

        $data['results'] = sprintf($this->language->get('text_pagination'), ($server_total) ? (($page - 1) * $this->config->get('config_limit_admin')) + 1 : 0, ((($page - 1) * $this->config->get('config_limit_admin')) > ($server_total - $this->config->get('config_limit_admin'))) ? $server_total : ((($page - 1) * $this->config->get('config_limit_admin')) + $this->config->get('config_limit_admin')), $server_total, ceil($server_total / $this->config->get('config_limit_admin')));

        $data['sort'] = $sort;
        $data['order'] = $order;

        $data['header'] = $this->load->controller('common/header');
        $data['column_left'] = $this->load->controller('common/column_left');
        $data['footer'] = $this->load->controller('common/footer');

        $this->response->setOutput($this->load->view('catalog/server_list.tpl', $data));
    }

    protected function getForm() {
        $data['heading_title'] = $this->language->get('heading_title');

        $data['text_form'] = !isset($this->request->get['server_id']) ? $this->language->get('text_add') : $this->language->get('text_edit');
        $data['text_none'] = $this->language->get('text_none');
        $data['text_default'] = $this->language->get('text_default');
        $data['text_enabled'] = $this->language->get('text_enabled');
        $data['text_disabled'] = $this->language->get('text_disabled');

        $data['entry_name'] = $this->language->get('entry_name');
        $data['entry_description'] = $this->language->get('entry_description');
        $data['entry_column'] = $this->language->get('entry_column');
        $data['entry_sort_order'] = $this->language->get('entry_sort_order');
        $data['entry_status'] = $this->language->get('entry_status');
        $data['entry_code'] = $this->language->get('entry_code');

        $data['help_filter'] = $this->language->get('help_filter');
        $data['help_keyword'] = $this->language->get('help_keyword');
        $data['help_top'] = $this->language->get('help_top');
        $data['help_column'] = $this->language->get('help_column');

        $data['button_save'] = $this->language->get('button_save');
        $data['button_cancel'] = $this->language->get('button_cancel');

        $data['tab_general'] = $this->language->get('tab_general');
        $data['tab_data'] = $this->language->get('tab_data');
        $data['tab_design'] = $this->language->get('tab_design');

        if (isset($this->error['warning'])) {
            $data['error_warning'] = $this->error['warning'];
        } else {
            $data['error_warning'] = '';
        }

        if (isset($this->error['name'])) {
            $data['error_name'] = $this->error['name'];
        } else {
            $data['error_name'] = array();
        }

        if (isset($this->error['code'])) {
            $data['error_code'] = $this->error['code'];
        } else {
            $data['error_code'] = '';
        }

        $url = '';

        if (isset($this->request->get['sort'])) {
            $url .= '&sort=' . $this->request->get['sort'];
        }

        if (isset($this->request->get['order'])) {
            $url .= '&order=' . $this->request->get['order'];
        }

        if (isset($this->request->get['page'])) {
            $url .= '&page=' . $this->request->get['page'];
        }

        $data['breadcrumbs'] = array();

        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('text_home'),
            'href' => $this->url->link('dashboard/common/dashboard', 'token=' . $this->session->data['token'], 'SSL')
        );

        $data['breadcrumbs'][] = array(
            'text' => $this->language->get('heading_title'),
            'href' => $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url, 'SSL')
        );

        if (!isset($this->request->get['server_id'])) {
            $data['action'] = $this->url->link('dashboard/catalog/server/add', 'token=' . $this->session->data['token'] . $url, 'SSL');
        } else {
            $data['action'] = $this->url->link('dashboard/catalog/server/edit', 'token=' . $this->session->data['token'] . '&server_id=' . $this->request->get['server_id'] . $url, 'SSL');
        }

        $data['cancel'] = $this->url->link('dashboard/catalog/server', 'token=' . $this->session->data['token'] . $url, 'SSL');

        if (isset($this->request->get['server_id']) && ($this->request->server['REQUEST_METHOD'] != 'POST')) {
            $server_info = $this->model_catalog_server->getServer($this->request->get['server_id']);
        }

        $data['token'] = $this->session->data['token'];

        $this->load->model('localisation/language');

        $data['languages'] = $this->model_localisation_language->getLanguages();

        if (isset($this->request->post['server_description'])) {
            $data['server_description'] = $this->request->post['server_description'];
        } elseif (isset($this->request->get['server_id'])) {
            $data['server_description'] = $this->model_catalog_server->getServerDescriptions($this->request->get['server_id']);
        } else {
            $data['server_description'] = array();
        }

        if (isset($this->request->post['code'])) {
            $data['code'] = $this->request->post['code'];
        } elseif (!empty($server_info)) {
            $data['code'] = $server_info['code'];
        } else {
            $data['code'] = '';
        }

        if (isset($this->request->post['sort_order'])) {
            $data['sort_order'] = $this->request->post['sort_order'];
        } elseif (!empty($server_info)) {
            $data['sort_order'] = $server_info['sort_order'];
        } else {
            $data['sort_order'] = 0;
        }

        if (isset($this->request->post['status'])) {
            $data['status'] = $this->request->post['status'];
        } elseif (!empty($server_info)) {
            $data['status'] = $server_info['status'];
        } else {
            $data['status'] = true;
        }

        $this->load->model('design/layout');

        $data['layouts'] = $this->model_design_layout->getLayouts();

        $data['header'] = $this->load->controller('common/header');
        $data['column_left'] = $this->load->controller('common/column_left');
        $data['footer'] = $this->load->controller('common/footer');

        $this->response->setOutput($this->load->view('catalog/server_form.tpl', $data));
    }

    protected function validateForm() {
        if (!$this->user->hasPermission('modify', 'catalog/server')) {
            $this->error['warning'] = $this->language->get('error_permission');
        }

        foreach ($this->request->post['server_description'] as $language_id => $value) {
            if ((utf8_strlen($value['name']) < 2) || (utf8_strlen($value['name']) > 16)) {
                $this->error['name'][$language_id] = $this->language->get('error_name');
            }
        }

        if ((utf8_strlen($this->request->post['code']) < 2) || (utf8_strlen($this->request->post['code']) > 16)) {
            $this->error['code'] = $this->language->get('error_code');
        } else {
            $query_data = array();

            if (isset($this->request->get['server_id']) && intval($this->request->get['server_id'])) {
                $query_data['not_like_id'] = $this->request->get['server_id'];
            }

            $other = $this->model_catalog_server->getServerByCode($this->request->post['code'], $query_data);
            if (!empty($other)) {
                $this->error['code'] = $this->language->get('error_code_already_exists');
            }
        }

        if ($this->error && !isset($this->error['warning'])) {
            $this->error['warning'] = $this->language->get('error_warning');
        }
        return !$this->error;
    }

    protected function validateDelete() {
        if (!$this->user->hasPermission('modify', 'catalog/server')) {
            $this->error['warning'] = $this->language->get('error_permission');
        }

        return !$this->error;
    }

    protected function validateRepair() {
        if (!$this->user->hasPermission('modify', 'catalog/server')) {
            $this->error['warning'] = $this->language->get('error_permission');
        }

        return !$this->error;
    }

    public function autocomplete() {
        $json = array();

        if (isset($this->request->get['filter_name'])) {
            $this->load->model('catalog/server');

            $filter_data = array(
                'filter_name' => $this->request->get['filter_name'],
                'sort'        => 'name',
                'order'       => 'ASC',
                'start'       => 0,
                'limit'       => 5
            );

            $results = $this->model_catalog_server->getServers($filter_data);

            foreach ($results as $result) {
                $json[] = array(
                    'server_id' => $result['minecraft_server_id'],
                    'name'      => strip_tags(html_entity_decode($result['name'], ENT_QUOTES, 'UTF-8')),
                    'code'      => $result['code']
                );
            }
        }

        $sort_order = array();

        foreach ($json as $key => $value) {
            $sort_order[$key] = $value['name'];
        }

        array_multisort($sort_order, SORT_ASC, $json);

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }
}