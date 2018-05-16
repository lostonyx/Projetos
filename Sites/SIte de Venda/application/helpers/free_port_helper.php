<?php
function free_port($arrobj){
    $start = 8000;
    foreach ($arrobj as $obj){
        if ($start < $obj->portbase){
            return $start;
        }
        else {
            $start+=2;
        }
    }
    return $start;
}
?>
