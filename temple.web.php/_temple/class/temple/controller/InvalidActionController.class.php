<?php

namespace temple\controller;

/**
 * Description of InvalidActionController
 *
 * @author florent
 */
final class InvalidActionController extends AbstractActionController {

	const DESCRIPTION = 'process action' ;
	
	const REASON = 'Invalid request' ;
	
	public function getActionDescription() {
		return self::DESCRIPTION ;
	}

	protected function processRequest() {
		$this->failure(self::REASON) ;
	}
	
}
