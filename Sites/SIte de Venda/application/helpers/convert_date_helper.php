<?php
function convert_date($date, $to = "d-m-Y H:i:s"){
    if ($date == "0000-00-00 00:00:00"){
        return "--";
    }
    return date($to, strtotime($date));
}
?>
