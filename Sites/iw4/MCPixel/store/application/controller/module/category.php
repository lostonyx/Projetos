<?php

class ControllerModuleCategory extends Controller {

    public function index() {
        $this->load->language('module/category');

        $data['heading_title'] = $this->language->get('heading_title');

        $this->load->model('catalog/category');

        if ($this->registry->get("current")) {

            $category = $this->model_catalog_category->getCategory($this->registry->get("current"));
            $category = (!empty($category)) ? $category : array();

            $data['category'] = array(
                "name" => $category['name'],
                "description" => html_entity_decode($category['description'], ENT_QUOTES, 'UTF-8'),
            );
        } else {
            $data['category'] = array();
        }

        if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/module/category.tpl')) {
            return $this->load->view($this->config->get('config_template') . '/template/module/category.tpl', $data);
        } else {
            return $this->load->view('/default/template/module/category.tpl', $data);
        }
    }

}
