<?php

namespace temple;

/**
 * Description of AbstractEnum
 *
 * @author florent
 */
abstract class AbstractEnum implements \Serializable {
	
	/** @var number */
	private $ordinal;

	/** @var string */
	private $name;

	protected function __construct($ordinal, $name) {
		$this->ordinal = $ordinal;
		$this->name = $name;
	}
	
	/**
	 * @return int TODOC
	 */
	public final function getOrdinal() {
		return $this->ordinal;
	}

	/**
	 * @return string TODOC
	 */
	public final function getName() {
		return $this->name;
	}

	public function serialize() {
		$c = get_class($this) ;
		return $c . ':' . $this->ordinal ;
	}

	public function unserialize($serialized) {
		
	}

	
}
