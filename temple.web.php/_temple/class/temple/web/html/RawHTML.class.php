<?php

namespace temple\web\html;

/**
 * Description of RawHTML
 *
 * @author flominou
 */
final class RawHTML extends AbstractHTMLElement {

	private $raw ;
	
	private function __construct($raw) {
		$this->raw = $raw;
	}
	
	protected function _render() {
		echo $this->raw ;
	}

	protected function _toString() {
		return $this->raw ;
	}

	public static final function create($raw) {
		return new RawHTML($raw) ;
	}
	
}
