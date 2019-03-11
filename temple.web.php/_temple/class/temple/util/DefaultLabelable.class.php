<?php

namespace temple\util;

/**
 * Simple implementation of Labelable
 *
 * @author flominou
 * @see Labelable
 */
class DefaultLabelable implements Labelable {

	private $icon ;
	
	private $name ;
	
	/**
	 * Constructor.
	 * 
	 * @param string $icon
	 * @param string $name
	 */
	public function __construct($icon, $name) {
		$this->icon = $icon;
		$this->name = $name;
	}

	public function getIcon() {
		return $this->icon;
	}

	public function getName() {
		return $this->name;
	}
	
}
