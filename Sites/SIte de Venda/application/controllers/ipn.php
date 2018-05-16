<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class ipn extends MY_Controller {
    
    function __construct() {
        parent::__construct();
        //error_reporting(0);
        //ini_set('display_errors', 0);
    }
    
    public function index($id = 0)
    {
        $this->load->view('header');
        $this->load->view('home');
        $this->load->view('footer');
    }
    
    public function pagseguro($id = 0)
    {
        $this->output->set_header('HTTP/1.0 200 OK');
        $this->output->set_header('HTTP/1.1 200 OK');
        error_reporting(0);
        ini_set('display_errors', 0);
        if (!$id){
            $id = 0;
        }
        $id = urldecode($id);
        require_once 'lib/PagSeguroLibrary/PagSeguroLibrary.php';
        $this->load->model('plugin_model');
        $this->load->library('util');
        if($_POST['notificationType'] and $_POST['notificationType'] == 'transaction'){
            $resu = $this->plugin_model->getCriador($id);
            if($resu[0]->pagseguro == 1){
                if(sizeof($resu) > 0){
                    $email = $resu[0]->pagseguroEmail;
                    $token = $resu[0]->pagseguroToken;
                    $url = 'https://ws.pagseguro.uol.com.br/v2/transactions/notifications/' . $_POST['notificationCode'] . '?email=' . $email . '&token=' . $token;
                    $curl = curl_init($url);
                    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
                    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
                    $transactionCurl = curl_exec($curl);
                    curl_close($curl);
                    
                    $transaction = simplexml_load_string($transactionCurl);
                    
                    if($transaction == 'Unauthorized'){
                        exit;
                    }
                    
                    $referencia = explode(';', $transaction->reference);
                    $usernamme = $referencia[0];
                    $produto = $referencia[1];
                    $codigo = $transaction->code;
                    $status = $transaction->status;
                    $this->plugin_model->criarPagamento($usernamme,$produto,"sem","PagSeguro",$status,$codigo);
                }
                else{
                    echo http_response_code(400);
                }
            }
        }
    }
    
    public function mercadopago($id = 0)
    {
        $this->output->set_header('HTTP/1.0 200 OK');
        $this->output->set_header('HTTP/1.1 200 OK');
        error_reporting(0);
        ini_set('display_errors', 0);
        require_once 'lib/mercadopago.php';
        if (!$id){
            $id = 0;
        }
        $id = urldecode($id);
        $this->load->model('plugin_model');
        $this->load->library('util');
        $resu = $this->plugin_model->getCriador($id);
        if($resu[0]->mercadopago == 1){
            if(sizeof($resu) > 0){
                $mp = new MP($resu[0]->mercadopagoID, $resu[0]->mercadopagoSECRET);
                if (!isset($_GET["id"]) || !ctype_digit($_GET["id"])) {
                    http_response_code(400);
                    return;
                }
                $payment_info = $mp->get_payment_info($_GET["id"]);
                $status = $payment_info['response']['status'];
                $preco = $payment_info['response']['transaction_amount'];
                $reference = explode(';', $payment_info['response']['external_reference']);
                $codigo = $payment_info['response']['id'];
                $usuario = $reference[0];
                $produto = $reference[1];
                print_r($payment_info['response']);
                $this->plugin_model->criarPagamento($usuario,$produto,$preco,"MercadoPago",$status,$codigo);
            }
            else{
                echo http_response_code(400);
            }
        }
    }
    
    
    
}
