<?php

namespace \data\model ;

use \persistence\ManyToOne ;

class YPBusinessModel extends AbstractModel {

	public static $insertables = array('name', 'address', 'phone', 'website', 'detailsURL') ;

	public static $relations ;
	
	public $id ;
	
	public $name ;
	
	public $address ;
	
	public $phone ;
	
	public $website ;
	
	public $detailsURL ;
	
	public $ypCategories ;

	public function getPrimaryKey() {
		return $this->id ;
	}
	
	private static function _init() {
		self::$relations = array() ;
		// self::$relations['ypCategory'] = new ManyToMany('ypCategory', true) ;
	}

}

?>