<?php

namespace temple\web;

/**
 * Description of Config
 *
 * @author florent
 */
abstract class Config {

	const TEMPLE_RESOURCE_PATH = '/_temple/resources/';

	const CUSTOM_RESOURCE_PATH = '/_custom/resources/';

	// FIXME we should load the modules here and manage them from here
	
	private function __construct() {}
	
}
