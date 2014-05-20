<?php

namespace data\model ;

use \persistence\ManyToOne ;

class ClientTaskModel extends AbstractModel {

	public static $table = 'client_task' ;

	public static $primaryKey = 'clientId' ;

	public static $insertables = array('clientId', 'userId') ;

	public static $updatables = array('done') ;

	// public static $autoFetch = array('client' => array('class' => 'ClientModel'), 
		// 'managerAccount' => array('class' => 'ManagerAccountModel', 'foreignKey' => 'userId')) ;
	
	public $clientId ;

	public $userId ;

	public $done ;
	
	public $client ;
	
	public $managerAccount ;
	
	public function getPrimaryKey() {
		return $this->clientId ;
	}
	
	private static function _init() {
		self::$relations = array() ;
		self::$relations['managerAccount'] = new ManyToOne('managerAccount', true, false, 'ManagerAccountModel', 'userId') ;
	}
	
}

?>