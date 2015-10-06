<?php

namespace temple\web\html;

/**
 * Description of AbstractHTMLElement
 *
 * @author florent
 */
abstract class AbstractHTMLElement implements HTMLElement {

	private $renderObservers = [] ;
	
	public function addRenderListener(RenderObserver $ro) {
		$this->renderObservers[] = $ro ;
	}

	public function removeRenderListener(RenderObserver $ro) {}

	public function toString() {
		$this->_preRender() ;
		return $this->_toString() ;
	}

	protected abstract function _toString() ;
	
	public function render() {
		$this->_preRender() ;
		$this->_render() ;
	}

	protected abstract function _render() ;

	private function _preRender() {
		foreach($this->renderObservers as $ro) {
			$ro->preRender($this) ;
		}
	}
	
	public final function __toString() {
		return $this->toString() ;
	}
	
	/**
	 * 
	 * @param mixed $x
	 * @return HTMLElement
	 */
	protected final function toHTMLElement($x) {
		return HTMLUtil::toHTMLElement($x) ;
	}
	
}
