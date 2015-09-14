<?php

namespace temple\data\persistence\model;

use \ReflectionClass;

/**
 * TODOC
 *
 * @author florent
 */
abstract class AbstractModel implements Model {

	use EntityCommon;

	/**
	 * @var Key
	 */
	public static $ID_PRIMARY_KEY ;
	
	private $id;

	public function __construct() {}

	public final function getId() {
		return $this->id;
	}

	protected final function setId($id) {
		$this->id = $id;
	}

	public function jsonSerialize() {
		$json = [] ;
		$this->setJSONFields($json) ;
		return $json ;
	}

	protected function setJSONFields(&$json) {
		$json['id'] = $this->id ;
	}

	public final function getClass() {
		return new ReflectionClass($this);
	}

	public function __toString() {
		return $this->getClass()->getName() . ' ' . json_encode($this);
	}

	private static function _init() {
		self::$ID_PRIMARY_KEY = new Key(self::_class(), 'id', true, Key::PRIMARY_KEY_NAME) ;
		self::$mappings['id'] = new BasicMapping(self::_property('id'), true, false);
	}
	
}
