<?php
class Changelog extends MY_Controller {
    
    function __construct() {
        parent::__construct();
        $this->load->library('pagination');
        $this->load->model('changelog_model');
        $this->adminid = $this->my_auth->get('admin', 'id');
    }
    
    public function index()
    {
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->default['content']['changelog'] = $this->changelog_model->selectTodos();
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/changelog/index', $this->default['content']);
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
        
        $pesquisa = $this->changelog_model->select($id);
        $this->default['content']['logs'] =  $pesquisa;
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/changelog/edit', $this->default['content']);
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
        
        $pesquisa = $this->changelog_model->select($id);
        $this->default['content']['logs'] =  $pesquisa;
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/changelog/view', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function add(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->view('/admin/header');
        $this->load->view('/admin/changelog/add');
        $this->load->view('/admin/footer');
    }
    
    public function del($id = 0){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->changelog_model->delete($id);
        add_message('Changelog deletado com sucesso!');
        redirect('/admin/changelog/');
    }
    
    public function create(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(!isset($_POST['texto'])){
                add_message('Algum campo esta vazio!');
                redirect('/admin/changelog/create/');
        }
        else{
            $id = $this->changelog_model->add($_POST);
            redirect("/admin/changelog/view/".$id);
        }
    }
    
    public function save(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(isset($_SESSION['editchangelog'])){
            $dado = $_SESSION['editchangelog'];
            if(!isset($_POST['texto'])){
                    add_message('Algum campo esta vazio!');
                    redirect('/admin/changelog/edit/'.$dado["id"]);
            }
            $this->changelog_model->update($dado["id"], $_POST);
            redirect("/admin/changelog/view/".$dado["id"]);
            unset($_SESSION['editchangelog']);
        }
        else{
            redirect('/admin/changelog/');
        }
    }
}