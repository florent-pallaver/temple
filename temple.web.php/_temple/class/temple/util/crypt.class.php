<?php

namespace temple\util ;

final class Crypt {
	
	public static $KEY_PREFIX = '$1$Dx1.QP1.$aoZDkJ' ;
	
	private function __construct() {} 
	
	public static function encrypt($str, $salt = '') {
		return base64_encode(mcrypt_encrypt(MCRYPT_RIJNDAEL_128, self::$KEY_PREFIX . $salt, $str, MCRYPT_MODE_ECB)) ;
	}
	
	public static function decrypt($str, $salt = '') {
		return mcrypt_decrypt(MCRYPT_RIJNDAEL_128, self::$KEY_PREFIX . $salt, base64_decode($str), MCRYPT_MODE_ECB) ;
	}
	
}

?>