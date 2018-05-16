<?php
class Session_Model extends CI_Model{
    
    function __construct() {
        parent::__construct();
    }
    
    /**
     * Cadastra uma nova sessão e retorna o id da mesma
     * @param <array> $post
     * @return <int>
     */
    function add($post){
        $data = array('ip' => $post['ip'],
            'infopc' => $post['infopc'],
            'createdat' => date('Y-m-d H:i:s'),
            'userid' => $post['userid']
        );
        $this->db->insert('session', $data);
        return $this->db->insert_id();
    }
    
    function selectCustomerSessions(){
        $this->db->select('cl.id, se.infopc');
        $this->db->from('customer_log cl');
        $this->db->join('session se', 'se.id = cl.sessionid', 'left');
        $this->db->group_by('se.id');
        $query = $this->db->get();
        return $query->result();
    }
    
}
?>
