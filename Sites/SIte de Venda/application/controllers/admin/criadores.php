<?php
class Criadores extends MY_Controller {
    
    function __construct() {
        parent::__construct();
        $this->load->library('pagination');
        $this->load->model('changelog_model');
        $this->load->model('admin_model');
        $this->adminid = $this->my_auth->get('admin', 'id');
    }
    
    public function index()
    {
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->default['content']['criadors'] = $this->admin_model->selectCriadores();
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/criadores/index', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function edit($id = 0){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        if (!$id){
            $id = 0;
        }
        $id = urldecode($id);
        
        $pesquisa = $this->admin_model->selectCriador($id);
        $this->default['content']['criadors'] =  $pesquisa;
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/criadores/edit', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function view($id = 0){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        if (!$id){
            $id = 0;
        }
        $id = urldecode($id);
        
        $pesquisa = $this->admin_model->selectCriador($id);
        $this->default['content']['criadors'] =  $pesquisa;
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/criadores/view', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function add(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->view('/admin/header');
        $this->load->view('/admin/criadores/add');
        $this->load->view('/admin/footer');
    }
    
    public function del($id = 0){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->admin_model->deleteCriador($id);
        add_message('Criador deletado com sucesso!');
        redirect('/admin/criadores/');
    }
    
    public function create(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(!isset($_POST['name']) || !isset($_POST['mercadopago']) || !isset($_POST['pagseguro']) || !isset($_POST['client_id'])
            || !isset($_POST['client_secret']) || !isset($_POST['email']) || !isset($_POST['token']) ){
            add_message('Algum campo esta vazio!');
            redirect('/admin/criadores/create/');
        }
        else{
            $id = $this->admin_model->addCriador($_POST);
            redirect("/admin/criadores/view/".$id);
        }
    }
    
    public function save(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(isset($_SESSION['editcriador'])){
            $dado = $_SESSION['editcriador'];
            if(!isset($_POST['name']) || !isset($_POST['mercadopago']) || !isset($_POST['pagseguro']) || !isset($_POST['client_id'])
                || !isset($_POST['client_secret']) || !isset($_POST['email']) || !isset($_POST['token']) ){
                add_message('Algum campo esta vazio!');
                redirect('/admin/criadores/edit/'.$dado["id"]);
            }
            
            $this->admin_model->updateCriador($dado["id"], $_POST);
            redirect("/admin/criadores/view/".$dado["id"]);
            unset($_SESSION['editcriador']);
        }
        else{
            redirect('/admin/criadores/');
        }
    }
}