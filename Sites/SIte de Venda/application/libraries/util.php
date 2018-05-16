<?php
class util {
    
    
    function __construct(){
    }
    
    function logMsg( $msg, $level = 'info', $file = './main.log' )
    {
        $levelStr = '';
        switch ( $level )
        {
            case 'info':
                $levelStr = 'INFO';
                break;
                
            case 'warning':
                $levelStr = 'WARNING';
                break;
                
            case 'error':
                $levelStr = 'ERROR';
                break;
        }
        $date = date( 'Y-m-d H:i:s' );
        $msg = sprintf( "[%s] [%s]: %s%s", $date, $levelStr, $msg, PHP_EOL );
        file_put_contents( $file, $msg, FILE_APPEND );
    }
    
    function criptoCesar($str, $shift)
    {
        $char = range('a', 'z');
        $flip = array_flip($char);
        
        for ($i = 0; $i < strlen($str); $i++) {
            if (in_array(strtolower($str{$i}), $char)) {
                $ord = $flip[strtolower($str{$i})];
                
                $ord = ($ord + $shift) % 26;
                
                if ($ord < 0) $ord += 26;
                
                $str{$i} = ($str{$i} == strtolower($str{$i})) ? $char[$ord]
                : strtoupper($char[$ord]);
            }
        }
        
        return $str;
    }
    
    function json_validator($data=NULL) {
        
        if (!empty($data)) {
            
            @json_decode($data);
            
            return (json_last_error() === JSON_ERROR_NONE);
            
        }
        
        return false;
    }
    
    function gerarkey($id, $ip, $plugin){
        $myObj->clientid = $id;
        $myObj->ip = $ip;
        $myObj->plugin = $plugin;
        $myJSON = json_encode($myObj);
        $crifrado = $this->criptoCesar($myJSON, +5);
        return base64_encode($crifrado);
    }
    
    function is_ip( $ip = null ) {
        
        if( !$ip or strlen(trim($ip)) == 0){
            return false;
        }
        
        $ip=trim($ip);
        if(preg_match("/^[0-9]{1,3}(.[0-9]{1,3}){3}$/",$ip)) {
            foreach(explode(".", $ip) as $block)
                if($block<0 || $block>255 )
                    return false;
                    return true;
        }
        return false;
    }
}
?>
