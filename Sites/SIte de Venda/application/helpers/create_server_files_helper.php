<?php
function create_server_files($base, $portbase, $serverid){
    fopen($base.'assets\\logs\\access_'.$portbase.'.log', 'w+');
    fopen($base.'assets\\logs\\error_'.$portbase.'.log', 'w+');
    
    mkdir($base.'assets\\musics\\server_'.$serverid);
    mkdir($base.'assets\\musics\\server_'.$serverid.'\\audio');
    mkdir($base.'assets\\musics\\server_'.$serverid.'\\audio\\musica');
    mkdir($base.'assets\\musics\\server_'.$serverid.'\\audio\\vinheta');
    mkdir($base.'assets\\musics\\server_'.$serverid.'\\audio\\comercial');
    mkdir($base.'assets\\musics\\server_'.$serverid.'\\audio\\podcast');
    mkdir($base.'assets\\musics\\server_'.$serverid.'\\playlist');
}
?>
