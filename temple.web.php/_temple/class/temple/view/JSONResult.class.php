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
	
	private $messages ;

	private $reload ;
	
	function __construct(Status $status, $messages = true, array $data = [], $reload = false) {
		$this->status = $status->getOrdinal();
		$this->data = $data;
		$this->messages = $messages ? Messages::getInstance()->popAll() : [] ;
		$this->reload = $reload ;
	}

	public function jsonSerialize() {
		return ['status'=>$this->status, 'data'=>$this->data, 'messages'=>$this->messages, 'reload'=>$this->reload] ;
	}

	public function render() {
		echo json_encode($this) ;
	}

}
