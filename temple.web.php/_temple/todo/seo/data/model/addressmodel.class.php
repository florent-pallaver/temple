<?php

namespace data\model ;

class AddressModel extends AbstractModel {

	public static $insertables = array('state', 'city', 'postcode') ;

	public static $formatters = array('state' => Model::STRING_FORMATTER, 'city' => Model::STRING_FORMATTER, 'postcode' => Model::STRING_FORMATTER) ;

	public $id ;

	public $state ;

	public $city ;

	public $postcode ;

	public function getPrimaryKey() {
		return $this->id ;
	}

}

?>