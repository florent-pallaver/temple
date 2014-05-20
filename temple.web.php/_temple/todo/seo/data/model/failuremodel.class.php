<?php

namespace data\model ;

class FailureModel extends AbstractModel {

	public static $primaryKey = 'failureTime' ;

	public static $insertables = array('id', 'actionId', 'domainId', 'step') ;

	public $failureTime ;

	public $id ;

	public $actionId ;

	public $domainId ;

	public $step ;
	
	public function getPrimaryKey() {
		return $this->failureTime ;
	}

}

?>