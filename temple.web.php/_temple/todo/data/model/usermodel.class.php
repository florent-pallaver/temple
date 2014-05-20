<?php

namespace data\model ;

class UserModel extends AbstractModel {

	const LEVEL_LEAD_GENERATOR = 1 ;

	const LEVEL_SALES_AGENT = 4 ;

	const LEVEL_ACCOUNT_MGR = 7 ;

	const LEVEL_ADMIN = 10 ;

	public static $insertables = array('username', 'password', 'level', 'firstName', 'lastName', 'email') ;

	public static $updatables = array('username', 'password', 'level', 'firstName', 'lastName', 'email') ;

	public static $formatters = array('username' => Model::STRING_FORMATTER, 'password' => Model::STRING_FORMATTER,
		'firstName' => Model::STRING_FORMATTER, 'lastName' => Model::STRING_FORMATTER, 'email' => Model::STRING_FORMATTER) ;

	public $id ;
	
	public $username ;
	
	public $password ;

	public $level ;
	
	public $firstName ;
	
	public $lastName ;
	
	public $email ;
	
	public $creation ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}
	
}

?>