<?php

namespace temple\web\html\bootstrap;

/**
 * Description of AbstractList
 *
 * @author florent
 */
abstract class AbstractList extends AbstractComponent {

	/**
	 * Constructor 
	 * 
	 * @param boolean $ordered
	 * @param string $cssClass
	 */
	public function __construct($ordered = false, $cssClass = null) {
		parent::__construct($ordered ? 'ol' : 'ul', $cssClass);
	}

	/**
	 * 
	 * @param mixed $item
	 * @param string $cssClass
	 * @return AbstractList
	 */
	public abstract function addItem($item = null, $cssClass = null) ;

	/**
	 * 
	 * @param array $items
	 * @return AbstractList
	 */
	public final function addItems(array $items) {
		foreach($items as $i) {
			$this->addItem($i) ;
		}
		return $this ;
	}
	
	/**
	 * 
	 * @return AbstractList
	 */
	public final function unstyled() {
		$this->addCssClass('list-unstyled') ;
		return $this ;
	}
	
	/**
	 * 
	 * @return AbstractList
	 */
	public final function inline() {
		$this->addCssClass('list-inline') ;
		return $this ;
	}
	
}
