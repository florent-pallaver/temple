<?php

namespace temple\web\mail;

/**
 * Description of Mailer
 *
 * @author florent
 */
abstract class Mailer {

	use \temple\ToImplement ;
	
	protected static $host = 'localhost';
	protected static $port = 465;
	protected static $user ;
	protected static $userName = '';
	protected static $password = '';
	protected static $templatePath;

	/**
	 * 
	 * @param type $subject
	 * @param type $content
	 * @param type $email
	 * @param type $nickname
	 * @throws MailerException
	 */
	public abstract function mailTemplate($subject, $content, $email, $nickname = '') ;

	/**
	 * 
	 * @param type $subject
	 * @param type $content
	 * @param type $email
	 * @param type $nickname
	 * @throws MailerException
	 */
	public abstract function mail($subject, $content, $email, $nickname = '') ;

	/**
	 * 
	 * @param type $user
	 * @param type $password
	 * @param type $host
	 * @param type $port
	 * @param type $userName
	 * @param type $impl
	 */
	public static function config($user, $password, $host, $port, $userName = '', $impl = 'phpmailer') {
		self::$SUB_NAMESPACE = _dif($impl, self::$SUB_NAMESPACE) ;
		self::$host = $host;
		self::$port = $port;
		self::$user = $user;
		self::$userName = $userName;
		self::$password = $password;
	}

	private static function _init() {
		self::$user = $_SERVER['SERVER_ADMIN'] ;
		self::$templatePath = '.' . \temple\web\Config::CUSTOM_RESOURCE_PATH . 'mailTemplate.html';
	}

}
