<?php
class MY_Auth {
    
    private $secure = true;
    
    function __construct(){
        if (isset($_POST['PHPSESSID'])){
            
            setcookie("PHPSESSID", $_POST['PHPSESSID'], time()+3600, "/");
            $_COOKIE['PHPSESSID'] = $_POST['PHPSESSID'];
            
            session_write_close();
            session_id($_POST['PHPSESSID']);
            session_name($_POST['PHPSESSNAME']);
            @session_start();
            
            session_write_close();
            
            session_id($_POST['PHPSESSID']);
            session_name($_POST['PHPSESSNAME']);
            @session_start();
            
            $this->secure = false;
        }
        @session_start();
    }

    public function create($id, $arr){
        session_regenerate_id();
                
        $_SESSION[$id] = $arr;
        $_SESSION[$id]['security_var'] = $this->hashSecurity($id);
    }

    public function check($id, $arr){
        if (isset($_SESSION[$id])){
            foreach ($arr as $ar){
                if (!isset($_SESSION[$id][$ar])){
                    $string = 'TESTES '.$id.' '.$ar;
                    $h = fopen('c:\wamp\www\debug5.txt', 'w+');
                    fwrite($h, $string);
                    fclose($h);
                    return false;
                }
            }
            if ($this->secure == true){
                if ($_SESSION[$id]['security_var'] == $this->hashSecurity($id)){
                    return true;
                }
            }
            else {
                return true;
            }
        }
        return false;
    }

    public function destroy($id){
        if (isset($_SESSION[$id])){
            unset($_SESSION[$id]);
        }
    }

    private function hashSecurity($id){
        return base64_decode(sha1(md5($id.'firstapp'.$_SERVER['HTTP_USER_AGENT'])));
    }

    function get($id, $index = null){
        if (isset($_SESSION[$id])){
            if ($index != null){
                return $_SESSION[$id][$index];
            }
            return $_SESSION[$id];
        }
        return null;
    }
}
?>
