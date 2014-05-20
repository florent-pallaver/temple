<?php

namespace data\model ;

class PhoneFilterModel implements Model {

	public static $table = 'phone_filter' ;

	public $id ;
	
	public $region ;
	
	public $format ;

	public $type ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}
	
}

?>