<?php
class Client extends MY_Controller {
    
    function __construct() {
        parent::__construct();
        $this->load->library('pagination');
        $this->load->model('client_model');
        $this->userid = $this->my_auth->get('user', 'id');
        $id = $this->my_auth->get('user', 'id');
        $this->useri = $this->client_model->select($id);	
    }
    
    public function index()
    {
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->model('client_model');
        $this->default['content']['trans'] = $this->client_model->selectTransUser($this->my_auth->get('user', 'id'));
        $this->default['content']['plugins'] = $this->client_model->getPluginUser($this->my_auth->get('user', 'id'));
        $this->default['content']['avisos'] = $this->client_model->getAvisos();
        
        $this->load->view('/client/header');
        $this->load->view('/client/panel/view', $this->default['content']);
        $this->load->view('/client/footer');
    }
    
    public function change(){
        if(!isset($_POST['now']) || !isset($_POST['new']) || !isset($_POST['repeat'])){
            add_message('Algum campo esta vazio!');
            redirect('client/');
        }
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->default['content']['plugins'] = $this->client_model->getPluginUser($this->my_auth->get('user', 'id'));
        $login = $this->client_model->login($this->my_auth->get('user', 'username'), $_POST['now']);
        if(sizeof($login) == 1){
            if(($_POST['new']) != ($_POST['repeat'])){
                add_message('As senhas nao estao iguais.');
                redirect('client/');
            }
            else {
                if(strlen($_POST['new']) < 6){
                    add_message(strlen($_POST['new']) < 6 ? "Pass Minimo de letra e 6" : "");
                    redirect('client/');
                }
                $this->client_model->updatesenha($this->my_auth->get('user', 'id'), $_POST);
                add_message('Senha mudada com sucesso!');
                $this->client_model->addLogUser($this->my_auth->get('user', 'id'), "Mudou a senha.", "Conta");
                redirect('client/');
                //$this->client_model->add($_POST);
            }
        }
        else{
            add_message('Senha atual invalida');
            redirect('client/');
        }
    }
    
    public function plugins()
    {
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->model('client_model');
        
        $this->default['content']['plugins'] = $this->client_model->getPluginUser($this->my_auth->get('user', 'id'));
        
        $this->load->view('/client/header');
        $this->load->view('/client/panel/plugins', $this->default['content']);
        $this->load->view('/client/footer');
    }
    
    public function changelog(){
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->default['content']['logs'] = $this->client_model->getChangeLog();
        
        $this->load->model('client_model');
        $this->load->view('/client/header');
        $this->load->view('/client/panel/changelog', $this->default['content']);
        $this->load->view('/client/footer');
    }
    
    public function transactions(){
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        
        $this->default['content']['trans'] = $this->client_model->selectTransUser($this->my_auth->get('user', 'id'));
        
        $this->load->model('client_model');
        $this->load->view('/client/header');
        $this->load->view('/client/panel/transactions', $this->default['content']);
        $this->load->view('/client/footer');
    }
    
    public function logs(){
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->default['content']['logs'] = $this->client_model->selectLogUser($this->my_auth->get('user', 'id'));
        
        $this->load->model('client_model');
        $this->load->view('/client/header');
        $this->load->view('/client/panel/log', $this->default['content']);
        $this->load->view('/client/footer');
    }
    
    public function preferencia($id = 0){
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if (!$id){
            $id = 0;
        }
        $id = urldecode($id);
        $this->load->library('util');
        $this->load->model('client_model');
        if(isset($_POST['ip'])){
            if($this->util->is_ip($_POST['ip']) == true){
                add_message('Preferencias salva!');
                $this->client_model->updateip($id,$this->my_auth->get('user', 'id'),$_POST);
                $this->client_model->addLogUser($this->my_auth->get('user', 'id'), "Mudou ip do plugin.", "Plugin");
                redirect('/client/preferencia/'.$id);
            }
            else{
                add_message('Coloque ip correto!');
                redirect('/client/preferencia/'.$id);
            }
        }
        
        $plugin = $this->client_model->getPluginUserAndIdPlugin($this->my_auth->get('user', 'id'), $id);
        $this->default['content']['dados'] = $plugin;
        
        if(sizeof($plugin) == 1){
            $this->load->view('/client/header');
            $this->load->view('/client/panel/pref', $this->default['content']);
            $this->load->view('/client/footer');
        }
        else{
            redirect("client/");
        }
    }
    
    public function  baixar($id = 0){
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
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
        $plugin = $this->plugin_model->selectComprado($this->my_auth->get('user', 'id'), $id);
        if(sizeof($plugin) == 1){
            //echo "tem";
            $info = $this->plugin_model->select($id);
            $nome = $info[0]->nome;
            $this->load->helper('download');
            force_download('jars/'.strtolower($nome).'.jar', NULL);
            redirect("/client/plugins");
        }
        else{
            redirect("/client/plugins");
        }
    }
    
    public function gerarkey(){
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        if(isset($_SESSION['keyinfo'])){
            $dado = $_SESSION['keyinfo'];
            $this->load->library('util');
            $key = $this->util->gerarkey($dado["user_id"], $dado["ip"], $dado["nome"]);
            add_message("Sua key sera : ".$key);
            $this->client_model->addLogUser($this->my_auth->get('user', 'id'), "Gerou a key.", "Plugin");
            redirect("/client/preferencia/".$dado["id"]);
            unset($_SESSION['keyinfo']);
        }
        
       
    }
    
    public function docs()
    {
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('/client/login/');
            die();
        }
        $this->load->view('/client/header');
        $this->load->view('/client/panel/docs');
        $this->load->view('/client/footer');
    }
    
    public function login()
    {
        if ($this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('client/');
        }
        
        $this->load->view('header');
        $this->load->view('/client/login');
        $this->load->view('footer');
    }
    
    public function criar()
    {
        if ($this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('client/');
        }
        
        $this->load->view('header');
        $this->load->view('/client/register');
        $this->load->view('footer');
    }
    
    public function autenticar()
    {
        if ($this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('client/');
        }
        
        $this->load->model('session_model');
        $this->load->model('client_model');
        
        $login = $this->client_model->login($_POST['username'], $_POST['password']);
        
        if (sizeof($login) == 1){
            $data = array('ip' => $_SERVER['REMOTE_ADDR'],
                'infopc' => $_SERVER['HTTP_USER_AGENT'],
                'userid' => $login[0]->id
            );
            $sessionid = $this->session_model->add($data);
            
            $arr['id']        = $login[0]->id;
            $arr['name']      = $login[0]->name;
            $arr['username']  = $login[0]->username;
            $arr['sessionid'] = $sessionid;
            
            $this->my_auth->create('user', $arr);
            $this->client_model->addLogUser($this->my_auth->get('user', 'id'), " Logou na conta.", "Conta");
            if(isset($_SESSION['page-redirect'])){
                redirect($_SESSION['page-redirect']);
            }
            else{
                redirect('client/');
            }
        }
        else {
            $this->load->model('admin_model');
            
            $logina = $this->admin_model->login($_POST['username'], $_POST['password']);
            
            if (sizeof($logina) == 1){
                $data = array('ip' => $_SERVER['REMOTE_ADDR'],
                    'infopc' => $_SERVER['HTTP_USER_AGENT'],
                    'userid' => $logina[0]->id
                );
                $sessionid = $this->session_model->add($data);
                
                $arr['id']        = $logina[0]->id;
                $arr['name']      = $logina[0]->name;
                $arr['username']  = $logina[0]->username;
                $arr['sessionid'] = $sessionid;
                
                $this->my_auth->create('admin', $arr);
                //$this->client_model->addLogUser($this->my_auth->get('user', 'id'), " Logou na conta.", "Conta");
                if(isset($_SESSION['page-redirect'])){
                    redirect($_SESSION['page-redirect']);
                }
                else{
                    redirect('/admin/admin/');
                }
            }
            else{
                add_message('Usuario ou Senha invalida.');
                redirect('client/login/');
            }
        }
    }
    
    public function registrar()
    {
        
        if(!isset($_POST['username']) && !isset($_POST['password']) && !isset($_POST['repeat']) && !isset($_POST['name'])){
            add_message('Algum campo esta vazio!');
            redirect('client/criar');
        }
        
        if ($this->my_auth->check('user', array('id', 'name', 'username'))){
            redirect('client/');
        }
        
        $this->load->model('session_model');
        $this->load->model('client_model');
        $pesquisa = $this->client_model->selectByUsarname(strtolower($_POST['username']));
      
        if (sizeof($pesquisa) == 1){
            add_message('Ja existe uma conta com esse username.');
            redirect('client/criar');
        }
        else {
            if(($_POST['password']) != ($_POST['repeat'])){
                add_message('As senhas nao estao iguais.');
                redirect('client/criar');
            }
            else {
                if(strlen($_POST['password']) < 6){
                    add_message(strlen($_POST['password']) < 6 ? "Pass Minimo de letra e 6" : "");
                    add_message(strlen($_POST['username']) < 6 ? "User Minimo de letra e 6" : "");
                    redirect('client/criar');
                }
                if(strlen($_POST['username']) < 6){
                    add_message(strlen($_POST['password']) < 6 ? "Pass Minimo de letra e 6" : "");
                    add_message(strlen($_POST['username']) < 6 ? "User Minimo de letra e 6" : "");
                    redirect('client/criar');
                }
                $this->client_model->add($_POST);
                
                $login = $this->client_model->login($_POST['username'], $_POST['password']);
                
                if (sizeof($login) == 1){
                    $data = array('ip' => $_SERVER['REMOTE_ADDR'],
                        'infopc' => $_SERVER['HTTP_USER_AGENT'],
                        'userid' => $login[0]->id
                    );
                    $sessionid = $this->session_model->add($data);
                    
                    $arr['id']        = $login[0]->id;
                    $arr['name']      = $login[0]->name;
                    $arr['username']  = $login[0]->username;
                    $arr['sessionid'] = $sessionid;
                    
                    $this->my_auth->create('user', $arr);
                    
                    if(isset($_SESSION['page-redirect'])){
                        redirect($_SESSION['page-redirect']);
                    }
                    else{
                        redirect('client/');
                    }
                }
            }
        }
    }
    
    function logout(){
        $this->my_auth->destroy("user");
        
        unset($_SESSION['page-redirect']);
        
        redirect('/');
    }
}