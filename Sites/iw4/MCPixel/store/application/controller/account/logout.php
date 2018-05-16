<?php

class ControllerAccountLogout extends Controller {

    public function index() {
        if ($this->customer->isLogged()) {
            $this->event->trigger('pre.customer.logout');

            $this->customer->logout();

            unset($this->session->data['payment_address']);
            unset($this->session->data['payment_method']);
            unset($this->session->data['payment_methods']);
            unset($this->session->data['order_id']);
            unset($this->session->data['coupon']);
			
            $this->event->trigger('post.customer.logout');

            if (isset($this->request->post['redirect'])) {
                $this->response->redirect($this->request->get['redirect']);
            } else {
                $this->response->redirect($this->url->link('common/home'));
            }
        }
    }

}
