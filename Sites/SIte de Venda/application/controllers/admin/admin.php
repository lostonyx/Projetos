<?php
class Admin extends MY_Controller {
    
    function __construct() {
        parent::__construct();
        
        $this->load->model('client_model');
        $this->load->model('plugin_model');
        $this->load->model('changelog_model');
        $this->load->model('admin_model');
        $this->adminid = $this->my_auth->get('admin', 'id');
        $id = $this->my_auth->get('admin', 'id');
        $this->admini = $this->admin_model->select($id);
    }
    
    public function index()
    {
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->default['content']['plugins'] = $this->plugin_model-> selectAll();
        $this->default['content']['lis'] = $this->plugin_model-> selectAllComprado();
        $this->default['content']['avisos'] = $this->client_model-> getAvisos();
        $this->default['content']['trans'] = $this->admin_model-> getTransa();
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/painel/view', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function change(){//mudar
        if(!isset($_POST['now']) || !isset($_POST['new']) || !isset($_POST['repeat'])){
            add_message('Algum campo esta vazio!');
            redirect('/admin/admin');
        }
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $login = $this->admin_model->login($this->my_auth->get('admin', 'username'), $_POST['now']);
        if(sizeof($login) == 1){
            if(($_POST['new']) != ($_POST['repeat'])){
                add_message('As senhas nao estao iguais.');
                redirect('/admin/admin');
            }
            else {
                if(strlen($_POST['new']) < 6){
                    add_message(strlen($_POST['new']) < 6 ? "Pass Minimo de letra e 6" : "");
                    redirect('/admin/admin');
                }
                $this->client_model->updatesenha($this->my_auth->get('admin', 'id'), $_POST);
                add_message('Senha mudada com sucesso!');
                $this->client_model->addLogUser($this->my_auth->get('admin', 'id'), "Mudou a senha.", "Conta");
                redirect('/admin/admin');
            }
        }
        else{
            add_message('Senha atual invalida');
            redirect('/admin/admin');
        }
    }
    
    public function transactions(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->default['content']['trans'] = $this->admin_model->getTransa();
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/painel/transactions', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function logs(){//editar
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->default['content']['logs'] = $this->client_model->selectLogAll();
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/painel/logs', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function docs()
    {
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/painel/docs');
        $this->load->view('/admin/footer');
    }
    
    function logout(){
        $this->my_auth->destroy("admin");
        
        unset($_SESSION['page-redirect']);
        
        redirect('/');
    }
}