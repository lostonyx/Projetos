<?php

class Loja extends MY_Controller
{

    function __construct()
    {
        parent::__construct();
        $this->load->model('loja_model');
        $this->load->model('plugin_model');
    }

    public function index()
    {
        $this->load->model('loja_model');
        $this->load->view('header');
        $this->load->view('/loja/view');
        $this->load->view('footer');
    }

    public function pago($pag = 0)
    {
        if (!$this->my_auth->check('user', array('id', 'name', 'username'))){
            $config['num_links'] = 2;
            
            $this->load->model('loja_model');
            $this->load->library('pagination');
            
            $total_plugins = $this->loja_model->selectAll(null, 0, 0);
            $this->default['content']['plugins'] = $this->loja_model->selectAllPago(null, $pag, 12);
            
            $config['base_url'] = base_url() . '/loja/pago/';
            $config['total_rows'] = sizeof($total_plugins);
            $config['per_page'] = 12;
            $config['uri_segment'] = 3;
            
            // custom paging configuration
            $config['use_page_numbers'] = TRUE;
            $config['reuse_query_string'] = TRUE;
            
            $config['full_tag_open'] = '<ul class="pagination justify-content-end">';
            $config['full_tag_close'] = '</ul>';
            
            $config['first_link'] = 'Primeira';
            $config['first_tag_open'] = '<li class="page-item"><span class="page-link">';
            $config['first_tag_close'] = '</span><li>';
            
            $config['last_link'] = 'Last Page';
            $config['last_tag_open'] = '<li class="page-item">';
            $config['last_tag_close'] = '</li>';
            
            $config['next_link'] = '';
            $config['next_tag_open'] = '';
            $config['next_tag_close'] = '';
            
            $config['prev_link'] = '';
            $config['prev_tag_open'] = '';
            $config['prev_tag_close'] = '';
            
            $config['cur_tag_open'] = '<li class="page-item">';
            $config['cur_tag_close'] = '</li>';
            
            $config['num_tag_open'] = '<li class="page-item">';
            $config['num_tag_close'] = '</li>';
            
            $this->pagination->initialize($config);
            $this->default['content']['pagination'] = $this->pagination->create_links();
            
            $this->load->view('header');
            $this->load->view('/loja/pago', $this->default['content']);
            $this->load->view('footer');
        }
        else{
            $config['num_links'] = 2;
            
            $this->load->model('loja_model');
            $this->load->library('pagination');
            
            $total_plugins = $this->loja_model->selectAll(null, 0, 0);
            $this->default['content']['plugins'] = $this->loja_model->selectAllPago(null, $pag, 12);
            
            $config['base_url'] = base_url() . '/loja/pago/';
            $config['total_rows'] = sizeof($total_plugins);
            $config['per_page'] = 12;
            $config['uri_segment'] = 3;
            
            // custom paging configuration
            $config['use_page_numbers'] = TRUE;
            $config['reuse_query_string'] = TRUE;
            
            $config['full_tag_open'] = '<ul class="pagination justify-content-end">';
            $config['full_tag_close'] = '</ul>';
            
            $config['first_link'] = 'Primeira';
            $config['first_tag_open'] = '<li class="page-item"><span class="page-link">';
            $config['first_tag_close'] = '</span><li>';
            
            $config['last_link'] = 'Last Page';
            $config['last_tag_open'] = '<li class="page-item">';
            $config['last_tag_close'] = '</li>';
            
            $config['next_link'] = '';
            $config['next_tag_open'] = '';
            $config['next_tag_close'] = '';
            
            $config['prev_link'] = '';
            $config['prev_tag_open'] = '';
            $config['prev_tag_close'] = '';
            
            $config['cur_tag_open'] = '<li class="page-item">';
            $config['cur_tag_close'] = '</li>';
            
            $config['num_tag_open'] = '<li class="page-item">';
            $config['num_tag_close'] = '</li>';
            
            $this->pagination->initialize($config);
            $this->default['content']['pagination'] = $this->pagination->create_links();
            
            
            $this->load->view('header');
            $this->load->view('/loja/pago_logado', $this->default['content']);
            $this->load->view('footer');
        }
    }

    public function free($pag = 0)
    {
        $config['num_links'] = 2;
        
        $this->load->model('loja_model');
        $this->load->library('pagination');
        
        $total_plugins = $this->loja_model->selectAll(null, 0, 0);
        $this->default['content']['plugins'] = $this->loja_model->selectAllFree(null, $pag, 12);
        
        $config['base_url'] = base_url() . '/loja/free/';
        $config['total_rows'] = sizeof($total_plugins);
        $config['per_page'] = 12;
        $config['uri_segment'] = 3;
        
        // custom paging configuration
        $config['use_page_numbers'] = TRUE;
        $config['reuse_query_string'] = TRUE;
        
        $config['full_tag_open'] = '<ul class="pagination justify-content-end">';
        $config['full_tag_close'] = '</ul>';
        
        $config['first_link'] = 'Primeira';
        $config['first_tag_open'] = '<li class="page-item"><span class="page-link">';
        $config['first_tag_close'] = '</span><li>';
        
        $config['last_link'] = 'Last Page';
        $config['last_tag_open'] = '<li class="page-item">';
        $config['last_tag_close'] = '</li>';
        
        $config['next_link'] = '';
        $config['next_tag_open'] = '';
        $config['next_tag_close'] = '';
        
        $config['prev_link'] = '';
        $config['prev_tag_open'] = '';
        $config['prev_tag_close'] = '';
        
        $config['cur_tag_open'] = '<li class="page-item">';
        $config['cur_tag_close'] = '</li>';
        
        $config['num_tag_open'] = '<li class="page-item">';
        $config['num_tag_close'] = '</li>';
        
        $this->pagination->initialize($config);
        $this->default['content']['pagination'] = $this->pagination->create_links();
        
        $this->load->view('header');
        $this->load->view('/loja/free', $this->default['content']);
        $this->load->view('footer');
    }

    public function detalhes($nome = null)
    {
        if (isset($_POST['nome'])) {
            redirect('/loja/detalhes/' . $_POST['nome'] . '/0');
        } else if (! $nome) {
            $nome = 'null';
        }
        $nome = urldecode($nome);
        
        $this->load->model('loja_model');
        $pesquisa = $this->loja_model->selectByName(strtolower($nome));
        $this->default['content']['dados'] = $this->loja_model->selectByName(strtolower($nome));
        
        if (sizeof($pesquisa) == 1) {
            $this->load->view('header');
            $this->load->view('/loja/detalhes.php', $this->default['content']);
            $this->load->view('footer');
        } else {
            $this->load->view('header');
            $this->load->view('/erro.php');
            $this->load->view('footer');
        }
    }

    public function checkout($nome = null)
    {
        if (isset($_POST['nome'])) {
            redirect('/loja/checkout/' . $_POST['nome'] . '/0');
        } else if (! $nome) {
            $nome = 'null';
        }
        $nome = urldecode($nome);
        
        $this->load->model('loja_model');
        $pesquisa = $this->loja_model->selectByName(strtolower($nome));
        $this->default['content']['dados'] = $this->loja_model->selectByName(strtolower($nome));
        
        if (sizeof($pesquisa) == 1) {
            if (! $this->my_auth->check('user', array(
                'id',
                'name',
                'username'
            ))) {
                $_SESSION['page-redirect'] = $this->uri->segment(1) . '/' . $this->uri->segment(2) . '/' . $this->uri->segment(3) . '/';
                redirect('client/login');
            }
            $this->load->view('header');
            $this->load->view('/loja/checkout.php', $this->default['content']);
            $this->load->view('footer');
        } else {
            $this->load->view('header');
            $this->load->view('/erro.php');
            $this->load->view('footer');
        }
    }

    public function pay()
    {
        if (! $this->my_auth->check('user', array(
            'id',
            'name',
            'username'
        ))) {
            redirect('client/login');
        }
        if (! isset($_POST['gateway']) && ! isset($_POST['id'])) {
            redirect('/');
        }
        $reference = $this->my_auth->get('user', 'id') . ";" . $_POST['id'];
        if ($_POST['gateway'] == "pagseguro") {
            $this->load->model('loja_model');
            $resultado = $this->loja_model->select((int) $_POST['id']);
            if (sizeof($resultado) == 1) {
                foreach ($resultado as $dado) {
                    $result = $this->plugin_model->getCriador($dado->criador_id);
                    $dados = $result[0];
                    require_once 'lib/PagSeguroLibrary/PagSeguroLibrary.php';
                    //$pc = new PagSeguroConfigWrapper();
                    //print_r ($pc->getEnvironment());
                    $ps = new PagSeguroPaymentRequest();
                    $psc = new PagSeguroAccountCredentials($dados->pagseguroEmail, $dados->pagseguroToken);
                    $ps->addItem($dado->nome, $dado->nome, 1, $dado->preco);
                    $ps->setCurrency("BRL");
                    $ps->setReference($reference);
                    $ps->addParameter('notificationURL', base_url() . "/ipn/pagseguro/".$dado->criador_id);
                    $url = $ps->register($psc);
                    header("Location: " . $url);
                }
            } else {
                redirect('/');
            }
        }
        if ($_POST['gateway'] == "paypal") {
            redirect('/');
        }
        if ($_POST['gateway'] == "mercadopago") {
            $resultado = $this->loja_model->select((int) $_POST['id']);
            if (sizeof($resultado) == 1) {
                foreach ($resultado as $dado) {
                    $result = $this->plugin_model->getCriador($dado->criador_id);
                    $dados = $result[0];
                    require_once 'lib/mercadopago.php';
                    $preference_data = array(
                        "items" => array(
                            array(
                                "title" => "" . $dado->nome,
                                "description" => "Compra do plugin " . $dado->nome,
                                "quantity" => 1,
                                "currency_id" => "BRL",
                                "unit_price" => floatval($dado->preco)
                            )
                        ),
                        "external_reference" => $reference
                    );
                    $mp = new MP($dados->mercadopagoID, $dados->mercadopagoSECRET);
                    $pref = $mp->create_preference($preference_data);
                    redirect($pref["response"]["init_point"]);
                }
            } else {
                redirect('/');
            }
        }
    }
    
    public function baixar()
    { 
       echo "teste";
    }

    /*public function debug()
    { // usar no plugin
        foreach ($_POST as $key => $value) {
            echo "<tr>";
            echo "<td>";
            echo $key;
            echo "</td>";
            echo "<td>";
            echo $value;
            echo "</td>";
            echo "</tr>";
            echo "<br>";
        }
    }*/
}
