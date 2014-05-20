<?php

namespace temple\controller;

use temple\Logger ;
use temple\view\JSONResult ;
use temple\data\Status ;

/**
 * Description of AbstractActionController
 *
 * @author florent
 */
abstract class AbstractActionController extends AbstractRequestController {

	private $includeMessages ;
	
	private $data = [] ;

	private $reload ;
	
	public function __construct($includeMessages = true, $reload = false) {
		parent::__construct();
		$this->includeMessages = $includeMessages ;
		$this->reload = $reload ;
	}

	/**
	 * 
	 * @param type $key
	 * @param type $value
	 */
	protected final function setData($key, $value) {
		$this->data[$key] = $value ;
	}

	/**
	 * @throws \temple\controller\ActionException
	 */
	protected abstract function processRequest() ;
	
	public final function createResponse() {
		try {
			$this->processRequest() ;
			$r = Status::$SUCCESS ;
		} catch (\Exception $e) {
			Logger::getInstance()->debug("Exception:\n" . $e->getTraceAsString()) ;
			$this->error($e->getMessage(), $e->getPrevious()) ;
			$r = Status::$ERROR ;
		}
		return new JSONResult($r, $this->includeMessages, $this->data, $this->reload) ;
	}
	
}
