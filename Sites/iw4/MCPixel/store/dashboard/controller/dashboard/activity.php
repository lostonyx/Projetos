<?php

class ControllerDashboardActivity extends Controller {
    public function index() {
        if (!$this->user->hasPermission('access', 'dashboard/activity')) {
            return false;
        }

        $this->load->language('dashboard/activity');

        $data['heading_title'] = $this->language->get('heading_title');

        $data['text_no_results'] = $this->language->get('text_no_results');

        $data['token'] = $this->session->data['token'];

        $data['activities'] = array();

        $this->load->model('report/activity');

        $results = $this->model_report_activity->getActivities();

        foreach ($results as $result) {
            $comment = vsprintf($this->language->get('text_' . $result['key']), json_decode($result['data'], true));

            $find = array(
                'customer_id=',
                'order_id=',
                'return_id='
            );

            $replace = array(
                $this->url->link('dashboard/customer/customer/edit', 'token=' . $this->session->data['token'] . '&customer_id=', 'SSL'),
                $this->url->link('dashboard/sale/order/info', 'token=' . $this->session->data['token'] . '&order_id=', 'SSL')
            );

            $data['activities'][] = array(
                'comment'    => str_replace($find, $replace, $comment),
                'date_added' => date($this->language->get('datetime_format'), strtotime($result['date_added']))
            );
        }

        return $this->load->view('dashboard/activity.tpl', $data);
    }
}
