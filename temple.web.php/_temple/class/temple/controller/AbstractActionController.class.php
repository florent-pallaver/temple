<?php

namespace temple\controller;

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
	
	private $goTo ;
	
	public function __construct($includeMessages = true, $reload = false, $goTo = '') {
		parent::__construct();
		$this->includeMessages = !$reload && $includeMessages ;
		$this->reload = $reload ;
		$this->goTo = $goTo ;
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
	 * @param type $goTo
	 */
	protected final function setGoTo($goTo) {
		$this->goTo = $goTo ;
	}
	
	/**
	 * @throws \temple\controller\ActionException
	 * @throws \temple\data\persistence\db\DBException 
	 */
	protected abstract function processRequest() ;
	
	public final function createResponse() {
		try {
			$this->processRequest() ;
			$r = Status::$SUCCESS ;
			$im = $this->includeMessages ;
		} catch (\Exception $e) {
			\temple\ExceptionHandler::log($e) ;
			$this->error($e->getMessage(), $e->getPrevious()) ;
			$r = Status::$ERROR ;
			$im = true ;
		}
		return new JSONResult($r, $im, $this->data, $this->reload, false, $this->goTo) ;
	}
	
}
