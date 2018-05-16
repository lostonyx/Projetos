<?php
// Session
if (session_status() === PHP_SESSION_NONE) {
    //session_start();
}

// Configuration
if (is_readable('config.php')) {
    require_once('config.php');
}

// Install
if (!defined('DIR_APPLICATION')) {
    exit('Application startup failed');
}

// Startup
require_once(DIR_SYSTEM . '/startup.php');

// Registry
$registry = new Registry();

// Loader
$loader = new Loader($registry);
$registry->set('load', $loader);

// Config
$config = new Config();
$registry->set('config', $config);

// Database
$db = new DB(DB_DRIVER, DB_HOSTNAME, DB_USERNAME, DB_PASSWORD, DB_DATABASE, DB_PORT);
$registry->set('db', $db);

// Settings
$query = $db->query("SELECT * FROM " . DB_PREFIX . "setting ORDER BY setting_id ASC");

foreach ($query->rows as $result) {
    if (!$result['serialized']) {
        $config->set($result['key'], $result['value']);
    } else {
        $config->set($result['key'], json_decode($result['value'], true));
    }
}

// Url
$url = new Url($config->get('config_url'), $config->get('config_secure') ? $config->get('config_ssl') : $config->get('config_url'));
$registry->set('url', $url);

// Log
$log = new Log($config->get('config_error_filename'));
$registry->set('log', $log);

function error_handler($code, $message, $file, $line) {
    global $log, $config;
    // error suppressed with @
    if (error_reporting() === 0) {
        return false;
    }
    switch ($code) {
        case E_NOTICE:
        case E_USER_NOTICE:
            $error = 'Notice';
            break;
        case E_WARNING:
        case E_USER_WARNING:
            $error = 'Warning';
            break;
        case E_ERROR:
        case E_USER_ERROR:
            $error = 'Fatal Error';
            break;
        default:
            $error = 'Unknown';
            break;
    }
    if ($config->get('config_error_display')) {
        echo '<b>' . $error . '</b>: ' . $message . ' in <b>' . $file . '</b> on line <b>' . $line . '</b>';
    }
    if ($config->get('config_error_log')) {
        $log->write('PHP ' . $error . ':  ' . $message . ' in ' . $file . ' on line ' . $line);
    }
    return true;
}

// Error Handler
set_error_handler('error_handler');

// Request
$request = new Request();
$registry->set('request', $request);

// Response
$response = new Response();
$response->addHeader('Content-Type: text/html; charset=utf-8');
$response->setCompression($config->get('config_compression'));
$registry->set('response', $response);

// Cache
$cache = new Cache('file');
$registry->set('cache', $cache);

// Session
if (isset($request->get['token']) && isset($request->get['route']) && startsWith($request->get['route'], 'api/')) {
    $db->query("DELETE FROM " . DB_PREFIX . "api_session WHERE TIMESTAMPADD(HOUR, 1, date_updated) < NOW()");
    $query = $db->query(""
        . "SELECT DISTINCT * FROM " . DB_PREFIX . "api a "
        . "LEFT JOIN " . DB_PREFIX . "api_session asn ON (a.api_id = asn.api_id) "
        . "LEFT JOIN " . DB_PREFIX . "api_ip ai ON (asn.api_id = ai.api_id) "
        . "WHERE a.status = '2' "
        . "AND asn.token = '" . $db->escape($request->get['token']) . "' "
        . "AND ai.ip = '" . $db->escape($request->server['REMOTE_ADDR']) . "'");
    if ($query->num_rows) {
        // Session class
        $session = new Session();
        $registry->set("api_id", $query->row['api_id']);
        $registry->set('session', $session);
        // keep the session alive
        $db->query("UPDATE " . DB_PREFIX . "api_session SET date_updated = NOW() WHERE api_session_id = '" . $query->row['api_session_id'] . "'");
    }
} else {
    $session = new Session();
    $registry->set('session', $session);
}

// Language Detection
$languages = array();

$query = $db->query("SELECT * FROM " . DB_PREFIX . "lang");
foreach ($query->rows as $result) {
    $languages[$result['code']] = $result;
}
if (isset($session->data['language']) && array_key_exists($session->data['language'], $languages)) {
    $code = $session->data['language'];
} elseif (isset($request->cookie['language']) && array_key_exists($request->cookie['language'], $languages)) {
    $code = $request->cookie['language'];
} else {
    $detect = '';
    if (isset($request->server['HTTP_ACCEPT_LANGUAGE']) && $request->server['HTTP_ACCEPT_LANGUAGE']) {
        $browser_languages = explode(',', $request->server['HTTP_ACCEPT_LANGUAGE']);
        foreach ($browser_languages as $browser_language) {
            foreach ($languages as $key => $value) {
                if (isset($value['status']) && $value['status']) {
                    $locale = explode(',', $value['locale']);
                    if (in_array($browser_language, $locale)) {
                        $detect = $key;
                        break 2;
                    }
                }
            }
        }
    }
    $code = $detect ? $detect : $config->get('config_language');
}

if (!isset($session->data['language']) || $session->data['language'] != $code) {
    $session->data['language'] = $code;
}

if (!isset($request->cookie['language']) || $request->cookie['language'] != $code) {
    setcookie('language', $code, time() + 60 * 60 * 24 * 30, '/', $request->server['HTTP_HOST']);
}
$config->set('config_language_id', $languages[$code]['language_id']);
$config->set('config_language', $languages[$code]['code']);

// Language
$language = new Language($languages[$code]['code']);
$language->load($languages[$code]['directory']);
$registry->set('language', $language);

// Document
$registry->set('document', new Document());

// Customer
$customer = new Customer($registry);
$registry->set('customer', $customer);

// Customer Group
if ($customer->isLogged()) {
    $config->set('config_customer_group_id', $customer->getGroupId());
} elseif (isset($session->data['customer']) && isset($session->data['customer']['customer_group_id'])) {
    // For API calls
    $config->set('config_customer_group_id', $session->data['customer']['customer_group_id']);
}

// Currency
$registry->set('currency', new Currency($registry));

// Cart
$registry->set('cart', new Cart($registry));

// Encryption
$registry->set('encryption', new Encryption($config->get('config_encryption')));

// Event
$event = new Event($registry);
$registry->set('event', $event);
$query = $db->query("SELECT * FROM " . DB_PREFIX . "event");
foreach ($query->rows as $result) {
    $event->register($result['trigger'], $result['action']);
}

// User
$registry->set('user', new User($registry));

// Front Controller
$controller = new Front($registry);

// Login
$controller->addPreAction(new Action('common/login/check'));

// Permission
$controller->addPreAction(new Action('error/permission/check'));

// Router
if (isset($request->get['route']) && !empty($request->get['route'])) {
    $action = new Action($request->get['route']);
} else {
    $action = new Action('common/home');
}

// Dispatch
$controller->dispatch($action, new Action('error/not_found'));

// Output
$response->output();
