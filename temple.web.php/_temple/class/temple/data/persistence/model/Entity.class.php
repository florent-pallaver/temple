<?php

namespace temple\data\persistence\model;

/**
 * Description of Entity
 *
 * @author florent
 */
trait Entity {

	use EntityCommon;

	/**
	 * @var string
	 */
	public static $TABLE;

	/**
	 * @var Key
	 */
	private static $PK;

	public static function getPK() {
		if (!self::$PK) {
			self::$PK = new Key(self::_class(), ['id'], true);
		}
		return self::$PK;
	}

	private static function _initEntity() {
		$class = self::_class();
		ProxyGenerator::getInstance()->generate($class);
		self::$TABLE = Config::$TABLE_PREFIX . strtolower($class->getShortName());
	}

}
