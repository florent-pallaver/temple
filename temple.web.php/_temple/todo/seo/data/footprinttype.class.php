<?php

namespace data ;

final class FootprintType {

	const FORUM = 0 ;
	
	const ARTICLE_DIRECTORY = 10 ;
	
	const PRESS_RELEASE = 20 ;

	const BLOG = 50 ;
	
	private static $all ;

	private $id ;

	private $description ;
	
	private function __construct($id, $description) {
		$this->id = $id ;
		$this->description = $description ;
	}

	public function getId() {
		return $this->id ;
	}
	
	public function getDescription() {
		return $this->description ;
	}
	
	public static function get($id) {
		return self::$all[$id] ;
	}
	
	public static function getAll() {
		return self::$all ;
	}
	
	private static function _init() {
		self::$all = array() ;
	
		self::$all[0] = new FootprintType(0, 'Forum') ;
		self::$all[10] = new FootprintType(10, 'Article Directory') ;
		self::$all[20] = new FootprintType(20, 'Press Release') ;
		self::$all[50] = new FootprintType(50, 'Blog') ;

	}
	
}

?>