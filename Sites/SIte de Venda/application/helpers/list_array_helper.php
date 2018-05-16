<?php
function list_array($array, $class, $id = false){
    if ($id){ $id = 'id="'.$id.'"'; }
    echo '<ul class="'.$class.'" '.$id.'>';
    foreach ($array as $name=>$item){
        if (is_array($item)){
            $class = strtolower(removeSpecialChars($name));
            echo '<li class="item-pasta">'.$name.'';
            list_array($item, '');
            echo '</li>';
        }
        else {
            echo '<li class="item-mp3">'.$item.'</li>';
        }
    }
    echo '</ul>';
}
?>
