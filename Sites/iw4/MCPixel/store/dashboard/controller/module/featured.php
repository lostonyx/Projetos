<?php
class ControllerModuleFeatured extends Controller {
	private $error = array();

	public function index() {
		$this->load->language('module/featured');

		$this->document->setTitle($this->language->get('heading_title'));

		$this->load->model('setting/setting');

		if (($this->request->server['REQUEST_METHOD'] == 'POST') && $this->validate()) {
			$this->model_setting_setting->editSetting('featured', $this->request->post);

			$this->session->data['success'] = $this->language->get('text_success');

			$this->response->redirect($this->url->link('dashboard/extension/module', 'token=' . $this->session->data['token'], 'SSL'));
		}

		$data['heading_title'] = $this->language->get('heading_title');

		$data['text_edit'] = $this->language->get('text_edit');
		$data['text_enabled'] = $this->language->get('text_enabled');
		$data['text_disabled'] = $this->language->get('text_disabled');

		$data['help_name'] = $this->language->get('help_name');
		$data['help_limit'] = $this->language->get('help_limit');

		$data['entry_status'] = $this->language->get('entry_status');
		$data['entry_name'] = $this->language->get('entry_name');
		$data['entry_limit'] = $this->language->get('entry_limit');

		$data['button_save'] = $this->language->get('button_save');
		$data['button_cancel'] = $this->language->get('button_cancel');

		if (isset($this->error['warning'])) {
			$data['error_warning'] = $this->error['warning'];
		} else {
			$data['error_warning'] = '';
		}

		$data['breadcrumbs'] = array();

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('text_home'),
			'href' => $this->url->link('dashboard/common/dashboard', 'token=' . $this->session->data['token'], 'SSL')
		);

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('text_module'),
			'href' => $this->url->link('dashboard/extension/module', 'token=' . $this->session->data['token'], 'SSL')
		);

		$data['breadcrumbs'][] = array(
			'text' => $this->language->get('heading_title'),
			'href' => $this->url->link('dashboard/module/featured', 'token=' . $this->session->data['token'], 'SSL')
		);

		$data['action'] = $this->url->link('dashboard/module/featured', 'token=' . $this->session->data['token'], 'SSL');

		$data['cancel'] = $this->url->link('dashboard/extension/module', 'token=' . $this->session->data['token'], 'SSL');

		if (isset($this->request->post['featured_status'])) {
			$data['featured_status'] = $this->request->post['featured_status'];
		} else {
			$data['featured_status'] = $this->config->get('featured_status');
		}

		if (isset($this->request->post['featured_name'])) {
			$data['featured_name'] = $this->request->post['featured_name'];
		} else {
			$data['featured_name'] = $this->config->get('featured_name');
		}

		if (isset($this->request->post['featured_limit'])) {
			$data['featured_limit'] = $this->request->post['featured_limit'];
		} else {
			$data['featured_limit'] = $this->config->get('featured_limit');
		}

		// FORM

		if (isset($this->error['featured_name'])) {
			$data['error_featured_name'] = $this->error['featured_name'];
		} else {
			$data['error_featured_name'] = array();
		}

		if (isset($this->error['featured_limit'])) {
			$data['error_featured_limit'] = $this->error['featured_limit'];
		} else {
			$data['error_featured_limit'] = array();
		}

		// Template

		$data['header'] = $this->load->controller('common/header');
		$data['column_left'] = $this->load->controller('common/column_left');
		$data['footer'] = $this->load->controller('common/footer');

		$this->response->setOutput($this->load->view('module/featured.tpl', $data));
	}

	protected function validate() {
		if (!$this->user->hasPermission('modify', 'module/featured')) {
			$this->error['warning'] = $this->language->get('error_permission');
		}

		if ((utf8_strlen($this->request->post['featured_name']) < 3) || (utf8_strlen($this->request->post['featured_name']) > 255)) {
			$this->error['featured_name'] = $this->language->get('error_name');
		}

		if (!intval(abs($this->request->post['featured_limit']))) {
			$this->error['featured_limit'] = $this->language->get('error_limit');
		} else {
			$this->request->post['featured_limit'] = abs($this->request->post['featured_limit']);
		}

		return !$this->error;
	}
}