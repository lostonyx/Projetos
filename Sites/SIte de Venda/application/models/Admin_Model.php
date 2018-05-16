<?php
class Admin_Model extends CI_Model{
    
    function __construct() {
        $this->load->database();
        parent::__construct();
    }
    
    /**
     * Adiciona um novo administrador
     * @param <array> $post
     * @return <int>
     */
    function add($post){
        $data = array(
            'name' => ($post['name']),
            'username' => ($post['username']),
            'password' => md5($post['password']));
        $this->db->insert('admin', $data);
        return $this->db->insert_id();
    }
    
    /**
     * Atualiza os dados de um administrador
     * @param <array> $post
     */
    function update($post){
        $data = array(
            'username' => ($post['username']),
            'password' => md5($post['password']));
        $this->db->where('id', (int)$post['id']);
        $this->db->update('admin', $data);
    }
    
    function updatesenha($id, $post){
        $data = array(
            'password' => md5($post['new']));
        $this->db->where('id', (int)$id);
        $this->db->update('admin', $data);
    }
    
    function updateip($idplugin, $iduser, $post){
        $data = array(
            'ip' => $post['ip']);
        $this->db->where('id_plugin', (int)$idplugin);
        $this->db->where('id_user', (int)$iduser);
        $this->db->update('plugins_comprado', $data);
    }
    
    /**
     * Exclui um administrador atrav�s do ID
     * @param <int> $id
     */
    function delete($id){
        $this->db->where('id', (int)$id);
        $this->db->delete('admin');
    }
    
    /**
     * Seleciona um administrador atrav�s do ID
     * @param <int> $id
     * @return <ActiveRecord>
     */
    function select($id){
        $this->db->where('id', (int)$id);
        $query = $this->db->get('admin');
        return $query->result();
    }
    
    /**
     * Seleciona os administradores, buscando por nome, e tamb�m com op��es
     * para pagina��o
     * @param <string> $name
     * @param <int> $ini
     * @param <int> $qtd
     * @return <ActiveRecord>
     */
    function selectAll($name = null, $ini = 0, $qtd = 0){
        if ($name != null && $name != 'null'){
            $this->db->like('username', $name);
        }
        if ($qtd != 0){
            $this->db->limit($qtd, $ini);
        }
        $this->db->order_by('username', 'asc');
        $query = $this->db->get('admin');
        return $query->result();
    }
    
    function getChangeLog(){
        $query = $this->db->get('changelog');
        return $query->result();
    }
    
    /**
     * Busca um administrador pelo usu�rio e senha
     * @param <string> $username
     * @param <string> $password
     * @return <ActiveRecord>
     */
    function login($username, $password){
        $this->db->where('username', $username);
        $this->db->where('password', md5($password));
        $query = $this->db->get('admin');
        return $query->result();
    }
    
    function selectByUsarname($name = null){
        if ($name != null && $name != 'null'){
            $this->db->like('username', $name);
        }
        $this->db->limit(1);
        $this->db->where('username', $name);
        $query = $this->db->get('admin');
        return $query->result();
    }
    
    function getPluginUser($id){//$this->my_auth->get('user', 'name')
        $this->db->where('id_user', (int)$id);
        $query = $this->db->get('plugins_comprado');
        return $query->result();
    }
    
    function getPluginUserAndIdPlugin($id, $idplugin){//$this->my_auth->get('user', 'name')
        $this->db->where('id_user', (int)$id);
        $this->db->where('id_plugin', (int)$idplugin);
        $query = $this->db->get('plugins_comprado');
        return $query->result();
    }
    
    function selectLogUser($id){
        $this->db->where('id_user', (int)$id);
        $query = $this->db->get('user_log');
        return $query->result();
    }
    
    function addLogUser($id, $texto, $tipo){
        $data = array(
            'id_user' => ($id),
            'texto' => ($texto),
            'tipo' => ($tipo),
            'data' => (date('Y-m-d H:i:s'))
        );
        $this->db->insert('user_log', $data);
        return $this->db->insert_id();
    }
    
    function getTransa(){
        $this->db->order_by('id', 'desc');
        $query = $this->db->get('user_transacoes');
        return $query->result();
    }
    
    ///// Criadores
    
    function selectCriadores(){
        $this->db->order_by('id', 'desc');
        $query = $this->db->get('criador');
        return $query->result();
    }
    
    function selectCriador($id){
        $this->db->where('id', (int)$id);
        $query = $this->db->get('criador');
        return $query->result();
    }
    
    function deleteCriador($id){
        $this->db->where('id', (int)$id);
        $this->db->delete('criador');
    }
    
    function updateCriador($id,$post){
        $data = array(
            'nome' => ($post['name']),
            'mercadopagoID' => ($post['client_id']),
            'mercadopagoSECRET' => ($post['client_secret']),
            'pagseguroEmail' => ($post['email']),
            'pagseguroToken' => ($post['token']),
            'mercadopago' => ($post['mercadopago']),
            'pagseguro' => ($post['pagseguro']) 
        );
        $this->db->where('id', (int)$id);
        $this->db->update('criador', $data);
    }
    
    function addCriador($post){
        $data = array(
            'nome' => ($post['name']),
            'mercadopagoID' => ($post['client_id']),
            'mercadopagoSECRET' => ($post['client_secret']),
            'pagseguroEmail' => ($post['email']),
            'pagseguroToken' => ($post['token']),
            'mercadopago' => ($post['mercadopago']),
            'pagseguro' => ($post['pagseguro'])
        );
        $this->db->insert('criador', $data);
        return $this->db->insert_id();
    }
}