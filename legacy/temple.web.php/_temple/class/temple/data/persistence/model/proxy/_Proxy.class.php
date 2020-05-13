<?php

namespace temple\data\persistence\model\proxy;

/**
 *
 * @author flominou
 */
interface _Proxy extends \temple\data\persistence\model\Model {

	/**
	 * @return \temple\data\persistence\model\Model
	 */
	function _instance() ;
	
}
