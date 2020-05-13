<?php

namespace temple\web\mail;

abstract class Mailer {

	use \temple\ToImplement;

	protected static $host = 'localhost';
	protected static $port = 465;
	protected static $user;
	protected static $userName = '';
	protected static $password = '';
	protected static $templatePath;

	public static $subjectPrefix = '' ;
	
        /**
         * @param Mail $mail
         * @throws MailerException
         */
        public abstract function sendMail(Mail $mail);
        
	/**
	 * 
	 * @param type $subject
	 * @param type $content
	 * @param type $email
	 * @param type $nickname
	 * @throws MailerException
	 */
	public abstract function mailTemplate($subject, $content, $email, $nickname = '');

	/**
	 * 
	 * @param type $subject
	 * @param type $content
	 * @param type $email
	 * @param type $nickname
	 * @throws MailerException
	 */
	public abstract function mail($subject, $content, $email, $nickname = '');

	/**
	 * 
	 * @param type $subject
	 * @param type $content
	 * @param array $tos
	 * @param array $ccs
	 * @param array $bccs
	 * @throws \temple\web\mail\MailerException
	 */
	public abstract function mailMany($subject, $content, array $tos, array $ccs = [], array $bccs = []);

	/**
	 * 
	 * @param type $subject
	 * @param type $content
	 * @param array $tos
	 * @param array $ccs
	 * @param array $bccs
	 * @throws \temple\web\mail\MailerException
	 */
	public abstract function mailManyTemplate($subject, $content, array $tos, array $ccs = [], array $bccs = []);

	/**
	 * 
	 * @param type $user
	 * @param type $password
	 * @param type $host
	 * @param type $port
	 * @param type $userName
	 * @param type $impl
	 */
	public static function config($user, $password, $host, $port = 465, $userName = '', $impl = 'phpmailer') {
		self::$SUB_NAMESPACE = _dif($impl, self::$SUB_NAMESPACE);
		self::$host = $host;
		self::$port = $port;
		self::$user = $user;
		self::$userName = $userName;
		self::$password = $password;
	}

	private static function _init() {
		self::$user = $_SERVER['SERVER_ADMIN'];
		self::$templatePath = '.' . \temple\web\Config::CUSTOM_RESOURCE_PATH . 'mailTemplate.html';
	}

}
