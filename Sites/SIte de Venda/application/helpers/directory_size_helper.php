<?php
function directory_size($path) {
    $infos = array('tamanho_arquivo' => 0,
                   'tamanho_total' => 0,
                   'total_pastas' => 0);
    if ($dir = opendir($path)) {
        while (false !== ($file = readdir($dir))) {
            if (is_dir($path . "/" . $file)) {
                if ($file != '.' && $file != '..') {
                    $inf = directory_size($path . "\\" . $file);
                    $infos['total_pastas']++;
                    $infos['total_pastas'] = $infos['total_pastas'] + $inf['total_pastas'];
                    $infos['tamanho_arquivo'] = $infos['tamanho_arquivo'] + $inf['tamanho_arquivo'];
                    $infos['tamanho_total'] = $infos['tamanho_total'] + $inf['tamanho_total'];
                }
            } else {
                $infos['tamanho_total'] = $infos['tamanho_total'] + filesize($path . '/' . $file);
                $infos['tamanho_arquivo']++;
            }
        }
        closedir($dir);
    }
    
    return $infos;
}

function delTree($dirname) {
    if (is_dir($dirname))
        $dir_handle = opendir($dirname);
    if (!$dir_handle)
        return false;
    while ($file = readdir($dir_handle)) {
        if ($file != "." && $file != "..") {
            if (!is_dir($dirname . "\\" . $file)){
                unlink($dirname . "\\" . $file);
            }
            else{
                delTree($dirname . '\\' . $file);
            }
        }
    }
    closedir($dir_handle);
    rmdir($dirname);
    return true;
}
?>