<?php

function token($length = 32)
{
    // Create token to login with
    $string = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

    $token = '';

    for ($i = 0; $i < $length; $i++) {
        $token .= $string[mt_rand(0, strlen($string) - 1)];
    }

    return $token;
}

function chk_array($arr, $value)
{
    return (is_array($arr) && isset($arr[$value])) ? $arr[$value] : false;
}

function startsWith($haystack, $needle)
{
    return (strpos($haystack, $needle) === 0);
}

function is_md5($md5 = '')
{
    return preg_match('/^[a-f0-9]{32}$/', $md5);
}

function group_assoc($array, $key)
{
    $return = array();
    foreach ($array as $v) {
        $return[$v[$key]][] = $v;
    }
    return $return;
}

function get_client_country($cache)
{
    if (getenv('HTTP_CLIENT_IP'))
        $ipaddress = getenv('HTTP_CLIENT_IP');
    else if (getenv('HTTP_X_FORWARDED_FOR'))
        $ipaddress = getenv('HTTP_X_FORWARDED_FOR');
    else if (getenv('HTTP_X_FORWARDED'))
        $ipaddress = getenv('HTTP_X_FORWARDED');
    else if (getenv('HTTP_FORWARDED_FOR'))
        $ipaddress = getenv('HTTP_FORWARDED_FOR');
    else if (getenv('HTTP_FORWARDED'))
        $ipaddress = getenv('HTTP_FORWARDED');
    else if (getenv('REMOTE_ADDR'))
        $ipaddress = getenv('REMOTE_ADDR');
    else
        $ipaddress = 'UNKNOWN';
    $cache_data = $cache->get('ip.' . str_replace(".", "_", $ipaddress));
    if (empty($cache_data)) {
        $jsonData = json_decode(file_get_contents("http://ip-api.com/json/" . $ipaddress), true);
        if (isset($jsonData['countryCode'])) {
            $cache->set('ip.' . str_replace(".", "_", $ipaddress), $jsonData);
            return $jsonData['countryCode'];
        }
    } else {
        return $cache_data['countryCode'];
    }
    return false;
}