<?php

class IP2Country
{
    private $country_code;

    public function __construct($registry)
    {
        $this->request = $registry->get('request');

        $this->country_code = false;

        if (isset($this->request->server['REMOTE_ADDR'])) {
            $ip = $this->request->server['REMOTE_ADDR'];

            $numbers = preg_split("/\./", $ip);
            $ip_file = DIR_SYSTEM . 'library/ip2country/' . $numbers[0] . '.php';

            if (file_exists($ip_file)) {
                require_once($ip_file);

                $code = ($numbers[0] * 16777216) + ($numbers[1] * 65536) + ($numbers[2] * 256) + ($numbers[3]);

                foreach ($ranges as $key => $value) {
                    if ($key <= $code) {
                        if ($ranges[$key][0] >= $code) {
                            $this->country_code = strtolower($ranges[$key][1]);
                            break;
                        }
                    }
                }
            }
        }
    }

    public function getCountryCode()
    {
        return $this->country_code;
    }
}