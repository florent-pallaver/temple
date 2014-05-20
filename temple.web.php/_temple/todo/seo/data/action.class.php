<?php

namespace data ;

final class Action {

	const ROOT_FOLDER = 'actions/' ;
	
	const GENERIC_FOLDER = 'actions/_generic/' ;

	const COMMON_FOLDER = '../_common/' ;
	
	const NAME_SUFFIX = '.actions.php' ;

	private static $all = array() ;

	private $id ;
	
	private $fileName ;
	
	private $description ;
	
	private function __construct($id, $namePrefix, $description) {
		$this->id = $id ;
		$this->fileName = $namePrefix . self::NAME_SUFFIX ;
		$this->description = $description ;
	}
	
	public function getId() {
		return $this->id ;
	}
	
	public function getFileName() {
		return $this->fileName ;
	}
	
	public function getDescription() {
		return $this->description ;
	}
	
	public static function get($id) {
		return self::$all[$id] ;
	}
	
	private static function _init() {
		self::$all[0] = new Action(0, 'signUp', 'Sign Up') ;
		self::$all[1] = new Action(1, 'signIn', 'Sign In') ;
		self::$all[2] = new Action(2, 'signOut', 'Sign Out') ;
		self::$all[50] = new Action(50, 'scrapeProxies', 'Scrape Proxies') ;
	}
	
}

?>