<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Home extends MY_Controller
{

    function __construct()
    {
        parent::__construct();
    }

    public function index()
    {
        
        $this->load->view('header');
        $this->load->view('home');
        $this->load->view('footer');
    }
    
    public function debug(){
        $this->load->view('teste');
    }
    
}
