<?php


// Dir

define('DIR_ROOT', dirname(__FILE__));
define('DIR_SYSTEM', DIR_ROOT . "/system");
define('DIR_RESOURCE', DIR_ROOT . "/resource");
define('DIR_APPLICATION', DIR_ROOT . "/application");
define('DIR_DASHBOARD', DIR_ROOT . "/dashboard");

// Dir > System

define('DIR_CONFIG', DIR_SYSTEM . "/config");

// Dir > System > Storage

define('DIR_STORAGE', DIR_SYSTEM . "/storage");
define('DIR_LOGS', DIR_STORAGE . "/logs");
define('DIR_CACHE', DIR_STORAGE . "/cache/");

// Dir > Resource

define('DIR_IMAGE', DIR_RESOURCE . "/image");

// Dir > Application

define('DIR_TEMPLATE', DIR_APPLICATION . "/view");
define('DIR_LANGUAGE', DIR_APPLICATION . "/language");

/* Database */

define('DB_DRIVER', 'mysqli');
define('DB_HOSTNAME', '127.0.0.1');
define('DB_USERNAME', 'root');
define('DB_PASSWORD', '');
define('DB_DATABASE', 'mcpixel-iw4');
define('DB_PORT', '3306');
define('DB_PREFIX', 'iw4_');