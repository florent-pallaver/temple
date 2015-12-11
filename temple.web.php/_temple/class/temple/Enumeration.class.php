<?php

namespace temple;

/**
 * TODOC
 * <br>
 * If in need to define your own <code>_init()</code> function, make sure to call <code>self::_enumInit()</code> to perform the initialization provided by this trait.
 * @author florent
 */
abstract class Enumeration implements \Serializable {

	/** @var array */
	private static $_all = [] ;
	
	/** @var number */
	private $ordinal;

	/** @var string */
	private $name;

	protected function __construct($name) {
		if(!isset(self::$_all[static::class])) {
			self::$_all[static::class] = [] ;
		}
		$this->ordinal = count(self::$_all[static::class]) ;
		$this->name = $name ;
		self::$_all[static::class][] = $this ;
	}

	/**
	 * @return int
	 */
	public final function ordinal() {
		return $this->ordinal;
	}

	/**
	 * @return string
	 */
	public final function name() {
		return $this->name;
	}

	public function __toString() {
		return $this->name ;
	}
	
	/**
	 * 
	 * @return string TODOC
	 */
	public function toLocalizedString() {
		$localeClass = static::class . 'Locale' ;
		return $localeClass::$all[$this->ordinal] ;
	}
	
	/**
	 * 
	 * @param mixed $other
	 * @return boolean
	 */
	public final function equals($other) {
//		$c = static::class ;
//		Logger::getInstance($c)->info( $other instanceof $c ? strval($this) . ' ' . strval($other) : 'non') ;
		return $other !== null && (static::class === get_class($other)) && ($this->ordinal == $other->ordinal) ;
	}
	
	/**
	 * 
	 * @param array $list
	 * @return boolean if this object is one of the given 
	 */
	public final function isOneOf(array $list) {
		$b = false ;
		foreach($list as $l) {
			$b = $this->equals($l) ;
			if($b) {
				break ;
			}
		}
		return $b ;
	}
	
	public function serialize() {
		return $this->ordinal . ':' . $this->name ;
	}

	public function unserialize($serialized) {
		$e = explode(':', $serialized) ;
		$this->ordinal = $e[0] ;
		$this->name = $e[1] ;
	}
	
	/**
	 * @return \ReflectionClass
	 */
	public static function _class() {
		return new \ReflectionClass(static::class) ;
	}
	
	/**
	 * TODOC
	 *
	 * @return array :
	 */
	public static function getAll() {
		return self::$_all[static::class] ;
	}

	/**
	 * 
	 * @param int $ordinal
	 * @return 
	 */
	public static function getByOrdinal($ordinal) {
		return _iod(self::$_all[static::class], $ordinal) ;
	}

	protected static function _initEnumeration() {
		$c = static::class ;
		$rc = new \ReflectionClass($c);
		$sps = $rc->getStaticProperties();
		foreach ($sps as $n => $sp) {
			$p = $rc->getProperty($n);
			if ($p->isPublic()) {
				$p->setValue(new $c($p->getName()));
			}
		}
	}
	
}
