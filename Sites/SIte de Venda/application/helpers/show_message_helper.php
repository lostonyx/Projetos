<?php
@session_start();
function show_message(){
    $saida = '';
    if (isset($_SESSION['helpers']['messages'])){
        $saida = '<div class="ok-msg">';
        foreach ($_SESSION['helpers']['messages'] as $msg){
            $saida .= '<p>'.$msg.'</p>';
        }
        $saida .= '</div>';
        unset($_SESSION['helpers']['messages']);
    }
    return $saida;
}

function add_message($message){
    $_SESSION['helpers']['messages'][] = $message;
}
?>
