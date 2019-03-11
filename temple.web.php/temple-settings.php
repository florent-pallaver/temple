<?php

if(!defined('ROOT_PATH')) {
	define('ROOT_PATH', '.' . DIRECTORY_SEPARATOR) ;
}

if(!defined('TEMPLE_ROOT_PATH')) {
	define('TEMPLE_ROOT_PATH', ROOT_PATH . '_temple' . DIRECTORY_SEPARATOR) ;
}

if(!defined('TEMPLE_CLASS_PATH')) {
	define('TEMPLE_CLASS_PATH', TEMPLE_ROOT_PATH . 'class' . DIRECTORY_SEPARATOR) ;
}

if(!defined('TEMPLE_RESOURCE_PATH')) {
	define('TEMPLE_RESOURCE_PATH', TEMPLE_ROOT_PATH . 'resources' . DIRECTORY_SEPARATOR) ;
}

if(!defined('CUSTOM_ROOT_PATH')) {
	define('CUSTOM_ROOT_PATH', ROOT_PATH . '_custom' . DIRECTORY_SEPARATOR) ;
}

if(!defined('CUSTOM_CLASS_PATH')) {
	define('CUSTOM_CLASS_PATH', CUSTOM_ROOT_PATH . 'class' . DIRECTORY_SEPARATOR) ;
}

if(!defined('CUSTOM_RESOURCE_PATH')) {
	define('CUSTOM_RESOURCE_PATH', CUSTOM_ROOT_PATH . 'resources' . DIRECTORY_SEPARATOR) ;
}

// Load useful functions
require_once TEMPLE_ROOT_PATH . 'util.php' ;

// Load default classes
require_once TEMPLE_CLASS_PATH . 'temple' . DIRECTORY_SEPARATOR . 'logger.class.php' ;
require_once TEMPLE_CLASS_PATH . 'temple' . DIRECTORY_SEPARATOR . 'exceptionhandler.class.php' ;

// Set the exception handler and class loader
set_exception_handler('\temple\ExceptionHandler::handle') ;
require_once TEMPLE_CLASS_PATH . 'temple' . DIRECTORY_SEPARATOR . 'classloader.class.php' ;

temple\Logger::logRequest() ;

// Set class pathes for the class loader
temple\ClassLoader::add(TEMPLE_CLASS_PATH) ;
temple\ClassLoader::add(TEMPLE_CLASS_PATH, false) ;
temple\ClassLoader::add(CUSTOM_CLASS_PATH) ;
temple\ClassLoader::add(CUSTOM_CLASS_PATH, false) ;

\temple\view\FailureView::$SHOW_STACKTRACE = true ;
