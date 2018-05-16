<?php

class ControllerModuleTextbox extends Controller {

    public function index($setting = array()) {

        if (!isset($setting['name'])) {
            $setting['name'] = $this->config->get("name");
        }

        if (!isset($setting['description'])) {
            $setting['description'] = $this->config->get("description");
        }

        $setting['description'] = html_entity_decode($setting['description'], ENT_QUOTES, 'UTF-8');

        if (file_exists(DIR_TEMPLATE . $this->config->get('config_template') . '/template/module/textbox.tpl')) {
            return $this->load->view($this->config->get('config_template') . '/template/module/textbox.tpl', $setting);
        } else {
            return $this->load->view('/default/template/module/textbox.tpl', $setting);
        }
    }

}
