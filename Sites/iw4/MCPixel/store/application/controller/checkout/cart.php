<?php

class ControllerCheckoutCart extends Controller {

    public function add() {
        if (isset($this->request->get['product'])) {
            $this->cart->add($this->request->get['product']);
        }

        if (isset($this->request->post['redirect'])) {
            $this->response->redirect($this->request->get['redirect']);
        } else {
            $this->response->redirect($this->url->link('checkout/view', '', 'SSL'));
        }
    }

    public function remove() {
        if (isset($this->request->get['product'])) {
            $this->cart->remove($this->request->get['product']);
        }

        if (isset($this->request->post['redirect'])) {
            $this->response->redirect($this->request->get['redirect']);
        } else {
            $this->response->redirect($this->url->link('checkout/view', '', 'SSL'));
        }
    }

    public function clear() {
        $this->cart->clear();

        if (isset($this->request->post['redirect'])) {
            $this->response->redirect($this->request->get['redirect']);
        } else {
            $this->response->redirect($this->url->link('checkout/view', '', 'SSL'));
        }
    }

    public function edit() {
        $this->load->language('checkout/view');

        $json = array();

        // Update
        if (!empty($this->request->post['quantity'])) {
            foreach ($this->request->post['quantity'] as $key => $value) {
                $this->cart->update($key, $value);
            }

            $this->response->redirect($this->url->link('checkout/view', '', 'SSL'));
        }

        $this->response->addHeader('Content-Type: application/json');
        $this->response->setOutput(json_encode($json));
    }

}
