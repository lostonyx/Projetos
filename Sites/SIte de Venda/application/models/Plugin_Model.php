<?php
class Plugin_Model extends CI_Model{
    
    function __construct() {
        $this->load->database();
        parent::__construct();
    }
    
    function add($post){
        $data = array(
            'nome' => $post['nome'],
            'descricao' => $post['descricao'],
            'preco' => $post['preco'],
            'tipo' => $post['tipo'],
            'base64' => $post['base64']
                );
        $this->db->insert('plugins', $data);
        return $this->db->insert_id();
    }
    
    function update($post){
        $data = array(
            'username' => ($post['username']),
            'password' => md5($post['password']));
        $this->db->where('id', (int)$post['id']);
        $this->db->update('admin', $data);
    }
    
    function delete($id){
        $this->db->where('id', (int)$id);
        $this->db->delete('plugins');
    }
    
    function select($id){
        $this->db->where('id', (int)$id);
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
    function selectByName($nome){
        $this->db->where('nome', $nome);
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
    function selectAll(){
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
    function selectComprado($user, $produto){
        $this->db->where('id_user', (int)$user);
        $this->db->where('id_plugin', (int)$produto);
        $query = $this->db->get('plugins_comprado');
        return $query->result();
    }
    
    function selectAllComprado(){
        $query = $this->db->get('plugins_comprado');
        return $query->result();
    }
    
    function editPlugin($id, $post){
        $data = array(
            'nome' => ($post['name']),
            'tipo' => ($post['tipo']),
            'criador_id' => ($post['criador']),
            'preco' => ($post['preco']),
            'descricao' => ($post['descricao']),
            'pago' => ($post['pago'])
        );
        $this->db->where('id', (int)$id);
        $this->db->update('plugins', $data);
    }
    
    function createPlugin($post){
        $data = array(
            'nome' => ($post['name']),
            'tipo' => ($post['tipo']),
            'criador_id' => ($post['criador']),
            'preco' => ($post['preco']),
            'descricao' => ($post['descricao']),
            'pago' => ($post['pago'])
        );
        $this->db->insert('plugins', $data);
        return $this->db->insert_id();
    }
    
    function getCriador($id){
        $this->db->where('id', (int)$id);
        $query = $this->db->get('criador');
        return $query->result();
    }
    
    function getCriadorAll(){
        $query = $this->db->get('criador');
        return $query->result();
    }
    /*
     * `id`, `user_id`, `plugin_id`, `data`, `gateway`, `status`, `codigo`
     */
    function criarPagamento( $usuario, $produto, $preco, $gateway, $status, $codigo ){
        if($gateway == "MercadoPago"){
            if($status == approved){
                $user_transacoes = array(
                    'user_id' => ($usuario),
                    'plugin_id' => ($produto),
                    'preco' => ($preco),
                    'data' => (date('Y-m-d H:i:s')),
                    'gateway' => ($gateway),
                    'status' => ("Aprovado"),
                    'codigo' => ($codigo)
                );
                $plugins_comprado = array(
                    'id_plugin' => ($produto),
                    'id_user' => ($usuario),
                    'ativo' => ('1'),
                    'ip' => ('127.0.0.1')
                );
                $this->db->insert('plugins_comprado', $plugins_comprado);
                $this->db->insert('user_transacoes', $user_transacoes);
                //return $this->db->insert_id();
            }
            else{
                $data = array(
                    'user_id' => ($usuario),
                    'plugin_id' => ($produto),
                    'preco' => ($preco),
                    'data' => (date('Y-m-d H:i:s')),
                    'gateway' => ($gateway),
                    'status' => "Recusado",
                    'codigo' => ($codigo)
                );
                $this->db->insert('user_transacoes', $data);
                //return $this->db->insert_id();
            }
        }
        if($gateway == "PagSeguro"){
            if($status == 3){
                $user_transacoes = array(
                    'user_id' => ($usuario),
                    'plugin_id' => ($produto),
                    'preco' => ($preco),
                    'data' => (date('Y-m-d H:i:s')),
                    'gateway' => ($gateway),
                    'status' => ("Aprovado"),
                    'codigo' => ($codigo)
                );
                $plugins_comprado = array(
                    'id_plugin' => ($produto),
                    'id_user' => ($usuario),
                    'ativo' => ('1'),
                    'ip' => ('127.0.0.1')
                );
                $this->db->insert('plugins_comprado', $plugins_comprado);
                $this->db->insert('user_transacoes', $user_transacoes);
            }
            else{
                $data = array(
                    'user_id' => ($usuario),
                    'plugin_id' => ($produto),
                    'preco' => ($preco),
                    'data' => (date('Y-m-d H:i:s')),
                    'gateway' => ($gateway),
                    'status' => "Recusado",
                    'codigo' => ($codigo)
                );
                $this->db->insert('user_transacoes', $data);
            }
        }
    }
    
}
