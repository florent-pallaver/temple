<?php

namespace data\model ;

use \data\Domain ;
use \persistence\ManyToOne ;

class AccountModel extends AbstractModel {

	public static $primaryKey = array('identityId', 'domain') ;

	public static $fields = array('identityId', 'domain', 'accountDomain') ;

	public static $updatables = array('alive') ;

	public static $formatters = array('accountDomain' => Model::VALUE_OR_NULL_FORMATTER) ;

	public static $relations ;
	
	public $identityId ;
	
	public $domain ;
	
	public $accountDomain = null ;
	
	public $creationTime ;
	
	public $alive ;
	
	public $parentAccount ;
	
	public function getPrimaryKey() {
		return array('identityId' => $this->identityId, 'domain' => $this->domain) ;
	}
	
	public function isEmailAccount() {
		return Domain::MAIL_MAX_ID >= $this->domain && $this->domain >= Domain::MAIL_MIN_ID ; 
	}
	
	public function isSocialAccount() {
		return Domain::SOCIAL_MAX_ID >= $this->domain && $this->domain >= Domain::SOCIAL_MIN_ID  ; 
	}
	
	private static function _init() {
		self::$relations = array() ;
		self::$relations['parentAccount'] = new ManyToOne('parentAccount', true, true, 'AccountModel', array('identityId', 'accountDomain')) ;
	}
	
}

?>