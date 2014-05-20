<?php

namespace temple\data\persistence\model ;

use temple\data\persistence\db\query\QueryFactory ;

/**
 * TODOC
 *
 * @author florent
 */
class KeyFilter extends Filter{

	/**
	 * 
	 * @param \temple\data\persistence\model\Key $key
	 * @param type $value
	 * @param array $orders
	 * @param type $maxCount
	 * @param type $offset
	 */
	public function __construct(Key $key, $value, array $orders = [], $maxCount = 0, $offset = 0) {
		parent::__construct($key->getClass(), QueryFactory::getInstance()->newKeyComparison($key, $value), $orders, $maxCount, $offset) ;
	}

}


