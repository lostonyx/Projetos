<?php
function removeSpecialChars($str){
    return strtr($str, "???????��������������������������������������������������������������%$\"\!?:;-+,<>/&(){}[]@.", "SOZsozYYuAAAAAAACEEEEIIIIDNOOOOOOUUUUYsaaaaaaaceeeeiiiionoooooouuuuyy-------------------------");
}
?>
