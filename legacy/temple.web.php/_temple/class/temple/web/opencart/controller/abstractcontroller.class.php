<?php

namespace temple\web\opencart\controller ;

use temple\web\opencart\Helper;
use temple\Logger;

/**
 *
 * TODOC
 *
 * @author florent
 */
abstract class AbstractController extends \Controller {

	/**
	 * TODOC
	 *
	 * @param unknown $registry
	 */
	public function __construct($registry) {
		parent::__construct($registry) ;
		Helper::setRegistry($registry) ;
	}

	protected function jsonResponse(array $json = array()) {
		$this->response->setOutput(json_encode($json));
	}

}
