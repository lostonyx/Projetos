<?php
function create_path_links($links){
    echo '<div id="links-topo">';
         foreach ($links as $t => $l){
             if ($l != null){
                echo anchor($l, $t, 'class="back-link"').' > ';
             }
             else {
                 echo '<span class="atual">'.$t.'</span>';
             }
         }
    echo '</div>';
}
?>
