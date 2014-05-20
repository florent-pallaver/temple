<?php

namespace data\model ;

use \persistence\ManyToOne ;

class LeadTaskModel extends AbstractModel {

	public static $table = 'lead_task' ;

	public static $primaryKey = 'businessId' ;

	public static $insertables = array('businessId', 'userId') ;

	public static $updatables = array('done') ;

	// public static $autoFetch = array('business' => array('class' => 'BusinessModel'), 
		// 'managerAccount' => array('class' => 'ManagerAccountModel', 'foreignKey' => 'userId')) ;
	
	public static $relations ;
	
	public $businessId ;

	public $userId ;

	public $done ;
	
	public $managerAccount ;
	
	public function getPrimaryKey() {
		return $this->businessId ;
	}
	
	private static function _init() {
		self::$relations = array() ;
		self::$relations['managerAccount'] = new ManyToOne('managerAccount', true, false, 'ManagerAccountModel', 'userId') ;
	}
	
}

?>