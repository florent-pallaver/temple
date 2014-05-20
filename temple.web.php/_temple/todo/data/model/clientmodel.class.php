<?php

namespace data\model ;

class ClientModel extends AbstractModel {

	public static $primaryKey = 'id' ;

	public static $insertables = array('id', 'firstName', 'lastName', 'adWordsEmail', 'email', 'phone') ;

	public static $updatables = array('firstName', 'lastName', 'adWordsEmail', 'email', 'phone') ;

	public static $formatters = array('firstName' => Model::STRING_FORMATTER, 'lastName' => Model::STRING_FORMATTER, 
		'adWordsEmail' => Model::STRING_FORMATTER, 'email' => Model::STRING_FORMATTER, 'phone' => Model::STRING_FORMATTER) ;

	public static $autoFetch = array('business' => array('class' => 'BusinessModel', 'foreignKey' => 'id')) ;
		
	public $id ;
	
	public $firstName ;
	
	public $lastName ;
	
	public $adWordsEmail ;
	
	public $email ;
	
	public $phone ;
	
	public $business ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}
	
}

?>