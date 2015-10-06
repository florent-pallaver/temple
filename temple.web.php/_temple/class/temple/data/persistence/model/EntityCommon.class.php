<?php

namespace temple\data\persistence\model;

/**
 * Description of EntityCommon
 *
 * @author florent
 */
trait EntityCommon {
	
	use \temple\Reflection ;

	/**
	 * @var array
	 */
	private static $mappings = [] ;

	private static function setTransientMapping($fieldName) {
		self::$mappings[$fieldName] = null ;
	}
	
	private static function setBasicMapping($fieldName, $insertable = true, $updatable = true, FieldConverter $converter = null, array $constraints = [], $colName = null) {
		self::$mappings[$fieldName] = new BasicMapping(self::_property($fieldName), $insertable, $updatable, $converter, $constraints, $colName) ;
	}

	private static function setDateTimeMapping($fieldName, $insertable = true, $updatable = true, array $constraints = [], $colName = null) {
		self::$mappings[$fieldName] = BasicMapping::createDateTime(self::_property($fieldName), $insertable, $updatable, $constraints, $colName) ;
	}

}
