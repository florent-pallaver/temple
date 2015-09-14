<?php

namespace temple\view;

use temple\data\Status ;
use temple\data\Messages ;

/**
 * Description of JSONResult
 *
 * @author florent
 */
final class JSONResult implements Renderable, \JsonSerializable {

	private $status ;
	
	private $data ;
	
	private $feedbacks ;
	
	private $messages ;

	private $reload ;
	
	private $resetForm ;
	
	private $goTo ;
	
	function __construct(Status $status, $messages = true, array $data = [], array $feedbacks =[], $reload = false, $resetForm = false, $goTo = '') {
		$this->status = $status ? $status->getOrdinal() : 0 ;
		$this->data = $data;
		$this->feedbacks = $feedbacks ;
		$this->messages = $messages ? Messages::getInstance()->popAll() : [] ;
		$this->reload = $reload ;
		$this->resetForm = $resetForm ;
		$this->goTo = $goTo ;
	}

	public function jsonSerialize() {
		return ['status'=>$this->status, 'data'=>$this->data, 'feedbacks'=>  $this->feedbacks, 'messages'=>$this->messages, 
			'reload'=>$this->reload, 'resetForm'=>$this->resetForm, 'goTo'=>$this->goTo] ;
	}

	public function render() {
		echo json_encode($this) ;
	}

}
