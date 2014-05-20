<?php

namespace data ;

final class RegistrationType {

	const UNDEFINED = 0 ;
	
	const IMPOSSIBLE = 1 ;
	
	const UNABLE_TO_RESOLVE = 2 ;

	const ERROR = 3 ;
	
	private static $all ;

	private $id ;

	private $folder ;
	
	private $description ;
	
	private function __construct($id, $folder, $description) {
		$this->id = $id ;
		$this->folder = Action::GENERIC_FOLDER . $folder ;
		$this->description = $description ;
	}

	public function getId() {
		return $this->id ;
	}
	
	public function getActionFolder() {
		return $this->folder ;
	}
	
	public function getActionCommonFolder() {
		return $this->folder . Action::COMMON_FOLDER ;
	}
	
	public function getDescription() {
		return $this->description ;
	}
	
	public static function get($registrationType) {
		return self::$all[$registrationType] ;
	}
	
	public static function getAll() {
		return self::$all ;
	}
	
	private static function _init() {
		self::$all = array() ;
	
		self::$all[0] = new RegistrationType(0, 'undefined/', 'Undefined') ;
		self::$all[1] = new RegistrationType(1, 'impossible/', 'Impossible') ;
		self::$all[2] = new RegistrationType(2, 'unknown/', 'Unable to Resolve') ;
		self::$all[3] = new RegistrationType(3, 'error/', 'Error') ;

		self::$all[5] = new RegistrationType(5, 'vbulletin/birthdate/', 'vBulletin | Birthdate First') ;
		self::$all[6] = new RegistrationType(6, 'vbulletin/agreement/', 'vBulletin | Agreement First') ;
		self::$all[7] = new RegistrationType(7, 'vbulletin/basic/', 'vBulletin | Basic') ;
		self::$all[8] = new RegistrationType(8, 'vbulletin/random/', 'vBulletin | With Random Question') ;

	}
	
}

?>