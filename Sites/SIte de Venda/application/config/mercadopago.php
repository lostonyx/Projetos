<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*
|
| https://www.mercadopago.com.ar/developers/es/solutions/payments/basic-checkout/receive-payments/
|
*/

// Custom Checkout
$config['app_id'] = 'd'; // not used by the Library
$config['public_key'] = 'd';  // not used by the Library
$config['access_token'] = 'd';
$config['use_access_token'] = FALSE; // TRUE or FALSE

// Basic Checkout
$config['client_id'] = '2725225290741486';
$config['client_secret'] = 'M5QoxRAqUbESkS8Do9u3ldOsZjpjPPMX';

// Sandbox
$config['sandbox_mode'] = TRUE; // TRUE or FALSE


/* End of file mercadopago.php */
/* Location: ./application/config/mercadopago.php */