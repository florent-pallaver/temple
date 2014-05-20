<?php

namespace temple\web\html;

/**
 * Description of HTMLElementList
 *
 * @author florent
 */
class HTMLElementList extends AbstractHTMLElement {

	/**
	 * @var array 
	 */
	private $elements ;

	/**
	 * @param array $elements
	 */
	public function __construct(array $elements = []) {
		$this->elements = $elements ;
	}
	
	/**
	 * @return boolean
	 */
	public function hasElement() {
		return (boolean)$this->elements ;
	}
	
	/**
	 * 
	 * @param HTMLElement $e
	 * @param scalar $key
	 * @return HTMLElementList
	 */
	public function addElement(HTMLElement $e = null, $key = null) {
		if($e) {
			$this->elements[$key === null ? count($this->elements) : $key] = $e ;
		}
		return $this ;
	}
	
	/**
	 * 
	 * @param scalar $key
	 * @return HTMLElement
	 */
	public function getElement($key) {
		return _iod($this->elements, $key) ;
	}
	
	protected function _toString() {
		return implode('', $this->elements) ;
	}

	protected function _render() {
		foreach($this->elements as $e) {
			$e->render() ;
		}
	}
}
