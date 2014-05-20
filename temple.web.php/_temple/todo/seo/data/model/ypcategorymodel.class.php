<?php

namespace data\model ;

class YPCategoryModel extends AbstractModel {

	public static $insertables = array('stateId', 'name', 'url') ;
	
	public static $formatters = array('name' => Model::STRING_FORMATTER, 'url' => Model::STRING_FORMATTER) ;

	public $id ;

	public $stateId ;
	
	public $name ;
	
	public $url ;

	public function getPrimaryKey() {
		return $this->id ;
	}

}

?>