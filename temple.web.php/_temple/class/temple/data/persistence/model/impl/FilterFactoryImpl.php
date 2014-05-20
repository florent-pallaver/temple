<?php

namespace temple\data\persistence\model\impl;

/**
 * Description of FilterFactoryImpl
 *
 * @author florent
 */
final class FilterFactoryImpl {

	use \temple\Singleton ;
	
	public function create(\ReflectionClass $root) {
		new \temple\data\persistence\model\Filter() ;
	}
	
//	public function createMapping
//	
}
