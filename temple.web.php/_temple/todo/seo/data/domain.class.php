<?php

namespace data ;

final class Domain {

	const MAIL_MIN_ID = 0 ;
	
	const MAIL_MAX_ID = 4 ;
	
	const SOCIAL_MIN_ID = 128 ;
	
	const SOCIAL_MAX_ID = 129 ;

	const PROXY_MIN_ID = 192 ;

	const PROXY_MAX_ID = 193 ;

	private static $names = array() ;
	
	private static $signUpURLs = array() ;

	private static $signInURLs = array() ;

	private static $signOutURLs = array() ;

	private static $mailServers = array() ;
	
	private static $mailPorts = array() ;
	
	private function __construct() {}
	
	public static function getName($id) {
		return self::$names[$id] ;
	}
	
	public static function getSignUpURL($id) {
		return self::$signUpURLs[$id] ;
	}
	
	public static function getSignInURL($id) {
		return self::$signInURLs[$id] ;
	}
	
	public static function getSignOutURL($id) {
		return self::$signOutURLs[$id] ;
	}
	
	private static function _init() {
		// Yahoo
		self::$names[0] = 'yahoo.com.au' ; 

		self::$signUpURLs[0] = 'https://au.edit.yahoo.com/registration' ;
		self::$signInURLs[0] = 'https://login.yahoo.com/config/login_verify2?.intl=au&.src=ym&.done=http://au.mail.yahoo.com/' ;
		self::$signOutURLs[0] = 'http://au.yahoo.com/' ;

		self::$mailServers[0] = 'pop.mail.yahoo.com' ;
		self::$mailPorts[0] = 995 ;

		// Hotmail
		self::$names[1] = 'hotmail.com' ;

		self::$signUpURLs[1] = 'https://signup.live.com' ;
		self::$signInURLs[1] = 'https://mail.live.com/' ;
		self::$signOutURLs[1] = 'https://mail.live.com/' ;

		self::$mailServers[1] = 'pop3.live.com' ;
		self::$mailPorts[1] = 995 ;

		// Outlook
		self::$names[2] = 'outlook.com' ;

		self::$signUpURLs[2] = 'https://signup.live.com' ;
		self::$signInURLs[2] = 'https://mail.live.com/' ;
		self::$signOutURLs[2] = 'https://mail.live.com/' ;

		self::$mailServers[2] = self::$mailServers[1] ;
		self::$mailPorts[2] = self::$mailPorts[1] ;

		// Gmail
		self::$names[3] = 'gmail.com' ;

		// Mail
		self::$names[4] = 'mail.com' ; 

		self::$signInURLs[4] = 'http://www.mail.com/int/' ;
		self::$signUpURLs[4] = 'https://service.mail.com/registration.html' ;
		self::$signInURLs[4] = 'http://www.mail.com/int/' ;
	}
	
}

?>