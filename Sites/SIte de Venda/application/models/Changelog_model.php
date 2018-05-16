<?php
class Changelog_Model extends CI_Model{
    
    function __construct() {
        $this->load->database();
        parent::__construct();
    }
    
    function add($post){
        $data = array(
            'texto' => $post['texto'],
            'data' => date('Y-m-d')
        );
        $this->db->insert('changelog', $data);
        return $this->db->insert_id();
    }
    
    function update($id,$post){
        $data = array(
            'texto' => $post['texto']);
        $this->db->where('id', (int)$id);
        $this->db->update('changelog', $data);
    }
    
    function delete($id){
        $this->db->where('id', (int)$id);
        $this->db->delete('changelog');
    }
    
    function select($id){
        $this->db->where('id', (int)$id);
        $query = $this->db->get('changelog');
        return $query->result();
    }
    
    function selectTodos(){
        $query = $this->db->get('changelog');
        return $query->result();
    }
    
}