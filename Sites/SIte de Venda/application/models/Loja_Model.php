<?php
class Loja_Model extends CI_Model{
    
    function __construct() {
        $this->load->database();
        parent::__construct();
    }
    
    function add($post){
        $data = array('nome' => ($post['nome']),
            'descricao' => ($post['descricao']),
            'preco' => ($post['preco']),
            'path' => ($post['path']));
        $this->db->insert('plugins', $data);
        return $this->db->insert_id();
    }
    
    
    function update($post){
        $data = array('nome' => ($post['nome']),
            'descricao' => ($post['descricao']),
            'preco' => ($post['preco']),
            'path' => ($post['path']));
        $this->db->where('id', (int)$post['id']);
        $this->db->update('plugins', $data);
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
    
    function selectByName($name){
        $this->db->where('nome', $name);
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
    function selectAll($nome = null, $ini = 0, $qtd = 0){
        if ($nome != null && $nome != 'null'){
            $this->db->like('nome', $nome);
        }
        if ($qtd != 0){
            $this->db->limit($qtd, $ini);
        }
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
    function selectAllPago($nome = null, $ini = 0, $qtd = 0){
        if ($nome != null && $nome != 'null'){
            $this->db->like('nome', $nome);
        }
        if ($qtd != 0){
            $this->db->limit($qtd, $ini);
        }
        $this->db->where('pago', 0);
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
    function selectAllFree($nome = null, $ini = 0, $qtd = 0){
        if ($nome != null && $nome != 'null'){
            $this->db->like('nome', $nome);
        }
        if ($qtd != 0){
            $this->db->limit($qtd, $ini);
        }
        $this->db->where('pago', 1);
        $query = $this->db->get('plugins');
        return $query->result();
    }
    
}?>