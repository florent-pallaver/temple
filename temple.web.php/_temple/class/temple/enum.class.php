<?php

namespace temple;

/**
 * TODOC
 * <br>
 * If in need to define your own <code>_init()</code> function, make sure to call <code>self::_enumInit()</code> to perform the initialization provided by this trait.
 * @author florent
 */
trait Enum {

	use Reflection ;
	
	/** @var array */
	private static $_all = [];

	/** @var number */
	private $ordinal;

	/** @var string */
	private $name;

	private function __construct($name) {
		$this->ordinal = count(self::$_all);
		$this->name = $name;
		self::$_all[] = $this;
	}

	/**
	 * TODOC
	 */
	public final function getOrdinal() {
		return $this->ordinal;
	}

	/**
	 * TODOC
	 */
	public final function getName() {
		return $this->name;
	}

	public final function equals($other) {
		return self::_class()->isInstance($other) && ($this->ordinal === $other->ordinal) ;
	}
	
	/**
	 * TODOC
	 *
	 * @return array :
	 */
	public static function getAll() {
		return self::$_all;
	}

	/**
	 * @return 
	 */
	public static function getByOrdinal($ordinal) {
		return _iod(self::$_all, $ordinal) ;
	}

	private static function _initEnum() {
		$c = __CLASS__;
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
