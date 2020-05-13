<?php

namespace temple\controller;

/**
 * Description of InvalidActionController
 *
 * @author florent
 */
final class InvalidActionController extends AbstractActionController {

	const DESCRIPTION = 'unable to process action' ;
	
	const REASON = 'Invalid request' ;
	
	public function getFailureMessage() {
		return self::DESCRIPTION ;
	}

	protected function processRequest() {
		$this->failure(self::REASON) ;
	}
	
}
