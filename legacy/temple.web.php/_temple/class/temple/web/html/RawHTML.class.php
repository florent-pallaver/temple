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

	/**
	 * 
	 * @param mixed $raw
	 * @return \temple\web\html\RawHTML
	 */
	public static final function create($raw) {
		return new RawHTML($raw) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\HTMLElement $e
	 * @return \temple\web\html\RawHTML
	 */
	public static final function fromHTMLElement(HTMLElement $e) {
		return new RawHTML($e->toString()) ;
	}
	
}
