<?php

namespace temple\web\html ;

/**
 * TODOC
 *
 * @author florent
 */
class HTMLString extends AbstractHTMLElement {

	public static $NBSP ;
	
	private $string ;

	private $formattedString ;

	/**
	 * Constructor.
	 *
	 * @param string $string - TODOC
	 * @param boolean $formatted - TODOC
	 */
	public function __construct($string, $formatted = false) {
		$this->string = $string ;
		$this->formattedString = $formatted ? $string : NULL ;
	}

	protected function _toString() {
		if($this->formattedString === NULL) {
			$this->formattedString = $this->format($this->string) ;
		}
		return $this->formattedString ;
	}

	protected function _render() {
		echo $this->toString() ;
	}
	
	/**
	 * Format the given string.
	 *
	 * @param string $string the string to format
	 * @return the formatted string.
	 */
	protected function format($string) {
		return htmlspecialchars($string);
	}

	private static function _init() {
		self::$NBSP = new HTMLString('&nbsp;', true) ;
	}
	
}
