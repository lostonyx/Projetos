<?php
class MY_Controller extends CI_Controller {
    
    protected $default = array();
    
    function __construct() {
        parent::__construct();
        header('Content-Type: text/html; charset=iso-8859-1');
        @session_start();
        if (substr($_SERVER['REQUEST_URI'], 0, 15) != '/index.php/home'){
            $_SESSION['redirect_last_page'] = $_SERVER['REQUEST_URI'];
        }
        
        $this->load->helper('url');
        $this->load->helper('html');
        $this->load->helper('form');
        $this->load->helper('show_message_helper');
        
        $this->load->library('my_auth');
        $this->load->library('util');
        
        //SISTEMAS DE GATEWAY
        /*$CI = &get_instance();
        $CI->config->load("mercadopago", TRUE);
        $config = $CI->config->item('mercadopago');*/
        //$this->load->library('Mercadopago');
        //$this->load->library('pagseguro');
        
    }
    
}
?>
