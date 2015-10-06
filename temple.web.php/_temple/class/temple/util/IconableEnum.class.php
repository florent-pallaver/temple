<?php

namespace temple\util ;

/**
 * TODOC
 * <br>
 * If in need to define your own <code>_init()</code> function, make sure to call <code>self::_enumInit()</code> to perform the initialization provided by this trait.
 * @author florent
 */
trait IconableEnum {

	use \temple\Enum ;
	
	/** @var array */
	private static $_icons = [];

	public final function getIcon() {
		return _iod(self::$_icons, $this->getOrdinal(), 'question');
	}

}
