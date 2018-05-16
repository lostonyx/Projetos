<?php
class Plugins extends MY_Controller {
    
    function __construct() {
        parent::__construct();
        $this->load->library('pagination');
        $this->load->model('client_model');
        $this->load->model('plugin_model');
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
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/menu');
        $this->load->view('/admin/plugins/index', $this->default['content']);
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
        
        $pesquisa = $this->plugin_model->select($id);
        $this->default['content']['plugins'] =  $pesquisa;
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/plugins/edit', $this->default['content']);
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
        
        $pesquisa = $this->plugin_model->select($id);
        $this->default['content']['plugins'] =  $pesquisa;
        
        $this->load->view('/admin/header');
        $this->load->view('/admin/plugins/view', $this->default['content']);
        $this->load->view('/admin/footer');
    }
    
    public function add(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->view('/admin/header');
        $this->load->view('/admin/plugins/add');
        $this->load->view('/admin/footer');
    }
    
    public function del($id = 0){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->plugin_model->delete($id);
        add_message('Plugin deletado com sucesso!');
        redirect('/admin/plugins/');
    }
    
    public function create(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(!isset($_POST['name']) || !isset($_POST['tipo']) || !isset($_POST['criador']) || !isset($_POST['preco'])
            || !isset($_POST['descricao']) || !isset($_FILES['pluginjar']) || !isset($_POST['pago'])){
                add_message('Algum campo esta vazio!');
                redirect('/admin/plugins/add/');
        }
        else{
            $file = $_FILES['pluginjar'];
            $conf = array(
                'upload_path' => './jars/',
                'allowed_types' => 'jar',
                'file_name' => strtolower($_POST['name']).'.jar',
            );
            $conf['overwrite'] = TRUE;
            $this->load->library('upload');
            $this->upload->initialize($conf);
            if($this->upload->do_upload('pluginjar')){
                $id = $this->plugin_model->createPlugin($_POST);
                redirect("/admin/plugins/view/".$id);
            }
            else{
                add_message($this->upload->display_errors());
                redirect('/admin/plugins/add/');
            }
        }
    }
    
    public function save(){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(isset($_SESSION['editplugin'])){
            $dado = $_SESSION['editplugin'];
            if(!isset($_POST['name']) || !isset($_POST['tipo']) || !isset($_POST['criador']) || !isset($_POST['preco'])
                || !isset($_POST['descricao']) || !isset($_FILES['pluginjar']) || !isset($_POST['pago'])){
                    add_message('Algum campo esta vazio!');
                    redirect('/admin/plugins/edit/'.$dado["id"]);
            }
            $id = $dado["id"];
            $file = $_FILES['pluginjar'];
            $conf = array(
                'upload_path' => './jars/',
                'allowed_types' => 'jar',
                'file_name' => strtolower($_POST['name']).'.jar',
            );
            $conf['overwrite'] = TRUE;
            $this->load->library('upload');
            $this->upload->initialize($conf);
            if($this->upload->do_upload('pluginjar')){
                $this->plugin_model->editPlugin($id , $_POST);
                redirect("/admin/plugins/view/".$id);
                unset($_SESSION['editplugin']);
            }
            else{
                add_message($this->upload->display_errors());
                redirect('/admin/plugins/edit/'.$dado["id"]);
                unset($_SESSION['editplugin']);
            }
            
        }
        else{
            redirect('/admin/plugins/');
        }
    }
    
    public function  baixar($id = 0){
        if (!$this->my_auth->check('admin', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        if (!$id){
            $id = 0;
        }
        $id = urldecode($id);
        $this->load->library('util');
        $this->load->model('client_model');
        $this->load->model('plugin_model');
        $info = $this->plugin_model->select($id);
        $nome = $info[0]->nome;
        $this->load->helper('download');
        force_download('jars/'.strtolower($nome).'.jar', NULL);
        redirect("/admin/plugins");
        
    }
}