<?php

namespace temple ;

/**
 * TODOC
 *
 * @author florent
 */
abstract class WithLogger {

	/**
	 * @var \temple\Logger
	 */
	protected $logger ;

	protected function __construct() {
		$this->logger = Logger::getInstance(static::class) ;
	}

}
