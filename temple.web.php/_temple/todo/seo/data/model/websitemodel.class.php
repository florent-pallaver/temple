<?php

namespace data\model ;

use \persistence\ManyToOne ;

class WebsiteModel extends AbstractModel {

	public static $insertables = array('footprintId', 'domain', 'url', 'searchEngine') ;

	public static $updatables = array('registrationType', 'registrationURL', 'registrationCount', 'registrationTries') ;
	
	public static $formatters = array('domain' => Model::STRING_FORMATTER, 'url' => Model::STRING_FORMATTER, 'registrationURL' => Model::STRING_FORMATTER) ;
	
	public static $relations ; // Relation[]
	
	public $id ;
	
	public $footprintId ;

	public $domain ;
	
	public $url ;
	
	public $searchEngine ;
	
	public $registrationType ;
	
	public $registrationURL ;
	
	public $registrationCount ;
	
	public $registrationTries ;
	
	public $footprint ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}
	
	public function setDomainFromURL($url) {
		$this->domain = Util::extractDomain($url) ;
	}

	public static function _init() {
		self::$relations = array() ;
		self::$relations['footprint'] = new ManyToOne('footprint', true) ;
	}
	
}

?>
