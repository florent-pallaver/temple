<?php

namespace temple ;

/**
 * TODOC
 *
 * @author florent
 */
trait ToImplement {

	/**
	 * @var string - the static no-args method name called to get an implementation instance.
	 */
	private static $GET_INSTANCE_METHOD_NAME = 'getInstance' ;

	/**
	 * @var string - the sub-namespace where to find the implementation class.
	 */
	private static $SUB_NAMESPACE = 'impl' ;

	/**
	 * @var string - the implementation class name prefix.
	 */
	private static $IMPLEMENTATION_PREFIX = '' ;

	/**
	 * @var string - the implementation class name suffix.
	 */
	private static $IMPLEMENTATION_SUFFIX = 'Impl' ;

	private static $_instance ;
	
	/**
	 * Constructor.
	 */
	protected function __construct() {}

	/**
	 * TODOC
	 * @return an instance of this class.
	 */
	public static function getInstance() {
		if(!self::$_instance) {
			$scn = util\Util::getSimpleClassName(__CLASS__) ;
			if(self::$SUB_NAMESPACE{0} === '\\') {
				$ns = self::$SUB_NAMESPACE ;
			} else {
				$ns = util\Util::getNamespace(__CLASS__) . '\\' . self::$SUB_NAMESPACE ;
			}
			$imp = $ns . '\\' . self::$IMPLEMENTATION_PREFIX . $scn . self::$IMPLEMENTATION_SUFFIX ;
			\temple\Logger::getInstance()->config('Using ' . $imp . ' as implementation of ' . __CLASS__) ;
			$rm = new \ReflectionMethod($imp, self::$GET_INSTANCE_METHOD_NAME) ;
			self::$_instance = $rm->invoke(null) ;
		}
		return self::$_instance ;
	}

}
