<?php

namespace temple\data\persistence\model;

use temple\data\persistence\db\Driver;

/**
 * Description of ScalarFieldConverter
 *
 * @author florent
 */
class ScalarFieldConverter extends AbstractFieldConverter {

	use \temple\Singleton;

	protected function toDBValue0($notNullValue) {
		if(!is_scalar($notNullValue)) {
			$type = gettype($notNullValue) ;
			throw new \temple\ConfigurationException("value of type $type is not a scalar, set an appropriate FieldConverter") ;
		}
		return is_string($notNullValue) ? '\'' . Driver::getInstance()->escape($notNullValue) . '\'' : $notNullValue;
	}

	protected function toPHPValue0($notNullValue) {
		return $notNullValue;
	}

}
