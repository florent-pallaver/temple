<?php

namespace data\model ;

use \DateTime ;
use \DateInterval ;

class FootprintModel extends AbstractModel {

	public static $insertables = array('query', 'type', 'queryFor') ;

	public static $updatables = array('queriedOn') ;

	public static $formatters = array('query' => Model::STRING_FORMATTER) ;

	private static $patterns = array() ;
	
	private static $replacements = array() ;
	
	public $id ;

	public $query ;

	public $type ;

	public $creationTime ;
	
	public $queryFor ;
	
	public $queriedOn ;
	
	public function getPrimaryKey() {
		return $this->id ;
	}
	
	public function getResolvedQuery() {
		return preg_replace(self::$patterns, self::$replacements, $this->query) ;
	}

	public static final function _init() {
		self::$patterns = array() ;
		self::$patterns[] = '/@Y@/' ;
		self::$patterns[] = '/@M Y@/' ;
		self::$patterns[] = '/@PM Y@/' ;

		$now = new DateTime() ;

		self::$replacements = array() ;
		self::$replacements[] = '"' . $now->format('Y') . '"' ;
		self::$replacements[] = '"' . $now->format('F Y') . '"' ;

		$now->sub(new DateInterval('P1M')) ;

		self::$replacements[] = '"' . $now->format('F Y') . '"' ;
	}
	
}

?>
