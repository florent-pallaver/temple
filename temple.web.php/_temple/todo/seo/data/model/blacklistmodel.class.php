<?php

namespace data\model ;

class BlackListModel extends AbstractModel {

	public static $insertables = array('pattern', 'regex') ;

	public static $formatters = array('pattern' => Model::STRING_FORMATTER) ;

	public $id ;

	public $pattern ;

	public $regex ;

	public $creationTime ;
	
	public function getPrimaryKey() {	
		return $this->id ;
	}

}

?>
