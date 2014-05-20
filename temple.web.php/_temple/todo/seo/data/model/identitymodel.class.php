<?php

namespace data\model ;

use \persistence\OneToMany ;
use \persistence\ManyToOne ;

class IdentityModel extends AbstractModel {

	public static $insertables = array('gender', 'firstName', 'lastName', 'password', 'addressId', 'dateOfBirth', 
		'emailId', 'phone', 'randomLastName') ;

	public static $updatables = array('accountCount') ;

	public static $formatters = array('firstName' => Model::STRING_FORMATTER, 'lastName' => Model::STRING_FORMATTER, 'password' => Model::STRING_FORMATTER, 
			'dateOfBirth' => Model::STRING_FORMATTER, 'emailId' => Model::STRING_FORMATTER, 'phone' => Model::STRING_FORMATTER, 'randomLastName' => Model::STRING_FORMATTER
			) ;

	public static $relations ;
	
	public $id ;

	public $gender ;

	public $firstName ;

	public $lastName ;

	public $password ;

	public $addressId ;

	public $dateOfBirth ;

	public $emailId ;

	public $phone ;

	public $randomLastName ;

	public $accountCount ;

	public $accounts = array();

	public $address ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}

	public function getAccountByDomainId($domainId) {
		$key = $this->id . '_' . $domainId ;
		return isset($this->accounts[$key]) ? $this->accounts[$key] : null ;
	}
	
	private static function _init() {
		self::$relations = array() ;
		self::$relations['address'] = new ManyToOne('address', true) ;
		self::$relations['accounts'] = new OneToMany('accounts', true, true, 'accountmodel', 'identityId') ;
	}
	
}

?>