<?php

class ServiceConnection
{

    protected $_api_url = NULL;
    protected $_api_token = NULL;
    protected $_api_role = 'guest';

    protected $_cookie = NULL;

    public function __construct($options)
    {
        if (!extension_loaded('curl')) {
            throw new Exception('cURL extension is not enabled');
        }

        if (isset($options['api_url'])) {
            $this->_api_url = $options['api_url'];
        }

        if (isset($options['api_role'])) {
            $this->_api_role = $options['api_role'];
        }

        if (isset($options['api_token'])) {
            $this->_api_token = $options['api_token'];
        }

        $this->_cookie = sys_get_temp_dir() . '/bbcookie.txt';
    }

    public function __call($method, $arguments)
    {
        $data = array();
        if (isset($arguments[0]) && is_array($arguments[0])) {
            $data = $arguments[0];
        }

        $module = substr($method, 0, strpos($method, '_'));
        $m = substr($method, strpos($method, '_') + 1);

        $url = $this->_api_url . '/' . $this->_api_role . '/' . $module . '/' . $m;

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($ch, CURLOPT_USERPWD, $this->_api_role . ":" . $this->_api_token);
        curl_setopt($ch, CURLOPT_COOKIEJAR, $this->_cookie);
        curl_setopt($ch, CURLOPT_COOKIEFILE, $this->_cookie);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($data));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $result = curl_exec($ch);

        if ($result === false) {
            $e = new Exception(sprintf('Curl Error: "%s"', curl_error($ch)));
            curl_close($ch);
            throw $e;
        }

        curl_close($ch);

        $json = json_decode($result);

        if (!is_object($json)) {
            throw new Exception(sprintf('ServiceConnection: Invalid Response "%s"', $result));
        }

        if (isset($json->error) && !empty($json->error)) {
            throw new Exception(sprintf('ServiceConnection method "%s" returned error: "%s"', $method, $json->error->message), $json->error->code);
        }

        if (!isset($json->result)) {
            throw new Exception(sprintf('ServiceConnection: Invalid Response "%s"', $result));
        }

        return $json->result;
    }

}