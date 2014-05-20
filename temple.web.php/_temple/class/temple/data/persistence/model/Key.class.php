<?php

namespace temple\data\persistence\model ;

use ReflectionClass ;

/**
 * TODOC
 *
 * @author florent
 */
class Key {

	const PRIMARY_KEY_NAME = 'PRIMARY_KEY' ;

	const KEY_NAME_PREFIX = 'KEY_' ;

	private static $i = 0 ;

	private $class ;

	private $unique ;

	private $name ;

	private $columns ;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param array $fieldNames
	 * @param boolean $unique
	 * @param string $name
	 */
	public function __construct(ReflectionClass $entityClass, $columns, $unique = false, $name = null) {
		$this->class = $entityClass;
		$this->columns = _eia($columns);
		$this->unique = $unique ;
		$this->name = $name ? $name : (self::KEY_NAME_PREFIX . self::$i++) ;
	}

	/**
	 * TODOC
	 * @return string
	 */
	public function getName() {
		return $this->name ;
	}

	/**
	 * TODOC
	 * @return boolean
	 */
	public function isUnique() {
		return $this->unique ;
	}

	/**
	 * TODOC
	 * @return ReflectionClass
	 */
	public function getClass() {
		return $this->class ;
	}

	/**
	 * TODOC
	 * @return array
	 */
	public function getColumnNames() {
		return $this->columns ;
	}

}
